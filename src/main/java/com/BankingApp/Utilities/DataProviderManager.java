/**
 * 
 */
package com.BankingApp.Utilities;

import org.testng.annotations.DataProvider;

/**
 * 
 */
public class DataProviderManager {
	
	@DataProvider(name ="CustomerDetails")
	public Object[][] CustomerDetails()
	{
		return new Object[][] {
			{"","Motsdhabi","123"},
			{"Steve","","123"},
			{"Steve","Motsdhabi",""},
			{"Steve","Motsdhabi","123"},
		};
	}
	
	@DataProvider(name ="AccountDetails")
	public Object[][] AccountDetails()
	{
		return new Object[][] {
			{"","Motsdhabi","123"},
			{"Steve","","123"},
			{"Steve","Motsdhabi",""},
			{"Steve","Motsdhabi","123"},
		};
	}
}
