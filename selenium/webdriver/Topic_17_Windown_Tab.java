package webdriver;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_17_Windown_Tab {

	WebDriver driver;
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

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Basic_Form() {

		// Driver đang ở trang A
		driver.get("https://automationfc.github.io/basic-form/index.html");
		sleepInSecond(2);

		String parentID = driver.getWindowHandle();

		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		sleepInSecond(2);

		// Switch qua trang B
		switchToWindownByTitle("Google");

		// Driver đang ở trang B
		driver.findElement(By.name("q")).sendKeys("Selenium");
		String googleTabID = driver.getWindowHandle();

		// Quay về trang A
		switchToWindownByTitle("SELENIUM WEBDRIVER FORM DEMO");

		// Driver đang ở trang A
		driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
		sleepInSecond(2);

		// Switch qua trang C
		switchToWindownByTitle("Facebook – log in or sign up");

		driver.findElement(By.id("email")).sendKeys("automationfc.vn@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("12345");

		// Quay về trang A
		switchToWindownByTitle("SELENIUM WEBDRIVER FORM DEMO");

		driver.findElement(By.xpath("//a[text()='TIKI']")).click();
		sleepInSecond(2);

		// Switch qua trang D
		switchToWindownByTitle("Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");

		closeAllWindowWithoutParent(parentID);
		sleepInSecond(2);

	}

	@Test
	public void TC_02_Tech_Panda() {
		// Đang ở trang Moble
		driver.get("http://live.techpanda.org/index.php/mobile.html");
		sleepInSecond(3);

		driver.findElement(By.xpath("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(), "The product Samsung Galaxy has been added to comparison list.");

		driver.findElement(By.xpath("//a[text()='Sony Xperia']/parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(), "The product Sony Xperia has been added to comparison list.");

		driver.findElement(By.cssSelector("button[title='Compare']")).click();

		// Switch qua Windows COmpare
		switchToWindownByTitle("Products Comparison List - Magento Commerce");

		Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='Compare Products']")).isDisplayed());

		driver.findElement(By.cssSelector("button[title='Close Window']")).click();
		sleepInSecond(3);

		// Switch qua Mobile
		switchToWindownByTitle("Mobile");

		driver.findElement(By.id("search")).sendKeys("Samsung Galaxy");
		sleepInSecond(3);

	}

	@Test
	public void TC_03_Cambridge_Dictionary() {
		// Đang ở trang Moble
		driver.get("https://dictionary.cambridge.org/vi/");
		sleepInSecond(3);

		driver.findElement(By.xpath("//div[@class='hdn hdib-s']//span[text()='Đăng nhập']")).click();
		sleepInSecond(3);

		switchToWindownByTitle("Login");

		driver.findElement(By.cssSelector("input[value='Log in']")).click();
		sleepInSecond(3);

		Assert.assertEquals(driver.findElement(By.cssSelector("input[aria-label='Email']~span[data-bound-to='loginID']")).getText(), "This field is required");
		Assert.assertEquals(driver.findElement(By.cssSelector("input[aria-label='Password']~span[data-bound-to='password']")).getText(), "This field is required");

		driver.findElement(By.cssSelector("input[placeholder='Email *']")).sendKeys("automationfc.com@gmail.com");
		driver.findElement(By.cssSelector("input[placeholder='Password *']")).sendKeys("Automation000111");

		driver.findElement(By.cssSelector("input[value='Log in']")).click();

		switchToWindownByTitle("ambridge Dictionary | Từ điển tiếng Anh, Bản dịch & Từ điển từ đồng nghĩa");

		Assert.assertEquals(driver.findElement(By.cssSelector("/div[class='hdn hdib-s']>div[amp-access='loggedIn']>span>i~span")).getText(), "Automation FC");
	}

	// Nó chỉ dùng cho duy nhất 2 tab/ Windown
	public void switchToWindowByID(String parentID) {
		// Lấy ra tất cả các ID của tab/ windown đang có
		Set<String> allWindownIDs = driver.getWindowHandles();

		// Dùng vòng lặp để duyệt qua từng ID
		for (String id : allWindownIDs) {

			// Nếu như có ID nào mà khác vs parentID
			if (!id.equals(parentID)) {

				// Switch vào
				driver.switchTo().window(id);
				break;
			}
		}

	}

	public void switchToWindownByTitle(String expectedPageTitle) {
		// Lấy ra tất cả các ID của tab/ windown đang có
		Set<String> allWindownIDs = driver.getWindowHandles();

		// Dùng vòng lặp để duyệt qua từng ID
		for (String id : allWindownIDs) {

			// Switch vào từng tab/ windown trước
			driver.switchTo().window(id);

			// Lấy ra title của page đã switch vào
			String currentPageTitle = driver.getTitle();

			if (currentPageTitle.equals(expectedPageTitle)) {
				// Thoát khỏi vòng lặp - ko duyệt tiếp nữa
				break;
			}
		}
	}

	public void closeAllWindowWithoutParent(String parentID) {
		// Lấy ra tất cả các ID của tab/ windown đang có
		Set<String> allWindownIDs = driver.getWindowHandles();

		// Dùng vòng lặp để duyệt qua từng ID
		for (String id : allWindownIDs) {

			if (!id.equals(parentID)) {
				driver.switchTo().window(id);

				// Đóng các tab đang active
				driver.close();
			}
		}
		// Vẫn còn lại parentID
		driver.switchTo().window(parentID);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
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
