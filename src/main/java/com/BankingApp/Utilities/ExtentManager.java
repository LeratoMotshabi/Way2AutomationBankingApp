package com.BankingApp.Utilities;

import com.BankingApp.ActionDriver.Action;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager extends Action {
	
	
	
	public static ExtentReports createInstance(String fileName) {
	    
	    if (fileName == null || fileName.trim().isEmpty()) {
	        throw new IllegalArgumentException("Report file name cannot be null or empty.");
	    }
	    
	    // Create a local HtmlReporter object and configure it
	    ExtentSparkReporter htmlReporter = new ExtentSparkReporter(fileName);
	    htmlReporter.config().setDocumentTitle("Test Report");  // Use a generic title or pass a custom one
	    htmlReporter.config().setReportName("Test Report for Banking Domain");  // Use a more descriptive report name
	    htmlReporter.config().setTheme(Theme.STANDARD);
	    htmlReporter.config().setEncoding("UTF-8");  // Corrected encoding
	    
	    // Create an instance of ExtentReports and attach the reporter
	    ExtentReports extent = new ExtentReports();
	    extent.setSystemInfo("Tester", "Lerato Motshabi");
	    extent.setSystemInfo("Environment", "Windows 10");
	    extent.setSystemInfo("Project", "Banking Domain Application Testing");
	    
	    extent.attachReporter(htmlReporter);
	    
	    return extent;
	}

}