package com.course.SeleniumFCourse;

import models.Browser;
import models.TestCase;
import models.TestSet;
import tc_00.LoginToSauceDemoPage;
import tc_02.TC_02_AddExpensiestProductInCar;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String[] args)
    {
        TestCase testcase1 = new LoginToSauceDemoPage(Browser.Chrome);
        
        try {
        	new TestSet(testcase1).run();
        }catch(Exception ex) {
        	System.out.println(ex.getMessage());
        }
    }
}
