package com.qa.opencart.util;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.factory.DriverFactory;

public class ElementUtil {

	private WebDriver driver;
	private Select sel;
	private Actions act;
	private JavascriptExecutor js;
	private WebDriverWait wait;
	private JavaScriptUtil jsUtil;
	private static final String ELEMENT_NOT_FOUND_ERROR="ELEMENT IS NOT VISIBLE ON THE PAGE: ";

	public ElementUtil(WebDriver driver) {
		this.driver = driver;
		jsUtil = new JavaScriptUtil(driver);
	}

	public void doClick(By locator) {
		getElement(locator).click();
	}

	public void doSendKeys(By locator, String value) {
		WebElement ele =getElement(locator);
		ele.clear();
		ele.sendKeys(value);
	}

	public WebElement getElement(By locator) {
		WebElement ele = driver.findElement(locator);
		if(Boolean.parseBoolean(DriverFactory.highlight)) {
			jsUtil.flash(ele);
		}
		return ele;
	}

	public String doGetText(By locator) {
		return getElement(locator).getText();
	}

	public void doSearchCheckAndVerifyTextOfHeader(By searchlocator, String sendKeys, By locatorLists, String checktext,
			By headerT, String compareText) throws InterruptedException {
		doSendKeys(searchlocator, sendKeys);
		Thread.sleep(8000);
		List<WebElement> searchList = getElemets(locatorLists);
		System.out.println(searchList.size());
		for (WebElement e : searchList) {
			String text = e.getText();
			System.out.println(text);
			if (text.contains(checktext)) {
				e.click();
				break;
			}
		}
		String copyText = getElement(headerT).getText();
		if (copyText.equals(compareText)) {
			System.out.println(copyText + ": Text is correct and Passed");
		} else {
			System.out.println("text not matched Failed");
		}
	}

	public void doSearch(String tagName, String text) {
		By printed = By.xpath("//" + tagName + "[text()='" + text + " ']");
		getElement(printed).click();
	}

	public String getAttributeText(By locator, String value) {
		return getElement(locator).getAttribute(value);
	}

	public boolean doElementIsDisplayed(By locator) {
		return getElement(locator).isDisplayed();
	}

	public boolean isSingleElementPresent(By locator) {
		List<WebElement> links = getElemets(locator);
		System.out.println(links.size());
		if (links.size() == 1) {
			System.out.println("Element is present");
			return true;
		} else {
			System.out.println("Element is not present");
			return false;
		}
	}

	public List<WebElement> getElemets(By locator) {
		List<WebElement> elements = driver.findElements(locator);
		for(WebElement element1 : elements) {
		if(Boolean.parseBoolean(DriverFactory.highlight)) {
			jsUtil.flash(element1);
		}
		}
		return elements;
	}

	public int getListSize(By locator) {
		return getElemets(locator).size();
	}

	public ArrayList<String> getElementsTextList(By locator) {
		List<WebElement> elementList = getElemets(locator);
		// List<String> totalText = new ArrayList<String>();
		ArrayList<String> totalText = new ArrayList<String>();
		for (WebElement e : elementList) {
			String text = e.getText();
			totalText.add(text);
		}
		return totalText;
	}

	public ArrayList<String> getElementsAttributeTextList(By locator, String value) {
		List<WebElement> attributeElementList = getElemets(locator);
		// List<String> totalText = new ArrayList<String>();
		ArrayList<String> totalAttributeText = new ArrayList<String>();
		for (WebElement e : attributeElementList) {
			String text = e.getAttribute(value);
			totalAttributeText.add(text);
		}
		return totalAttributeText;
	}

	// ****************Select Utility*********************//
	// Only for Select tag name
	public void doSelectDropDownByIndex(By locator, int index) {
		sel = new Select(getElement(locator));
		sel.selectByIndex(index);
	}

	public void doSelectDropDownByValue(By locator, String value) {
		sel = new Select(getElement(locator));
		sel.selectByValue(value);
	}

	public void doSelectDropDownByVisibleText(By locator, String VisibleText) {
		sel = new Select(getElement(locator));
		sel.selectByVisibleText(VisibleText);
	}

	public List<WebElement> getDropDownOptionsCount(By locator) {
		sel = new Select(getElement(locator));
		return sel.getOptions();
	}

	public int doGetDropDownCount(By locator) {
		return getDropDownOptionsCount(locator).size();
	}

	public List<String> doGetDropDownListText(By locator) {
		List<WebElement> optionCount = getDropDownOptionsCount(locator);
		List<String> addText = new ArrayList<String>();
		for (WebElement e : optionCount) {
			String str = e.getText();
			addText.add(str);
		}
		return addText;
	}

	public ArrayList<String> googleSearch(By searchLocator, String senKeyValue, By locator, String value)
			throws InterruptedException {
		doSendKeys(searchLocator, senKeyValue);
		Thread.sleep(4000);
		List<WebElement> allSuggList = getElemets(locator);
		ArrayList<String> listText = new ArrayList<String>();
		for (WebElement e : allSuggList) {
			String str = e.getText();
			if (str.equals(value)) {
				e.click();
				break;
			}
			listText.add(str);
		}
		return listText;
	}

//--------------SHADOW DOM Element handling--------------------------//
	public void sendTextOverShadowDomElement(String jsScript, String text) {
		js = (JavascriptExecutor) driver;
		WebElement ele = (WebElement) js.executeScript("return " + jsScript);
		ele.sendKeys(text);
	}

//-------------Pesudo Element----------------//
	public boolean checkElementIsMondatory(String jsScript) {
		js = (JavascriptExecutor) driver;
		String fieldText = js.executeScript("return " + jsScript).toString();
		// window.getComputedStyle(document.querySelector("label[for='input-firstname']"),
		// '::before').getPropertyValue('content')
		System.out.println(fieldText);
		if (fieldText.contains("*")) {
			System.out.println("Element is a mandatory ");
			return true;
		} else {
			System.out.println("Element is not a mandatory ");
			return false;
		}

	}

//---------------------Handling Action methods-----------------//
	public void handleLevelMenuItems(By parentMenu, By childMenu) throws InterruptedException {
		act = new Actions(driver);
		act.moveToElement(getElement(parentMenu)).build().perform();
		Thread.sleep(3000);
		getElement(childMenu).click();
	}

	public void doActionsSendkeys(By locator, String text) {
		act = new Actions(driver);
		act.sendKeys(getElement(locator), text).build().perform();
	}

	public void doActionsClick(By locator) {
		act = new Actions(driver);
		act.click(getElement(locator)).build().perform();
	}

//--------------------------Wait util-------------------------//
	/*
	 * //An expectation for checking that an element is present on the DOM of a
	 * page. //This does not necessarily mean that the element is visible.
	 */
	public WebElement waitForElementPresence(By locator, int timeouts) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(timeouts));
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	public void doSendKeysWithWait(By locator, int timeouts, String text) {
		waitForElementPresence(locator, timeouts).sendKeys(text);
	}

	public void doClickWithWait(By locator, int timeouts) {
		waitForElementPresence(locator, timeouts).click();
	}

	public String getElementWithWait(By locator, int timeouts) {
		return waitForElementPresence(locator, timeouts).getText();
	}

	/*
	 * Default pooling time is 500 milliseconds An expectation for checking that an
	 * element is present on the DOM of a page and visible. Visibility means that
	 * the element is not only displayed but also has a height and width that is
	 * greater than 0.
	 */
	public WebElement waitForElementVisible(By locator, int timeouts) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(timeouts));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	/**
	 * IMP question Default polling time = Custom polling time
	 * 
	 * @param locator
	 * @param timeouts
	 * @param pollingTime=Customized polling time- we can enter manually time here
	 */
	public void WaitForElementVisible(By locator, int timeouts, int pollingTime) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeouts), Duration.ofSeconds(pollingTime));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	/**
	 * An expectation for checking that all elements present on the web page that
	 * match the locatorare visible. Visibility means that the elements are not only
	 * displayed but also have a heightand width that is greater than 0.
	 * 
	 * @param locator
	 * @param timeOut
	 * @return
	 */
	public List<WebElement> WaitForElementTobeVisible(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}

	public WebElement waitForElementToBeVisibleWithFluentWait(By locator, int timeOut, int pollingTimeOut) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeOut))
				.pollingEvery(Duration.ofSeconds(pollingTimeOut)).ignoring(NoSuchElementException.class)
				.withMessage(ElementUtil.ELEMENT_NOT_FOUND_ERROR + locator);
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

	}

	public void doSendKeysWithVisibleElement(By locator, int timeouts, String text) {
		waitForElementPresence(locator, timeouts).sendKeys(text);
	}

	public void doClickWithVisibleElement(By locator, int timeouts) {
		waitForElementPresence(locator, timeouts).click();
	}

	public String getElementWithVisibleElement(By locator, int timeouts) {
		return waitForElementPresence(locator, timeouts).getText();
	}

	public void clickWhenReady(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
	}

	public void clickWhenReady(WebElement element, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.elementToBeClickable(element)).click();
	}

	// ---------------Alerts--------------------//
	public Alert waitForAlert(int timeouts) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(timeouts));
		return wait.until(ExpectedConditions.alertIsPresent());
	}

	public String getAlertText(int timeouts) {
		return waitForAlert(timeouts).getText();
	}

	public void acceptAlert(int timeouts) {
		waitForAlert(timeouts).accept();
	}

	public void dismissAlert(int timeouts) {
		waitForAlert(timeouts).dismiss();
	}

	public void alertWithSendkeys(int timeouts, String value) {
		waitForAlert(timeouts).sendKeys(value);
	}

	public String waitForTitleContains(int timeouts, String titleFraction) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(timeouts));
		if (wait.until(ExpectedConditions.titleContains(titleFraction))) {
			return driver.getTitle();
		} else {
			return null;
		}
	}

	public String waitForTitles(int timeouts, String titleFraction) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(timeouts));
		if (wait.until(ExpectedConditions.titleIs(titleFraction))) {
			return driver.getTitle();
		} else {
			return null;
		}
	}

	public String waitForURLContains(int timeouts, String urlFraction) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(timeouts));
		if (wait.until(ExpectedConditions.urlContains(urlFraction))) {
			return driver.getCurrentUrl();
		} else {
			return null;
		}
	}

	public String waitForURLIs(int timeouts, String urlFraction) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(timeouts));
		if (wait.until(ExpectedConditions.urlToBe(urlFraction))) {
			return driver.getCurrentUrl();
		} else {
			return null;
		}
	}

	/// ------------------------Wait with Frame-------------------//
	public void waitForFrame(int timeouts, By frameLocator) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(timeouts));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
	}

	public void waitForFrame(int timeouts, String frameNameOrId) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(timeouts));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameNameOrId));
	}

	public void waitForFrame(int timeouts, int frameIndex) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(timeouts));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameIndex));
	}

	public void waitForFrame(int timeouts, WebElement frameElement) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(timeouts));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameElement));
	}
	
	//---------------------Custom Wait----------------//
	public WebElement retryingElement(By locator, int timeOut) {
		WebElement element = null;
		int attempts = 0;
		while (attempts < timeOut) {
			try {
				element = getElement(locator);
				System.out.println("Element is found at: " + attempts + " attempt");
				break;
			} catch (Exception e) {
				System.out.println("Element not is found in attempts: " + attempts + " : " + "for" + locator);
				try {
					Thread.sleep(500);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}

			attempts++;
		}
		if (element == null) {
			System.out.println(
					"Element is not found expection...tried for: " + timeOut + "secs " + "with the interval of " + 500+ " ms");
		}
		return element;
	}
	
	public WebElement retryingElement(By locator, int timeOut, int pollingTime) {
		int attempts=0;
		WebElement element=null;
		
		while(attempts < timeOut) {
			
			try {
			element=getElement(locator);
			System.out.println("Element is found in "+attempts+" attempt");
			break;
			}catch(Exception e) {
				System.out.println("Element not fount in attempts "+attempts+" : "+" for "+timeOut+" secs.");
				try {
					Thread.sleep(pollingTime);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			attempts++;
		}
		if(element==null) {
			try {
				throw new Exception("ELEMENTNOTFOUNDEXCEPTION");
				
			}catch(Exception e)
			{
				System.out.println("Element is not found... tried for "+timeOut+ "secs " + " With the interval of "+pollingTime+" ms");
			}
		}
		return element;
	}
}
