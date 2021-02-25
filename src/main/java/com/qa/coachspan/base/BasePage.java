package com.qa.coachspan.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;



import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {

	public WebDriver driver;
	public Properties prop;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}
	
	public WebDriver init_Browser(String browser) 
	{
		
		System.out.println("broser value is : "+browser);
		if(browser.equalsIgnoreCase("chrome")) 
		{	
			WebDriverManager.chromedriver().setup();
			tlDriver.set(new ChromeDriver());
		}
		else if(browser.equalsIgnoreCase("firefox"))
		{	
			WebDriverManager.firefoxdriver().setup();
			tlDriver.set(new FirefoxDriver());
		}
		else if(browser.equalsIgnoreCase("safari")) {
			
			tlDriver.set(new SafariDriver());
		}
		
		else {
			
			System.out.println("please pass the correct browser value" + browser);
		}
		
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return getDriver();
	}	

	/*
	 * This method is used to load properties from config file
	 * return properties
	 */
   public Properties init_Prop() 
   {
	  prop = new Properties();
	  try {
	  FileInputStream ip = new FileInputStream("C:\\Users\\Raj\\eclipse-workspace\\Coachspan1\\src\\main\\java\\"
	  		+ "com\\qa\\coachspan\\config\\config.properties");
	     prop.load(ip); 
	  }
	  catch(FileNotFoundException e) {
		  
		  e.printStackTrace();
	  }
	  catch(IOException e) {
		  e.printStackTrace();
	  }
	  return prop;
   }
   

  public String getScreenshot() {
	  	
	  File src = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
	  String path = System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis()+ ".png";
	 File destination = new File(path);
	 
	 try {
		 FileUtils.copyFile(src, destination);
	 }
	 catch(Exception e ) {
		 e.printStackTrace();
	 }
	 
	 return path;
  }

}
