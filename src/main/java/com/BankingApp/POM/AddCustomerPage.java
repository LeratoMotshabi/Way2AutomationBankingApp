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
public class AddCustomerPage extends BaseClass {
	
	
	public AddCustomerPage(WebDriver driver)
	{
		BaseClass.driver=driver;
		
	}
	
	public void enterFirstName(String value)
	{
		Action.type("firstName_xpath", value);
	}
	
	public void enterLastName(String value)
	{
		Action.type("lastName_xpath", value);
	}
	
	public void enterPostCode(String value)
	{
		Action.type("postCode_xpath", value);
	}
	
	public void clickAddCustomerButton()
	{
		Action.click("AddCustomer_xpath");
	}

}
