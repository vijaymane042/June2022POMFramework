package com.qa.opencart.testcases;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebElement;
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

@Epic("Epic 55:--This is ProductPageTest functionality")
@Story("US: 55.3 ---Develope product serach functionality")
public class ProductPageTest extends BaseTest{

	

	@BeforeClass
	public void productInfoSetup() {
		accpage=loginpage.doLogin(prop.getProperty("userName"), prop.getProperty("password"));
	}
	@DataProvider
	public Object getHeaderData() {
		return new Object[][] {{"macbook","MacBook Air"},
                               {"macbook","MacBook Pro"},
                               {"imac","iMac"}};
	}
	
	@Description("Verifying the product header test")
	@Severity(SeverityLevel.MINOR)
	@Test(dataProvider = "getHeaderData",priority = 1)
	public void productHeaderTest(String searchkey,String productText) {
		searchResultPage = accpage.performSearch(searchkey);
		productInfoPage=searchResultPage.selectProduct(productText);	
		String productHeaderText=productInfoPage.getProductHeader(productText);
		System.out.println("Product header text is : " +productHeaderText);
		Assert.assertEquals(productHeaderText, productText);
	}
	
	@Description("Verifying the title of product header")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 2, enabled = false)
	public void getProductTitleTest() {
		searchResultPage = accpage.performSearch("macbook");
		productInfoPage=searchResultPage.selectProduct("MacBook Air");	
		String productPageTitle=productInfoPage.getProductPageTitle("MacBook Air");
		System.out.println("Product Page Title is : " +productPageTitle);
		Assert.assertEquals(productPageTitle, "MacBook Air");
	}
	
	@Description("Verifying the product page URL")
	@Severity(SeverityLevel.BLOCKER)
	@Test(priority = 3, enabled = false)
	public void getProductPageURLTest() {
		searchResultPage = accpage.performSearch("macbook");
		productInfoPage=searchResultPage.selectProduct("MacBook Air");
		String productPageURL=productInfoPage.getProductPageURL("search=macbook");
		Assert.assertEquals(productPageURL, "https://naveenautomationlabs.com/opencart/index.php?route=product/product&product_id=44&search=macbook");
	}
	
	@DataProvider
	public Object getProductInfoData() {
		return new Object[][] {{"macbook","MacBook Air"},
			                   {"macbook","MacBook Pro"},
			                   {"imac","iMac"}};
	}
	@Description("Verifying the product images")
	@Severity(SeverityLevel.NORMAL)
	@Test(dataProvider = "getProductInfoData")
	public void getProductImageCountTest(String searchkey,String productHeaderText) {
		searchResultPage = accpage.performSearch(searchkey);
		productInfoPage=searchResultPage.selectProduct(productHeaderText);
		List<WebElement> actProductImageCount=productInfoPage.getProductImageCount();
		int imageCount=actProductImageCount.size();
		System.out.println("Product image count is : " +actProductImageCount.size());
		Assert.assertEquals(imageCount, actProductImageCount.size());
	}
	
	@Description("Search product and verify the text of product")
	@Severity(SeverityLevel.CRITICAL)
	@Test
	public void getProductMetaDataTest() {
		searchResultPage = accpage.performSearch("macbook");
		productInfoPage=searchResultPage.selectProduct("MacBook Pro");
		Map<String,String>actMetaData=productInfoPage.getMetaData();
		Assert.assertEquals(actMetaData.get("Brand"),"Apple");
		Assert.assertEquals(actMetaData.get("Product Code"),"Product 18");
		Assert.assertEquals(actMetaData.get("Reward Points"),"800");
		Assert.assertEquals(actMetaData.get("Availability"),"In Stock");

		
	}

}
