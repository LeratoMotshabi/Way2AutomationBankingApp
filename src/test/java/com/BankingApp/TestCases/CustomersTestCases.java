/**
 * 
 */
package com.BankingApp.TestCases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.BankingApp.Base.BaseClass;
import com.BankingApp.POM.BankManagerLoginPage;
import com.BankingApp.POM.CustomersPage;
import com.BankingApp.POM.HomePage;

/**
 * 
 */
public class CustomersTestCases extends BaseClass {

	HomePage homePage = new HomePage(driver);
	BankManagerLoginPage bankManagerLogin = new BankManagerLoginPage(driver);
	CustomersPage customersPage = new CustomersPage(driver);

	@BeforeClass
	public void goToCustomers() {
		homePage.click_HomeButton();
		homePage.click_BankManagerLoginButton();
		bankManagerLogin.goToCustomers();
	}

	@Test(priority = 1)
	public void searchCustomer() {
		SoftAssert softAssert = new SoftAssert();
		String name = "Hermoine".toLowerCase();

		customersPage.searchCustomer(name);
		boolean foundDetails = customersPage.searchCustomerName(name);

		softAssert.assertTrue(foundDetails, "The correct details were not displayed");

		softAssert.assertAll();

	}

	@Test(priority = 0)
	public void deleteCustomer() throws InterruptedException {
		SoftAssert softAssert = new SoftAssert();
		// String ActualName = randomNameSelector.getRandomName();
		String name = "Harry";
		// customersPage.searchCustomer(name);

		Thread.sleep(3000);
		customersPage.deleteCustomers(name);

		// customersPage.searchCustomer(name);
		boolean foundDetails = customersPage.searchCustomerName(name);

		softAssert.assertFalse(foundDetails, "Customer name was not deleted");

		softAssert.assertAll();

	}

}
