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

	import org.apache.poi.EncryptedDocumentException;
	import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
	import org.apache.poi.ss.usermodel.Workbook;
	import org.apache.poi.ss.usermodel.WorkbookFactory;
	import org.openqa.selenium.Alert;
	import org.openqa.selenium.By;
	import org.openqa.selenium.ElementNotVisibleException;
	import org.openqa.selenium.JavascriptExecutor;
	import org.openqa.selenium.NoSuchElementException;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.interactions.Actions;
	import org.openqa.selenium.support.ui.Select;

	public class InstaExtension {
		static String[] arr = {"Months", "January","February","March","April", "May", "June", "July", "August","September", "October", "November", "December"};
		public static void main(String[] args) throws EncryptedDocumentException, InvalidFormatException, FileNotFoundException, IOException, InterruptedException {
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
				
				 DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				 Date date = new Date();
				 String date1= dateFormat.format(date);
				 String[] arrSplit = date1.split("/");
				 System.out.println("System date: " +arrSplit[0]);
				 int sysMnth =  Integer.parseInt(arrSplit[1]);
				 
				String ExtensionDropdateTxt = driver.findElement(By.xpath("(//table[@id='ctl00_CP_ContentPage_grid_BookInfo']//td)[7]")).getText();
				String[] arrExtension = ExtensionDropdateTxt.split("/");
				System.out.println(arrExtension[0]+"asdasdf");
				int arrInt = Integer.parseInt(arrExtension[0]);    // 11(int)
				//int Position = 1;
				driver.findElement(By.id("ctl00_CP_ContentPage_txt_extendedDate")).click();
				String monthNum = wb.getSheet("Insta-Extension").getRow(1).getCell(5).getStringCellValue();
				System.out.println("2");
				int positionOfMonth = Integer.parseInt(monthNum);  // 12 (int)
				String month = arr[positionOfMonth];
				System.out.println(month);
				//WebElement extensionUpto = driver.findElement(By.xpath("//input[@id='ctl00_CP_ContentPage_txt_extendedDate']"));
				System.out.println("3");
				WebElement nxtBtn = driver.findElement(By.id("ctl00_CP_ContentPage_CalendarExtender1_nextArrow"));
				System.out.println("5");
				
				if (positionOfMonth < (sysMnth))   //arrInt = 10     position of month = 10 
				{
					System.out.println("positionOfMonth > (sysMnth)");
					System.out.println("Cannot select extended date less than drop off  date!");
					driver.close();
				}
				else if (positionOfMonth > (sysMnth)) 
				{
					System.out.println(month+" month is greater");
					for (int i = 0; i < arr.length; i++)
					{
						System.out.println("positionOfMonth > (sysMnth)");
						nxtBtn.click();
						String calenderExtUpto = driver.findElement(By.id("ctl00_CP_ContentPage_CalendarExtender1_title")).getText();
						String[] calExtUptoArr = calenderExtUpto.split(",");
						if(calExtUptoArr[0].equalsIgnoreCase(month))
						{
							String dateforExtention = wb.getSheet("Insta-Extension").getRow(1).getCell(6).getStringCellValue();
				//****  //tbody//div[text()='5']     **** //div[text()='"+dateforExtention+"' and @class='ajax__calendar_day']
							driver.findElement(By.xpath("//tbody//div[text()='"+dateforExtention+"'] ")).click();
							break;
						}
						
					}
					
					
				}
				else if (positionOfMonth == (sysMnth)) 
				{
					for (int i = 0; i < arr.length; i++)
					{
						System.out.println("equal");
						//nxtBtn.click();
//						String calenderExtUpto = driver.findElement(By.id("ctl00_CP_ContentPage_CalendarExtender1_title")).getText();
//						String[] calExtUptoArr = calenderExtUpto.split(",");
//						if(calExtUptoArr[0].equalsIgnoreCase(month))
//						{
							String dateforExtention = wb.getSheet("Insta-Extension").getRow(1).getCell(6).getStringCellValue();
							System.out.println(dateforExtention);
				//****  //tbody//div[text()='5']     **** //div[text()='"+dateforExtention+"' and @class='ajax__calendar_day']
							driver.findElement(By.xpath("//tbody//div[text()='"+dateforExtention+"'] ")).click();
							break;
//						}
						
					}
				}
				
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
//				
//				 DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//				 Date date = new Date();
//				 String date1= dateFormat.format(date);
//				 String[] arrSplit = date1.split("/");
//				 System.out.println(arrSplit[0]);
				
				 char s = arrSplit[0].charAt(0);
				 String sChar = Character.toString(s);
					if(sChar.equals("0"))
					{
						driver.findElement(By.id("ctl00_CP_ContentPage_txtbxrequesttime")).click();
						driver.findElement(By.xpath("//b[text()='Request Time']/..//following-sibling::td//div[text()='"+arrSplit[0].charAt(1)+"']")).click();
						WebElement reqestPickupHour = driver.findElement(By.id("ctl00_CP_ContentPage_ddlrequesthour"));
						//String excelReqPickUpHour = wb.getSheet("Insta-Extension").getRow(1).getCell(10).getStringCellValue();
						Select selReqHour = new Select(reqestPickupHour);
						selReqHour.selectByVisibleText("19");
					}
					else {
				driver.findElement(By.id("ctl00_CP_ContentPage_txtbxrequesttime")).click();
				driver.findElement(By.xpath("//b[text()='Request Time']/..//following-sibling::td//div[text()='"+arrSplit[0]+"']")).click();
				WebElement reqestPickupHour = driver.findElement(By.id("ctl00_CP_ContentPage_ddlrequesthour"));
				//String excelReqPickUpHour = wb.getSheet("Insta-Extension").getRow(1).getCell(10).getStringCellValue();
				Select selReqHour = new Select(reqestPickupHour);
				selReqHour.selectByVisibleText("19");
					}
				Thread.sleep(2000);
				
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


}
