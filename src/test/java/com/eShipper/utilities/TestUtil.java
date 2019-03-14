package com.eShipper.utilities;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.DataProvider;

import com.eShipper.base.TestBase;

public class TestUtil extends TestBase {
	 
	public static String screenShotName;
	
	public static String captureScreenshot(ITestResult result)
	{
		TakesScreenshot ts =(TakesScreenshot) driver;
		
		File src = ts.getScreenshotAs(OutputType.FILE);
		
		Date d = new Date();
		screenShotName = result.getName()+d.toString().replace(":","_").replace(" ","_")+".jpg";
		
		String dst = System.getProperty("user.dir")+"\\ExtentReports\\Screenshots\\"+ screenShotName;
		System.out.println("The dst path name is " + dst);
		//System.currentTimeMillis()+".png";
		try 
		{
			FileUtils.copyFile(src,new File(dst));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		return dst;
	}
	
	public static String captureScreenshot()
	{
		TakesScreenshot ts =(TakesScreenshot) driver;
		
		File src = ts.getScreenshotAs(OutputType.FILE);
		
		Date d = new Date();
		screenShotName = d.toString().replace(":","_").replace(" ","_")+".jpg";
		
		String dst = System.getProperty("user.dir")+"\\ExtentReports\\Screenshots\\"+ screenShotName;
		System.out.println("The dst path name is " + dst);
		//System.currentTimeMillis()+".png";
		try 
		{
			FileUtils.copyFile(src,new File(dst));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		return dst;
	}

	@DataProvider(name="dp")
	public Object[][] getData(Method m)
	{
		String sheetName = m.getName();
		int rows = excel.getRowCount(sheetName); // 0 based getLastRownum so increased with 1
		int cols = excel.getColumnCount(sheetName); // 1 based getLastCellnum so left as is
		
		Object[][] data = new Object[rows-1][cols];
		
		for(int rowNum = 2 ; rowNum<=rows ;rowNum++)
		{
			for(int colNum = 0 ; colNum<cols ;colNum++)
			{
				//dat[0][0]
                data[rowNum - 2][colNum] = excel.getCellData(sheetName, colNum, rowNum);
             }
		}
		
		return data;
	}
	
	@DataProvider(name="HT_dp")
	public Object[][] get_HashData(Method m)
	{
		String sheetName = m.getName();
		int rows = excel.getRowCount(sheetName);
		int cols = excel.getColumnCount(sheetName);
		
		Object[][] data = new Object[rows-1][cols];
		
		for(int rowNum = 2 ; rowNum<=rows ;rowNum++)
		{
			for(int colNum = 0 ; colNum<cols ;colNum++)
			{
				//dat[0][0]
                data[rowNum - 2][colNum] = excel.getCellData(sheetName, colNum, rowNum);
             }
		}
		
		return data;
	}
	
	
}
