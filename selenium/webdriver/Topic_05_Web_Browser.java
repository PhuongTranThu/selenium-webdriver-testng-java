package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Web_Browser {

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
	public void TC_02() {
		
	}


	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
