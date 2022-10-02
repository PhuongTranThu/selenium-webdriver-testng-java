package webdriver;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_19_Upload_Sendkey {

	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	// Image name
	String vietnam = "vietnam.jpeg";
	String thailan = "thailan.jpeg";
	String indonesia = "indonesia.jpeg";

	// Upload file folder
	String uploadFileFolderPath = projectPath + File.separator + "uploadFiles" + File.separator;

	// Image path
	String vietnamFilePath = uploadFileFolderPath + vietnam;
	String thailanFilePath = uploadFileFolderPath + thailan;
	String indonesiaFilePath = uploadFileFolderPath + indonesia;

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Mac OS")) {//
			System.setProperty("webdriver.edge.driver", projectPath + "/browserDrivers/msedgedriver");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		}

		driver = new EdgeDriver();

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_Upload_One_File_Per_Time() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		sleepInSecond(2);

		By uploadFile = By.cssSelector("input[type='file']");

		// Load File (Browse file)
		driver.findElement(uploadFile).sendKeys(vietnamFilePath);
		driver.findElement(uploadFile).sendKeys(thailanFilePath);
		driver.findElement(uploadFile).sendKeys(indonesiaFilePath);

		// Verify image load lên thành công
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='" + vietnam + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='" + thailan + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='" + indonesia + "']")).isDisplayed());

		// Thực hiện upload
		List<WebElement> startButtons = driver.findElements(By.cssSelector("table button.start"));

		for (WebElement startButton : startButtons) {
			startButton.click();
			sleepInSecond(2);
		}

		// Verify image upload thành công
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + vietnam + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + thailan + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + indonesia + "']")).isDisplayed());
	}

	@Test
	public void TC_02_Upload_Multiple_Per_Time() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		sleepInSecond(2);

		By uploadFile = By.cssSelector("input[type='file']");

		// Load File (Browse file)
		driver.findElement(uploadFile).sendKeys(vietnamFilePath + "\n" + thailanFilePath + "\n" + indonesiaFilePath);

		// Verify image load lên thành công
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='" + vietnam + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='" + thailan + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='" + indonesia + "']")).isDisplayed());

		// Thực hiện upload
		List<WebElement> startButtons = driver.findElements(By.cssSelector("table button.start"));

		for (WebElement startButton : startButtons) {
			startButton.click();
			sleepInSecond(2);
		}

		// Verify image upload thành công
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + vietnam + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + thailan + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + indonesia + "']")).isDisplayed());
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
