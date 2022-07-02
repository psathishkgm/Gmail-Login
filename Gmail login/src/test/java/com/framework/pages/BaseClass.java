package com.framework.pages;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.*;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.framework.utilities.*;

public class BaseClass {
	
	public ExtentTest logger;
	public WebDriver driver;
	
	
	@BeforeClass // before starting test run once per class
	@Parameters ({"browser","url"})
	public void setup(String browser,String url) {
	
	driver = BrowserFactory.startBrowser(driver,browser, url);
		
	}
	
	@AfterClass // after completion of entire test only run  once per class
	public void teardown() {
	
	   Reporter.log("Quiting",true);
		
	   BrowserFactory.quitbrowser(driver);
	   
	   Reporter.log("Quiting done",true);
	  
	}
	
	@AfterMethod // if 2 test means 2 times will run
	public void teardownMessage(ITestResult result) throws Exception {
		
		Reporter.log("Test in After method",true);
		
		if(result.getStatus() == ITestResult.FAILURE) {
			logger.fail("Test Failed", MediaEntityBuilder.createScreenCaptureFromPath(Helper.CaptureScreenshot(driver)).build());
		}
		else if(result.getStatus() == ITestResult.SUCCESS) {
				logger.pass("Test Passed", MediaEntityBuilder.createScreenCaptureFromPath(Helper.CaptureScreenshot(driver)).build());
		}
		//report.flush();
		
		Reporter.log("Test completed and Reports generated",true);
	}
}
