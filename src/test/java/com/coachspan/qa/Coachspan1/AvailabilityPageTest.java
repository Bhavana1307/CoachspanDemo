package com.coachspan.qa.Coachspan1;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.coachspan.base.BaseTest;

public class AvailabilityPageTest extends BaseTest
{

	@BeforeClass
	public void HomePage_setUp() 
	{
		homepage =loginpage.DoLogin(prop.getProperty("username"), prop.getProperty("password"));
		csbdpage=homepage.selectContextvalues(driver, prop.getProperty("orgname"), homepage.orgContext, homepage.listoforgs);
		 String emailstring =
			      csbdpage.associatedEmailID(prop.getProperty("firstname"), prop.getProperty("lastname"), prop.getProperty("status"));
	 ceepage = csbdpage.landonCEE();
	availabilitypage=  ceepage.launchAvailabilitypage(emailstring);
	}
	
	
	@Test
	public void fieldvisibility()
	{
	boolean visible = availabilitypage.fieldvisible();
		System.out.println(visible);
	}
	

	@Test
	public void redirectThankYouPage()
	{
		availabilitypage.CreateMemeberAccount(
				prop.getProperty("phonetype"), prop.getProperty("accountpassword"));
	}


}
