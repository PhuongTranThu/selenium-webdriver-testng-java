package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.xml.sax.Locator;

import net.bytebuddy.asm.Advice.Argument;

public class Topic_08_Custom_Dropdown {
	
	// Khai báo
	WebDriver driver;
	WebDriverWait explicitWait;	
	JavascriptExecutor jsExecutor;
	
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		// Đang bị null
		// System.out.println(driver.toString());
		
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		
		// Khởi tạo driver
		driver = new FirefoxDriver();
		
		// driver.manage().window().setSize(new Dimension(1366, 768));
		
		// Khởi tạo wait
		explicitWait = new WebDriverWait(driver, 30);

		// Khởi tạo
		jsExecutor = (JavascriptExecutor) driver;
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_01() {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");	
		
		// Gọi hàm: 1 hàm có thể gọi 1 hàm khác để dùng trong cùng 1 class
		selectItemInCustomDropdown("span#number-button", "ul#number-menu div", "7");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("span#number-button>span.ui-selectmenu-text")).getText(), "7");
		
		selectItemInCustomDropdown("span#number-button", "ul#number-menu div", "3");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("span#number-button>span.ui-selectmenu-text")).getText(), "3");
		
		// Speed dropdown
		selectItemInCustomDropdown("span#speed-button", "ul#speed-menu div", "Slower");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button>span.ui-selectmenu-text")).getText(), "Slower");
		
		// Title dropdown
		selectItemInCustomDropdown("span#salutation-button", "ul#salutation-menu div", "Other");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("span#salutation-button>span.ui-selectmenu-text")).getText(), "Other");
		
			
	}
	private void If(boolean equals) {
		// TODO Auto-generated method stub
			}

	@Test
	public void TC_02_Honda() {
		driver.get("https://www.honda.com.vn/o-to/du-toan-chi-phi");
		
		scrollToElement("div.carousel-item");
		sleepInSecond(3);
		
		selectItemInCustomDropdown("button#selectize-input", "button#selectize-input+div>a", "CITY L");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("button#selectize-input")).getText(), "CITY L");
		
		scrollToElement("div.container");
		sleepInSecond(3); 
		
		Select select = new Select(driver.findElement(By.cssSelector("select#province")));
		select.selectByVisibleText("Đà Nẵng");
		sleepInSecond(3);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Đà Nẵng");
		
		select = new Select(driver.findElement(By.cssSelector("select#registration_fee")));
		select.selectByVisibleText("Khu vực II");
		sleepInSecond(3);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Khu vực II");
	
	}

	@Test
	public void TC_03_React() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		
		selectItemInCustomDropdown("div.dropdown", "div.menu span.text", "Jenny Hess");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Jenny Hess");
		
		selectItemInCustomDropdown("div.dropdown", "div.menu span.text", "Stevie Feliciano");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Stevie Feliciano");
		
		selectItemInCustomDropdown("div.dropdown", "div.menu span.text", "Matt");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Matt");
	}
	
	@Test
	public void TC_04_VueJS() {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		
		selectItemInCustomDropdown("li.dropdown-toggle", "ul.dropdown-menu a", "Second Option");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "Second Option");
		
		selectItemInCustomDropdown("li.dropdown-toggle", "ul.dropdown-menu a", "First Option");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "First Option");
		
		selectItemInCustomDropdown("li.dropdown-toggle", "ul.dropdown-menu a", "Third Option");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "Third Option");
		
	}
	
	@Test
	public void TC_05_React_Selectable() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
		
		selectItemInCustomDropdown("div.dropdown", "div.menu span.text", "Jenny Hess");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Jenny Hess");
		
	}
	
	@Test
	public void TC_06_React_Selectable() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
		
		enterItemInCustomDropdown("input.search", "div.menu span.text", "Algeria");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Algeria");
		
		enterItemInCustomDropdown("input.search", "div.menu span.text", "Bahrain");
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Bahrain");
		
	}
	@AfterClass
	public void afterClass() {		
		// driver.quit();
		}
	
	public void scrollToElement(String cssLocator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);\r\n"
				+ "\r\n"
				+ "", driver.findElement(By.cssSelector(cssLocator)));

		
	}
	public void selectItemInCustomDropdown(String parentLocator, String childLocator, String textExpectedItem) {
		driver.findElement(By.cssSelector(parentLocator)).click();
		
		// bắt được 1 locator để đại diện cho tất cả các item
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childLocator)));
		
		// Lưu trữ tất cả các item lại thì mới duyệt qua được
		List<WebElement> allItems = driver.findElements(By.cssSelector(childLocator));
		
		// Vòng lặp forearch
		for (WebElement item : allItems) {
			// Dùng biến item để thao tác với vòng lặp for
			
			// Lấy ra text
			String textActualItem = item.getText();
			
			// Kiểm tra nếu nó bằng vs text mk mong muốn thì click vào
			if(textExpectedItem.equals(textActualItem)){
				// Nó sẽ nhận vào 1 điều kiện là boolean (true/ false)
				// Nếu như điều kiện mà đúng thì mới vào trong hàm if
				// Điều kiện sai thì bỏ qua
				
				// thì click vào
				item.click();
			}
			
		}
		
	}
	
	public void enterItemInCustomDropdown(String parentLocator, String childLocator, String textExpectedItem) {
		driver.findElement(By.cssSelector(parentLocator)).clear();
		driver.findElement(By.cssSelector(parentLocator)).sendKeys(textExpectedItem);
		
		// bắt được 1 locator để đại diện cho tất cả các item
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childLocator)));
		
		// Lưu trữ tất cả các item lại thì mới duyệt qua được
		List<WebElement> allItems = driver.findElements(By.cssSelector(childLocator));
		
		// Vòng lặp forearch
		for (WebElement item : allItems) {
			// Dùng biến item để thao tác với vòng lặp for
			
			// Lấy ra text
			String textActualItem = item.getText();
			
			// Kiểm tra nếu nó bằng vs text mk mong muốn thì click vào
			if(textExpectedItem.equals(textActualItem)){
				// Nó sẽ nhận vào 1 điều kiện là boolean (true/ false)
				// Nếu như điều kiện mà đúng thì mới vào trong hàm if
				// Điều kiện sai thì bỏ qua
				
				// thì click vào
				item.click();
			}
			
		}
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
