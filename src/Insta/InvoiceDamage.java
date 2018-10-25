package Insta;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class InvoiceDamage {
	public static void main(String[] args) throws InterruptedException, EncryptedDocumentException, InvalidFormatException, FileNotFoundException, IOException {
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
		WebElement damageInvoice = driver.findElement(By.xpath("//a[contains(text(),'Damage Invoice')]"));
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
//		String damageAmount = wb.getSheet("Invoice-Damage").getRow(1).getCell(10).getStringCellValue();
//		driver.findElement(By.xpath("//table[@id='ctl00_CP_ContentPage_tbl_Details']//input[@id='ctl00_CP_ContentPage_txtDamageCharges']")).sendKeys(damageAmount);
//		driver.findElement(By.xpath("//table[@id='ctl00_CP_ContentPage_tbl_Details']//textarea[@id='ctl00_CP_ContentPage_txtRemarks']")).click();
//		Thread.sleep(2000);
//		String remark = wb.getSheet("Invoice-Damage").getRow(1).getCell(11).getStringCellValue();
//		driver.findElement(By.xpath("//table[@id='ctl00_CP_ContentPage_tbl_Details']//textarea[@id='ctl00_CP_ContentPage_txtRemarks']")).sendKeys(remark);
//		Thread.sleep(2000);
		String wallLocation = "D:\\ShareEclipse\\Myles\\Wall\\wall.jpg";
		driver.findElement(By.xpath("//table[@id='ctl00_CP_ContentPage_tbl_DetailsRequired']//input[@id='ctl00_CP_ContentPage_DamageFileUpload']")).sendKeys(wallLocation);
		Thread.sleep(3000);
	    driver.findElement(By.xpath("//input[@id='ctl00_CP_ContentPage_btnDamageInvoice' and @type='submit']")).click();
		String ConfirmationMsg = driver.findElement(By.id("ctl00_CP_ContentPage_lbl_messageDamage")).getText();
		System.out.println(ConfirmationMsg);
		
		
		
		
		
	}

}
