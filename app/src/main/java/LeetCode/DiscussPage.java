package LeetCode;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

// This class is for the Discuss page ( Doesn't need authentication )

public class DiscussPage {
  protected WebDriver driver;
  private WebDriverWait wait;

  // Initializing the locators to get elements by xpath
  private final By searchBoxLocator = By
      .xpath("//*[@id='discuss-container']/div/div/div[5]/div[1]/div/div[2]/div/span/input");
  private final By firstResultViewsLocator = By
      .xpath("//*[@id='discuss-container']/div/div/div[5]/div[1]/div/div[3]/div/div[2]/div[1]/div/div[2]/div[2]/div");
  private final By rulesLocator = By
      .xpath("//*[@id='discuss-container']/div/div/div[4]/div/a");
  private final By lastUpdateLocator = By
      .xpath("//*[@id='discuss-container']/div/div/div/div[2]/div[1]/div[2]/div[1]/div/div/div[2]/p[1]");
  // Method to get the elements by their locator
  private WebElement waitVisibiiltyAndFindElement(By locator) {
    this.wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    return this.driver.findElement(locator);
  }

  // Initializing the page driver and wait
  public DiscussPage(WebDriver driver, WebDriverWait wait) {
    this.driver = driver;
    this.wait = wait;
  }

  // Getting the number of views for the first post with the given keyword
  public String getFirstPostViews(String keyword) {
    WebElement search = waitVisibiiltyAndFindElement(searchBoxLocator);
    search.clear();
    search.clear();
    search.sendKeys(keyword);
    WebElement views = waitVisibiiltyAndFindElement(firstResultViewsLocator);
    System.out.println("Views is equal to = " + views.getText());
    return views.getText();
  }

  // Getting the date of the last edit to the rules
  public String getRulesUpdate() {
    WebElement rules = waitVisibiiltyAndFindElement(rulesLocator);
    rules.click();
    WebElement updateDate = waitVisibiiltyAndFindElement(lastUpdateLocator);
    return updateDate.getText();
  }
}
