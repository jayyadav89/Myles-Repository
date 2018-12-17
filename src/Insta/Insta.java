package Insta;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.myles.generic.pkg.Generic_Methods;

public class Insta extends Generic_Methods {
    static WebDriver driver;
	static Workbook wb;
	static String[] arr = {"Months", "January","February","March","April", "May", "June", "July", "August","September", "October", "November", "December"};
	
	
	public void InstaBooking() throws InterruptedException, InvalidFormatException, FileNotFoundException, IOException{
		System.setProperty("webdriver.chrome.driver", "./ExeFolder/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("http://10.90.90.32:8080");  
		wb = WorkbookFactory.create(new FileInputStream(new File("./Myles.xlsx"))); 
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

			driver.findElement(By.xpath("//div[@id='ctl00_CP_ContentPage_UpdatePanel1']//input[@type='submit' and @id='ctl00_CP_ContentPage_btn_Book']")).click();
			Thread.sleep(2000);
			//js.executeScript("arguments[0].click();", driver.findElement(By.className("cofirm-booking")));
			System.out.println("booked");
//			Thread.sleep(2000);
          }
		  }
		else 
		{
			System.out.println("Please select the detial shown below");
		}
		}
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
	   
	   
	   
	   
	   public void DSOpen() throws InterruptedException, InvalidFormatException, FileNotFoundException, IOException{
		   
		   System.setProperty("webdriver.chrome.driver", "./ExeFolder/chromedriver.exe");
			WebDriver driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			driver.manage().deleteAllCookies();
			driver.get("http://10.90.90.32:8080/");
			Workbook wb = WorkbookFactory.create(new FileInputStream(new File("./Myles.xlsx")));
			String id = wb.getSheet("Insta-Open").getRow(1).getCell(1).getStringCellValue();
			driver.findElement(By.id("loginID")).sendKeys(id);
			String pwd = wb.getSheet("Insta-Open").getRow(1).getCell(2).getStringCellValue();
			driver.findElement(By.name("pwd")).sendKeys(pwd);
			driver.findElement(By.name("btnSubmit")).click();
			Thread.sleep(1000);
			Actions act = new Actions(driver);
			WebElement dsDrpDwn = driver.findElement(By.xpath("//td[@id='ctl00_CP_Menu_menun3']//a[contains(text(),'Dutyslip')]"));
			WebElement opnDS = driver.findElement(By.xpath("//a[contains(text(),'Open Dutyslip')]"));
		    act.moveToElement(dsDrpDwn).perform();
		    act.click(opnDS).perform();
		    Thread.sleep(1000);
		    /******************************************************************************Booking id****************************************************************/
		    String bookingID = wb.getSheet("Insta-Open").getRow(1).getCell(3).getStringCellValue();
		    driver.findElement(By.id("ctl00_CP_ContentPage_txtBkid")).sendKeys(bookingID);
		    driver.findElement(By.id("ctl00_CP_ContentPage_btn_search")).click();
		    Thread.sleep(1000);
		    
		    driver.findElement(By.xpath("//a[text()='Create']")).click();
		    Thread.sleep(1000);
		    
		    WebElement rentalStrtKM = driver.findElement(By.id("ctl00_CP_ContentPage_lblLastInKm"));
		    String rentalTxt = rentalStrtKM.getText();
		    System.out.println(rentalTxt);
		    String[] arr = rentalTxt.split(":");
		    int lstKM = Integer.parseInt(arr[1])+1;
		    String strngRentalKM = Integer.toString(lstKM);
		    driver.findElement(By.id("ctl00_CP_ContentPage_txt_km")).sendKeys(strngRentalKM);
		    
		    driver.findElement(By.id("ctl00_CP_ContentPage_txt_DLExpiry")).sendKeys("12-22-2020");
		    driver.findElement(By.id("ctl00_CP_ContentPage_txt_PAddress")).sendKeys("asd");
		    driver.findElement(By.id("ctl00_CP_ContentPage_txt_LAddress")).sendKeys("qwe");
		    
		    driver.findElement(By.id("ctl00_CP_ContentPage_txtLicenseNo")).sendKeys("ags344542");
		    driver.findElement(By.id("ctl00_CP_ContentPage_txt_PIssue")).sendKeys("Delhi");
		    
		    
		    WebElement preAuthId = driver.findElement(By.id("ctl00_CP_ContentPage_txt_TranID"));
		    String preId = wb.getSheet("Insta-Open").getRow(1).getCell(4).getStringCellValue();
		    preAuthId.sendKeys(preId + "abc"  );
		    
		    
		    WebElement stateDrpDwn = driver.findElement(By.id("ctl00_CP_ContentPage_ddlAllocationState"));
		    Select sel = new Select(stateDrpDwn);
		    sel.selectByValue("7");
		    Thread.sleep(1000);
		    
		    WebElement cityDrpDwn = driver.findElement(By.id("ctl00_CP_ContentPage_ddlAllocationCity"));
		    Select sel1 = new Select(cityDrpDwn);
		    sel1.selectByValue("50");
		    Thread.sleep(1000);
		    
		    driver.findElement(By.id("ctl00_CP_ContentPage_txtAgreementNo")).sendKeys("1234");
		    driver.findElement(By.id("ctl00_CP_ContentPage_FileUpload2")).sendKeys("C:\\Users\\jay.yadav\\Desktop\\Aadhar.png");
		    
		    
//		    boolean flag = preAuthId.isEnabled();
//		   	    if(flag)
//		    {
//		   	 /**********************************************************enter unique preAuthID for each booking id******************************************************/   	
//		   	    	String preId = wb.getSheet("Insta-Open").getRow(1).getCell(4).getStringCellValue();
//		   	    	preAuthId.sendKeys(preId);
//		    }
//		    else
//		    {
//		    	System.out.println("Trip Insurence");
//		    }
//		    
		   	 driver.findElement(By.id("ctl00_CP_ContentPage_btn_Cont")).click();
		   	 String opnCnfrmTxt = driver.findElement(By.id("ctl00_CP_ContentPage_lbl_message")).getText();
		     System.out.println(opnCnfrmTxt);
		}
		   
		   
	   
	   
	   
	   
	   public void InstaExtension() throws InterruptedException, InvalidFormatException, FileNotFoundException, IOException{
		   
		   System.setProperty("webdriver.chrome.driver", "./ExeFolder/chromedriver.exe");
			WebDriver driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			driver.get("http://10.90.90.32:8080/");
			
			Workbook wb = WorkbookFactory.create(new FileInputStream(new File("./Myles.xlsx")));
			String id = wb.getSheet("Insta-Extension").getRow(1).getCell(1).getStringCellValue();
			driver.findElement(By.id("loginID")).sendKeys(id);
			String pwd = wb.getSheet("Insta-Extension").getRow(1).getCell(2).getStringCellValue();
			driver.findElement(By.name("pwd")).sendKeys(pwd);
			driver.findElement(By.name("btnSubmit")).click();
			Thread.sleep(1000);
			WebElement drpDwn = driver.findElement(By.xpath("//table[@id='ctl00_CP_Menu_menu']//a[contains(text(),'Self Drive')]"));
			Actions act = new Actions(driver);
			act.moveToElement(drpDwn).perform();
			driver.findElement(By.xpath("//a[contains(text(),'Extension of Bookings')]")).click();
			Thread.sleep(2000);
			String bookingId = wb.getSheet("Insta-Extension").getRow(1).getCell(3).getStringCellValue();
			driver.findElement(By.id("ctl00_CP_ContentPage_txt_BookingID")).sendKeys(bookingId);
			driver.findElement(By.id("ctl00_CP_ContentPage_btn_search")).click();
			try {
			WebElement warning = driver.findElement(By.xpath("//span[@id='ctl00_CP_ContentPage_lblextensioninformation']//h3"));
//			if (warning.isDisplayed()) 
//			{
				String errMsg = driver.findElement(By.xpath("//span[@id='ctl00_CP_ContentPage_lblextensioninformation']//h3")).getText();
				System.out.println(errMsg);
	    	}
//			else if(!warning.isDisplayed())
//			{
			catch(NoSuchElementException e)
			{
				System.out.println("Extension for this booking is available");
				WebElement sourceOfInfo = driver.findElement(By.id("ctl00_CP_ContentPage_ddlsourceofinformation")); 
				Select sel = new Select(sourceOfInfo);
				String mailCall = wb.getSheet("Insta-Extension").getRow(1).getCell(4).getStringCellValue();
				if (mailCall.equalsIgnoreCase("mail"))
				{
					sel.selectByVisibleText("Mail");
				}
				else if (mailCall.equalsIgnoreCase("call")) 
				{
					sel.selectByVisibleText("Call");
				}
				
//				 DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//				 Date date = new Date();
//				 String date1= dateFormat.format(date);
//				 String[] arrSplit = date1.split("/");
//				 System.out.println("System date: " +arrSplit[0]);
//				 int sysMnth =  Integer.parseInt(arrSplit[1]);
//				 
//				String ExtensionDropdateTxt = driver.findElement(By.xpath("(//table[@id='ctl00_CP_ContentPage_grid_BookInfo']//td)[7]")).getText();
//				String[] arrExtension = ExtensionDropdateTxt.split("/");
//				System.out.println(arrExtension[0]+"asdasdf");
//				int arrInt = Integer.parseInt(arrExtension[0]);    // 11(int)
//				//int Position = 1;
//				driver.findElement(By.id("ctl00_CP_ContentPage_txt_extendedDate")).click();
//				String monthNum = wb.getSheet("Insta-Extension").getRow(1).getCell(5).getStringCellValue();
//				System.out.println("2");
//				int positionOfMonth = Integer.parseInt(monthNum);  // 12 (int)
//				String month = arr[positionOfMonth];
//				System.out.println(month);
//				//WebElement extensionUpto = driver.findElement(By.xpath("//input[@id='ctl00_CP_ContentPage_txt_extendedDate']"));
//				System.out.println("3");
//				WebElement nxtBtn = driver.findElement(By.id("ctl00_CP_ContentPage_CalendarExtender1_nextArrow"));
//				System.out.println("5");
//				
//				if (positionOfMonth < (sysMnth))   //arrInt = 10     position of month = 10 
//				{
//					System.out.println("positionOfMonth > (sysMnth)");
//					System.out.println("Cannot select extended date less than drop off  date!");
//					driver.close();
//				}
//				else if (positionOfMonth > (sysMnth)) 
//				{
//					System.out.println(month+" month is greater");
//					for (int i = 0; i < arr.length; i++)
//					{
//						System.out.println("positionOfMonth > (sysMnth)");
//						nxtBtn.click();
//						String calenderExtUpto = driver.findElement(By.id("ctl00_CP_ContentPage_CalendarExtender1_title")).getText();
//						String[] calExtUptoArr = calenderExtUpto.split(",");
//						if(calExtUptoArr[0].equalsIgnoreCase(month))
//						{
//							String dateforExtention = wb.getSheet("Insta-Extension").getRow(1).getCell(6).getStringCellValue();
//				//****  //tbody//div[text()='5']     **** //div[text()='"+dateforExtention+"' and @class='ajax__calendar_day']
//							driver.findElement(By.xpath("//tbody//div[text()='"+dateforExtention+"'] ")).click();
//							break;
//						}
//						
//					}
//					
//					
//				}
//				else if (positionOfMonth == (sysMnth)) 
//				{
//					for (int i = 0; i < arr.length; i++)
//					{
//						System.out.println("equal");
//						//nxtBtn.click();
//
//							String dateforExtention = wb.getSheet("Insta-Extension").getRow(1).getCell(6).getStringCellValue();
//							System.out.println(dateforExtention);
//				//****  //tbody//div[text()='5']     **** //div[text()='"+dateforExtention+"' and @class='ajax__calendar_day']
//							driver.findElement(By.xpath("//tbody//div[text()='"+dateforExtention+"'] ")).click();
//							break;
////						}
//						
//					}
//				}
				
				
				driver.findElement(By.id("ctl00_CP_ContentPage_txt_extendedDate")).sendKeys("01-19-2019");
				
				
				
				WebElement ExtensionPickupHour = driver.findElement(By.id("ctl00_CP_ContentPage_ddl_PickupHr"));
				String excelExtPickUpHour = wb.getSheet("Insta-Extension").getRow(1).getCell(7).getStringCellValue();
				Select selExtHour = new Select(ExtensionPickupHour);
				selExtHour.selectByVisibleText(excelExtPickUpHour);
				Thread.sleep(2000);
			
				WebElement ExtensionPickupMin = driver.findElement(By.xpath("//select[contains(@name,'PickupMin')]"));
			//	ExtensionPickupMin.click();
				String excelExtPickUpMin = wb.getSheet("Insta-Extension").getRow(1).getCell(8).getStringCellValue();
				Select selExtMin = new Select(ExtensionPickupMin);
				selExtMin.selectByValue(excelExtPickUpMin);
				Thread.sleep(2000);

				
				driver.findElement(By.id("ctl00_CP_ContentPage_txtbxrequesttime")).sendKeys("01-18-2019");
				
				
//				 char s = arrSplit[0].charAt(0);
//				 String sChar = Character.toString(s);
//					if(sChar.equals("0"))
//					{
//						driver.findElement(By.id("ctl00_CP_ContentPage_txtbxrequesttime")).click();
//						driver.findElement(By.xpath("//b[text()='Request Time']/..//following-sibling::td//div[text()='"+arrSplit[0].charAt(1)+"']")).click();
						WebElement reqestPickupHour = driver.findElement(By.id("ctl00_CP_ContentPage_ddlrequesthour"));
				//		String excelReqPickUpHour = wb.getSheet("Insta-Extension").getRow(1).getCell(10).getStringCellValue();
						Select selReqHour = new Select(reqestPickupHour);
						selReqHour.selectByVisibleText("19");
//					}
//					else {
//				driver.findElement(By.id("ctl00_CP_ContentPage_txtbxrequesttime")).click();
//				driver.findElement(By.xpath("//b[text()='Request Time']/..//following-sibling::td//div[text()='"+arrSplit[0]+"']")).click();
//				WebElement reqestPickupHour = driver.findElement(By.id("ctl00_CP_ContentPage_ddlrequesthour"));
//				//String excelReqPickUpHour = wb.getSheet("Insta-Extension").getRow(1).getCell(10).getStringCellValue();
//				Select selReqHour = new Select(reqestPickupHour);
//				selReqHour.selectByVisibleText("19");
//					}
//				Thread.sleep(2000);
				
				driver.findElement(By.id("ctl00_CP_ContentPage_btnExtensionCalculation")).click();
//				List<WebElement> listStar = driver.findElements(By.xpath("//span[contains(@id,'RequiredFieldValidator4')]"));
//				if (listStar.size()==0) 
//				{
					String confirmExpextedMsg = "Car is available for this extension.You may proceed with update";
					String confirmActualMsg = driver.findElement(By.xpath("//span[@id='ctl00_CP_ContentPage_lblextensioninformation']//h3")).getText();
					String errExpextedMsg ="Car is Booked by Another Booking.You can update it but it would be violation";
					String errActualMsg = driver.findElement(By.xpath("//span[@id='ctl00_CP_ContentPage_lblextensioninformation']//h3")).getText();
					if (confirmActualMsg.equals(confirmExpextedMsg)) 
					{
						String amount = driver.findElement(By.id("ctl00_CP_ContentPage_lblExtensionCalculatedAmount")).getText();
						System.out.println(amount);
						
						driver.findElement(By.id("ctl00_CP_ContentPage_btnUpdate")).click();
						driver.switchTo().alert().accept();
						Thread.sleep(4000);
						System.out.println("extended date Updated successfully!");
						//driver.quit();
						driver.close();
					}
					else if (errActualMsg.equals(errExpextedMsg)) 
					{
						
						String amount = driver.findElement(By.id("ctl00_CP_ContentPage_lblExtensionCalculatedAmount")).getText();
						System.out.println(amount);
						driver.findElement(By.id("ctl00_CP_ContentPage_btnUpdate")).click();
						String nxtDate = driver.findElement(By.xpath("(//table[@id='ctl00_CP_ContentPage_gvNextBookingDetails']//td)[7]")).getText();
						System.out.println("You can select the date after "+nxtDate);
					}
			}
			
			catch (ElementNotVisibleException e) {
				e.printStackTrace();
				System.out.println("please choose the correct locator");
			}
			
		   }
	   
	   
	   
	   
	   public void DamageInvoice() throws InterruptedException, InvalidFormatException, FileNotFoundException, IOException{
		   
		   System.setProperty("webdriver.chrome.driver", "./ExeFolder/chromedriver.exe");
			WebDriver driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			driver.get("http://10.90.90.32:8080");
			Workbook wb = WorkbookFactory.create(new FileInputStream(new File("./Myles.xlsx"))); 
			String id = wb.getSheet("Invoice-Damage").getRow(1).getCell(1).getStringCellValue();
			driver.findElement(By.id("loginID")).sendKeys(id);
			String pwd = wb.getSheet("Invoice-Damage").getRow(1).getCell(2).getStringCellValue();
			driver.findElement(By.name("pwd")).sendKeys(pwd);
			driver.findElement(By.name("btnSubmit")).click();
			Thread.sleep(1000);
			Actions act = new Actions(driver);
			WebElement invoiceDrpDwn = driver.findElement(By.xpath("//td[@id='ctl00_CP_Menu_menun2']//a[contains(text(),'Invoice')]"));
			WebElement damageInvoice = driver.findElement(By.xpath("//a[contains(text(),'Close Dutyslip')]"));
		    act.moveToElement(invoiceDrpDwn).perform();
		    act.click(damageInvoice).perform();
		    Thread.sleep(3000);
			String bookingID = wb.getSheet("Invoice-Damage").getRow(1).getCell(3).getStringCellValue();
		    driver.findElement(By.xpath("//b[text()='Enter Booking ID:']//following-sibling::input[@id='ctl00_CP_ContentPage_txtBookingID']")).sendKeys(bookingID);
		    driver.findElement(By.xpath("//b[text()='Enter Booking ID:']//following-sibling::input[@id='ctl00_CP_ContentPage_btnSearch']")).click();
			Thread.sleep(2000);
			
			String damageAmount = wb.getSheet("Invoice-Damage").getRow(1).getCell(4).getStringCellValue();
			driver.findElement(By.xpath("//table[@id='ctl00_CP_ContentPage_tbl_Details']//input[@id='ctl00_CP_ContentPage_txtDamageCharges']")).sendKeys(damageAmount);
			driver.findElement(By.xpath("//table[@id='ctl00_CP_ContentPage_tbl_Details']//textarea[@id='ctl00_CP_ContentPage_txtRemarks']")).click();
			Thread.sleep(2000);
			
			
			
//			DesiredCapabilities dc = new DesiredCapabilities();
//			dc.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
//			driver = new ChromeDriver(dc);
			
			
			
//			try{
			
			String remark = wb.getSheet("Invoice-Damage").getRow(1).getCell(5).getStringCellValue();
			driver.findElement(By.xpath("//table[@id='ctl00_CP_ContentPage_tbl_Details']//textarea[@id='ctl00_CP_ContentPage_txtRemarks']")).sendKeys(remark);
			Thread.sleep(2000);
			
			
			String mannualChargingInfo = wb.getSheet("Invoice-Damage").getRow(1).getCell(6).getStringCellValue();
			driver.findElement(By.xpath("//table[@id='ctl00_CP_ContentPage_tbl_DetailsRequired']//input[@id='ctl00_CP_ContentPage_txtManualChargInfo']")).sendKeys(mannualChargingInfo);
			Thread.sleep(2000);
			WebElement cardType = driver.findElement(By.xpath("//table[@id='ctl00_CP_ContentPage_tbl_DetailsRequired']//select[@id='ctl00_CP_ContentPage_ddlCardType']"));
			Select selCard = new Select(cardType);
			selCard.selectByIndex(1);
			Thread.sleep(2000);
			String cardNumber = wb.getSheet("Invoice-Damage").getRow(1).getCell(7).getStringCellValue();
			driver.findElement(By.xpath("//table[@id='ctl00_CP_ContentPage_tbl_DetailsRequired']//input[@id='ctl00_CP_ContentPage_txtCardNumber']")).sendKeys(cardNumber);
			Thread.sleep(2000);
			String midNumber = wb.getSheet("Invoice-Damage").getRow(1).getCell(8).getStringCellValue();
			driver.findElement(By.xpath("//table[@id='ctl00_CP_ContentPage_tbl_DetailsRequired']//input[@id='ctl00_CP_ContentPage_txtMidNumber']")).sendKeys(midNumber);
			Thread.sleep(2000);
			String tidNumber = wb.getSheet("Invoice-Damage").getRow(1).getCell(9).getStringCellValue();
			driver.findElement(By.xpath("//table[@id='ctl00_CP_ContentPage_tbl_DetailsRequired']//input[@id='ctl00_CP_ContentPage_txtTIDNumber']")).sendKeys(tidNumber);
			Thread.sleep(2000);
			String approvalNumber = wb.getSheet("Invoice-Damage").getRow(1).getCell(10).getStringCellValue();
			driver.findElement(By.xpath("//table[@id='ctl00_CP_ContentPage_tbl_DetailsRequired']//input[@id='ctl00_CP_ContentPage_txtApprovalNumber']")).sendKeys(approvalNumber);
			Thread.sleep(2000);
			
			String amount = driver.findElement(By.id("ctl00_CP_ContentPage_lblTotalDamageAmt")).getText();
			driver.findElement(By.xpath("//table[@id='ctl00_CP_ContentPage_tbl_DetailsRequired']//input[@id='ctl00_CP_ContentPage_txtChargingAmount']")).sendKeys(amount);
			Thread.sleep(2000);
			WebElement damageType = driver.findElement(By.xpath("//table[@id='ctl00_CP_ContentPage_tbl_DetailsRequired']//select[@id='ctl00_CP_ContentPage_ddlDamageType']"));
			Select selDamage = new Select(damageType);
			selDamage.selectByIndex(1);
			Thread.sleep(2000);
			WebElement actualCarNum = driver.findElement(By.xpath("//table[@id='ctl00_CP_ContentPage_tbl_DetailsRequired']//select[@id='ctl00_CP_ContentPage_ddlCarNo']"));
			Select selCarNum = new Select(actualCarNum);
			selCarNum.selectByIndex(4);
			Thread.sleep(2000);

			String wallLocation = "D:\\ShareEclipse\\Myles\\Wall\\wall.jpg";
			driver.findElement(By.xpath("//table[@id='ctl00_CP_ContentPage_tbl_DetailsRequired']//input[@id='ctl00_CP_ContentPage_DamageFileUpload']")).sendKeys(wallLocation);
			Thread.sleep(3000);
		    driver.findElement(By.xpath("//input[@id='ctl00_CP_ContentPage_btnDamageInvoice' and @type='submit']")).click();
			String ConfirmationMsg = driver.findElement(By.id("ctl00_CP_ContentPage_lbl_messageDamage")).getText();
			System.out.println(ConfirmationMsg);
		
		
	//	}
		
//		catch (UnhandledAlertException f) {
//			
//			DesiredCapabilities dc = new DesiredCapabilities();
//			dc.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
//			driver = new ChromeDriver(dc);
//		    try {
//		        Alert alert = driver.switchTo().alert();
//		        String alertText = alert.getText();
//		        System.out.println("Alert data: " + alertText);
//		        alert.accept();
//		    } catch (NoAlertPresentException e) {
//		        e.printStackTrace();
//		    }
//		   }
		
		
		
		   
		   }
	   
	   
	   
	   
	   
	   public void PromotionMaster() throws InterruptedException, InvalidFormatException, FileNotFoundException, IOException{
		   
		   System.setProperty("webdriver.chrome.driver", "./ExeFolder/chromedriver.exe");
			WebDriver driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			driver.manage().deleteAllCookies();
			driver.get("http://10.90.90.32:8080/");
			Workbook wb = WorkbookFactory.create(new FileInputStream(new File("./Myles.xlsx")));
			String id = wb.getSheet("PromotionMaster").getRow(1).getCell(1).getStringCellValue();
			driver.findElement(By.id("loginID")).sendKeys(id);
			String pwd = wb.getSheet("PromotionMaster").getRow(1).getCell(2).getStringCellValue();
			driver.findElement(By.name("pwd")).sendKeys(pwd);
			driver.findElement(By.name("btnSubmit")).click();
			Thread.sleep(1000);
			Actions act = new Actions(driver);
			WebElement dsDrpDwn = driver.findElement(By.xpath("//td[@id='ctl00_CP_Menu_menun4']//a[contains(text(),'Masters')]"));
			WebElement opnDS = driver.findElement(By.xpath("//a[contains(text(),'Promotion Master')]"));
		    act.moveToElement(dsDrpDwn).perform();
		    act.click(opnDS).perform();
		    Thread.sleep(1000);
		    driver.findElement(By.id("ctl00_CP_ContentPage_txtPromotionCode")).sendKeys("JP33");
		    driver.findElement(By.xpath("//td[text()='Promotion Description']/..//textarea[@id='ctl00_CP_ContentPage_txtPromotionDescription']")).sendKeys("For testing purpose");
		  
		    String normal = "Normal Coupon";
			String weekday = "Only Weekday";
			String weekend = "Only Weekend";
			String txt = "Only Weekday";
			JavascriptExecutor js = (JavascriptExecutor) driver;
			if (txt.equalsIgnoreCase(normal)) {
				WebElement nc = driver.findElement(By.xpath("//tr[@id='ctl00_CP_ContentPage_trApplicableonWeekend']//tr//input[@id='ctl00_CP_ContentPage_rdoWeekdayCondition_0']"));
				js.executeScript("arguments[0].click();", nc);
				}
			else if (txt.equalsIgnoreCase(weekday)) {
				WebElement wk = driver.findElement(By.xpath("//tr[@id='ctl00_CP_ContentPage_trApplicableonWeekend']//tr//input[@id='ctl00_CP_ContentPage_rdoWeekdayCondition_1']"));
				js.executeScript("arguments[0].click();", wk);
			}
			else if (txt.equalsIgnoreCase(weekend)) {
				WebElement we = driver.findElement(By.xpath("//tr[@id='ctl00_CP_ContentPage_trApplicableonWeekend']//tr//input[@id='ctl00_CP_ContentPage_rdoWeekdayCondition_2']"));
				js.executeScript("arguments[0].click();", we);
			}
		    
			Thread.sleep(2000);
			String disPercent = "Percent Discount";
			String disFlat = "Flat Discount";
			String txt1 = "Flat Discount";
			if (txt1.equalsIgnoreCase(disPercent)) {
				WebElement nc = driver.findElement(By.xpath("//table[@id='ctl00_CP_ContentPage_rdoDiscount']//input[@id='ctl00_CP_ContentPage_rdoDiscount_0']"));
				js.executeScript("arguments[0].click();", nc);
				WebElement percentage = driver.findElement(By.id("ctl00_CP_ContentPage_drpDiscPercent"));
				Select sel = new Select(percentage);
				sel.selectByVisibleText("30");
				
				}
			else if (txt1.equalsIgnoreCase(disFlat)) {
				WebElement wk = driver.findElement(By.xpath("//table[@id='ctl00_CP_ContentPage_rdoDiscount']//input[@id='ctl00_CP_ContentPage_rdoDiscount_1']"));
				js.executeScript("arguments[0].click();", wk);
				driver.findElement(By.xpath("//input[@id='ctl00_CP_ContentPage_txtDiscount' and @class='input']")).sendKeys("500");
			}
		    
					
		    Thread.sleep(5000);
//		    driver.findElement(By.xpath("//td[contains(text(),'Start Date')]/..//input[@id='ctl00_CP_ContentPage_txtStartDate']")).click();
//		    Thread.sleep(2000);
//		    driver.findElement(By.xpath("//td[contains(text(),'Start Date')]/..//input[@id='ctl00_CP_ContentPage_txtStartDate']")).click();
//		   driver.findElement(By.xpath("//div[contains(@id,'ctl00_CP_ContentPage_CalendarExtender_txtStartDate_container')]//div[contains(@title,'October 10, 2018')]")).click();
//		    Thread.sleep(2000);
		    
			
			driver.findElement(By.id("ctl00_CP_ContentPage_txtStartDate")).sendKeys("02-11-2019");
			Thread.sleep(3000);
			driver.findElement(By.id("ctl00_CP_ContentPage_txtExpiryDate")).sendKeys("02-15-2019");
			Thread.sleep(2000);
			
			
			
				   
//		    driver.findElement(By.xpath("//td[contains(text(),'Expiry Date')]/..//input[@id='ctl00_CP_ContentPage_txtExpiryDate']")).click();
//		    Thread.sleep(2000);
//		    driver.findElement(By.xpath("//div[contains(@id,'ctl00_CP_ContentPage_CalendarExtender_txtExpiryDate_container')]//div[contains(@title,'October 15, 2018')]")).click();
//		    Thread.sleep(2000);	   
		    driver.findElement(By.xpath("//td[text()='Max Count']/..//input[@id='ctl00_CP_ContentPage_txtMaxCount']")).sendKeys("5");	   
				   
			WebElement model = driver.findElement(By.id("ctl00_CP_ContentPage_lstModel"));
			Select mSel = new Select(model);
			for (int i = 0; i < 5; i++) 
			{
				mSel.selectByIndex(i);
				
			}
			
			driver.findElement(By.xpath("//input[@id='ctl00_CP_ContentPage_txtMinEligibleAmt']")).sendKeys("500");
			driver.findElement(By.xpath("//input[@id='ctl00_CP_ContentPage_txtOccurance']")).sendKeys("1");
			driver.findElement(By.xpath("//input[@id='ctl00_CP_ContentPage_txtPickup']")).click();
//			driver.findElement(By.xpath("//div[contains(@id,'ctl00_CP_ContentPage_CalendarExtender_txtPickup_container')]//div[contains(@title,'February 10, 2019')]")).click();
//			driver.findElement(By.xpath("//input[@id='ctl00_CP_ContentPage_txtDropOff']")).click();
//			driver.findElement(By.xpath("//div[contains(@id,'ctl00_CP_ContentPage_CalendarExtender_txtDropOff_container')]//div[contains(@title,'February 15, 2019')]")).click();
			
			driver.findElement(By.id("ctl00_CP_ContentPage_txtPickup")).sendKeys("02-11-2019");
			Thread.sleep(3000);
			driver.findElement(By.id("ctl00_CP_ContentPage_txtDropOff")).sendKeys("02-15-2019");
			Thread.sleep(2000);
			
			
			driver.findElement(By.xpath("//td[text()='Package Types']/..//input[@id='ctl00_CP_ContentPage_chkDaily']")).click();
			Thread.sleep(3000);
			WebElement pkgKms = driver.findElement(By.id("ctl00_CP_ContentPage_lstPkgKms"));
			Select pkgKmsSel = new Select(pkgKms);
			for (int i = 6; i < 9; i++) 
			{
				pkgKmsSel.selectByIndex(i);
				
			}

			WebElement source = driver.findElement(By.id("ctl00_CP_ContentPage_lstSource"));
			Select sourceSel = new Select(source);
			for (int i = 0; i < 4; i++) 
			{
				sourceSel.selectByIndex(i);
				
			}	   
				   
			Thread.sleep(3000);	   
			driver.findElement(By.id("ctl00_CP_ContentPage_chkShowToCustomer")).click();
			driver.findElement(By.xpath("//input[@id='ctl00_CP_ContentPage_txtTitle']")).sendKeys("Jp-Test-Code");
			
			WebElement img=driver.findElement(By.id("ctl00_CP_ContentPage_ddlimage"));
			Select img1= new Select(source);
			img1.selectByIndex(1);
			Thread.sleep(2000);	
			driver.findElement(By.id("ctl00_CP_ContentPage_btn_Submit")).click();
		   
		   
	   }
	   
	   
	   
      public void DSClose() throws InterruptedException, InvalidFormatException, FileNotFoundException, IOException{
		   
		    System.setProperty("webdriver.chrome.driver", "./ExeFolder/chromedriver.exe");
			WebDriver driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			driver.get("http://10.90.90.32:8080");
			Workbook wb = WorkbookFactory.create(new FileInputStream(new File("./Myles.xlsx"))); 
			String id = wb.getSheet("Insta-Close").getRow(1).getCell(1).getStringCellValue();
			driver.findElement(By.id("loginID")).sendKeys(id);
			String pwd = wb.getSheet("Insta-Close").getRow(1).getCell(2).getStringCellValue();
			driver.findElement(By.name("pwd")).sendKeys(pwd);
			driver.findElement(By.name("btnSubmit")).click();
			Thread.sleep(1000);
			Actions act = new Actions(driver);
			WebElement invoiceDrpDwn = driver.findElement(By.xpath("//td[@id='ctl00_CP_Menu_menun2']//a[contains(text(),'Invoice')]"));
			WebElement closeDs = driver.findElement(By.xpath("//a[contains(text(),'Close Dutyslip')]"));
		    act.moveToElement(invoiceDrpDwn).perform();
		    act.click(closeDs).perform();
		    Thread.sleep(3000);
		    
		    
			String bookingID = wb.getSheet("Insta-Close").getRow(1).getCell(3).getStringCellValue();
		    driver.findElement(By.xpath("//input[@id='ctl00_CP_ContentPage_txtBookingID']")).sendKeys(bookingID);
		    driver.findElement(By.xpath("//input[@id='ctl00_CP_ContentPage_btn_search']")).click();
			Thread.sleep(2000);
			
			driver.findElement(By.id("ctl00_CP_ContentPage_grv_CloseSearch_ctl02_hyperlnkCloseDayWise")).click();
			Thread.sleep(2000);
			
			
		String scheduleDateIn=driver.findElement(By.xpath("//input[@id='ctl00_CP_ContentPage_txtDateIn']")).getAttribute("value").trim();
		System.out.println(scheduleDateIn);
		driver.findElement(By.xpath("//input[@id='ctl00_CP_ContentPage_txtActualDateIn']")).clear();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='ctl00_CP_ContentPage_txtActualDateIn']")).sendKeys(scheduleDateIn);
		WebDriverWait wait = new WebDriverWait(driver, 40);
		
//		try{
//				
//		wait.until(ExpectedConditions.alertIsPresent());
//		Alert alert=driver.switchTo().alert();
//		
//		if(alert.accept()){
//		    alert.accept();
//           }
//			catch(NoAlertPresentException alt){
//				System.out.println("No alert present");
//			}
		String kmout=driver.findElement(By.xpath("//span[@id='ctl00_CP_ContentPage_lblKmOut']")).getText().trim();
		int kmin=Integer.parseInt(kmout);
		int kminput= kmin+100;
//		String strngInputKM = Integer.toString(kminput);
		String strngInputKM=String.valueOf(kminput);
		
		ReadWriteExcel("./Myles.xlsx","Insta-Close",1,4,strngInputKM);
		String inputkm = wb.getSheet("Insta-Close").getRow(1).getCell(4).getStringCellValue();
		driver.findElement(By.xpath("//input[@id='ctl00_CP_ContentPage_txtKmIn']")).sendKeys(inputkm );
		System.out.println(inputkm);
			
		driver.findElement(By.xpath("//input[@id='ctl00_CP_ContentPage_txtFuelCharge']")).sendKeys("10" );
		driver.findElement(By.xpath("//input[@id='ctl00_CP_ContentPage_txtPdTicket']")).sendKeys("10" );
		driver.findElement(By.xpath("//textarea[@id='ctl00_CP_ContentPage_txtRemarks']")).sendKeys("Test" );
		
		driver.findElement(By.id("ctl00_CP_ContentPage_btnClose")).click();
		Thread.sleep(3000);
		
		
		try{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_CP_ContentPage_lbl_message")));
			driver.close();
           }
			catch(NoAlertPresentException alt){
				System.out.println("No alert present");
				driver.close();
			}
		
		
		    }
   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   }
	   
	   
	   
	   
	   
	   
	   
		 	
		
		

	



	
	

