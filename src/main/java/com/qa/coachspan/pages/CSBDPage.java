package com.qa.coachspan.pages;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.List;
import java.util.Random;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import com.qa.coachspan.base.BasePage;
import com.qa.coachspan.util.ElementActions;

public class CSBDPage extends BasePage {
	
	private WebDriver driver;
	ElementActions elementActions;
	private String emailID;
	private By CL4D = By.xpath("//a[@href='/coachSummary/division']");	
	private By associateButton= By.xpath("//div[@class='container']//button[contains(@class,'btn')]");
	private By addNewCoachWindow= By.xpath("//div[@id='addNewCoachWindow']");
	private By selectCoach = By.xpath("//span[text()='Select a coach...']");
	private By addMemberToDB = By.xpath("//button[contains(@class,'px-1 py-0 p-medium')]");
	private By addMemberPopup = By.xpath("//div[@id='memberDetail']");
	private By firstName = By.xpath("//input[@id='firstName']");
	private By lastName = By.xpath("//input[@id='lastName']");
	private By emailId = By.xpath("//input[@id='primaryEmail']");
	private By saveButton = By.xpath("//button[@id='saveMemberChanges']");
	private By coachStatus = By.xpath("//span[@title='Coach Status']");
	private By coachStatusList = By.xpath("//ul[@id='coachStatusDDAdd_listbox']/li");
	private By associateCoachButton = By.xpath("(//div[@id='addNewCoachWindow']//button[contains(@class,'btn-primary')])[2]");
	private By adminTab=By.xpath("//a[text()='Admin']");
	private By programSettingsTab = By.xpath("//a[text()='Program Settings']");
	private By ceeTab = By.xpath("//li[@id='cee-li']");
	private By copyLinkTab = By.xpath("(//div[@class='input-group']/button)[1]");
	private By menuLink = By.xpath("//a[@id='navbarDropdownMenuLink']");
	private By changePassword = By.xpath("//span[@class='dropdown-item'][1]");
	private By logOut = By.xpath("//span[@class='dropdown-item'][2]");
	
	public CSBDPage(WebDriver driver)
	{
		this.driver=driver;
		elementActions = new ElementActions(driver);
	}
			
	public String generateMemberData(String Firstname, String Lastname) {
		Random randomGenerator = new Random();  
		int randomInt = randomGenerator.nextInt(1000);
		WebElement firstnameW = driver.findElement(firstName);
		firstnameW.sendKeys(Firstname);
		WebElement lastnameW = driver.findElement(lastName);
		String lastnameS = Lastname+randomInt;
		lastnameW.sendKeys(lastnameS);
		String emailid = Firstname+ lastnameS +"@test.com";
		return emailid;
	}
	
	public void getCoachStatus(String status) 
	{
		driver.findElement(coachStatus).click();
		 List<WebElement> list = driver.findElements(coachStatusList);
		 for(int i=0;i<list.size();i++) 
		 {
			 if(list.get(i).getText().contains(status))
			 {
				 list.get(i).click();
			 }
		
		 }
	}
	
	public boolean associateACoach(String Firstname, String Lastname,String status)
	{
		driver.findElement(CL4D).click();
		driver.findElement(associateButton).click();
		elementActions.longwaitforElement();
		driver.findElement(addNewCoachWindow).click();
		elementActions.shortwaitforElement();
        driver.findElement(addMemberToDB).click();
        elementActions.shortwaitforElement();
        String emailString = generateMemberData(Firstname, Lastname);
		driver.findElement(emailId).sendKeys(emailString);
		driver.findElement(saveButton).click();
		 elementActions.shortwaitforElement();
		 getCoachStatus(status);
		 driver.findElement(associateCoachButton).click();
		 elementActions.shortwaitforElement();
	   return	driver.findElement(By.xpath("//table[@role='treegrid']/tbody/tr/td/a[text()="
					+ "'"+emailString+ "']")).isDisplayed(); 
		 
	}

	public String associatedEmailID(String Firstname, String Lastname,String status) 
	{

		driver.findElement(CL4D).click();
		driver.findElement(associateButton).click();
		elementActions.longwaitforElement();
		driver.findElement(addNewCoachWindow).click();
		elementActions.shortwaitforElement();
        driver.findElement(addMemberToDB).click();
        elementActions.shortwaitforElement();
        String emailString = generateMemberData(Firstname, Lastname);
		driver.findElement(emailId).sendKeys(emailString);
		driver.findElement(saveButton).click();
		 elementActions.shortwaitforElement();
		 getCoachStatus(status);
		 driver.findElement(associateCoachButton).click();
		 elementActions.shortwaitforElement();
		 return emailString;
	}
	
	public String getCEEUrl()
	{
		elementActions.shortwaitforElement();
		driver.findElement(adminTab).click();
		driver.findElement(programSettingsTab).click();
		driver.findElement(ceeTab).click();
		//WebElement copylinkbutton = driver.findElement(copylinktab);
	
		try {
			WebElement copylinkbutton = driver.findElement(copyLinkTab);
			copylinkbutton.click();
			Clipboard clipb = Toolkit.getDefaultToolkit().getSystemClipboard();
			Object data = clipb.getData(DataFlavor.stringFlavor);
			System.out.println(data);
			String URL = data.toString();
			
			return URL;
		} catch (UnsupportedFlavorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     return null;
	}
	
	public void menuOption(String function) 
	{
		driver.findElement(menuLink).click();
		if(function.equalsIgnoreCase("change password"))
		{
			driver.findElement(changePassword).click();
		}		
		else if(function.equalsIgnoreCase("logout")) {
			driver.findElement(logOut).click();
		}
		else {
			System.out.println("invalid!!");
		}	
	}
	
	public CEEPage openCEEPage()
	{	
		String URL=getCEEUrl();
		menuOption("logout");
		elementActions.shortwaitforElement();
		driver.get(URL);
		return new CEEPage(driver);	
	}
     
	

}
