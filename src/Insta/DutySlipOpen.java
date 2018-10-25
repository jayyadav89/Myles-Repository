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


public class DutySlipOpen {
	public static void main(String[] args) throws InterruptedException, EncryptedDocumentException, InvalidFormatException, FileNotFoundException, IOException {
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
	    driver.findElement(By.id("ctl00_CP_ContentPage_txt_PAddress")).sendKeys("asd");
	    driver.findElement(By.id("ctl00_CP_ContentPage_txt_LAddress")).sendKeys("qwe");
	    WebElement stateDrpDwn = driver.findElement(By.id("ctl00_CP_ContentPage_ddlAllocationState"));
	    Select sel = new Select(stateDrpDwn);
	    sel.selectByValue("7");
	    Thread.sleep(1000);
	    
	    WebElement cityDrpDwn = driver.findElement(By.id("ctl00_CP_ContentPage_ddlAllocationCity"));
	    Select sel1 = new Select(cityDrpDwn);
	    sel1.selectByValue("50");
	    Thread.sleep(1000);
	    
	    driver.findElement(By.id("ctl00_CP_ContentPage_txtAgreementNo")).sendKeys("1234");
	    WebElement preAuthId = driver.findElement(By.id("ctl00_CP_ContentPage_txt_TranID"));
	    boolean flag = preAuthId.isEnabled();
	   	    if(flag)
	    {
	   	 /**********************************************************enter unique preAuthID for each booking id******************************************************/   	
	   	    	String preId = wb.getSheet("Insta-Open").getRow(1).getCell(4).getStringCellValue();
	   	    	preAuthId.sendKeys(preId);
	    }
	    else
	    {
	    	System.out.println("Trip Insurence");
	    }
	    
	   	 driver.findElement(By.id("ctl00_CP_ContentPage_btn_Cont")).click();
	   	 String opnCnfrmTxt = driver.findElement(By.id("ctl00_CP_ContentPage_lbl_message")).getText();
	     System.out.println(opnCnfrmTxt);
	}


}
