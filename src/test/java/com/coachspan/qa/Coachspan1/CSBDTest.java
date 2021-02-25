package com.coachspan.qa.Coachspan1;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.coachspan.base.BaseTest;

import junit.framework.Assert;

public class CSBDTest extends BaseTest {

	
	@BeforeClass
	public void HomePage_setUp() 
	{
		homePage =loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		csbdPage=homePage.selectContextvalues(driver, prop.getProperty("orgname"), homePage.orgContext, homePage.listOfOrgs);	
	}
	
	@Test(enabled=false)
	public void verifyAddedMember()
	{
		Assert.assertTrue(csbdPage.associateACoach(prop.getProperty("firstname"),prop.getProperty("lastname"),prop.getProperty("status")));
		
	}
	
	@Test(enabled=false)
	public void VerifyCEElink() {
		Assert.assertTrue(csbdPage.associateACoach(prop.getProperty("firstname"),prop.getProperty("lastname"),prop.getProperty("status")));
		Object link = csbdPage.getCEEUrl();
		System.out.println(link);
	}
	
	@Test(description="Verify Program CEE link is copied successfully")
	public void verifyCEELPageLinkPageCopied() 
	{
        csbdPage.openCEEPage();		
	}
	
	
	
}
