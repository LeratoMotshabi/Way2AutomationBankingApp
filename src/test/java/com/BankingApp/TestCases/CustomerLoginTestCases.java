package com.BankingApp.TestCases;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.BankingApp.Base.BaseClass;
import com.BankingApp.POM.AccountPage;
import com.BankingApp.POM.CustomerLoginPage;
import com.BankingApp.POM.HomePage;
import com.BankingApp.Utilities.RandomSelector;

public class CustomerLoginTestCases extends BaseClass {

	CustomerLoginPage customerLoginPage = new CustomerLoginPage(driver);
	HomePage homePage = new HomePage(driver);
	AccountPage accountPage = new AccountPage(driver);

	@BeforeTest
	public void goToCustomerLogin() {
		homePage.click_HomeButton();
		homePage.click_CustomerLoginButton();
	}

	@Test
	public void loginCustomerName() {
		SoftAssert softAssert = new SoftAssert();

		String ActualName = RandomSelector.getRandomName();
		System.out.println("Random name " + ActualName);
		customerLoginPage.selectYourName(ActualName);
		customerLoginPage.click_LoginButton();

		String expectedName = accountPage.selectedCustomerName();
		softAssert.assertEquals(ActualName, expectedName);
		System.out.println("Name displayed " + accountPage.selectedCustomerName());

		softAssert.assertAll();

	}
}
