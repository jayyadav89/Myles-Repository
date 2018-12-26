package Insta;

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

import MylesBooking.BookingInMyles;

import com.myles.generic.pkg.Generic_Methods;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class InstaScripts extends Generic_Methods{

	ExtentReports report;
	ExtentTest logger;
	
	BookingInMyles bookingmyles=new BookingInMyles();
	Insta insta=new Insta();
	
	@BeforeTest
	public void ExtentReportGen(){
     report=new ExtentReports("D:\\Myles Framework\\Automation Result\\InstaBooking.html");
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
	
	
	
	
	@Test(priority=1,enabled=false)
	public void fn_InstaBooking() throws IOException, InterruptedException, EncryptedDocumentException, InvalidFormatException{
		logger=report.startTest("InstaBooking");
		try{
			insta.InstaBooking();
			logger.log(LogStatus.INFO, "Insta booking created successfully");
		 }
		 catch(FileNotFoundException e){
			logger.log(LogStatus.INFO, "Insta booking not created: Failed");
			}
	       }
	
	
	
	@Test(priority=2,enabled=false)
	public void fn_DSOpen() throws IOException, InterruptedException, EncryptedDocumentException, InvalidFormatException{
		logger=report.startTest("DS Open");
		try{
			insta.DSOpen();
			logger.log(LogStatus.INFO, "DS Opened successfully");
		 }
		 catch(FileNotFoundException e){
			logger.log(LogStatus.INFO, "DS not Opened: Failed");
			}
	       }
	
	
	
	
	@Test(priority=3,enabled=false)
	public void fn_InstaExtension() throws IOException, InterruptedException, EncryptedDocumentException, InvalidFormatException{
		logger=report.startTest("Insta Extension");
		try{
			insta.InstaExtension();
			logger.log(LogStatus.INFO, "Extension done successfully");
		 }
		 catch(FileNotFoundException e){
			logger.log(LogStatus.INFO, "Extension not done successfully: Failed");
			}
	       }
	
	
	
	@Test(priority=4,enabled=false)
	public void fn_DamageInvoice() throws IOException, InterruptedException, EncryptedDocumentException, InvalidFormatException{
		logger=report.startTest("Damage Invoice");
		try{
//			bookingmyles.MylesBooking2();
//			Thread.sleep(2000);
			insta.DSOpen();
			Thread.sleep(2000);
			insta.DamageInvoice();
			logger.log(LogStatus.INFO, "Damage Invoice created successfully");
		 }
		 catch(FileNotFoundException e){
			logger.log(LogStatus.INFO, "Damage Invoice not created successfully: Failed");
			}
	       }
	
	
	
	@Test(priority=5,enabled=false)
	public void fn_DSClose() throws IOException, InterruptedException, EncryptedDocumentException, InvalidFormatException{
		logger=report.startTest("DS Close");
		try{
//			bookingmyles.MylesBooking2();
//			Thread.sleep(2000);
//			insta.DSOpen();
//			Thread.sleep(2000);
			insta.DSClose();
			logger.log(LogStatus.INFO, "DS Closed successfully");
		 }
		 catch(FileNotFoundException e){
			logger.log(LogStatus.INFO, "DS not Closed successfully: Failed");
			}
	       }
	
	
	
	
	
	
	@Test(priority=5,enabled=false)
	public void fn_PromotionMaster() throws IOException, InterruptedException, EncryptedDocumentException, InvalidFormatException{
		logger=report.startTest("Promotion Master");
		try{
			insta.PromotionMaster();
			logger.log(LogStatus.INFO, "Promotion created successfully");
		 }
		 catch(FileNotFoundException e){
			logger.log(LogStatus.INFO, "Promotion not created successfully: Failed");
			}
	       }
	
	
	
	
//	@Test(priority=6,enabled=true)
	public void fn_Extension() throws IOException, InterruptedException, EncryptedDocumentException, InvalidFormatException{
		logger=report.startTest("Extension");
		try{
			bookingmyles.MylesBooking2();
			Thread.sleep(2000);
			insta.DSOpen();
			Thread.sleep(2000);
			insta.Extension();
			logger.log(LogStatus.INFO, "Extension successfully");
		 }
		 catch(FileNotFoundException e){
			logger.log(LogStatus.INFO, "Extension not successfully: Failed");
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
