package LeetCode;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

// This class is for the Home page of a logged out user

public class HomePage {
  protected WebDriver driver;
  private WebDriverWait wait;

  // Initializing the locators to get elements by xpath
  private final By loginButtonLocator = By
      .xpath("//*[@id='landing-page-app']/div/div[1]/div[3]/div[1]/div/div/div[2]/div/a[5]/span");
  private final By registerButtonLocator = By
      .xpath("//*[@id='landing-page-app']/div/div[1]/div[3]/div[2]/div/div/div[2]/div/a");

  // Method to get the elements by their locator
  private WebElement waitVisibiiltyAndFindElement(By locator) {
    this.wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    return this.driver.findElement(locator);
  }

  // Initializing the page driver and wait
  public HomePage(WebDriver driver, WebDriverWait wait) {
    this.driver = driver;
    this.wait = wait;
  }

  // Redirecting to the login page
  public LoginPage login() {
    WebElement loginButton = waitVisibiiltyAndFindElement(loginButtonLocator);
    loginButton.click();
    return new LoginPage(driver, wait);
  }

  // Redirecting to the register page
  public RegisterPage register() {
    WebElement registerButton = waitVisibiiltyAndFindElement(registerButtonLocator);
    registerButton.click();
    return new RegisterPage(driver, wait);
  }
}
