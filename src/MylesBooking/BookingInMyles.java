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
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
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
	
	
	public void SignIn() throws InterruptedException, InvalidFormatException, FileNotFoundException, IOException{
		System.setProperty("webdriver.chrome.driver", "./ExeFolder/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://qa2.mylescars.com/");
		Scanner scan = new Scanner(System.in);
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
		
		
	}
	
	
	
	
//   @Test(enabled=true)
	public void MylesBooking() throws EncryptedDocumentException, InvalidFormatException, FileNotFoundException, IOException, InterruptedException{
		System.setProperty("webdriver.chrome.driver", "./ExeFolder/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://qa2.mylescars.com/");
		Scanner scan = new Scanner(System.in);
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
		String pickTime = wb.getSheet("Myles").getRow(1).getCell(5).getStringCellValue();
		selPickTym.selectByValue(pickTime);

		driver.findElement(By.id("inputFieldSF2")).click();
		String dropDate = wb.getSheet("Myles").getRow(1).getCell(6).getStringCellValue();
		driver.findElement(By.xpath("//a[contains(text(),'"+dropDate+"')]")).click();

		WebElement dropDrpDwn = driver.findElement(By.id("inputFieldDT"));
		Select selDropTym = new Select(dropDrpDwn);
		String dropTime = wb.getSheet("Myles").getRow(1).getCell(7).getStringCellValue();
		selDropTym.selectByValue(dropTime);
		
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
		    System.out.println("2");
			Thread.sleep(3000);
			
		WebElement popup=driver.findElement(By.xpath("//a[@id='nv_js-box-close-button_11441']"));
			
		if(popup.isDisplayed()){
			popup.click();
		}
		else{
			System.out.println("popup not present");
		}
		
			driver.findElement(By.xpath("//select[@id='sel2']//following-sibling::div//button[@id='search_bookthis2']")).click();
			System.out.println("3");
			System.out.println("4");
			Thread.sleep(4000);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id("proceed_auto")));
			System.out.println("5");
			driver.findElement(By.id("proceed_auto")).click();
		Thread.sleep(7000);
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
				driver.findElement(By.id("credit_security_code")).sendKeys("111");
				driver.findElement(By.id("card_payment")).click();
				Thread.sleep(1000);
				driver.findElement(By.id("OTP")).sendKeys("123123");
				driver.findElement(By.name("proceed")).click();
				
				String bookId = driver.findElement(By.xpath("//p[@class='bookID_T']")).getText();
	////////////takes booking id 		
				String BookingId=bookId.substring(69, 76).trim();
				
				System.out.println(BookingId);
				
				ReadWriteExcel("./Myles.xlsx","Myles-Cancel",2,3,BookingId);
				
				ReadWriteExcel("./Myles.xlsx","Myles-Extension",1,3,BookingId);
				
				
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
			else
			{
				System.out.println("No cars available");
			}
	        }
	

	
//	@Test(enabled=false)
	public void MylesCancelBooking() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, FileNotFoundException, IOException{
		System.setProperty("webdriver.chrome.driver", "./ExeFolder/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://qa2.mylescars.com/");
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
		System.out.println("aaaaa");
		Thread.sleep(3000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();",driver.findElement(By.cssSelector("li#uRides")));
		System.out.println("qqqqqq");
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
		String expMsg = "The Booking has been successfully cancelled.";
		String actMsg = driver.switchTo().alert().getText();
		Assert.assertEquals(actMsg, expMsg);
		System.out.println(actMsg);
		}
		catch(NoSuchElementException e)
		{
			System.out.println("Search again with different cancel reason");
		}
		
		
	   }
	
	
//	@Test(enabled=false)
	public void MylesExtension() throws IOException, EncryptedDocumentException, InvalidFormatException, InterruptedException{
		System.setProperty("webdriver.chrome.driver", "./ExeFolder/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://qa2.mylescars.com/");
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
				sel.selectByVisibleText("12");
				WebElement expYear = driver.findElement(By.id("credit_card_exp_year"));
				Select sel1 = new Select(expYear);
				sel1.selectByVisibleText("2018");
				driver.findElement(By.id("credit_security_code")).sendKeys("111");
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
		
		
		
	}
	
	
	
	
	
    
