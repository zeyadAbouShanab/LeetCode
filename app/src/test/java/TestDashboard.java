import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import LeetCode.DashboardPage;
import LeetCode.HomePage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;

// This test was written for the Dashboard Page
public class TestDashboard {
  private WebDriver driver;
  private WebDriverWait wait;
  private DashboardPage dashboardPage;

  @Before
  public void setup() {
    System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");

    WebDriverManager.chromedriver().setup();
    ChromeOptions options = new ChromeOptions();
    options.addArguments("start-maximized");
    options.addArguments("disable-infobars");
    options.addArguments("--disable-extensions");
    options.addArguments("user-data-dir=/Users/user/Library/Application");

    driver = new ChromeDriver(options);
    wait = new WebDriverWait(driver, 20);

    this.driver.get("https://leetcode.com/");

    // Logging in if the user is not logged in
    try {
      new WebDriverWait(driver, 5)
          .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='we-hire']/a/p")));
      dashboardPage = new HomePage(driver, wait).login().login("SeleniumUser", "seleniumIsFun1");
    } catch (TimeoutException e) {
      dashboardPage = new DashboardPage(driver, wait);
    }
  }

  @Test
  public void testProblemTitle() {
    System.out.println(dashboardPage.getRandomProblemTitle());
    assertTrue(driver.getCurrentUrl().contains("https://leetcode.com/problems/"));
  }

  @Test
  public void testDiscuss() {
    dashboardPage.discuss();
    assertTrue(driver.getCurrentUrl().contains("https://leetcode.com/discuss"));
  }

  @Test
  public void testLogout() {
    dashboardPage.logout();

    // Getting element which is visible only in the homepage to mkae sure we reached
    // the home page
    String text = new WebDriverWait(driver, 5)
        .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='we-hire']/a/p"))).getText();

    assertEquals("Join Our Team  ", text);

  }

  @After
  public void close() {
    if (driver != null) {
      driver.quit();
    }
  }
}
