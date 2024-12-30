package com.BankingApp.POM;

import org.openqa.selenium.WebDriver;

import com.BankingApp.ActionDriver.Action;
import com.BankingApp.Base.BaseClass;

public class AccountPage extends BaseClass {
	
	
	
	public AccountPage(WebDriver driver)
	{
		BaseClass.driver=driver;
	}
	
	
	
	public void clickOnTransactions()
	{
		Action.click("Transactions_css");
	}
	
	public void clickOnDeposit()
	{
		Action.click("Deposit_css");
	}
	
	public void clickOnWithdrawl()
	{
		Action.click("withdrawl_xpath");
	}
	
	public void selectAccountnumber(String value)
	{
		Action.select("accountNumber_xpath", value);
	}
	
	public String selectedAccountNumber()
	{
		return Action.getText("selectedAccountNumber_xpath");
	}
	
	public String  Availablebalance()
	{
		return Action.getText("balance_xpath");
		
	}
	
	public String Accountcurrency()
	{
		return Action.getText("currency_xpath");
	}
	
	public String selectedCustomerName()
	{
		return Action.getText("selectedCustomerName_xpath");
	}
	
	public void enterAmount(String value)
	{
		Action.type("amount_xpath", value);
		
	}
	
	public void click_depositButton()
	{
		Action.click("submitButton_xpath");
	}
	
	public void click_withdrawlButton()
	{
		Action.click("submitButton_xpath");		
	}
	
	public String depositSuccessfulMessage()
	{
		return Action.getText("depositSuccessfulMessage_xpath");
	}
	
	public String withdrawlSuccessfulMessage()
	{
		return Action.getText("withdrawlSuccessfulMessage_xpath");
	}
	
	public String withdrawlFailedMessage()
	{
		return Action.getText("withdrawlFailedMessage_xpath");
	}
	
	

}
