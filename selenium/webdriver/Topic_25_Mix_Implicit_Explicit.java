package webdriver;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_25_Mix_Implicit_Explicit {

	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	WebDriverWait explicitWait;

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Mac OS")) {//
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		}

		driver = new FirefoxDriver();

	}

	@Test
	public void TC_01_Element_Found() {
		// Element có xuất hiện và ko cần chờ hết timeout
		// Dù có set cả 2 loại wait thì ko ảnh hưởng
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 15);

		// implicitWait chỉ apply cho việc findElement/ findElements, ko có ngoại lệ
		// ExplicitWait cho các điều kiện của Element
		driver.get("https://www.facebook.com/");

		// explicitWait
		System.out.println("Thời gian bắt đầu của ExplicitWait " + getTimeStamp());
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#email")));
		System.out.println("Thời gian kết thúc của ExplicitWait " + getTimeStamp());

		// ImplicitWait
		System.out.println("Thời gian bắt đầu của ImplicitWait " + getTimeStamp());
		driver.findElement(By.cssSelector("input#email"));
		System.out.println("Thời gian bắt đầu của ImplicitWait " + getTimeStamp());

	}

	@Test
	public void TC_02_Element_Not_Found_Implicit() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.get("https://www.facebook.com/");

		// ImplicitWait
		System.out.println("Thời gian bắt đầu của ImplicitWait " + getTimeStamp());
		try {
			driver.findElement(By.cssSelector("input#selenium"));
		} catch (Exception e) {
			System.out.println("Thời gian kết thúc của ImplicitWait " + getTimeStamp());
		}
		// Output
		// Có cơ chế tìm lại sau mỗi 0.5s
		// Hết timeout đánh fail testcase này
		// Throw ra 1 exception: NoSuchElement
	}

	@Test
	public void TC_03_Element_Not_Found_Implicit_Explicit() {
		// 3.1 Implicit = Explicit = 5s
		// 3.2 Implicit > Explicit
		// 3.3 Implicit < Explicit
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 8);

		driver.get("https://www.facebook.com/");

		// explicitWait
		System.out.println("Thời gian bắt đầu của ExplicitWait " + getTimeStamp());
		try {
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#selenium")));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Thời gian kết thúc của ExplicitWait " + getTimeStamp());
		}
	}

	@Test
	public void TC_04_Element_Not_Found_Explicit_By() {

		explicitWait = new WebDriverWait(driver, 5);

		driver.get("https://www.facebook.com/");

		// explicitWait - By là tham số nhận vào của explicit - visibilityOfElementLocated(By)
		// Implicit = 0
		// Tổng time = explicit
		System.out.println("Thời gian bắt đầu của ExplicitWait " + getTimeStamp());
		try {
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#selenium")));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Thời gian kết thúc của ExplicitWait " + getTimeStamp());
		}
	}

	@Test
	public void TC_05_Element_Not_Found_Explicit_Element() {

		explicitWait = new WebDriverWait(driver, 5);

		driver.get("https://www.facebook.com/");

		// explicitWait - By là tham số nhận vào của explicit - visibilityOfElementLocated(By)
		// Implicit = 0
		// Tổng time = explicit
		System.out.println("Thời gian bắt đầu của ExplicitWait " + getTimeStamp());
		try {
			explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("input#selenium"))));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Thời gian kết thúc của ExplicitWait " + getTimeStamp());
		}
	}

	@Test
	public void TC_06_Real() {

		explicitWait = new WebDriverWait(driver, 5);

		driver.get("https://www.facebook.com/");

		explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("input#email"))));

		driver.findElement(By.cssSelector("input#email")).sendKeys("test");

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	// Show ra time-stamp tại thười điểm gọi hàm này
	// time-stamp = ngày - giờ - phút - giây
	public String getTimeStamp() {
		Date date = new Date();
		return date.toString();
	}

}
