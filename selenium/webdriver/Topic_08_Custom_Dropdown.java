package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.xml.sax.Locator;

public class Topic_08_Custom_Dropdown {
	
	// Khai báo
	WebDriver driver;
	WebDriverWait explicitWait;	
	
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		// Đang bị null
		// System.out.println(driver.toString());
		
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		
		// Khởi tạo driver
		driver = new FirefoxDriver();
		// System.out.println(driver.toString());
		
		// Khởi tạo wait
		explicitWait = new WebDriverWait(driver, 30);

		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_01() {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");	
		
		// Gọi hàm: 1 hàm có thể gọi 1 hàm khác để dùng trong cùng 1 class
		selectItemInCustomDropdown("span#number-button", "ul#number-menu div", "7");
		sleepInSecond(3);
		
		selectItemInCustomDropdown("span#number-button", "ul#number-menu div", "3");
		sleepInSecond(3);
		
		// Speed dropdown
		selectItemInCustomDropdown("span#speed-button", "ul#speed-menu div", "Slower");
		sleepInSecond(3);
		
		// Title dropdown
		selectItemInCustomDropdown("span#salutation-button", "ul#salutation-menu div", "Other");
		sleepInSecond(3);
		
			
	}
	private void If(boolean equals) {
		// TODO Auto-generated method stub
		
	}

	@Test
	public void TC_02() {
		sleepInSecond(3);
	}


	@AfterClass
	public void afterClass() {		
		// driver.quit();
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
