package com.Breville.Pages;


import java.io.IOException;
import java.util.List;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.Breville.Base.BaseSetup;
import com.Breville.Utilities.WrapperMethods;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;


public class HomePage extends BaseSetup {

	public HomePage() {

		PageFactory.initElements(driver, this);
	}

	private static final String SEACRH_ICON = "//li[@id='pimcore_menu_search']";
	private static final String QUICK_SEARCH_ICON = "//span[text()='Quick Search']";
	private static final String QUICK_SEARCH_TEXTBOX = "//input[@id='quickSearchCombo-inputEl']";
	private static final String LIST_OF_SKUS = "//div[@class='list-path']";
	private static final String SELECT_SKU = "//div[contains(@id,'boundlist-')]//child :: ul//li[1]";
	private static final String ANOTHER_PERSON_OPENED = "(//div[text()='The desired element is currently opened by another person:'])[1]";
	private static final String YES_BUTTON ="//span[text()='Yes']";

	// SKU'S TABS
	private static final String GENERAL_TAB = "//span[text()='General']";
	private static final String RELEASE_TAB = "	(//span[text()='Release'])[3]";
	private static final String PURCHES_OR_PLANNING_TAB = "(//span[text()='Purchase/Planning'])[3]";
	private static final String SERVICE_BOM_TAB = "(//span[text()='Service BOM'])[3]";
	private static final String WEB_TAB = "(//span[text()='Web'])[3]";
	private static final String MANAGE_INVENTORY_TAB = "(//span[text()='Manage Inventory'])[3]";
	private static final String MANAGE_PRICE_TAB = "(//span[text()='Manage Price'])[3]";
	private static final String FINANCIAL_DIMENSIONS_TAB = "(//span[text()='Financial Dimensions'])[3]";
	private static final String RETAIL_TAB = "(//span[text()='Retail'])[3]";
	private static final String WAREHOUSING_TAB = "(//span[text()='Warehousing'])[3]";
	private static final String SALESFORCE_TAB = "(//span[text()='SalesForce'])[3]";
	private static final String BREVILLE_TOOLS = "//li[@id='pimcore_menu_mds']";
	private static final String PRODUCT_IMPORT_AND_EXPORT = "//span[text()='Product Import/Export']";
	private static final String SAVE_AND_PUBLISH = "//span[text()='Save & Publish']";
	private static final String SAVEDSUCCESSFUL_ALERTMESSAGE = "//div[text()='Saved successfully!']";
	private static final String ERROR_POPUP = "//div[text()='Error']";
	private static final String OK_BUTTON = "//div[text()='Error']//following:: span[text()='OK']";
	private static final String LOGOUT_BUTTON = "//a[@id='pimcore_logout']";
	private static final String BREVILLE_LOGO = "//div[@id='header']";

	@FindBy(how = How.XPATH, using = SEACRH_ICON)
	private WebElement wbSearchIcon;

	@FindBy(how = How.XPATH, using = QUICK_SEARCH_ICON)
	private WebElement wbQuickSearchIcon;

	@FindBy(how = How.XPATH, using = QUICK_SEARCH_TEXTBOX)
	private WebElement wbQuickSearchTextbox;

	@FindBy(how = How.XPATH, using = LIST_OF_SKUS)
	private List<WebElement> wbListOfSku;

	@FindBy(how = How.XPATH, using = SELECT_SKU)
	private WebElement wbSelectSku;

	@FindBy(how = How.XPATH, using = ANOTHER_PERSON_OPENED)
	private WebElement wbAnotherPersonOpenedPopUp;

	@FindBy(how = How.XPATH, using = YES_BUTTON)
	private WebElement wbYesButton;

	@FindBy(how = How.XPATH, using = GENERAL_TAB)
	private WebElement wbGeneralTab;

	@FindBy(how = How.XPATH, using = BREVILLE_TOOLS)
	private WebElement wbBrevilleTools;

	@FindBy(how = How.XPATH, using = SAVE_AND_PUBLISH)
	private WebElement wbSaveAndPublish;

	@FindBy(how = How.XPATH, using = SAVEDSUCCESSFUL_ALERTMESSAGE)
	private WebElement wbSavedSuccessfulAlertMessage;

	@FindBy(how = How.XPATH, using = ERROR_POPUP)
	private WebElement wbErrorPopup;

	@FindBy(how = How.XPATH, using = OK_BUTTON)
	private WebElement wbOKButton;

	@FindBy(how = How.XPATH, using = PRODUCT_IMPORT_AND_EXPORT)
	private WebElement wbProductImportAndExport;

	@FindBy(how = How.XPATH, using = LOGOUT_BUTTON)
	private WebElement wbLogOut;

	@FindBy(how = How.XPATH, using = BREVILLE_LOGO)
	private WebElement wbBrevilleLogo;

	public HomePage clickSearchIcon() {
		WrapperMethods.clickElement(wbSearchIcon);
		return this;
	}

	public HomePage clickQuickSearch() {
		WrapperMethods.clickElement(wbQuickSearchIcon);
		return this;
	}

	public HomePage enterQuickSearchTextBox(String data) {
		WrapperMethods.enterText(wbQuickSearchTextbox, data);
		return this;
	}

	public HomePage selectSKU(String skuNumber) {
		String al = "";
		for (int i = 0; i <= wbListOfSku.size(); i++) {

			al = wbListOfSku.get(i).getText();
			if (al.contains(skuNumber)) {
				wbListOfSku.get(i).click();
				try{
					if(wbAnotherPersonOpenedPopUp.isDisplayed()) {
						WrapperMethods.clickElement(wbYesButton);
					}else {
						extlogger.log(Status.FAIL, "Snapshot below:" + extlogger.addScreenCaptureFromPath(WrapperMethods.getScreenShot()));
					}
				}catch(Exception e)
				{
					//System.out.println(e.getMessage());
				}
				break;
			}
		}

		return this;
	}

	public HomePage clickGeneralTab() {
		WrapperMethods.clickElement(wbGeneralTab);
		return this;
	}

	public HomePage clickBrevilletools() {
		WrapperMethods.clickElement(wbBrevilleTools);
		return this;
	}

	public HomePage clickSaveAndPublish(){
		WrapperMethods.clickElement(wbSaveAndPublish);
		try {
			if(verifyPublishSuccesfullMessage()) {
			}
		}catch(Exception e)
		{
			try {
				extlogger.log(Status.FAIL,"Test Case Failed is " + e.getMessage(),MediaEntityBuilder.createScreenCaptureFromPath(WrapperMethods.getScreenShot()).build());
			} catch (IOException e1) {
	
				e1.printStackTrace();
			}
			
			WrapperMethods.clickElement(wbOKButton);
			clickLogout();	
		}
		return this;
	}
	
	
	public HomePage clickSaveAndPublishWithoutVerifySuccessMessage(){
		WrapperMethods.clickElement(wbSaveAndPublish);
		try {
			if(verifyErrorPopup()) {
				WrapperMethods.clickElement(wbOKButton);
				//clickLogout();	
			}
		}catch(Exception e)
		{
			verifyPublishSuccesfullMessage();
		}
		return this;
	}

	public HomePage clickProductImportAndExport() {
		WrapperMethods.clickElement(wbProductImportAndExport);
		return this;
	}

	public  Boolean verifyErrorPopup() {
		if (wbErrorPopup.getText() !=null) {
			//System.out.println(wbErrorPopup.getText());
			return true;
		}
		else
			return false;
	}

	public Boolean verifyPublishSuccesfullMessage() {
		if (wbSavedSuccessfulAlertMessage.getText() !=null) {
			//System.out.println(wbSavedSuccessfulAlertMessage.getText());
			return true;
		}
		else
			return false;
	}

	public HomePage clickLogout() {
		WrapperMethods.waitUntilElementIsVisible(wbLogOut);
		WrapperMethods.clickElement(wbLogOut);
		wrapperMethods.alertHandle(true, null);
		return this;
	}

	public boolean verifyBrevilleLogo() {
		WrapperMethods.waitUntilElementIsVisible(wbBrevilleLogo);
		if (wbBrevilleLogo.isDisplayed()) {
			return true;
		}
		else
			return false;
	}
}
