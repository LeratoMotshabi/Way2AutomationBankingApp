package com.BankingApp.TestCases;

import org.openqa.selenium.Alert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.BankingApp.ActionDriver.Action;
import com.BankingApp.Base.BaseClass;
import com.BankingApp.POM.BankManagerLoginPage;
import com.BankingApp.POM.CustomerLoginPage;
import com.BankingApp.POM.HomePage;
import com.BankingApp.POM.OpenAccountPage;
import com.BankingApp.Utilities.RandomSelector;

public class OpenAccountTestCases extends BaseClass {

	OpenAccountPage openAccountPage = new OpenAccountPage(driver);
	BankManagerLoginPage bankManagerLoginPage = new BankManagerLoginPage(driver);
	HomePage homePage = new HomePage(driver);
	CustomerLoginPage customerLoginPage = new CustomerLoginPage(driver);
	Action actions = new Action();

	String accountNumber;
	String alertMessage;
	String actualMessage;
	String expectedMessage = "Account created successfully with account Number";
	static Alert alert;

	String customerName = RandomSelector.getRandomName();
	String currency;

	@BeforeClass
	public void gotToOpenAccountPage() {
		homePage.click_HomeButton();
		homePage.click_BankManagerLoginButton();
		bankManagerLoginPage.goToOpenAccount();
	}

	@Test(priority = 0)
	public void openNewDollarAccount() {
		SoftAssert softassert = new SoftAssert();

		openAccountPage.selectCustomerName(customerName);
		openAccountPage.selectCurrency("Dollar");
		openAccountPage.clickProcessButton();

		if (actions.isAlertPresent()) {
			alert = driver.switchTo().alert();
			alertMessage = alert.getText();
			actualMessage = actions.splitText(alertMessage, " :", 0);
			alert.accept();

		} else {

		}
		softassert.assertEquals(actualMessage, expectedMessage, "incorrect message is displayed");

		softassert.assertAll();

	}

	@Test(priority = 1)
	public void openNewPoundAccount() {
		SoftAssert softassert = new SoftAssert();

		openAccountPage.selectCustomerName(customerName);
		openAccountPage.selectCurrency("Pound");
		openAccountPage.clickProcessButton();

		if (actions.isAlertPresent()) {
			alert = driver.switchTo().alert();
			alertMessage = alert.getText();
			actualMessage = actions.splitText(alertMessage, " :", 0);
			alert.accept();

		}
		softassert.assertEquals(actualMessage, expectedMessage, "incorrect message is displayed");

		softassert.assertAll();
	}

	@Test(priority = 2)
	public void openNewRupeeAccount() {
		SoftAssert softassert = new SoftAssert();

		openAccountPage.selectCustomerName(customerName);
		openAccountPage.selectCurrency("Rupee");
		openAccountPage.clickProcessButton();

		if (actions.isAlertPresent()) {
			alert = driver.switchTo().alert();
			alertMessage = alert.getText();
			actualMessage = actions.splitText(alertMessage, " :", 0);
			alert.accept();

		}
		softassert.assertEquals(actualMessage, expectedMessage, "incorrect message is displayed");

		softassert.assertAll();
	}

	@Test(priority = 3)
	public void openAccountWithoutcustomerName() {
		SoftAssert softassert = new SoftAssert();

		openAccountPage.selectCustomerName("---Customer Name---");
		openAccountPage.selectCurrency("Rupee");
		openAccountPage.clickProcessButton();

		softassert.assertFalse(actions.isAlertPresent(), " Unexpected alert message was diplayed ");

		softassert.assertAll();

	}

	@Test(priority = 4)
	public void openAccountWithoutCurrency() {
		SoftAssert softassert = new SoftAssert();

		openAccountPage.selectCustomerName(customerName);
		openAccountPage.selectCurrency("Rupee");
		openAccountPage.clickProcessButton();

		softassert.assertFalse(actions.isAlertPresent(), " Unexpected alert message was diplayed ");

		softassert.assertAll();

	}

	@Test(priority = 5)
	public void openAccountWithoutCurrencyAndCustomerName() {
		SoftAssert softassert = new SoftAssert();

		openAccountPage.selectCustomerName("---Customer Name---");
		openAccountPage.selectCurrency("---Currency---");
		openAccountPage.clickProcessButton();

		softassert.assertFalse(actions.isAlertPresent(), " Unexpected alert message was diplayed ");

		softassert.assertAll();

	}

}
