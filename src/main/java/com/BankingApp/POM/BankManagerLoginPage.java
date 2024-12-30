/**
 * 
 */
package com.BankingApp.POM;

import org.openqa.selenium.WebDriver;

import com.BankingApp.ActionDriver.Action;
import com.BankingApp.Base.BaseClass;

/**
 * 
 */
public class BankManagerLoginPage extends BaseClass {

	
	
	public BankManagerLoginPage(WebDriver driver)
	{
		BaseClass.driver = driver;
	}
	
	public void goToAddCustomer()
	{
		Action.click("addCustomer_xpath");
	}
	
	public void goToOpenAccount()
	{
		Action.click("openAccount_xpath");
	}
	
	public void goToCustomers()
	{
		Action.click("customers_xpath");
	}
}
