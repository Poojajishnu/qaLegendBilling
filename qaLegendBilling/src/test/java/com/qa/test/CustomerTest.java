package com.qa.test;

import org.testng.Assert;
import org.testng.annotations.Test;


import com.qa.page.CustomersPage;
import com.qa.page.LoginPage;
import com.qa.utility.FakerUtility;

public class CustomerTest extends Base{
	String customerName=FakerUtility.getRandom();
	
	@Test(priority=1,groups={"smoke","regression"})
	public void testCustomer()
	{
		LoginPage lp=new LoginPage(driver);
		lp.doLogin("admin", "123123");
		
		CustomersPage custpage = new CustomersPage(driver);
		custpage.contactClick();
		custpage.customersLink();
		String actmsg=custpage.addCustomers(customerName,FakerUtility.getNumber(),"tx123","500","months","silver","psrjs@gmail.com","6453789762","Ernakulam","KERALA","INDIA");
		String expmsg="Contact added successfully";

		Assert.assertEquals(actmsg,expmsg);

		String actualmsg= custpage.searchBar(customerName);
		String expectedmsg=customerName;
		System.out.println("verifyaddsupplier:"+actualmsg);
		Assert.assertEquals(actualmsg,expectedmsg);
	
		
		

		
		
	}
	@Test(priority=2,groups= {"regression"})
		public void verifysearchCustomer()
	
	{
			LoginPage lp=new LoginPage(driver);
			lp.doLogin("admin", "123123");
			
			CustomersPage custpage = new CustomersPage(driver);
			custpage.contactClick();
			custpage.customersLink();
			String actmsg=custpage.searchBar(customerName);
			String expmsg=customerName;
			Assert.assertEquals(actmsg, expmsg);
			
		
	}
	/*@Test(priority=3,groups={"regression"})
	public void verifyviewCustomers()
	{
		LoginPage lp=new LoginPage(driver);
		lp.doLogin("admin", "123123");
		
		CustomersPage custpage = new CustomersPage(driver);
		custpage.contactClick();
		custpage.customersLink();
		custpage.searchBar(customerName);
		
		
		
	}*/
	@Test(priority=4,groups= {"smoke"})
	public void verifyeditCustomers()
	{
		
		LoginPage lp=new LoginPage(driver);
		lp.doLogin("admin", "123123");
		
		CustomersPage custpage = new CustomersPage(driver);
		custpage.contactClick();
		custpage.customersLink();
		custpage.searchBar(customerName);
		 String  actualMsg=custpage.editCustomer("7234567654");
		 String expectedMsg="7234567654";
		 Assert.assertEquals(actualMsg, expectedMsg);
		 
		
		
		
		
	}
	@Test(priority=5,groups= {"smoke","regression"})
	public void verifyDeleteCustomers()
	{
		
		LoginPage lp=new LoginPage(driver);
		lp.doLogin("admin", "123123");
		
		CustomersPage custpage = new CustomersPage(driver);
		custpage.contactClick();
		custpage.customersLink();
		custpage.searchBar(customerName);
		
		String actmsg=custpage.deleteCustomer();
        String expmsg="Are you sure ?";
		
		Assert.assertEquals(actmsg, expmsg);
		
		
		
	}
	
  

}