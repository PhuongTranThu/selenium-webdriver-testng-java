package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Selenium_By {

	//Khai báo 1 biến để đại diện cho Selenium WebDriver
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		//Bước 1: Mở Browser lên
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		
		//Bấm cho maximize browser lên
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_01() {
		//Bước 2: Nhập URL
		driver.get("http://live.techpanda.org/index.php/customer/account/login/");
		
		//Bước 3: Click vào My Account  để mở trang Login ra
		
		
	//HTML của elemeny (Email Textbox)
	//	<input type="email" autocapitalize="off" 
	//	autocorrect="off" spellcheck="false" 
	//	name="login[username]" value="" 
	//	id="email" class="input-text required-entry validate-email validation-failed" 
	//	title="Email Address">
		
	// input - thẻ elemement này (tagname)
	// attribute name - type autocapitalize autocorrect spellcheck name value id class title
	// attribute value - email none off...
		
		
	// Xpath
	//tagname[@sttribute-name='attribute-value']
	
    //input[@name='login[username]']
	//input[@id='email']
	//input[@id='Email Address']
		
	//Css Format:  tagname[sttribute-name='attribute-value']
		//Tìm 1 element
		
		//ID - Email Textbox
		driver.findElement(By.id("email"));
		
		//Cách gõ code ít bị lỗi
		//Không được dư ký tự:{ }
		//Không được thiếu ký tự ;
		//Ctrl Space
		
		
		//Class: New user from
		//1: Gía trị không chưa khoảng trắng -> lấy hết
		//2: Giá trị chứa khoảng trắng -> Lấy 1 phần
		driver.findElement(By.className("new-users"));
			
		
		//Name
		driver.findElement(By.name("login[username]"));
		
		//Tagname: Tìm xem có bao nhiêu element trên 1 màn hình
		driver.findElement(By.tagName("a"));
		
		//LinkText (Link) -Text tuyệt đối
		driver.findElement(By.linkText("SEARCH TERMS"));
		
		//Partial LinkTexxt (Link) -Text tương đối/ tuyệt đối
		driver.findElement(By.partialLinkText("SEARCH TERMSs"));
		driver.findElement(By.partialLinkText("SE TER"));
		driver.findElement(By.partialLinkText("SEARCH"));
		driver.findElement(By.partialLinkText("TERMS"));
		
		//Css: Cover được hết cả 6 loại trên
		driver.findElement(By.cssSelector("input[name='login[username]']"));
		driver.findElement(By.cssSelector("input[id='email']"));
		driver.findElement(By.cssSelector("input[id='Email Address']"));
	
		//Xpath
		driver.findElement(By.xpath("//input[@name='login[username]']"));
		driver.findElement(By.xpath("//input[@id='email']"));
		driver.findElement(By.xpath("//input[@id='Email Address']"));
		
		//Tìm nhiều element
		//driver.findElements
	}

	@Test
	public void TC_02() {
		
	}


	@AfterClass
	public void afterClass() {
		//Bước 6: Đóng trình duyệt
		driver.quit();
	}

}
