package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.util.ElementUtil;

public class RegisterPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	private By firstName = By.id("input-firstname");
	private By lastName = By.id("input-lastname");
	private By email = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By confirmPassword = By.id("input-confirm");
	private By subscriberYes = By.xpath("//label[@class='radio-inline']/input[@type='radio' and @value='1']");
	private By subscriberNo = By.xpath("//label[@class='radio-inline']/input[@type='radio' and @value='0']");
	private By agreePolicyCheckBox = By.xpath("//input[@type='checkbox']");
	private By continueBTN = By.xpath("//input[@type='submit']");
	private By registartionConfirmMessage = By.xpath("//h1[text()='Your Account Has Been Created!']");
	private By logout = By.linkText("Logout");
	private By register = By.linkText("Register");
	
	
	public RegisterPage(WebDriver driver) {
		
		this.driver= driver;
		eleUtil= new ElementUtil(driver);
	}


	public String userRegister(String firstName, String lastName, String email, String telephone, String password, String subscriber) {
		eleUtil.doSendKeysWithVisibleElement(this.firstName, AppConstants.DEFAULT_LARGE_TIME_OUT, firstName);
		eleUtil.doSendKeys(this.lastName, lastName);
		eleUtil.doSendKeys(this.email, email);
		eleUtil.doSendKeys(this.telephone, telephone);
		eleUtil.doSendKeys(this.password, password);
		eleUtil.doSendKeys(this.confirmPassword, password);
		if(subscriber.equalsIgnoreCase("yes")) {
			eleUtil.doClick(this.subscriberYes);
		}else
		{
			eleUtil.doClick(this.subscriberNo);
		}
		
		eleUtil.doClick(this.agreePolicyCheckBox);
		eleUtil.doClick(this.continueBTN);
		
		String getConfirmationMessageText=eleUtil.waitForElementVisible(registartionConfirmMessage, AppConstants.DEFAULT_LARGE_TIME_OUT).getText();
		System.out.println("Resistartion is successfully!!!!........"+getConfirmationMessageText);
		
		eleUtil.doClick(logout);
		
		eleUtil.doClick(register);
		return getConfirmationMessageText;
	}
	

}
