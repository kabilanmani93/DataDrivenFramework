package com.eShipper.utilities;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

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

}
