package tc_01;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import models.Browser;
import models.Step;
import models.TestCase;

public class TC01_ClickOnTheFirstProduct extends TestCase {

	public TC01_ClickOnTheFirstProduct(Browser browser) {
		this.browser = browser;
		this.name = "Login To Sauce Demo App";
	}
	
	private void navigateToSauceDemoApp() throws Exception {
		driver.get("https://www.saucedemo.com/v1/index.html");
	}
	
	private void typeUserName() throws Exception{
		driver.findElement(Elements.userNameInput).sendKeys("standard_user");
	}
	
	private void typePass() throws Exception{
		driver.findElement(Elements.passInput).sendKeys("secret_sauce");
	}
	
	private void clickToLoginBtn() throws Exception{
		driver.findElement(Elements.loginBtn).click();
	}
	
	private void waitUntilProductToBeClickable() throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.elementToBeClickable(Elements.ThirdProduct));
	}
	
	private void click() throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.elementToBeClickable(Elements.ThirdProduct));
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
		
		Step fifth = new Step("Validate Clickable Button") {
			public void  executeStep() throws Exception{
				waitUntilProductToBeClickable();
			}
		};
		
		return Arrays.asList(first, second, third, fourth, fifth);
		
	}
}
