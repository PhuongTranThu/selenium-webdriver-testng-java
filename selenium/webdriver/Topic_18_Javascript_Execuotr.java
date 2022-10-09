package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_18_Javascript_Execuotr {

	WebDriver driver;
	JavascriptExecutor jsExecutor;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Mac OS")) {//
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		}

		driver = new FirefoxDriver();
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01() {
		navigateToUrlByJS("http://live.techpanda.org/");
		sleepInSecond(20);

		// *********************
		String techPandaDomain = (String) executeForBrowser("return document.domain");
		Assert.assertEquals(techPandaDomain, "live.techpanda.org");
		sleepInSecond(5);

		String homePageUrl = (String) executeForBrowser("return document.URL");
		Assert.assertEquals(homePageUrl, "http://live.techpanda.org/");
		sleepInSecond(5);

		// *********************
		hightlightElement("//a[text()='Mobile']");
		clickToElementByJS("//a[text()='Mobile']");
		sleepInSecond(5);

		hightlightElement("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button");
		clickToElementByJS("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button");
		sleepInSecond(5);

		Assert.assertTrue(areExpectedTextInInnerText("Samsung Galaxy was added to your shopping cart."));

		hightlightElement("//a[text()='Customer Service']");
		clickToElementByJS("//a[text()='Customer Service']");
		sleepInSecond(5);

		hightlightElement("//input[@id='newsletter']");
		scrollToElementOnTop("//input[@id='newsletter']");
		sleepInSecond(5);

		String emailAdress = "afc" + generateRandomNumber() + "@hotmail.vn";
		sendkeyToElementByJS("//input[@id='newsletter']", emailAdress);
		sleepInSecond(5);

		hightlightElement("//button[@title='Subscribe']");
		clickToElementByJS("//button[@title='Subscribe']");
		sleepInSecond(5);

		Assert.assertTrue(areExpectedTextInInnerText("Thank you for your subscription."));

		navigateToUrlByJS("https://demo.guru99.com");
		sleepInSecond(3);
		String demoGuruDomain = (String) executeForBrowser("return document.domain");
		Assert.assertEquals(demoGuruDomain, "demo.guru99.com");

	}

	@Test
	public void TC_02() {
		driver.get("https://warranty.rode.com/");
		sleepInSecond(3);

		By registerButton = By.xpath("//button[contains(text(),'Register')]");
		driver.findElement(registerButton).click();
		sleepInSecond(3);

		// Firstname
		Assert.assertEquals(getElementValidationMessage("//input[@id='firstname']"), "Please fill out this field.");
		driver.findElement(By.xpath("//input[@id='firstname']")).sendKeys("Automation");

		driver.findElement(registerButton).click();
		sleepInSecond(2);

		// Lastname
		Assert.assertEquals(getElementValidationMessage("//input[@id='surname']"), "Please fill out this field.");
		driver.findElement(By.xpath("//input[@id='surname']")).sendKeys("test");

		driver.findElement(registerButton).click();
		sleepInSecond(2);

		// Email
		Assert.assertEquals(getElementValidationMessage("//div[contains(text(),'Register')]/following-sibling::div//input[@id='email']"), "Please fill out this field.");
		driver.findElement(By.xpath("//div[contains(text(),'Register')]/following-sibling::div//input[@id='email']")).sendKeys("1234@1234@123");

		driver.findElement(registerButton).click();
		sleepInSecond(2);

		Assert.assertEquals(getElementValidationMessage("//div[contains(text(),'Register')]/following-sibling::div//input[@id='email']"), "Please enter an email address.");
		driver.findElement(By.xpath("//div[contains(text(),'Register')]/following-sibling::div//input[@id='email']")).clear();
		driver.findElement(By.xpath("//div[contains(text(),'Register')]/following-sibling::div//input[@id='email']")).sendKeys("phuong@gmail.com");

		driver.findElement(registerButton).click();
		sleepInSecond(2);

		Assert.assertEquals(getElementValidationMessage("//div[contains(text(),'Register')]/following-sibling::div//input[@id='password']"), "Please fill out this field.");
		sleepInSecond(2);

	}

	@AfterClass
	public void afterClass() {
		// driver.quit();
	}

	// Sleep cứng (Static wait)
	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Object executeForBrowser(String javaScript) {
		return jsExecutor.executeScript(javaScript);
	}

	public String getInnerText() {
		return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
	}

	public boolean areExpectedTextInInnerText(String textExpected) {
		String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage() {
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(String url) {
		jsExecutor.executeScript("window.location = '" + url + "'");
	}

	public void hightlightElement(String locator) {
		WebElement element = getElement(locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
	}

	public void clickToElementByJS(String locator) {
		jsExecutor.executeScript("arguments[0].click();", getElement(locator));
	}

	public void scrollToElementOnTop(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
	}

	public void scrollToElementOnDown(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getElement(locator));
	}

	public void sendkeyToElementByJS(String locator, String value) {
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
	}

	public void removeAttributeInDOM(String locator, String attributeRemove) {
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
	}

	public String getElementValidationMessage(String locator) {
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
	}

	public boolean isImageLoaded(String locator) {
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", getElement(locator));
		return status;
	}

	public WebElement getElement(String locator) {
		return driver.findElement(By.xpath(locator));
	}

	public int generateRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(99999);
	}
}
