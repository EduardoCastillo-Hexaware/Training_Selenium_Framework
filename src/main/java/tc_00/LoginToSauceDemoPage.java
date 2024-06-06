package tc_00;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebElement;

import Execution.DriverHandler;
import models.Action;
import models.Browser;
import models.LocatorType;
import models.Step;
import models.TestCase;

public class LoginToSauceDemoPage extends TestCase {
	
	public LoginToSauceDemoPage(Browser browser) {
		this.browser = browser;
		this.name = "Login To Sauce Demo App";
	}
	
	private void navigateToSauceDemoApp() throws Exception {
		DriverHandler.performAction(Action.Navigate, "https://www.saucedemo.com/v1/index.html", null, null);
		//driver.get("https://www.saucedemo.com/v1/index.html");
	}
	
	private void typeUserName() throws Exception{
		DriverHandler.performAction(Action.Type, "standard_user", LocatorType.Id, "user-name");
//		WebElement element = DriverHandler.findElement(LocatorType.Id, "user-name");
//		element.sendKeys("standard_user");
//		DriverHandler.clearHightlightElement(element);
//		DriverHandler.takeScreenshot(true,"s1");
	}
	
	private void typePass() throws Exception{
		DriverHandler.performAction(Action.Type, "secret_sauce", LocatorType.Id, "password");
//		WebElement element = DriverHandler.findElement(LocatorType.Id, "user-name");
//		element.sendKeys("secret_sauce");
//		DriverHandler.clearHightlightElement(element);
//		DriverHandler.takeScreenshot(true,"s2");
	}
	
	private void clickToLoginBtn() throws Exception{
		DriverHandler.performAction(Action.Click, null, LocatorType.Id, "login-button");
//		WebElement element = DriverHandler.findElement(LocatorType.Id, "login-button");
//		DriverHandler.clearHightlightElement(element);
//		element.click();
//		DriverHandler.takeScreenshot(true,"s3");
		Thread.sleep(3000);
	}
	
	@Override
	public List<Step> getSteps(){
		
		Step first = new Step("Navigate to Sauce Demo Page") {
			public void  executeStep() throws Exception{
				navigateToSauceDemoApp();
			}
		};
		
		Step second = new Step("Type user Name") {
			public void  executeStep() throws Exception{
				typeUserName();
			}
		};
		
		Step third = new Step("Type password") {
			public void  executeStep() throws Exception{
				typePass();
			}
		};
		
		Step fourth = new Step("Click to login Button") {
			public void  executeStep() throws Exception{
				clickToLoginBtn();
			}
		};
		
		return Arrays.asList(first, second, third, fourth);
		
	}
}
