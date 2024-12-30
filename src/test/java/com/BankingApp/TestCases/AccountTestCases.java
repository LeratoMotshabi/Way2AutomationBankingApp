/**
 * 
 */
package com.BankingApp.TestCases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.BankingApp.ActionDriver.Action;
import com.BankingApp.Base.BaseClass;
import com.BankingApp.POM.AccountPage;
import com.BankingApp.POM.CustomerLoginPage;
import com.BankingApp.POM.HomePage;
import com.BankingApp.Utilities.RandomSelector;

/**
 * 
 */
public class AccountTestCases extends BaseClass {

	CustomerLoginPage customerLoginPage = new CustomerLoginPage(driver);
	HomePage homePage = new HomePage(driver);
	AccountPage accountPage = new AccountPage(driver);
	RandomSelector randomNameSelector = new RandomSelector();
	Action action = new Action();
	String ActualName = RandomSelector.getRandomName();

	String currentAmount = null;
	int previousAmount = 0;

	@BeforeClass
	public void goToCustomerLogin() {

		homePage.click_HomeButton();
		homePage.click_CustomerLoginButton();
		customerLoginPage.selectYourName("Hermoine Granger");
		customerLoginPage.click_LoginButton();

	}

	@Test(priority = 0)
	public void verifyDepositSuccessfulMessage() {
		SoftAssert softAssert = new SoftAssert();

		String Ammount = String.valueOf(RandomSelector.randomnumber());
		String actualMessage = null;
		String expectedMessage = "Deposit Successful";
		accountPage.clickOnDeposit();
		accountPage.enterAmount(Ammount);
		accountPage.click_depositButton();

		actualMessage = accountPage.depositSuccessfulMessage();
		System.out.println("Current balance " + Ammount);
		softAssert.assertEquals(actualMessage, expectedMessage);

		softAssert.assertAll();

	}

	@Test(priority = 1)
	public void verifyAmountDepositedSuccessfully() {
		SoftAssert softAssert = new SoftAssert();
		String availableBalance;
		String Ammount = String.valueOf(RandomSelector.randomnumber());
		accountPage.clickOnDeposit();
		availableBalance = accountPage.Availablebalance();
		accountPage.enterAmount(Ammount);
		accountPage.click_depositButton();

		if (action.isElementPresent("depositSuccessfulMessage_xpath")) {
			currentAmount = accountPage.Availablebalance();
			previousAmount = Integer.parseInt(Ammount) + Integer.parseInt(availableBalance);
		}

		softAssert.assertEquals(currentAmount, String.valueOf(previousAmount), "The amount is not correct");

		softAssert.assertAll();

	}

	@Test(priority = 2)
	public void verifyWithdrawlSuccessfulMessage() throws InterruptedException {
		SoftAssert softAssert = new SoftAssert();

		String Ammount = "100";
		String actualMessage = null;
		String expectedMessage = "Transaction successful";
		accountPage.clickOnWithdrawl();

		Thread.sleep(1000);

		accountPage.enterAmount(Ammount);
		accountPage.click_withdrawlButton();

		actualMessage = accountPage.withdrawlSuccessfulMessage();

		softAssert.assertEquals(actualMessage, expectedMessage);

		softAssert.assertAll();

	}

	@Test(priority = 4)
	public void verifyAmountwithdrawnSuccessfully() {

		SoftAssert softAssert = new SoftAssert();

		String availableBalance;
		String Ammount = "100";
		accountPage.clickOnWithdrawl();

		accountPage.enterAmount(Ammount);
		availableBalance = accountPage.Availablebalance();
		accountPage.click_withdrawlButton();

		if (action.isElementPresent("withdrawlSuccessfulMessage_xpath")) {
			currentAmount = accountPage.Availablebalance();
			previousAmount = Integer.parseInt(availableBalance) - Integer.parseInt(Ammount);
		}

		softAssert.assertEquals(currentAmount, String.valueOf(previousAmount), "The amount is not correct");

		softAssert.assertAll();

	}

	@Test(priority = 3)
	public void verifyWithdrawlAmountMoreThanBalance() {
		SoftAssert softAssert = new SoftAssert();

		String Ammount = "1000";
		String actualMessage = "Transaction successful";
		String expectedMessage = "Transaction Failed. You can not withdraw amount more than the balance.";
		accountPage.clickOnWithdrawl();
		accountPage.enterAmount(Ammount);
		accountPage.click_withdrawlButton();

		// actualMessage = accountPage.withdrawlFailedMessage();

		softAssert.assertEquals(actualMessage, expectedMessage);

		softAssert.assertAll();

	}

}
