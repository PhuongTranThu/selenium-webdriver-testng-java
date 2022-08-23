package exercise;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_12_Custom_Checkbox_Radio {

	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor jsExecutor;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		jsExecutor = (JavascriptExecutor) driver;
		
	}

	@Test
	public void TC_01() {
		driver.get("https://material.angular.io/components/radio/examples");	
		sleepInSecond(1);
		WebElement summerRadio = driver.findElement(By.cssSelector("label.mat-radio-label>span>input[value='Summer']"));
		jsExecutor.executeScript("arguments[0].click();", summerRadio);
		Assert.assertTrue(summerRadio.isSelected());
		if (!(summerRadio.isSelected())) {
			summerRadio.click();
			
		}
		
	}

	@Test
	public void TC_02_Checked_Checkbox() {
		driver.get("https://material.angular.io/components/checkbox/examples");	
		sleepInSecond(1);
		
		By checkedCheckbox = By.xpath("//span[text()='Checked']/preceding-sibling::span/input");
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(checkedCheckbox));
		sleepInSecond(1);
		Assert.assertEquals(driver.findElement(checkedCheckbox).getAttribute("aria-checked"), "true");
		
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(checkedCheckbox));
		Assert.assertEquals(driver.findElement(checkedCheckbox).getAttribute("aria-nchecked"), "false");
		
	}
	
	@Test
	public void TC_03_Can_Tho_Radio_Button() {
		driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");	
		sleepInSecond(1);
		
		driver.findElement(By.xpath("//div[@data-value='Cần Thơ']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//div[@aria-label='Cần Thơ' and @aria-checked='true']")).isDisplayed());
		
	}

	@Test
	public void TC_04() {
		sleepInSecond(3);
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
