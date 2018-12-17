package MylesBooking;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Insta.Insta;

import com.myles.generic.pkg.Generic_Methods;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class MylesBooking_Scripts extends Generic_Methods{

	ExtentReports report;
	ExtentTest logger;
	
	BookingInMyles bookingmyles=new BookingInMyles();
	Insta insta=new Insta();
	
	@BeforeTest
	public void ExtentReportGen(){
//	  report=new ExtentReports("D:\\Myles Framework\\Automation Result\\MylesBooking.html");
		report=new ExtentReports("C:\\Users\\jay.yadav\\Downloads\\Myles\\test-output\\Extent Report\\MylesBooking.html");
	}
	
	
//	@BeforeMethod
//	public void fn_SignIn() throws InvalidFormatException, FileNotFoundException, InterruptedException, IOException{
//		bookingmyles.SignIn();
//	}
	
	
	
	@Test(priority=1,enabled=false)
	public void fn_MylesBooking() throws IOException, InterruptedException, EncryptedDocumentException, InvalidFormatException{
		logger=report.startTest("MylesBooking");
		try{
			bookingmyles.MylesBooking2();
			logger.log(LogStatus.INFO, "Myles booking done successfully");
		 }
		 catch(FileNotFoundException e){
			logger.log(LogStatus.INFO, "Myles booking: Failed");
			}
	       }
	
	
	
	@Test(priority=2,enabled=false)
	public void fn_MylesCancelBooking() throws IOException, InterruptedException, EncryptedDocumentException, InvalidFormatException{
		logger=report.startTest("MylesCancelBooking");
		try{
			bookingmyles.MylesCancelBooking();
			logger.log(LogStatus.INFO, "Myles booking cancelled successfully");
		 }
		 catch(FileNotFoundException e){
			logger.log(LogStatus.INFO, "Myles booking not cancelled: Failed");
			}
	       }
	
	
	
	@Test(priority=3,enabled=true)
	public void fn_MylesExtension() throws IOException, InterruptedException, EncryptedDocumentException, InvalidFormatException{
		logger=report.startTest("MylesExtension");
		try{
			bookingmyles.MylesBooking2();
			Thread.sleep(2000);
			insta.DSOpen();
			Thread.sleep(2000);
			bookingmyles.MylesExtension();
			logger.log(LogStatus.INFO, "Myles booking extension done successfully");
		 }
		 catch(FileNotFoundException e){
			logger.log(LogStatus.INFO, "Myles booking extension not done: Failed");
			}
	       }
	
	
	
	
	
//	@Test(priority=3,enabled=true)
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
	
	
	
//	@Test(priority=4,enabled=true)
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
