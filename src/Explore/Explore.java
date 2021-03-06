package Explore;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.myles.generic.pkg.Generic_Methods;

public class Explore extends Generic_Methods{
	
	
	public void CarSwap() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, FileNotFoundException, IOException {
//		System.setProperty("webdriver.chrome.driver", "./ExeFolder/chromedriver.exe");
//		WebDriver driver = new ChromeDriver();
//		driver.manage().window().maximize();
//		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("http://10.90.90.32:8443/");
		Workbook wb = WorkbookFactory.create(new FileInputStream(new File("./Myles.xlsx"))); 
		String userName = wb.getSheet("Swap-Car").getRow(1).getCell(1).getStringCellValue();
		driver.findElement(By.id("Username")).sendKeys(userName);
		String password = wb.getSheet("Swap-Car").getRow(1).getCell(2).getStringCellValue();
		driver.findElement(By.id("Password")).sendKeys(password);
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		WebDriverWait wait = new WebDriverWait(driver, 40);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//li[@class='active has-sub']//span[@class='booking']"))));
		driver.findElement(By.xpath("//li[@class='active has-sub']//span[@class='booking']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[contains(@href,'SwapCar')]")).click();
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//input[@placeholder='Please Enter Booking ID']"))));
		String bookingId = wb.getSheet("Swap-Car").getRow(1).getCell(3).getStringCellValue();
		driver.findElement(By.xpath("//input[@placeholder='Please Enter Booking ID']")).sendKeys(bookingId);
		Thread.sleep(2000);
		driver.findElement(By.className("btn_sub")).click();
		String txt = "Booking ID not correct.";
		if(driver.findElement(By.className("field-validation-error")).getText().equalsIgnoreCase(txt))
		{
			System.out.println("SORRY!!!!! This booking Id is not available for swapping. ");
		}
		else
		{
			WebElement drpDwnUnAvailable = driver.findElement(By.xpath("//label[text()='Unavailable cars']/..//select"));
			Select selUnavail = new Select(drpDwnUnAvailable);
			selUnavail.selectByIndex(2);
			Thread.sleep(2000);
			String txtReason = driver.findElement(By.xpath("//label[text()='Unavailable cars']/../..//p")).getText();
			System.out.println(txtReason);
			Thread.sleep(2000);
			String AvailableCars = wb.getSheet("Swap-Car").getRow(1).getCell(4).getStringCellValue();
			try {
			WebElement drpDwnAvailableCar = driver.findElement(By.xpath("//label[text()='Available Car Models ']/..//select"));
			Select selAvail = new Select(drpDwnAvailableCar);
			selAvail.selectByVisibleText(AvailableCars);
			Thread.sleep(2000);
			String AvailCarNo = wb.getSheet("Swap-Car").getRow(1).getCell(5).getStringCellValue();
			   try {
			    WebElement drpDwnAvailableCarNo = driver.findElement(By.xpath("//label[text()='Available Cars ']/..//select"));
			    Select selAvailNo = new Select(drpDwnAvailableCarNo);
			//    selAvailNo.selectByVisibleText(AvailCarNo);
			    selAvailNo.selectByIndex(1);
			    wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//div[@id='SwapReason']//select"))));
				WebElement drpDwnReason = driver.findElement(By.xpath("//div[@id='SwapReason']//select"));
				Select selReason = new Select(drpDwnReason);
				String reason = wb.getSheet("Swap-Car").getRow(1).getCell(6).getStringCellValue();
				selReason.selectByVisibleText(reason);
				Thread.sleep(2000);
				driver.findElement(By.xpath("//div[@class='clr']//following-sibling::input[@id='SwapSubmit']")).click();
				System.out.println("Car is sucessfully swapped");
			    }
			    catch (NoSuchElementException e) 
			    {
				System.out.println(AvailCarNo+" car number is not available. Please search again with different car number");
			    }
			
			
			}
			catch (NoSuchElementException e) 
			{
				System.out.println(AvailableCars+" car is not available. Please search again with different car");
			}
			
			
		}
		
	}

	
	
	public void ExploreCancel() throws InterruptedException, EncryptedDocumentException, InvalidFormatException, FileNotFoundException, IOException {
//		System.setProperty("webdriver.chrome.driver", "./ExeFolder/chromedriver.exe");
//		WebDriver driver = new ChromeDriver();
//		driver.manage().window().maximize();
//		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("http://10.90.90.32:8443/");
		Workbook wb = WorkbookFactory.create(new FileInputStream(new File("./Myles.xlsx"))); 
		String userName = wb.getSheet("Swap-Car").getRow(1).getCell(1).getStringCellValue();
		driver.findElement(By.id("Username")).sendKeys(userName);
		String password = wb.getSheet("Swap-Car").getRow(1).getCell(2).getStringCellValue();
		driver.findElement(By.id("Password")).sendKeys(password);
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		WebDriverWait wait = new WebDriverWait(driver, 40);
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//li[@class='active has-sub']//span[@class='booking']"))));
		driver.findElement(By.xpath("//li[@class='active has-sub']//span[@class='booking']")).click();
		Thread.sleep(2000);
//	    driver.findElement(By.xpath("//a[contains(@href,'Cancel Booking')]")).click();
	    driver.findElement(By.xpath("//div[@id='cssmenu']/ul/li/ul/li[3]/a/span")).click();
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("BookingID")));
		String bookingId = wb.getSheet("Explore-Cancel").getRow(1).getCell(3).getStringCellValue();
		driver.findElement(By.id("BookingID")).sendKeys(bookingId);
		Thread.sleep(2000);
		driver.findElement(By.className("btn_sub")).click();
		
		WebElement drpDwnDueTo = driver.findElement(By.id("due_to"));
		Select selDue = new Select(drpDwnDueTo);
		selDue.selectByVisibleText("Customer");
		Thread.sleep(2000);
		
		WebElement drpReason = driver.findElement(By.id("CancelBookingReasons"));
		Select selReason = new Select(drpReason);
		selReason.selectByVisibleText("Change Of Plan");
		Thread.sleep(3000);
		driver.findElement(By.id("CancelRequest")).click();
		Thread.sleep(3000);
		String cancdelMsg="BookingId has been Canceled successfully" ;
		try{
		wait.until(ExpectedConditions.alertIsPresent());
		Alert alert=driver.switchTo().alert();
		String alertmsg=alert.getText();
		
		if(cancdelMsg.equalsIgnoreCase(alertmsg)){
			alert.accept();
			System.out.println("Booking Cancelled Successfully");
		}}
		catch(NoAlertPresentException alt){
			System.out.println("No alert present");
		}
		
		driver.close();
		
		
		}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
