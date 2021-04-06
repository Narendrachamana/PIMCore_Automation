package com.Breville.Tests;

import org.testng.annotations.Test;

import com.Breville.Base.BaseSetup;
import com.Breville.Pages.HomePage;

public class BulkPublishTest extends BaseSetup{
	
	@Test()
	public void verifyBulkPublish() {
	
	extlogger = extent.createTest("verifyBulkPublish");
	
	loginpage.enterUserName(username)
			.enterPassWord(password)
			.clickLogin();
	
	homepage.clickBrevilletools()
	.clickProductImportAndExport()
	.clickBulkPublishTab();
	
	bulkPublishPage.enterEmailID("narendra")
	.selectBusinessUnit("BUK")
	.selectBusinessUnit("BCA")
	.selectChannel("BUK")
	.selectExportToWeb("pimExportAem")
	.selectOtherSystems("D365")
	.selectD365PricePush("rrp");
	
	homepage.clickLogout();

}

}