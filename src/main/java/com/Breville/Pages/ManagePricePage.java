package com.Breville.Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.Breville.Base.BaseSetup;
import com.Breville.Utilities.WrapperMethods;

public class ManagePricePage extends BaseSetup {

	public ManagePricePage() {
		PageFactory.initElements(driver, this);
	}

	private static final String RRP_PRICE_TEXTBOX = "//span[text()='RRP/MAP/Web Price:']//following :: input[1]";
	private static final String MANAGE_PRICE_DETAILS = "//div[text()='Manage Price Details']";

	@FindBy(how = How.XPATH, using = RRP_PRICE_TEXTBOX)
	private WebElement wbItemPurchasePrice;

	@FindBy(how = How.XPATH, using = MANAGE_PRICE_DETAILS)
	private WebElement wbManagePriceDetails;

	public ManagePricePage enterRRPPrice(String data) {
		WrapperMethods.scrollToViewTillElement(wbManagePriceDetails);
		WrapperMethods.enterText(wbItemPurchasePrice, data);
		return this;
	}

}
