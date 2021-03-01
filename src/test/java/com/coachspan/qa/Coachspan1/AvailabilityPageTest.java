package com.coachspan.qa.Coachspan1;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.coachspan.base.BaseTest;



public class AvailabilityPageTest extends BaseTest
{

	@BeforeClass
	public void HomePage_setUp() 
	{
		homePage =loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		csbdPage=homePage.selectContextvalues(driver, prop.getProperty("orgname"), homePage.orgContext, homePage.listOfOrgs);
		 String emailstring =
			      csbdPage.getAssociatedEmailID(prop.getProperty("firstname"), prop.getProperty("lastname"), prop.getProperty("status"));
	        ceePage = csbdPage.openCEEPage();
	       availabilityPage=  ceePage.launchAvailabilityPage(emailstring);
	}
	
		
	@Test(description="Verify if PrimaryEmail field is visible")
	public void verifyVisibilityofPrimaryEmailField()
	{
	boolean visible = availabilityPage.isPrimaryEmailFieldVisible();
		System.out.println(visible);
	}
	

	@Test(description="Verify if Member is created successfully")
	public void VerifyCoachCreationFunctionality()
	{
		availabilityPage.CreateMemeberAccount(
				prop.getProperty("phonetype"), prop.getProperty("accountpassword"),prop.getProperty("uri"));
	}


}
