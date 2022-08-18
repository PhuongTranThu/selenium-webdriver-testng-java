package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
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
	public void TC_01_Custom_Checkbox() {
		driver.get("https://material.angular.io/components/checkbox/examples");	
		
		// Thẻ input của Checkbox bị ẩn này có click đc ko?
		// driver.findElement(By.xpath("//span[text()='Checked']/preceding-sibling::span/input")).click();
		// Hàm isSelected() chỉ work được với loại element là checkbox/ radio (phải là thẻ input)
		
		// Case 1:
		// Thẻ input ko click được -> Failed
		// Nhưng thẻ input dùng verify được -> Pass
		// driver.findElement(By.xpath("//span[text()='Checked']/preceding-sibling::span/input")).click();
		
		// Case 2:
		// Không dùng thẻ input để click -  thẻ span chứa text -> Passed
		// Không dùng thẻ input để verify -> Failed
		/*
		By checkedCheckbox = By.xpath("//span[text()='Checked']");
		driver.findElement(checkedCheckbox).click();
		sleepInSecond(3);
		
		Assert.assertTrue(driver.findElement(checkedCheckbox).isSelected());
		*/
		
		// Case 3: Thỏa mãn hết điều kiện (Vừa click được/ vừa verify được)
		// Không dùng thẻ input để click - thẻ span chưa text -> Passed
		// Thẻ input dùng verify dduwwojc -> Passed
		
		// Vấn đề: 1 element phải define tới 2 locator/ by
		
		// Case 4: Thỏa mãn hết điều kiện (vừa click được/ vừa verify được)
		// Thẻ input để click -> Passed (JavascriptExecutor)
		// Thẻ input dùng verify được -> Passed
		// JavascriptExecutor ko quan tâm element bị che hay ko (vẫn click được)
		
		By checkedCheckbox1 = By.xpath("//span[text()='Checked']/preceding-sibling::span/input");
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(checkedCheckbox1));
		Assert.assertTrue(driver.findElement(checkedCheckbox1).isSelected());
		
		By beforeRadio = By.xpath("//span[text()='Before']/preceding-sibling::span/input");
		jsExecutor.executeScript("arguments[0].click();" , driver.findElement(beforeRadio));
		Assert.assertTrue(driver.findElement(beforeRadio).isSelected());
	}

	@Test
	public void TC_02_Google_Docs() {
		driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");	
		
		// Radio
		By hanoiRadio = By.xpath("//div[@aria-label='Hà Nội']");
		driver.findElement(hanoiRadio).click();
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(hanoiRadio).getAttribute("aria-checked"), "true");
		
		// Checkbox
		By miquangCheckbox = By.xpath("//div[@aria-label='Mì Quảng']/parent::div");
		driver.findElement(miquangCheckbox).click();
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.xpath("//div[@aria-label='Mì Quảng']")).getAttribute("aria-checked"), "true");
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
