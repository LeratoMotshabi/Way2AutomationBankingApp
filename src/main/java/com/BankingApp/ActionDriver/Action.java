package com.BankingApp.ActionDriver;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.BankingApp.Base.BaseClass;
import com.BankingApp.Listeners.Listeners;

public class Action extends BaseClass {

	// Initialize WebDriverWait with a timeout of 30 seconds
	static WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

	/**
	 * Waits until an element is clickable and returns the element.
	 * 
	 * @param locator - The WebElement to be clicked
	 * @return The clickable WebElement
	 */
	public static WebElement WaitElementToBeClickable(WebElement locator) {
		return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	/**
	 * Waits until an element is visible based on the provided locator.
	 * 
	 * @param webElement - The By locator for the element
	 * @return The visible WebElement
	 */
	public static WebElement waitVisibilityOf(By webElement) {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(webElement));
	}

	/**
	 * Finds an element based on the provided locator.
	 * 
	 * @param elements - The By locator for the element
	 * @return The found WebElement
	 */
	public static WebElement element(By elements) {
		return driver.findElement(elements);
	}

	/**
	 * Clicks on an element identified by the locator. Supports XPath, CSS, and ID
	 * locators.
	 * 
	 * @param locator - The locator of the element to click
	 */
	public static void click(String locator) {
		// Determine locator type (xpath, css, id) and wait until the element is
		// clickable
		if (locator.endsWith("_xpath")) {
			WaitElementToBeClickable(element(By.xpath(locators.getProperty(locator)))).click();
		} else if (locator.endsWith("_css")) {
			WaitElementToBeClickable(element(By.cssSelector(locators.getProperty(locator)))).click();
		} else if (locator.endsWith("_id")) {
			WaitElementToBeClickable(element(By.id(locators.getProperty(locator)))).click();
		}
		// Log the action
		Listeners.test.get().info("clicked on: " + locator);
	}

	/**
	 * Types a value into an element identified by the locator. Supports XPath, CSS,
	 * and ID locators.
	 * 
	 * @param locator - The locator of the element to type into
	 * @param value   - The value to be typed into the element
	 */
	public static void type(String locator, String value) {
		WebElement element;
		// Determine locator type (xpath, css, id) and wait until the element is
		// clickable
		if (locator.endsWith("_xpath")) {
			element = WaitElementToBeClickable(element(By.xpath(locators.getProperty(locator))));
			element.clear(); // Clear the existing text in the field
			element.sendKeys(value); // Type the value into the field
		} else if (locator.endsWith("_css")) {
			element = WaitElementToBeClickable(element(By.cssSelector(locators.getProperty(locator))));
			element.clear();
			element.sendKeys(value);
		} else if (locator.endsWith("_id")) {
			element = WaitElementToBeClickable(element(By.id(locators.getProperty(locator))));
			element.clear();
			element.sendKeys(value);
		}

		// Log the typing action
		Listeners.test.get().info("Typed : " + value + " in " + locator);
	}

	/**
	 * Selects a value from a dropdown list identified by the locator.
	 * 
	 * @param locator - The locator of the dropdown element
	 * @param value   - The value to select from the dropdown
	 */
	public static void select(String locator, String value) {
		// Create a Select object for dropdown interaction
		Select select = new Select(element(By.xpath(locators.getProperty(locator))));
		select.selectByVisibleText(value); // Select by visible text
		// Log the selection action
		Listeners.test.get().info("Selected : " + value + " from " + locator);
	}

	/**
	 * Checks if an alert is present on the page.
	 * 
	 * @return true if an alert is present, otherwise false
	 */
	public boolean isAlertPresent() {
		try {
			// Wait with a 0-second timeout to check if alert is present
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(0));
			wait.until(ExpectedConditions.alertIsPresent());
			Listeners.test.get().info("Alert message is returned");
			return true;
		} catch (TimeoutException e) {
			// Log if no alert is present
			Listeners.test.get().info("Incorrect alert message is returned / No alert message was displayed");
			return false;
		}
	}

	/**
	 * Checks if an element is present on the page using the provided locator.
	 * 
	 * @param locator - The locator of the element to be checked
	 * @return true if the element is found, otherwise false
	 */
	public boolean isElementPresent(String locator) {
		try {
			// Try finding the element using the locator
			element(By.xpath(locators.getProperty(locator)));
			Listeners.test.get().info("Element is found");
			return true;
		} catch (NoSuchElementException e) {
			// Log if the element is not found
			Listeners.test.get().info("Could not find the element");
			return false;
		}
	}

	/**
	 * Splits a string based on the provided character and returns the specified
	 * part.
	 * 
	 * @param value     - The string to be split
	 * @param character - The delimiter for splitting
	 * @param part      - The part to be returned after splitting
	 * @return The specified part of the split string
	 */
	public String splitText(String value, String character, int part) {
		String[] parts = value.split(character); // Split the string
		return parts[part]; // Return the requested part
	}

	/**
	 * Searches for a value in a table using the provided locator.
	 * 
	 * @param locator - The locator of the table
	 * @param value   - The value to search for in the table
	 * @return true if the value is found, otherwise false
	 */
	public static boolean searchInTable(String locator, String value) {
		// Find all rows in the table
		List<WebElement> rows = driver.findElements(By.xpath(locators.getProperty(locator)));

		// Iterate through each row and check if the row contains the value
		for (WebElement row : rows) {
			String rowTextLower = row.getText().toLowerCase();
			if (rowTextLower.contains(value.toLowerCase())) {
				// Log the search result
				System.out.println(rowTextLower);
				Listeners.test.get().info("Search for the customer by name");
				return true;
			}
		}
		// If value is not found, return false
		System.out.println("rowTextLower");
		return false;
	}

	/**
	 * Retrieves the text of an element based on the provided locator.
	 * 
	 * @param locator - The locator of the element
	 * @return The text of the element
	 */
	public static String getText(String locator) {
		return element(By.xpath(locators.getProperty(locator))).getText();
	}

	/**
	 * Takes a screenshot and saves it to the specified file path.
	 * 
	 * @param file - The file path where the screenshot will be saved
	 * @return The file path of the saved screenshot
	 */
	public static String takeScreenshot(String file) {
		try {
			// Capture screenshot and save it to the specified location
			File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			File destination = new File(file);
			FileUtils.copyFile(screenshot, destination);
		} catch (IOException e) {
			// Handle exception if screenshot fails
			e.printStackTrace();
		}
		// Return the file path of the screenshot
		return file;
	}
}
