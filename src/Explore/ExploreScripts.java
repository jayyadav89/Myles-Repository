package Explore;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
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
	CarSwap explore=new CarSwap();
	@BeforeTest
	public void ExtentReportGen(){
//	  report=new ExtentReports("D:\\Myles Framework\\Automation Result\\MylesBooking.html");
		report=new ExtentReports("C:\\Users\\jay.yadav\\Downloads\\Myles\\test-output\\Extent Report\\Explore.html");
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
