/**
 * 
 */
package com.BankingApp.Listeners;





import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.BankingApp.ActionDriver.Action;
import com.BankingApp.Utilities.ExtentManager;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public class Listeners implements ITestListener {

	static Date date = new Date();
	static String file = date.toString().replace(":", "_").toString().replace(" ", "_");
	public static String screenshotFilePath = System.getProperty("user.dir")+ "/src/test/resources/Screenshots/";
	
    public static String filePath = System.getProperty("user.dir") + "/src/test/resources/ExtentReports/extent.html";
    public static ExtentReports extent = ExtentManager.createInstance(filePath);
    
    
    
    public static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>() {
    	@Override
    	 // Initialize ExtentTest object for the current thread
    	protected ExtentTest initialValue() {
    		return extent.createTest(Thread.currentThread().getName());
    	}
    };
   

    @Override
    public void onTestStart(ITestResult result) {
    	
    	ExtentTest testReport = extent.createTest(result.getTestClass() + " @TEST CASE " + result.getMethod().getMethodName());
    	 test.set(testReport); 
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        if (test.get() != null) {
            Markup m = MarkupHelper.createLabel(result.getName() + " :PASSED ", ExtentColor.GREEN);
            test.get().pass(m);
        }else {
            System.out.println("ExtentTest is null in onTestSuccess for: " + result.getMethod().getMethodName());
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        if (test.get() != null) {
            String details = result.getThrowable().getMessage();
            String getThrowable = "<details><summary><font color=red>" + "Exception occurred: Click to see more"
                    + "</font></summary> <p>" + details.replaceAll(",", "<br>") + "</p></details>";
            Markup m = MarkupHelper.createLabel(result.getName() + " :FAILED ", ExtentColor.RED);
            test.get().fail(m);
            test.get().fail(getThrowable);
            
            test.get().info("Screenshot for " + result.getMethod().getMethodName() +" TEST CASE" ,MediaEntityBuilder.createScreenCaptureFromPath(Action.takeScreenshot(screenshotFilePath + result.getMethod().getMethodName() + file  +".png")).build());
            
        }else {
            System.out.println("ExtentTest is null in onTestFailure for: " + result.getMethod().getMethodName());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        if (test.get() != null) {
            String details = result.getThrowable().getMessage();
            String getThrowable = "<details><summary><font color=orange>" + "Exception occurred: Click to see more"
                    + "</font></summary> <p>" + details.replaceAll(",", "<br>") + "</p></details>";
            Markup m = MarkupHelper.createLabel(result.getName() + " :SKIPPED ", ExtentColor.AMBER);
            test.get().skip(m);
            test.get().skip(getThrowable);
        }else {
            System.out.println("ExtentTest is null in onTestSkipped for: " + result.getMethod().getMethodName());
        }
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // No change needed here
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        // No change needed here
    }

    @Override
    public void onStart(ITestContext context) {
    	
    	
    	
    }

    @Override
    public void onFinish(ITestContext context) {
        if (extent != null) {
            extent.flush(); // This finalizes the report
        }
    }
}
