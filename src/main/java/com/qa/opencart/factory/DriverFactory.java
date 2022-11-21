package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;



import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.qa.opencart.errors.AppErrors;
import com.qa.opencart.exception.FrameworkException;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	public WebDriver driver;
	public Properties prop;
	public OptionsManager optionsManager;

	private static final Logger LOG = Logger.getLogger(DriverFactory.class);
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	public static String highlight;

	/**
	 * this method is used to initialize the web driver as given below browserName
	 * 
	 * @param browserName
	 * @return this will return the web driver
	 */
	public WebDriver initDriver(Properties prop) {
		String browserName = prop.getProperty("browser").toLowerCase();
		//String browserName = System.getProperty("env");
		LOG.info("Running the browser :" + browserName);
		highlight = prop.getProperty("highlight").trim();
		 optionsManager = new OptionsManager(prop);

		if (browserName.equals("chrome")) {
			
			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				// remote run:
				init_remoteDriver("chrome");
			} else {
				// local run:
				WebDriverManager.chromedriver().setup();
				tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			}
		} else if (browserName.equals("firefox")) {
			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				// remote run:
				init_remoteDriver("firefox");
			} else {
				// local run:
				WebDriverManager.firefoxdriver().setup();
				tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
			}
		} else if (browserName.equals("edge")) {
			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				// remote run:
				init_remoteDriver("edge");
			} else {
				// local run:
				WebDriverManager.edgedriver().setup();
				tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
			}
		} else {
			System.out.println("Please pass the right browser : " + browserName);
			LOG.error("pass the right borwser :" + browserName);
			throw new FrameworkException(AppErrors.BROWSER_NOT_EXCEPTION);
		}
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("URL"));
		return getDriver();
	}
/**
 * remote execution
 */
	private void init_remoteDriver(String browser) {
	
		System.out.println("Running test cases on remote GRID machine....with browser: " + browser);

		if (browser.equals("chrome")) {
			try {
				tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")),optionsManager.getChromeOptions()));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		} 
		
		else if (browser.equals("firefox")) {
			try {
				tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")),optionsManager.getFirefoxOptions()));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}

		else if (browser.equals("edge")) {
			try {
				tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsManager.getEdgeOptions()));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Please pass the right browser for remote exution...." + browser);
		}
	}

	/**
	 * thread local will give you a local copy of each and every driver. one thread
	 * will not impact another one
	 * 
	 * @return it will return WebDriver
	 */
	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}

	/**
	 * this method is used to get(load) the all the values are stored in
	 * config.properties file
	 * 
	 * @return this will return all the values of the properties file
	 */
	public Properties initProp() {
		prop = new Properties();
		FileInputStream ip = null;
        
		String envName = System.getProperty("env");
		System.out.println("----->Running the test cases on :" + envName);
		LOG.warn("----->Running the test cases on :" + envName);
		if (envName == null) {
			try {
				System.out.println("No env is given ...hence running test cases on default environment");
				
				ip = new FileInputStream("./src/test/resources/config/config.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			try {
				switch (envName) {
				case "qa":
					ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
					break;
				case "dev":
					ip = new FileInputStream("./src/test/resources/config/dev.config.properties");
					break;

				default:

					System.out.println("please pass the right environment name");
					throw new FrameworkException(AppErrors.ENVIRONMENT_NOT_EXCEPTION);
					//break;
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		try {
			prop.load(ip);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop;
	}

	public static String getScreenshot() {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);

		try {
			FileUtils.copyFile(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}
}
