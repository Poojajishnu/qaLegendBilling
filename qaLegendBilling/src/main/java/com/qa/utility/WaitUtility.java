package com.qa.utility;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtility {
	WebDriver driver;
	public WaitUtility(WebDriver driver)
	{
		this.driver=driver;
	}
	public void waitForVisibility(WebElement element)
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(60));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
public void waitelementClick(WebElement element)
{
	WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(60));
	wait.until(ExpectedConditions.elementToBeClickable(element));
	
}
public void waitforvisible(By locator) {
	// TODO Auto-generated method stub
	 WebDriverWait waitv= new WebDriverWait(driver,Duration.ofSeconds(120));
	 waitv.until(ExpectedConditions.visibilityOfElementLocated(locator));
}
public void waitforvisible(WebElement element) {
	// TODO Auto-generated method stub
	 WebDriverWait waitv= new WebDriverWait(driver,Duration.ofSeconds(120));
	 waitv.until(ExpectedConditions.visibilityOf(element));
}
public void waitforvisible(List<WebElement> element ) {
	// TODO Auto-generated method stub
	 WebDriverWait waitv= new WebDriverWait(driver,Duration.ofSeconds(120));
	 waitv.until(ExpectedConditions.visibilityOfAllElements(element));
}
}
