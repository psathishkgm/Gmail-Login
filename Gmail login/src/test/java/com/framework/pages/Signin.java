package com.framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import com.framework.utilities.Helper;

public class Signin {
	
	WebDriver driver;

	public Signin(WebDriver ldriver) { // ldriver is the reference webDriver of main test case and store into local variable driver.
		this.driver = ldriver;
	}
	
	By user = By.xpath("//input[@type='email']");
	By pass = By.xpath("//input[@type='password']");
	By login = By.xpath("//span[text()='Next']");
	
  @Parameters ({"url"})  
  public void signin_page(WebDriver driver,String uname, String passw,String url) throws Exception {

	  Helper.enterTextUsingJavaScriptExecutor(driver, user, uname, "xpath");
	  Helper.Loginelement(driver, login, 2).click();
	  Helper.enterTextUsingJavaScriptExecutor(driver, pass, passw, "xpath");
	  Helper.Loginelement(driver, login, 2).click();
			  
  }
}
		
 
