/**
 * 
 */
package com.BankingApp.Base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeSuite;

import com.BankingApp.Listeners.Listeners;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * Code by Lerato Motshabi Environment : Windows 11
 */
public class BaseClass  {

	public static WebDriver driver;
	public static Properties locators = new Properties();
	public Properties config = new Properties();
	public FileInputStream configFile;
	public FileInputStream locatorFile;
	
	

	@BeforeSuite
	public void setup() {
		try {
			configFile = new FileInputStream(
					System.getProperty("user.dir") + "/src/test/resources/Config_Files/config.properties");
			locatorFile = new FileInputStream(
					System.getProperty("user.dir") + "/src/test/resources/Config_Files/locator.properties");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			config.load(configFile);
			locators.load(locatorFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (config.getProperty("browser").equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (config.getProperty("browser").equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		} else if (config.getProperty("browser").equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}

		driver.get(config.getProperty("url"));
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));
		driver.manage().window().maximize();
		

	}

	public void tearDown() {

		driver.quit();

	}

}
