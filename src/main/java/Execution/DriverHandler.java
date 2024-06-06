package Execution;

import java.io.File;
import java.time.Duration;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import models.Action;
import models.Browser;
import models.LocatorType;

public class DriverHandler {

	public static WebDriver driver;
	public static Browser browser;
	
	public DriverHandler(WebDriver _driver) {
		driver = _driver;
	}
	
	public static void setSystemProperty(Browser broswer) {
		String property;
		String pathToWebDriver;
		
		switch(broswer) {
		case Chrome:
			property = "webdriver.chrome.driver";
			pathToWebDriver = "C:\\Hexaware\\TALOS Utility\\JavaCABuild\\Drivers\\chromedriver.exe";
			break;
		case Edge:
			property = "webdriver.msedge.driver";
			pathToWebDriver = "C:\\Hexaware\\TALOS Utility\\JavaCABuild\\Drivers\\msedgedriver.exe";
			break;
		case Firefox:
			property = "webdriver.gecko.driver";
			pathToWebDriver = "PathToGeckoDriver.exe";
			break;
		case Safari:
			property = "webdriver.safari.driver";
			pathToWebDriver = "PathToDriverDriver.exe";
			break;
		default:
			property = "webdriver.chrome.driver";
			pathToWebDriver = "C:\\Hexaware\\TALOS Utility\\JavaCABuild\\Drivers\\chromedriver.exe";
			break;
		}
		System.setProperty(property, pathToWebDriver);
	}
	
	public static WebDriver createDriver(Browser _browser) {
		
		browser = _browser;
		
		ChromeOptions chromeOptions = new ChromeOptions();	
		switch(_browser) {
		case Chrome:
			chromeOptions.addArguments("--remote-allow-origins=*");
			chromeOptions.addArguments("--start-maximized");
			return new ChromeDriver(chromeOptions);
		case Firefox:
			return new FirefoxDriver();
		case Edge:
			return new EdgeDriver();
		case Safari:
			return new SafariDriver();
		default:
			chromeOptions.addArguments("--remote-allow-origins=*");
			chromeOptions.addArguments("--start-maximized");
			return new ChromeDriver(chromeOptions);
		}
	}
	
	public static void hightlightElement(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].style.outline = '#f00 solid 5px'", element);
	}
	
	public static void clearHightlightElement(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].style.outline = 'none'", element);
	}
	
	public static WebElement findElement(LocatorType locatorType, String locatorValue) throws Exception {
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		
		WebElement element = null;
		switch(locatorType) {
		case Id:
			element = wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.id(locatorValue)));
			break;
		case Xpath:
			element = wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath(locatorValue)));
			break;
		case Name:
			element = wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.name(locatorValue)));
			break;
		case CSSSelector:
			element = wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.cssSelector(locatorValue)));
			break;
		case ClassName:
			element = wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.className(locatorValue)));
			break;
		default:
			throw new Exception ("Invalid locator Type " + locatorType);
		}
		if (element != null) {
			hightlightElement(element);
		}
		
		return element;
	}
	
	public static void takeScreenshot(boolean flag, String stepName) throws Exception {
		if (flag) {
			String folder = "D:\\01-Trainings\\03-SeleniumFTraining\\SeleniumFCourse\\\\screenshots\\%s.png";
			String path = String.format(folder, stepName);
			TakesScreenshot screenshotDriver = (TakesScreenshot)driver;
			File ssFile = screenshotDriver.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(ssFile, new File(path));
		}
	}
	
	public static void performAction(Action action, String value, LocatorType locatorType, String locatorValue) throws Exception {
		
		WebElement element = null;
		if (locatorType != null && locatorValue != null) { 
			element = findElement(locatorType, locatorValue); 
			hightlightElement(element);
		}
		
		switch(action) {
		case AlertAccept:
			driver.switchTo().alert().accept();
			break;
		case AlertAcceptIfExist:
			if (isAlertPresent()) {
				driver.switchTo().alert().accept();
			}
			break;
		case AlertGetText:
			String alertTextVal = driver.switchTo().alert().getText();
			System.out.println(alertTextVal);
			break;
		case AlertSetText:
            Alert promptWindow = driver.switchTo().alert();
            promptWindow.sendKeys(value);
			break;
		case Navigate:
			driver.get(value);
			break;
		case Click:
			element.click();
			break;
		case Type:
			element.sendKeys(value);
			break;
		case SelectByIndex:
		case SelectByText:
		case SelectByValue:
			performSelectByAction(action,element,value);
			break;
		case Clear:
			break;
        case GoBack:
            driver.navigate().back();
            break;
        case GoForward:
            driver.navigate().forward();
            break;
        case Refresh:
        	driver.navigate().refresh();
        	break;
        case VerifyText:
        	String elementText = element.getText();
        	if (elementText.equalsIgnoreCase(value)) {
				System.out.println("Element text match with text: " + value);
			}else {
				System.out.println("Text given does not match with element text");
			}
        	break;
        case VerifyTitle:
            String[] verifyTitleargs = value.split(",");
            if (verifyTitleargs.length == 2) {
                Boolean verifyTitleisVerified = Boolean.FALSE;
                String obtainedTitle = driver.getTitle();
                String expectedTitle = verifyTitleargs[1];
                switch (verifyTitleargs[0].toLowerCase()) {
                  case "equals":
                      verifyTitleisVerified = obtainedTitle.equals(expectedTitle);
                      break;
                  case "notequals":
                      verifyTitleisVerified = !obtainedTitle.equals(expectedTitle);
                      break;
                  case "contains":
                      verifyTitleisVerified = obtainedTitle.contains(expectedTitle);
                      break;
                  default:
                      throw new Exception("Provided Verification operation is not supported <Operation>:<ValueToCompare>");
                }
                if (!verifyTitleisVerified) {
                    throw new Exception("Verification Failed.");
                }
            } else {
                throw new Exception(
                    "Value is not in Expected Format. Expected Format: <Operation>:<ValueToCompare>");
            }
        	break;
        case VerifyProperty:
        	break;
		default:
			break;	
		}
		if (element != null) {
			clearHightlightElement(element);
		}
	}
	
    private static void performSelectByAction(Action action, WebElement element,
            final String value) throws Exception {
        Select seleniumSelect = new Select(element);
        switch (action) {
          case SelectByIndex:
              seleniumSelect.selectByIndex(Integer.parseInt(value));
              break;
          case SelectByText:
              seleniumSelect.selectByVisibleText(value);
              break;
          case SelectByValue:
              seleniumSelect.selectByValue(value);
              break;
          default:
              throw new Exception("Unsupported select action : " + action);
        }
    }
    
    private static boolean isAlertPresent() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3L));

            wait.until(ExpectedConditions.alertIsPresent());
            return true;
        } catch (TimeoutException eTO) {
            return false;
        }
    }
	
	
	
}


