package com.Breville.Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.Breville.Base.BaseSetup;

public class BrandNameValidationTest extends BaseSetup {
	
String itemNumber = "SES008WHT0NEU1";

@Test(/*dataProvider="productItemData"*/)
public void verifyBrandName()
{
	
extlogger = extent.createTest("verifyBrandName : " +itemNumber);
	
	loginpage.enterUserName(username)
			.enterPassWord(password)
			.clickLogin();
	Assert.assertTrue(loginpage.verifyPIMCoreLogin(), "Login is Failed");

	homepage.clickSearchIcon()
			.clickQuickSearch()
			.enterQuickSearchTextBox(itemNumber)
			.selectSKU(itemNumber)
			.clickGeneralTab();
	
	generalPage.clickBrandDeletion();
	
	homepage.clickSaveAndPublishWithoutValidatingErrorMessages();
	
	Assert.assertEquals(homepage.displayErrorMessages(), "Brand cannot be empty." ,"test case is pass");
	
	homepage.clickOKErrorPopup();
	
	homepage.clickLogout();
	
	
	
}
	

}
