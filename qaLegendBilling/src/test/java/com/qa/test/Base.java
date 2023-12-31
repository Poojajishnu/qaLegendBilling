package com.qa.test;


import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.qa.constants.Constants;
import com.qa.utility.ElementUtility;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;


public class Base {
	WebDriver driver;
	  @Parameters({"browserName"})
	  @BeforeMethod(alwaysRun = true)
	  public void beforeMethod(@Optional ("chrome")String browserName) 
	  {
		  if(browserName.equalsIgnoreCase("chrome"))
		  {
			   
			  driver=new ChromeDriver();
			  
		  }
		  else if(browserName.equalsIgnoreCase("edge"))
		  {
			  
			  driver=new EdgeDriver();
		  }
			
			driver.get("https://qalegend.com/billing/public/login");
			driver.get(ElementUtility.getPropertyValue("baseurl"));
			
			driver.manage().window().maximize();
			
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
	  }
	  @AfterMethod
	  
	  public void takeScreenShotOnFailure(ITestResult iTestResult) throws IOException 
	    {
	  		if (iTestResult.getStatus() == ITestResult.FAILURE) {
	  			takeScreenShotOnFailure(iTestResult.getName());

	  		}
	  		driver.quit();
	   }
	  public String takeScreenShotOnFailure(String name) throws IOException {
			String dateName = new SimpleDateFormat("yyyy_MM_dd_hh_mm").format(new Date());


			File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

			String destination =Constants.screenShotPath + name + dateName + ".png";

			File finalDestination = new File(destination);

			FileUtils.copyFile(source, finalDestination);
			return destination;
		}

	}