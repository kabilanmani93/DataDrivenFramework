package com.eShipper.testcases;

import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.ITest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.eShipper.base.TestBase;


public class AddUser extends TestBase implements ITest{

	String TCName = "Add User 2nd Test";
	ExtentTest Test;
	ExtentTest parentTest;
	ExtentTest childTest;
	
	 @Override
	public String getTestName() 
	{
		// TODO Auto-generated method stub
		return TCName;
	}

	
	@Test(dataProvider="getUserData")
	public void CreateUser(String Userid, String Email, String Phone, String Fax, String Enabled, String Roles)
	{
		
		int i = 0;
		childTest = parentTest.createNode("Test Parameter + " + i);
		childTest.log(Status.INFO, MarkupHelper.createLabel("The url is opened through Chrome", ExtentColor.BLUE));
		i++;
		
		System.out.println(Userid);
		
		String[][] data = (String[][]) getUserData();
		Markup m = MarkupHelper.createTable(data);
		test.info(m);
		
		//Alert alert = wait.until(ExpectedConditions.alertIsPresent());		
		//Assert.assertTrue(alert.getText().contains("Sucess")); 	
	}
	
	
	@DataProvider
	public Object[][] getUserData()
	{
		String sheetName = "CreateUser_Details";
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
