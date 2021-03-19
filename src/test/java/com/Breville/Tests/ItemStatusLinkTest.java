package com.Breville.Tests;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.Breville.Base.BaseSetup;
import com.Breville.Utilities.ExcelReader;

public class ItemStatusLinkTest extends BaseSetup  {
	
@Test(dataProvider="productItemData")
public void verifyItemStatusLink(String itemNumber)
{
	
	extlogger = extent.createTest("verifyItemStatusLink : " +itemNumber);
	
	loginpage.enterUserName(username)
			.enterPassWord(password)
			.clickLogin();
	Assert.assertTrue(loginpage.verifyPIMCoreLogin(), "Login is Failed");

	homepage.clickSearchIcon()
			.clickQuickSearch()
			.enterQuickSearchTextBox(itemNumber)
			.selectSKU(itemNumber)
			.clickGeneralTab();
	
	generalPage.clickDeleteItemSatusLink();
	
	homepage.clickSaveAndPublishWithoutVerifySuccessMessage()
	.clickLogout();
/*	generalPage.clickAddItemSatusLink()
	.clickBusinessUnit()
	.selectItemStatusLink("02_Active_Critical");
	homepage.clickSaveAndPublishWithoutVerifySuccessMessage();*/

}

@DataProvider(name = "productItemData")
public Object [][]  getItemData() throws Exception
{
	String path = System.getProperty("user.dir")+File.separator+"Resources"+File.separator+"TestData.xlsx";
	return ExcelReader.getDataFromSpreadSheet(path,"Save_and_Publish");
}


}
