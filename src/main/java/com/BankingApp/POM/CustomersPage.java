/**
 * 
 */
package com.BankingApp.POM;




import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.BankingApp.ActionDriver.Action;
import com.BankingApp.Base.BaseClass;


/**
 * 
 */
public class CustomersPage extends BaseClass {
	
	
	public CustomersPage(WebDriver driver)
	{
		BaseClass.driver = driver;
	}
	
	public void searchCustomer(String value)
	{
		Action.type("searchCustomer_xpath", value);
	}
	
	public boolean searchCustomerName(String value)
	{
		return Action.searchInTable("CustomerTable_xpath",value);
	}
	
	public String deleteCustomers(String value)
	{
		String name = null;
		List<WebElement> rows = driver.findElements(By.xpath(("/html/body/div[1]/div/div[2]/div/div[2]/div/div/table/tbody/tr")));
		
		for(int i=1;i<rows.size();i++)
		{
			
			WebElement row = driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div/div[2]/div/div/table/tbody/tr["+ i +"]"));
			name = row.getText().toLowerCase();
			if(name.contains(value.toLowerCase()))
			{
				System.out.println("details" +name);
				driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div/div[2]/div/div/table/tbody/tr["+ i +"]/td[5]/button")).click();
				
				
			}
			
		}
		return name;
		
		
	}
	
	
	

}
