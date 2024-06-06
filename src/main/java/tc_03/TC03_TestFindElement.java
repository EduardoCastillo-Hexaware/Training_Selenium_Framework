package tc_03;

import org.openqa.selenium.WebElement;

import Execution.DriverHandler;
import models.Action;
import models.Browser;
import models.LocatorType;
import models.TestCase;

public class TC03_TestFindElement extends TestCase{

	public TC03_TestFindElement (Browser browser) {
		name = "Test Case to test method findElement from drvierHandler";
		this.browser  = browser;
	}
	
	private void navigateToSauceDemoApp() throws Exception {
		driver.get("https://www.saucedemo.com/v1/index.html");
	}
	
	private void typeUserName() throws Exception{
//		WebElement element = DriverHandler.findElement(LocatorType.Id, "user-name");
//		element.sendKeys("standard_user");
		
		driverHandler.performAction(Action.Type, "standard_user", LocatorType.Id, "user-name");
		//driver.findElement(Elements.userNameInput).sendKeys("standard_user");
		//DriverHandler.clearHightlightElement(element);
		DriverHandler.takeScreenshot(true,name);
	}
	
	private void typePass() throws Exception{
		WebElement element = DriverHandler.findElement(LocatorType.Id, "user-name");
		element.sendKeys("secret_sauce");
		DriverHandler.clearHightlightElement(element);
	}
	
	private void clickToLoginBtn() throws Exception{
		WebElement element = DriverHandler.findElement(LocatorType.Id, "login-button");
		
		DriverHandler.clearHightlightElement(element);
		element.click();
		}
}
