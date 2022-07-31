package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Web_Browser_Element_P1 {

	WebDriver driver;
	WebElement element;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		
		// Khởi tạo
		driver = new FirefoxDriver();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_01() {
		driver.get("https://www.facebook.com/");
		// Các hàm tương tác với browser sẽ thông qua biến driver
		
		// Đóng browser (1 tab)/ window đang active
		driver.close();  //**
		
		// Đóng browser
		driver.quit();  //**
		
		//Tìm ra 1 element (single)
		WebElement loginButton = driver.findElement(By.cssSelector(""));  //**
		
		// Tìm ra nhiều element (multiple)
		List<WebElement> links = driver.findElements(By.cssSelector(""));  //**
		
		// Mở ra cái Url truyền vào
		driver.get("https://www.facebook.com/");  //**
		
		//Trả về 1 Url tại page đang đứng
		String gamePageUrlString = driver.getCurrentUrl();
		
		// Source code của page hiện tại
		String gamePageSourceCodeString = driver.getPageSource();
		
		// Lấy ra cái ID của tab/ windown đang đứng/ active (Windown/ Tab)
		driver.getWindowHandle();  //1  //**
		driver.getWindowHandles(); // Tất cả  //**
		
		driver.manage().getCookies();  // Cookies (Framework)  //**
		driver.manage().logs().getAvailableLogTypes(); // Log Framework
		
		driver.manage().window().fullscreen();
		driver.manage().window().maximize();  //**
		
		// Test GUI (Graphic User Interface)
		// Front/ Size/ Color/ Position/ location/...
		// Ưu tiên làm chức năng trước (Functional UI)
		// Ưu tiên làm giao diện sau
		
		// Chờ cho element được tìm thấy trong khoảng time xx giây (WebDriverWait)
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);  //**
		
		// Chờ cho page load thành công trong xx giây
		driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
		
		// Chờ cho script load được inject thành công vào browser/ element sau xx giây (JavascriptExecutor)
		driver.manage().timeouts().setScriptTimeout(15, TimeUnit.SECONDS);
		
		driver.navigate().back();
		driver.navigate().forward();
		driver.navigate().refresh();
		driver.navigate().to("https://www.facebook.com/");
		
		// Alert/ Frame/ (Iframe)/ Windown (Tab)
		driver.switchTo().alert();  //**
		
		driver.switchTo().frame(0);  //**
		
		driver.switchTo().window("");  //**
	
		
	}

	@Test
	public void TC_02_(String propertyName) {
		driver.get("https://www.facebook.com/");
		// Các hàm tương tác với Element sẽ thông qua cái class Webelement (biến nào đó)
		
		// 2 cách để thao tác 
		// khai báo biến và dùng lại
		// Dùng đi dùng lại nhiều lần- dùng ít nhất 2 lần thì mới cần khai báo biến
		
		// Khai báo biến cùng với kiểu dữ liệu trả về của hàm findElement
		WebElement emailAddressTexboxElement = driver.findElement(By.id("email"));
		emailAddressTexboxElement.clear();
		emailAddressTexboxElement.sendKeys("phuong@gmail.com");
		
		// Dùng trực tiếp- dùng 1 lần
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys("phuong@gmail.com");
		
		// Xóa dữ liệu trong 1 field dạng editable ( có thể nhập)
		// Textbox/ Text Area
		element.clear();
		
		// Trả về giá trị nằm trong các Attribute của element
		element.getAttribute("placeholder");
		
		driver.findElement(By.id("firstname")).getAttribute("value");
		
		// Trả về thuộc tính Css của element này
		// Font/ size/ color
		element.getCssValue(propertyName);
		
		// Trả về màu nên của element
		element.getCssValue("background-color");
		
		// Trả về font size của element
		element.getCssValue("font-size");
		
		// Cho phép click vào Button/ Radio/ link....
		element.click();
		
		// Test GUI: Point/ Rectanle/ Size (Visual Testing)
		element.getLocation();
		element.getRect();
		
		// Chụp hình và attach vào HTTP Report
		element.getScreenshotAs(OutputType.FILE);

		// Trả về thẻ HTML của element
		WebElement emailAddressTextbox = driver.findElement(By.xpath("//input[@id='email']"));
		emailAddressTextbox.getTagName();
		
		// input
		
		// Trả về text của 1 element (Link/ Header/ Message lỗi/ Message success)
		element.getText();
		
		// Trả về giá trị đúng hoặc sai của 1 element có hiển thị không
		element.isDisplayed();
		// Hiển thị: true
		// Không hiển thị: false
		
		// Trả về gia trị đúng hoặc sai của 1 element có thể thao tác được hay ko
		// Có bị disable ko
		element.isEnabled();
		// Enable: true
		// Bị disabled: false
		
		// Trả về gia trị đúng hoặc sai của 1 element đã được chọn rồi hay chưa
		// Checkbox/ Radio
		element.isSelected();
		// chọn ra: true
		// Chưa chọn: false
		
		// Dropdown: có 1 thư viện riêng để xử lí (Select)
		// Chỉ làm việc được vs from (Register/ Login/ search...)
		// Submit = ENTER ở 1 file nào đó
		// Submit vào 1 file nào đó trong from
		element.submit();
	}


	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
