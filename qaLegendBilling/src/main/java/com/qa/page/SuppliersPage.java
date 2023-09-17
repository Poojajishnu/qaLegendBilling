package com.qa.page;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.utility.ElementUtility;
import com.qa.utility.WaitUtility;

public class SuppliersPage {
	WebDriver driver;
	@FindBy(xpath="//span[text()='Contacts']")
	WebElement contactclick;
	@FindBy(xpath="//*[contains(text(), 'Suppliers')]")
	WebElement supplierlink;
	@FindBy(xpath="//button[contains(@class,'btn-modal')]")
	WebElement addclick;
	@FindBy(xpath="//select[@id='contact_type']")
	List<WebElement> contacttype;
	@FindBy(xpath="//input[@id='name']")
	WebElement namefield;
	@FindBy(xpath="//input[@id='supplier_business_name']")
	WebElement businessname;
	@FindBy(xpath="//input[@id='contact_id']")
	WebElement contactidtype;
	@FindBy(xpath="//input[@id='tax_number']")
	WebElement taxnum;
	@FindBy(xpath="//input[@id='pay_term_number']")
	WebElement paytermlink;

	@FindBy(xpath="//input[@id='email']")
	WebElement email;
	@FindBy(xpath="//input[@id='mobile']")
	WebElement mobile;
	@FindBy(xpath="//input[@id='alternate_number']")
	WebElement altmobnum;
	@FindBy(xpath="//input[@id='landline']")
	WebElement landlinenum;
	@FindBy(xpath="//input[@id='city']")
	WebElement cityname;
	@FindBy(xpath="//input[@id='state']")
	WebElement statename;
	@FindBy(xpath="//input[@id='country']")
	WebElement countryname;
	@FindBy(xpath="//input[@id='landmark']")
	WebElement landmarkfield;
	@FindBy(xpath="//button[@class='btn btn-primary']")
	WebElement savebutton;
	@FindBy(xpath="//*[text()='Contact added successfully']")
	WebElement addmsg;

	@FindBy(xpath="//input[contains(@class,'input-sm')]")
	WebElement search;
	@FindBy(xpath="//h3[text()='All your Suppliers']")
	WebElement searchText;




	@FindBy(xpath="//table[@id='contact_table']//tbody//tr[1]//td[7]")
	WebElement actions;
	@FindBy(xpath="//table[@id='contact_table']//tbody//tr[1]//td[7]//li[1]//a[contains(text(),' View')]")
	WebElement view;
	@FindBy(xpath="//h1[text()='View Contact']")
	WebElement viewcontact;

	@FindBy(xpath="//table[@id='contact_table']//tbody//tr[1]//td[7]//li[2]")
	WebElement editfield;
	@FindBy(xpath="//table[@id='contact_table']//tbody//tr[1]//td[2]")
	WebElement editmessage;


	@FindBy(xpath="//button[text()='Update']")
	WebElement update;
	@FindBy(xpath="//table[@id='contact_table']//tbody//tr[1]//td[7]//a[contains(text(),'Delete')]")
	WebElement delete;


	@FindBy(xpath="//button[text()='OK']")
	WebElement okbutton;

	@FindBy(xpath="//div[text()='This contact will be deleted']")
	WebElement deletemsg;
	@FindBy(xpath= "//table[@id='contact_table']//tbody//tr//td[3]")
	List<WebElement>  contacttable;
	String addnamefield="3";
	String searchNamecolumn="3";
	String editNamecolumn="2";


	WaitUtility waitutil;
	ElementUtility elementutil;

	public SuppliersPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
		waitutil=new WaitUtility(driver);
		elementutil=new ElementUtility(driver);

	}
	public void clickcontactLink()
	{

		elementutil.click(contactclick);

	}
	public void clickSupplierLink()
	{
		elementutil.click(supplierlink);
	}
	public String addsupplier(String name,String busname,String contactid,String taxnumber,String payterm,String emailid,String mobilenum,
			String altmobnumber,String landlinenumber,String city,String state,String country,String landmark )
	{

		elementutil.click(addclick);
		waitutil.waitelementClick(namefield);
		elementutil.sendKeys(namefield, name);

		elementutil.sendKeys(businessname, busname);

		elementutil.sendKeys(contactidtype, contactid);
		//contactidtype.sendKeys(contactid);
		elementutil.sendKeys(taxnum, taxnumber);
		elementutil.sendKeys(paytermlink, payterm);

		elementutil.sendKeys(email, emailid);

		elementutil.sendKeys(mobile, mobilenum);

		elementutil.sendKeys(altmobnum, altmobnumber);

		elementutil.sendKeys(landlinenum, landlinenumber);

		elementutil.sendKeys(cityname, city);

		elementutil.sendKeys(statename, state);

		elementutil.sendKeys(countryname, country);

		elementutil.sendKeys(landmarkfield, landmark);

		elementutil.click(savebutton);
		waitutil.waitforvisible(addmsg);


		String actmsg=addmsg.getText();
		System.out.println("verifyaddmessage:"+actmsg);
		if(actmsg!=null)
		{
			return actmsg;
		}
		else
		{
			return null;
		}



	}
	public String searchBar(String searchName)
	{
		waitutil.waitelementClick(search);

		elementutil.sendKeys(search, searchName);
		By locator=By.xpath("//table[@id='contact_table']//tbody//tr//td[contains(text(),'"+searchName+"')]");
		waitutil.waitforvisible(locator);
		List<WebElement> contacttable=driver.findElements(By.xpath("//table[@id='contact_table']//tbody//tr//td["+searchNamecolumn+"]"));
		waitutil.waitforvisible(contacttable);
		int row=elementutil.getTableDataRowCount(contacttable, searchName);
		System.out.println(row);
		String actualMsg="";
		if(row!=0) {
			WebElement tableRow=driver.findElement(By.xpath("//table[@id='contact_table']//tbody//tr["+row+"]//td["+searchNamecolumn+"]"));
			actualMsg=tableRow.getText();
		}
		System.out.println("verifySearchSupplier: "+actualMsg);
		return actualMsg;
	}







	public String  viewSupplier()
	{

		elementutil.click(actions);
		waitutil.waitelementClick(view);
		elementutil.click(view);

		String actualMsg=elementutil.getText(viewcontact);
		System.out.println("verifyViewSupplier: "+actualMsg);
		return actualMsg;



	}




	public String editElement(String edited)
	{
		elementutil.click(actions);
		waitutil.waitelementClick(editfield);
		elementutil.click(editfield);
		elementutil.clear(businessname);
		elementutil.sendKeys(businessname,edited );


		waitutil.waitelementClick(update);
		waitutil.waitForVisibility(update);
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
	public String delete()
	{
		elementutil.click(actions);
		
		elementutil.click(delete);
		
		
		
		waitutil.waitForVisibility(okbutton);
		elementutil.click(okbutton);
		String actualmsg=elementutil.getText(deletemsg);
		System.out.println("text of verifyDeletesupplier :"+actualmsg);
		return actualmsg;


	}
}



























