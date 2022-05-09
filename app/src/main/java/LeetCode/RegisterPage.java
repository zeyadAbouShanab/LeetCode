package LeetCode;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

// This class is for the Register page of a logged out user

public class RegisterPage {
        protected WebDriver driver;
        private WebDriverWait wait;

        // Initializing the locators to get elements by xpath or css-selector
        private final By copyEmailLocator = By
                        .xpath("//*[@id='copbtn']");
        private final By nameBoxLocator = By
                        .xpath("//*[@id='app']/div/div[2]/div/div[2]/div/div/div/form/span[1]/input");
        private final By passwordBoxLocator = By
                        .xpath("//*[@id='app']/div/div[2]/div/div[2]/div/div/div/form/span[2]/input");
        private final By confirmPasswordBoxLocator = By
                        .xpath("//*[@id='app']/div/div[2]/div/div[2]/div/div/div/form/span[3]/input");
        private final By emailBoxLocator = By
                        .xpath("//*[@id='app']/div/div[2]/div/div[2]/div/div/div/form/span[4]/input");
        private final By studentImageLocator = By
                        .xpath("//*[@id='__next']/div/div/div[2]/div[1]/img");
        private final By skipButtonLocator = By
                        .xpath("//*[@id='__next']/div/div/div[1]/img");
        private final By signUpButtonLocator = By
                        .xpath("//*[@id='app']/div/div[2]/div/div[2]/div/div/div/button");
        private final By confirmLinkLocator = By
                        .cssSelector("a[href*='leetcode']");

        // Method to get the elements by their locator
        private WebElement waitVisibiiltyAndFindElement(By locator) {
                this.wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
                return this.driver.findElement(locator);
        }

        // Initializing the page driver and wait
        public RegisterPage(WebDriver driver, WebDriverWait wait) {
                this.driver = driver;
                this.wait = wait;
        }

        // Register new user method, please give a unique username and a strong password
        public DashboardPage register(String userName, String password) {
                // Opening new tab with the email generator and switching to it
                ((JavascriptExecutor) driver).executeScript("window.open()");
                ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
                driver.switchTo().window(tabs.get(1));
                driver.get("https://generator.email/");

                // Copying the generated email
                WebElement copyEmailElement = waitVisibiiltyAndFindElement(copyEmailLocator);
                copyEmailElement.click();

                // Going back to the base page and clicking register
                driver.switchTo().window(tabs.get(0));

                // Filling in the user data and clicking sign up
                WebElement nameBox = waitVisibiiltyAndFindElement(nameBoxLocator);
                nameBox.sendKeys(userName);
                WebElement passwordBox = waitVisibiiltyAndFindElement(passwordBoxLocator);
                passwordBox.sendKeys(password);
                WebElement confirmPasswordBox = waitVisibiiltyAndFindElement(confirmPasswordBoxLocator);
                confirmPasswordBox.sendKeys(password);
                WebElement emailBox = waitVisibiiltyAndFindElement(emailBoxLocator);
                emailBox.sendKeys(Keys.COMMAND + "v");
                WebElement signUpButton = waitVisibiiltyAndFindElement(signUpButtonLocator);
                JavascriptExecutor executor = (JavascriptExecutor) driver;
                executor.executeScript("arguments[0].click();", signUpButton);

                // Skipping the intro of the registration process
                WebElement studentImage = waitVisibiiltyAndFindElement(studentImageLocator);
                studentImage.click();
                WebElement skipButton = waitVisibiiltyAndFindElement(skipButtonLocator);
                skipButton.click();

                // Going back to the mail page and confirming the email
                driver.switchTo().window(tabs.get(1));
                WebElement confirmLink = waitVisibiiltyAndFindElement(confirmLinkLocator);
                confirmLink.click();
                driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

                // Closing the unrequired tabs and switching to the home page of a registered
                // user
                driver.switchTo().window(tabs.get(0));
                driver.close();
                driver.switchTo().window(tabs.get(1));
                driver.close();
                tabs = new ArrayList<String>(driver.getWindowHandles());
                driver.switchTo().window(tabs.get(0));

                return new DashboardPage(driver, wait);
        }
}
