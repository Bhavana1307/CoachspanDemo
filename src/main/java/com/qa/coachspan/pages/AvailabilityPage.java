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
	private By primaryEmail = By.xpath("//input[@id='emailAddress']");
	private By phonenoElement = By.xpath("//input[@id='phoneNumber']");
	private By submitButton= By.xpath("//input[@name='submit']");
	private By accountPassele = By.xpath("//input[@name='account.password']");
	private By retypeAccPasswordElement = By.xpath("//input[@name='account.retypedPassword']");
	private By retypeEmail = By.xpath("(//input[@id='retypeEmailAddress'])");
	private By phoneTypeList = By.xpath("//ul[@id='drop_downForPhoneNumberType_listbox']/li");
	private By TnC = By.xpath("(//input[@type='checkbox'])[1]");
    private By dataPrivacy = By.xpath("(//input[@type='checkbox'])[2]");
	private By accountId= By.xpath("//input[@id='accountId']");
	private By verificationCodeText= By.xpath("//input[@id='verificationCode']");
	private By verifyButton = By.xpath("//button[contains(@onclick,'verify')]");
	private By selectDropdown = By.xpath("(//span[contains(@class,'k-dropdown-wrap')])[1]");
	public String accountID;
	
	public AvailabilityPage(WebDriver driver) 
	{
		this.driver=driver;
		elementActions = new ElementActions(driver);
		javascripts = new JavaScriptUtils(driver);
		
	}
	
	public void selectPhoneType(String phoneType) 
	{		
		driver.findElement(selectDropdown).click();
		List<WebElement> list = driver.findElements(phoneTypeList);
		for(int i=0;i<list.size();i++) {
			
			String values = list.get(i).getText();
			//System.out.println(values);
			elementActions.moveTOelement(list.get(0));
			if(values.equalsIgnoreCase(phoneType))
			{
				 list.get(i).getText();
				list.get(i).click();
				break;			
			}
			
		}		
	}
	
	public boolean isPrimaryEmailFieldVisible() 
	{		
	   return	driver.findElement(primaryEmail).isDisplayed();		
	}
	
	public void CreateMemeberAccount(String phonetype, String accountpassword,String URI)
	{
		elementActions.longwaitforElement();
		String valueprimary = driver.findElement(primaryEmail).getAttribute("value");
		System.out.println(valueprimary);
		driver.findElement(retypeEmail).sendKeys(valueprimary);	
		driver.findElement(phonenoElement).click();
		driver.findElement(phonenoElement).sendKeys("9773950224");
		selectPhoneType(phonetype);
		WebElement accpass = driver.findElement(accountPassele);
		accpass.sendKeys(accountpassword);
		String retypepassString = accpass.getAttribute("value");
		driver.findElement(retypeAccPasswordElement).sendKeys(retypepassString);	
		javascripts.scrollPageDown();
		elementActions.shortwaitforElement();
		WebElement termsnconditions = driver.findElement(TnC);
		javascripts.scrollIntoView(termsnconditions);
		elementActions.shortwaitforElement();
		elementActions.moveTOelement(termsnconditions);
		elementActions.doActionsClick(TnC);
	    WebElement dataprivacyele=	driver.findElement(dataPrivacy);
	    javascripts.scrollIntoView(dataprivacyele);
	    elementActions.shortwaitforElement();
	    elementActions.moveTOelement(dataprivacyele);
	    elementActions.doActionsClick(dataPrivacy);
		driver.findElement(submitButton).click();
		elementActions.shortwaitforElement();
		 accountID = driver.findElement(accountId).getAttribute("value");
				System.out.println(accountID);
				String code = getVerificationCode(URI);
		elementActions.shortwaitforElement(); 
		driver.findElement(verificationCodeText).sendKeys(code);		 
			driver.findElement(verifyButton).click();	 
	}
	
	public String getVerificationCode(String URI) 
	{		
       RestAssured.baseURI = URI;     
       RequestSpecification httprequest = RestAssured.given();    
       Response response = httprequest.request(Method.GET, "/"+accountID);
	   String Verificationcode = response.getBody().asString();
	   System.out.println("responsebody: "+Verificationcode );      
		return Verificationcode;
	}
	

}
