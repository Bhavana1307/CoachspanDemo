package com.qa.coachspan.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.coachspan.base.BasePage;
import com.qa.coachspan.util.ElementActions;

public class CEEPage extends BasePage
{
	private WebDriver driver;
	ElementActions elementActions;
	private By inputEmail = By.xpath("//input[@name='emailId']");
	private By okButton = By.xpath("//button[@onclick='postEmail()']");
	private By continueButton = By.xpath("//div[@id='dialog-content']/button[1]");
	private By tryAgainButton = By.xpath("//div[@id='dialog-content']/button[2]");
	
	
	public CEEPage(WebDriver driver) 
	{
		this.driver=driver;
		elementActions = new ElementActions(driver);
	}
	
	public AvailabilityPage launchAvailabilityPage(String email) 
	{
		elementActions.shortwaitforElement();
		driver.findElement(inputEmail).sendKeys(email);
		driver.findElement(okButton).click();
		//driver.findElement(Continuebutton).click();
		return new AvailabilityPage(driver);
	}
	
	
}
