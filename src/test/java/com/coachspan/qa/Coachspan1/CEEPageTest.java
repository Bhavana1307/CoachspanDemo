package com.coachspan.qa.Coachspan1;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.coachspan.base.BaseTest;

public class CEEPageTest extends BaseTest{

	
	
	@BeforeClass
	public void HomePage_setUp() 
	{
		homepage =loginpage.DoLogin(prop.getProperty("username"), prop.getProperty("password"));
		csbdpage=homepage.selectContextvalues(driver, prop.getProperty("orgname"), homepage.orgContext, homepage.listoforgs);
	   	
	}
	
	@Test(description="Verify if AvailabilityPage shown from CEE")
	public void landonAvailabilityPage() 
	{
		 String emailstring =
	      csbdpage.associatedEmailID(prop.getProperty("firstname"), prop.getProperty("lastname"), prop.getProperty("status"));
		  ceepage = csbdpage.landonCEE();
		  ceepage.launchAvailabilitypage(emailstring);
		
	}
	
}
