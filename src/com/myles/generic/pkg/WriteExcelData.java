package com.myles.generic.pkg;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
////import java.util.Iterator;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.xssf.usermodel.XSSFCell;
//import org.apache.poi.xssf.usermodel.XSSFRow;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.*
//import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.util.Locale;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class WriteExcelData {

	public void write() throws IOException{
//		
//		FileOutputStream fos= new FileOutputStream("D:\\Jay Documents\\SoapUIData.xlsx");
//		XSSFWorkbook wb =new XSSFWorkbook(fos);
		
		
		FileInputStream FIS=new FileInputStream("D:\\Jay Documents\\SoapUIData1.xlsx");
 	      Workbook WBookObj=new XSSFWorkbook(FIS);
 	      Sheet SheetObj=WBookObj.getSheet("TestCases");
 	      Row RowObj=SheetObj.getRow(4);
 	      
// 	   DataFormatter formatter = new DataFormatter(Locale.US);
//
// 	String netpriceobj = formatter.formatCellValue(RowObj.getCell(0)).replaceAll("0", "").trim();
 	     RowObj.getCell(11).setCellValue("Pass");
		  FIS.close();
 	     FileOutputStream outputStream = new FileOutputStream("D:\\Jay Documents\\SoapUIData1.xlsx");
          WBookObj.write(outputStream);
      //close output stream

 	    outputStream.close();

		
		
	}
	
	
//	FileOutputStream fs = new FileOutputStream("D:\\Jay Documents\\SoapUIData.xlsx");
//			XSSFWorkbook wb = new XSSFWorkbook(fs);
//			XSSFSheet ws = wb.getSheet("TestCases")
//			int totalRows = ws.getPhysicalNumberOfRows();
//
//			//def list1 = new ArrayList<String>();
//
//
//			//for(def j=1; i<totalRows; j++)
//			//{
//			XSSFRow row = ws.getRow(4);
//			//def c = row.getPhysicalNumberOfCells()
//
//			//for (def j=0;j<c;j++)
//			//{
//			String city = row.getCell(11).setCellValue("}
//			    
//			     else{
//			     	log.info "status is true: pass";
//			     }
//	
//	
	

	
	
	
	
}	
	
	
	
	
	

