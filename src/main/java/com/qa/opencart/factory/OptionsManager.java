package com.qa.opencart.factory;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionsManager {
	
	private ChromeOptions chromeOptions;
	private FirefoxOptions firefoxOptions;
	private EdgeOptions edgeOptions;
	private Properties prop;
	
	public OptionsManager(Properties prop) {
		this.prop = prop;
	}
	
	/**
	 * 
	 * @return this method will return chrome driver to run on headless or incognito mode
	 */
	public ChromeOptions getChromeOptions() {
		chromeOptions = new ChromeOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless"))) {
			chromeOptions.setHeadless(true);
		}
		if(Boolean.parseBoolean(prop.getProperty("incognito"))) {
			chromeOptions.addArguments("--incognito");
		}
//		if(Boolean.parseBoolean(prop.getProperty("remote"))) {
//			String browserVersion=prop.getProperty("browserversion");
//			chromeOptions.setBrowserVersion(browserVersion);
//			chromeOptions.setPlatformName("linux");
//			chromeOptions.setCapability("enableVNC", true);
//			chromeOptions.setCapability("name", "OpenAppTest -" + prop.getProperty("testname"));
//			
//		}
		return chromeOptions;
	}
	
	/**
	 * 
	 * @return this method will return firefox driver to run on headless or incognito mode
	 */
	public FirefoxOptions getFirefoxOptions() {
		firefoxOptions = new FirefoxOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless"))) {
			firefoxOptions.setHeadless(true);
		}
		if(Boolean.parseBoolean(prop.getProperty("firefoxprivate"))) {
			//firefoxOptions.addArguments("-private");
			firefoxOptions.addArguments("-private-window");
		}
//		if(Boolean.parseBoolean(prop.getProperty("remote"))) {
//			String browserVersion=prop.getProperty("browserversion");
//			firefoxOptions.setBrowserVersion(browserVersion);
//			firefoxOptions.setPlatformName("linux");
//			firefoxOptions.setCapability("enableVNC", true);
//			
//		}
		return firefoxOptions;
	}
	
	/**
	 * 
	 * @return this method will return edge driver to run on headless or incognito mode
	 */
	public EdgeOptions getEdgeOptions() {
		edgeOptions = new EdgeOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless"))) {
			edgeOptions.setHeadless(true);
		}
		if(Boolean.parseBoolean(prop.getProperty("edgeinprivate"))) {
			edgeOptions.addArguments("-InPrivate");
		}
		return edgeOptions;
	}

}
