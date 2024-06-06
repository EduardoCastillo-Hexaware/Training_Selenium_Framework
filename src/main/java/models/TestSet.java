package models;

import Execution.Execution;

public class TestSet {

	private TestCase[] tests;
	
	public TestSet(TestCase... tests) throws Exception {
		if (tests.length == 0) throw new Exception("There are not any test cases specified");
		this.tests = tests;
	}
	
	public void run() throws Exception{
		if(this.tests == null) throw new Exception("Does not contain any Test Case");
		new Execution(this.tests);
	}
	
}
