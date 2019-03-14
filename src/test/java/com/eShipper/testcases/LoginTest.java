package com.eShipper.testcases;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITest;
import org.testng.ITestResult;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.eShipper.base.TestBase;

public class LoginTest extends TestBase implements ITest{

	String TCName = "Login Eshipper as Admin and Click on Tracking";
	ExtentTest Test;
	ExtentTest parentTest;
	ExtentTest childTest;
	
	 @Override
	public String getTestName() 
	{
		// TODO Auto-generated method stub
		return TCName;
	}
		
	
	@Test
	public void loginAsAdmin() throws InterruptedException {
		
		childTest = test.createNode("Open Application URL");
		childTest.log(Status.INFO, MarkupHelper.createLabel("The url is opened through Chrome", ExtentColor.BLUE));
		
		Thread.sleep(3000);
		log.debug("Inside Login test");
		
		WebElement Username = driver.findElement(By.xpath(OR.getProperty("LP_Username_Txt_Xpth")));
		WebElement NxtBtn =driver.findElement(By.xpath(OR.getProperty("LP_Nxt_Btn_Xpth")));
		WebElement Password = driver.findElement(By.cssSelector(OR.getProperty("LP_Password_Txt_Css")));
		WebElement Loginbtn = driver.findElement(By.xpath(OR.getProperty("LP_Submit_Btn_Xpth")));
		
		WebDriverWait wait = new WebDriverWait(driver,300);
	   				
		Username.sendKeys("admin@eshipper.com");		
		NxtBtn.click();
		Password.sendKeys("admin");
		wait.until(ExpectedConditions.visibilityOf(Loginbtn));
		Loginbtn.click();
		
		log.debug("Login succesfully executed");		 
		Thread.sleep(3000);
		
		//Assert login with any Main Menu
		WebElement Tracking_Menu = driver.findElement(By.xpath(OR.getProperty("Side_MainMenu_List")+"span[contains(text(),'Tracking')]"));
		Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("Side_MainMenu_List")+"span[contains(text(),'Tracking')]")));
		Tracking_Menu.click();
		
		//Verify the Sub-menus by clicking on each menu
		List<WebElement>  SubMenus_List= driver.findElements(By.xpath("//ul[contains(@id,'tracking')]/child:: li/a"));
		
		//print all the sub-menus
		String Expectedmenulist="Order Search,Today's Orders,Job Board";
		String Actualmenulist="";
		for(WebElement submenu:SubMenus_List)
		{
			System.out.println(submenu.getText());
			Actualmenulist = Actualmenulist+","+submenu.getText();					
		}
		
		while(Actualmenulist.charAt(0) == ',')
		{
		  Actualmenulist=Actualmenulist.substring(1);
		}
		
		System.out.println(Actualmenulist);			
		Assert.assertEquals(Expectedmenulist, Actualmenulist);
		test.log(Status.INFO,"Values match");
		
		//Assert.assertEquals("1","0");
		
		
		
		//click on submenu link
		SubMenus_List.get(1).click();	
		test.log(Status.INFO,"The value is captured even after report flush after above failure");
	
		
	}

}