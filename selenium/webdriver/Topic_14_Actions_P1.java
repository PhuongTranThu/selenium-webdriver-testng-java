package webdriver;

import static org.testng.Assert.ARRAY_MISMATCH_TEMPLATE;
import static org.testng.Assert.assertEquals;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_14_Actions_P1 {

    WebDriver driver;
	Actions action;
	JavascriptExecutor jsExecutor;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");;
		driver = new FirefoxDriver();
		
		action = new Actions(driver);
		jsExecutor = (JavascriptExecutor) driver;
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_01_Hover() {
		driver.get("https://automationfc.github.io/jquery-tooltip/");	
		
		WebElement ageTextbox = driver.findElement(By.cssSelector("input#age"));
		action.moveToElement(ageTextbox).perform();
		sleepInSecond(5);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.ui-tooltip-content")).getText(), "We ask for your age only for statistical purposes.");

	}
	
	@Test
	public void TC_02_Hover() {
		driver.get("http://www.myntra.com/");	
		
		WebElement kidLink = driver.findElement(By.xpath("//header[@id='desktop-header-cnt']//a[text()='Kids']"));
		action.moveToElement(kidLink).perform();
		sleepInSecond(2);
		
		action.click(driver.findElement(By.xpath("//a[text()='Home & Bath']"))).perform();
		sleepInSecond(2);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("span.header-title")).getText(), "FILTERS");
		
	}
	
	@Test
	public void TC_03_Hover() {
		driver.get("https://fptshop.com.vn/");	
		action.moveToElement(driver.findElement(By.xpath("//a[@title='ĐIỆN THOẠI']"))).perform();
		sleepInSecond(5);
		
		action.click(driver.findElement(By.xpath("//a[@title='Apple (iPhone)']"))).perform();
		sleepInSecond(3);
		
		Assert.assertEquals(driver.getCurrentUrl(), "https://fptshop.com.vn/dien-thoai/apple-iphone");
	}

	@Test
	public void TC_04_Click_And_Hold_Block() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		
		// Store all 12 elements
		List<WebElement> allNumbers = driver.findElements(By.cssSelector("ol#selectable>li"));
		Assert.assertEquals(allNumbers.size(), 12);
		
		// Click và giữ chuột tại số thử 1, sau đó 
		action.clickAndHold(allNumbers.get(0))
		// Hover chuột tới số 11
		.moveToElement(allNumbers.get(10))
		// Nhả chuột 
		.release()
		// Thực 
		.perform();
		
		allNumbers = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));
		Assert.assertEquals(allNumbers.size(), 9);	
		
	}
	
	@Test
	public void TC_05_Click_And_Hold_Random() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		
		// Store all 12 elements
		List<WebElement> allNumbers = driver.findElements(By.cssSelector("ol#selectable>li"));
		Assert.assertEquals(allNumbers.size(), 12);
		
		// Nhan phim Control
		action.keyDown(Keys.COMMAND).perform();
		
		action.click(allNumbers.get(0))
		.click(allNumbers.get(3))
		.click(allNumbers.get(5))
		.click(allNumbers.get(7)).perform();
		
		action.keyUp(Keys.COMMAND).build().perform();
		
		allNumbers = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));
		Assert.assertEquals(allNumbers.size(), 4);	
		
	}

	@Test
	public void TC_06_Double_Click() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		WebElement doubleClickMeText= driver.findElement(By.xpath("//button[text()='Double click me']"));
		
		// Scroll to elemet
		// true: mép trên của element và kéo element lên trên cùng
		// false: mép dưới của element cà kéo element cuống dưới 
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", doubleClickMeText);
		
		action.doubleClick(doubleClickMeText).perform();
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("p#demo")).getText(), "Hello Automation Guys!");
	}
	@Test
	public void TC_07_Right_Click() {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		
		action.contextClick(driver.findElement(By.xpath("//span[text()='right click me']"))).perform();
		sleepInSecond(3);
		
		WebElement deleteBefore= driver.findElement(By.cssSelector("li.context-menu-icon-delete"));
		action.moveToElement(deleteBefore).perform();
		sleepInSecond(3);
		
		Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-delete.context-menu-visible")).isDisplayed());
		action.click(deleteBefore).perform();
		Alert alert= driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "clicked: delete");
		alert.accept();
		sleepInSecond(3);
		Assert.assertFalse(deleteBefore.isDisplayed());

	}
	@Test
	public void TC_08_Drag_and_Drop() {
		driver.get("https://automationfc.github.io/kendo-drag-drop/");
		
		WebElement sourceCircle = driver.findElement(By.cssSelector("div#draggable"));
		WebElement targetCircle = driver.findElement(By.cssSelector("div#droptarget"));
		
		action.dragAndDrop(sourceCircle, targetCircle).perform();
		sleepInSecond(3);
		
		Assert.assertEquals(targetCircle.getText(), "You did great!");
		
		// Background color
		String loginButtonBackgroundHexa = Color.fromString(targetCircle.getCssValue("background-color")).asHex().toUpperCase();
		Assert.assertEquals(loginButtonBackgroundHexa, "#03A9F4");
		
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
