package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.server.handler.GetCurrentUrl;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Web_Browser_Element_P2 {

	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_01_Current_Url() {
		driver.get("http://live.techpanda.org/");	
		
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		
		String loginPageUrl = driver.getCurrentUrl();
		
		Assert.assertEquals(loginPageUrl, "http://live.techpanda.org/index.php/customer/account/login/");
		
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
				
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/create/");
		
	}

	@Test
	public void TC_02_Page_Title() {
		driver.get("http://live.techpanda.org/");
		
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		
	    String loginPageUrl = driver.getCurrentUrl();
	    
	    Assert.assertEquals(driver.getTitle(), "Customer Login");
	    
	    driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
	    
	    Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
		
	}

	@Test
	public void TC_03_Navigation() {
		driver.get("http://live.techpanda.org/");
		
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		
	    Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/create/");
	    
	    driver.navigate().back();
	    
	    Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/login/");
	    
	    driver.navigate().forward();
	    
	    Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/create/");
	}

	@Test
	public void TC_04_Page_Source() {
		driver.get("http://live.techpanda.org/");
		
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		
		String loginPageSource = driver.getPageSource();
		Assert.assertTrue(loginPageSource.contains("Login or Create an Account"));
		
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		Assert.assertTrue(driver.getPageSource().contains("Create an Account"));
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
