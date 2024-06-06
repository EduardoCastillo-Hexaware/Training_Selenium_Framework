package tc_02;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Execution.DriverHandler;
import models.Browser;
import models.Step;
import models.TestCase;

public class TC_02_AddExpensiestProductInCar extends TestCase {

	private int waitV = 1000;
	
	public TC_02_AddExpensiestProductInCar(Browser browser){
		this.browser = browser;
		this.name = "Login To Sauce Demo App";
	}
	
	private void navigateToSauceDemoApp() throws Exception {
		driver.get("https://www.saucedemo.com/v1/index.html");
	}
	
	private void typeUserName() throws Exception{
		WebElement element = driver.findElement(Element.userNameInput);
		DriverHandler.hightlightElement(element);
		element.sendKeys("standard_user");
		DriverHandler.clearHightlightElement(element);
		Thread.sleep(waitV);
	}
	
	private void typePass() throws Exception{
		WebElement element = driver.findElement(Element.passInput);
		DriverHandler.hightlightElement(element);
		element.sendKeys("secret_sauce");
		DriverHandler.clearHightlightElement(element);
		Thread.sleep(waitV);
	}
	
	private void clickToLoginBtn() throws Exception{
		driver.findElement(Element.loginBtn).click();
		Thread.sleep(waitV);
	}
	
	private void waitUntilProductToBeClickable() throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.elementToBeClickable(Element.latestProduct));
		Thread.sleep(waitV);
	}
	
	private void selectPriceLowToHight() throws Exception {
		driver.findElement(Element.sortDropDown).click();
		Thread.sleep(waitV);
	}
	
	private void selectLastProduct() throws Exception {
		driver.findElement(Element.latestProduct).click();
		Thread.sleep(waitV);
	}
	
	private void addProductToCart() throws Exception {
		driver.findElement(Element.addToCart).click();
		Thread.sleep(waitV);
	}
	
	private void clickOnCart() throws Exception {
		driver.findElement(Element.cartIcon).click();
		Thread.sleep(waitV);
	}
	
	private void clickOnCheckout() throws Exception {
		driver.findElement(Element.checkoutBtn).click();
		Thread.sleep(waitV);
	}
	
	private void fillForm(String fn, String ln, String zip) throws InterruptedException {
		driver.findElement(Element.firstNameInput).sendKeys(fn);
		driver.findElement(Element.lastNameInput).sendKeys(ln);
		driver.findElement(Element.zipCodeInput).sendKeys(zip);
		Thread.sleep(waitV);
	}
	
	private void clickOnContinue() throws Exception {
		driver.findElement(Element.continueBtn).click();
		Thread.sleep(3000);
	}
	
	
	@Override
	public List<Step> getSteps(){
		
		Step one = new Step("Navigate to Sauce Demo Page") {
			public void  executeStep() throws Exception{
				navigateToSauceDemoApp();
			}
		};
		
		Step two = new Step("Type user Name") {
			public void  executeStep() throws Exception{
				typeUserName();
			}
		};
		
		Step three = new Step("Type password") {
			public void  executeStep() throws Exception{
				typePass();
			}
		};
		
		Step four = new Step("Click to login Button") {
			public void  executeStep() throws Exception{
				clickToLoginBtn();
			}
		};
		
		Step five = new Step("Validate Login Successfully") {
			public void  executeStep() throws Exception{
				waitUntilProductToBeClickable();
			}
		};
		
		Step six = new Step("Sort Products Low to Hight by Price") {
			public void  executeStep() throws Exception{
				selectPriceLowToHight();
			}
		};		
		
		Step seven = new Step("Click last product") {
			public void  executeStep() throws Exception{
				selectLastProduct();
			}
		};
		
		Step nine = new Step("Click add to cart") {
			public void  executeStep() throws Exception{
				addProductToCart();
			}
		};
		
		Step ten = new Step("Click cart button") {
			public void  executeStep() throws Exception{
				clickOnCart();
			}
		};
		
		Step eleven = new Step("Click on checkout btn") {
			public void  executeStep() throws Exception{
				clickOnCheckout();
			}
		};
		
		Step twelve = new Step("Fill form") {
			public void  executeStep() throws Exception{
				fillForm("Eduardo", "Tom", "2516");
			}
		};
		
		Step thirdteen = new Step("Click on continue") {
			public void  executeStep() throws Exception{
				clickOnContinue();
			}
		};
		
		return Arrays.asList(one, two, three, four, five, six, seven, nine, ten, eleven, twelve, thirdteen );
		
	}
	
}
