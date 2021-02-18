package com.coachspan.qa.Coachspan1;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.coachspan.base.BaseTest;
import com.qa.coachspan.pages.LoginPage;

public class LoginPageTest  extends BaseTest
{

	@Test(priority=1)
	public void VerifyLoginPageTitle() 
	{
		String title = loginpage.getLoginPageTitle();
		System.out.println("title is " + title);
		Assert.assertEquals(title, "CoachSpan");
	}
	
	@Test(priority=2)
	public void verifyforgotpasswordlink() 
	{
		Assert.assertTrue(loginpage.isForgotPasswordlinkexist());
	}
	
	@Test(priority=3)
	public void login() 
	{
		loginpage.DoLogin(prop.getProperty("username"), prop.getProperty("password"));
		
	}
	
	
}
