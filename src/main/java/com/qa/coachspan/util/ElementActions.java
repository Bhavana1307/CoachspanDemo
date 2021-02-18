package com.qa.coachspan.util;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.coachspan.util.AppConstants;

public class ElementActions {
		
	WebDriver driver;
	WebDriverWait wait;
	Actions action;

	public ElementActions(WebDriver driver)
	{
		this.driver = driver;
		action = new Actions(this.driver);
	}
	

	public WebElement getElement(By locator) 
	{
		WebElement element = null;
		try {
			element = driver.findElement(locator);
		} catch (Exception e) {
			System.out.println("some exception occurred while creating the webelement : " + locator);
		}
		return element;
	}

	/**
	 * 
	 * @param locator
	 */
	public void doClick(By locator) {
		getElement(locator).click();
	}

	public void moveTOelement(WebElement locator) {
		action.moveToElement(locator).build().perform();
	}
	public void doActionsClick(By locator) {
		action.click(getElement(locator)).build().perform();
	}

	public void doSendKeys(By locator, String value) {
		getElement(locator).sendKeys(value);
	}

	public void doActionsSendKeys(By locator, String value) {
		action.sendKeys(getElement(locator), value).build().perform();
	}
	
	
	
	public void doMoveToElement(By locator){
		action.moveToElement(getElement(locator)).build().perform();
	}

	public boolean doIsDisplayed(By locator) {
		return getElement(locator).isDisplayed();
	}

	public String doGetText(By locator) {
		return getElement(locator).getText();
	}
	
	public void longwaitforElement()
	{
		try {
			Thread.sleep(10000);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void shortwaitforElement()
	{
		try {
			Thread.sleep(5000);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	public static int getRecordcount(String recordcountvalue) 
	{	
		String reccountstring = recordcountvalue.substring(0, 1);
		int reccount = Integer.parseInt(reccountstring);
		System.out.println(reccount);

		return reccount;
	}
	

}
