package com.eShipper.base;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.eShipper.utilities.ExcelReader;
import com.eShipper.utilities.ExtentManager;
import com.eShipper.utilities.TestUtil;

public class TestBase {
	
	static String resource_basePath = System.getProperty("user.dir")+"\\src\\test\\resources\\";
	
	public static WebDriver driver;
	public static Properties config = new Properties();;
	public static Properties OR = new Properties();
	public static FileInputStream fis;
	public static Logger log =Logger.getLogger("devpinoyLogger");

	public static ExcelReader excel  = new  ExcelReader(resource_basePath + "excel\\AppData.xlsx");
	public static WebDriverWait wait; 
	public ExtentReports report =ExtentManager.getInstance();
	public static ExtentTest test;		
	public static ExtentTest parentTest;
	public static ExtentTest childTest;
	
	
	@BeforeSuite
	public void setUp() {
		
		PropertyConfigurator.configure(resource_basePath + "properties\\log4j.properties");
		
		if(driver == null)
		{
			
			//------------------------------------------Config Properties load
			try
			{
				String Config_Properties_Source = resource_basePath + "properties\\Config.properties";
				fis = new FileInputStream(Config_Properties_Source);	
			}
			catch(FileNotFoundException e)
			{
				e.printStackTrace();
			}
			try 
			{
				config.load(fis);
				log.debug("Config file loaded");
			}
			catch (IOException e1) 
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			//--------------------------------------------OR Properties load
			try
			{
				String OR_Properties_Source = System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\OR.properties";
				fis = new FileInputStream(OR_Properties_Source);	
			}
			catch(FileNotFoundException e)
			{
				e.printStackTrace();
			}
			
			try
			{
				OR.load(fis);
				log.debug("OR file loaded");
			} 
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		//------------------------------------------Initialise Browser
		String BrowserName = config.getProperty("browser");
		
		if(BrowserName.equalsIgnoreCase("firefox")) 
		{
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"\\src\\test\\resources\\executables\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
		
		
		else if(BrowserName.equalsIgnoreCase("Chrome"))
			
		{
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\test\\resources\\executables\\chromedriver.exe");
			driver = new ChromeDriver();
			log.debug("Chrome launched");
		}
		
		else if(BrowserName.equalsIgnoreCase("ie"))
		{
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}
		
		System.out.println(config.getProperty("browser"));
		
		//--------------------------------------Maximise and get Application URL
		driver.get(config.getProperty("ApplicationURL"));
		log.debug("Navigated to : "+ config.getProperty("ApplicationURL"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("ImplicitWait")), TimeUnit.SECONDS);
		
		wait= new WebDriverWait(driver,10);
				
	}

	public boolean isElementPresent(By by)
	{
		try
		{
			driver.findElement(by);
			return true;
		}
		catch(NoSuchElementException e)
		{
			return false;			
		}
	}
	
	public void click(String locator) {

		if (locator.endsWith("_CSS")) {
			driver.findElement(By.cssSelector(OR.getProperty(locator))).click();
		} else if (locator.endsWith("_XPATH")) {
			driver.findElement(By.xpath(OR.getProperty(locator))).click();
		} else if (locator.endsWith("_ID")) {
			driver.findElement(By.id(OR.getProperty(locator))).click();
		}
		test.log(Status.INFO, "Clicking on : " + locator);
	}

	public void type(String locator, String value) {

		if (locator.endsWith("_CSS")) {
			driver.findElement(By.cssSelector(OR.getProperty(locator))).sendKeys(value);
		} else if (locator.endsWith("_XPATH")) {
			driver.findElement(By.xpath(OR.getProperty(locator))).sendKeys(value);
		} else if (locator.endsWith("_ID")) {
			driver.findElement(By.id(OR.getProperty(locator))).sendKeys(value);
		}

		test.log(Status.INFO, "Typing in : " + locator + " entered value as " + value);

	}
	

	static WebElement dropdown;
	public void select(String locator, String value) {

	
		if (locator.endsWith("_CSS")) {
			dropdown = driver.findElement(By.cssSelector(OR.getProperty(locator)));
		} else if (locator.endsWith("_XPATH")) {
			dropdown = driver.findElement(By.xpath(OR.getProperty(locator)));
		} else if (locator.endsWith("_ID")) {
			dropdown = driver.findElement(By.id(OR.getProperty(locator)));
		}
		
		Select select = new Select(dropdown);
		select.selectByVisibleText(value);

		test.log(Status.INFO, "Selecting from dropdown : " + locator + " value as " + value);

	}


	public static void verifyEquals(String expected, String actual) throws IOException {

		try {

			Assert.assertEquals(actual, expected);

		} catch (Throwable t) {

			String Path =  TestUtil.captureScreenshot();
			// Extent Reports
			test.log(Status.FAIL, " Verification failed with exception : " + t.getMessage());
			test.log(Status.FAIL,"sc", MediaEntityBuilder.createScreenCaptureFromPath(Path).build());
		}

	}
	
	
	@AfterSuite
	public void tearDown() {
		
		if(driver != null)
		{
			driver.quit();
		}
		log.debug("test execution completed");
		
	}

	public String getTestName() {
		// TODO Auto-generated method stub
		return null;
	}
}
