package com.framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PrimaryTab {

	WebDriver driver;
	
	public PrimaryTab(WebDriver kdriver) { // ldriver is the reference webDriver of main test case and store into local variable driver.
		this.driver = kdriver;
	}

	public void primaryTabSelection(WebDriver driver,String Primary)
	{
		//Primary tab Selection
		WebElement ele = driver.findElement(By.xpath("//*[@aria-label ='"+Primary+"']"));
		String value = ele.getAttribute("aria-selected");
		
		boolean CnPvalue = Boolean.valueOf(value); // value will be true by default
		
		if(CnPvalue)
		{
			System.out.println("Primary Tab is already Selected");
		}
		else
		{
			ele.click();
			System.out.println("Primary tab is selected by click operation");
		}	
	}
}
