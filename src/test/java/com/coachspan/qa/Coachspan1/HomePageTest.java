package com.coachspan.qa.Coachspan1;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.coachspan.base.BaseTest;

import junit.framework.Assert;

public class HomePageTest extends BaseTest
{

	@BeforeClass
	public void HomePage_setUp() 
	{
		homePage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test(description="Verify after login Org link is shown to AYS admin")
	public void verfiyHomeOrgLinkTest() 
	
	{
		Assert.assertTrue(homePage.verifyOrgLink());	
	}
	
	@Test
	public void verifyHomePageHeadingTest() 
	{		
		String heading = homePage.verifyHomePageTitle();
		Assert.assertEquals(heading, "Organization List");
	}
	
	@Test(description="Verify if given org is successfully selected")
	public void verifyContextBarSelection() 
	{	
		homePage.selectContextvalues
		(driver, prop.getProperty("orgname"), homePage.orgContext, homePage.listOfOrgs);
	}

}
