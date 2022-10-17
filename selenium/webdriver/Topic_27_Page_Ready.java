package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_27_Page_Ready {

	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	WebDriverWait explicitWait;
	FluentWait<WebDriver> fluentDriver;
	JavascriptExecutor jsExecutor;
	Actions action;

	FluentWait<WebElement> fluentElement;

	long allTime = 15; // second
	long pollingTime = 1000; // milisecond

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Mac OS")) {//
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		}

		driver = new FirefoxDriver();
		jsExecutor = (JavascriptExecutor) driver;
		action = new Actions(driver);

		// Apply 15s cho các trạng thái cụ thể
		explicitWait = new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_OrangeHRM() {

		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		String employeeName = "Peter Mac";

		driver.findElement(By.cssSelector("input[name='username']")).sendKeys("Admin");
		driver.findElement(By.cssSelector("input[type='password']")).sendKeys("admin123");
		// Click xong chuyển sang trang mới
		driver.findElement(By.cssSelector("button[type='submit']")).click();

		Assert.assertTrue(isPageLoadedSuccess());

		driver.findElement(By.xpath("//span[text()='PIM']")).click();
		Assert.assertTrue(isPageLoadedSuccess());

		driver.findElement(By.xpath("//label[text()='Employee Name']/parent::div/following-sibling::div/div/div/input")).sendKeys(employeeName);
		// Click xong load lại 1 phần trang
		driver.findElement(By.cssSelector("button[type='submit']")).click();
		Assert.assertTrue(isPageLoadedSuccess());

		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='" + employeeName + "']")).isDisplayed());

	}

	@Test
	public void TC_02() {

		driver.get("https://blog.testproject.io/");
		String keyWord = "Selenium";

		if (driver.findElement(By.cssSelector("div.mailch-wrap")).isDisplayed()) {
			System.out.println("Close Popup");
			driver.findElement(By.cssSelector("div#close-mailch")).click();
		}

		action.moveToElement(driver.findElement(By.cssSelector("section#search-2 input.search-field"))).perform();
		Assert.assertTrue(isPageLoadedSuccess());
		System.out.println("Loading first time");

		driver.findElement(By.cssSelector("section#search-2 input.search-field")).sendKeys(keyWord);
		System.out.println("SendKey");

		driver.findElement(By.cssSelector("section#search-2 span.glass")).click();
		Assert.assertTrue(driver.findElement(By.cssSelector("h2.page-title>span")).isDisplayed());

		Assert.assertTrue(isPageLoadedSuccess());

		List<WebElement> postTitles = driver.findElements(By.cssSelector("h3.post-title>a"));
		for (WebElement title : postTitles) {
			String postTitle = title.getText();
			System.out.println(postTitle);
			Assert.assertTrue(postTitle.contains(keyWord));

		}

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public boolean isPageLoadedSuccess() {
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return (Boolean) jsExecutor.executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
			}
		};
		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}

		};
		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}

}
