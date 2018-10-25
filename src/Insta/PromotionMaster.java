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
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.ClickAction;
import org.openqa.selenium.support.ui.Select;

import com.gargoylesoftware.css.parser.selector.Selector.SelectorType;

public class PromotionMaster {
	static WebDriver driver;
	
	public static void main(String[] args) throws InterruptedException, EncryptedDocumentException, InvalidFormatException, FileNotFoundException, IOException {
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
	    driver.findElement(By.id("ctl00_CP_ContentPage_txtPromotionCode")).sendKeys("AJ33");
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
	    
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("//td[contains(text(),'Start Date')]/..//input[@id='ctl00_CP_ContentPage_txtStartDate']")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("//td[contains(text(),'Start Date')]/..//input[@id='ctl00_CP_ContentPage_txtStartDate']")).click();
	   driver.findElement(By.xpath("//div[contains(@id,'ctl00_CP_ContentPage_CalendarExtender_txtStartDate_container')]//div[contains(@title,'October 10, 2018')]")).click();
	    Thread.sleep(2000);
	    
			   
	    driver.findElement(By.xpath("//td[contains(text(),'Expiry Date')]/..//input[@id='ctl00_CP_ContentPage_txtExpiryDate']")).click();
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("//div[contains(@id,'ctl00_CP_ContentPage_CalendarExtender_txtExpiryDate_container')]//div[contains(@title,'October 15, 2018')]")).click();
	    Thread.sleep(2000);	   
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
		driver.findElement(By.xpath("//div[contains(@id,'ctl00_CP_ContentPage_CalendarExtender_txtPickup_container')]//div[contains(@title,'October 11, 2018')]")).click();
		driver.findElement(By.xpath("//input[@id='ctl00_CP_ContentPage_txtDropOff']")).click();
		driver.findElement(By.xpath("//div[contains(@id,'ctl00_CP_ContentPage_CalendarExtender_txtDropOff_container')]//div[contains(@title,'October 12, 2018')]")).click();
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
		driver.findElement(By.xpath("//input[@id='ctl00_CP_ContentPage_txtTitle']")).sendKeys("Aj-Test-Code");
		driver.findElement(By.id("ctl00_CP_ContentPage_btn_Submit")).click();
			   
			   
			   
			   
			   
			   
			   
			   
			   
			   
			   
			   
			   
			   
			   
			   
			   
			   
			   
			   
	}
	
	
}
