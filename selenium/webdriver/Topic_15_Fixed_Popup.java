package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_15_Fixed_Popup {

	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor jsExecutor;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		driver = new ChromeDriver();

		jsExecutor = (JavascriptExecutor) driver;

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_Fixed_In_DOM_Ngoaingu_24h() {
		driver.get("https://ngoaingu24h.vn/");
		sleepInSecond(2);

		WebElement loginPopup = driver.findElement(By.xpath("//div[@id='modal-login-v1'][1]"));
		Assert.assertFalse(loginPopup.isDisplayed());

		driver.findElement(By.cssSelector("button.login_ ")).click();
		sleepInSecond(3);

		Assert.assertTrue(loginPopup.isDisplayed());

		driver.findElement(By.xpath("//div[@id='modal-login-v1'][1]//input[@id='account-input']")).sendKeys("automationfc");
		driver.findElement(By.xpath("//div[@id='modal-login-v1'][1]//input[@id='password-input']")).sendKeys("automationfc");
		driver.findElement(By.xpath("//div[@id='modal-login-v1'][1]//button[contains(@class,'btn-login-v1')]")).click();
		sleepInSecond(3);

		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='modal-login-v1'][1]//div[@class='row error-login-panel']")).getText(), "Tài khoản không tồn tại!");

		driver.findElement(By.xpath("//div[@id='modal-login-v1'][1]//button[@class='close']")).click();
		sleepInSecond(3);

		Assert.assertFalse(loginPopup.isDisplayed());
	}

	@Test
	public void TC_02_Fixed_In_DOM_Kyna() {
		driver.get("https://kyna.vn/");
		sleepInSecond(2);

		WebElement loginPopup = driver.findElement(By.cssSelector("div#k-popup-account-login"));
		Assert.assertFalse(loginPopup.isDisplayed());

		driver.findElement(By.cssSelector("a.login-btn")).click();

		Assert.assertTrue(loginPopup.isDisplayed());

		driver.findElement(By.cssSelector("input#user-login")).sendKeys("automation@gmail.com");
		driver.findElement(By.cssSelector("input#user-password")).sendKeys("1234");
		driver.findElement(By.cssSelector("button#btn-submit-login")).click();

		sleepInSecond(3);

		Assert.assertEquals(driver.findElement(By.cssSelector("div#password-form-login-message")).getText(), "Sai tên đăng nhập hoặc mật khẩu");

		driver.findElement(By.cssSelector("div#k-popup-account-login-mb button.k-popup-account-close")).click();
		sleepInSecond(3);

		Assert.assertFalse(loginPopup.isDisplayed());
	}

	@Test
	public void TC_03_Fixed_In_DOM_Tiki() {
		driver.get("https://tiki.vn/");
		sleepInSecond(2);

		// Trong trường hợp popup ko có trong DOM thì findElements sẽ tìm thấy 0 element
		// Và cũng chờ hết timeout của impliciWait nhưng ko đánh fail testcase và cũng ko show Exception
		// Empty list = 0 element
		List<WebElement> loginPopup = driver.findElements(By.cssSelector("div.ReactModal__Content"));

		Assert.assertEquals(loginPopup.size(), 0);

		driver.findElement(By.xpath("//span[text()='Đăng Nhập / Đăng Ký']")).click();
		sleepInSecond(3);

		// Displayed (Single element: findElement)
		Assert.assertTrue(driver.findElement(By.cssSelector("div.ReactModal__Content")).isDisplayed());

		// Displayed (Multiple element: findElements)
		loginPopup = driver.findElements(By.cssSelector("div.ReactModal__Content"));
		Assert.assertEquals(loginPopup.size(), 1);
		Assert.assertTrue(loginPopup.get(0).isDisplayed());

		// Click để đóng popup
		driver.findElement(By.cssSelector("img.close-img")).click();
		sleepInSecond(3);

		loginPopup = driver.findElements(By.cssSelector("div.ReactModal__Content"));
		Assert.assertEquals(loginPopup.size(), 0);
	}

	@Test
	public void TC_04_Random_In_Dom_KMPlayer() {
		driver.get("https://www.kmplayer.com/home");
		sleepInSecond(8);

		WebElement popupPlayer = driver.findElement(By.cssSelector("div.pop-layer"));

		// Phải luôn chạy được testcase dù popup có hiển thị hay ko
		// 1. Đang trong thời gian sale nó hiển thị thì script mk phải đóng đi để chạy tiếp
		// 2. Hết đợt sale ko hiển thị thì script chạy luôn
		// Muốn test thử thì sẽ cố tình tắt popup khi tới hàm check display

		if (popupPlayer.isDisplayed()) {
			// Close nó đi để chạy tiếp step tiếp theo
			System.out.println("-------Step close popup-------");
			jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("area#btn-r")));
			sleepInSecond(2);
		}
		System.out.println("-------Next step-------");
		driver.findElement(By.cssSelector("p.donate-coffee")).click();
		sleepInSecond(3);
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.buymeacoffee.com/kmplayer?ref=hp&kind=top");
	}

	@Test
	public void TC_05_Random_Not_Dom_DeHieu() {
		driver.manage().window().setSize(new Dimension(1100, 558));
		driver.get("https://dehieu.vn/");
		sleepInSecond(2);

		// Phải luôn chạy được testcase dù popup có hiển thị hay ko
		// 1. Đang trong thời gian sale nó hiển thị thì script mk phải đóng đi để chạy tiếp
		// 2. Hết đợt sale ko hiển thị thì script chạy luôn
		List<WebElement> contentPopup = driver.findElements(By.cssSelector("div.popup-content"));

		// Nếu element > 0 (tương ứng từ 1 trở lên)
		if (contentPopup.size() > 0 && contentPopup.get(0).isDisplayed()) {
			driver.findElement(By.id("popup-name")).sendKeys("John");
			driver.findElement(By.id("popup-email")).sendKeys("John@gmail.com");
			driver.findElement(By.id("popup-phone")).sendKeys("1234");
			sleepInSecond(8);

			jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("button#close-popup")));
			sleepInSecond(2);

		}

		driver.findElement(By.xpath("//a[text()='Tất cả khóa học']")).click();
		sleepInSecond(2);
		Assert.assertEquals(driver.getCurrentUrl(), "https://dehieu.vn/khoa-hoc");

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
