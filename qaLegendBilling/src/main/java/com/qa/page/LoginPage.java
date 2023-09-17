package com.qa.page;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.utility.ElementUtility;
import com.qa.utility.WaitUtility;




public class LoginPage
{
WebDriver driver;


@FindBy(name="username") 
WebElement userName;

//@FindBy(locator="value")
//WebElement elementname

@FindBy(name="password")
WebElement passWord;
	
@FindBy(name="remember")
List<WebElement> checkBox;

@FindBy(className="btn-primary")
WebElement login;

@FindBy(xpath="//button[@class='btn btn-default btn-sm']")
WebElement  endTour;

@FindBy(xpath="//h1[text()]")
WebElement successMsg;

ElementUtility elementutil;
WaitUtility waitutil;
public LoginPage(WebDriver driver)
{
	this.driver=driver;
	PageFactory.initElements(driver,this);
	
	waitutil=new WaitUtility(driver);
	elementutil=new ElementUtility(driver);
}
public String doLogin(String username,String password)
{
	elementutil.sendKeys(userName, username);
	elementutil.sendKeys(passWord, password);
	elementutil.click(checkBox, 0);
	elementutil.click(login);
	elementutil.click(endTour);
	String actualMsg=elementutil.getText(successMsg);
	return actualMsg;
	
	}
	
}

