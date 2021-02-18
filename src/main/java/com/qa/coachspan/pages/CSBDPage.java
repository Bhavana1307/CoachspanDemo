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
	private By associatebutton= By.xpath("//div[@class='container']//button[contains(@class,'btn')]");
	private By addnewcoachwindow= By.xpath("//div[@id='addNewCoachWindow']");
	private By selectcoach = By.xpath("//span[text()='Select a coach...']");
	private By addmembertoDB = By.xpath("//button[contains(@class,'px-1 py-0 p-medium')]");
	private By addmemberpopup = By.xpath("//div[@id='memberDetail']");
	private By firstname = By.xpath("//input[@id='firstName']");
	private By lastname = By.xpath("//input[@id='lastName']");
	private By emailid = By.xpath("//input[@id='primaryEmail']");
	private By savebutton = By.xpath("//button[@id='saveMemberChanges']");
	private By coachstatus = By.xpath("//span[@title='Coach Status']");
	private By coachstatuslist = By.xpath("//ul[@id='coachStatusDDAdd_listbox']/li");
	private By associatecoachbutton = By.xpath("(//div[@id='addNewCoachWindow']//button[contains(@class,'btn-primary')])[2]");
	private By admintab=By.xpath("//a[text()='Admin']");
	private By programsettingstab = By.xpath("//a[text()='Program Settings']");
	private By ceetab = By.xpath("//li[@id='cee-li']");
	private By copylinktab = By.xpath("(//div[@class='input-group']/button)[1]");
	private By menulink = By.xpath("//a[@id='navbarDropdownMenuLink']");
	private By changepassword = By.xpath("//span[@class='dropdown-item'][1]");
	private By logout = By.xpath("//span[@class='dropdown-item'][2]");
	
	public CSBDPage(WebDriver driver)
	{
		this.driver=driver;
		elementActions = new ElementActions(driver);
	}
			
	public String GenerateMemberData(String Firstname, String Lastname) {
		Random randomGenerator = new Random();  
		int randomInt = randomGenerator.nextInt(1000);
		WebElement firstnameW = driver.findElement(firstname);
		firstnameW.sendKeys(Firstname);
		WebElement lastnameW = driver.findElement(lastname);
		String lastnameS = Lastname+randomInt;
		lastnameW.sendKeys(lastnameS);
		String emailid = Firstname+ lastnameS +"@test.com";
		return emailid;
	}
	
	public void getcoachstatus(String status) 
	{
		driver.findElement(coachstatus).click();
		 List<WebElement> list = driver.findElements(coachstatuslist);
		 for(int i=0;i<list.size();i++) 
		 {
			 if(list.get(i).getText().contains(status))
			 {
				 list.get(i).click();
			 }
		
		 }
	}
	
	public boolean associateacoach(String Firstname, String Lastname,String status)
	{
		driver.findElement(CL4D).click();
		driver.findElement(associatebutton).click();
		elementActions.longwaitforElement();
		driver.findElement(addnewcoachwindow).click();
		elementActions.shortwaitforElement();
        driver.findElement(addmembertoDB).click();
        elementActions.shortwaitforElement();
        String emailString = GenerateMemberData(Firstname, Lastname);
		driver.findElement(emailid).sendKeys(emailString);
		driver.findElement(savebutton).click();
		 elementActions.shortwaitforElement();
		 getcoachstatus(status);
		 driver.findElement(associatecoachbutton).click();
		 elementActions.shortwaitforElement();
	   return	driver.findElement(By.xpath("//table[@role='treegrid']/tbody/tr/td/a[text()="
					+ "'"+emailString+ "']")).isDisplayed(); 
		 
	}

	public String associatedEmailID(String Firstname, String Lastname,String status) 
	{

		driver.findElement(CL4D).click();
		driver.findElement(associatebutton).click();
		elementActions.longwaitforElement();
		driver.findElement(addnewcoachwindow).click();
		elementActions.shortwaitforElement();
        driver.findElement(addmembertoDB).click();
        elementActions.shortwaitforElement();
        String emailString = GenerateMemberData(Firstname, Lastname);
		driver.findElement(emailid).sendKeys(emailString);
		driver.findElement(savebutton).click();
		 elementActions.shortwaitforElement();
		 getcoachstatus(status);
		 driver.findElement(associatecoachbutton).click();
		 elementActions.shortwaitforElement();
		 return emailString;
	}
	
	public String getCEEUrl()
	{
		elementActions.shortwaitforElement();
		driver.findElement(admintab).click();
		driver.findElement(programsettingstab).click();
		driver.findElement(ceetab).click();
		//WebElement copylinkbutton = driver.findElement(copylinktab);
	
		try {
			WebElement copylinkbutton = driver.findElement(copylinktab);
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
	
	public void menuoption(String function) 
	{
		driver.findElement(menulink).click();
		if(function.equalsIgnoreCase("change password"))
		{
			driver.findElement(changepassword).click();
		}		
		else if(function.equalsIgnoreCase("logout")) {
			driver.findElement(logout).click();
		}
		else {
			System.out.println("invalid!!");
		}	
	}
	
	public CEEPage landonCEE()
	{	
		String URL=getCEEUrl();
		menuoption("logout");
		driver.get(URL);
		return new CEEPage(driver);	
	}
     
	

}
