package tiki.admin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Shopper_01_Manage_Product {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeTest(alwaysRun = true)
	public void initBrowser() {
		System.out.println("Open Browser");
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		driver = new FirefoxDriver();
	}

	@Test(groups = { "admin", "product" })
	public void Product_01_Create_Product() {
	}

	@Test(groups = { "admin", "product" })
	public void Product_02_View_Product() {
	}

	@Test(groups = { "admin", "product" })
	public void Product_03_Update_Product() {
	}

	@Test(groups = { "admin", "product" })
	public void Product_04_Delete_Product() {
	}

	@AfterTest
	public void cleanBrowser() {
		System.out.println("Close Browser");
		driver.quit();
	}
}
