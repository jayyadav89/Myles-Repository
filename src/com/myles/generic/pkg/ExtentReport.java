package com.myles.generic.pkg;

import java.io.File;
import java.io.IOException;

import org.apache.bcel.classfile.Utility;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ExtentReport {

	ExtentReports report;
	ExtentTest logger;
	WebDriver driver;
	
	
	@Test
	public void verifyBlogTitle(){
		
		report=new ExtentReports("C:\\Report1\\automation.html");
		logger=report.startTest("Test1VerifyBlogTitle");
		
		driver=new FirefoxDriver();
		
		logger.log(LogStatus.INFO, "Browser started");
		
		driver.manage().window().maximize();
		
		driver.get("http://www.learn-automation.com");
		
		logger.log(LogStatus.INFO, "Application is up and running");
		
		String title=driver.getTitle();
		
		Assert.assertTrue(title.contains("Selenium"));
		logger.log(LogStatus.PASS, "Title verified");
	}
	
	
	@AfterMethod
	public void TearDown(ITestResult result) throws IOException{
		if(result.getStatus()==ITestResult.FAILURE){
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File("C:/Screenshot/screenshot.png"));
		}
		
		report.endTest(logger);
		report.flush();
		
		driver.get("C:\\Report1\\automation.html");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
