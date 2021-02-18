package com.coachspan.qa.Coachspan1;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.coachspan.base.BaseTest;

import junit.framework.Assert;

public class CSBDTest extends BaseTest {

	
	@BeforeClass
	public void HomePage_setUp() 
	{
		homepage =loginpage.DoLogin(prop.getProperty("username"), prop.getProperty("password"));
		csbdpage=homepage.selectContextvalues(driver, prop.getProperty("orgname"), homepage.orgContext, homepage.listoforgs);	
	}
	
	@Test(enabled=false)
	public void verifyaddedmember()
	{
		Assert.assertTrue(csbdpage.associateacoach(prop.getProperty("firstname"),prop.getProperty("lastname"),prop.getProperty("status")));
		
	}
	
	@Test(enabled=false)
	public void VerifyCEElink() {
		Assert.assertTrue(csbdpage.associateacoach(prop.getProperty("firstname"),prop.getProperty("lastname"),prop.getProperty("status")));
		Object link = csbdpage.getCEEUrl();
		System.out.println(link);
	}
	
	@Test
	public void launchCEEPage() 
	{
        csbdpage.landonCEE();		
	}
	
	
	
}
