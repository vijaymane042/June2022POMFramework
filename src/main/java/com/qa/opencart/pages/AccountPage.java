package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.util.ElementUtil;

import io.qameta.allure.Epic;
import io.qameta.allure.Step;
import io.qameta.allure.Story;


public class AccountPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	// 1.Locators
	By logoutLink = By.linkText("Logout");
	By search = By.name("search");
	By searchIcon = By.cssSelector("div#search button");
	By headerList = By.xpath("//div[@id='content']//h2");

	// 2.Constructor
	public AccountPage(WebDriver driver) {
		this.driver = driver;
		eleUtil=new ElementUtil(driver);
	}

	// 3.Actions

	@Step(" Waiting for account page title")
	public String getAccPageTitle() {
		String accPageTitle = eleUtil.waitForTitles(AppConstants.DEFAULT_TIME_OUT, AppConstants.ACCOUNT_PAGE_TITLE);
		System.out.println("Account page title is : " + accPageTitle);
		return accPageTitle;
	}
	@Step(" Waiting for account page URL")
	public boolean getAccPageURL() {
		String accPageURL = eleUtil.waitForURLContains(AppConstants.DEFAULT_TIME_OUT,
				AppConstants.ACCOUNT_PAGE_URL_PARAM);
		System.out.println("Account page URL is :" + accPageURL);
		if (accPageURL.contains(AppConstants.ACCOUNT_PAGE_URL_PARAM)) {
			return true;
		}
		return false;
	}
	@Step(" Waiting for account Logout link")
	public boolean isAccLogoutLinkExist() {
		return eleUtil.doElementIsDisplayed(logoutLink);
	}
	@Step(" Waiting for search box")
	public boolean isAccSeacrhExist() {
		return eleUtil.doElementIsDisplayed(search);
	}
	
	@Step(" Waiting for search result : {0}")
	public SearchResultPage performSearch(String productKey) {
		System.out.println("Product Search key is :"+productKey);
		if(isAccSeacrhExist()) {
			eleUtil.doSendKeys(search, productKey);
			eleUtil.doClick(searchIcon);
			return new SearchResultPage(driver);
		}else {
			System.out.println("Search key is not available.........");
			return null;
		}
	}
	@Step(" Waiting for search icon")
	public boolean isAccSearchIconExist() {
		return eleUtil.doElementIsDisplayed(searchIcon);
	}

	@Step(" Waiting for headers of the account page")
	public ArrayList<String> getAccPageHeaderList() {
		List<WebElement> accHeaderList=eleUtil.WaitForElementTobeVisible(headerList, AppConstants.DEFAULT_TIME_OUT);
		ArrayList<String> sectionHeaderList = new ArrayList<String>();
		for (WebElement e : accHeaderList) {
			String headerText = e.getText();
			sectionHeaderList.add(headerText);
		}
		return sectionHeaderList;
	}

}
