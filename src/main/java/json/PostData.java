package json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostData {

  @SerializedName("userId")
  @Expose
  private Integer userId;
  @SerializedName("id")
  @Expose
  private Integer id;
  @SerializedName("title")
  @Expose
  private String title;
  @SerializedName("body")
  @Expose
  private String body;

  /**
   * No args constructor for use in serialization
   *
   */
  public PostData() {
  }

  /**
   *
   * @param id
   * @param body
   * @param title
   * @param userId
   */
  public PostData(Integer userId, Integer id, String title, String body) {
    super();
    this.userId = userId;
    this.id = id;
    this.title = title;
    this.body = body;
  }

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public PostData withUserId(Integer userId) {
    setUserId(userId);
    return this;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public PostData withId(Integer id) {
    setId(id);
    return this;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public PostData withTitle(String title) {
    setTitle(title);
    return this;
  }

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }

  public PostData withBody(String body) {
    setBody(body);
    return this;
  }

}