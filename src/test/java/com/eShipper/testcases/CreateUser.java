package com.eShipper.testcases;

import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.ITest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.eShipper.base.TestBase;
import com.eShipper.listeners.CustomListeners;
import com.eShipper.utilities.TestUtil;


//@Listeners(CustomListeners.class)
public class CreateUser extends TestBase implements ITest{

	String TCName = "Add User 2nd Test";
	
	 @Override
	public String getTestName() 
	{
		// TODO Auto-generated method stub
		return TCName;
	}

	 //String sheetName = "CreateUser_Details";
	@Test(dataProviderClass=TestUtil.class,dataProvider="dp")
	public void createUser(String Userid, String Email, String Phone, String Fax, String Enabled, String Roles)
	{
		
		int i = 0;
		//childTest = parentTest.createNode("Test Parameter + " + i);
		test.log(Status.INFO, MarkupHelper.createLabel("The usedrid is", ExtentColor.BLUE));
		i++;
		
		System.out.println(Userid);
				
		//Alert alert = wait.until(ExpectedConditions.alertIsPresent());		
		//Assert.assertTrue(alert.getText().contains("Sucess")); 	
	}
	
	
	

}
