package webdriver;

import java.awt.AWTException;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_14_Actions_P2 {

    WebDriver driver;
	Actions action;
	JavascriptExecutor jsExecutor;
	String projectPath = System.getProperty("user.dir");
	String jsFileContent;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		driver = new FirefoxDriver();
		
		action = new Actions(driver);
		jsExecutor = (JavascriptExecutor) driver;
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_08_Drag_and_Drop_HTML5() throws IOException {
		driver.get("https://automationfc.github.io/drag-drop-html5//");
		
		// Mac /, Win ||
		jsFileContent = getContentFile(projectPath + "/dragAndDrop/drag_and_drop_helper");
		
		WebElement sourceRectangle = driver.findElement(By.cssSelector("div#column-a"));
		WebElement targetRectagle = driver.findElement(By.cssSelector("div#column-b"));
		
		jsFileContent = jsFileContent + "$(\"div#column-a\").simulateDragDrop({ dropTarget: \"div#column-b\"});";
		
		// Drag A to B
		jsExecutor.executeScript(jsFileContent);
		sleepInSecond(5);
		Assert.assertEquals(driver.findElement(By.cssSelector("div#column-a>header")).getText(), "B");
		Assert.assertEquals(driver.findElement(By.cssSelector("div#column-b>header")).getText(), "A");
		
		// Drag A to B
		jsExecutor.executeScript(jsFileContent);
		sleepInSecond(5);	
		Assert.assertEquals(driver.findElement(By.cssSelector("div#column-a>header")).getText(), "A");
		Assert.assertEquals(driver.findElement(By.cssSelector("div#column-b>header")).getText(), "B");
		
	}
	
	@Test
	public void TC_08_Drag_and_Drop_Java_Robot_Offset() throws IOException, AWTException {
		driver.get("https://automationfc.github.io/drag-drop-html5//");
		
		// A -> 
		dragAndDropHTML5ByXpath("//div[@id='column-a']", "//div[@id='column-b']");
		sleepInSecond(5);
		Assert.assertEquals(driver.findElement(By.cssSelector("div#column-a>header")).getText(), "B");
		Assert.assertEquals(driver.findElement(By.cssSelector("div#column-b>header")).getText(), "A");
		
		// B -> A
		dragAndDropHTML5ByXpath("//div[@id='column-a']", "//div[@id='column-b']");
		sleepInSecond(5);	
		Assert.assertEquals(driver.findElement(By.cssSelector("div#column-a>header")).getText(), "A");
		Assert.assertEquals(driver.findElement(By.cssSelector("div#column-b>header")).getText(), "B");
		
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
	
	public String getContentFile(String filePath) throws IOException {
		Charset cs = Charset.forName("UTF-8");
		FileInputStream stream = new FileInputStream(filePath);
		try {
			Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
			StringBuilder builder = new StringBuilder();
			char[] buffer = new char[8192];
			int read;
			while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
				builder.append(buffer, 0, read);
			}
			return builder.toString();
		} finally {
			stream.close();
		}
	}

	public void dragAndDropHTML5ByXpath(String sourceLocator, String targetLocator) throws AWTException {

		WebElement source = driver.findElement(By.xpath(sourceLocator));
		WebElement target = driver.findElement(By.xpath(targetLocator));

		// Setup robot
		Robot robot = new Robot();
		robot.setAutoDelay(500);

		// Get size of elements
		Dimension sourceSize = source.getSize();
		Dimension targetSize = target.getSize();

		// Get center distance
		int xCentreSource = sourceSize.width / 2;
		int yCentreSource = sourceSize.height / 2;
		int xCentreTarget = targetSize.width / 2;
		int yCentreTarget = targetSize.height / 2;

		org.openqa.selenium.Point sourceLocation = source.getLocation();
		org.openqa.selenium.Point targetLocation = target.getLocation();
		

		// Make Mouse coordinate center of element
		sourceLocation.x += 20 + xCentreSource;
		sourceLocation.y += 110 + yCentreSource;
		targetLocation.x += 20 + xCentreTarget;
		targetLocation.y += 110 + yCentreTarget;

		System.out.println(sourceLocation.toString());
		System.out.println(targetLocation.toString());

		// Move mouse to drag from location
		robot.mouseMove(sourceLocation.x, sourceLocation.y);

		// Click and drag
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseMove(((sourceLocation.x - targetLocation.x) / 2) + targetLocation.x, ((sourceLocation.y - targetLocation.y) / 2) + targetLocation.y);

		// Move to final position
		robot.mouseMove(targetLocation.x, targetLocation.y);

		// Drop
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
	}
}
