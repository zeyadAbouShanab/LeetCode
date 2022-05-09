import static org.junit.Assert.assertEquals;

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

// This test was written for the Home Page
public class TestHome {
    private WebDriver driver;
    private WebDriverWait wait;
    private HomePage homePage;

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

        // Logging the user out if logged in
        try {
            new WebDriverWait(driver, 5)
                    .until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='we-hire']/a/p")));
        } catch (TimeoutException e) {
            new DashboardPage(driver, wait).logout();
        }

        homePage = new HomePage(driver, wait);
    }

    @Test
    public void testLogin() {
        homePage.login();
        // Explicit wait to get the current URL
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable(
                        By.xpath("/html/body/div[1]/div/div[2]/div/div[2]/div/div/div/button/div")));
        assertEquals(driver.getCurrentUrl(), "https://leetcode.com/accounts/login/");
    }

    @Test
    public void testRegister() {
        homePage.register();
        // Explicit wait to get the current URL
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//*[@id='app']/div/div[2]/div/div[2]/div/div/div/form/span[1]/input")));
        assertEquals(driver.getCurrentUrl(), "https://leetcode.com/accounts/signup/");
    }

    @After
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }
}
