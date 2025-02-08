Banking App - Test Automation
This repository contains the test automation scripts for a Banking Application using Selenium WebDriver, TestNG, Page Object Model (POM), Listeners, Extent Reports, and Action Driver. The tests are written in Java.
The project follows the Page Object Model design pattern, which helps in creating maintainable and scalable test scripts.

Base Class: Contains setup and teardown methods for WebDriver initialization and configuration.
Action Driver: Contains reusable functions to perform actions on web elements (e.g., click, type, select, etc.).
PageObjects: Contains classes representing each page of the Banking App with locators and actions specific to those pages.
Listeners: Custom listeners for logging and reporting purposes.
Test Classes: Contains the actual test scripts that use the page objects to perform actions and validate functionality.

Features
Selenium WebDriver: Used for browser automation (Chrome, Edge, Firefox).
TestNG: Used for running and reporting tests.
Page Object Model (POM): Encapsulates page elements and actions into separate classes for better maintainability.
Action Driver: Provides reusable actions such as clicking, typing, and selecting options.
Extent Reports: Provides detailed test execution reports.

Features
Selenium WebDriver: Used for browser automation (Chrome, Edge, Firefox).
TestNG: Used for running and reporting tests.
Page Object Model (POM): Encapsulates page elements and actions into separate classes for better maintainability.
Action Driver: Provides reusable actions such as clicking, typing, and selecting options.
Extent Reports: Provides detailed test execution reports.

Test Execution and Reporting
Extent Reports
Extent Reports provide detailed logs of each test's execution. When you run the tests, a report will be generated in the test-output/ExtentReports folder. This report provides information about each test case, such as the test status (pass/fail), execution time, and captured logs/screenshots.

Listeners
Custom listeners have been implemented to capture test execution events such as test start, test success, test failure, etc. The listeners are used to:

Log detailed information during the test execution.
Capture screenshots on failure.
Generate detailed execution logs in the Extent Report.
Key Components
1. BaseClass (com.BankingApp.Base.BaseClass)
The BaseClass is responsible for setting up and tearing down the WebDriver session before and after each suite. It loads the configurations and locators from property files and initializes the WebDriver accordingly.

2. Action Driver (com.BankingApp.ActionDriver.Action)
The Action class provides reusable methods for interacting with web elements, such as clicking, typing, selecting from dropdowns, and waiting for elements. It handles common web interactions across all pages.

Page Objects
Each page of the banking application is represented by a separate class in the PageObjects package. For example, HomePage, CustomerLoginPage, AddCustomerPage, CustomerPage, and so on. These classes contain locators for each element on the page and methods to interact with them.

Listeners (com.BankingApp.Listeners.Listeners)
The listeners are responsible for logging actions during the test execution. The listeners help track each step of the test case and provide detailed logs for success or failure.

Extent Report Integration
Extent Reports integration allows detailed reporting for test execution, which includes:

Test steps and logs
Pass/Fail status
Screenshots for failed tests
The Listeners class captures these details during test execution and logs them into the Extent report.

Configuration Files
config.properties: Contains the configuration for the browser, URL, and other settings.
locator.properties: Contains the locators for all elements used in the tests (XPath, CSS selectors, etc.).

Screenshot on Failure
If a test fails, a screenshot will be automatically captured and saved in the test-output/screenshots folder. This is handled by the Action class and the Listeners.

