package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_23_StaticWait {

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

		// 1. Ảnh hưởng trực tiếp tới 2 hàm: findElement/ findElements
		// 2. Ngoại lệ
		// ImplicitWait set ở đâu nó sẽ apply từ đó trở xuống
		// Nếu bị gán lại thì nó sẽ dùnggias trị mới/ ko dùng giá trị cũ
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_Not_Enough_Time() {

		driver.get("https://automationfc.github.io/dynamic-loading/");

		// Click vào Start button
		driver.findElement(By.cssSelector("div#start>button")).click();
		sleepInSecond(3);

		// Loading icon mất 5s mới biến mất

		// Get text
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");

	}

	@Test
	public void TC_02_Enough_Time() {

		driver.get("https://automationfc.github.io/dynamic-loading/");

		// Click vào Start button
		driver.findElement(By.cssSelector("div#start>button")).click();
		sleepInSecond(5);

		// Get text
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");

	}

	@Test
	public void TC_03_More_Time() {

		driver.get("https://automationfc.github.io/dynamic-loading/");

		// Click vào Start button
		driver.findElement(By.cssSelector("div#start>button")).click();
		sleepInSecond(10);

		// Get text
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");

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
