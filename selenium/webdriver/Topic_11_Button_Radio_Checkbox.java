package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_Button_Radio_Checkbox {

	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		driver = new FirefoxDriver();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_01_Button() {
		driver.get("https://www.fahasa.com/customer/account/create");	
		sleepInSecond(5);
		
		driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();
		
		By loginButtonBy = By.cssSelector("button.fhs-btn-login");
		
		// Verify login button is disabled
		Assert.assertFalse(driver.findElement(loginButtonBy).isEnabled());
		
		driver.findElement(By.id("login_username")).sendKeys("sara@gmail.net");
		driver.findElement(By.id("login_password")).sendKeys("123456");
		sleepInSecond(3);
		
		// Verify login button is enable
		Assert.assertTrue(driver.findElement(loginButtonBy).isEnabled());
		
		// Verify background color is red
		String loginButtonBackgroundHexa = Color.fromString( driver.findElement(loginButtonBy).getCssValue("background-color")).asHex().toUpperCase();
		Assert.assertEquals(loginButtonBackgroundHexa, "#C92127");
		
	}

	@Test
	public void TC_02_Default_Radio_Checkbox() {
		driver.get("https://automationfc.github.io/multiple-fields/");
		
		// Checkbox
		By enotionalCheckbox = By.xpath("//input[@value='Emotional Disorder']");
		By digestiveCheckbox = By.xpath("//input[@value='Digestive Problems']");
		By venerealCheckbox = By.xpath("//input[@value='Venereal Disease']");
		
		// Radio
		By fivedayRadio = By.xpath("//input[@value='5+ days']");
		By dietPlanRadio = By.xpath("//input[@value=\"I don't have a diet plan\"]");
		By glassRadio = By.xpath("//input[@value='3-4 glasses/day']");
		
		sleepInSecond(3);
		
		// 1- Chọn (Choice) - Click
		driver.findElement(enotionalCheckbox).click();
		driver.findElement(digestiveCheckbox).click();
		driver.findElement(venerealCheckbox).click();
		sleepInSecond(3);
		
		driver.findElement(fivedayRadio).click();
		driver.findElement(dietPlanRadio).click();
		driver.findElement(glassRadio).click();
		
		// 2- Verify (Chọn rồi hay chưa chọn thành công)
		Assert.assertTrue(driver.findElement(enotionalCheckbox).isSelected());
		Assert.assertTrue(driver.findElement(digestiveCheckbox).isSelected());
		Assert.assertTrue(driver.findElement(venerealCheckbox).isSelected());
		
		Assert.assertTrue(driver.findElement(fivedayRadio).isSelected());
		Assert.assertTrue(driver.findElement(dietPlanRadio).isSelected());
		Assert.assertTrue(driver.findElement(glassRadio).isSelected());
		
		// 3- Bỏ chọn (De-select) ->Click
		driver.findElement(enotionalCheckbox).click();
		driver.findElement(digestiveCheckbox).click();
		driver.findElement(venerealCheckbox).click();
		sleepInSecond(3);
		
		Assert.assertTrue(driver.findElement(fivedayRadio).isSelected());
		Assert.assertTrue(driver.findElement(dietPlanRadio).isSelected());
		Assert.assertTrue(driver.findElement(glassRadio).isSelected());
		sleepInSecond(3);
		
		// 4 - Verify (Chọn rồi hay chưa chọn thành công)
		Assert.assertFalse(driver.findElement(enotionalCheckbox).isSelected());
		Assert.assertFalse(driver.findElement(digestiveCheckbox).isSelected());
		Assert.assertFalse(driver.findElement(venerealCheckbox).isSelected());
		
		Assert.assertTrue(driver.findElement(fivedayRadio).isSelected());
		Assert.assertTrue(driver.findElement(dietPlanRadio).isSelected());
		Assert.assertTrue(driver.findElement(glassRadio).isSelected());
		
	}
	
	@Test
	public void TC_03_Select_All_Checkbox() {
		driver.get("https://automationfc.github.io/multiple-fields/");
		
		// Select all checkbox
		List<WebElement> allCheckboxs = driver.findElements(By.xpath("//input[@type='checkbox']"));
		
		for (WebElement checkbox : allCheckboxs) {
			if (!checkbox.isSelected()) {
				checkbox.click();
				sleepInSecond(1);	
			}
			
		}
		
		// Verify all checkbox  are selected
		for (WebElement checkbox : allCheckboxs) {
			Assert.assertTrue(checkbox.isSelected());
			
		}
		
		// De-select all
		for (WebElement checkbox : allCheckboxs) {
			if (checkbox.isSelected()) {
				checkbox.click();
				sleepInSecond(1);	
			}
		}
		
		// Verify all checkbox  are selected
		for (WebElement checkbox : allCheckboxs) {
			Assert.assertFalse(checkbox.isSelected());
		}
		
	}

	@AfterClass
	public void afterClass() {		
		// driver.quit();
		}
	// Sleep cứng (Static wait)
	public void sleepInSecond(long timeInSecond) {
	try {
		Thread.sleep(timeInSecond* 1000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
}
