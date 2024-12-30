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
public class CustomerLoginPage extends BaseClass {
	

	public CustomerLoginPage(WebDriver driver)
	{
		BaseClass.driver = driver;
	}
	
	public void selectYourName(String value)
	{
		Action.select("yourName_xpath", value);
	}
	
	public void click_LoginButton()
	{
		Action.click("loginButton_xpath");
		
	}

}
