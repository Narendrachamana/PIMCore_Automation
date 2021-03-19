package com.Breville.Pages;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.Breville.Base.BaseSetup;
import com.Breville.Utilities.WrapperMethods;

public class LoginPage extends BaseSetup {

	public LoginPage() {
		PageFactory.initElements(driver, this);
	}

	private static final String USERNAME_TEXTBOX = "//input[@name='username']";
	private static final String PASSWORD_TEXTBOX = "//input[@name='password']";
	private static final String LOGIN_BUTTON = "//button[text()='Login']";
	private static final String PIMCORE_LOGO = "//div[@id='pimcore_panel_tabs']";

	@FindBy(how = How.XPATH, using = USERNAME_TEXTBOX)
	private WebElement wbUserName;

	@FindBy(how = How.XPATH, using = PASSWORD_TEXTBOX)
	private WebElement wbPassWord;

	@FindBy(how = How.XPATH, using = LOGIN_BUTTON)
	private WebElement wbLoginButton;

	@FindBy(how = How.XPATH, using = PIMCORE_LOGO)
	private WebElement wbPIMCoreLogo;

	public LoginPage enterUserName(String userName) {

		WrapperMethods.enterText(wbUserName, userName);
		return this;
	}

	public LoginPage enterPassWord(String passWord) {

		WrapperMethods.enterText(wbPassWord, passWord);
		return this;
	}

	public LoginPage clickLogin() {
		WrapperMethods.clickElement(wbLoginButton);
		return this;
	}

	public Boolean verifyPIMCoreLogin() {
		if (wbPIMCoreLogo.isDisplayed())
			return true;
		else
			return false;
	}

}
