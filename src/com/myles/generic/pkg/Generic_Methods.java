package com.myles.generic.pkg;



	import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
	import java.util.Iterator;
	import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.Random;
	import java.util.Set;
	import java.util.concurrent.TimeUnit;

	import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
	import org.openqa.selenium.OutputType;
	import org.openqa.selenium.TakesScreenshot;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.firefox.FirefoxDriver;
	import org.openqa.selenium.ie.InternetExplorerDriver;
	import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
	import org.testng.Reporter;

	    public class Generic_Methods {
	     
	    	
	    	public static WebDriver driver;
		 
	     public static void launchBrowser(String browserName){
	    	 WebDriver DriverObj=null;
			if(browserName.equalsIgnoreCase("FF")){
				DriverObj=new FirefoxDriver();
				//return driver;
			}else if(browserName.equalsIgnoreCase("CH")){
				  System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
				  DriverObj=new ChromeDriver();
			
				//return driver;
			}else if(browserName.equalsIgnoreCase("IE")){
				  System.setProperty("webdriver.ie.driver", "drivers\\IEDriverServer.exe");
				  DriverObj=new InternetExplorerDriver();
				//return driver;
				 }
			DriverObj.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			
			
//			driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
//			driver.manage().timeouts().setScriptTimeout(90, TimeUnit.SECONDS);
			DriverObj.manage().window().maximize();
			
			driver= DriverObj;       
			//return driver;  
			        
	        }
	     
		
		   public static void fn_LandingHome() throws IOException{
			   Properties obj = new Properties();   
		    	  //Create Object of FileInputStream Class. Pass file path.
		    	  FileInputStream objfile = new FileInputStream(System.getProperty("user.dir")+"\\src\\ObjectRepo\\objects.properties");
		    	  //Pass object reference objfile to load method of Properties object.
		    	  obj.load(objfile);
			    driver.get(obj.getProperty("URL"));
			    driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
				driver.manage().timeouts().pageLoadTimeout(300, TimeUnit.SECONDS);
	//			driver.manage().timeouts().setScriptTimeout(180, TimeUnit.SECONDS);
	         }
		   
	     
		   
		  public static void fn_Click(WebElement ElementToClick){ 
	//		  driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
			    try{
			     WebDriverWait wait = new WebDriverWait(driver, 60);
			     wait.until(ExpectedConditions.visibilityOf(ElementToClick));
			     ElementToClick.click();
			    }
			    catch(ElementNotVisibleException e){
			    	driver.navigate().refresh();
			    	ElementToClick.click();
			    }
		   }
		   
		   
		  
		  public static void fn_Submit(WebElement ElementToClick){ 
				//		  driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
						    try{
						     WebDriverWait wait = new WebDriverWait(driver, 15);
						     wait.until(ExpectedConditions.visibilityOf(ElementToClick));
						     ElementToClick.click();
						    }
						    catch(ElementNotVisibleException e){
						    	driver.navigate().refresh();
						    	ElementToClick.submit();
						    }
					   } 
		  
		  
		  
		  public static void fn_SelectbyIndex(WebElement element,int index){
			Select select=new Select(element);
			select.selectByIndex(index);
		  }
		  
		   
		  
		   public static void fn_Input(WebElement ElementToInput, String value){
			   ElementToInput.sendKeys(value);
			 
		   }
		   
		   
		   public static void fn_WiatUntilVisible(WebElement ElementToVisible){ 
		     try{
		     WebDriverWait wait = new WebDriverWait(driver, 15);
		     wait.until(ExpectedConditions.visibilityOf(ElementToVisible));
		     }
		     catch(ElementNotVisibleException e){
		    	 driver.navigate().refresh(); 
		     }
		   }
		 
		   
		   public static void fn_ScrollDown100() throws IOException  {
				 JavascriptExecutor scroll = (JavascriptExecutor)driver;
			      scroll.executeScript("window.scrollBy(0,100)", "");
			
			      }
		   
		   public static void fn_ScrollDown250() throws IOException  {
				 JavascriptExecutor scroll = (JavascriptExecutor)driver;
			      scroll.executeScript("window.scrollBy(0,250)", "");
			
			      }
		   
		   public static void fn_ScrollUp250() throws IOException  {
				 JavascriptExecutor scroll = (JavascriptExecutor)driver;
			      scroll.executeScript("window.scrollBy(0,-250)", "");
			
			      } 
		   
		   
		    public static void fn_ScrollDown500() throws IOException  {
				 JavascriptExecutor scroll = (JavascriptExecutor)driver;
			      scroll.executeScript("window.scrollBy(0,500)", "");
			
			      }
		    
		    public static void fn_ScrollDown1000() throws IOException  {
				 JavascriptExecutor scroll = (JavascriptExecutor)driver;
			      scroll.executeScript("window.scrollBy(0,1000)", "");
			
			      }
		   
		   
		    public static void fn_ScrollUP500() throws IOException  {
				 JavascriptExecutor scroll = (JavascriptExecutor)driver;
			      scroll.executeScript("window.scrollBy(0,-500)", "");
			
			      }
		   
		    public static void fn_ScrollUP1500() throws IOException  {
				 JavascriptExecutor scroll = (JavascriptExecutor)driver;
			      scroll.executeScript("window.scrollBy(0,-1500)", "");
			
			      }
		   
		    public static void TakesScreenShot() throws IOException{ 
			     File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
					FileUtils.copyFile(scrFile, new File("C:/Users/jyadav/LTE Workspace/LTE_Project/Issues/screenshot.png"));
				 } 
				    
				    
		     public void WindowHandling() throws InterruptedException{
			    	// It will return the parent window name as a String
			    	String parent=driver.getWindowHandle();
			    	 
		    	    // This will return the number of windows opened by Webdriver and will return Set of Strings
			    	Set<String>s1=driver.getWindowHandles();
			    	 
			    	// Now we will iterate using Iterator
			    	Iterator<String> I1= s1.iterator();
			    	   while(I1.hasNext())
			    	    {
			    	     String child_window=I1.next();
			        // Here we will compare if parent window is not equal to child window then we will close
			    	 if(!parent.equals(child_window))
			    	{
			    	driver.switchTo().window(child_window);
			    	  System.out.println(driver.switchTo().window(child_window).getTitle());
			    	     driver.close();
			    	}
			    	}
			    	// once all pop up closed now switch to parent window
			    	driver.switchTo().window(parent);
			    	 
			    	}
		      
		      
		      
		      public void ListCollection() throws InterruptedException{
		    	   List<WebElement> options= driver.findElements(By.xpath("//table/tr"));
		             for(int i=0; i<=options.size(); i++){
		    		  String coll =options.get(i).getText();
		    		  WebElement element=options.get(i);
		    		  element.click();
		    	   }
		    	   
		    	    }
		      
		      
		      public static int fn_GetRandomNumber(int upperLimit, int lowerLimit){
		   		int randomindex=(int) (Math.random()* upperLimit+lowerLimit);
		   		return randomindex;
		   	}
		   	
		   
		           
		   	public static void fn_mouseClick(WebElement ElementToMouseOver){
		   		WebDriverWait wait = new WebDriverWait(driver, 30);
			     wait.until(ExpectedConditions.visibilityOf(ElementToMouseOver));
		   		   new Actions(driver).moveToElement(ElementToMouseOver).click().build().perform();
		   		}
		   	
		   	
		   	
		   	public static void fn_mouseOver(WebElement ElementToMouseOver){
		   		WebDriverWait wait = new WebDriverWait(driver, 30);
			     wait.until(ExpectedConditions.visibilityOf(ElementToMouseOver));
		   		   new Actions(driver).moveToElement(ElementToMouseOver).build().perform();
		   		}
		   	
		   	
		   	
		   	
		   	
		   	
		   	public static void fn_Mandatoryalert() throws InterruptedException{
		//   		String text=ExpectedText.trim();
		//   		System.out.println(text);
		   		
	//	   		String alerttext=driver.findElement(By.xpath("//div[@class='jqimessage']")).getText().trim();
		//   	     System.out.println(alerttext);
		//   		 Assert.assertTrue(alerttext.equals(text), "Text not matched: Failed");
		   	    WebElement Okbutton=driver.findElement(By.xpath("//button[@name='jqi_state0_buttonOk']"));
		   	    Assert.assertTrue(Okbutton.isDisplayed());
		   	    Thread.sleep(2000);
		   	    Okbutton.click();
		     	}
		   	
		   	
		   	
		   	
		   	public static void fn_AlertHandle(String ExpectedText) throws InterruptedException{
		   	    String text=ExpectedText.trim();
		   		System.out.println(text);
		   		Thread.sleep(2000);
		   		String alerttext=driver.findElement(By.xpath("//div[@class='jqi ']/form/div/div/div[2]")).getText().trim();
		   	    System.out.println(alerttext);
		   	    Thread.sleep(2000);
		   		Assert.assertTrue(alerttext.equals(text), "Text not matched: Failed");
		   		Thread.sleep(2000);
		   		driver.findElement(By.xpath("//button[@name='jqi_state0_buttonOk']")).click();

		   	}
		   	
		   	
		   	
		   	
		   	public  static void fn_MouseClick(WebElement ElementToClick) {
		   		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		   		new Actions(driver).click(ElementToClick).build().perform();
		   			try{
		   			Thread.sleep(2000);
		   			}catch(Exception e){
		   				
		   			}
		   		}
		   	
		   	public  static void fn_MouseDoubleClick(WebElement ElementToClick) {
		   		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		   		new Actions(driver).doubleClick(ElementToClick).build().perform();
		   			try{
		   			Thread.sleep(2000);
		   			}catch(Exception e){
		   				
		   			}
		   		}
//		   	public  static void fn_Input(WebElement ElementInput, String InputValue){
//		   		InputValue=(String) testData_HM.get(InputValue);
//		   		System.out.println(InputValue);
//		   		ElementInput.sendKeys(InputValue);
//		   	}
		   	

		   public  static void fn_SelectWithText(WebElement FoundElement, String SelectionValue){
		   		List<WebElement> ListItemColl=FoundElement.findElements(By.xpath("//option"));
		   		for(int i=0; i<=ListItemColl.size()-1; i++){
		   			String  ItemText=ListItemColl.get(i).getText();
		   			if(ItemText.trim().equalsIgnoreCase(SelectionValue.trim())){
		   				new Select(FoundElement).selectByIndex(i);
		   				break;
		   			}
		   			}
		     	}
		   
		   
		   
		    public  static void fn_SelectRandomItem(WebElement FoundElement){
			   	List<WebElement> ListItemColl=FoundElement.findElements(By.xpath("//option"));
			   	int ItemCount=ListItemColl.size();
			   	ItemCount=ItemCount-1;
			   	Random rand = new Random();
			   	int  randomindex = rand.nextInt(ItemCount) + 1;
			   	
			   	new Select(FoundElement).selectByIndex(randomindex-1);
			   		
			   }
		   
		   
		    
		    
		   public  static void fn_DragandDrop(WebElement drag,WebElement drop){
		    Actions act=new Actions(driver);
		    act.dragAndDrop(drag, drop).build().perform();
		      }  
		    
		    		    
		   
		   
//		   public  static void verifyUrl(String ExpColumnName, int rowNumber) throws IOException{
//		   	              String ActualURL=driver.getCurrentUrl();
//		   	              String ExpectedValue=(String) testData_HM.get(ExpColumnName);
//		   	              if(ActualURL.equals(ExpectedValue)){
//		   	            	  Reporter.log("URL Verification Passed. Expected URL="+ExpectedValue+" Actual URL-"+ActualURL);
//		   	              }else{
//		   	                  Assert.assertTrue(false, "URL Verification Failed. Expected URL="+ExpectedValue+" Actual URL-"+ActualURL);
//		   	              }
//		   }

//		   public  static void verifyText(WebElement WebObj,String ExpColumnName, int rowNumber) throws IOException{
//		   	
//		       String ActualText=WebObj.getText();
//		       String ExpectedText=(String) testData_HM.get(ExpColumnName);
//		       if(ActualText.equals(ExpectedText)){
//		       	Reporter.log("Text Verification Passed. Expected Text="+ExpectedText+" Actual Text-"+ActualText);
//		           
//		       }else{
//		       	String msg="Text Verification Failed. Expected Text="+ExpectedText+" Actual Text-"+ActualText;
//		       	 Assert.assertTrue(false,msg);
//		   	}
//		   }
	//
//		   public  static String fn_GetText(WebElement WebObj) throws IOException{
//		   	
//		       String ActualText=WebObj.getText();
//		       return ActualText;
//		   }
//		   public  static void verifyText(WebElement WebObj,String ColumnName){
//		   	
//		       	String ActualText=WebObj.getText();
//		           String ExpectedText=(String) testData_HM.get(ColumnName);
//		           if(ActualText.equals(ExpectedText)){
//		           	Reporter.log("Text Verification Passed. Expected Text="+ExpectedText+" Actual Text-"+ActualText);
//		           }else{
//		           	String msg="Text Verification Failed. Expected Text="+ExpectedText+" Actual Text-"+ActualText;
//		           	 Assert.assertTrue(false,msg);
//		          	}
//		   	
//		   }

		   public static void verifyText(String ActualText,String ExpectedText){
		       if(ActualText.trim().equals(ExpectedText.trim())){
		       	Reporter.log("Text Verification Passed. Expected Text="+ExpectedText+" Actual Text-"+ActualText);
		       }else{
		       	String msg="Text Verification Failed. Expected Text="+ExpectedText+" Actual Text-"+ActualText;
		       	 Assert.assertTrue(false,msg);
		      	}

		   }

		   
		   
		   public static void verifyVisibility(WebElement WebObj,String ExpectedText){
			   String ActualText=WebObj.getText().trim();
			   if(ActualText.equalsIgnoreCase(ExpectedText)){
				   Reporter.log("Passed. Expected Text Exist in Actual Text. Expected Text="+ExpectedText+" Actual Text-"+ActualText);
			   }
		   else{
		       	String msg="Failed. Expected Text doesn't Exist in Actual Text. Expected Text="+ExpectedText+" Actual Text-"+ActualText;
		       	Assert.assertTrue(false,msg);
		   }
		   }
	    
		   
		   
		   
		   
		   
//		   String ActualText=WebObj.getText().trim().toUpperCase();
//	       if(ActualText.indexOf(ExpectedText.trim().toUpperCase())>-1){
		   
		   public static void verifyTextExistance(WebElement WebObj,String ExpectedText){
		   	
		   	String ActualText=WebObj.getText().trim();
		       if(ActualText.trim().equals(ExpectedText)){
		       	Reporter.log("Passed. Expected Text Exist in Actual Text. Expected Text="+ExpectedText+" Actual Text-"+ActualText);
		       }else{
		       	String msg="Failed. Expected Text doesn't Exist in Actual Text. Expected Text="+ExpectedText+" Actual Text-"+ActualText;
		       	Assert.assertTrue(false,msg);
		   	}
		     }
		   
		   public static void verifyTextExistance(String ActualText,String ExpectedText){
		       if(ActualText.indexOf(ExpectedText)>-1){
		       	Reporter.log("Passed. Expected Text Exist in Actual Text. Expected Text="+ExpectedText+" Actual Text-"+ActualText);
		       }else{
		       	String msg="Failed. Expected Text doesn't Exist in Actual Text. Expected Text="+ExpectedText+" Actual Text-"+ActualText;
		       	Assert.assertTrue(false,msg);
		   	}
		       
		   }
//		   public  static void fn_SelectedItem(WebElement WebObj,String ExpColumnName, int rowNumber) throws IOException{
//		      String ActualSelectedItem=new Select(WebObj).getFirstSelectedOption().getText().trim();
//		       String ExpectedValue=(String) testData_HM.get(ExpColumnName);
//		       if(ActualSelectedItem.equals(ExpectedValue)){
//		       	Reporter.log("Passed. Right Item is Selected. Expected Text="+ExpectedValue+" Actual Text-"+ActualSelectedItem);
//		       }else{
//		       	String msg="Failed. Wrong Item is Selected. Expected Text="+ExpectedValue+" Actual Text-"+ActualSelectedItem;
//		       	Assert.assertTrue(false,msg);
//		       }
//		   }
	//
//		   public  static void verifyTextBoxValue(WebElement WebObj,String ExpColumnName, int rowNumber) throws IOException{
//		   	   String ActualTextboxValue=WebObj.getAttribute("value").trim();
//		   	    String ExpectedValue=(String) testData_HM.get(ExpColumnName);
//		   	    if(ActualTextboxValue.equals(ExpectedValue)){
//		   	    	Reporter.log("TextBox Value is Verified. Expected Value="+ExpectedValue+" Actual Text-"+ActualTextboxValue);
//		   	    }else{
//		   	    	String msg="TextBox Value is not Verified. Expected Value="+ExpectedValue+" Actual Text-"+ActualTextboxValue;
//		   	    	Assert.assertTrue(false,msg);
//		   	    }
//		   	}

		   	
		   public static void verifyTextBoxValue(WebElement WebObj, String ExpectedValue){
		   	   String ActualTextboxValue=WebObj.getAttribute("value").trim();
		   	    
		   	    if(ActualTextboxValue.equals(ExpectedValue)){
		   	    	Reporter.log("TextBox Value is Verified. Expected Value="+ExpectedValue+" Actual Text-"+ActualTextboxValue);
		   	    }else{
		   	    	String msg="TextBox Value is not Verified. Expected Value="+ExpectedValue+" Actual Text-"+ActualTextboxValue;
		   	    	Assert.assertTrue(false,msg);
		   	    }
		   	}

		   public static void verifyVisible(WebElement WebObj){
		   	   boolean ActualStatus=WebObj.isDisplayed();
		   	   String Text=WebObj.getText();
		   	   if(Text.trim().equalsIgnoreCase("")){
		   		   Text=WebObj.getAttribute("name");
		   		   if(Text.trim().equalsIgnoreCase("")){
		   			   Text=WebObj.getAttribute("id");
		   		   }
		   	   }
		   	   
		   	    if(ActualStatus==true){
		   	    	Reporter.log("Display Verification Passed. "+Text+ "is Displaying.");
		   	    }else{
		   	    	String msg="Display Verification Failed. "+Text+ "is not Displaying.";
		   	    	Assert.assertTrue(false,msg);
		   	    }
		   	}


		   public static void verifyMultipleVisible(WebElement[] WebObjArray){
		   	for(int i=0; i<=WebObjArray.length-1; i++ ){
		   		WebObjArray[i].getClass();
		   		WebElement WebObj=WebObjArray[i];
		   	  verifyVisible(WebObj);
		   	}
		   }

		   public static void verifyEnabled(WebElement WebObj){
		   	   boolean ActualStatus=WebObj.isEnabled();
		   	   String Text=WebObj.getText();
		   	   if(Text.trim().equalsIgnoreCase("")){
		   		   Text=WebObj.getAttribute("name");
		   		   if(Text.trim().equalsIgnoreCase("")){
		   			   Text=WebObj.getAttribute("id");
		   		   }
		   	   } 
		   	   if(ActualStatus==true){
		   		   Reporter.log("Staus Verification Passed. "+Text+ "is enabled");
		   	    }else{
		   	    	String msg="Staus Verification Failed. "+Text+ "is not enabled";
		   	    	Assert.assertTrue(false,msg);
		   	    }
		   	}

//		   public static void verifyMultipleEnabled(WebElement[] WebObjArray){
//		   	for(int i=0; i<=WebObjArray.length; i++ ){
//		   		WebElement WebObj=WebObjArray[i];
//		   	    verifyEnabled(WebObj);
//		   	    
//		   	}	
//		   	 }
//		   
		   
		   
		   
		   
		   
			 
			   
	//	   }  
			   
			   
		   
		   
		   
		   
			   
			   
			   

//		   public  static void verifyItemExist_Listbox(WebElement WebObj,String ExpColumnName, int rowNumber) throws IOException{
//		      List<WebElement> OptionsCOll=new Select(WebObj).getOptions();
//		      String ExpectedValue=(String) testData_HM.get(ExpColumnName);
//		      boolean ItemExist=false;
//		      for(int i=0; i<=OptionsCOll.size()-1;i++){
//		   	   if(OptionsCOll.get(i).getText().trim().equalsIgnoreCase(ExpectedValue)==true){
//		   		   ItemExist=true;
//		   		   break;
//		   	   }
//		   	   
//		      }
//		      Assert.assertTrue(ItemExist,ExpColumnName+" is not existing in Listbox");
//		   }
//		      public  static void verifyAllItems_Listbox(WebElement WebObj,String Delimeter, String ExpColumnName, int rowNumber) throws IOException{
//		   	   List<WebElement> OptionsCOll=new Select(WebObj).getOptions();
//		   	   String ExpectedValues=(String) testData_HM.get(ExpColumnName);
//		   	  String[] ArrExpectedItems= ExpectedValues.split(Delimeter);
//		   	   for(int i=0; i<=OptionsCOll.size()-1;i++){
//		   		   if(OptionsCOll.get(i).getText().trim().equalsIgnoreCase(ArrExpectedItems[i].trim())==true){
//		   			   System.out.println("Passed");
//		   		   }else{
//		   			   System.out.println("Failed");
//		   		   }
//		   			   
//		   	   }
		    
		//   }
		      

//		                  public  static void fn_verifygetAttribute(WebElement WebObj,String ExpColumnName, int rowNumber) throws IOException{
//		               	        String  getAttribute=  WebObj.getAttribute("type").trim();
//		               	        String ExpectedValue=(String) testData_HM.get(ExpColumnName);
//		               		    if(getAttribute.equals(ExpectedValue)){
//		               		  	  System.out.println("Passed");
//		               		    }else{
//		               		  	  System.out.println("Failed");
//		               		    }
//		                  }
		                  
		   
		   
		   
//		          public static void fn_FetchExcelData(int row_num) throws IOException{
////		               	   String WbookPath=TestDataPath;
////		               	   String SheetName=TestDataSheetName; 
////		               	      File FileObj=new File(WbookPath);
//		               	      FileInputStream FIS=new FileInputStream("Automation_TestCases.xlsx");
//		               	      XSSFWorkbook WBookObj=new XSSFWorkbook(FIS);
//		               	      XSSFSheet SheetObj=WBookObj.getSheet("TestData");
//		               	      XSSFRow RowObj=SheetObj.getRow(row_num);
//		               	      
//		               	   DataFormatter formatter = new DataFormatter(Locale.US);
//
//		               	String netpriceobj = formatter.formatCellValue(RowObj.getCell(0)).replaceAll("0", "").trim();
//		               	
//		               	String totalpayablepriceobj = formatter.formatCellValue(RowObj.getCell(1)).replaceAll("0", "").trim();
//		               	  //   String netpriceobj=RowObj.getCell(0).getStringCellValue();
//		               	  //   String totalpayablepriceobj=RowObj.getCell(1).getStringCellValue();
//		               	  
//		               	
//		               	  String netpricereviewpage =driver.findElement(By.id("net_price")).getText().trim();
//		               	  String parts=netpricereviewpage.replaceAll(".", "").replaceAll("0", "").trim();
//		            //   	  String part1 = parts[1].trim();
//		                  Assert.assertTrue(netpriceobj.equals(parts));
//		                  
//		               	  String totalpayablepricereviewpage =driver.findElement(By.id("total_price")).getText().trim();
//		               	  String partpayable=netpricereviewpage.replaceAll(".", "").replaceAll("0", "").trim();
//		            //   	  String part2 = partpayable[1].trim();
//		               	  Assert.assertTrue(totalpayablepriceobj.equals(partpayable));
		            
		               	      
//		               	      int cellCount=FstRowObj.getLastCellNum();
//		               	      Row RowObj=SheetObj.getRow(row_num);
//		               	      for(int i=0;i<=cellCount-1;i++){
//		               	       String ColumnName=FstRowObj.getCell(i, Row.CREATE_NULL_AS_BLANK).getStringCellValue();
//		               	          String reqRowCellValue=RowObj.getCell(i, Row.CREATE_NULL_AS_BLANK).getStringCellValue();
//		               	          if(ColumnName.trim().equalsIgnoreCase("")==false && ColumnName!=null){
//		               	           testData_HM.put(ColumnName,reqRowCellValue); 
		               	  //        }
		               	           
//		              	      }
//		               	  }
		//   }

		       
		       
		       
		       
		       
		       
		      
		       
		       
		       
		       
		       
//		   public  static void Autoit() throws IOException{
//		       
//		   Runtime.getRuntime().exec("G:/Tutorial/AutoItScripts/upload.exe");
//		       
//		   }
		      
	   

	    public void readWriteExcel(int rowcount,int columncount,String filepath,String Sheetname,String expcellval,String statusvalue) throws  IOException {
		//	String FilePath = "D:\\sampledoc.xls";
			try
	        {
	            FileInputStream input= new FileInputStream(filepath);
	            XSSFWorkbook wb=new XSSFWorkbook(input);
	             XSSFSheet sh=wb.getSheet(Sheetname);
	            XSSFRow row=sh.getRow(rowcount);
	           String cellval =row.getCell(columncount).toString();
	           System.out.println(cellval);
	            if(cellval.equalsIgnoreCase(expcellval)){
	        	 FileOutputStream fos=new FileOutputStream(filepath);
	        	 row.createCell(10).setCellValue(statusvalue);  //working
	             	 wb.write(fos);
	                }
	         else{
	    //    	 System.out.println("Test case name not matching");
	        	 row.createCell(10).setCellValue("Test case name not matching");
	         }
	           }
	        catch(Exception e)
	        {
	            System.out.println(e);
	        }
	        }
	
	
	    
	    
	    public void readWriteExcel1(String filepath,String Sheetname,String Testname ,String statusvalue) throws  IOException {
			//	String FilePath = "D:\\sampledoc.xls";
				try
		        {
		            FileInputStream input= new FileInputStream(filepath);
		            XSSFWorkbook wb=new XSSFWorkbook(input);
		           
		            XSSFSheet sh=wb.getSheet(Sheetname);
		            int row=sh.getLastRowNum();
		            
		//            System.out.println(row);
		  //          int noOfColumns =row.getLastCellNum();
		            for(int i=0; i<row-5; i++){
		            	XSSFRow rownum=sh.getRow(i);
		            	for(int j=0; j<rownum.getLastCellNum(); j++){
		            		String cellval=rownum.getCell(3).getStringCellValue();
		            		if(cellval.equalsIgnoreCase(Testname)){
		            			 FileOutputStream fos=new FileOutputStream(filepath);
		            			 rownum.createCell(10).setCellValue(statusvalue);  //working
		        	             	 
		        	        	 wb.write(fos); 
		         		    }
		            		else{
		            			
		            		        	 System.out.println("Test case name not matching");
		            		        	 rownum.createCell(10).setCellValue("Test case name not matching");
		            		         }
		            	}
		            }}
		    	        catch(Exception e)
		    	        {
		    	            System.out.println(e);
		    	        }
		    	
		    	       }
	    
	    
	    
	    public void readWriteExcel2(String filepath,String Sheetname,String testname,String statusvalue) throws  IOException {
			//	String FilePath = "D:\\sampledoc.xls";
				try
		        {
		            FileInputStream input= new FileInputStream(filepath);
		            XSSFWorkbook wb=new XSSFWorkbook(input);
		           
		            XSSFSheet sh=wb.getSheet(Sheetname);
		    //        XSSFRow row=sh.getRow(rowcount);
		            
		            
		          int lastrownum =sh.getLastRowNum();
		          //System.out.println(lastrownum);
		          for(int i=0; i<=lastrownum-1; i++){
		        	  XSSFRow row=sh.getRow(i);
		        	 XSSFCell cell1=row.getCell(3);
		        	String cellval=cell1.getStringCellValue();
		        	
		       // 	System.out.println(cellval);
		          if(cellval.equals(testname)){
		        		FileOutputStream fos=new FileOutputStream(filepath);
			        	 row.createCell(10).setCellValue(statusvalue);  //working
			    //    	 row.createCell(7).setCellValue(statusvalue);
			             wb.write(fos);
			         }
		          
		          }
		        }
		           
		        catch(Exception e)
		        {
		            System.out.println(e);
		        }
		
		        }
	    
	    
	    

	    
	    public void ReadWriteExcel(String filepath,String Sheetname,int rownum,int columnnum,String colValue) throws  IOException {
			//	String FilePath = "D:\\sampledoc.xls";
				try
		        {
		            FileInputStream input= new FileInputStream(filepath);
		            XSSFWorkbook wb=new XSSFWorkbook(input);
		             XSSFSheet sh=wb.getSheet(Sheetname);
		             XSSFRow row=sh.getRow(rownum);
		//           String cellval =row.getCell(columncount).toString();
		//           System.out.println(cellval);
		  //          if(cellval.equalsIgnoreCase(expcellval)){
		        	 FileOutputStream fos=new FileOutputStream(filepath);
		        	 row.createCell(columnnum).setCellValue(colValue);  //working
		             	 wb.write(fos);
		                }
//		         else{
//		    //    	 System.out.println("Test case name not matching");
//		        	 row.createCell(10).setCellValue("Test case name not matching");
//		         }
		       //    }
		        catch(Exception e)
		        {
		            System.out.println(e);
		        }
		        }
	    
	    
	    
	    
	
	    }
	
//}
