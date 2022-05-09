package LeetCode;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

// This class is for the Login page of a logged out user

public class LoginPage {

  protected WebDriver driver;
  private WebDriverWait wait;

  // Initializing the locators to get elements by xpath
  private final By usernameBox = By
      .xpath("/html/body/div[1]/div/div[2]/div/div[2]/div/div/div/form/span[1]/input");
  private final By passwordBox = By
      .xpath("/html/body/div[1]/div/div[2]/div/div[2]/div/div/div/form/span[2]/input");
  private final By loginButton = By
      .xpath("/html/body/div[1]/div/div[2]/div/div[2]/div/div/div/button/div");

  // Method to get the elements by their locator
  private WebElement waitVisibiiltyAndFindElement(By locator) {
    this.wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    return this.driver.findElement(locator);
  }

  // Initializing the page driver and wait
  public LoginPage(WebDriver driver, WebDriverWait wait) {
    this.driver = driver;
    this.wait = wait;
  }

  // Logging a user in with a given valid username and password
  public DashboardPage login(String userName, String pass) {
    WebElement username = waitVisibiiltyAndFindElement(usernameBox);
    username.clear();
    username.sendKeys(userName);
    WebElement password = waitVisibiiltyAndFindElement(passwordBox);
    password.clear();
    password.sendKeys(pass);
    WebElement login = waitVisibiiltyAndFindElement(loginButton);
    login.click();
    // Capcha might stop the process
    return new DashboardPage(driver, wait);
  }
}
