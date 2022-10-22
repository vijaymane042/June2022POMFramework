package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.util.ElementUtil;

import io.qameta.allure.Epic;
import io.qameta.allure.Step;
import io.qameta.allure.Story;


public class ProductInfoPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	private Map<String, String> productInfoMap;
	
	private By searchProductImages=By.xpath("//ul[@class='thumbnails']//img");
	private By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=1]/li");
	private By productPriceMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=2]/li");
	
	public ProductInfoPage(WebDriver driver) {
     
		this.driver=driver;
		eleUtil = new ElementUtil(driver);
	
	}
	
	@Step("Waiting for product header text")
	public String getProductHeader(String mainPrdocutName) {
		String xpath = "//h1[text()='"+mainPrdocutName+"']";
		String actHeaderTeaxt=eleUtil.doGetText(By.xpath(xpath));
		return actHeaderTeaxt;
	}
	
	@Step("Waiting for product images")
	public List<WebElement> getProductImageCount() {
		List<WebElement> procductCount= eleUtil.WaitForElementTobeVisible(searchProductImages, AppConstants.DEFAULT_TIME_OUT);
		return procductCount;
	}
	
	@Step("Waiting for product page title")
	public String getProductPageTitle(String productTitle) {
		return eleUtil.waitForTitles(AppConstants.DEFAULT_TIME_OUT,productTitle);
	}
	
	@Step("Waiting for product page URL")
	public String getProductPageURL(String productURL) {
		return eleUtil.waitForURLContains(AppConstants.DEFAULT_TIME_OUT, productURL);
	}
	
	public Map<String, String> getMetaData() {
		List<WebElement> metaList=eleUtil.getElemets(productMetaData);
		System.out.println(metaList.size());
		//productInfoMap = new HashMap<String, String>();
		productInfoMap = new LinkedHashMap<String, String>();
		//productInfoMap = new TreeMap<String, String>();
		for(WebElement e : metaList) {
			String metaText=e.getText();
			String meta[] = metaText.split(":");
			String metaKey=meta[0].trim();
			String metaValue=meta[1].trim();
			productInfoMap.put(metaKey, metaValue);
		}
		productInfoMap.forEach((k,v) -> System.out.println( k +" : "+v));
		return productInfoMap;
	}
	

}
