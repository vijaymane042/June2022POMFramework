package com.qa.opencart.constants;

import java.util.Arrays;
import java.util.List;

public class AppConstants {

	public static final int DEFAULT_TIME_OUT= 4 ;
	public static final int DEFAULT_LARGE_TIME_OUT= 10 ;
	
	public static final String LOGIN_PAGE_TITLE="Account Login";
	public static final String ACCOUNT_PAGE_TITLE="My Account";
	
	public static final String LOGIN_PAGE_URL_PARAM="naveenautomationlabs";
	public static final String ACCOUNT_PAGE_URL_PARAM="route=account/account";
	
	public static final List<String> ACCONT_PAGE_SECTION_HEADER= Arrays.asList("My Account","My Orders","My Affiliate Account","Newsletter");
	
	public static final int PRODUCT_IMAGE_COUNT=4;
	public static final String ACC_CREATE_SUCC_MESSAGE = "Your Account Has Been Created!";
	//-----------------------------Excel constants--------------------//
	public static final String REGISTER_SHEET_NAME = "register";
}
