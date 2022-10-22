package com.qa.opencart.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Epic 100:--This is Account page functionality")
@Story("US: 200: ---Develope product serach functionality")
public class LoginPageTest extends BaseTest{

	@Description("vefirying the login page title")
	@Severity(SeverityLevel.MINOR)
	@Test(priority = 1)
	public void getPageTitleTest() {
		Assert.assertEquals(loginpage.getPageTitle(),AppConstants.LOGIN_PAGE_TITLE);
	}
	
	@Description("vefirying the login page URL")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 2)
	public void getPageURLTest() {
		Assert.assertTrue(loginpage.getPageURL());
	}
	
	@Description("vefirying the forgot password link")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 3)
	public void isForgotPasswordLinkVisibleTest() {
		Assert.assertEquals(loginpage.isForgotPasswordLinkVisible(),true);
	}
	
	@Description("Login to account")
	@Severity(SeverityLevel.BLOCKER)
	@Test(priority = 4)
	public void doLoginTest() {
		accpage=loginpage.doLogin(prop.getProperty("userName"), prop.getProperty("password"));
		Assert.assertTrue(accpage.isAccLogoutLinkExist());
	}
}
