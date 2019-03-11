package com.eShipper.listeners;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.eShipper.base.TestBase;
import com.eShipper.utilities.TestUtil;

public class CustomListeners extends TestBase implements ITestListener
{

	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		parentTest = report.createTest(result.getTestName());
		
	}

	public void onTestSuccess(ITestResult result) {
		
		// TODO Auto-generated method stub		
		test.log(Status.PASS,result.getName().toUpperCase()+" PASS");
		report.flush();	
				
	}

	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub		
	
		test.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+" Test case FAILED due to below issues:", ExtentColor.RED));
        test.log(Status.INFO,result.getThrowable());
    	String Path =  TestUtil.captureScreenshot(result);
        
        try 
        {
        	test.log(Status.FAIL,"sc", MediaEntityBuilder.createScreenCaptureFromPath(Path).build());
			//test.log(Status.FAIL,"Snapshot below: " + test.addScreenCaptureFromPath(TestUtil.screenShotName));
		} catch (IOException e) 
        {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		report.flush();	
		
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

}
