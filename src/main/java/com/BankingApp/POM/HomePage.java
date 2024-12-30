package com.BankingApp.POM;

import org.openqa.selenium.WebDriver;

import com.BankingApp.ActionDriver.Action;
import com.BankingApp.Base.BaseClass;


public class HomePage extends BaseClass {

	public HomePage(WebDriver driver) {
		BaseClass.driver = driver;
	}

	public void click_HomeButton() {
		
		Action.click("Home_xpath");
	}

	public void click_CustomerLoginButton() {
		Action.click("customerLogin_xpath");
	}

	public void click_BankManagerLoginButton() {
		Action.click("bankManagerLogin_xpath");
	}

}
