package Execution;

import java.util.List;

import org.openqa.selenium.WebDriver;

import logger.Logger;
import models.Browser;
import models.Step;
import models.TestCase;

public class Execution {

	private Logger log;
	
	public Execution(TestCase[] tests) {
		log = new Logger();
		executeTestCases(tests);
	}
	
	private void executeTestCases(TestCase[] tests) {
		for (TestCase testCase : tests) {
			run(testCase);
		}
	}
	
	private void run(TestCase test) {
		
		boolean testCaseState = true;
		Browser browser = test.browser;
		DriverHandler.setSystemProperty(browser);
		WebDriver driver = DriverHandler.createDriver(browser);
		
		log.startExecution(test.name, browser);
		
		test.setDriver(driver);
		List<Step> steps = test.getSteps();
		
		for (int i = 0; i < steps.size(); i++) {
			Step step = steps.get(i);
			try {
				log.logSteps(step, i+1);
				step.executeStep();
			} catch (Exception e) {
				testCaseState = false;
				System.out.print(e.getMessage());
			}
		}
		log.endExecution(testCaseState);
		driver.quit();
	}
}
