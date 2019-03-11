package com.eShipper.rough;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class test {

	
	public static Properties config = new Properties();;
	public static Properties OR = new Properties();
	public static FileInputStream fis;
	
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub


		
		String OR_Properties_Source = System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\OR.properties";
		fis = new FileInputStream(OR_Properties_Source);
		
		try
		{
			OR.load(fis);
		} 
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		System.out.println(OR.getProperty("bmlBtn"));
		System.out.println(System.getProperty("user.dir")+"\\src\\test\\resources\\executables\\chromedriver.exe");
	}

}
