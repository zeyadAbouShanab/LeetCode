import static org.junit.Assert.assertEquals;

import LeetCode.DashboardPage;
import LeetCode.HomePage;
import LeetCode.RegisterPage;
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

// This test was written for the Register Page
public class TestRegister {
    private WebDriver driver;
    private WebDriverWait wait;
    private RegisterPage registerPage;

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
        wait = new WebDriverWait(driver, 30);
        this.driver.get("https://leetcode.com/accounts/signup/");
        // Logging the user out if he is already logged in
        if (!this.driver.getCurrentUrl().equals("https://leetcode.com/accounts/signup/")) {
            new DashboardPage(driver, wait).logout();
            new HomePage(driver, wait).register();
        }
        registerPage = new RegisterPage(driver, wait);

    }

    @Test
    public void testRegister() throws InterruptedException {
        // Making sure a user is created and can log out after registration
        // Providing unique username and strong password
        DashboardPage dashboardPage = registerPage.register("newSeleniumUserTest2022", "reallyStrongPass5432");
        Thread.sleep(5000);
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
