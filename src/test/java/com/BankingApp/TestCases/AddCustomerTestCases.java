package com.BankingApp.TestCases;

import org.openqa.selenium.Alert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.BankingApp.ActionDriver.Action;
import com.BankingApp.Base.BaseClass;
import com.BankingApp.POM.AddCustomerPage;
import com.BankingApp.POM.BankManagerLoginPage;
import com.BankingApp.POM.CustomersPage;
import com.BankingApp.POM.HomePage;
import com.BankingApp.Utilities.DataProviderManager;

public class AddCustomerTestCases extends BaseClass {

	// Initialize page objects and action driver
	AddCustomerPage addcustomerPage = new AddCustomerPage(driver);
	HomePage homePage = new HomePage(driver);
	BankManagerLoginPage bankManagerLoginPage = new BankManagerLoginPage(driver);
	CustomersPage customers = new CustomersPage(driver);
	Action actions = new Action();

	// Before running the tests, this method will navigate to the 'Add Customer'
	// page
	@BeforeTest
	public void goToAddCustomer() {

		homePage.click_HomeButton();
		homePage.click_BankManagerLoginButton();
		bankManagerLoginPage.goToAddCustomer();
	}

	// Test case to verify blank fields handling (using data provider for multiple
	// test cases)
	@Test(priority = 0, dataProvider = "CustomerDetails", dataProviderClass = DataProviderManager.class)
	public void VerifyBlankFields(String firstName, String lastName, String postCode) {
		// Use SoftAssert to perform assertions without stopping the test on failure
		SoftAssert softassert = new SoftAssert();
		// Enter customer details into the fields (provided by the DataProvider)
		addcustomerPage.enterFirstName(firstName);
		addcustomerPage.enterLastName(lastName);
		addcustomerPage.enterPostCode(postCode);
		// Click the 'Add Customer' button
		addcustomerPage.clickAddCustomerButton();

		// Verify if an alert is displayed (it shouldn't be)
		softassert.assertFalse(actions.isAlertPresent(), " Unexpected alert message was diplayed ");
		// If an alert is present, handle it by accepting it so test can continue to the
		// next test case
		if (actions.isAlertPresent() == true) {

			driver.switchTo().alert().accept();

		}
		// Execute all assertions
		softassert.assertAll();
	}

	// Test case to Verify the addition of a new customer
	@Test(priority = 1)
	public void VerifyNewCustomer() {
		// Use SoftAssert to perform assertions without stopping the test on failure
		SoftAssert softassert = new SoftAssert();
		// initialize actual and expected messages for verification
		String actualMessage = null;
		String alertMessage = null;
		String expectedMessage = "Customer added successfully with customer id";

		// Enter valid customer details for adding a new customer
		addcustomerPage.enterFirstName("Lerato");
		addcustomerPage.enterLastName("Motshabi");
		addcustomerPage.enterPostCode("123");
		// Click the 'Add Customer' button
		addcustomerPage.clickAddCustomerButton();

		// If an alert is present (indicating success), capture and verify the message
		if (actions.isAlertPresent()) {
			// Switch to the alert and get its text
			Alert alert = driver.switchTo().alert();
			alertMessage = alert.getText();
			// Split the message to extract the actual customer message without the unique
			// customer ID
			actualMessage = actions.splitText(alertMessage, " :", 0);
			// Accept the alert to close it
			alert.accept();

		}
		// Verify if the alert message matches the expected message
		softassert.assertEquals(actualMessage, expectedMessage, "incorrect message is displayed");

		// Execute all assertions
		softassert.assertAll();
	}

	@Test(priority = 2)
	public void VerifyNameIsAdded() {
		SoftAssert softAssert = new SoftAssert();

		// Convert name to lowercase for search consistency
		String name = "LeRaTo".toLowerCase();
		// Navigate to the Customers page
		bankManagerLoginPage.goToCustomers();
		// Search for the customer by name
		boolean foundCustomer = customers.searchCustomerName(name);
		// Assert that the customer is found in the list
		softAssert.assertTrue(foundCustomer, "The name was not found");

		softAssert.assertAll();
	}

}
