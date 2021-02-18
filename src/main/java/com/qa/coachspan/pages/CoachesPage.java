package com.qa.coachspan.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.coachspan.base.BasePage;
import com.qa.coachspan.util.ElementActions;

public class CoachesPage extends BasePage {
	

	private WebDriver driver;
	ElementActions elementActions;
	private By pageheading = By.xpath("//h1[contains(text(),'Coach List for Season')]");
	private By rowscount = By.xpath("//table[@role='treegrid']/tbody/tr");
	private By reccount = By.id("recordscount");
	
	public CoachesPage(WebDriver driver) 
	{	
		this.driver=driver;
		elementActions = new ElementActions(driver);
	}
	
	
	public boolean isheadingvisible() 
	{
		 return driver.findElement(pageheading).isDisplayed();
		
	}
	
	public int numberofcoaches()
	{
		return driver.findElements(rowscount).size();	  
	}
	
	public int reccountgrid() 
	{	
		String reccountstring = driver.findElement(reccount).getText();
		String[] reccounts = reccountstring.split(" ");
		int reccount = Integer.parseInt(reccounts[0]);
		System.out.println(reccount);
		return reccount;
	}
			
	

}
