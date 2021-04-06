package com.Breville.Tests;


import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections4.list.TreeList;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.Breville.Base.BaseSetup;
import com.Breville.Pages.HomePage;
import com.Breville.Pages.LoginPage;
import com.Breville.Pages.ProductImportAndExportPage;
import com.Breville.Utilities.WrapperMethods;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;


public class ExportProductTest extends BaseSetup{
	
	String path = "C:"+File.separator+"Users"+File.separator+"LENOVO"+File.separator+"Downloads";
	String path2 = System.getProperty("user.dir")+File.separator+"Resources"+File.separator+"TestData.xlsx"; 
	String downloadedFileName = "product_export";
	String exportFileSheetName = "product_data";
	String importData = "";

	@Test
	public void verifyProductExport() throws Exception
	{
		extlogger = extent.createTest("verifyProductExport");
		

		loginpage.enterUserName(username)
				.enterPassWord(password)
				.clickLogin();
		importData = WrapperMethods.getProductData(path2, "Product_Export", 0,true);
		Assert.assertTrue(loginpage.verifyPIMCoreLogin(), "Login is failed");

			homepage.clickBrevilletools()
				.clickProductImportAndExport();

		productImportAndExportPage.clickProductExportTab()
				.enterItemNumbers(importData)
				.clickExportButton(path,downloadedFileName);
		
		String fileName =	WrapperMethods.verifyDownloadedFile(path,downloadedFileName);

		String exportData = WrapperMethods.getProductData(path+File.separator+fileName, exportFileSheetName,0,false);

		String str[] = importData.split(",");
		List<String> importDataList = new TreeList<String>();
		importDataList = Arrays.asList(str);
		Collections.sort(importDataList);

		String str1[] = exportData.split(" ");
		List<String> exportDataList = new TreeList<String>();
		exportDataList = Arrays.asList(str1);
		Collections.sort(exportDataList);

		Assert.assertTrue(exportDataList.equals(importDataList) == true,"Both input and export data are not equal");
	
		Assert.assertTrue(WrapperMethods.deleteDownloadedFile(path+File.separator+fileName),"File deletion unsuccessfully");
		
	}

}
