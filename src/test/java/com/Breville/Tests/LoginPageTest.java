package com.Breville.Tests;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.Breville.Base.BaseSetup;
import com.Breville.Pages.HomePage;
import com.Breville.Pages.LoginPage;
import com.Breville.Utilities.ExcelReader;

public class LoginPageTest extends BaseSetup {

	@Test(dataProvider = "productItemData")
	public void VerifyLoaginAndPublishItems(String itemNumber) throws Exception
	{
		
		extlogger = extent.createTest("VerifyLoaginAndPublishItems : " +itemNumber);
		
		loginpage.enterUserName(username)
				.enterPassWord(password)
				.clickLogin();
		Assert.assertTrue(loginpage.verifyPIMCoreLogin(), "Login is Failed");

		homepage.clickSearchIcon()
				.clickQuickSearch()
				.enterQuickSearchTextBox(itemNumber)
				.selectSKU(itemNumber)
				.clickGeneralTab()
				.clickSaveAndPublish();
		Assert.assertTrue(homepage.verifyPublishSuccesfullMessage(), "Saved and Published UnSuccessfully");

		homepage.clickLogout();

		Assert.assertTrue(homepage.verifyBrevilleLogo(), "Logout UnSuccessfully");
	}

	
	@DataProvider(name = "productItemData")
	public Object [][]  getItemData() throws Exception
	{
		String path = System.getProperty("user.dir")+File.separator+"Resources"+File.separator+"TestData.xlsx";
		return ExcelReader.getDataFromSpreadSheet(path,"Save_and_Publish");
	}

}
