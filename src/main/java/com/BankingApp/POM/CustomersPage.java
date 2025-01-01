/**
 * 
 */
package com.BankingApp.POM;




import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

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
		int rowsize = rows.size()+1;
		for(int i=1;i<rowsize;i++)
		{
			
			WebElement row = driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div/div[2]/div/div/table/tbody/tr["+ i +"]"));
			name = row.getText().toLowerCase();
			System.out.println("details.............." +name);
			if(name.contains(value.toLowerCase()))
			{
				
				
				
				JavascriptExecutor js = (JavascriptExecutor) driver;
		        js.executeScript("arguments[0].scrollTop = arguments[0].scrollTop + arguments[1];", driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div/div[2]/div")),200);
				System.out.println("detailssssssssss" +name);
				WebElement elemnt = driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div/div[2]/div/div/table/tbody/tr["+ i +"]/td[5]/button"));
				elemnt.click();
				
				
			}
			
		}
		return name;
		
		
	}
	
	
	

}
