package com.qa.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.page.LoginPage;
import com.qa.page.SuppliersPage;
import com.qa.utility.ElementUtility;
import com.qa.utility.FakerUtility;


public class SupplierTest extends Base {
	String supplierName=FakerUtility.getRandom();
	@Test(priority=1,groups= {"regression"})
	public void testSupplier(){

		LoginPage lp=new LoginPage(driver);
		lp.doLogin(ElementUtility.getPropertyValue("username"),ElementUtility.getPropertyValue("password"));
		SuppliersPage sp=new SuppliersPage(driver);
		sp.clickcontactLink();
		sp.clickSupplierLink();
		String actmsg=sp.addsupplier(supplierName, "Srishti",FakerUtility.getNumber() , "AMX4324", "56000", "pqrs@gmail.com", "9697886756", "8989786756","0471223243", "TVM", "KERALA", "INDIA", "near mgclg");
		//sp.searchBar(supplierName);
		String expectedmsg="Contact added successfully";

		Assert.assertEquals(actmsg,expectedmsg);

		String actualmessage= sp.searchBar(supplierName);
		String expectedmessage=supplierName;
		System.out.println("verifyaddsupplier :"+actualmessage);
		Assert.assertEquals(actualmessage,expectedmessage);
		
		
	     
	 

	}
	
	@Test(priority=2,groups= {"smoke"})
	public void verifySearchSupplier(){
		LoginPage lp=new LoginPage(driver);
		lp.doLogin("admin", "123123");
		SuppliersPage sp=new SuppliersPage(driver);
		sp.clickcontactLink();
		sp.clickSupplierLink();
		String actmsg=sp.searchBar(supplierName);
		String expmsg=supplierName;
		Assert.assertEquals(expmsg, actmsg);
		  
	     


	}
	@Test(priority=3,groups= {"smoke"})
	public void verifyviewSupplier(){
		LoginPage lp=new LoginPage(driver);
		lp.doLogin("admin", "123123");
		SuppliersPage sp=new SuppliersPage(driver);
		sp.clickcontactLink();
		sp.clickSupplierLink();
		sp.searchBar(supplierName);
		String actmsg=sp.viewSupplier();
		String expmsg="View Contact";
		
		
		Assert.assertEquals(actmsg,expmsg);
		


	}
	
	 
	@Test(priority=4,groups= {"regression"})
	public void verifyEditSupplier(){

		LoginPage lp=new LoginPage(driver);
		lp.doLogin("admin", "123123");
		SuppliersPage sp=new SuppliersPage(driver);
		sp.clickcontactLink();
		sp.clickSupplierLink();
		
		sp.searchBar(supplierName);
		//sp.editElement();
		String actmsg= sp.editElement("sunrise");
		String expmsg="sunrise";
		
		
		Assert.assertEquals(actmsg, expmsg);
		
		
		
		 
		

	}
	@Test(priority=5,groups= {"regression,smoke"})
	public void verifyDelete()
	{
		LoginPage lp=new LoginPage(driver);
		lp.doLogin("admin", "123123");
		SuppliersPage sp=new SuppliersPage(driver);
		sp.clickcontactLink();
		sp.clickSupplierLink();
		sp.searchBar(supplierName);
		String actualmsg=sp.delete();
		String expmsg="This contact will be deleted";
		System.out.println("verifydeletemessage:"+expmsg);
		
		Assert.assertEquals(actualmsg, expmsg);
		
	    String actualMessage=sp.searchBar(supplierName);
	    String expectedMessage=supplierName;
	    System.out.println("verifyDeleteSupplier :"+actualMessage);
	    Assert.assertEquals(actualMessage,expectedMessage);
		
		
	}
}
































