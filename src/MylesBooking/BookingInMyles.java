package MylesBooking;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.myles.generic.pkg.Generic_Methods;


public class BookingInMyles extends Generic_Methods{
	
//	Workbook wb;
//	Actions act;
//	WebDriverWait wait;
//	Scanner scan;
	
	
	public void URL() throws InterruptedException, InvalidFormatException, FileNotFoundException, IOException{
		
		driver.get("https://qa2.mylescars.com/");
		Scanner scan = new Scanner(System.in);
		
		
		
	}
	
	
	
	
	
	public void MylesBooking() throws EncryptedDocumentException, InvalidFormatException, FileNotFoundException, IOException, InterruptedException{
        URL();
		Workbook wb = WorkbookFactory.create(new FileInputStream(new File("./Myles.xlsx"))); 
        driver.findElement(By.id("hmsignin")).click();
		String mobNo = wb.getSheet("Myles").getRow(1).getCell(1).getStringCellValue();
		System.out.println(mobNo);
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("mobile")));
		driver.findElement(By.id("mobile")).sendKeys(mobNo);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("contact_auto")));
		driver.findElement(By.id("contact_auto")).click();
		Thread.sleep(1000);
        Actions act =new Actions(driver);
		
		try {
			String pass = wb.getSheet("Myles").getRow(1).getCell(2).getStringCellValue();
			WebDriverWait wait1 = new WebDriverWait(driver, 40);
		    wait1.until(ExpectedConditions.presenceOfElementLocated(By.id("userpassword")));
		    driver.findElement(By.id("userpassword")).sendKeys(pass);
			driver.findElement(By.className("sign_in")).click();
		} 
		catch (ElementNotVisibleException e)
		{
			System.out.println("User is not registered");
		}
		driver.navigate().refresh();
		WebElement cityDrpDwn = driver.findElement(By.id("mobilecity"));
		Select selCity = new Select(cityDrpDwn);
		String loc = wb.getSheet("Myles").getRow(1).getCell(3).getStringCellValue();
		selCity.selectByVisibleText(loc);

		driver.findElement(By.id("inputFieldSF1")).click();
		driver.findElement(By.xpath("//span[text()='Next']")).click();
		String pickDate = wb.getSheet("Myles").getRow(1).getCell(4).getStringCellValue();
		driver.findElement(By.xpath("//a[contains(text(),'"+pickDate+"')]")).click();

		WebElement pickDrpDwn = driver.findElement(By.id("inputFieldPT"));
		Select selPickTym = new Select(pickDrpDwn);
		Thread.sleep(2000);
		String pickTime = wb.getSheet("Myles").getRow(1).getCell(5).getStringCellValue();
		Thread.sleep(2000);
		selPickTym.selectByValue(pickTime);
		Thread.sleep(2000);
		driver.findElement(By.id("inputFieldSF2")).click();
		Thread.sleep(2000);
		String dropDate = wb.getSheet("Myles").getRow(1).getCell(6).getStringCellValue();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[contains(text(),'"+dropDate+"')]")).click();
		Thread.sleep(2000);
		WebElement dropDrpDwn = driver.findElement(By.id("inputFieldDT"));
		Thread.sleep(2000);
		Select selDropTym = new Select(dropDrpDwn);
		Thread.sleep(2000);
		String dropTime = wb.getSheet("Myles").getRow(1).getCell(7).getStringCellValue();
		Thread.sleep(2000);
		selDropTym.selectByValue(dropTime);
		Thread.sleep(2000);
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.className("H_Button")));
		driver.findElement(By.className("H_Button")).click();
		Thread.sleep(5000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        List<WebElement> cars = driver.findElements(By.className("HomeP_carBr "));
		if (cars.size() > 0) {
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@id='sel2']")));
			WebElement siteLoc = driver.findElement(By.xpath("//select[@id='sel2']"));
			Select selSiteLoc = new Select(siteLoc);
			selSiteLoc.selectByIndex(1);
			System.out.println("1");
		//    System.out.println("2");
			Thread.sleep(3000);
			
			WebElement popup=driver.findElement(By.xpath("//a[@id='nv_js-box-close-button_13093']"));
			if(popup.isDisplayed()){
			popup.click();
		}
		else{
			System.out.println("popup not present");
		}
		
			driver.findElement(By.xpath("//select[@id='sel2']//following-sibling::div//button[@id='search_bookthis2']")).click();
			System.out.println("3");
			Thread.sleep(3000);
			
///////////////////////////////////////////////////////////////////////////////////////////////////			
//			try{  
		
	//		    WebElement popup2=driver.findElement(By.xpath("//a[@id='nv_js-box-close-button_13132']"));
		    
//			    if(driver.findElement(By.xpath("//a[@id='nv_js-box-close-button_13132']")).isDisplayed()){
//					driver.findElement(By.xpath("//a[@id='nv_js-box-close-button_13132']")).click();
					
					wait.until(ExpectedConditions.presenceOfElementLocated(By.id("proceed_auto")));
					System.out.println("5");
					driver.findElement(By.id("proceed_auto")).click();
				    Thread.sleep(5000);
				    
                      js.executeScript("arguments[0].click();",driver.findElement(By.xpath("//div[@id='PickupDetails']//button[@id='pickupdropupAddr' and text()='Proceed']")));
                         Thread.sleep(2000);
				    
				    
				    js.executeScript("arguments[0].click();",
								driver.findElement(By.xpath("//label[text()='I accept MYLES ']")));
						Thread.sleep(5000);
						
						 
							WebElement cardNum = driver.findElement(By.id("credit_card_number"));
							Thread.sleep(2000);
						    wait.until(ExpectedConditions.presenceOfElementLocated(By.id("credit_card_number")));
						    cardNum.sendKeys("4242424242424242");
							Thread.sleep(2000);
							driver.findElement(By.id("creditcardname")).sendKeys("test");
							WebElement expMonth = driver.findElement(By.id("credit_card_exp_month"));
							Select sel = new Select(expMonth);
							sel.selectByVisibleText("10");
							WebElement expYear = driver.findElement(By.id("credit_card_exp_year"));
							Select sel1 = new Select(expYear);
							sel1.selectByVisibleText("2020");
							driver.findElement(By.id("credit_security_code")).sendKeys("123");
							driver.findElement(By.id("card_payment")).click();
							Thread.sleep(1000);
							driver.findElement(By.id("OTP")).sendKeys("123123");
							driver.findElement(By.name("proceed")).click();
							
							String bookId = driver.findElement(By.xpath("//p[@class='bookID_T']")).getText();
				////////////takes booking id 		
							String BookingId=bookId.substring(69, 76).trim();
							
							System.out.println(BookingId);
							
							ReadWriteExcel("./Myles.xlsx","Myles-Cancel",2,3,BookingId);
							ReadWriteExcel("./Myles.xlsx","Insta-Open",1,3,BookingId);
							ReadWriteExcel("./Myles.xlsx","Insta-Open",1,4,BookingId);
							ReadWriteExcel("./Myles.xlsx","Insta-Close",1,3,BookingId);
							ReadWriteExcel("./Myles.xlsx","Insta-Extension",1,3,BookingId);
							ReadWriteExcel("./Myles.xlsx","Invoice-Damage",1,3,BookingId);
							ReadWriteExcel("./Myles.xlsx","Swap-Car",1,3,BookingId);
							ReadWriteExcel("./Myles.xlsx","Myles-Extension",1,3,BookingId);
							ReadWriteExcel("./Myles.xlsx","Explore-Cancel",1,3,BookingId);
//------------------------------------------------------------------------------------------------------------							
//							driver.findElement(By.linkText("Pay Now")).click();
//							Thread.sleep(2000);
//							wait.until(ExpectedConditions.presenceOfElementLocated(By.id("credit_card_number")));
//						    cardNum.sendKeys("4242424242424242");
//							Thread.sleep(2000);
//							driver.findElement(By.id("creditcardname")).sendKeys("test");
//							WebElement expMonth1 = driver.findElement(By.id("credit_card_exp_month"));
//							Select sel2 = new Select(expMonth1);
//							sel2.selectByVisibleText("10");
//							WebElement expYear1 = driver.findElement(By.id("credit_card_exp_year"));
//							Select sel3 = new Select(expYear1);
//							sel3.selectByVisibleText("2020");
//							driver.findElement(By.id("credit_security_code")).sendKeys("123");
//							driver.findElement(By.id("card_payment")).click();
//							Thread.sleep(1000);
//							driver.findElement(By.id("OTP")).sendKeys("123123");
//							driver.findElement(By.name("proceed")).click();
						
							
			    }			
							
							
			}	
	
	
	
	
	
	
	  public void MylesBooking2() throws EncryptedDocumentException, InvalidFormatException, FileNotFoundException, IOException, InterruptedException{

        URL();
		Workbook wb = WorkbookFactory.create(new FileInputStream(new File("./Myles.xlsx"))); 
        driver.findElement(By.id("hmsignin")).click();
		String mobNo = wb.getSheet("Myles").getRow(1).getCell(1).getStringCellValue();
		System.out.println(mobNo);
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("mobile")));
		driver.findElement(By.id("mobile")).sendKeys(mobNo);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("contact_auto")));
		driver.findElement(By.id("contact_auto")).click();
		Thread.sleep(1000);
        Actions act =new Actions(driver);
		
		try {
			String pass = wb.getSheet("Myles").getRow(1).getCell(2).getStringCellValue();
			WebDriverWait wait1 = new WebDriverWait(driver, 40);
		    wait1.until(ExpectedConditions.presenceOfElementLocated(By.id("userpassword")));
		    driver.findElement(By.id("userpassword")).sendKeys(pass);
			driver.findElement(By.className("sign_in")).click();
		} 
		catch (ElementNotVisibleException e)
		{
			System.out.println("User is not registered");
		}
		driver.navigate().refresh();
		WebElement cityDrpDwn = driver.findElement(By.id("mobilecity"));
		Select selCity = new Select(cityDrpDwn);
		String loc = wb.getSheet("Myles").getRow(1).getCell(3).getStringCellValue();
		selCity.selectByVisibleText(loc);

		driver.findElement(By.id("inputFieldSF1")).click();
		driver.findElement(By.xpath("//span[text()='Next']")).click();
		String pickDate = wb.getSheet("Myles").getRow(1).getCell(4).getStringCellValue();
		driver.findElement(By.xpath("//a[contains(text(),'"+pickDate+"')]")).click();

		WebElement pickDrpDwn = driver.findElement(By.id("inputFieldPT"));
		Select selPickTym = new Select(pickDrpDwn);
		Thread.sleep(2000);
		String pickTime = wb.getSheet("Myles").getRow(1).getCell(5).getStringCellValue();
		Thread.sleep(2000);
		selPickTym.selectByValue(pickTime);
		Thread.sleep(2000);
		driver.findElement(By.id("inputFieldSF2")).click();
		Thread.sleep(2000);
		String dropDate = wb.getSheet("Myles").getRow(1).getCell(6).getStringCellValue();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[contains(text(),'"+dropDate+"')]")).click();
		Thread.sleep(2000);
		WebElement dropDrpDwn = driver.findElement(By.id("inputFieldDT"));
		Thread.sleep(2000);
		Select selDropTym = new Select(dropDrpDwn);
		Thread.sleep(2000);
		String dropTime = wb.getSheet("Myles").getRow(1).getCell(7).getStringCellValue();
		Thread.sleep(2000);
		selDropTym.selectByValue(dropTime);
		Thread.sleep(2000);
		
		String ladakh = wb.getSheet("Myles").getRow(1).getCell(8).getStringCellValue();
		if (ladakh.equalsIgnoreCase("yes")) 
		{
        try
		 {
		 WebElement ladakhEle = driver.findElement(By.xpath("//label[@for='ladakh']"));
		 ladakhEle.click();
		 }
		 catch (ElementNotVisibleException e)
		 {
			 System.out.println("Take to ladakh element is not available");
		 
		 }
		 }
		else
		{
			System.out.println("Elemennt to take my car to Ladakh is not select");
		}
		wait.until(ExpectedConditions.presenceOfElementLocated(By.className("H_Button")));
		driver.findElement(By.className("H_Button")).click();
		Thread.sleep(5000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        List<WebElement> cars = driver.findElements(By.className("HomeP_carBr "));
		if (cars.size() > 0) {
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@id='sel2']")));
			WebElement siteLoc = driver.findElement(By.xpath("//select[@id='sel2']"));
			Select selSiteLoc = new Select(siteLoc);
			selSiteLoc.selectByIndex(1);
			System.out.println("1");
		//    System.out.println("2");
			Thread.sleep(3000);
			
			WebElement popup=driver.findElement(By.xpath("//a[@id='nv_js-box-close-button_13093']"));
			if(popup.isDisplayed()){
			popup.click();
		}
		else{
			System.out.println("popup not present");
		}
		
			driver.findElement(By.xpath("//select[@id='sel2']//following-sibling::div//button[@id='search_bookthis2']")).click();
			System.out.println("3");
			Thread.sleep(3000);
			
///////////////////////////////////////////////////////////////////////////////////////////////////			
			try{  
		
	//		    WebElement popup2=driver.findElement(By.xpath("//a[@id='nv_js-box-close-button_13132']"));
		    
			    if(driver.findElement(By.xpath("//a[@id='nv_js-box-close-button_13132']")).isDisplayed()){
					driver.findElement(By.xpath("//a[@id='nv_js-box-close-button_13132']")).click();
					
					
					wait.until(ExpectedConditions.presenceOfElementLocated(By.id("proceed_auto")));
					System.out.println("5");
					driver.findElement(By.id("proceed_auto")).click();
				    Thread.sleep(5000);
				    
				    try {
						 List<WebElement> additionalService = driver.findElements(By.className("newAddresBlock"));
						 if (additionalService.size() > 0) 
						{
							 String addServiceGps = wb.getSheet("Myles").getRow(1).getCell(9).getStringCellValue();
							if (driver.findElement(By.xpath("//label[contains(text(),'Gps Navigation')]")).isDisplayed() && addServiceGps.equalsIgnoreCase("yes")) {
								js.executeScript("arguments[0].click();",driver.findElement(By.xpath("//label[contains(text(),'Gps Navigation')]")));
								System.out.println("gps-navigation is available");
							} 
							else
							{
								System.out.println("Gps navigation is unavailable");
							}
							Thread.sleep(3000);
						    String addServiceBaby = wb.getSheet("Myles").getRow(1).getCell(10).getStringCellValue();

							if (driver.findElement(By.xpath("//label[contains(text(),'Baby Seat')]")).isDisplayed() && addServiceBaby.equalsIgnoreCase("yes"))
							{
								js.executeScript("arguments[0].click();",driver.findElement(By.xpath("//label[contains(text(),'Baby Seat')]")));
								System.out.println("Baby seat is available");
							} 
							else 
							{
								System.out.println("BabySeat is unavailable");

							}
							js.executeScript("arguments[0].click();",driver.findElement(By.xpath("//div[@id='PickupDetails']//button[@id='pickupdropupAddr' and text()='Proceed']")));
						}

						else 
						{
							System.out.println("Both Gps navigation and Baby seat are unavailable");
							js.executeScript("arguments[0].click();",driver.findElement(By.xpath("//div[@id='PickupDetails']//button[@id='pickupdropupAddr' and text()='Proceed']")));
						}
			            }
					catch (NoSuchElementException a)
					    {
						System.out.println("no gps-navigattion and baby seat available");
					    }
			            Thread.sleep(4000);
					    String LDW = wb.getSheet("Myles").getRow(1).getCell(11).getStringCellValue();
					 if (LDW.equalsIgnoreCase("yes")) {
					List<WebElement> dynamicElement = driver.findElements(By.xpath("//label[contains(text(),'Myles Secure')]/../input[@type='checkbox']"));
					 System.out.println("size: " +dynamicElement.size());
						
					if(dynamicElement.size() != 0){
						wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("label.mylesCoverHdn")));
					    WebElement chkBx = driver.findElement(By.cssSelector("label.mylesCoverHdn"));
						//WebElement chkBx = driver.findElement(By.xpath("//div[@id='PaymentDetails']//div[@class='mylesCover']//label"));
						System.out.println("Element is present");
					    act.moveToElement(chkBx).click().build().perform();
					    System.out.println("clicked");
					}

					}
					 else if(LDW.equalsIgnoreCase("no"))
					 {
						 System.out.println("Security deposit pay without LDW");
					 }
						//Thread.sleep(2000);

						Thread.sleep(3000);

						js.executeScript("arguments[0].click();",
								driver.findElement(By.xpath("//label[text()='I accept MYLES ']")));
						Thread.sleep(5000);
						// wait.until(ExpectedConditions.presenceOfElementLocated(By.id("credit_card_number")));
						 String CreditDebit =driver.findElement(By.xpath("//a[@href='#CreditDebit']")).getText();
						 System.out.println(CreditDebit);
					     String wallet =driver.findElement(By.xpath("//a[@href='#Wallet']")).getText();
						 System.out.println(wallet);
						 String netBanking =driver.findElement(By.xpath("//a[@href='#NetBanking']")).getText();
						 System.out.println(netBanking);
						 String payment = wb.getSheet("Myles").getRow(1).getCell(12).getStringCellValue();

						 if (CreditDebit.equalsIgnoreCase(payment))
						 {
							WebElement cardNum = driver.findElement(By.id("credit_card_number"));
							Thread.sleep(2000);
						    wait.until(ExpectedConditions.presenceOfElementLocated(By.id("credit_card_number")));
						    cardNum.sendKeys("4242424242424242");
							Thread.sleep(2000);
							driver.findElement(By.id("creditcardname")).sendKeys("test");
							WebElement expMonth = driver.findElement(By.id("credit_card_exp_month"));
							Select sel = new Select(expMonth);
							sel.selectByVisibleText("10");
							WebElement expYear = driver.findElement(By.id("credit_card_exp_year"));
							Select sel1 = new Select(expYear);
							sel1.selectByVisibleText("2020");
							driver.findElement(By.id("credit_security_code")).sendKeys("123");
							driver.findElement(By.id("card_payment")).click();
							Thread.sleep(1000);
							driver.findElement(By.id("OTP")).sendKeys("123123");
							driver.findElement(By.name("proceed")).click();
							
							String bookId = driver.findElement(By.xpath("//p[@class='bookID_T']")).getText();
				////////////takes booking id 		
							String BookingId=bookId.substring(69, 76).trim();
							
							System.out.println(BookingId);
							
							ReadWriteExcel("./Myles.xlsx","Myles-Cancel",2,3,BookingId);
							
					//		ReadWriteExcel("./Myles.xlsx","Myles-Extension",1,3,BookingId);
							
							ReadWriteExcel("./Myles.xlsx","Insta-Open",1,3,BookingId);
							ReadWriteExcel("./Myles.xlsx","Insta-Open",1,4,BookingId);
							ReadWriteExcel("./Myles.xlsx","Insta-Close",1,3,BookingId);
							ReadWriteExcel("./Myles.xlsx","Insta-Extension",1,3,BookingId);
							ReadWriteExcel("./Myles.xlsx","Invoice-Damage",1,3,BookingId);
							ReadWriteExcel("./Myles.xlsx","Swap-Car",1,3,BookingId);
							ReadWriteExcel("./Myles.xlsx","Myles-Extension",1,3,BookingId);
							ReadWriteExcel("./Myles.xlsx","Explore-Cancel",1,3,BookingId);
//------------------------------------------------------------------------------------------------------------							
							
//						driver.findElement(By.linkText("Pay Now")).click();
//						
//						String securityamt=driver.findElement(By.id("spShowPay")).getText();
//						Integer SecAmt=Integer.parseInt(securityamt);
//						if(SecAmt<5000 || SecAmt==5000){
//							
//							wait.until(ExpectedConditions.presenceOfElementLocated(By.id("credit_card_number")));
//						    cardNum.sendKeys("4242424242424242");
//							Thread.sleep(2000);
//							driver.findElement(By.id("creditcardname")).sendKeys("test");
//							WebElement expMonth1 = driver.findElement(By.id("credit_card_exp_month"));
//							Select sel2 = new Select(expMonth1);
//							sel2.selectByVisibleText("10");
//							WebElement expYear1 = driver.findElement(By.id("credit_card_exp_year"));
//							Select sel3 = new Select(expYear1);
//							sel3.selectByVisibleText("2020");
//							driver.findElement(By.id("credit_security_code")).sendKeys("123");
//							driver.findElement(By.id("card_payment")).click();
//							Thread.sleep(1000);
//							driver.findElement(By.id("OTP")).sendKeys("123123");
//							driver.findElement(By.name("proceed")).click();
//							
//							
//							
//						}
//							
//						else{
//							driver.close();
//						}
							
							
							
							
		           //		scan.close();	
						 }
						 else if (wallet.equalsIgnoreCase(payment))
						 {
						   driver.findElement(By.id("paytm")).click();
						   driver.findElement(By.id("w_payment")).click();
						}
						else if (netBanking.equalsIgnoreCase(payment)) 
						 {
						  driver.findElement(By.xpath("//a[@href='#NetBanking']")).click();
						 }
						File srcfile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
						File destFile = new File("./Shots/Booking.png");
						FileUtils.copyFile(srcfile, destFile);
						}
					
				}
				
	//			}
			  
			catch(Exception e){
					wait.until(ExpectedConditions.presenceOfElementLocated(By.id("proceed_auto")));
					
					driver.findElement(By.id("proceed_auto")).click();
				    Thread.sleep(3000);
				    
				    try {
						 List<WebElement> additionalService = driver.findElements(By.className("newAddresBlock"));
						 if (additionalService.size() > 0) 
						{
							 String addServiceGps = wb.getSheet("Myles").getRow(1).getCell(9).getStringCellValue();
							if (driver.findElement(By.xpath("//label[contains(text(),'Gps Navigation')]")).isDisplayed() && addServiceGps.equalsIgnoreCase("yes")) {
								js.executeScript("arguments[0].click();",driver.findElement(By.xpath("//label[contains(text(),'Gps Navigation')]")));
								System.out.println("gps-navigation is available");
							} 
							else
							{
								System.out.println("Gps navigation is unavailable");
							}
							Thread.sleep(3000);
						    String addServiceBaby = wb.getSheet("Myles").getRow(1).getCell(10).getStringCellValue();

							if (driver.findElement(By.xpath("//label[contains(text(),'Baby Seat')]")).isDisplayed() && addServiceBaby.equalsIgnoreCase("yes"))
							{
								js.executeScript("arguments[0].click();",driver.findElement(By.xpath("//label[contains(text(),'Baby Seat')]")));
								System.out.println("Baby seat is available");
							} 
							else 
							{
								System.out.println("BabySeat is unavailable");

							}
							js.executeScript("arguments[0].click();",driver.findElement(By.xpath("//div[@id='PickupDetails']//button[@id='pickupdropupAddr' and text()='Proceed']")));
						}

						else 
						{
							System.out.println("Both Gps navigation and Baby seat are unavailable");
							js.executeScript("arguments[0].click();",driver.findElement(By.xpath("//div[@id='PickupDetails']//button[@id='pickupdropupAddr' and text()='Proceed']")));
						}
			            }
					catch (NoSuchElementException a)
					    {
						System.out.println("no gps-navigattion and baby seat available");
					    }
			            Thread.sleep(4000);
					    String LDW = wb.getSheet("Myles").getRow(1).getCell(11).getStringCellValue();
					 if (LDW.equalsIgnoreCase("yes")) {
					List<WebElement> dynamicElement = driver.findElements(By.xpath("//label[contains(text(),'Myles Secure')]/../input[@type='checkbox']"));
					 System.out.println("size: " +dynamicElement.size());
						
					if(dynamicElement.size() != 0){
						wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("label.mylesCoverHdn")));
					    WebElement chkBx = driver.findElement(By.cssSelector("label.mylesCoverHdn"));
						//WebElement chkBx = driver.findElement(By.xpath("//div[@id='PaymentDetails']//div[@class='mylesCover']//label"));
						System.out.println("Element is present");
					    act.moveToElement(chkBx).click().build().perform();
					    System.out.println("clicked");
					}

					}
					 else if(LDW.equalsIgnoreCase("no"))
					 {
						 System.out.println("Security deposit pay without LDW");
					 }
						Thread.sleep(2000);

						Thread.sleep(3000);

						js.executeScript("arguments[0].click();",
								driver.findElement(By.xpath("//label[text()='I accept MYLES ']")));
						Thread.sleep(5000);
						// wait.until(ExpectedConditions.presenceOfElementLocated(By.id("credit_card_number")));
						 String CreditDebit =driver.findElement(By.xpath("//a[@href='#CreditDebit']")).getText();
						 System.out.println(CreditDebit);
					     String wallet =driver.findElement(By.xpath("//a[@href='#Wallet']")).getText();
						 System.out.println(wallet);
						 String netBanking =driver.findElement(By.xpath("//a[@href='#NetBanking']")).getText();
						 System.out.println(netBanking);
						 String payment = wb.getSheet("Myles").getRow(1).getCell(12).getStringCellValue();

						 if (CreditDebit.equalsIgnoreCase(payment))
						 {
							WebElement cardNum = driver.findElement(By.id("credit_card_number"));
							Thread.sleep(2000);
						    wait.until(ExpectedConditions.presenceOfElementLocated(By.id("credit_card_number")));
						    cardNum.sendKeys("4242424242424242");
							Thread.sleep(2000);
							driver.findElement(By.id("creditcardname")).sendKeys("test");
							WebElement expMonth = driver.findElement(By.id("credit_card_exp_month"));
							Select sel = new Select(expMonth);
							sel.selectByVisibleText("10");
							WebElement expYear = driver.findElement(By.id("credit_card_exp_year"));
							Select sel1 = new Select(expYear);
							sel1.selectByVisibleText("2020");
							driver.findElement(By.id("credit_security_code")).sendKeys("123");
							driver.findElement(By.id("card_payment")).click();
							Thread.sleep(1000);
							driver.findElement(By.id("OTP")).sendKeys("123123");
							driver.findElement(By.name("proceed")).click();
							
							String bookId = driver.findElement(By.xpath("//p[@class='bookID_T']")).getText();
				////////////takes booking id 		
							String BookingId=bookId.substring(69, 76).trim();
							
							System.out.println(BookingId);
							System.out.println("jay");
							
							ReadWriteExcel("./Myles.xlsx","Myles-Cancel",2,3,BookingId);
							
					//		ReadWriteExcel("./Myles.xlsx","Myles-Extension",1,3,BookingId);
							
							ReadWriteExcel("./Myles.xlsx","Insta-Open",1,3,BookingId);
							ReadWriteExcel("./Myles.xlsx","Insta-Open",1,4,BookingId);
							ReadWriteExcel("./Myles.xlsx","Insta-Close",1,3,BookingId);
							ReadWriteExcel("./Myles.xlsx","Insta-Extension",1,3,BookingId);
							ReadWriteExcel("./Myles.xlsx","Invoice-Damage",1,3,BookingId);
							ReadWriteExcel("./Myles.xlsx","Swap-Car",1,3,BookingId);
							ReadWriteExcel("./Myles.xlsx","Myles-Extension",1,3,BookingId);
							
							System.out.println("prakash");
					//		scan.close();
							
							
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////						
//							driver.findElement(By.linkText("Pay Now")).click();
//							
//							String securityamt=driver.findElement(By.id("spShowPay")).getText();
//							Integer SecAmt=Integer.parseInt(securityamt);
//							if(SecAmt<5000 || SecAmt==5000){
//								
//								wait.until(ExpectedConditions.presenceOfElementLocated(By.id("credit_card_number")));
//							    cardNum.sendKeys("4242424242424242");
//								Thread.sleep(2000);
//								driver.findElement(By.id("creditcardname")).sendKeys("test");
//								WebElement expMonth1 = driver.findElement(By.id("credit_card_exp_month"));
//								Select sel2 = new Select(expMonth1);
//								sel2.selectByVisibleText("10");
//								WebElement expYear1 = driver.findElement(By.id("credit_card_exp_year"));
//								Select sel3 = new Select(expYear1);
//								sel3.selectByVisibleText("2020");
//								driver.findElement(By.id("credit_security_code")).sendKeys("123");
//								driver.findElement(By.id("card_payment")).click();
//								Thread.sleep(1000);
//								driver.findElement(By.id("OTP")).sendKeys("123123");
//								driver.findElement(By.name("proceed")).click();
//								
//								
//								
//							}
//								
//							else{
//								driver.close();
//							}
							
							
									
						 }
						 else if (wallet.equalsIgnoreCase(payment))
						 {
						   driver.findElement(By.id("paytm")).click();
						   driver.findElement(By.id("w_payment")).click();
						}
						else if (netBanking.equalsIgnoreCase(payment)) 
						 {
						  driver.findElement(By.xpath("//a[@href='#NetBanking']")).click();
						 }
						File srcfile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
						File destFile = new File("./Shots/Booking.png");
						FileUtils.copyFile(srcfile, destFile);
						}
			
		

		    
  
		   	        }

	}

	
	
	
	
	
	
	
	
	public void MylesBooking5() throws EncryptedDocumentException, InvalidFormatException, FileNotFoundException, IOException, InterruptedException{

//		driver.get("https://qa2.mylescars.com/");
//		Scanner scan = new Scanner(System.in);
//		try{
//			WebDriverWait wait = new WebDriverWait(driver, 60);
//		    wait.until(ExpectedConditions.presenceOfElementLocated(By.id("nv_js-box-close-button_13317")));
//		    driver.findElement(By.xpath("//a[@id='nv_js-box-close-button_13317']")).click();
//		 }
//		catch(NoAlertPresentException e){
//			System.out.println("Alert not present");
//		}
		
		URL();
		
		Workbook wb = WorkbookFactory.create(new FileInputStream(new File("./Myles.xlsx"))); 
        driver.findElement(By.id("hmsignin")).click();
		String mobNo = wb.getSheet("Myles").getRow(1).getCell(1).getStringCellValue();
		System.out.println(mobNo);
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("mobile")));
		driver.findElement(By.id("mobile")).sendKeys(mobNo);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("contact_auto")));
		driver.findElement(By.id("contact_auto")).click();
		Thread.sleep(1000);
        Actions act =new Actions(driver);
		
		try {
			String pass = wb.getSheet("Myles").getRow(1).getCell(2).getStringCellValue();
			WebDriverWait wait1 = new WebDriverWait(driver, 40);
		    wait1.until(ExpectedConditions.presenceOfElementLocated(By.id("userpassword")));
		    driver.findElement(By.id("userpassword")).sendKeys(pass);
			driver.findElement(By.className("sign_in")).click();
		} 
		catch (ElementNotVisibleException e)
		{
			System.out.println("User is not registered");
		}
		driver.navigate().refresh();
		WebElement cityDrpDwn = driver.findElement(By.id("mobilecity"));
		Select selCity = new Select(cityDrpDwn);
		String loc = wb.getSheet("Myles").getRow(1).getCell(3).getStringCellValue();
		selCity.selectByVisibleText(loc);

		driver.findElement(By.id("inputFieldSF1")).click();
		driver.findElement(By.xpath("//span[text()='Next']")).click();
		String pickDate = wb.getSheet("Myles").getRow(1).getCell(4).getStringCellValue();
		driver.findElement(By.xpath("//a[contains(text(),'"+pickDate+"')]")).click();

		WebElement pickDrpDwn = driver.findElement(By.id("inputFieldPT"));
		Select selPickTym = new Select(pickDrpDwn);
		Thread.sleep(2000);
		String pickTime = wb.getSheet("Myles").getRow(1).getCell(5).getStringCellValue();
		Thread.sleep(2000);
		selPickTym.selectByValue(pickTime);
		Thread.sleep(2000);
		driver.findElement(By.id("inputFieldSF2")).click();
		Thread.sleep(2000);
		String dropDate = wb.getSheet("Myles").getRow(1).getCell(6).getStringCellValue();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[contains(text(),'"+dropDate+"')]")).click();
		Thread.sleep(2000);
		WebElement dropDrpDwn = driver.findElement(By.id("inputFieldDT"));
		Thread.sleep(2000);
		Select selDropTym = new Select(dropDrpDwn);
		Thread.sleep(2000);
		String dropTime = wb.getSheet("Myles").getRow(1).getCell(7).getStringCellValue();
		Thread.sleep(2000);
		selDropTym.selectByValue(dropTime);
		Thread.sleep(2000);
		
		String ladakh = wb.getSheet("Myles").getRow(1).getCell(8).getStringCellValue();
		if (ladakh.equalsIgnoreCase("yes")) 
		{
        try
		 {
		 WebElement ladakhEle = driver.findElement(By.xpath("//label[@for='ladakh']"));
		 ladakhEle.click();
		 }
		 catch (ElementNotVisibleException e)
		 {
			 System.out.println("Take to ladakh element is not available");
		 
		 }
		 }
		else
		{
			System.out.println("Elemennt to take my car to Ladakh is not select");
		}
		wait.until(ExpectedConditions.presenceOfElementLocated(By.className("H_Button")));
		driver.findElement(By.className("H_Button")).click();
		Thread.sleep(5000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        List<WebElement> cars = driver.findElements(By.className("HomeP_carBr "));
		if (cars.size() > 0) {
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@id='sel2']")));
			WebElement siteLoc = driver.findElement(By.xpath("//select[@id='sel2']"));
			Select selSiteLoc = new Select(siteLoc);
			selSiteLoc.selectByIndex(1);
			System.out.println("1");
		//    System.out.println("2");
			Thread.sleep(3000);
			
			
			
			
			
		WebElement popup=driver.findElement(By.xpath("//a[@id='nv_js-box-close-button_13093']"));
			
		if(popup.isDisplayed()){
			popup.click();
		}
		else{
			System.out.println("popup not present");
		}
		
			driver.findElement(By.xpath("//select[@id='sel2']//following-sibling::div//button[@id='search_bookthis2']")).click();
			System.out.println("3");
			Thread.sleep(4000);
///////////////////////////////////////////////////////////////////////////////////////////////////			
//			try{  
//			    WebElement popup2=driver.findElement(By.xpath("//a[@id='nv_js-box-close-button_13132']"));
//			    
//			    
//			    if(popup2.isDisplayed()){
//					popup2.click();
					
//					wait.until(ExpectedConditions.presenceOfElementLocated(By.id("proceed_auto")));
//					System.out.println("5");
//					driver.findElement(By.id("proceed_auto")).click();
//				    Thread.sleep(5000);
//				}
//				
//				}
//			  
//				finally{
//					wait.until(ExpectedConditions.presenceOfElementLocated(By.id("proceed_auto")));
//					System.out.println("5");
//					driver.findElement(By.id("proceed_auto")).click();
//				    Thread.sleep(5000);
//				}
			
//////////////////////////////////////////////////////////////////////////////////////////////////////////////			
			
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id("proceed_auto")));
			System.out.println("5");
			driver.findElement(By.id("proceed_auto")).click();
		    Thread.sleep(5000);
		    
  
		    try {
			 List<WebElement> additionalService = driver.findElements(By.className("newAddresBlock"));
			 if (additionalService.size() > 0) 
			{
				 String addServiceGps = wb.getSheet("Myles").getRow(1).getCell(9).getStringCellValue();
				if (driver.findElement(By.xpath("//label[contains(text(),'Gps Navigation')]")).isDisplayed() && addServiceGps.equalsIgnoreCase("yes")) {
					js.executeScript("arguments[0].click();",driver.findElement(By.xpath("//label[contains(text(),'Gps Navigation')]")));
					System.out.println("gps-navigation is available");
				} 
				else
				{
					System.out.println("Gps navigation is unavailable");
				}
				Thread.sleep(3000);
			    String addServiceBaby = wb.getSheet("Myles").getRow(1).getCell(10).getStringCellValue();

				if (driver.findElement(By.xpath("//label[contains(text(),'Baby Seat')]")).isDisplayed() && addServiceBaby.equalsIgnoreCase("yes"))
				{
					js.executeScript("arguments[0].click();",driver.findElement(By.xpath("//label[contains(text(),'Baby Seat')]")));
					System.out.println("Baby seat is available");
				} 
				else 
				{
					System.out.println("BabySeat is unavailable");

				}
				js.executeScript("arguments[0].click();",driver.findElement(By.xpath("//div[@id='PickupDetails']//button[@id='pickupdropupAddr' and text()='Proceed']")));
			}

			else 
			{
				System.out.println("Both Gps navigation and Baby seat are unavailable");
				js.executeScript("arguments[0].click();",driver.findElement(By.xpath("//div[@id='PickupDetails']//button[@id='pickupdropupAddr' and text()='Proceed']")));
			}
            }
		catch (NoSuchElementException e)
		    {
			System.out.println("no gps-navigattion and baby seat available");
		    }
            Thread.sleep(4000);
		    String LDW = wb.getSheet("Myles").getRow(1).getCell(11).getStringCellValue();
		 if (LDW.equalsIgnoreCase("yes")) {
		List<WebElement> dynamicElement = driver.findElements(By.xpath("//label[contains(text(),'Myles Secure')]/../input[@type='checkbox']"));
		 System.out.println("size: " +dynamicElement.size());
			
		if(dynamicElement.size() != 0){
			wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("label.mylesCoverHdn")));
		    WebElement chkBx = driver.findElement(By.cssSelector("label.mylesCoverHdn"));
			//WebElement chkBx = driver.findElement(By.xpath("//div[@id='PaymentDetails']//div[@class='mylesCover']//label"));
			System.out.println("Element is present");
		    act.moveToElement(chkBx).click().build().perform();
		    System.out.println("clicked");
		}

		}
		 else if(LDW.equalsIgnoreCase("no"))
		 {
			 System.out.println("Security deposit pay without LDW");
		 }
			Thread.sleep(2000);

			Thread.sleep(3000);

			js.executeScript("arguments[0].click();",
					driver.findElement(By.xpath("//label[text()='I accept MYLES ']")));
			Thread.sleep(5000);
			// wait.until(ExpectedConditions.presenceOfElementLocated(By.id("credit_card_number")));
			 String CreditDebit =driver.findElement(By.xpath("//a[@href='#CreditDebit']")).getText();
			 System.out.println(CreditDebit);
		     String wallet =driver.findElement(By.xpath("//a[@href='#Wallet']")).getText();
			 System.out.println(wallet);
			 String netBanking =driver.findElement(By.xpath("//a[@href='#NetBanking']")).getText();
			 System.out.println(netBanking);
			 String payment = wb.getSheet("Myles").getRow(1).getCell(12).getStringCellValue();

			 if (CreditDebit.equalsIgnoreCase(payment))
			 {
				WebElement cardNum = driver.findElement(By.id("credit_card_number"));
				Thread.sleep(2000);
			    wait.until(ExpectedConditions.presenceOfElementLocated(By.id("credit_card_number")));
			    cardNum.sendKeys("4242424242424242");
				Thread.sleep(2000);
				driver.findElement(By.id("creditcardname")).sendKeys("test");
				WebElement expMonth = driver.findElement(By.id("credit_card_exp_month"));
				Select sel = new Select(expMonth);
				sel.selectByVisibleText("10");
				WebElement expYear = driver.findElement(By.id("credit_card_exp_year"));
				Select sel1 = new Select(expYear);
				sel1.selectByVisibleText("2020");
				driver.findElement(By.id("credit_security_code")).sendKeys("123");
				driver.findElement(By.id("card_payment")).click();
				Thread.sleep(1000);
				driver.findElement(By.id("OTP")).sendKeys("123123");
				driver.findElement(By.name("proceed")).click();
				
				String bookId = driver.findElement(By.xpath("//p[@class='bookID_T']")).getText();
	////////////takes booking id 		
				String BookingId=bookId.substring(69, 76).trim();
				
				System.out.println(BookingId);
				
				ReadWriteExcel("./Myles.xlsx","Myles-Cancel",2,3,BookingId);
				
		//		ReadWriteExcel("./Myles.xlsx","Myles-Extension",1,3,BookingId);
				
				ReadWriteExcel("./Myles.xlsx","Insta-Open",1,3,BookingId);
				ReadWriteExcel("./Myles.xlsx","Insta-Open",1,4,BookingId);
				ReadWriteExcel("./Myles.xlsx","Insta-Close",1,3,BookingId);
				ReadWriteExcel("./Myles.xlsx","Insta-Extension",1,3,BookingId);
				ReadWriteExcel("./Myles.xlsx","Invoice-Damage",1,3,BookingId);
				ReadWriteExcel("./Myles.xlsx","Swap-Car",1,3,BookingId);
				
				
		//		scan.close();	
			 }
			 else if (wallet.equalsIgnoreCase(payment))
			 {
			   driver.findElement(By.id("paytm")).click();
			   driver.findElement(By.id("w_payment")).click();
			}
			else if (netBanking.equalsIgnoreCase(payment)) 
			 {
			  driver.findElement(By.xpath("//a[@href='#NetBanking']")).click();
			 }
			File srcfile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			File destFile = new File("./Shots/Booking.png");
			FileUtils.copyFile(srcfile, destFile);
			} 
			else
			{
				System.out.println("No cars available");
			}
	        }
	

	

	public void MylesCancelBooking() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, FileNotFoundException, IOException{

//		driver.get("https://qa2.mylescars.com/");
//		Scanner scan = new Scanner(System.in);
//		try{
//			WebDriverWait wait = new WebDriverWait(driver, 60);
//		    wait.until(ExpectedConditions.presenceOfElementLocated(By.id("nv_js-box-close-button_13317")));
//		    driver.findElement(By.xpath("//a[@id='nv_js-box-close-button_13317']")).click();
//		 }
//		catch(NoAlertPresentException e){
//			System.out.println("Alert not present");
//		}
		URL();
		
		Workbook wb = WorkbookFactory.create(new FileInputStream(new File("./Myles.xlsx"))); 
        driver.findElement(By.id("hmsignin")).click();
		String mobNo = wb.getSheet("Myles-Cancel").getRow(2).getCell(1).getStringCellValue();
		driver.findElement(By.id("mobile")).sendKeys(mobNo);
		driver.findElement(By.id("contact_auto")).click();
		Thread.sleep(1000);
		
		try {
			String pass = wb.getSheet("Myles-Cancel").getRow(2).getCell(2).getStringCellValue();
			driver.findElement(By.id("userpassword")).sendKeys(pass);
			driver.findElement(By.className("sign_in")).click();
		} 
		catch (ElementNotVisibleException e)
		{
			System.out.println("User is not registered");            
		}
		Thread.sleep(3000);
		driver.findElement(By.id("headername")).click();
        Thread.sleep(3000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();",driver.findElement(By.cssSelector("li#uRides")));
        String bookngId = wb.getSheet("Myles-Cancel").getRow(2).getCell(3).getStringCellValue();
        String extendBtn = "//span[contains(text(),'( Booking- "+bookngId+" )')]/../../../following-sibling::div//button";
        js.executeScript("arguments[0].click();", driver.findElement(By.xpath(extendBtn)));
		
        List<WebElement> list = driver.findElements(By.xpath("//span[@id='other_"+bookngId+"']//following-sibling::div//li"));
		int priceTxtSize = list.size();
		for (int i = 1; i <= priceTxtSize; i++) 
		 {
			String txt = driver.findElement(By.xpath("(//span[@id='other_"+bookngId+"']//following-sibling::div//li)["+i+"]")).getText();
			System.out.println(txt);
			}
		
		String CancelReason = wb.getSheet("Myles-Cancel").getRow(2).getCell(4).getStringCellValue();
		try {
		js.executeScript("arguments[0].click();",driver.findElement(By.xpath("//input[@value='"+CancelReason+"' and contains(@id,'"+bookngId+"')]")));
		driver.findElement(By.xpath("//button[text()='Cancel' and @id='cbooking_"+bookngId+"']")).click();
		
		WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        String actMsg =alert.getText();
        String expMsg = "The Booking has been successfully cancelled.";
        Assert.assertEquals(actMsg, expMsg);
        System.out.println(actMsg);
        alert.accept();
        }
		catch(NoSuchElementException e)
		{
			System.out.println("Search again with different cancel reason");
		}
		}
	
	

	public void MylesExtension() throws IOException, EncryptedDocumentException, InvalidFormatException, InterruptedException{
//		driver.get("https://qa2.mylescars.com/");
//		Scanner scan = new Scanner(System.in);
//		try{
//			WebDriverWait wait = new WebDriverWait(driver, 60);
//		    wait.until(ExpectedConditions.presenceOfElementLocated(By.id("nv_js-box-close-button_13317")));
//		    driver.findElement(By.xpath("//a[@id='nv_js-box-close-button_13317']")).click();
//		 }
//		catch(NoAlertPresentException e){
//			System.out.println("Alert not present");
//		}
		URL();
		Scanner scan = new Scanner(System.in);
		Workbook wb = WorkbookFactory.create(new FileInputStream(new File("./Myles.xlsx"))); 
        driver.findElement(By.id("hmsignin")).click();
		String mobNo = wb.getSheet("Myles-Extension").getRow(1).getCell(1).getStringCellValue();
		driver.findElement(By.id("mobile")).sendKeys(mobNo);
		driver.findElement(By.id("contact_auto")).click();
		Thread.sleep(1000);
		
		try {
			String pass = wb.getSheet("Myles-Extension").getRow(1).getCell(2).getStringCellValue();
			driver.findElement(By.id("userpassword")).sendKeys(pass);
			driver.findElement(By.className("sign_in")).click();
		} 
		catch (ElementNotVisibleException e)
		{
			System.out.println("User is not registered");            
		}
		Thread.sleep(3000);
		
		driver.findElement(By.id("headername")).click();
		Thread.sleep(4000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();",driver.findElement(By.cssSelector("li#onRides")));
		
		WebDriverWait wait = new WebDriverWait(driver, 40);
//		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[text()='Ongoing Rides']")));
//		driver.findElement(By.cssSelector("li#onRides")).click();
//		Thread.sleep(3000);
		
//		int bookID =(int) wb.getSheet("Myles-Extension").getRow(1).getCell(3).getNumericCellValue();
		
		String BookID =wb.getSheet("Myles-Extension").getRow(1).getCell(3).getStringCellValue();
		int bookID = Integer.parseInt(BookID);
		
		String extendBtn = "//span[contains(text(),'( Booking- "+bookID+" )')]/../../../following-sibling::div//button";
		
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath(extendBtn)));
		//int bookID =(int) wb.getSheet("Myles-Extension").getRow(1).getCell(4).getNumericCellValue();
		
		
		driver.findElement(By.xpath("//input[@id='datepicker_"+bookID+"']")).click();
		//driver.findElement(By.xpath("//input[@id='datepicker_6666691']")).click();
		String month = driver.findElement(By.className("ui-datepicker-month")).getText();
		String exlMonth = wb.getSheet("Myles-Extension").getRow(1).getCell(4).getStringCellValue();
		String exlDate = wb.getSheet("Myles-Extension").getRow(1).getCell(5).getStringCellValue();
		try 
		{
			while(true)
			{
				
			    if (month.equalsIgnoreCase(exlMonth))
			    {
				
				driver.findElement(By.linkText(exlDate)).click();
				//driver.findElement(By.xpath("//div[@class='btnBox']/button[@id='ext_update_but_"+bookID+"']")).click();
				//driver.findElement(By.xpath("//span[text()='Booking id - 6666691']/following-sibling::div//button[contains(@id,'6666691')]")).click();
				js.executeScript("arguments[0].click();", driver.findElement(By.id("ext_update_but_"+bookID+"")));

				break;
				
			    }
				driver.findElement(By.xpath("//span[text()='Next']")).click();
				String nxtMonth = driver.findElement(By.className("ui-datepicker-month")).getText();
				if (nxtMonth.equalsIgnoreCase(exlMonth)) 
				{
					driver.findElement(By.linkText(exlDate)).click();
					js.executeScript("arguments[0].click();", driver.findElement(By.id("ext_update_but_"+bookID+"")));
					//driver.findElement(By.xpath("//div[@class='btnBox']/button[@id='ext_update_but_"+bookID+"']")).click();
					//driver.findElement(By.xpath("//span[text()='Booking id - 6666691']/following-sibling::div//button[contains(@id,'6666691')]")).click();

					break;
					
				}
				
			}
			
		
			
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			System.out.println("error");
		}
		
		Thread.sleep(3000);
		


		
		 ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
		 Thread.sleep(3000);
		    driver.switchTo().window(tabs2.get(1));
		    String currentUrl = driver.getCurrentUrl();
		    System.out.println(currentUrl);
		    String newUrl = currentUrl.replaceAll("https://www", "https://qa2");
			System.out.println(newUrl);
			driver.navigate().to(newUrl);
			Thread.sleep(3000);
			 String CreditDebit =driver.findElement(By.xpath("//a[@href='#CreditDebit']")).getText();
			 System.out.println(CreditDebit);
		     String wallet =driver.findElement(By.xpath("//a[@href='#Wallet']")).getText();
			 System.out.println(wallet);
			 String netBanking =driver.findElement(By.xpath("//a[@href='#NetBanking']")).getText();
			 System.out.println(netBanking);
			 String payment = wb.getSheet("Myles-Extension").getRow(1).getCell(7).getStringCellValue();
			 Thread.sleep(2000);

			 if (CreditDebit.equalsIgnoreCase(payment))
			 {
				WebElement cardNum = driver.findElement(By.id("credit_card_number"));
				Thread.sleep(2000);
			    wait.until(ExpectedConditions.presenceOfElementLocated(By.id("credit_card_number")));
			    cardNum.sendKeys("4242424242424242");
				Thread.sleep(2000);
				driver.findElement(By.id("creditcardname")).sendKeys("test");
				WebElement expMonth = driver.findElement(By.id("credit_card_exp_month"));
				Select sel = new Select(expMonth);
				sel.selectByVisibleText("10");
				WebElement expYear = driver.findElement(By.id("credit_card_exp_year"));
				Select sel1 = new Select(expYear);
				sel1.selectByVisibleText("2020");
				driver.findElement(By.id("credit_security_code")).sendKeys("123");
				driver.findElement(By.id("card_payment")).click();
				Thread.sleep(1000);
				driver.findElement(By.id("OTP")).sendKeys("123123");
				driver.findElement(By.name("proceed")).click();
				scan.close();	
					
				
			}
			 else if (wallet.equalsIgnoreCase(payment))
			 {
			   driver.findElement(By.id("paytm")).click();
			   driver.findElement(By.id("w_payment")).click();
				
			 }
			
			 else if (netBanking.equalsIgnoreCase(payment)) 
			 {
				 driver.findElement(By.xpath("//a[@href='#NetBanking']")).click();
				
			}
			File srcfile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			File destFile = new File("./Shots/Booking.png");
			FileUtils.copyFile(srcfile, destFile);
			
			
		}
	
	
	
		


	public void MylesBooking1() throws EncryptedDocumentException, InvalidFormatException, FileNotFoundException, IOException, InterruptedException{

//		driver.get("https://qa2.mylescars.com/");
//		Scanner scan = new Scanner(System.in);
//		try{
//			WebDriverWait wait = new WebDriverWait(driver, 60);
//		    wait.until(ExpectedConditions.presenceOfElementLocated(By.id("nv_js-box-close-button_13317")));
//		    driver.findElement(By.xpath("//a[@id='nv_js-box-close-button_13317']")).click();
//		 }
//		catch(NoAlertPresentException e){
//			System.out.println("Alert not present");
//		}
		URL();
		
		Workbook wb = WorkbookFactory.create(new FileInputStream(new File("./Myles.xlsx"))); 
        driver.findElement(By.id("hmsignin")).click();
		String mobNo = wb.getSheet("Myles").getRow(1).getCell(1).getStringCellValue();
		System.out.println(mobNo);
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("mobile")));
		driver.findElement(By.id("mobile")).sendKeys(mobNo);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("contact_auto")));
		driver.findElement(By.id("contact_auto")).click();
		Thread.sleep(1000);
        Actions act =new Actions(driver);
		
		try {
			String pass = wb.getSheet("Myles").getRow(1).getCell(2).getStringCellValue();
			WebDriverWait wait1 = new WebDriverWait(driver, 40);
		    wait1.until(ExpectedConditions.presenceOfElementLocated(By.id("userpassword")));
		    driver.findElement(By.id("userpassword")).sendKeys(pass);
			driver.findElement(By.className("sign_in")).click();
		} 
		catch (ElementNotVisibleException e)
		{
			System.out.println("User is not registered");
		}
		driver.navigate().refresh();
		WebElement cityDrpDwn = driver.findElement(By.id("mobilecity"));
		Select selCity = new Select(cityDrpDwn);
		String loc = wb.getSheet("Myles").getRow(1).getCell(3).getStringCellValue();
		selCity.selectByVisibleText(loc);

		driver.findElement(By.id("inputFieldSF1")).click();
		driver.findElement(By.xpath("//span[text()='Next']")).click();
		String pickDate = wb.getSheet("Myles").getRow(1).getCell(4).getStringCellValue();
		driver.findElement(By.xpath("//a[contains(text(),'"+pickDate+"')]")).click();

		WebElement pickDrpDwn = driver.findElement(By.id("inputFieldPT"));
		Select selPickTym = new Select(pickDrpDwn);
		Thread.sleep(2000);
		String pickTime = wb.getSheet("Myles").getRow(1).getCell(5).getStringCellValue();
		Thread.sleep(2000);
		selPickTym.selectByValue(pickTime);
		Thread.sleep(2000);
		driver.findElement(By.id("inputFieldSF2")).click();
		Thread.sleep(2000);
		String dropDate = wb.getSheet("Myles").getRow(1).getCell(6).getStringCellValue();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[contains(text(),'"+dropDate+"')]")).click();
		Thread.sleep(2000);
		WebElement dropDrpDwn = driver.findElement(By.id("inputFieldDT"));
		Thread.sleep(2000);
		Select selDropTym = new Select(dropDrpDwn);
		Thread.sleep(2000);
		String dropTime = wb.getSheet("Myles").getRow(1).getCell(7).getStringCellValue();
		Thread.sleep(2000);
		selDropTym.selectByValue(dropTime);
		Thread.sleep(2000);
		
		String ladakh = wb.getSheet("Myles").getRow(1).getCell(8).getStringCellValue();
		if (ladakh.equalsIgnoreCase("yes")) 
		{
        try
		 {
		 WebElement ladakhEle = driver.findElement(By.xpath("//label[@for='ladakh']"));
		 ladakhEle.click();
		 }
		 catch (ElementNotVisibleException e)
		 {
			 System.out.println("Take to ladakh element is not available");
		 
		 }
		 }
		else
		{
			System.out.println("Elemennt to take my car to Ladakh is not select");
		}
		wait.until(ExpectedConditions.presenceOfElementLocated(By.className("H_Button")));
		driver.findElement(By.className("H_Button")).click();
		Thread.sleep(5000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        List<WebElement> cars = driver.findElements(By.className("HomeP_carBr "));
		if (cars.size() > 0) {
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@id='sel2']")));
			WebElement siteLoc = driver.findElement(By.xpath("//select[@id='sel2']"));
			Select selSiteLoc = new Select(siteLoc);
			selSiteLoc.selectByIndex(1);
			System.out.println("1");
		//    System.out.println("2");
			Thread.sleep(3000);
			
			
			
			
			
		WebElement popup=driver.findElement(By.xpath("//a[@id='nv_js-box-close-button_13093']"));
			
		if(popup.isDisplayed()){
			popup.click();
		}
		else{
			System.out.println("popup not present");
		}
		
			driver.findElement(By.xpath("//select[@id='sel2']//following-sibling::div//button[@id='search_bookthis2']")).click();
			System.out.println("3");
			Thread.sleep(3000);
			
///////////////////////////////////////////////////////////////////////////////////////////////////			
			try{  
		
			    WebElement popup2=driver.findElement(By.xpath("//a[@id='nv_js-box-close-button_13132']"));
		//	    WebElement popup2 =driver.findElement(By.xpath("//a[starts-with(@id,�nv_js-box-close-button�)]"));
			    
			    if(popup2.isDisplayed()){
					popup2.click();
					
//					wait.until(ExpectedConditions.presenceOfElementLocated(By.id("proceed_auto")));
//					System.out.println("5");
//					driver.findElement(By.id("proceed_auto")).click();
//					Thread.sleep(5000);
				}
				
				}
			  
				catch(NoSuchElementException e){
					wait.until(ExpectedConditions.presenceOfElementLocated(By.id("proceed_auto")));
					System.out.println("5");
					driver.findElement(By.id("proceed_auto")).click();
				    Thread.sleep(5000);
				    
				    try {
						 List<WebElement> additionalService = driver.findElements(By.className("newAddresBlock"));
						 if (additionalService.size() > 0) 
						{
							 String addServiceGps = wb.getSheet("Myles").getRow(1).getCell(9).getStringCellValue();
							if (driver.findElement(By.xpath("//label[contains(text(),'Gps Navigation')]")).isDisplayed() && addServiceGps.equalsIgnoreCase("yes")) {
								js.executeScript("arguments[0].click();",driver.findElement(By.xpath("//label[contains(text(),'Gps Navigation')]")));
								System.out.println("gps-navigation is available");
							} 
							else
							{
								System.out.println("Gps navigation is unavailable");
							}
							Thread.sleep(3000);
						    String addServiceBaby = wb.getSheet("Myles").getRow(1).getCell(10).getStringCellValue();

							if (driver.findElement(By.xpath("//label[contains(text(),'Baby Seat')]")).isDisplayed() && addServiceBaby.equalsIgnoreCase("yes"))
							{
								js.executeScript("arguments[0].click();",driver.findElement(By.xpath("//label[contains(text(),'Baby Seat')]")));
								System.out.println("Baby seat is available");
							} 
							else 
							{
								System.out.println("BabySeat is unavailable");

							}
							js.executeScript("arguments[0].click();",driver.findElement(By.xpath("//div[@id='PickupDetails']//button[@id='pickupdropupAddr' and text()='Proceed']")));
						}

						else 
						{
							System.out.println("Both Gps navigation and Baby seat are unavailable");
							js.executeScript("arguments[0].click();",driver.findElement(By.xpath("//div[@id='PickupDetails']//button[@id='pickupdropupAddr' and text()='Proceed']")));
						}
			            }
					catch (NoSuchElementException a)
					    {
						System.out.println("no gps-navigattion and baby seat available");
					    }
			            Thread.sleep(4000);
					    String LDW = wb.getSheet("Myles").getRow(1).getCell(11).getStringCellValue();
					 if (LDW.equalsIgnoreCase("yes")) {
					List<WebElement> dynamicElement = driver.findElements(By.xpath("//label[contains(text(),'Myles Secure')]/../input[@type='checkbox']"));
					 System.out.println("size: " +dynamicElement.size());
						
					if(dynamicElement.size() != 0){
						wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("label.mylesCoverHdn")));
					    WebElement chkBx = driver.findElement(By.cssSelector("label.mylesCoverHdn"));
						//WebElement chkBx = driver.findElement(By.xpath("//div[@id='PaymentDetails']//div[@class='mylesCover']//label"));
						System.out.println("Element is present");
					    act.moveToElement(chkBx).click().build().perform();
					    System.out.println("clicked");
					}

					}
					 else if(LDW.equalsIgnoreCase("no"))
					 {
						 System.out.println("Security deposit pay without LDW");
					 }
						Thread.sleep(2000);

						Thread.sleep(3000);

						js.executeScript("arguments[0].click();",
								driver.findElement(By.xpath("//label[text()='I accept MYLES ']")));
						Thread.sleep(5000);
						// wait.until(ExpectedConditions.presenceOfElementLocated(By.id("credit_card_number")));
						 String CreditDebit =driver.findElement(By.xpath("//a[@href='#CreditDebit']")).getText();
						 System.out.println(CreditDebit);
					     String wallet =driver.findElement(By.xpath("//a[@href='#Wallet']")).getText();
						 System.out.println(wallet);
						 String netBanking =driver.findElement(By.xpath("//a[@href='#NetBanking']")).getText();
						 System.out.println(netBanking);
						 String payment = wb.getSheet("Myles").getRow(1).getCell(12).getStringCellValue();

						 if (CreditDebit.equalsIgnoreCase(payment))
						 {
							WebElement cardNum = driver.findElement(By.id("credit_card_number"));
							Thread.sleep(2000);
						    wait.until(ExpectedConditions.presenceOfElementLocated(By.id("credit_card_number")));
						    cardNum.sendKeys("4242424242424242");
							Thread.sleep(2000);
							driver.findElement(By.id("creditcardname")).sendKeys("test");
							WebElement expMonth = driver.findElement(By.id("credit_card_exp_month"));
							Select sel = new Select(expMonth);
							sel.selectByVisibleText("10");
							WebElement expYear = driver.findElement(By.id("credit_card_exp_year"));
							Select sel1 = new Select(expYear);
							sel1.selectByVisibleText("2020");
							driver.findElement(By.id("credit_security_code")).sendKeys("123");
							driver.findElement(By.id("card_payment")).click();
							Thread.sleep(1000);
							driver.findElement(By.id("OTP")).sendKeys("123123");
							driver.findElement(By.name("proceed")).click();
							
							String bookId = driver.findElement(By.xpath("//p[@class='bookID_T']")).getText();
				////////////takes booking id 		
							String BookingId=bookId.substring(69, 76).trim();
							
							System.out.println(BookingId);
							
							ReadWriteExcel("./Myles.xlsx","Myles-Cancel",2,3,BookingId);
							
					//		ReadWriteExcel("./Myles.xlsx","Myles-Extension",1,3,BookingId);
							
							ReadWriteExcel("./Myles.xlsx","Insta-Open",1,3,BookingId);
							ReadWriteExcel("./Myles.xlsx","Insta-Open",1,4,BookingId);
							ReadWriteExcel("./Myles.xlsx","Insta-Close",1,3,BookingId);
							ReadWriteExcel("./Myles.xlsx","Insta-Extension",1,3,BookingId);
							ReadWriteExcel("./Myles.xlsx","Invoice-Damage",1,3,BookingId);
							ReadWriteExcel("./Myles.xlsx","Swap-Car",1,3,BookingId);
							ReadWriteExcel("./Myles.xlsx","Myles-Extension",1,3,BookingId);
							
							
					//		scan.close();	
						 }
						 else if (wallet.equalsIgnoreCase(payment))
						 {
						   driver.findElement(By.id("paytm")).click();
						   driver.findElement(By.id("w_payment")).click();
						}
						else if (netBanking.equalsIgnoreCase(payment)) 
						 {
						  driver.findElement(By.xpath("//a[@href='#NetBanking']")).click();
						 }
						File srcfile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
						File destFile = new File("./Shots/Booking.png");
						FileUtils.copyFile(srcfile, destFile);
						}
			
		
//						else{
//						
//							System.out.println("No cars available");
//						}
				         
				    
				    
				    
				    
				    
				    
				
			
//////////////////////////////////////////////////////////////////////////////////////////////////////////////			
			
//			wait.until(ExpectedConditions.presenceOfElementLocated(By.id("proceed_auto")));
//			System.out.println("5");
//			driver.findElement(By.id("proceed_auto")).click();
//		    Thread.sleep(5000);
		    
  
		    try {
			 List<WebElement> additionalService = driver.findElements(By.className("newAddresBlock"));
			 if (additionalService.size() > 0) 
			{
				 String addServiceGps = wb.getSheet("Myles").getRow(1).getCell(9).getStringCellValue();
				if (driver.findElement(By.xpath("//label[contains(text(),'Gps Navigation')]")).isDisplayed() && addServiceGps.equalsIgnoreCase("yes")) {
					js.executeScript("arguments[0].click();",driver.findElement(By.xpath("//label[contains(text(),'Gps Navigation')]")));
					System.out.println("gps-navigation is available");
				} 
				else
				{
					System.out.println("Gps navigation is unavailable");
				}
				Thread.sleep(3000);
			    String addServiceBaby = wb.getSheet("Myles").getRow(1).getCell(10).getStringCellValue();

				if (driver.findElement(By.xpath("//label[contains(text(),'Baby Seat')]")).isDisplayed() && addServiceBaby.equalsIgnoreCase("yes"))
				{
					js.executeScript("arguments[0].click();",driver.findElement(By.xpath("//label[contains(text(),'Baby Seat')]")));
					System.out.println("Baby seat is available");
				} 
				else 
				{
					System.out.println("BabySeat is unavailable");

				}
				js.executeScript("arguments[0].click();",driver.findElement(By.xpath("//div[@id='PickupDetails']//button[@id='pickupdropupAddr' and text()='Proceed']")));
			}

			else 
			{
				System.out.println("Both Gps navigation and Baby seat are unavailable");
				js.executeScript("arguments[0].click();",driver.findElement(By.xpath("//div[@id='PickupDetails']//button[@id='pickupdropupAddr' and text()='Proceed']")));
			}
            }
		catch (NoSuchElementException e)
		    {
			System.out.println("no gps-navigattion and baby seat available");
		    }
            Thread.sleep(4000);
		    String LDW = wb.getSheet("Myles").getRow(1).getCell(11).getStringCellValue();
		 if (LDW.equalsIgnoreCase("yes")) {
		List<WebElement> dynamicElement = driver.findElements(By.xpath("//label[contains(text(),'Myles Secure')]/../input[@type='checkbox']"));
		 System.out.println("size: " +dynamicElement.size());
			
		if(dynamicElement.size() != 0){
			wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("label.mylesCoverHdn")));
		    WebElement chkBx = driver.findElement(By.cssSelector("label.mylesCoverHdn"));
			//WebElement chkBx = driver.findElement(By.xpath("//div[@id='PaymentDetails']//div[@class='mylesCover']//label"));
			System.out.println("Element is present");
		    act.moveToElement(chkBx).click().build().perform();
		    System.out.println("clicked");
		}

		}
		 else if(LDW.equalsIgnoreCase("no"))
		 {
			 System.out.println("Security deposit pay without LDW");
		 }
			Thread.sleep(2000);

			Thread.sleep(3000);

			js.executeScript("arguments[0].click();",
					driver.findElement(By.xpath("//label[text()='I accept MYLES ']")));
			Thread.sleep(5000);
			// wait.until(ExpectedConditions.presenceOfElementLocated(By.id("credit_card_number")));
			 String CreditDebit =driver.findElement(By.xpath("//a[@href='#CreditDebit']")).getText();
			 System.out.println(CreditDebit);
		     String wallet =driver.findElement(By.xpath("//a[@href='#Wallet']")).getText();
			 System.out.println(wallet);
			 String netBanking =driver.findElement(By.xpath("//a[@href='#NetBanking']")).getText();
			 System.out.println(netBanking);
			 String payment = wb.getSheet("Myles").getRow(1).getCell(12).getStringCellValue();

			 if (CreditDebit.equalsIgnoreCase(payment))
			 {
				WebElement cardNum = driver.findElement(By.id("credit_card_number"));
				Thread.sleep(2000);
			    wait.until(ExpectedConditions.presenceOfElementLocated(By.id("credit_card_number")));
			    cardNum.sendKeys("4242424242424242");
				Thread.sleep(2000);
				driver.findElement(By.id("creditcardname")).sendKeys("test");
				WebElement expMonth = driver.findElement(By.id("credit_card_exp_month"));
				Select sel = new Select(expMonth);
				sel.selectByVisibleText("10");
				WebElement expYear = driver.findElement(By.id("credit_card_exp_year"));
				Select sel1 = new Select(expYear);
				sel1.selectByVisibleText("2020");
				driver.findElement(By.id("credit_security_code")).sendKeys("123");
				driver.findElement(By.id("card_payment")).click();
				Thread.sleep(1000);
				driver.findElement(By.id("OTP")).sendKeys("123123");
				driver.findElement(By.name("proceed")).click();
				
				String bookId = driver.findElement(By.xpath("//p[@class='bookID_T']")).getText();
	////////////takes booking id 		
				String BookingId=bookId.substring(69, 76).trim();
				
				System.out.println(BookingId);
				
				System.out.println("jay");
				
				Thread.sleep(2000);
				
				ReadWriteExcel("./Myles.xlsx","Myles-Cancel",2,3,BookingId);
				
				ReadWriteExcel("./Myles.xlsx","Myles-Extension",1,3,BookingId);
				
				ReadWriteExcel("./Myles.xlsx","Insta-Open",1,3,BookingId);
				ReadWriteExcel("./Myles.xlsx","Insta-Open",1,4,BookingId);
				ReadWriteExcel("./Myles.xlsx","Insta-Close",1,3,BookingId);
				ReadWriteExcel("./Myles.xlsx","Insta-Extension",1,3,BookingId);
				ReadWriteExcel("./Myles.xlsx","Invoice-Damage",1,3,BookingId);
				ReadWriteExcel("./Myles.xlsx","Swap-Car",1,3,BookingId);
				
				System.out.println("end");
		//		scan.close();	
			 }
			 else if (wallet.equalsIgnoreCase(payment))
			 {
			   driver.findElement(By.id("paytm")).click();
			   driver.findElement(By.id("w_payment")).click();
			}
			else if (netBanking.equalsIgnoreCase(payment)) 
			 {
			  driver.findElement(By.xpath("//a[@href='#NetBanking']")).click();
			 }
			File srcfile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			File destFile = new File("./Shots/Booking.png");
			FileUtils.copyFile(srcfile, destFile);
			 
//			else{
//			
//				System.out.println("No cars available");
//			}
	        }


            }
	
	
	
	
	
	
	
	
	
	
///////////////////testing purpose	
	
	public void MylesExtension1() throws IOException, EncryptedDocumentException, InvalidFormatException, InterruptedException{

//		driver.get("https://qa2.mylescars.com/");
//		Scanner scan = new Scanner(System.in);
//		try{
//			WebDriverWait wait = new WebDriverWait(driver, 60);
//		    wait.until(ExpectedConditions.presenceOfElementLocated(By.id("nv_js-box-close-button_13317")));
//		    driver.findElement(By.xpath("//a[@id='nv_js-box-close-button_13317']")).click();
//		 }
//		catch(NoAlertPresentException e){
//			System.out.println("Alert not present");
//		}
		
		URL();
		Scanner scan = new Scanner(System.in);
		Workbook wb = WorkbookFactory.create(new FileInputStream(new File("./Myles.xlsx"))); 

		driver.findElement(By.id("hmsignin")).click();
		String mobNo = wb.getSheet("Myles-Extension").getRow(1).getCell(1).getStringCellValue();
		driver.findElement(By.id("mobile")).sendKeys(mobNo);
		driver.findElement(By.id("contact_auto")).click();
		Thread.sleep(1000);
		
		try {
			String pass = wb.getSheet("Myles-Extension").getRow(1).getCell(2).getStringCellValue();
			driver.findElement(By.id("userpassword")).sendKeys(pass);
			driver.findElement(By.className("sign_in")).click();
		} 
		catch (ElementNotVisibleException e)
		{
			System.out.println("User is not registered");            
		}
		Thread.sleep(3000);
		
		driver.findElement(By.id("headername")).click();
		Thread.sleep(4000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();",driver.findElement(By.cssSelector("li#onRides")));
		
		WebDriverWait wait = new WebDriverWait(driver, 40);
//		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[text()='Ongoing Rides']")));
//		driver.findElement(By.cssSelector("li#onRides")).click();
//		Thread.sleep(3000);
		
//		int bookID =(int) wb.getSheet("Myles-Extension").getRow(1).getCell(3).getNumericCellValue();
		
		String BookID =wb.getSheet("Myles-Extension").getRow(1).getCell(3).getStringCellValue();
		int bookID = Integer.parseInt(BookID);
		
		String extendBtn = "//span[contains(text(),'( Booking- "+bookID+" )')]/../../../following-sibling::div//button";
		
		js.executeScript("arguments[0].click();", driver.findElement(By.xpath(extendBtn)));
		//int bookID =(int) wb.getSheet("Myles-Extension").getRow(1).getCell(4).getNumericCellValue();
		
		
		driver.findElement(By.xpath("//input[@id='datepicker_"+bookID+"']")).click();
		//driver.findElement(By.xpath("//input[@id='datepicker_6666691']")).click();
		String month = driver.findElement(By.className("ui-datepicker-month")).getText();
		String exlMonth = wb.getSheet("Myles-Extension").getRow(1).getCell(4).getStringCellValue();
		String exlDate = wb.getSheet("Myles-Extension").getRow(1).getCell(5).getStringCellValue();
		try 
		{
			while(true)
			{
				
			    if (month.equalsIgnoreCase(exlMonth))
			    {
				
				driver.findElement(By.linkText(exlDate)).click();
				
				
			try{
		        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='cal_error_ext_"+bookID+"']/span")));
				
				if(driver.findElement(By.xpath("//div[@id='cal_error_ext_"+bookID+"']/span"))!= null){
				Thread.sleep(2000);
				String acterror	=driver.findElement(By.xpath("//div[@id='cal_error_ext_"+bookID+"']/span")).getText();
				String experror="Sorry! This car is blocked for this period, kindly contact the customer care to extend your booking";
				
				System.out.println(acterror);
				
				if(acterror.equalsIgnoreCase(experror)){
			      js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//div[@id='cal_error_ext_"+bookID+"']/div[3]/button")));
		
				}
				
				}
			}
				
				
			catch(NoSuchElementException e){
               js.executeScript("arguments[0].click();", driver.findElement(By.id("ext_update_but_"+bookID+"")));
                break;
				}
			    	}
				driver.findElement(By.xpath("//span[text()='Next']")).click();
				String nxtMonth = driver.findElement(By.className("ui-datepicker-month")).getText();
				if (nxtMonth.equalsIgnoreCase(exlMonth)) 
				{
					driver.findElement(By.linkText(exlDate)).click();
					
					
					
					try{
						wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='cal_error_ext_"+bookID+"']/span")));
						
						if(driver.findElement(By.xpath("//div[@id='cal_error_ext_"+bookID+"']/span"))!= null){
							String acterror	=driver.findElement(By.xpath("//div[@id='cal_error_ext_"+bookID+"']/span")).getText();
			                String experror="Sorry! This car is blocked for this period, kindly contact the customer care to extend your booking";
						System.out.println(acterror);
						if(acterror.equalsIgnoreCase(experror)){
					
							
							js.executeScript("arguments[0].click();", driver.findElement(By.xpath("//div[@id='cal_error_ext_"+bookID+"']/div[3]/button")));
						}
						
						}
					}	
					
				catch(NoSuchElementException e){
	//		   else{	
					js.executeScript("arguments[0].click();", driver.findElement(By.id("ext_update_but_"+bookID+"")));
					//driver.findElement(By.xpath("//div[@class='btnBox']/button[@id='ext_update_but_"+bookID+"']")).click();
					//driver.findElement(By.xpath("//span[text()='Booking id - 6666691']/following-sibling::div//button[contains(@id,'6666691')]")).click();

					break;
					
				}
					}
				
				
				
			}
			
		
			
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			System.out.println("error");
		}
		
		Thread.sleep(3000);
		


		
		 ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
		 Thread.sleep(3000);
		    driver.switchTo().window(tabs2.get(1));
		    String currentUrl = driver.getCurrentUrl();
		    System.out.println(currentUrl);
		    String newUrl = currentUrl.replaceAll("https://www", "https://qa2");
			System.out.println(newUrl);
			driver.navigate().to(newUrl);
			Thread.sleep(3000);
			 String CreditDebit =driver.findElement(By.xpath("//a[@href='#CreditDebit']")).getText();
			 System.out.println(CreditDebit);
		     String wallet =driver.findElement(By.xpath("//a[@href='#Wallet']")).getText();
			 System.out.println(wallet);
			 String netBanking =driver.findElement(By.xpath("//a[@href='#NetBanking']")).getText();
			 System.out.println(netBanking);
			 String payment = wb.getSheet("Myles-Extension").getRow(1).getCell(7).getStringCellValue();
			 Thread.sleep(2000);

			 if (CreditDebit.equalsIgnoreCase(payment))
			 {
				WebElement cardNum = driver.findElement(By.id("credit_card_number"));
				Thread.sleep(2000);
			    wait.until(ExpectedConditions.presenceOfElementLocated(By.id("credit_card_number")));
			    cardNum.sendKeys("4242424242424242");
				Thread.sleep(2000);
				driver.findElement(By.id("creditcardname")).sendKeys("test");
				WebElement expMonth = driver.findElement(By.id("credit_card_exp_month"));
				Select sel = new Select(expMonth);
				sel.selectByVisibleText("10");
				WebElement expYear = driver.findElement(By.id("credit_card_exp_year"));
				Select sel1 = new Select(expYear);
				sel1.selectByVisibleText("2020");
				driver.findElement(By.id("credit_security_code")).sendKeys("123");
				driver.findElement(By.id("card_payment")).click();
				Thread.sleep(1000);
				driver.findElement(By.id("OTP")).sendKeys("123123");
				driver.findElement(By.name("proceed")).click();
				scan.close();	
					
				
			}
			 else if (wallet.equalsIgnoreCase(payment))
			 {
			   driver.findElement(By.id("paytm")).click();
			   driver.findElement(By.id("w_payment")).click();
				
			 }
			
			 else if (netBanking.equalsIgnoreCase(payment)) 
			 {
				 driver.findElement(By.xpath("//a[@href='#NetBanking']")).click();
				
			}
			File srcfile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			File destFile = new File("./Shots/Booking.png");
			FileUtils.copyFile(srcfile, destFile);
			
			
		}
	
	
	
	
	
/////////////////////////////////////////////////////////////////////////////	
	
	public void MylesBooking3() throws EncryptedDocumentException, InvalidFormatException, FileNotFoundException, IOException, InterruptedException{

//		driver.get("https://qa2.mylescars.com/");
//		Scanner scan = new Scanner(System.in);
//		
//		
//		if(driver.findElement(By.xpath("//a[@id='nv_js-box-close-button_13317']")).isDisplayed()){
//			
//		try{
//			WebDriverWait wait = new WebDriverWait(driver, 60);
//		    wait.until(ExpectedConditions.presenceOfElementLocated(By.id("nv_js-box-close-button_13317")));
//		    driver.findElement(By.xpath("//a[@id='nv_js-box-close-button_13317']")).click();
//		 }
//		catch(NoAlertPresentException e){
//			System.out.println("Alert not present");
//		}
//		}
//		else{
//			System.out.println("alert not present");
//		}
		
		URL();
		Workbook wb = WorkbookFactory.create(new FileInputStream(new File("./Myles.xlsx"))); 
        driver.findElement(By.id("hmsignin")).click();
		String mobNo = wb.getSheet("Myles").getRow(1).getCell(1).getStringCellValue();
		System.out.println(mobNo);
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("mobile")));
		driver.findElement(By.id("mobile")).sendKeys(mobNo);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("contact_auto")));
		driver.findElement(By.id("contact_auto")).click();
		Thread.sleep(1000);
        Actions act =new Actions(driver);
		
		try {
			String pass = wb.getSheet("Myles").getRow(1).getCell(2).getStringCellValue();
			WebDriverWait wait1 = new WebDriverWait(driver, 40);
		    wait1.until(ExpectedConditions.presenceOfElementLocated(By.id("userpassword")));
		    driver.findElement(By.id("userpassword")).sendKeys(pass);
			driver.findElement(By.className("sign_in")).click();
		} 
		catch (ElementNotVisibleException e)
		{
			System.out.println("User is not registered");
		}
		driver.navigate().refresh();
		WebElement cityDrpDwn = driver.findElement(By.id("mobilecity"));
		Select selCity = new Select(cityDrpDwn);
		String loc = wb.getSheet("Myles").getRow(1).getCell(3).getStringCellValue();
		selCity.selectByVisibleText(loc);

		driver.findElement(By.id("inputFieldSF1")).click();
		driver.findElement(By.xpath("//span[text()='Next']")).click();
		String pickDate = wb.getSheet("Myles").getRow(1).getCell(4).getStringCellValue();
		driver.findElement(By.xpath("//a[contains(text(),'"+pickDate+"')]")).click();

		WebElement pickDrpDwn = driver.findElement(By.id("inputFieldPT"));
		Select selPickTym = new Select(pickDrpDwn);
		Thread.sleep(2000);
		String pickTime = wb.getSheet("Myles").getRow(1).getCell(5).getStringCellValue();
		Thread.sleep(2000);
		selPickTym.selectByValue(pickTime);
		Thread.sleep(2000);
		driver.findElement(By.id("inputFieldSF2")).click();
		Thread.sleep(2000);
		String dropDate = wb.getSheet("Myles").getRow(1).getCell(6).getStringCellValue();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[contains(text(),'"+dropDate+"')]")).click();
		Thread.sleep(2000);
		WebElement dropDrpDwn = driver.findElement(By.id("inputFieldDT"));
		Thread.sleep(2000);
		Select selDropTym = new Select(dropDrpDwn);
		Thread.sleep(2000);
		String dropTime = wb.getSheet("Myles").getRow(1).getCell(7).getStringCellValue();
		Thread.sleep(2000);
		selDropTym.selectByValue(dropTime);
		Thread.sleep(2000);
		
		String ladakh = wb.getSheet("Myles").getRow(1).getCell(8).getStringCellValue();
		if (ladakh.equalsIgnoreCase("yes")) 
		{
        try
		 {
		 WebElement ladakhEle = driver.findElement(By.xpath("//label[@for='ladakh']"));
		 ladakhEle.click();
		 }
		 catch (ElementNotVisibleException e)
		 {
			 System.out.println("Take to ladakh element is not available");
		 
		 }
		 }
		else
		{
			System.out.println("Elemennt to take my car to Ladakh is not select");
		}
		wait.until(ExpectedConditions.presenceOfElementLocated(By.className("H_Button")));
		driver.findElement(By.className("H_Button")).click();
		Thread.sleep(5000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        List<WebElement> cars = driver.findElements(By.className("HomeP_carBr "));
		if (cars.size() > 0) {
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@id='sel2']")));
			WebElement siteLoc = driver.findElement(By.xpath("//select[@id='sel2']"));
			Select selSiteLoc = new Select(siteLoc);
			selSiteLoc.selectByIndex(1);
			System.out.println("1");
		//    System.out.println("2");
			Thread.sleep(3000);
			
			WebElement popup=driver.findElement(By.xpath("//a[@id='nv_js-box-close-button_13093']"));
			if(popup.isDisplayed()){
			popup.click();
		}
		else{
			System.out.println("popup not present");
		}
		
			driver.findElement(By.xpath("//select[@id='sel2']//following-sibling::div//button[@id='search_bookthis2']")).click();
			System.out.println("3");
			Thread.sleep(3000);
			
///////////////////////////////////////////////////////////////////////////////////////////////////			
			try{  
		
	//		    WebElement popup2=driver.findElement(By.xpath("//a[@id='nv_js-box-close-button_13132']"));
		    
			    if(driver.findElement(By.xpath("//a[@id='nv_js-box-close-button_13132']")).isDisplayed()){
					driver.findElement(By.xpath("//a[@id='nv_js-box-close-button_13132']")).click();
					
					
					wait.until(ExpectedConditions.presenceOfElementLocated(By.id("proceed_auto")));
					System.out.println("5");
					driver.findElement(By.id("proceed_auto")).click();
				    Thread.sleep(5000);
				    
				    try {
						 List<WebElement> additionalService = driver.findElements(By.className("newAddresBlock"));
						 if (additionalService.size() > 0) 
						{
							 String addServiceGps = wb.getSheet("Myles").getRow(1).getCell(9).getStringCellValue();
							if (driver.findElement(By.xpath("//label[contains(text(),'Gps Navigation')]")).isDisplayed() && addServiceGps.equalsIgnoreCase("yes")) {
								js.executeScript("arguments[0].click();",driver.findElement(By.xpath("//label[contains(text(),'Gps Navigation')]")));
								System.out.println("gps-navigation is available");
							} 
							else
							{
								System.out.println("Gps navigation is unavailable");
							}
							Thread.sleep(3000);
						    String addServiceBaby = wb.getSheet("Myles").getRow(1).getCell(10).getStringCellValue();

							if (driver.findElement(By.xpath("//label[contains(text(),'Baby Seat')]")).isDisplayed() && addServiceBaby.equalsIgnoreCase("yes"))
							{
								js.executeScript("arguments[0].click();",driver.findElement(By.xpath("//label[contains(text(),'Baby Seat')]")));
								System.out.println("Baby seat is available");
							} 
							else 
							{
								System.out.println("BabySeat is unavailable");

							}
							js.executeScript("arguments[0].click();",driver.findElement(By.xpath("//div[@id='PickupDetails']//button[@id='pickupdropupAddr' and text()='Proceed']")));
						}

						else 
						{
							System.out.println("Both Gps navigation and Baby seat are unavailable");
							js.executeScript("arguments[0].click();",driver.findElement(By.xpath("//div[@id='PickupDetails']//button[@id='pickupdropupAddr' and text()='Proceed']")));
						}
			            }
					catch (NoSuchElementException a)
					    {
						System.out.println("no gps-navigattion and baby seat available");
					    }
			            Thread.sleep(4000);
					    String LDW = wb.getSheet("Myles").getRow(1).getCell(11).getStringCellValue();
					 if (LDW.equalsIgnoreCase("yes")) {
					List<WebElement> dynamicElement = driver.findElements(By.xpath("//label[contains(text(),'Myles Secure')]/../input[@type='checkbox']"));
					 System.out.println("size: " +dynamicElement.size());
						
					if(dynamicElement.size() != 0){
						wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("label.mylesCoverHdn")));
					    WebElement chkBx = driver.findElement(By.cssSelector("label.mylesCoverHdn"));
						//WebElement chkBx = driver.findElement(By.xpath("//div[@id='PaymentDetails']//div[@class='mylesCover']//label"));
						System.out.println("Element is present");
					    act.moveToElement(chkBx).click().build().perform();
					    System.out.println("clicked");
					}

					}
					 else if(LDW.equalsIgnoreCase("no"))
					 {
						 System.out.println("Security deposit pay without LDW");
					 }
						//Thread.sleep(2000);

						Thread.sleep(3000);

						js.executeScript("arguments[0].click();",
								driver.findElement(By.xpath("//label[text()='I accept MYLES ']")));
						Thread.sleep(5000);
						// wait.until(ExpectedConditions.presenceOfElementLocated(By.id("credit_card_number")));
						 String CreditDebit =driver.findElement(By.xpath("//a[@href='#CreditDebit']")).getText();
						 System.out.println(CreditDebit);
					     String wallet =driver.findElement(By.xpath("//a[@href='#Wallet']")).getText();
						 System.out.println(wallet);
						 String netBanking =driver.findElement(By.xpath("//a[@href='#NetBanking']")).getText();
						 System.out.println(netBanking);
						 String payment = wb.getSheet("Myles").getRow(1).getCell(12).getStringCellValue();

						 if (CreditDebit.equalsIgnoreCase(payment))
						 {
							WebElement cardNum = driver.findElement(By.id("credit_card_number"));
							Thread.sleep(2000);
						    wait.until(ExpectedConditions.presenceOfElementLocated(By.id("credit_card_number")));
						    cardNum.sendKeys("4242424242424242");
							Thread.sleep(2000);
							driver.findElement(By.id("creditcardname")).sendKeys("test");
							WebElement expMonth = driver.findElement(By.id("credit_card_exp_month"));
							Select sel = new Select(expMonth);
							sel.selectByVisibleText("10");
							WebElement expYear = driver.findElement(By.id("credit_card_exp_year"));
							Select sel1 = new Select(expYear);
							sel1.selectByVisibleText("2020");
							driver.findElement(By.id("credit_security_code")).sendKeys("123");
							driver.findElement(By.id("card_payment")).click();
							Thread.sleep(1000);
							driver.findElement(By.id("OTP")).sendKeys("123123");
							driver.findElement(By.name("proceed")).click();
							
							String bookId = driver.findElement(By.xpath("//p[@class='bookID_T']")).getText();
				////////////takes booking id 		
							String BookingId=bookId.substring(69, 76).trim();
							
							System.out.println(BookingId);
							
//							ReadWriteExcel("./Myles.xlsx","Myles-Cancel",2,3,BookingId);
//							ReadWriteExcel("./Myles.xlsx","Insta-Open",1,3,BookingId);
//							ReadWriteExcel("./Myles.xlsx","Insta-Open",1,4,BookingId);
//							ReadWriteExcel("./Myles.xlsx","Insta-Close",1,3,BookingId);
//							ReadWriteExcel("./Myles.xlsx","Insta-Extension",1,3,BookingId);
//							ReadWriteExcel("./Myles.xlsx","Invoice-Damage",1,3,BookingId);
//							ReadWriteExcel("./Myles.xlsx","Swap-Car",1,3,BookingId);
//							ReadWriteExcel("./Myles.xlsx","Myles-Extension",1,3,BookingId);
//							ReadWriteExcel("./Myles.xlsx","Explore-Cancel",1,3,BookingId);
//------------------------------------------------------------------------------------------------------------							
							
						driver.findElement(By.linkText("Pay Now")).click();
						Thread.sleep(2000);
						
///////////////////////////////////////////						
//						try{
//						driver.navigate().refresh();
//						Thread.sleep(2000);
//						String securityamt= driver.findElement(By.xpath("//button[@id='card_payment']/span")).getText().trim();
//						System.out.println(securityamt);
//						
//						Integer SecAmt=Integer.parseInt(securityamt);
//						System.out.println(SecAmt);
//						if(SecAmt==5000 || SecAmt<5000){
//							
//							wait.until(ExpectedConditions.presenceOfElementLocated(By.id("credit_card_number")));
//						    cardNum.sendKeys("4242424242424242");
//							Thread.sleep(2000);
//							driver.findElement(By.id("creditcardname")).sendKeys("test");
//							WebElement expMonth1 = driver.findElement(By.id("credit_card_exp_month"));
//							Select sel2 = new Select(expMonth1);
//							sel2.selectByVisibleText("10");
//							WebElement expYear1 = driver.findElement(By.id("credit_card_exp_year"));
//							Select sel3 = new Select(expYear1);
//							sel3.selectByVisibleText("2020");
//							driver.findElement(By.id("credit_security_code")).sendKeys("123");
//							driver.findElement(By.id("card_payment")).click();
//							Thread.sleep(1000);
//							driver.findElement(By.id("OTP")).sendKeys("123123");
//							driver.findElement(By.name("proceed")).click();
//							
//						}
//							
//						else{
//							driver.close();
//						}
//						
//						
//						
//						
//						}
//						
//						catch(org.openqa.selenium.StaleElementReferenceException ex){
//						driver.navigate().refresh();
//							
//							String securityamt= driver.findElement(By.xpath("//button[@id='card_payment']/span")).getText().trim();
//							System.out.println(securityamt);
//							
//							Integer SecAmt=Integer.parseInt(securityamt);
//							System.out.println(SecAmt);
//							if(SecAmt==5000 || SecAmt<5000){
//								
//								wait.until(ExpectedConditions.presenceOfElementLocated(By.id("credit_card_number")));
//							    cardNum.sendKeys("4242424242424242");
//								Thread.sleep(2000);
//								driver.findElement(By.id("creditcardname")).sendKeys("test");
//								WebElement expMonth1 = driver.findElement(By.id("credit_card_exp_month"));
//								Select sel2 = new Select(expMonth1);
//								sel2.selectByVisibleText("10");
//								WebElement expYear1 = driver.findElement(By.id("credit_card_exp_year"));
//								Select sel3 = new Select(expYear1);
//								sel3.selectByVisibleText("2020");
//								driver.findElement(By.id("credit_security_code")).sendKeys("123");
//								driver.findElement(By.id("card_payment")).click();
//								Thread.sleep(1000);
//								driver.findElement(By.id("OTP")).sendKeys("123123");
//								driver.findElement(By.name("proceed")).click();
//								
//							}
//								
//							else{
//								driver.close();
//							}
//							
//						   }
							
							
							
						
				//		String securityamt= driver.findElement(By.xpath("//div[@class='paymentbackground']//span[2]")).getText();
					
//						Integer SecAmt=Integer.parseInt(securityamt);
//						System.out.println(SecAmt);
//						if(SecAmt==5000 || SecAmt<5000){
							System.out.println("Test");
						
						
							wait.until(ExpectedConditions.presenceOfElementLocated(By.id("credit_card_number")));
						    cardNum.sendKeys("4242424242424242");
							Thread.sleep(2000);
							driver.findElement(By.id("creditcardname")).sendKeys("test");
							WebElement expMonth1 = driver.findElement(By.id("credit_card_exp_month"));
							Select sel2 = new Select(expMonth1);
							sel2.selectByVisibleText("10");
							WebElement expYear1 = driver.findElement(By.id("credit_card_exp_year"));
							Select sel3 = new Select(expYear1);
							sel3.selectByVisibleText("2020");
							driver.findElement(By.id("credit_security_code")).sendKeys("123");
							driver.findElement(By.id("card_payment")).click();
							Thread.sleep(1000);
							driver.findElement(By.id("OTP")).sendKeys("123123");
							driver.findElement(By.name("proceed")).click();
							
//						}
//							
//						else{
//							driver.close();
//						}
							
							
							
							
							
							
							
							
							
							
							
					//		scan.close();	
						 }
						 else if (wallet.equalsIgnoreCase(payment))
						 {
						   driver.findElement(By.id("paytm")).click();
						   driver.findElement(By.id("w_payment")).click();
						}
						else if (netBanking.equalsIgnoreCase(payment)) 
						 {
						  driver.findElement(By.xpath("//a[@href='#NetBanking']")).click();
						 }
						File srcfile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
						File destFile = new File("./Shots/Booking.png");
						FileUtils.copyFile(srcfile, destFile);
						}
					
				}
				
	//			}
			  
			catch(Exception e){
					wait.until(ExpectedConditions.presenceOfElementLocated(By.id("proceed_auto")));
					
					driver.findElement(By.id("proceed_auto")).click();
				    Thread.sleep(3000);
				    
				    try {
						 List<WebElement> additionalService = driver.findElements(By.className("newAddresBlock"));
						 if (additionalService.size() > 0) 
						{
							 String addServiceGps = wb.getSheet("Myles").getRow(1).getCell(9).getStringCellValue();
							if (driver.findElement(By.xpath("//label[contains(text(),'Gps Navigation')]")).isDisplayed() && addServiceGps.equalsIgnoreCase("yes")) {
								js.executeScript("arguments[0].click();",driver.findElement(By.xpath("//label[contains(text(),'Gps Navigation')]")));
								System.out.println("gps-navigation is available");
							} 
							else
							{
								System.out.println("Gps navigation is unavailable");
							}
							Thread.sleep(3000);
						    String addServiceBaby = wb.getSheet("Myles").getRow(1).getCell(10).getStringCellValue();

							if (driver.findElement(By.xpath("//label[contains(text(),'Baby Seat')]")).isDisplayed() && addServiceBaby.equalsIgnoreCase("yes"))
							{
								js.executeScript("arguments[0].click();",driver.findElement(By.xpath("//label[contains(text(),'Baby Seat')]")));
								System.out.println("Baby seat is available");
							} 
							else 
							{
								System.out.println("BabySeat is unavailable");

							}
							js.executeScript("arguments[0].click();",driver.findElement(By.xpath("//div[@id='PickupDetails']//button[@id='pickupdropupAddr' and text()='Proceed']")));
						}

						else 
						{
							System.out.println("Both Gps navigation and Baby seat are unavailable");
							js.executeScript("arguments[0].click();",driver.findElement(By.xpath("//div[@id='PickupDetails']//button[@id='pickupdropupAddr' and text()='Proceed']")));
						}
			            }
					catch (NoSuchElementException a)
					    {
						System.out.println("no gps-navigattion and baby seat available");
					    }
			            Thread.sleep(4000);
					    String LDW = wb.getSheet("Myles").getRow(1).getCell(11).getStringCellValue();
					 if (LDW.equalsIgnoreCase("yes")) {
					List<WebElement> dynamicElement = driver.findElements(By.xpath("//label[contains(text(),'Myles Secure')]/../input[@type='checkbox']"));
					 System.out.println("size: " +dynamicElement.size());
						
					if(dynamicElement.size() != 0){
						wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("label.mylesCoverHdn")));
					    WebElement chkBx = driver.findElement(By.cssSelector("label.mylesCoverHdn"));
						//WebElement chkBx = driver.findElement(By.xpath("//div[@id='PaymentDetails']//div[@class='mylesCover']//label"));
						System.out.println("Element is present");
					    act.moveToElement(chkBx).click().build().perform();
					    System.out.println("clicked");
					}

					}
					 else if(LDW.equalsIgnoreCase("no"))
					 {
						 System.out.println("Security deposit pay without LDW");
					 }
						Thread.sleep(2000);

						Thread.sleep(3000);

						js.executeScript("arguments[0].click();",
								driver.findElement(By.xpath("//label[text()='I accept MYLES ']")));
						Thread.sleep(5000);
						// wait.until(ExpectedConditions.presenceOfElementLocated(By.id("credit_card_number")));
						 String CreditDebit =driver.findElement(By.xpath("//a[@href='#CreditDebit']")).getText();
						 System.out.println(CreditDebit);
					     String wallet =driver.findElement(By.xpath("//a[@href='#Wallet']")).getText();
						 System.out.println(wallet);
						 String netBanking =driver.findElement(By.xpath("//a[@href='#NetBanking']")).getText();
						 System.out.println(netBanking);
						 String payment = wb.getSheet("Myles").getRow(1).getCell(12).getStringCellValue();

						 if (CreditDebit.equalsIgnoreCase(payment))
						 {
							WebElement cardNum = driver.findElement(By.id("credit_card_number"));
							Thread.sleep(2000);
						    wait.until(ExpectedConditions.presenceOfElementLocated(By.id("credit_card_number")));
						    cardNum.sendKeys("4242424242424242");
							Thread.sleep(2000);
							driver.findElement(By.id("creditcardname")).sendKeys("test");
							WebElement expMonth = driver.findElement(By.id("credit_card_exp_month"));
							Select sel = new Select(expMonth);
							sel.selectByVisibleText("10");
							WebElement expYear = driver.findElement(By.id("credit_card_exp_year"));
							Select sel1 = new Select(expYear);
							sel1.selectByVisibleText("2020");
							driver.findElement(By.id("credit_security_code")).sendKeys("123");
							driver.findElement(By.id("card_payment")).click();
							Thread.sleep(1000);
							driver.findElement(By.id("OTP")).sendKeys("123123");
							driver.findElement(By.name("proceed")).click();
							
							String bookId = driver.findElement(By.xpath("//p[@class='bookID_T']")).getText();
				////////////takes booking id 		
							String BookingId=bookId.substring(69, 76).trim();
							
							System.out.println(BookingId);
							System.out.println("jay");
							
//							ReadWriteExcel("./Myles.xlsx","Myles-Cancel",2,3,BookingId);
//							ReadWriteExcel("./Myles.xlsx","Insta-Open",1,3,BookingId);
//							ReadWriteExcel("./Myles.xlsx","Insta-Open",1,4,BookingId);
//							ReadWriteExcel("./Myles.xlsx","Insta-Close",1,3,BookingId);
//							ReadWriteExcel("./Myles.xlsx","Insta-Extension",1,3,BookingId);
//							ReadWriteExcel("./Myles.xlsx","Invoice-Damage",1,3,BookingId);
//							ReadWriteExcel("./Myles.xlsx","Swap-Car",1,3,BookingId);
//							ReadWriteExcel("./Myles.xlsx","Myles-Extension",1,3,BookingId);
							
							System.out.println("prakash");
					//		scan.close();
							
							
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////						
							driver.findElement(By.linkText("Pay Now")).click();
							
//							String securityamt=driver.findElement(By.id("spShowPay")).getText();
//							Integer SecAmt=Integer.parseInt(securityamt);
//							if(SecAmt<5000 || SecAmt==5000){
								
								wait.until(ExpectedConditions.presenceOfElementLocated(By.id("credit_card_number")));
							    cardNum.sendKeys("4242424242424242");
								Thread.sleep(2000);
								driver.findElement(By.id("creditcardname")).sendKeys("test");
								WebElement expMonth1 = driver.findElement(By.id("credit_card_exp_month"));
								Select sel2 = new Select(expMonth1);
								sel2.selectByVisibleText("10");
								WebElement expYear1 = driver.findElement(By.id("credit_card_exp_year"));
								Select sel3 = new Select(expYear1);
								sel3.selectByVisibleText("2020");
								driver.findElement(By.id("credit_security_code")).sendKeys("123");
								driver.findElement(By.id("card_payment")).click();
								Thread.sleep(1000);
								driver.findElement(By.id("OTP")).sendKeys("123123");
								driver.findElement(By.name("proceed")).click();
								
								
								
//							}
//								
//							else{
//								driver.close();
//							}
//							
							
							
							
							
							
							
							
							
							
							
							
							
							
							
							
							
							
							
							
							
							
						 }
						 else if (wallet.equalsIgnoreCase(payment))
						 {
						   driver.findElement(By.id("paytm")).click();
						   driver.findElement(By.id("w_payment")).click();
						}
						else if (netBanking.equalsIgnoreCase(payment)) 
						 {
						  driver.findElement(By.xpath("//a[@href='#NetBanking']")).click();
						 }
						File srcfile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
						File destFile = new File("./Shots/Booking.png");
						FileUtils.copyFile(srcfile, destFile);
						}
			
		
//						else{
//						
//							System.out.println("No cars available");
//						}
				         
				    
				    
				    
				    
				    
				    
				
			
//////////////////////////////////////////////////////////////////////////////////////////////////////////////			
			
//			wait.until(ExpectedConditions.presenceOfElementLocated(By.id("proceed_auto")));
//			System.out.println("5");
//			driver.findElement(By.id("proceed_auto")).click();
//		    Thread.sleep(5000);
		    
  
		   	        }

	}

	
	
	
	
	
		
							
							
							
							
							
	
							
								

               }
	
	
	
	
	
	
	
		
		
	
	
	
	
	
    
