package dia.test.csssr;

import java.net.URL;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class Link {
  private WebDriver driver;
  private Wait<WebDriver> wait;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @BeforeClass(alwaysRun = true)
  public void setUp() throws Exception {

    System.setProperty("webdriver.chrome.driver", "D:\\selenium\\chromedriver.exe");

    driver = new ChromeDriver();//true to enable the JS

    wait = new WebDriverWait(driver, 3000);


    JavascriptExecutor js = (JavascriptExecutor) driver;

    }

  @Test
  public void testUntitledTestCase() throws Exception {
    driver.get("http://blog.csssr.ru/qa-engineer/");
    driver.findElement(By.linkText("НАХОДИТЬ НЕСОВЕРШЕНСТВА")).click();
    wait = new WebDriverWait(driver, 1000);
    Assert.assertEquals(driver.findElement(By.cssSelector(".info-errors")).getCssValue("display"), "block", "Проверка видимости блока при первом клике");

    Thread.sleep(1000);
    driver.findElement(By.linkText("НАХОДИТЬ НЕСОВЕРШЕНСТВА")).click();
    Thread.sleep(1000);
    Assert.assertEquals(driver.findElement(By.cssSelector(".info-errors")).getCssValue("display"), "block", "Проверка видимости блока при втором клике");

  }

  @AfterClass(alwaysRun = true)
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
