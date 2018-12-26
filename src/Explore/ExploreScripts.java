package Explore;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Insta.Insta;
import MylesBooking.BookingInMyles;

import com.myles.generic.pkg.Generic_Methods;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ExploreScripts extends Generic_Methods{

	ExtentReports report;
	ExtentTest logger;
	
	BookingInMyles bookingmyles=new BookingInMyles();
	Insta insta=new Insta();
	Explore explore=new Explore();
	@BeforeTest
	public void ExtentReportGen(){
//	  report=new ExtentReports("D:\\Myles Framework\\Automation Result\\MylesBooking.html");
		report=new ExtentReports("D:\\Myles Framework\\Automation Result\\Explore.html");
	}
	
	
	
	@BeforeMethod(alwaysRun = true)
	@Parameters("browser")
      public void setup(String browser) throws IOException, InterruptedException {
       //Check if parameter passed from TestNG is 'firefox'
         if(browser.equalsIgnoreCase("Firefox")){
        	 driver = new FirefoxDriver();
        	 driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
     		 driver.manage().window().maximize();
        }
	       else if(browser.equalsIgnoreCase("chrome")){
			  System.setProperty("webdriver.chrome.driver", "./ExeFolder/chromedriver.exe");
			  driver=new ChromeDriver();
			  driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			  driver.manage().window().maximize();
         }
           else if(browser.equalsIgnoreCase("IE")){
 			  System.setProperty("webdriver.ie.driver", "drivers\\IEDriverServer.exe");
 			  driver=new InternetExplorerDriver();
 			  driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
 			  driver.manage().window().maximize();
         }
	    }
	
	
	
	
	
	
	@Test(priority=1,enabled=true)
	public void fn_CarSwap() throws IOException, InterruptedException, EncryptedDocumentException, InvalidFormatException{
		logger=report.startTest("Car Swap");
		try{
			bookingmyles.MylesBooking2();
			Thread.sleep(2000);
			explore.CarSwap();
			logger.log(LogStatus.INFO, "Car Swapped successfully");
		 }
		 catch(FileNotFoundException e){
			logger.log(LogStatus.INFO, "Car not Swapped : Failed");
			}
	       }
	

	
	
	@Test(priority=2,enabled=true)
	public void fn_ExploreCancel() throws IOException, InterruptedException, EncryptedDocumentException, InvalidFormatException{
		logger=report.startTest("Car Swap");
		try{
			bookingmyles.MylesBooking2();
			Thread.sleep(2000);
			explore.ExploreCancel();
			logger.log(LogStatus.INFO, "Booking Cancelled successfully");
		 }
		 catch(FileNotFoundException e){
			logger.log(LogStatus.INFO, "Booking Cancellation : Failed");
			}
	       }
	
	
	
	
	
	
	
	@AfterMethod(alwaysRun= true)
	public void TearDown(ITestResult result) throws IOException, InterruptedException{
		if(result.getStatus()==ITestResult.FAILURE){
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File("D:/Myles Workspace/Screenshots/screenshot" + result.getName().toString().trim()+".png"));
		}
		report.endTest(logger);
		report.flush();
		
	}

	
	
	
	
	
	
	
	
       }
