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
		homepage =loginpage.DoLogin(prop.getProperty("username"), prop.getProperty("password"));
		csbdpage=homepage.selectContextvalues(driver, prop.getProperty("orgname"), homepage.orgContext, homepage.listoforgs);	
	}
	
	@Test(priority=1)
	public void verifyCL4SPageShown() 
	{		
	  coachpage=homepage.clickoncoaches();
	}
	
	@Test(priority=2)
	public void verifyCL4SPageHeadingTest() 
	{		
		Assert.assertTrue(coachpage.isheadingvisible());	
	}
	
	@Test(priority=3)
	public void verifyTotalnumbercoachsonCL4S() 
	{
		Assert.assertEquals(coachpage.numberofcoaches(), coachpage.reccountgrid());
		
	}
	
}
