package com.framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Totalmails {
		
	WebDriver driver;
		
	public Totalmails(WebDriver ldriver) { // ldriver is the reference webDriver of main test case and store into local variable driver.
		this.driver = ldriver;
	}
		
	public void gettotalmails(WebDriver driver)
	{
		WebElement ele = driver.findElement(By.xpath("(//*[@role='button' and @aria-label='Show more messages']//span[@class])[4]"));  
		String total = ele.getText();
		System.out.println("The total number of emails in Primary Tab is " +total); // print total
	}

	public  void GetNthDetails(WebDriver driver,int Nvalue)
	{
		String Sender = driver.findElement(By.xpath("(//td//span[@email])["+Nvalue+"]")).getAttribute("email");
		String Subject =  driver.findElement(By.xpath("(//td//span[@email])["+Nvalue+"]")).getAttribute("data-hovercard-id");
		System.out.println("The sender is "+Sender+" \n and the subject is "+Subject+" of "+ Nvalue+"th email");
		
	}
}

