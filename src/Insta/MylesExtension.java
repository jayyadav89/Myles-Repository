package Insta;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MylesExtension {
	
	static WebDriver driver;
	static Workbook wb;

	public static void main(String[] args) throws EncryptedDocumentException, InvalidFormatException, FileNotFoundException, IOException, InterruptedException {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://qa2.mylescars.com/");
		Scanner scan = new Scanner(System.in);
		wb = WorkbookFactory.create(new FileInputStream(new File("./Myles.xlsx"))); 

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
		Thread.sleep(3000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();",driver.findElement(By.cssSelector("li#onRides")));
		
		WebDriverWait wait = new WebDriverWait(driver, 40);
//		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//li[text()='Ongoing Rides']")));
//		driver.findElement(By.xpath("//li[text()='Ongoing Rides']")).click();
		Thread.sleep(3000);
		int bookID =(int) wb.getSheet("Myles-Extension").getRow(1).getCell(3).getNumericCellValue();
		String extendBtn = "//span[contains(text(),'( Booking- "+bookID+" )')]/../../../following-sibling::div//button";

		js.executeScript("arguments[0].click();", driver.findElement(By.xpath(extendBtn)));
		
		driver.findElement(By.xpath("//input[@id='datepicker_"+bookID+"']")).click();
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
				Thread.sleep(3000);
				String exp = "Extension Charges";
				String act = driver.findElement(By.xpath("//div[@id='cal_success_ext_"+bookID+"']//span")).getText();
				System.out.println(act);
				if (act.contains(exp)) 
				{
				System.out.println("12234");
				js.executeScript("arguments[0].click();", driver.findElement(By.id("ext_update_but_"+bookID+"")));
				System.out.println("aaaaa");
				 ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
				    driver.switchTo().window(tabs2.get(1));
				    String currentUrl = driver.getCurrentUrl();
				    System.out.println(currentUrl);
				    String newUrl = currentUrl.replaceAll("https://www", "https://qa2");
					System.out.println(newUrl);
					driver.navigate().to(newUrl);
					Thread.sleep(3000);
					 String CreditDebit =driver.findElement(By.xpath("//a[@href='#CreditDebit']")).getText();
				
				     String wallet =driver.findElement(By.xpath("//a[@href='#Wallet']")).getText();
			
					 String netBanking =driver.findElement(By.xpath("//a[@href='#NetBanking']")).getText();
				
					 String payment = wb.getSheet("Myles-Extension").getRow(1).getCell(7).getStringCellValue();
					 Thread.sleep(2000);

					 if (CreditDebit.equalsIgnoreCase(payment))
					 {
						WebElement cardNum = driver.findElement(By.id("credit_card_number"));
						Thread.sleep(2000);
						//WebDriverWait wait = new WebDriverWait(driver, 40);
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
						Thread.sleep(4000);
						driver.findElement(By.id("OTP")).sendKeys("123123");
						driver.findElement(By.name("proceed")).click();
					
			//	pay();
				}
				else {
					String errorTxt = driver.findElement(By.xpath("//div[@id='cal_error_ext_"+bookID+"']//span")).getText();
					System.out.println(errorTxt+"igytrstea");
				}
				break;
				
			    }
			    else
			    {
				driver.findElement(By.xpath("//span[text()='Next']")).click();
				String nxtMonth = driver.findElement(By.className("ui-datepicker-month")).getText();
				System.out.println(nxtMonth);
				if (nxtMonth.equalsIgnoreCase(exlMonth)) 
				{
					driver.findElement(By.linkText(exlDate)).click();
					Thread.sleep(3000);
					String exp1 = "Extension Charges";
					String act1 = driver.findElement(By.xpath("//div[@id='cal_success_ext_"+bookID+"']//span")).getText();
					System.out.println(act1);
					if (act1.contains(exp1))  {
						System.out.println("32543654747");
						js.executeScript("arguments[0].click();", driver.findElement(By.id("ext_update_but_"+bookID+"")));
						System.out.println("qqqqq");
						//pay();
						
						 ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
						    driver.switchTo().window(tabs2.get(1));
						    String currentUrl = driver.getCurrentUrl();
						    System.out.println(currentUrl);
						    String newUrl = currentUrl.replaceAll("https://www", "https://qa2");
							System.out.println(newUrl);
							driver.navigate().to(newUrl);
							Thread.sleep(3000);
							 String CreditDebit =driver.findElement(By.xpath("//a[@href='#CreditDebit']")).getText();
						
						     String wallet =driver.findElement(By.xpath("//a[@href='#Wallet']")).getText();
					
							 String netBanking =driver.findElement(By.xpath("//a[@href='#NetBanking']")).getText();
						
							 String payment = wb.getSheet("Myles-Extension").getRow(1).getCell(7).getStringCellValue();
							 Thread.sleep(2000);

							 if (CreditDebit.equalsIgnoreCase(payment))
							 {
								WebElement cardNum = driver.findElement(By.id("credit_card_number"));
								Thread.sleep(2000);
								//WebDriverWait wait = new WebDriverWait(driver, 40);
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
								Thread.sleep(4000);
								driver.findElement(By.id("OTP")).sendKeys("123123");
								driver.findElement(By.name("proceed")).click();
							
                             }
					else {
						String errorTxt = driver.findElement(By.xpath("//div[@id='cal_error_ext_"+bookID+"']//span")).getText();
						System.out.println(errorTxt+"igytrstea");
					}
					break;
					
				}
				}
			    }
			    }
			    }
			    }
			    
		catch (NoSuchElementException e) {
			e.printStackTrace();
			System.out.println("error");
		}
		
		Thread.sleep(3000);
		System.out.println("wwww");
	   }
	 }
			

	