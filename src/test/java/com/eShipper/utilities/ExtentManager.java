package com.eShipper.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager
{
	 private static ExtentHtmlReporter htmlreports;
	 private static ExtentReports extent;
	 
	public static ExtentReports getInstance()
	{
		if (extent == null)
		{
			htmlreports = new ExtentHtmlReporter(System.getProperty("user.dir")+"\\ExtentReports\\Extentreport.html");
			extent = new ExtentReports();
			extent.attachReporter(htmlreports);
						
			htmlreports.config().setReportName("Test Extent V3.0");
			htmlreports.config().setTheme(Theme.STANDARD);
			htmlreports.config().setTestViewChartLocation(ChartLocation.TOP);
		}
		
		return extent;
	}	
}
