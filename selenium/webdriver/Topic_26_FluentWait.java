package webdriver;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.base.Function;

public class Topic_26_FluentWait {

	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	WebDriverWait explicitWait;
	FluentWait<WebDriver> fluentDriver;

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

		// Apply 15s cho các trạng thái cụ thể
		explicitWait = new WebDriverWait(driver, 15);

		driver.manage().window().maximize();

	}

	@Test
	public void TC_01() {

		driver.get("https://automationfc.github.io/dynamic-loading/");

		findElement("//div[@id='start']/button").click();

		Assert.assertEquals(findElement("//div[@id='finish']/h4").getText(), "Hello World!");

	}

	@Test
	public void TC_02() {

		driver.get("https://automationfc.github.io/fluent-wait/");

		WebElement countdountTime = findElement("//div[@id='javascript_countdown_time']");

		fluentElement = new FluentWait<WebElement>(countdountTime);

		fluentElement.withTimeout(Duration.ofSeconds(allTime)).pollingEvery(Duration.ofMillis(pollingTime)).ignoring(NoSuchElementException.class);

		fluentElement.until(new Function<WebElement, Boolean>() {

			@Override
			public Boolean apply(WebElement element) {
				String text = element.getText();
				System.out.println(text);
				return text.endsWith("00");
			}
		});
	}

	public WebElement findElement(final String xpathLocator) {
		fluentDriver = new FluentWait<WebDriver>(driver);

		// Set tổng thời gian và tần số
		fluentDriver.withTimeout(Duration.ofSeconds(allTime))
				// 1/3 giaay check 1 lần
				.pollingEvery(Duration.ofMillis(pollingTime)).ignoring(NoSuchElementException.class);

		// Apply điều kiện
		return fluentDriver.until(new Function<WebDriver, WebElement>() {
			@Override
			public WebElement apply(WebDriver driver) {
				return driver.findElement(By.xpath(xpathLocator));
			}
		});
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
