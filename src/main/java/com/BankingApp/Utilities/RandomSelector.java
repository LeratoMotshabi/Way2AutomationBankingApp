/**
 * 
 */
package com.BankingApp.Utilities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;



/**
 * 
 */
public class RandomSelector {

	public static String getRandomName()
	{
		List <String> names = new ArrayList<>();
		names.add("Hermoine Granger");
		names.add("Harry Potter");
		names.add("Ron Weasly");
		names.add("Neville Longbottom");
		names.add("Hermoine Granger");
		names.add("Hermoine Granger");
		
		Random random = new Random();
		int randomindex = random.nextInt(names.size());
		return names.get(randomindex);
		
		
	}
	
	public static String getRandomCurrency()
	{
		List <String> names = new ArrayList<>();
		names.add("Dollar");
		names.add("Pound");
		names.add("Rupee");
		
		
		Random random = new Random();
		int randomindex = random.nextInt(names.size());
		return names.get(randomindex);
		
		
	}
	
	public static int randomnumber()
	{
		
		return ThreadLocalRandom.current().nextInt(0,10000);
	}
}
