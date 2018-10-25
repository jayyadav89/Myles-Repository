package Insta;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;


public class InstaBooking {
	static WebDriver driver;
	static Workbook wb;
	public static void main(String[] args) throws InterruptedException, EncryptedDocumentException, InvalidFormatException, FileNotFoundException, IOException {
		System.setProperty("webdriver.chrome.driver", "./ExeFolder/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("http://10.90.90.32:8080");  
		wb = WorkbookFactory.create(new FileInputStream(new File("./Myles.xlsx"))); //http://10.90.90.32:8080/  (Local insta)
		String id = wb.getSheet("Insta-Booking").getRow(1).getCell(1).getStringCellValue();
		driver.findElement(By.id("loginID")).sendKeys(id);
		String pwd = wb.getSheet("Insta-Booking").getRow(1).getCell(2).getStringCellValue();
		driver.findElement(By.name("pwd")).sendKeys(pwd);
		driver.findElement(By.name("btnSubmit")).click();
		Thread.sleep(2000);
		WebElement drpDwn = driver.findElement(By.xpath("//table[@id='ctl00_CP_Menu_menu']//a[contains(text(),'Self Drive')]"));
		Actions act = new Actions(driver);
		act.moveToElement(drpDwn).perform();
		driver.findElement(By.xpath("//a[contains(text(),'New Booking')]")).click();
		String mobNo = wb.getSheet("Insta-Booking").getRow(1).getCell(3).getStringCellValue();
		driver.findElement(By.id("ctl00_CP_ContentPage_txt_Phone")).sendKeys(mobNo);
		driver.findElement(By.id("ctl00_CP_ContentPage_btn_searchClient")).click();
		Thread.sleep(2000);
		WebElement pickupDrpDwn = driver.findElement(By.id("ctl00_CP_ContentPage_ddl_PickupCity"));
		Select sel = new Select(pickupDrpDwn);
		String pickCity = wb.getSheet("Insta-Booking").getRow(1).getCell(4).getStringCellValue();
		sel.selectByVisibleText(pickCity);
		Thread.sleep(2000);
		WebElement dateCal = driver.findElement(By.id("ctl00_CP_ContentPage_txt_PickupDate"));// month-date-year
		dateCal.click();
		
		driver.findElement(By.id("ctl00_CP_ContentPage_CalendarExtender1_nextArrow")).click();
		Thread.sleep(2000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String dateP = wb.getSheet("Insta-Booking").getRow(1).getCell(5).getStringCellValue();
        WebElement datePick = driver.findElement(By.xpath("//div[@class='ajax__calendar_container']//div[contains(@title,'"+dateP+"')]"));

		js.executeScript("arguments[0].click();", datePick);
		Thread.sleep(2000);
		WebElement pickupTym = driver.findElement(By.id("ctl00_CP_ContentPage_ddl_PickupHr"));
		String pickTime = wb.getSheet("Insta-Booking").getRow(1).getCell(6).getStringCellValue();
		Select pickSel = new Select(pickupTym);
		pickSel.selectByValue(pickTime);
		Thread.sleep(2000);
		WebElement hourDrpDwn = driver.findElement(By.id("ctl00_CP_ContentPage_ddl_PickupMin"));
		String pickMin = wb.getSheet("Insta-Booking").getRow(1).getCell(7).getStringCellValue();
		Select hourSel = new Select(hourDrpDwn);
		hourSel.selectByVisibleText(pickMin);
		Thread.sleep(2000);
		
		WebElement dateDropCal = driver.findElement(By.id("ctl00_CP_ContentPage_txt_dropOffDate"));// month-date-year
		dateDropCal.click();
		String dateD = wb.getSheet("Insta-Booking").getRow(1).getCell(8).getStringCellValue();
		WebElement dateDrop = driver.findElement(By.xpath("//div[@class='ajax__calendar_container']//div[contains(@title,'"+dateD+"')]"));

		js.executeScript("arguments[0].click();", dateDrop);
		Thread.sleep(4000);
		try 
		{
			System.out.println("aaaa");
			WebElement dropupTym = driver.findElement(By.id("ctl00_CP_ContentPage_ddl_dropoffHr"));
			String dropTime = wb.getSheet("Insta-Booking").getRow(1).getCell(10).getStringCellValue();
			Select dropSel = new Select(dropupTym);
			dropSel.selectByValue(dropTime);
			Thread.sleep(2000);
			WebElement hourDrpDwnDrop = driver.findElement(By.id("ctl00_CP_ContentPage_ddl_dropoffmin"));
			String dropMin = wb.getSheet("Insta-Booking").getRow(1).getCell(11).getStringCellValue();
			Select hourSelDrop = new Select(hourDrpDwnDrop);
			hourSelDrop.selectByVisibleText(dropMin);
			
			
			
		}
		catch(NoSuchElementException e)
		{
			System.out.println("sadsa");
			Select selHour = new Select(driver.findElement(By.id("ctl00_CP_ContentPage_ddlHour")));
			String nOfHours = wb.getSheet("Insta-Booking").getRow(1).getCell(9).getStringCellValue();
			selHour.selectByVisibleText(nOfHours);
		}
		Thread.sleep(2000);
		
		boolean flag = locationCarPick();
		
//		String locWiseTxt = driver.findElement(By.xpath("//table[@class='my-radio']//label[text()='Location Wise']")).getText();
//		String modelWiseTxt = driver.findElement(By.xpath("//table[@class='my-radio']//label[text()='Model Wise']")).getText();
//		Thread.sleep(2000);
//		String LocWiseModelWise = wb.getSheet("Insta-Booking").getRow(1).getCell(12).getStringCellValue();     //Location Wise   &    Model Wise
//		if (locWiseTxt.equalsIgnoreCase(LocWiseModelWise)) 
//		{
//			
//			WebElement locDrpDwn = driver.findElement(By.id("ctl00_CP_ContentPage_ddlSubLocation"));
//			String location = wb.getSheet("Insta-Booking").getRow(1).getCell(13).getStringCellValue();
//			Select locSel = new Select(locDrpDwn);
//			locSel.selectByVisibleText(location);
//			Thread.sleep(3000);
//			//WebElement carDrpDwn = driver.findElement(By.xpath("ctl00_CP_ContentPage_ddl_CarModel"));
//			try {
//			WebElement carDrpDwn = driver.findElement(By.id("ctl00_CP_ContentPage_ddl_CarModel"));
//			String car = wb.getSheet("Insta-Booking").getRow(1).getCell(14).getStringCellValue();
//			Select carSel =new Select(carDrpDwn);
//			carSel.selectByVisibleText(car);
//			}
//			catch (NoSuchElementException e) 
//			{
//				List<WebElement> l = driver.findElements(By.id("ctl00_CP_ContentPage_ddl_CarModel"));
//				int size = l.size();
//				for (int i = 0; i < size; i++) 
//				{
//					System.out.println(l.get(i).getText());
//					driver.close();
//					driver.quit();
//					
//				}
//			
//			}
//		}
//		else if (modelWiseTxt.equalsIgnoreCase(LocWiseModelWise)) 
//		{
//			driver.findElement(By.id("ctl00_CP_ContentPage_rdbtnInventory_1")).click();
//			Thread.sleep(3000);
//			String car = wb.getSheet("Insta-Booking").getRow(1).getCell(14).getStringCellValue();
//			WebElement carDrpDwn = driver.findElement(By.id("ctl00_CP_ContentPage_ddl_CarModel"));
//			Select carSel =new Select(carDrpDwn);
//			carSel.selectByVisibleText(car);
//			Thread.sleep(3000);
//			WebElement locDrpDwn = driver.findElement(By.id("ctl00_CP_ContentPage_ddlSubLocation")); 
//			String location = wb.getSheet("Insta-Booking").getRow(1).getCell(13).getStringCellValue();
//			Select locSel = new Select(locDrpDwn);
//			locSel.selectByVisibleText(location);
//		}
		
		if(flag == true)
		{
			Thread.sleep(3000);
		WebElement pkgDrpDwn = driver.findElement(By.xpath("//div[@class='location']/select[@id='ctl00_CP_ContentPage_ddl_pkgkm']"));
		String pkg = wb.getSheet("Insta-Booking").getRow(1).getCell(15).getStringCellValue();
		Select pkgSel = new Select(pkgDrpDwn);
		pkgSel.selectByVisibleText(pkg);
		System.out.println("pkg selected");
		Thread.sleep(8000);
		
		driver.findElement(By.id("ctl00_CP_ContentPage_btnCalculatePkgPrice")).click();
		Thread.sleep(4000);
		
		if (driver.findElement(By.xpath("//span[@id='ctl00_CP_ContentPage_lbl_ApproxCost']")).getText().equals("No Package Available"))
		{
			System.out.println("No package available");
			
		}
		else
		{
			
			Thread.sleep(2000);
			try {
				 List<WebElement> additionalService = driver.findElements(By.id("ctl00_CP_ContentPage_grvAdditionalService"));
				 if (additionalService.size() > 0) 
				{
					String addServiceGps = wb.getSheet("Insta-Booking").getRow(1).getCell(16).getStringCellValue();
					if (driver.findElement(By.id("ctl00_CP_ContentPage_grvAdditionalService_ctl02_chkSrvices")).isDisplayed() && addServiceGps.equalsIgnoreCase("yes")) {
					// if (driver.findElement(By.id("ctl00_CP_ContentPage_grvAdditionalService_ctl02_chkSrvices")).isDisplayed()) {
					 js.executeScript("arguments[0].click();",
								driver.findElement(By.xpath("//label[contains(text(),'Gps Navigation')]")));
						System.out.println("gps-navigation is available");
					} 
					else
					{
						System.out.println("Gps navigation is unavailable");
					}
	 
					String addServiceBaby = wb.getSheet("Insta-Booking").getRow(1).getCell(17).getStringCellValue();

					if (driver.findElement(By.xpath("//label[contains(text(),'Baby Seat')]")).isDisplayed() && addServiceBaby.equalsIgnoreCase("yes"))
					// if (driver.findElement(By.xpath("//label[contains(text(),'Baby Seat')]")).isDisplayed())
					 {
						js.executeScript("arguments[0].click();",driver.findElement(By.xpath("//label[contains(text(),'Baby Seat')]")));
						System.out.println("Baby seat is available");
					} 
					else 
					{
						System.out.println("BabySeat is unavailable");

					}
				}

				else 
				{
					System.out.println("Both Gps navigation and Baby seat are unavailable");
				
				}

			}
			catch (NoSuchElementException e)
			{
				System.out.println("no gps-navigattion and baby seat available");
			}
			
			Thread.sleep(5000);
			driver.findElement(By.id("ctl00_CP_ContentPage_chkDL")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("ctl00_CP_ContentPage_chkPass")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("ctl00_CP_ContentPage_chkCreditlimit")).click();
			Thread.sleep(2000);
			//js.executeScript("arguments[0].click();", driver.findElement(By.id("ctl00_CP_ContentPage_btn_Book")));
//			js.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("input#ctl00_CP_ContentPage_btn_Book")));
//			js.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("input#ctl00_CP_ContentPage_btn_Book")));
			//driver.findElement(By.id("ctl00_CP_ContentPage_btn_Book")).click();
			driver.findElement(By.xpath("//div[@id='ctl00_CP_ContentPage_UpdatePanel1']//input[@type='submit' and @id='ctl00_CP_ContentPage_btn_Book']")).click();
			//js.executeScript("arguments[0].click();", driver.findElement(By.className("cofirm-booking")));
			System.out.println("booked");
			Thread.sleep(5000);

		}
		}
		else 
		{
			System.out.println("Please select the detial shown below");
			
		}
		
		
		
	}
	
//	static boolean carPick()
//	{
//		boolean flag = true;
//		try {
//			WebElement carDrpDwn = driver.findElement(By.id("ctl00_CP_ContentPage_ddl_CarModel"));
//			String car = wb.getSheet("Insta-Booking").getRow(1).getCell(14).getStringCellValue();
//			Select carSel =new Select(carDrpDwn);
//			carSel.selectByVisibleText(car);
//			return flag;
//			}
//			catch (NoSuchElementException e) 
//			{
//				List<WebElement> l = driver.findElements(By.id("ctl00_CP_ContentPage_ddl_CarModel"));
//				int size = l.size();
//				for (int i = 0; i < size; i++) 
//				{
//					System.out.println(l.get(i).getText());
//					flag = false;
//					
//				}
//				return false;
//			
//			}
//		
//		
//		
//	}
	
	static boolean locationCarPick() throws InterruptedException
	{
		boolean flag = true;
		String locWiseTxt = driver.findElement(By.xpath("//table[@class='my-radio']//label[text()='Location Wise']")).getText();
		String modelWiseTxt = driver.findElement(By.xpath("//table[@class='my-radio']//label[text()='Model Wise']")).getText();
		Thread.sleep(2000);
		String LocWiseModelWise = wb.getSheet("Insta-Booking").getRow(1).getCell(12).getStringCellValue();     //Location Wise   &    Model Wise
		if (locWiseTxt.equalsIgnoreCase(LocWiseModelWise)) 
		{
			
			WebElement locDrpDwn = driver.findElement(By.id("ctl00_CP_ContentPage_ddlSubLocation"));
			String location = wb.getSheet("Insta-Booking").getRow(1).getCell(13).getStringCellValue();
			Select locSel = new Select(locDrpDwn);
			locSel.selectByVisibleText(location);
			Thread.sleep(5000);
			//WebElement carDrpDwn = driver.findElement(By.xpath("ctl00_CP_ContentPage_ddl_CarModel"));
			try {
			WebElement carDrpDwn = driver.findElement(By.id("ctl00_CP_ContentPage_ddl_CarModel"));
			String car = wb.getSheet("Insta-Booking").getRow(1).getCell(14).getStringCellValue();
			Select carSel =new Select(carDrpDwn);
			carSel.selectByVisibleText(car);
			}
			catch (NoSuchElementException e) 
			{
				List<WebElement> l = driver.findElements(By.id("ctl00_CP_ContentPage_ddl_CarModel"));
				int size = l.size();
				for (int i = 0; i < size; i++) 
				{
					System.out.println("List of cars :> ");
					System.out.println(l.get(i).getText());					
				}
				flag = false;
			}
		}
		else if (modelWiseTxt.equalsIgnoreCase(LocWiseModelWise)) 
		{
			driver.findElement(By.id("ctl00_CP_ContentPage_rdbtnInventory_1")).click();
			Thread.sleep(5000);
			String car = wb.getSheet("Insta-Booking").getRow(1).getCell(14).getStringCellValue();
			WebElement carDrpDwn = driver.findElement(By.id("ctl00_CP_ContentPage_ddl_CarModel"));
			Select carSel =new Select(carDrpDwn);
			carSel.selectByVisibleText(car);
			Thread.sleep(5000);
			try {
			WebElement locDrpDwn = driver.findElement(By.id("ctl00_CP_ContentPage_ddlSubLocation")); 
			String location = wb.getSheet("Insta-Booking").getRow(1).getCell(13).getStringCellValue();
			Select locSel = new Select(locDrpDwn);
			locSel.selectByVisibleText(location);
			}
			catch(NoSuchElementException e)
			{
				List<WebElement> l = driver.findElements(By.id("ctl00_CP_ContentPage_ddlSubLocation"));
				int size = l.size();
				for (int i = 0; i < size; i++) 
				{
					System.out.println("List of location :> ");
					System.out.println(l.get(i).getText());
				}
				flag = false;
			}
	}
		return flag;
	}
	

}
