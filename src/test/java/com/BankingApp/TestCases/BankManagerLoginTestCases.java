package com.BankingApp.TestCases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.BankingApp.Base.BaseClass;
import com.BankingApp.POM.BankManagerLoginPage;
import com.BankingApp.POM.HomePage;

public class BankManagerLoginTestCases extends BaseClass {

	BankManagerLoginPage bankManagerLoginPage = new BankManagerLoginPage(driver);
	HomePage homePage = new HomePage(driver);

	@BeforeClass
	public void bankManagerLogin() {
		homePage.click_HomeButton();
		homePage.click_BankManagerLoginButton();
	}

	@Test(priority = 0)
	public void verifyAddCustomerButton() {

		bankManagerLoginPage.goToAddCustomer();
	}

	@Test(priority = 1)
	public void verifyOpenAccountButton() {

		bankManagerLoginPage.goToOpenAccount();
	}

	@Test(priority = 2)
	public void verifyCustomersButton() {

		bankManagerLoginPage.goToCustomers();

	}

}
