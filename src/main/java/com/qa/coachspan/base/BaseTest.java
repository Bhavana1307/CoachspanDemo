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

	public BasePage basepage;
	public Properties prop;
	public LoginPage loginpage;
	public HomePage homepage;
	public CoachesPage coachpage;
	public CSBDPage csbdpage;
	public CEEPage ceepage;
	public AvailabilityPage availabilitypage;
	public WebDriver driver;
	
	
	@BeforeTest
	public void setUp()
	{
		basepage = new BasePage();
		prop = basepage.init_prop();
		String browser = prop.getProperty("browser");
		driver = basepage.init_browser(browser);
		loginpage= new LoginPage(driver);
		driver.get(prop.getProperty("url"));
		
	}
	@AfterTest
	public void teardown() 
	{
		driver.quit();
	}
	
	
}
