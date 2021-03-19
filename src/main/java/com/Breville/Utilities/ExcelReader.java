package com.Breville.Utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ExcelReader {

	public static FileInputStream fi;
	public static FileOutputStream fo;
	public static XSSFWorkbook wb;
	public static XSSFSheet sh;
	public static XSSFRow row;
	public static XSSFCell cell;


	public static int getRowCount(String xlFile,String xlSheet) throws Exception {

		fi =new FileInputStream(xlFile);
		wb = new XSSFWorkbook(fi);
		sh =wb.getSheet(xlSheet);
		int rowCount =sh.getLastRowNum();
		wb.close();
		fi.close();
		return rowCount;

	}

	public static int getCellCount(String xlFile,String xlSheet, int rowNum) throws Exception {

		fi =new FileInputStream(xlFile);
		wb = new XSSFWorkbook(fi);
		sh =wb.getSheet(xlSheet);
		row =sh.getRow(rowNum);
		int cellCount =row.getLastCellNum();
		wb.close();
		fi.close();
		return cellCount;

	}

	public static String getCellData(String xlFile,String xlSheet, int rowNum, int colNum) throws Exception {

		fi =new FileInputStream(xlFile);
		wb = new XSSFWorkbook(fi);
		sh =wb.getSheet(xlSheet);
		row =sh.getRow(rowNum);
		cell = row.getCell(colNum);

		String data;

		try
		{
			DataFormatter formatter = new DataFormatter();
			String cellData = formatter.formatCellValue(cell);
			return cellData;

		}catch(Exception e)
		{
			data ="";
		}

		wb.close();
		fi.close();

		return data;
	}


	public static void setCellData(String xlFile,String xlSheet, int rowNum, int colNum, String data) throws Exception {

		fi =new FileInputStream(xlFile);
		wb = new XSSFWorkbook(fi);
		sh =wb.getSheet(xlSheet);
		row =sh.getRow(rowNum);
		cell = row.createCell(colNum);
		cell.setCellValue(data);	
		fo= new FileOutputStream(xlFile);
		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();
	}


	public static Object[][] getDataFromSpreadSheet(String path, String workSheetName) throws Exception {

		int rowCount = ExcelReader.getRowCount(path,workSheetName);
		int colCount = ExcelReader.getCellCount(path,workSheetName,1);
		Object[][] data = new Object[rowCount][colCount];
		for (int rowNum = 1; rowNum <= rowCount; rowNum++) {
			// loop all the available row values
			for (int colNum = 0; colNum < colCount; colNum++) {
				// while returning the data[][] you should not send the header
				// values
				data[rowNum - 1][colNum] = ExcelReader.getCellData(path,workSheetName,rowNum, colNum);
			}
		}

		return data;
	}
	
	public static Object[][] getDataFromSpreadSheet(String path, String workSheetName,int colNum1) throws Exception {

		int rowCount = ExcelReader.getRowCount(path,workSheetName);
		int colCount = ExcelReader.getCellCount(path,workSheetName,1);
		Object[][] data = new Object[rowCount][colCount];
		for (int rowNum = 1; rowNum <= rowCount; rowNum++) {
			// loop all the available row values
			for (int colNum = 0; colNum < colCount; colNum++) {
				// while returning the data[][] you should not send the header
				// values
				data[rowNum - 1][colNum1] = ExcelReader.getCellData(path,workSheetName,rowNum, colNum1);
				
			}
		}

		return data;
	}
	
	
	
	public static String getCellData(String path, String workSheetName, int ColNum) throws Exception{
		int rowCount = ExcelReader.getRowCount(path,workSheetName);
		String data= "";
		try{
			for (int rowNum = 1; rowNum <= rowCount; rowNum++) {

				data = ExcelReader.getCellData(path, workSheetName, rowNum, ColNum);
			}
		  return data;

		  }catch (Exception e){

		  return"";

		  }

		 }
}


