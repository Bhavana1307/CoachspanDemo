package com.qa.coachspan.base;

import java.util.Properties;


import org.openqa.selenium.WebDriver;

import org.testng.annotations.AfterTest;

import org.testng.annotations.BeforeTest;

import com.qa.coachspan.pages.AvailabilityPage;
import com.qa.coachspan.pages.CEEPage;
import com.qa.coachspan.pages.CSBDPage;
import com.qa.coachspan.pages.CoachesPage;
import com.qa.coachspan.pages.HomePage;
import com.qa.coachspan.pages.LoginPage;

public class BaseTest {

	public BasePage basePage;
	public Properties prop;
	public LoginPage loginPage;
	public HomePage homePage;
	public CoachesPage coachPage;
	public CSBDPage csbdPage;
	public CEEPage ceePage;
	public AvailabilityPage availabilityPage;
	public WebDriver driver;
	
	
	@BeforeTest
	public void setUp()
	{
		basePage = new BasePage();
		prop = basePage.init_Prop();
		String browser = prop.getProperty("browser");
		driver = basePage.init_Browser(browser);
		loginPage= new LoginPage(driver);
		driver.get(prop.getProperty("url"));
		
	}
	@AfterTest
	public void teardown() 
	{
		driver.quit();
	}
	
	
}
