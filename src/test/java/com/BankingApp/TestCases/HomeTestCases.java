package com.BankingApp.TestCases;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.BankingApp.ActionDriver.Action;
import com.BankingApp.Base.BaseClass;
import com.BankingApp.POM.HomePage;

public class HomeTestCases extends BaseClass {

	HomePage homePage = new HomePage(driver);
	Action actions = new Action();

	@BeforeMethod
	public void goToHomePage() {
		homePage.click_HomeButton();
	}

	@Test(priority = 0)
	public void verifyCustomerLogin() throws InterruptedException {
		SoftAssert soft = new SoftAssert();

		homePage.click_CustomerLoginButton();
		Thread.sleep(3000);
		soft.assertTrue(actions.isElementPresent("yourName_xpath"));

		soft.assertAll();
	}

	@Test(priority = 1)
	public void verifyBankManagerLoginButton() throws InterruptedException {
		SoftAssert soft = new SoftAssert();

		homePage.click_BankManagerLoginButton();
		Thread.sleep(3000);
		soft.assertTrue(actions.isElementPresent("addCustomer_xpath"));

		soft.assertAll();
	}

}
