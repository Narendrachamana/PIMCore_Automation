package com.Breville.Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.Breville.Base.BaseSetup;
import com.Breville.Utilities.WrapperMethods;

public class ProductImportAndExportPage extends BaseSetup{
	
	public ProductImportAndExportPage() {
	
		PageFactory.initElements(driver, this);
	}

	private static final String PRODUCT_EXPORT_TAB = "//span[text()='Product Export']";
	private static final String ITEMNUMBERS_TEXTBOX = "//textarea[@id='itemNumbers']";
	private static final String EXPORT_BUTTON = "//button[text()='Export']";
	private static final String EXPORT_SCHCEDULED = "//h4[text()='Export Shceduled']";
	private static final String CLOSE_BUTTON = "//button[text()='Close']";
	
	
	@FindBy(how = How.XPATH, using = PRODUCT_EXPORT_TAB)
	private WebElement wbProductExportTab;
	
	@FindBy(how = How.XPATH, using = ITEMNUMBERS_TEXTBOX)
	private WebElement wbItemNumbersTextbox;
	
	@FindBy(how = How.XPATH, using = EXPORT_BUTTON)
	private WebElement wbExportButton;
	
	
	public ProductImportAndExportPage clickProductExportTab()
	{
		WrapperMethods.clickElement(wbProductExportTab);
		return this;
	}
	
	public ProductImportAndExportPage enterItemNumbers(String data)
	{
		homepage.switchToIFrame();
		WrapperMethods.enterText(wbItemNumbersTextbox, data);
		return this;
	}
	
	public ProductImportAndExportPage clickExportButton(String path,String fileName)
	{
		WrapperMethods.clickElement(wbExportButton);
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WrapperMethods.verifyDownloadedFile(path,fileName);
		return this;
	}
	
}
