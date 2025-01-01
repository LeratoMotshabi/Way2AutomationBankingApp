package com.BankingApp.TestCases;

import org.openqa.selenium.Alert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.BankingApp.ActionDriver.Action;
import com.BankingApp.Base.BaseClass;
import com.BankingApp.POM.AccountPage;
import com.BankingApp.POM.AddCustomerPage;
import com.BankingApp.POM.BankManagerLoginPage;
import com.BankingApp.POM.CustomerLoginPage;
import com.BankingApp.POM.CustomersPage;
import com.BankingApp.POM.HomePage;
import com.BankingApp.POM.OpenAccountPage;
import com.BankingApp.Utilities.RandomSelector;

public class integrationTestCase extends BaseClass {

    // Page Object Model (POM) object initialization
    HomePage homePage = new HomePage(driver);
    BankManagerLoginPage bankManagerLogin = new BankManagerLoginPage(driver);
    AddCustomerPage addcustomerPage = new AddCustomerPage(driver);
    CustomersPage customersPage = new CustomersPage(driver);
    OpenAccountPage openAccountPage = new OpenAccountPage(driver);
    CustomerLoginPage customerLoginPage = new CustomerLoginPage(driver);
    AccountPage accountPage = new AccountPage(driver);
    Action action = new Action();

    static String firstname = "Lerato".toLowerCase();
    static String lastname = "Motshabi".toLowerCase();
    static String postcode = "1234";
    String alertMessage;
    String actualMessage;
    String Expectedaccountnumber;

    // Test case for adding a new customer
    @Test(priority = 0)
    public void addNewCustomers() {
        SoftAssert softassert = new SoftAssert();
        String expectedMessage = "Customer added successfully with customer id";

        homePage.click_HomeButton();
        homePage.click_BankManagerLoginButton();
        bankManagerLogin.goToAddCustomer();
        addcustomerPage.enterFirstName(firstname);
        addcustomerPage.enterLastName(lastname);
        addcustomerPage.enterPostCode(postcode);
        addcustomerPage.clickAddCustomerButton();

        // Check for alert and capture message
        if (action.isAlertPresent()) {
            Alert alert = driver.switchTo().alert();
            alertMessage = alert.getText();
            actualMessage = action.splitText(alertMessage, " :", 0);
            alert.accept();
        }

        // Verify if the correct message is displayed
        softassert.assertEquals(actualMessage, expectedMessage, "Incorrect message is displayed");
        softassert.assertAll();
    }

    // Test case to verify if the newly added customer appears in the customer list
    @Test(priority = 1)
    public void verifyCustomerIsAddedSuccefully() {
        SoftAssert softAssert = new SoftAssert();
        bankManagerLogin.goToCustomers();
        customersPage.searchCustomer(firstname);
        boolean foundDetails = customersPage.searchCustomerName(firstname);
        softAssert.assertTrue(foundDetails, "The correct details were not displayed");
        softAssert.assertAll();
    }

    // Test case to open a new account for the customer
    @Test(priority = 2)
    public void openNewAccount() {
        SoftAssert softassert = new SoftAssert();
        String expectedMessage = "Account created successfully with account Number";

        bankManagerLogin.goToOpenAccount();
        openAccountPage.selectCustomerName(firstname + " " + lastname);
        openAccountPage.selectCurrency(RandomSelector.getRandomCurrency());
        openAccountPage.clickProcessButton();

        // If alert is present, capture the account number
        if (action.isAlertPresent()) {
            Alert alert = driver.switchTo().alert();
            alertMessage = alert.getText();
            actualMessage = action.splitText(alertMessage, " :", 0);
            Expectedaccountnumber = action.splitText(alertMessage, " :", 1);
            alert.accept();
        }

        // Verify the success message
        softassert.assertEquals(actualMessage, expectedMessage, "Incorrect message is displayed");
        softassert.assertAll();
    }

    // Test case to verify the account creation was successful
    @Test(priority = 3)
    public void verifyAccountIsAddedSuccessfully() {
        SoftAssert softassert = new SoftAssert();
        homePage.click_HomeButton();
        homePage.click_CustomerLoginButton();
        customerLoginPage.selectYourName(firstname + " " + lastname);
        customerLoginPage.click_LoginButton();

        // Compare the actual account number with the expected one
        String Actualaccountnumber = accountPage.selectedAccountNumber();
        softassert.assertEquals(Actualaccountnumber, Expectedaccountnumber, "Account was not found");
        softassert.assertAll();
    }

    // Test case to verify that an amount is deposited successfully into the account
    @Test(priority = 4)
    public void verifyAmountDepositedSuccessfully() {
        SoftAssert softAssert = new SoftAssert();
        String Ammount = String.valueOf(RandomSelector.randomnumber());
        int previousAmount = 0;
        String currentAmount = null;

        accountPage.clickOnDeposit();
        String availableBalance = accountPage.Availablebalance();
        accountPage.enterAmount(Ammount);
        accountPage.click_depositButton();

        // If deposit is successful, verify the balance
        if (action.isElementPresent("depositSuccessfulMessage_xpath")) {
            currentAmount = accountPage.Availablebalance();
            previousAmount = Integer.parseInt(Ammount) + Integer.parseInt(availableBalance);
        }

        // Assert if the deposit was successful
        softAssert.assertEquals(currentAmount, String.valueOf(previousAmount), "The amount is not correct");
        softAssert.assertAll();
    }

    // Test case to verify that an amount is withdrawn successfully from the account
    @Test(priority = 5)
    public void verifyAmountwithdrawnSuccessfully() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();
        String Ammount = "100";
        String availableBalance = accountPage.Availablebalance();
        int previousAmount = 0;
        String currentAmount = null;

        accountPage.clickOnWithdrawl();
        Thread.sleep(1000);
        accountPage.enterAmount(Ammount);
        accountPage.click_withdrawlButton();

        // If withdrawal is successful, verify the balance
        if (action.isElementPresent("withdrawlSuccessfulMessage_xpath")) {
            currentAmount = accountPage.Availablebalance();
            previousAmount = Integer.parseInt(availableBalance) - Integer.parseInt(Ammount);
        }

        // Assert if the withdrawal was successful
        softAssert.assertEquals(currentAmount, String.valueOf(previousAmount), "The amount is not correct");
        softAssert.assertAll();
    }

    // Test case to delete the customer from the system
    @Test(priority = 6)
    public void deleteCustomer() {
        SoftAssert softAssert = new SoftAssert();
        homePage.click_HomeButton();
        homePage.click_BankManagerLoginButton();
        bankManagerLogin.goToCustomers();

        // Search for the customer and delete them
        customersPage.searchCustomer(firstname);
        customersPage.deleteCustomers(firstname);

        // Verify if the customer is deleted
        boolean foundDetails = customersPage.searchCustomerName(firstname);
        softAssert.assertFalse(foundDetails, "Customer name was not deleted");
        softAssert.assertAll();
    }
}
