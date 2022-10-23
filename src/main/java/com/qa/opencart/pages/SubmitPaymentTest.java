package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SubmitPaymentTest {

	WebDriver driver;
	private By text = By.id("login");

	public void sum() {
		System.out.println("Sum");
		driver.findElement(text).click();
	}
}
