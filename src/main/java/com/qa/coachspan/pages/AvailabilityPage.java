package com.qa.coachspan.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.coachspan.base.BasePage;
import com.qa.coachspan.util.ElementActions;
import com.qa.coachspan.util.JavaScriptUtils;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class AvailabilityPage extends BasePage {
	
	private WebDriver driver;
	ElementActions elementActions;
	JavaScriptUtils javascripts;
	private By primaryemail = By.xpath("//input[@id='emailAddress']");
	private By phonenoelement = By.xpath("//input[@id='phoneNumber']");
	private By submitbuton= By.xpath("//input[@name='submit']");
	private By accountpassele = By.xpath("//input[@name='account.password']");
	private By retypeaccpassele = By.xpath("//input[@name='account.retypedPassword']");
	private By retypeemail = By.xpath("(//input[@id='retypeEmailAddress'])");
	private By phonetypelist = By.xpath("//ul[@id='drop_downForPhoneNumberType_listbox']/li");
	private By TnC = By.xpath("(//input[@type='checkbox'])[1]");
    private By DataPrivacy = By.xpath("(//input[@type='checkbox'])[2]");
	private By accountId= By.xpath("//input[@id='accountId']");
	private By verificodetext= By.xpath("//input[@id='verificationCode']");
	private By verifybutton = By.xpath("//button[contains(@onclick,'verify')]");
	private By selectdropdown = By.xpath("(//span[contains(@class,'k-dropdown-wrap')])[1]");
	public String accountID;
	
	public AvailabilityPage(WebDriver driver) 
	{
		this.driver=driver;
		elementActions = new ElementActions(driver);
		javascripts = new JavaScriptUtils(driver);
		
	}
	
	public void selectphonetype(String phonetype) 
	{		
		driver.findElement(selectdropdown).click();
		List<WebElement> list = driver.findElements(phonetypelist);
		for(int i=0;i<list.size();i++) {
			
			String values = list.get(i).getText();
			//System.out.println(values);
			elementActions.moveTOelement(list.get(0));
			if(values.equalsIgnoreCase(phonetype))
			{
				 list.get(i).getText();
				list.get(i).click();
				break;			
			}
			
		}		
	}
	
	public boolean fieldvisible() 
	{		
	   return	driver.findElement(primaryemail).isDisplayed();		
	}
	
	public void CreateMemeberAccount(String phonetype, String accountpassword)
	{
		elementActions.shortwaitforElement();
		String valueprimary = driver.findElement(primaryemail).getAttribute("value");
		System.out.println(valueprimary);
		driver.findElement(retypeemail).sendKeys(valueprimary);	
		driver.findElement(phonenoelement).click();
		driver.findElement(phonenoelement).sendKeys("9773950224");
		selectphonetype(phonetype);
		WebElement accpass = driver.findElement(accountpassele);
		accpass.sendKeys(accountpassword);
		String retypepassString = accpass.getAttribute("value");
		driver.findElement(retypeaccpassele).sendKeys(retypepassString);	
		javascripts.scrollPageDown();
		elementActions.shortwaitforElement();
		WebElement termsnconditions = driver.findElement(TnC);
		javascripts.scrollIntoView(termsnconditions);
		elementActions.shortwaitforElement();
		elementActions.moveTOelement(termsnconditions);
		elementActions.doActionsClick(TnC);
	    WebElement dataprivacyele=	driver.findElement(DataPrivacy);
	    javascripts.scrollIntoView(dataprivacyele);
	    elementActions.shortwaitforElement();
	    elementActions.moveTOelement(dataprivacyele);
	    elementActions.doActionsClick(DataPrivacy);
		driver.findElement(submitbuton).click();
		elementActions.shortwaitforElement();
		 accountID = driver.findElement(accountId).getAttribute("value");
				System.out.println(accountID);
				String code = getVerificationCode();
		elementActions.shortwaitforElement(); 
		driver.findElement(verificodetext).sendKeys(code);		 
			driver.findElement(verifybutton).click();	 
	}
	
	public String getVerificationCode() 
	{
		
       RestAssured.baseURI = "http://personserver.coachspan.internal:9002/sec/code/account/";     
       RequestSpecification httprequest = RestAssured.given();    
       Response response = httprequest.request(Method.GET, "/"+accountID);
	   String Verificationcode = response.getBody().asString();
	   System.out.println("responsebody: "+Verificationcode );
       
		return Verificationcode;
	}
	

}
