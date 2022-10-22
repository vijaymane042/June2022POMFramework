package com.qa.opencart.testcases;

import java.awt.Robot;
import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.util.ExcelUtil;

public class RegisterTest extends BaseTest{

	
	@BeforeClass
	public void regSetup() {
	registerPage=loginpage.navigateToRegisterPage();
	}
	
	@DataProvider()
	public Object[][] getRegData() {
	Object regData [][]=ExcelUtil.getTestData(AppConstants.REGISTER_SHEET_NAME);
	return regData;
	}
	
	
		public static String generateRandomPassword(int len) {
			String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijk"
	          +"lmnopqrstuvwxyz";
			Random rnd = new Random();
			StringBuilder sb = new StringBuilder(len);
			for (int i = 0; i < len; i++)
				sb.append(chars.charAt(rnd.nextInt(chars.length())));
			return sb.toString();
		}
	
	public  String getRondomEmail() {
		Random random = new Random();
		String email=generateRandomPassword(5)+""+random.nextInt(1000)+"@abc.com";
		System.out.println("Email is :" +email);
		return email;
	}
	@Test(dataProvider = "getRegData")
	public void registerUserTest(String firstname, String lastname, String telephone, String password, String subscriber) {
		String actregistrationMsg=registerPage.userRegister( firstname,  lastname,  getRondomEmail(),  telephone,  password,  subscriber );
		Assert.assertEquals(actregistrationMsg, AppConstants.ACC_CREATE_SUCC_MESSAGE);
	}
}
