 package com.qa.utility;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.qa.constants.Constants;

public class ElementUtility {
	
	WebDriver driver;
	static Properties pro=new Properties();
	public ElementUtility(WebDriver driver)
	{
		this.driver=driver;
	}
	public void sendKeys(WebElement element,String value)
	{
		element.sendKeys(value);
		
	}
	public void click(WebElement element)
	{
		element.click();
		
	}
	public void clear(WebElement element)
	{
		element.clear();
	}
	/*public void sendkeyElement(WebElement element,String value)
	{
		element.sendKeys(value);
	}*/
	public void click(List<WebElement> element,int index)
	{
		element.get(index).click();
	}
	
	public void selectByIndex(WebElement element,int index)
	{
		Select elementSelect= new Select(element);
		elementSelect.selectByIndex(index);
		
	}
	
	public String getText(WebElement element)
	{
		 String text=element.getText();
		 return text;
	}
	public static String getPropertyValue(String key) 
	{

		File src=new File(Constants.propertyConfig_File);
		
		try {
			FileInputStream fis = new FileInputStream (src);
			pro=new Properties();
			pro.load(fis);  //to read data from property file
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		String value=pro.get(key).toString();
		return value;
	}

public int getTableDataRowCount(List<WebElement> tableRowData ,String expectedValue)
	{
		
	        int counter=0;
		for(int i=0;i<tableRowData.size();i++)
		{
			String value=tableRowData.get(i).getText();
			if(expectedValue.equalsIgnoreCase(value))
			{
				counter=i+1;
				break;
			}
			
		}
		return counter;
	}

	

}
