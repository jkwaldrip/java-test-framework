package junit;

import com.codeborne.selenide.WebDriverRunner;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.lang.management.ManagementFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BrowserTest {


  // This method sets Chrome to run headlessly, unless we're in debug mode.
  // In debug mode, we assume that we want to be able to see what Selenium is doing.
  public void startHeadlessChrome() {

    // Here, we're getting the arguments for starting the Java process
    // If the list of arguments contains the command for debug mode,
    // this boolean will be set to true, otherwise it will have the
    // default value of false
    final boolean debugMode = ManagementFactory
        .getRuntimeMXBean()
        .getInputArguments()
        .toString()
        .indexOf("-agentlib:jdwp") > 0;

    // We're declaring that we want to start a Chromedriver manager session
    // Selenide starts Chromedriver by default, but we're starting it
    // here because we want to be able to take control of it.
    WebDriverManager.chromedriver().setup();

    // We'll set options on the Chrome instance we're going to start
    ChromeOptions chromeOptions = new ChromeOptions();

    // Set some options needed for headless mode to work
    chromeOptions.addArguments("--no-sandbox");
    chromeOptions.addArguments("--disable-dev-shm-usage");

    // Let's also set the Chrome window size!
    // This will be the size of the Chrome window
    // no matter what mode we're running in.
    chromeOptions.addArguments("--window-size=1300,830");

    // Set Chrome to run in headless mode, but only if we're not in debug mode!
    if(!debugMode) {
      chromeOptions.addArguments("--headless");
    }

    // This is where we actually start up our Chrome session
    WebDriver webDriver = new ChromeDriver(chromeOptions);
    WebDriverRunner.setWebDriver(webDriver);
  }


  // Since we started Chrome explicitly in the method above, we have to
  // also stop our Chrome session explicitly when a test is finished.
  public void stopChrome() {
    WebDriverRunner.getWebDriver().quit();
  }

}
