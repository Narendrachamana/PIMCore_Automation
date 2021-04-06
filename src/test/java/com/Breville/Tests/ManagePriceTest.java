package com.Breville.Tests;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.Breville.Base.BaseSetup;
import com.Breville.Utilities.ExcelReader;

public class ManagePriceTest extends BaseSetup{
	
	@Test(dataProvider="RRPPriceData")
	public void verifyPricingDetails(String itemNumber)
	{
		
		extlogger = extent.createTest("verifyPricingDetails : " +itemNumber);
		
		loginpage.enterUserName(username)
				.enterPassWord(password)
				.clickLogin();
		Assert.assertTrue(loginpage.verifyPIMCoreLogin(), "Login is Failed");

		homepage.clickSearchIcon()
				.clickQuickSearch()
				.enterQuickSearchTextBox(itemNumber)
				.selectSKU(itemNumber)
				.clickManagePriceTab();
		
		managePricePage.enterRRPPrice("19.45");
		homepage.clickSaveAndPublishWithoutVerifySuccessMessage();
		homepage.clickLogout();
				
}
	@DataProvider(name = "RRPPriceData")
	public Object [][]  getPriceData() throws Exception
	{
		String path = System.getProperty("user.dir")+File.separator+"Resources"+File.separator+"TestData.xlsx";
		return ExcelReader.getDataFromSpreadSheet(path,"Manage_Price");
	}
}
