package com.coachspan.qa.Coachspan1;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.coachspan.base.BaseTest;

public class CEEPageTest extends BaseTest{

	
	
	@BeforeClass
	public void HomePage_setUp() 
	{
		homePage =loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		csbdPage=homePage.selectContextvalues(driver, prop.getProperty("orgname"), homePage.orgContext, homePage.listOfOrgs);
	   	
	}
	
	@Test(description="Verify if AvailabilityPage shown from CEE")
	public void verifyCEEPageShown() 
	{
		 String emailstring =
	      csbdPage.associatedEmailID(prop.getProperty("firstname"), prop.getProperty("lastname"), prop.getProperty("status"));
		  ceePage = csbdPage.openCEEPage();
		  ceePage.launchAvailabilityPage(emailstring);
		
	}
	
}
