package com.qa.coachspan.pages;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.coachspan.base.BasePage;
import com.qa.coachspan.util.ElementActions;

public class CoachesPage extends BasePage {
	

	private WebDriver driver;
	ElementActions elementActions;
	private By pageHeading = By.xpath("//h1[contains(text(),'Coach List for Season')]");
	private By rowsCount = By.xpath("//table[@role='treegrid']/tbody/tr");
	private By recordsCount = By.id("recordscount");
	
	public CoachesPage(WebDriver driver) 
	{	
		this.driver=driver;
		elementActions = new ElementActions(driver);
	}
	
	
	public boolean isHeadingVisible() 
	{
		elementActions.shortwaitforElement();
		 return driver.findElement(pageHeading).isDisplayed();
		
	}
	
	public int getNumberofCoaches()
	{
		return driver.findElements(rowsCount).size();	  
	}
	
	public int getRecordCountOnGrid() 
	{	
		String reccountstring = driver.findElement(recordsCount).getText();
		String[] reccounts = reccountstring.split(" ");
		int reccount = Integer.parseInt(reccounts[0]);
		System.out.println(reccount);
		return reccount;
	}
			
	

}
