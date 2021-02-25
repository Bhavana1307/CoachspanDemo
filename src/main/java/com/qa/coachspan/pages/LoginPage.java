package com.qa.coachspan.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.coachspan.base.BasePage;

public class LoginPage extends BasePage 
{
 
	private WebDriver driver;
	private By emailIdElement = By.id("username");
	private By passwordElement = By.id("password");
	private By submitButton = By.xpath("//button[@type='submit']");
	private By forgotPassword = By.linkText("Forgot Password?");
	
	
	public LoginPage(WebDriver driver) 
	{
		this.driver=driver;
	}
	
	public String getLoginPageTitle() 
	{
		return driver.getTitle();	
	}
	
	public boolean isForgotPasswordLinkExist()
	{	
		return driver.findElement(forgotPassword).isDisplayed();
	}
	
	public HomePage doLogin(String username, String stringpassword)
	{
		System.out.println("Login with " + username + " and " + stringpassword);
		driver.findElement(emailIdElement).sendKeys(username);
		driver.findElement(passwordElement).sendKeys(stringpassword);
		driver.findElement(submitButton).click();
		return new HomePage(driver);
	}
	
}
