package www.ilab.test;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class AutomationILab {

	PositionSet positionSet = null;
	WebDriver driver = null;
	String firefoxpath = System.getProperty("user.dir")+"\\BrowserDrivers\\geckodriver.exe";
	String iefile = System.getProperty("user.dir")+"\\BrowserDrivers\\IEDriverServer.exe";
	String expectedtext= "You need to upload at least one file.";
	public AutomationILab()
	{
		positionSet = new PositionSet();
	}
	@BeforeTest
	@Parameters("browserName")
	public void launchBrowser(String browserName) 
	{
		if(browserName.equalsIgnoreCase("IE"))

		{
			System.setProperty("webdriver.IE.drive",iefile);
			driver = new InternetExplorerDriver();

		}
		else if (browserName.equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.gecko.drive",firefoxpath);
			driver = new FirefoxDriver();
		}
		
	}
	@Test
	public void applyPosition() throws IOException
	{
		loadBrowser();
		userClickLinks();
		userSetValues();
		userSubmitApplication();
		checkExpected();
	}
	public void loadBrowser() throws IOException
	{
		driver.manage().window().maximize();
		driver.get(positionSet.sendurl());
	}

	public void userClickLinks()
	{
		
		driver.findElement(By.linkText("CAREERS")).click();
		driver.findElement(By.xpath("//a[contains(.,'South Africa')]")).click();
		driver.findElement(By.xpath(positionSet.objevalue())).click();
		driver.findElement(By.xpath("//a[contains(.,'Apply Online')]")).click();
	}
	
	public void userSetValues()
	{
		driver.findElement(By.id("applicant_name")).sendKeys(positionSet.sendname());
		driver.findElement(By.id("email")).sendKeys(positionSet.sendemail());
		driver.findElement(By.id("phone")).sendKeys(positionSet.cellNumber());
		
	}
	public void userSubmitApplication()
	{
		driver.findElement(By.id("wpjb_submit")).click();
	}
	public void checkExpected()
	{
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		String actualText = driver.findElement(By.className("wpjb-errors")).getText();
		Assert.assertEquals(actualText,expectedtext);
	}
	@AfterTest
	public void closeBrowser()
	{
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.close();
	}

}
