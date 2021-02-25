package com.coachspan.qa.Coachspan1;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.coachspan.base.BaseTest;
import com.qa.coachspan.util.ElementActions;

public class CoachesPageTest extends BaseTest
{
	
	@BeforeClass
	public void HomePage_setUp() 
	{
		homePage =loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		csbdPage=homePage.selectContextvalues(driver, prop.getProperty("orgname"), homePage.orgContext, homePage.listOfOrgs);	
	}
	
	@Test(priority=1)
	public void verifyCL4SPageShown() 
	{		
	  coachPage=homePage.clickOnCoachesTab();
	}
	
	@Test(priority=2)
	public void verifyCL4SPageHeadingTest() 
	{		
		Assert.assertTrue(coachPage.isHeadingVisible());	
	}
	
	@Test(priority=3)
	public void verifyTotalNumberCoachsOnCL4S() 
	{
		Assert.assertEquals(coachPage.getNumberofCoaches(), coachPage.getRecordCountOnGrid());
		
	}
	
}
