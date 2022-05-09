package LeetCode;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

// This class is for the Dashboard page of a logged in user

public class DashboardPage {
    protected WebDriver driver;
    private WebDriverWait wait;

    // Initializing the locators to get elements by xpath
    private final By profileIconLocator = By
            .xpath("//*[@id='navbar-right-container']/div[4]/a");
    private final By logoutButtonLocator = By
            .xpath("//html/body/div[7]/div/div/ul/li[11]/div");
    private final By discussButtonLocator = By
            .xpath("// *[@id='navbar-root']/div/div/div[1]/div[6]/a");
    private final By problemsButtonLocator = By
            .xpath("//*[@id='navbar-root']/div/div/div[1]/div[3]/a");
    private final By getRandomLocator = By
            .xpath("//*[text()='Pick One']");
    private final By problemTitleLocator = By
            .xpath("//*[@id='app']/div/div[2]/div[1]/div/div[1]/div/div[1]/div[1]/div/div[2]/div/div[1]/div[1]");

    // Method to get the elements by their locator
    private WebElement waitVisibiiltyAndFindElement(By locator) {
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return this.driver.findElement(locator);
    }

    // Initializing the page driver and wait
    public DashboardPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    // Opening the discuss page from dashboard
    public DiscussPage discuss() {
        WebElement discuss = waitVisibiiltyAndFindElement(discussButtonLocator);
        discuss.click();
        return new DiscussPage(driver, wait);

    }

    // Getting a random programming problem title
    public String getRandomProblemTitle() {
        WebElement problemsButton = waitVisibiiltyAndFindElement(problemsButtonLocator);
        problemsButton.click();
        WebElement randomButton = waitVisibiiltyAndFindElement(getRandomLocator);
        randomButton.click();
        WebElement problemTitle = waitVisibiiltyAndFindElement(problemTitleLocator);
        return problemTitle.getText();
    }

    // Loggong the user out and going to the homepage
    public void logout() {
        WebElement profile = waitVisibiiltyAndFindElement(profileIconLocator);
        profile.click();
        WebElement logout = waitVisibiiltyAndFindElement(logoutButtonLocator);
        logout.click();
    }
}
