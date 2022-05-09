import static org.junit.Assert.assertEquals;

import LeetCode.DashboardPage;
import LeetCode.HomePage;
import LeetCode.LoginPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

// This test was written for the Login Page
public class TestLogin {
  private WebDriver driver;
  private WebDriverWait wait;
  private LoginPage loginPage;

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
    this.driver.get("https://leetcode.com/accounts/login/");

    // Logging the user out if he is already logged in
    if (!this.driver.getCurrentUrl().equals("https://leetcode.com/accounts/login/")) {
      new DashboardPage(driver, wait).logout();
      new HomePage(driver, wait).login();
    }

    loginPage = new LoginPage(driver, wait);
  }

  @Test
  public void testLogin() {
    loginPage.login("SeleniumUser", "seleniumIsFun1");
    
    // Explicit wait to get the current URL
    new WebDriverWait(driver, 5)
        .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='navbar-right-container']/div[4]/a")));
    assertEquals(driver.getCurrentUrl(), "https://leetcode.com/");
  }

  @After
  public void close() {
    if (driver != null) {
      driver.quit();
    }
  }
}
