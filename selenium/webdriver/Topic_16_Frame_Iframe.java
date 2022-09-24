package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_16_Frame_Iframe {

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
	public void TC_01_Iframe_Kyna() {
		// A
		driver.get("https://kyna.vn/");
		sleepInSecond(2);

		// Index: Nếu như thêm hoặc xoá bớt ỉame thì nọ bị cập nhật
		// driver.switchTo().frame(0);

		// ID/ Name
		// driver.switchTo().frame("cs_chat_iframe");

		// A -> B
		// WebElement: all cases
		driver.switchTo().frame(driver.findElement(By.cssSelector("div.fanpage iframe")));

		// B (Element thuộc B)
		String facebookLikeNumber = driver.findElement(By.xpath("//a[text()='Kyna.vn']/parent::div/following-sibling::div")).getText();
		Assert.assertEquals(facebookLikeNumber, "166K likes");

		// Ko support nhảy từ iframe B qua iframe C
		// B -> A
		driver.switchTo().defaultContent();

		// A -> C (Element thuộc C)
		driver.switchTo().frame("cs_chat_iframe");

		driver.findElement(By.cssSelector("div.meshim_widget_widgets_BorderOverlay")).click();

		driver.findElement(By.cssSelector("input.input_name")).sendKeys("Jonh");
		driver.findElement(By.cssSelector("input.input_phone")).sendKeys("0123456789");
		new Select(driver.findElement(By.cssSelector("select#serviceSelect"))).selectByVisibleText("TƯ VẤN TUYỂN SINH");
		driver.findElement(By.cssSelector("textarea[name='message']")).sendKeys("Resgister new Course");
		sleepInSecond(5);

		// C -> A
		driver.switchTo().defaultContent();

		String keyword = "Excel";
		// A (Element thuộc A)
		driver.findElement(By.cssSelector("input#live-search-bar")).sendKeys(keyword);
		driver.findElement(By.cssSelector("button.search-button")).click();

		// Verify
		List<WebElement> courseName = driver.findElements(By.cssSelector("div.content>h4"));
		// Number
		Assert.assertEquals(courseName.size(), 9);

		for (WebElement course : courseName) {
			System.out.println(course.getText());
			// Course name contains keywords
			Assert.assertTrue(course.getText().contains(keyword));
		}

	}

	@Test
	public void TC_02_HDFC_Bank() {
		driver.get("https://netbanking.hdfcbank.com/netbanking/");
		sleepInSecond(3);

		driver.switchTo().frame("login_page");
		driver.findElement(By.name("fldLoginUserId")).sendKeys("automationfc");
		driver.findElement(By.cssSelector("a.login-btn")).click();
		sleepInSecond(3);

		WebElement passwordTextbox = driver.findElement(By.id("fldPasswordDispId"));
		Assert.assertTrue(passwordTextbox.isDisplayed());

		passwordTextbox.sendKeys("automationfc");
		sleepInSecond(3);
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
