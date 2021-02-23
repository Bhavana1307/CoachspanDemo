package com.qa.coachspan.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.coachspan.base.BasePage;
import com.qa.coachspan.util.ElementActions;

public class CEEPage extends BasePage{
 
	private WebDriver driver;
	ElementActions elementActions;
	private By inputemail = By.xpath("//input[@name='emailId']");
	private By Okbutton = By.xpath("//button[@onclick='postEmail()']");
	private By Continuebutton = By.xpath("//div[@id='dialog-content']/button[1]");
	private By Tryagainbutton = By.xpath("//div[@id='dialog-content']/button[2]");
	
	
	public CEEPage(WebDriver driver) 
	{
		this.driver=driver;
		elementActions = new ElementActions(driver);
	}
	
	public AvailabilityPage launchAvailabilitypage(String email) 
	{
		elementActions.shortwaitforElement();
		driver.findElement(inputemail).sendKeys(email);
		driver.findElement(Okbutton).click();
		//driver.findElement(Continuebutton).click();
		return new AvailabilityPage(driver);
	}
	
	
}
