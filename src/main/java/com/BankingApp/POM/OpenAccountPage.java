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
public class OpenAccountPage extends BaseClass {
	
	
	
	public OpenAccountPage(WebDriver driver)
	{
		BaseClass.driver = driver;
	}
	
	public void selectCustomerName(String value)
	{
		Action.select("customer_xpath", value);
	}
	
	public void selectCurrency(String value)
	{
		Action.select("currency_xpath", value);
	
	}
	
	public void clickProcessButton()
	{
		Action.click("processButton_xpath");
		
	}

}
