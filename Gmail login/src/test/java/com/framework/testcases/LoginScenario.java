package com.framework.testcases;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.framework.pages.BaseClass;
import com.framework.pages.PrimaryTab;
import com.framework.pages.Signin;
import com.framework.pages.Totalmails;

public class LoginScenario extends BaseClass {
	
@Test
@Parameters ({"url"})
public void test(String url) throws Exception {
	
	System.out.println("Login to Gmail");
	Signin signin = new Signin(driver);
	Totalmails totalmails = new Totalmails(driver);
	PrimaryTab primary = new PrimaryTab(driver);
	//String pathOfScreenShot = System.getProperty("user.dir") + "\\Screenshot\\Gmail_"+Helper.getCurrentDateTime()+"";
	//logger.addScreenCaptureFromPath(pathOfScreenShot, "Login to Gmail");
	signin.signin_page(driver, "psathish445@gmail.com", "Sathish123", url);
	System.out.println("Signed in successfully");
	primary.primaryTabSelection(driver,"Primary");
	totalmails.gettotalmails(driver);
	System.out.println("Mail count captured successfully");
	totalmails.GetNthDetails(driver,7);
	System.out.println("Details captured successfully");
}
}


