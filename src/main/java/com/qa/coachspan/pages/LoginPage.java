package com.qa.coachspan.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.coachspan.base.BasePage;

public class LoginPage extends BasePage 
{
 
	private WebDriver driver;
	private By emailId = By.id("username");
	private By password = By.id("password");
	private By submitbutton = By.xpath("//button[@type='submit']");
	private By forgotpassword = By.linkText("Forgot Password?");
	
	
	public LoginPage(WebDriver driver) 
	{
		this.driver=driver;
	}
	
	public String getLoginPageTitle() 
	{
		return driver.getTitle();	
	}
	
	public boolean isForgotPasswordlinkexist()
	{	
		return driver.findElement(forgotpassword).isDisplayed();
	}
	
	public HomePage DoLogin(String username, String stringpassword)
	{
		System.out.println("Login with " + username + " and " + stringpassword);
		driver.findElement(emailId).sendKeys(username);
		driver.findElement(password).sendKeys(stringpassword);
		driver.findElement(submitbutton).click();
		return new HomePage(driver);
	}
	
}
