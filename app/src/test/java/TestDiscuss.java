import static org.junit.Assert.assertTrue;

import LeetCode.DiscussPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

// This test was written for the Discuss Page
public class TestDiscuss {
    private WebDriver driver;
    private WebDriverWait wait;
    private DiscussPage discussPage;

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
        this.driver.get("https://leetcode.com/discuss");
        discussPage = new DiscussPage(driver, wait);

    }

    @Test
    public void testPostViews() throws InterruptedException {
        String result = discussPage.getFirstPostViews("java");
        assertTrue(Float.parseFloat(result.substring(0, result.length() - 1)) >= 0);
    }

    @Test
    public void testRulesUpdate() {
        assertTrue(discussPage.getRulesUpdate().contains("Last Edit"));
    }

    @After
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }
}
