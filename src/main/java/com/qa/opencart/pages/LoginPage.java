package com.qa.opencart.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.util.ElementUtil;

import io.qameta.allure.Step;


public class LoginPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	

	// 1.By Locator
	private By emailID = By.id("input-email");
	private By password = By.id("input-password");
	private By login = By.xpath("//input[@type='submit']");
	private By forgotPWD = By.xpath("//div[@class='form-group']//a[text()='Forgotten Password']");
	private By register = By.linkText("Register");
	
	
	private static final Logger LOG = Logger.getLogger(DriverFactory.class);

	// 2.Constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil=new ElementUtil(driver);
	}

	// 3.Actions
	@Step("Waiting for login page title")
	public String getPageTitle() {
		String pageTitle= eleUtil.waitForTitles(AppConstants.DEFAULT_TIME_OUT, AppConstants.LOGIN_PAGE_TITLE);
		System.out.println("Page Title is : " + pageTitle);
		LOG.info("Page Title is : " + pageTitle);
		return pageTitle;
	}
	@Step("Waiting for login page URL")
	public boolean getPageURL() {
		String pageURL = eleUtil.waitForURLContains(AppConstants.DEFAULT_TIME_OUT, AppConstants.LOGIN_PAGE_URL_PARAM);
		System.out.println("Page URL is : " + pageURL);
	
		if (pageURL.contains(AppConstants.LOGIN_PAGE_URL_PARAM)) {
			return true;
		}
		return false;
	}
	@Step("Waiting for forgot password link")
	public boolean isForgotPasswordLinkVisible() {
		return eleUtil.doElementIsDisplayed(forgotPWD);
	}
	@Step("Login using UserName : {0} and Password : {1}")
	public AccountPage doLogin(String userName, String pwd) {
		System.out.println("User credentials are UserName :" + userName + " Password :" + pwd);
		eleUtil.doSendKeysWithWait(emailID, AppConstants.DEFAULT_TIME_OUT, userName);
        eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(login);
      return new AccountPage(driver);
	}
	
	@Step("Navigates to Register Page")
	public RegisterPage navigateToRegisterPage() {
		eleUtil.doClick(register);
		return new RegisterPage(driver);
	}

}
