package selenide;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

import org.junit.Test;

public class SelenideTest {

  @Test
  public void entersANumber() {
    open("https://the-internet.herokuapp.com/inputs");

    $(".example>input")
        .shouldBe(enabled)
        .setValue("47")
        .shouldHave(value("47"));



  }

}
