package selenide;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

import junit.BrowserTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SelenideTest extends BrowserTest {

  @Before
  public void setup() {
    // We're running Chrome invisibly in the background!
    //
    // If you select "debug" instead of run, you'll still
    // see the Chrome window.
    //
    // See src/main/junit/BrowserTest for more!
    startHeadlessChrome();
  }

  @Test
  public void entersANumber() {
    // Navigate to the Inputs page of The Internet @ Herokuapp.com
    open("https://the-internet.herokuapp.com/inputs");

    // Wait for the input field to be enabled
    // Set a number in the input field
    // Check that the value of the input field has been set
    $(".example>input")
        .shouldBe(enabled)
        .setValue("47")
        .shouldHave(value("47"));
  }


  @After
  public void teardown() {
    stopChrome();
  }
}
