package logger;

import models.Browser;
import models.Step;

public class Logger {

	public Logger() {}
	
	public void startExecution(String tcName, Browser browser) {
		System.out.println("===================== Start Execution =====================");
		
		String message = "";
		
		if(tcName != null) {
			message ="Test Case to Execute: " + tcName;
		}else {
			message ="Test Case to Execute without name";
		}
		if(browser != null) {
			message.concat(" in " + browser);
		}else {
			message.concat(" in default broswer");
		}
		System.out.println(message);
	}
	
	public void logSteps(Step step, int stepNumber) {
		if (step.name != null) {
			System.out.println("Step " + stepNumber + ": " + step.name);
		}
	}
	
	public void endExecution(boolean state) {
		String stateMessage = state ? "Successfully" : "Failed";
		System.out.println("=========== Execution Completed :" + stateMessage +"===============");
	}
	
}
