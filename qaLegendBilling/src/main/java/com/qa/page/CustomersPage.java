package com.qa.page;

import java.util.List;

import org.openqa.selenium.By;
//import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.utility.ElementUtility;
import com.qa.utility.WaitUtility;

public class CustomersPage {
	WebDriver driver;

	@FindBy(xpath="//span[text()='Contacts']")
	WebElement contactclicks;

	@FindBy(xpath="//a[contains(text(),' Customers')]")
	WebElement customers;

	@FindBy(xpath="//button[contains(@class,'btn-modal')]")
	WebElement add;

	@FindBy(xpath="//select[@id='contact_type']")
	List<WebElement> contactType;

	@FindBy(xpath="//input[@id='name']")
	WebElement nameField;

	@FindBy(xpath="//input[@id='contact_id']")
	WebElement contactId;

	@FindBy(xpath="//input[@id='tax_number']")
	WebElement taxnum;

	@FindBy(xpath="//input[@id='opening_balance']")
	WebElement openingBalance;

	@FindBy(xpath="//input[@id='pay_term_number']")
	WebElement payterm;

	@FindBy(xpath="//select[@id='customer_group_id']")
	WebElement custgroup;

	@FindBy(xpath="//input[@id='email']")
	WebElement mail;

	@FindBy(xpath="//input[@id='mobile']")
	WebElement mobilenum;

	@FindBy(xpath="//input[@id='city']")
	WebElement cityname;

	@FindBy(xpath="//input[@id='state']")
	WebElement statename;

	@FindBy(xpath="//input[@id='country']")
	WebElement country;

	@FindBy(xpath="//button[contains(text(),'Save')]")
	WebElement savebutton;
	@FindBy(xpath="//*[text()='Contact added successfully']")
	WebElement Addmsg;


	@FindBy(xpath="//input[contains(@class,'input-sm')]")
	WebElement search;

	@FindBy(xpath="//table[@id='contact_table']//tbody//tr[1]//td[8]")
	WebElement actions;

	@FindBy(xpath="//table[@id='contact_table']//tbody//tr[1]//td[8]//a[contains(text(), ' View')]")
	WebElement view;

	@FindBy(xpath="//h1[text()='View Contact']")
	WebElement viewcontact;
	@FindBy(xpath="//table[@id='contact_table']//tbody//tr[1]//td[8]//li[3]")
	WebElement editbutton;

	/*@FindBy(xpath="//table[@id='contact_table']//tbody//tr[1]//td[1]")
	WebElement editmessage;*/

	@FindBy(xpath="//button[contains(@class,'btn btn-primary')]")
	WebElement update;

	@FindBy(xpath="//table[@id='contact_table']//tbody//tr[1]//td[8]//a[contains(text(), 'Delete')]")
	WebElement delete;

	@FindBy(xpath="//button[text()='OK']")
	WebElement okbutton;
	@FindBy(xpath="//h3[text()='All your Customers']")
	WebElement searchText;


	@FindBy(xpath="//div[text()='Are you sure ?']")
	WebElement deleteButton;

	@FindBy(xpath= "//table[@id='contact_table']//tbody//tr//td[2]")
	List<WebElement>  contacttable;
	String addNamecolumn="3";
	String searchNamecolumn="2";
	String editNamecolumn="5";



	WaitUtility waitutil;
	ElementUtility elementutil;

	public CustomersPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
		waitutil=new WaitUtility(driver);
		elementutil=new ElementUtility(driver);

	}

	public void contactClick()
	{
		elementutil.click(contactclicks);
	}

	public void customersLink()
	{
		elementutil.click(customers);
	}
	public String addCustomers(String name,String contid,String tax,String openingblnc,String pay,String custgrp,String email,String mobile,String city,
			String state,String Country	)
	{

		elementutil.click(add);
		elementutil.sendKeys(nameField,name);
		elementutil.sendKeys(contactId, contid);
		elementutil.sendKeys(taxnum, tax);
		elementutil.sendKeys(openingBalance, openingblnc);
		elementutil.sendKeys(payterm,pay);

		elementutil.sendKeys(custgroup, custgrp);
		elementutil.sendKeys(mail, email);
		elementutil.sendKeys(mobilenum, mobile);
		elementutil.sendKeys(cityname, city);
		elementutil.sendKeys(statename, state);
		elementutil.click(savebutton);
		String actualMsg=Addmsg.getText();
		System.out.println("verifyAddCustomer: "+actualMsg);

		if(actualMsg!=null)
		{
			return  actualMsg;
		}
		else
		{
			return null;
		}



	}
	public String searchBar(String name)
	{

		waitutil.waitelementClick(search);
		elementutil.sendKeys(search, name);
		By locator=By.xpath("//table[@id='contact_table']//tbody//tr//td[contains(text(),'"+name+"')]");
		waitutil.waitforvisible(locator);
		List<WebElement> contacttable=driver.findElements(By.xpath("//table[@id='contact_table']//tbody//tr//td["+searchNamecolumn+"]"));
		waitutil.waitforvisible(contacttable);
		int row=elementutil.getTableDataRowCount(contacttable, name);
		String actualMsg="";
		if(row!=0) {
			WebElement tableRow=driver.findElement(By.xpath("//table[@id='contact_table']//tbody//tr["+row+"]//td["+searchNamecolumn+"]"));
			actualMsg=tableRow.getText();
		}
		System.out.println("verifySearchSupplier: "+actualMsg);
		return actualMsg;


	}
	/*public String viewCustomer(String viewname)
	{
		elementutil.click(actions);
		waitutil.waitForVisibility(view);
		elementutil.click(view);
		WebElement viewelement=driver.findElement(By.xpath("//strong[text()='"+nameField+"']"));
		String actualmsg=viewelement.getText();

		System.out.println("verifyViewCustomer: "+ actualmsg);
		return actualmsg;




	}*/
	public String editCustomer(String edited)
	{
		elementutil.click(actions);
		waitutil.waitelementClick(editbutton);
		elementutil.click(editbutton);
		elementutil.clear(mobilenum);


		
		elementutil.sendKeys(mobilenum, edited);

		//waitutil.waitForVisibility(update);
		waitutil.waitelementClick(update);
		elementutil.click(update);

		By locator=By.xpath("//table[@id='contact_table']//tbody//tr//td[contains(text(),'"+edited+"')]");
		waitutil.waitforvisible(locator);
		List<WebElement> contacttable=driver.findElements(By.xpath("//table[@id='contact_table']//tbody//tr//td["+editNamecolumn+"]"));
		waitutil.waitforvisible(contacttable);
		int row=elementutil.getTableDataRowCount(contacttable,edited);
		String actualMsg="";
		if(row!=0) {
			WebElement tableRow=driver.findElement(By.xpath("//table[@id='contact_table']//tbody//tr["+row+"]//td["+editNamecolumn+"]"));
			actualMsg=tableRow.getText();
		}
		System.out.println("verifyEditSupplier: "+actualMsg);
		return actualMsg;





	}
	public String deleteCustomer()
	{
		elementutil.click(actions);
		elementutil.click(delete);
		waitutil.waitForVisibility(okbutton);
		elementutil.click(okbutton);
		String actualMsg=elementutil.getText(deleteButton);
		System.out.println("verifyDeleteCustomer: "+actualMsg);
		return actualMsg;


	}
}