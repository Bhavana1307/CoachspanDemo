package com.qa.coachspan.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.coachspan.base.BasePage;
import com.qa.coachspan.util.ElementActions;

public class HomePage extends BasePage{

	private WebDriver driver;
	ElementActions elementActions;
	private By coachtab = By.xpath("//div[@id='tabstrip']/ul/li[3]");
	private By tabs = By.xpath("//div[@id='tabstrip']/ul/li"); 
	private By orglink = By.xpath("//a[@href='https://coachspan.com/terms-and-conditions-for-youth-organizations/']");
	private By homeheading = By.xpath("//h1[text()='Organization List']");
	public By orgContext = By.xpath("(//span[@class='k-input'])[1]");
	public By programContext = By.xpath("(//span[@class='k-input'])[2]");
	public By seasonContext = By.xpath("(//span[@class='k-input'])[3]");
	public By listoforgs = By.xpath("//ul[@id='context-menu-org_listbox']/li");
	public By listofprograms = By.xpath("//ul[@id='context-menu-program_listbox']/li");
	public By listofseasons = By.xpath("//ul[@id='context-menu-season_listbox']/li");
	
	public HomePage(WebDriver driver)
	{
		this.driver=driver;
		elementActions = new ElementActions(driver);
	}
	
	public boolean verifyorglink() 
	{
		return driver.findElement(orglink).isDisplayed();	
	}
	
	public String verifyTitle()
	{
		return driver.findElement((homeheading)).getText();	
	}
	
	public CoachesPage clickoncoaches()
	{
		elementActions.shortwaitforElement();
		driver.findElement(coachtab).click();
		return new CoachesPage(driver);
	}
	
	public CSBDPage selectContextvalues(WebDriver driver, String value, By menuoption,By listofelement)
	{
		elementActions.shortwaitforElement();
		driver.findElement(menuoption).click();
		elementActions.shortwaitforElement();
		List<WebElement> list = driver.findElements(listofelement);
		System.out.println(list.size());
		elementActions.shortwaitforElement();
		try {
		for(int i =0;i<list.size();i++) 
		{
			String values = list.get(i).getText().trim();
			//System.out.println(values);
			elementActions.moveTOelement(list.get(0));
			if(values.contains((value)))
			{
				//System.out.println(list.get(i).getText());
				list.get(i).click();
				break;	
			}
		
		}}
		catch(Exception e) {
			e.printStackTrace();
		}
		return new CSBDPage(driver);
	}
	
}
