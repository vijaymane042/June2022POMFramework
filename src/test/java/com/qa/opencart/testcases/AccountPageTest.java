package com.qa.opencart.testcases;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Epic 101:--This is Account page functionality")
@Story("US: 201: ---Develope acc page product serach functionality")
public class AccountPageTest extends BaseTest {

	@BeforeClass
	public void accSetup() {
		accpage = loginpage.doLogin(prop.getProperty("userName"), prop.getProperty("password"));
	}

	@Description("Verifying the account page title")
	@Severity(SeverityLevel.MINOR)
	@Test(priority = 1)
	public void accPageTitleTest() {
		String accPageTitle = accpage.getAccPageTitle();
		Assert.assertEquals(accPageTitle,AppConstants.ACCOUNT_PAGE_TITLE);
	}

	@Description("Verifying the account page URL")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 2)
	public void accPageURLTest() {
		Assert.assertTrue(accpage.getAccPageURL());
	}

	@Description("Verifying wheather the search field is exist")
	@Severity(SeverityLevel.TRIVIAL)
	@Test(priority = 3)
	public void accSearchExistTest() {
		Assert.assertEquals(accpage.isAccSeacrhExist(), true);
		
	}

	@Description("Verifying wheather the search ICON is exist")
	@Severity(SeverityLevel.BLOCKER)
	@Test(priority = 4)
	public void accSearchIConExistTest() {
		Assert.assertEquals(accpage.isAccSearchIconExist(), true);
	}

	@Description("Verifying wheather the account page logout button is exist")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 5)
	public void accLogoutLinkExistTest() {
		Assert.assertEquals(accpage.isAccLogoutLinkExist(), true);
	}

	@Description("Verifying the product header test")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 6)
	public void accHeaderListTest() {
	ArrayList<String> actHeaderList=accpage.getAccPageHeaderList();
	System.out.println("Acc header list is :" +actHeaderList);
	Assert.assertEquals(actHeaderList, AppConstants.ACCONT_PAGE_SECTION_HEADER);
	}
	
	@DataProvider
	public Object[][] getSearchData() {
		return new Object[][] {{"macbook"},
			                   {"imac"},
			                   {"samsung"}};
		
	}
	
	@Description("Searching the product")
	@Severity(SeverityLevel.CRITICAL)
	@Test(dataProvider = "getSearchData", priority = 7)
	public void searchCheckTest(String productKey) {
		searchResultPage=accpage.performSearch(productKey);
		Assert.assertTrue(searchResultPage.isSearchSuccessfully());
	}
	
	@DataProvider
	public Object[][] getProductData()
	{
		return new Object[][] {{"macbook","MacBook"},
			                   {"MacBook","MacBook Air"},
			                   {"MacBook","MacBook Pro"},
			                   {"imac","iMac"},
			                   {"samsung","Samsung Galaxy Tab 10.1"},
			                   {"samsung","Samsung SyncMaster 941BW"}};
	}
	
	@Description("Search and open the product that user want to search")
	@Severity(SeverityLevel.CRITICAL)
	@Test(dataProvider = "getProductData", priority = 8)
	public void searchTest(String productKey, String mainProductName) {
		searchResultPage=accpage.performSearch(productKey);
		productInfoPage=searchResultPage.selectProduct(mainProductName);
		String headerText=productInfoPage.getProductHeader(mainProductName);
		System.out.println("Product header is : "+headerText);
		Assert.assertEquals(headerText, mainProductName);
	}
	
	
	
	
	
}
