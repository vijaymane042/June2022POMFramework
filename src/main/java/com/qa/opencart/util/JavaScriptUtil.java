package com.qa.opencart.util;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptUtil {
	private WebDriver driver;
	private JavascriptExecutor js;

	public JavaScriptUtil(WebDriver driver) {
		this.driver = driver;
	}

	public void generateAlert(String message) {
		js = (JavascriptExecutor) driver;
		js.executeScript("alert('" + message + "')");
	}

	public void refreshBrowserByJS() {
		js = (JavascriptExecutor) driver;
		js.executeScript("history.go(0)");
	}

	public void goBackwordByJS() {
		js = (JavascriptExecutor) driver;
		js.executeScript("history.go(-1)");
	}

	public void goForwordByJS() {
		js = (JavascriptExecutor) driver;
		js.executeScript("history.go(1)");
	}

	public String getPageInnerText() {
		js = (JavascriptExecutor) driver;
		// return (String) js.executeScript("return
		// document.documentElement.innerText");
		return js.executeScript("return document.documentElement.innerText").toString();
	}

	public String getPageTitle() {
		js = (JavascriptExecutor) driver;
		return js.executeScript("return document.title").toString();
	}

	public void clickOnElement(WebElement element) {
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
	}

	public void flash(WebElement element) {
		String bgColor = element.getCssValue("backgroundColor");
		for (int i = 0; i < 3; i++) {
			changeColor("rgb(100,1000,500)", element);
			changeColor(bgColor, element);
		}
	}
	
	

	public void changeColor(String color, WebElement element) {
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].style.backgroundColor='" + color + "'", element);
	}

	public void scrollPageDown() {
		js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
	}

	public void scrollPageDown(String height) {
		js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,'" + height + "')");
	}

	public void scrollPageUp() {
		js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(document.body.scrollHeight,0)");
	}

	public void scrollIntoView(WebElement element) {
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public void drowBorader(WebElement element) {
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].style.border='3px solid red'", element);
	}

}
