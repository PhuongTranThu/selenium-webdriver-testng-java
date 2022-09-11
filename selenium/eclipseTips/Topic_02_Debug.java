package eclipseTips;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Topic_02_Debug {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@Test
	public void TC_01() {
		System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		driver = new ChromeDriver();
		driver.get("https://www.facebook.com/");
		String headerText = driver.findElement(By.xpath("" + "//img[@alt='Facebook']/parent::div/following-sibling::h2")).getText();
		Assert.assertEquals(headerText, "Facebook helps you connect and share with the people in your life.");

		boolean loginStatus = driver.findElement(By.name("login")).isDisplayed();
		Assert.assertTrue(loginStatus);
	}

}
