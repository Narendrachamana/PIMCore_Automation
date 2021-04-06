package com.Breville.Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.Breville.Base.BaseSetup;


public class ProductSearchNameTest extends BaseSetup{
	
	String itemNumber = "SES008WHT0NEU1";
	
	@Test(/*dataProvider="productItemData"*/)
	public void verifyProductSearchName()
	{
		
	extlogger = extent.createTest("verifyProductSearchName : " +itemNumber);
		
		loginpage.enterUserName(username)
				.enterPassWord(password)
				.clickLogin();
		Assert.assertTrue(loginpage.verifyPIMCoreLogin(), "Login is Failed");

		homepage.clickSearchIcon()
				.clickQuickSearch()
				.enterQuickSearchTextBox(itemNumber)
				.selectSKU(itemNumber)
				.clickGeneralTab();
		
		generalPage.clearProductSearchName();
		
		homepage.clickSaveAndPublishWithoutValidatingErrorMessages();
		
		Assert.assertEquals(homepage.displayErrorMessages(), "Search Name is Mandatory" ,"test case is pass");
		
		homepage.clickOKErrorPopup();
		
		homepage.clickLogout();
		
		
	}

}
