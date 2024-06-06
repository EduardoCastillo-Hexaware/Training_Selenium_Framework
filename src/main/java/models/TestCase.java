package models;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebDriver;

import Execution.DriverHandler;

public class TestCase implements ITestCase {


	public WebDriver driver;
	public DriverHandler driverHandler;
	public String name;
	public Browser browser;
	
	public List<Step> getSteps() {
		return Arrays.asList();
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
		driverHandler = new DriverHandler(driver);
	}
}
