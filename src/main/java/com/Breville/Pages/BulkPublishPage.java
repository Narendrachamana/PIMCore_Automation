package com.Breville.Pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.Breville.Base.BaseSetup;
import com.Breville.Utilities.WrapperMethods;

public class BulkPublishPage extends BaseSetup{

	public BulkPublishPage() {
		PageFactory.initElements(driver, this);
	}
	
	
	private static final String EMAIL_NOTIFICATION_TEXTBOX = "//input[@name='email']";
	private static final String BUSINESS_UNITS_CHECKBOX = "//input[@name='business_unit[]']";
	private static final String CHANNEL_CHECKBOX = "//input[@name='channel[]']";
	private static final String EXPORT_TO_WEB_CHECKBOX = "//input[@name='pim_exports[]']";
	private static final String OTHER_SYSTEMS_CHECKBOX = "//legend[text()='Other systems']//following :: input[@class='form-check-input left_push' or @name='down_stream[]']";
	private static final String D365_PRICEPUSH_CHECKBOX = "//input[@name='down_stream_d365[]']";
	private static final String ITEM_GROUP_CHECKBOX = "//input[@name='all_item']";
	private static final String ITEM_NUMBER_TEXTBOX = "//textarea[@name='item_number']";
	private static final String PUBLISH_SKU_BUTTON = "//button[text()='Publish SKU']";
	private static final String VALIDATE_SKU_BUTTON = "//button[text()='Validate SKU']";
	
	@FindBy(how = How.XPATH, using = EMAIL_NOTIFICATION_TEXTBOX)
	private WebElement wbEmailNotifiactions;
	
	@FindBy(how = How.XPATH, using = BUSINESS_UNITS_CHECKBOX)
	private List<WebElement> wbBusinessUnits;
	
	@FindBy(how = How.XPATH, using = CHANNEL_CHECKBOX)
	private List<WebElement> wbChannels;
	
	@FindBy(how = How.XPATH, using = EXPORT_TO_WEB_CHECKBOX)
	private List<WebElement> wbExportToWeb;
	
	@FindBy(how = How.XPATH, using = OTHER_SYSTEMS_CHECKBOX)
	private List<WebElement> wbOtherSystems;
	
	@FindBy(how = How.XPATH, using = D365_PRICEPUSH_CHECKBOX)
	private List<WebElement> wbD365PricePush;
	
	@FindBy(how = How.XPATH, using = ITEM_GROUP_CHECKBOX)
	private WebElement wbItemGroup;
	
	@FindBy(how = How.XPATH, using = ITEM_NUMBER_TEXTBOX)
	private WebElement wbItemNumber;
	
	@FindBy(how = How.XPATH, using = PUBLISH_SKU_BUTTON)
	private WebElement wbPublishSku;
	
	@FindBy(how = How.XPATH, using = VALIDATE_SKU_BUTTON)
	private WebElement wbValidateSku;
	
	
	public BulkPublishPage enterEmailID(String data)
	{
		homepage.switchToIFrame();
		WrapperMethods.enterText(wbEmailNotifiactions,data);
		return this;
	}
	
	
	public BulkPublishPage selectBusinessUnit(String data)
	{
		WrapperMethods.selectCheckboxValue(wbBusinessUnits,data);
		return this;
	}
	
	public BulkPublishPage selectChannel(String data)
	{
		WrapperMethods.selectCheckboxValue(wbChannels,data);
		return this;
	}
	
	public BulkPublishPage selectExportToWeb(String data)
	{
		WrapperMethods.selectCheckboxValue(wbExportToWeb,data);
		return this;
	}
	
	public BulkPublishPage selectOtherSystems(String data)
	{
		WrapperMethods.selectCheckboxValue(wbOtherSystems,data);
		return this;
	}
	
	public BulkPublishPage selectD365PricePush(String data)
	{
		WrapperMethods.selectCheckboxValue(wbD365PricePush,data);
		return this;
	}
	
	public BulkPublishPage enterItemNumber(String data)
	{
		WrapperMethods.enterText(wbItemNumber,data);
		return this;
	}
	
	public BulkPublishPage clickPublishSKU() {
		WrapperMethods.clickElement(wbPublishSku);
		return this;
	}
	
	public BulkPublishPage clickValidateSKU() {
		WrapperMethods.clickElement(wbValidateSku);
		return this;
	}
	
}
