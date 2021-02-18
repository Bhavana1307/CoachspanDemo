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
		homepage = loginpage.DoLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test
	public void homeorglinkTest() 
	
	{
		Assert.assertTrue(homepage.verifyorglink());	
	}
	
	@Test
	public void homeheadingTest() 
	{		
		String heading = homepage.verifyTitle();
		Assert.assertEquals(heading, "Organization List");
	}
	
	@Test
	public void selectContext() 
	{	
		homepage.selectContextvalues
		(driver, prop.getProperty("orgname"), homepage.orgContext, homepage.listoforgs);
	}

}
