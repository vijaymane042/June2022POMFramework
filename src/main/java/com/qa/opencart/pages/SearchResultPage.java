package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.util.ElementUtil;

import io.qameta.allure.Epic;
import io.qameta.allure.Step;
import io.qameta.allure.Story;

public class SearchResultPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	private By searchCriteria = By.cssSelector("div.product-layout");

	public SearchResultPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
    
	@Step("Waiting for search")
	public boolean isSearchSuccessfully() {
		List<WebElement> totalSerachedProductList = eleUtil.WaitForElementTobeVisible(searchCriteria,
				AppConstants.DEFAULT_TIME_OUT);
		if (totalSerachedProductList.size() > 0) {
			System.out.println("Product search is successfully done.........");
			return true;
		} else {
			System.out.println("product search is not done........");
			return false;
		}
	}

	@Step("Waiting for product and open product")
	public ProductInfoPage selectProduct(String prdocutName) {
    eleUtil.doClick(By.linkText(prdocutName));
    return new ProductInfoPage(driver);
	}
}
