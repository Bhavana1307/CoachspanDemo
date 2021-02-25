package com.coachspan.qa.Coachspan1;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.coachspan.base.BaseTest;
import com.qa.coachspan.pages.LoginPage;

public class LoginPageTest  extends BaseTest
{

	@Test(priority=1,description="Verify title of coachspan")
	public void verifyLoginPageTitle() 
	{
		String title = loginPage.getLoginPageTitle();
		System.out.println("title is " + title);
		Assert.assertEquals(title, "CoachSpan");
	}
	
	@Test(priority=2,description="Verify forgot link is shown successfully")
	public void verifyForgotPasswordLink() 
	{
		Assert.assertTrue(loginPage.isForgotPasswordLinkExist());
	}
	
	@Test(priority=3, description="Verify if user is logged in successfully")
	public void verifyLoginFunctionality() 
	{
		loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		
	}
	
	
}
