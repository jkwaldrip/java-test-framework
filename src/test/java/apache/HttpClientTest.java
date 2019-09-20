package apache;

import static io.netty.handler.codec.http.HttpHeaders.Values.APPLICATION_JSON;
import static org.apache.http.HttpHeaders.CONTENT_TYPE;
import static org.assertj.core.api.Assertions.assertThat;

import com.google.gson.Gson;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import json.PostData;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.junit.Before;
import org.junit.Test;

public class HttpClientTest {

  private static final String POSTS = "https://jsonplaceholder.typicode.com/posts/";
  private Gson gson = new Gson();
  private HttpClient httpClient;

  @Before
  public void buildClient() {
    // Create a cookie store
    BasicCookieStore cookieStore = new BasicCookieStore();

    // Build Apache Http Client to execute requests
    this.httpClient = HttpClientBuilder.create()
            .setDefaultCookieStore(cookieStore)
            .setRedirectStrategy(new LaxRedirectStrategy())
            .build();
  }

  @Test
  public void getPosts() throws IOException {
    // Create an HTTP Get Request
    HttpGet get = new HttpGet(POSTS + "1");

    // Execute the HTTP Get Request with the client
    HttpResponse response = this.httpClient.execute(get);

    // Get a PostData class instance from the response
    PostData postData = getPostData(response);

    // Make assertions about the response
    assertThat(response.getStatusLine().getStatusCode())
        .isEqualTo(200);

    // Make assertions about the PostData
    assertThat(postData.getId()).isEqualTo(1);
    assertThat(postData.getUserId()).isEqualTo(1);
    assertThat(postData.getBody()).isNotBlank();
    assertThat(postData.getTitle()).isNotBlank();
  }

  @Test
  public void createPost() throws IOException {
    // Create an instance of the PostData class
    PostData postData = new PostData()
        .withId(101)
        .withUserId(1)
        .withTitle("New Post")
        .withBody("This is the body of the post.");

    // Create an HTTP Post Request
    HttpPost post = new HttpPost(POSTS);

    // Set the "Content-type" header of the
    // HTTP Post Request to "Application/json; charset=UTF-8"
    post.setHeader(
        CONTENT_TYPE,
        "Application/json; charset=UTF-8"
    );

    // Create a String Entity out of the JSON data and add it
    // to the HTTP Post Request.
    //
    // This will serialize the JSON data into a String
    post.setEntity(
        new StringEntity(
            this.gson.toJson(postData)
        )
    );

    // Execute the HTTP Post with the client
    HttpResponse response = this.httpClient.execute(post);

    // Get a PostData class instance from the response
    PostData responsePostData = getPostData(response);

    // Make assertions about the response
    assertThat(response.getStatusLine().getStatusCode())
        .isEqualTo(201);

    // Make assertions about the data returned
    assertThat(responsePostData.getId()).isEqualTo(101);
    assertThat(responsePostData.getUserId()).isEqualTo(1);
    assertThat(responsePostData.getTitle())
        .isEqualTo(postData.getTitle());
    assertThat(responsePostData.getBody())
        .isEqualTo(postData.getBody());
  }

  // Get the content of the response
  private String getContent(HttpResponse response) throws IOException {
    return IOUtils.toString(
        response.getEntity().getContent(),
        StandardCharsets.UTF_8
    );
  }

  // De-serialize JSON data from the response
  private PostData getPostData(HttpResponse response) throws IOException {
    return this.gson.fromJson(
        getContent(response),
        PostData.class
    );
  }
}
