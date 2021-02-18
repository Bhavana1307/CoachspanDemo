package com.qa.coachspan.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {
	
	private static Workbook book;
	private static Sheet sheet;
	public static String Test_Data_path="";
	
	public static Object[][] getTestData(String sheetname) 
	{
		  Object data[][]=null;
		try 
		{		
			FileInputStream ip = new FileInputStream(Test_Data_path);
				book=WorkbookFactory.create(ip);
			 sheet = book.getSheet(sheetname);
		   
		 data = new Object[sheet.getLastRowNum()][6];
		   for(int i=0;i<sheet.getLastRowNum();i++) 
		   {
			   for(int j=0;j<sheet.getRow(0).getLastCellNum();j++)
			   {
				   data[i][j] = sheet.getRow(i+1).getCell(j).toString();
			   }
		   }
		} catch(FileNotFoundException e) {
			  e.printStackTrace();
		  }
		catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}
	
	
		
	
	
	
	
	

}
