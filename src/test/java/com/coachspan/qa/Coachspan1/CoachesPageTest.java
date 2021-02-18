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
	
	@Test
	public void coachespage() 
	{
		
	  coachpage=homepage.clickoncoaches();
	}
	
	@Test
	public void pageheadingTest() 
	{		
		Assert.assertTrue(coachpage.isheadingvisible());	
	}
	
	@Test
	public void noofcoaches() 
	{
		Assert.assertEquals(coachpage.numberofcoaches(), coachpage.reccountgrid());
		
	}
	
}
