// this is the heart of the framework
// this contains all the java classes used to do most things on a webpage, you can add to these to build up the framework
// note to change where you are running the tests, currently set to local hosts but change to run on a VM
package co.uk.ordnancesurvey.testutils;

import java.io.FileInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;

public class WebConnector {

	Logger APPLICATION_LOGS = Logger.getLogger("devpinoyLogger");
	Properties OR = null;
	Properties CONFIG = null;
	WebDriver driver = null;
	WebDriver webDriver = null;
	WebDriver chrome = null;
	WebDriver internetExplorer = null;
	static WebConnector w;

	public WebConnector() {

		if (OR == null) {
			try {
				OR = new Properties();
				System.out.println(Constants.OR_FILE_PATH);
				FileInputStream fs = new FileInputStream(Constants.OR_FILE_PATH);

				OR.load(fs);
				System.out.println("OR properties loaded");

			} catch (Exception e) {
				System.out.println("Error on intializing OR properties files");
			}

			if (CONFIG == null) {
				try {
					CONFIG = new Properties();

					FileInputStream fs = new FileInputStream(
							Constants.CONFIG_FILE_PATH);
					CONFIG.load(fs);
					System.out.println("CONFIG properties loaded");

				} catch (Exception e) {
					System.out
							.println("Error on intializing CONFIG properties files");
				}
			}
		}
	}

	/******** Singleton **********/
	public static WebConnector getInstance() {
		if (w == null)
			w = new WebConnector();

		return w;
	}

	/************** Logging ***************/
	public void log(String msg) {
		APPLICATION_LOGS.debug(msg);
	}

	public WebDriver sharedDriver(Browser browser) {
		log("Opening Driver ");
		try {

			DesiredCapabilities cap = null;
			switch (browser) {
			case FIREFOX:

				cap = DesiredCapabilities.firefox();
				cap.setBrowserName("firefox");
				// cap.setCapability("marionette", true);
				//System.setProperty("webdriver.gecko.driver","C:\\JAR\\geckodriver.exe");
				System.setProperty("webdriver.gecko.driver","C:\\Selenium grid\\3.8.1\\geckodriver.exe"); 
				System.out.println("Opening " + cap.getBrowserName()
						+ " browser");
				cap.setPlatform(org.openqa.selenium.Platform.ANY);
				driver = new RemoteWebDriver(new URL(
						"http://localhost:4444/wd/hub"), cap);
				driver.manage().deleteAllCookies();
				break;

			case CHROME:
				cap = DesiredCapabilities.chrome();
				cap.setBrowserName("chrome");
				System.out.println(cap.getBrowserName());
				cap.setPlatform(org.openqa.selenium.Platform.ANY);
				System.out.println(cap.getPlatform());
				/*System.setProperty("webdriver.chrome.driver","C:\\Drivers\\chromedriver.exe");
							
				 ChromeOptions options = new ChromeOptions();
				 options.addArguments("test-type", "chrome.switches",
				 "--disable-extensions", "disable-infobars");
				 options.addArguments(
				 "user-data-dir=C://Users//OFIPTest1//AppData//Local//Google//Chrome//User Data//Profile 1"
				 ); //cap.setCapability("chrome.binary","<<your chrome path>>");
				  cap.setCapability(ChromeOptions.CAPABILITY, options);*/
				 
				driver = new RemoteWebDriver(new URL(
						"http://localhost:4444/wd/hub"), cap);
				System.out.println("Past Driver");
				driver.manage().deleteAllCookies();
				break;

			case IE:
				cap = DesiredCapabilities.internetExplorer();
				cap.setBrowserName("internet explorer");
				System.out.println(cap.getBrowserName());
				cap.setPlatform(org.openqa.selenium.Platform.ANY);
				cap.setCapability(
						InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
						true);
				cap.setCapability(
						InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
				driver = new RemoteWebDriver(
						new URL("http://localhost:4444/wd/hub"), cap);
				driver.manage().deleteAllCookies();
				break;
			}

			// max
			driver.manage().window().maximize();
			System.out.println("Maximise Window");
			// implicit wait
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			System.out.println("Driver Wait");
			webDriver = driver;
			System.out.println("Webdriver");

			// max
			driver.manage().window().maximize();
			// implicit wait
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		} catch (Exception e) {
			System.out.println("Error on intializing properties files");
		}

		return webDriver;
	}

	/******** Helper methods **********/

	//This Method is to Navigate to URL
	public void navigate(String URL) {
		// log("Naviating to "+URL);
		System.out.println("Naviating to " + URL);
		//driver.manage().deleteAllCookies();
		driver.get(CONFIG.getProperty(URL));
		System.out.println("Step Pass");
	}

	//This method is to Check if the link exists 
	public void checkIflinkExists(String linkname) {
		WebElement link = driver.findElement(By.linkText(linkname));
		System.out.println(link.getText());
		Assert.assertNotNull(link);

	}

	// clicking on any object
	public void click(String objectName) throws InterruptedException {
		log("Clicking on " + objectName);
		Thread.sleep(100);
		driver.findElement(By.xpath(OR.getProperty(objectName))).isEnabled();
		System.out.println(objectName + "element is clickable ");
		driver.findElement(By.xpath(OR.getProperty(objectName))).click();

		Thread.sleep(200);
	}

	//Click on Link Text
	public void clickOnLinkText(String linkname) {
		log("Clicking on " + linkname);
		driver.findElement(By.linkText(linkname)).click();
	}
//This method is to send keys or Type 
	public void type(String text, String objectName) {
		log("Typing in " + objectName);
		driver.findElement(By.xpath(OR.getProperty(objectName))).sendKeys(text);
		System.out.println(text);
	}
    
	//This method is to send keys to enter 
		public void sendKeys(String text, String objectName) {
			log("Typing in " + objectName);
			driver.findElement(By.xpath(OR.getProperty(objectName))).sendKeys(Keys.ENTER);
			System.out.println(text);
		}
	//This method is to select choice from the dropdown
	public void selectFromDropdown(String objectName1, String objectName2)
			throws InterruptedException {
		Thread.sleep(2000);
		WebElement listbox = driver.findElement(By.xpath(OR
				.getProperty(objectName2)));
		WebElement option = driver.findElement(By.xpath(OR
				.getProperty(objectName1)));
		Actions action = new Actions(driver);
		action.moveToElement(listbox).clickAndHold(listbox)
				.moveToElement(option)

				.click(option).build().perform();
		Thread.sleep(3000);
	}

	// use this method to checkin checkbox by sending keys.space
	public void select(String text, String objectName) {
		log("Selecting from " + objectName);
		driver.findElement(By.xpath(OR.getProperty(objectName))).sendKeys(text);
	}

	//This method is to delete cookies
	public void deleteCookies() {
		driver.manage().deleteAllCookies();
	}

	//This method is to check WebElement present
	public boolean isElementPresent(String objectName) {
		log("Checking object presence " + objectName);
		int count = driver.findElements(By.xpath(OR.getProperty(objectName)))
				.size();

		return count == 0 ? false : true;

	}

	//This method is to use Default login Credentials from Config
	public void doDefaultLogin() throws InterruptedException {
		navigate("loginURL");
		type(CONFIG.getProperty("defaultUsername"), "loginusername");
		type(CONFIG.getProperty("defaultPassword"), "loginpassword");
		click("loginButton");
	}

	//This method is used to check Text Present on Webpage
	public boolean isTextPresent(String objectName, String expected) {
		log("Checking object presence " + objectName);
		String actual = driver
				.findElement(By.xpath(OR.getProperty(objectName))).getText();
		driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
		System.out.println(expected);

		return actual.equals(expected);
	}

	//This Method is used to get text of a WebElement
	public String getText(String objectname) {
		String actualText = driver.findElement(
				By.xpath(OR.getProperty(objectname))).getText();
		return actualText;
	}

	//This method to get the PageTiTle
	public String getPageTitle() {
		return driver.getTitle();

	}

	//This method is used to get Current URL
	public String getCurrentUrl() {
		return driver.getCurrentUrl();

	}

	//This method is used to wait 50 seconds allow the page to load
	public void implicitWait() {
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
	}

	//This method is used to select the required option from a list of options from a drop down
	public void selectOptionByTextDropDown(String objectOne, String objectTwo)
			throws InterruptedException {
		Thread.sleep(2000);
		Select dropdown = new Select(driver.findElement(By.xpath(OR
				.getProperty(objectTwo))));
		dropdown.selectByVisibleText(objectOne);
		Thread.sleep(3000);
	}

	//This method is used to select the required option from a list of options from a drop down
	public List<String> getOptionsFromDropdown(String dropdownObject) {
		List<String> optionsFromDropdown = new ArrayList<String>();

		Select dropdown = new Select(driver.findElement(By.xpath(OR
				.getProperty(dropdownObject))));
		List<WebElement> list = dropdown.getOptions();

		for (WebElement webElement : list) {
			String text = webElement.getText();
			optionsFromDropdown.add(text);
		}

		return optionsFromDropdown;
	}

	//This method is to Navigate Back
	public void navigateBack() {
		driver.navigate().back();

	}

	//This method is used to close browser after a test is finished
	public void closeBrowser() {
		driver.close();
		// driver.quit();
	}

	//This method is used to switch to alerts to accept or decline 
	public void alert() {
		Alert alert = driver.switchTo().alert();
		System.out.println(alert.getText());
		alert.dismiss();

	}

	//This method is used to switch the focus to next window
	public void switchToNextWindow() {

		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
		}

	}

	//This method is used to Switch Back the focus to Main window
	public void switchToMainWindow() {

		// get current window
		String mainWindowHandle = driver.getWindowHandles().iterator().next();
		// do what you want in other window
		// ...
		// switch back
		driver.switchTo().window(mainWindowHandle);
	}

	//This method is used to check whether the object is displayed eg:image
	public boolean isObjectDisplayed(String object) {
		return driver.findElement(By.xpath(OR.getProperty(object)))
				.isDisplayed();
	}

	//This method is used to count the no of rows in a table
	public int countRowsInATable() {
		List<WebElement> rows = driver.findElements(By.cssSelector("tbody tr"));
		return rows.size();
	}

	public List<WebElement> getWebElementsFromContainer(String containerId,
			String elementName) {
		return driver.findElements(By.cssSelector("#" + containerId + " "
				+ elementName));
	}

	//This method is used to get element attribute
	public String getElementAttribute(String elementId, String attributeName) {
		return driver.findElement(By.id(elementId)).getAttribute(attributeName);
	}

	//This method is used to check if the Element is present by class
	public boolean checkIfElementIsPresentByCssClass(String className) {
		return driver.findElements(By.cssSelector(className)).size() > 0;
	}
}
