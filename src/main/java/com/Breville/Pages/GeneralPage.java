package com.Breville.Pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.Breville.Base.BaseSetup;
import com.Breville.Utilities.WrapperMethods;
import com.aventstack.extentreports.Status;

public class GeneralPage extends BaseSetup{
	
	public GeneralPage() {
		PageFactory.initElements(driver, this);
	}

	private static final String ITEMSTATUSLINK_DROPDOWN = "//ul[@role='listbox' and (starts-with(@id,'boundlist-15'))]/li";
	private static final String ADD_ITEMSTATUSLINK_FIELDS = "//div[text()='Item Status']//following ::span[@data-ref='btnIconEl' and(starts-with (@id,'button-15'))]";
	private static final String DELETE_ITEMSTATUSLINK_FIELDS = "//div[text()='Item Status']//following ::span[@data-ref='btnIconEl' and(starts-with (@id,'button-15'))][2]";
	private static final String BUSINESS_UNITS_CHECKBOX ="//div[text()='Item Status']//following :: span[contains(@id, 'checkbox-15')]//following :: span[text()='BUS:']";
	private static final String ITEMSTATUSLINK_DROPDOWN_BUTTON ="//span[text()='Item Status Link:']//following :: div[starts-with(@id,'combo-15')][4]";
	
	@FindBy(how = How.XPATH, using = ITEMSTATUSLINK_DROPDOWN)
	private List<WebElement> wbItemStatusLinkDropdown;
	
	@FindBy(how = How.XPATH, using = ITEMSTATUSLINK_DROPDOWN_BUTTON)
	private WebElement wbItemStatusLinkDropdownButton;
	
	@FindBy(how = How.XPATH, using = ADD_ITEMSTATUSLINK_FIELDS)
	private WebElement wbAddItemStatusLinkFields;
	
	@FindBy(how = How.XPATH, using = DELETE_ITEMSTATUSLINK_FIELDS)
	private WebElement wbDeleteItemStatusLinkFields;
	
	@FindBy(how = How.XPATH, using = BUSINESS_UNITS_CHECKBOX)
	private WebElement wbBusinessUnitCheckboxes;

	public GeneralPage selectItemStatusLink(String data) {
		String al = "";
		WrapperMethods.clickElement(wbItemStatusLinkDropdownButton);
		for (int i = 0; i <= wbItemStatusLinkDropdown.size(); i++) {

			al = wbItemStatusLinkDropdown.get(i).getText();
			if (al.contains(data)) {
				wbItemStatusLinkDropdown.get(i).click();
				break;
			}
		}
		return this;
	}
	
	
	public GeneralPage clickAddItemSatusLink() {
		WrapperMethods.clickElement(wbAddItemStatusLinkFields);
		return this;
	}
	
	public GeneralPage clickDeleteItemSatusLink() {
		WrapperMethods.scrollToViewTillElement(wbDeleteItemStatusLinkFields);
		WrapperMethods.clickElement(wbDeleteItemStatusLinkFields);
		return this;
	}
	
	public GeneralPage clickBusinessUnit()
	{
		WrapperMethods.scrollToViewTillElement(wbBusinessUnitCheckboxes);
		WrapperMethods.clickElement(wbBusinessUnitCheckboxes);
		return this;
		
	}


}
