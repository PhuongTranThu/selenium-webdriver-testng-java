package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_20_Element_Condition_Status {

	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	WebDriverWait explicitWait;

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Mac OS")) {//
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		}

		driver = new FirefoxDriver();

		explicitWait = new WebDriverWait(driver, 10);

	}

	@Test
	public void TC_01_Visible_Displayed_Visibility_I() {
		driver.get("https://www.facebook.com/");
		sleepInSecond(2);
		// 1. Có trên UI (bắt buộc)
		// 1. Có trong HTML (bắt buộc)

		// Wait cho email address hiển thị
		// Chờ cho email address hiển thị trong vòng 10s
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));

		driver.findElement(By.id("email")).sendKeys("automation@gmail.net");
	}

	@Test
	public void TC_02_Invible_Undisplayed_Invisibility_I() {
		// 2. Không có trên UI (bắt buộc)
		// 1. Có trong HTML
		driver.get("https://www.facebook.com/");

		driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();

		// Chờ cho Re-enter Email testbox ko hiển thị trong vòng 10s
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.name("reg_email_confirmation__")));
	}

	@Test
	public void TC_02_Invible_Undisplayed_Invisibility_II() {
		// 2. Không có trên UI (bắt buộc)
		// 2. Không có trong HTML

		driver.get("https://www.facebook.com/");
		// Chờ cho Re-enter Email testbox ko hiển thị trong vòng 10s
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.name("reg_email_confirmation__")));
	}

	@Test
	public void TC_03_Presence_I() {
		// 1. Có trên UI
		// 1. Có trong HTML (bắt buộc)
		driver.get("https://www.facebook.com/");

		// Chờ cho email address text box presence trong HTML trong vòng 10s
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.id("email")));

	}

	@Test
	public void TC_03_Presence_II() {
		// 2. Không có trên UI
		// 1. Có trong HTML (bắt buộc)
		driver.get("https://www.facebook.com/");

		driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();

		// Chờ cho Re-enter Email testbox ko hiển thị trong vòng 10s
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.name("reg_email_confirmation__")));
	}

	@Test
	public void TC_04_Staleness() {
		// 2. Không có trên UI (bắt buộc)
		// 2. Không có trong HTML
		driver.get("https://www.facebook.com/");
		driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();

		// Phase 1: Element có trong cây HTML
		WebElement reEnterEmailAddressTextbox = driver.findElement(By.name("reg_email_confirmation__"));

		// Thao tác vs element làm cho element re-enter ko còn trong DOM nữa

		// Close popup đi
		driver.findElement(By.cssSelector("img._8idr")).click();

		// Chờ cho Re-enter Email testbox ko còn trong DOM trong vòng 10s
		explicitWait.until(ExpectedConditions.stalenessOf(reEnterEmailAddressTextbox));

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
}
