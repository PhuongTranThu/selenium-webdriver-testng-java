package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Web_Browser_Element_P3 {

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
	public void TC_01_Is_Display() {
		// - Có thể nhìn thấy
		// - Có kích thước: dài rộng
		// - Phạm vi áp dụng: Tất cả các loại Element (Textbox, TextArea, Radio, Link, Button)
		
		driver.get("https://automationfc.github.io/basic-form/index.html");	
		
		// Email textbox
		WebElement emailTextbox = driver.findElement(By.cssSelector("input#email"));
		
		if (emailTextbox.isDisplayed()) {
			emailTextbox.sendKeys("Automation FC");
			System.out.println("emailTextbox is displayed");
		} else {
			System.out.println("emailTextbox is not displayed");
			
		}
		System.out.println(emailTextbox.isDisplayed());
		
		// Age under 18 radio button
		WebElement ageUnder18Radio = driver.findElement(By.cssSelector("input#under_18"));
		
		if (ageUnder18Radio.isDisplayed()) {
			ageUnder18Radio.click();
			System.out.println("Age Under 18 Radio is Displayed");
			
		} else {
			System.out.println("Age Under 18 Radio is not Displayed");
		}
		System.out.println(ageUnder18Radio.isDisplayed());
		
		// Education TextArea
		
		WebElement educationTextArea = driver.findElement(By.cssSelector("textarea#edu"));
		if (educationTextArea.isDisplayed()) {
			educationTextArea.sendKeys("Automation Testting Area");
			System.out.println("Education TextArea is displayed");
		} else {
			System.out.println("Education TextArea is not displayed");
		}
		System.out.println(educationTextArea.isDisplayed());
		
		// Image 5 (Undisplay)
		
		WebElement image5 = driver.findElement(By.xpath("//h5[text()='Name: User5']"));
		if (image5.isDisplayed()) {
			System.out.println("Image 5 is displayed");
		} else {
			System.out.println("Image 5 is not displayed");
		}
		
		// Nếu element hiển thị thì hàm isDisplayed trả về true
		// Nếu element hiển thị thì hàm isDisplayed trả về true
		
		System.out.println(image5.isDisplayed());
	}

	@Test
	public void TC_02_Is_Enable() {
		// - Tương tác lên được -> Enable -> true
		// - Không tương tác lên được -> Disable -> false
		// - Phạm vi áp dụng: Tất cả các loại Element (Textbox, TextArea, Radio, Link, Button)
		
		driver.get("https://automationfc.github.io/basic-form/index.html");	
		// Email Textbox
		WebElement emailTextbox = driver.findElement(By.cssSelector("input#email"));
		if (emailTextbox.isEnabled()) {
			System.out.println("Email Textbox is enabled");
		} else {
			System.out.println("Email Textbox is disabled");
		}
		
		// Job Role 1 Dropdown
		WebElement jobRole1Dropdown = driver.findElement(By.cssSelector("input#email"));
		if (jobRole1Dropdown.isEnabled()) {
			System.out.println("Job Role 1 Dropdown is enabled");
		} else {
			System.out.println("Job Role 1 Dropdown is enabled");
		}
		
		//Password Textbox
		WebElement passwordTextbox = driver.findElement(By.cssSelector("input#disable_password"));
		if (passwordTextbox.isEnabled()) {
			System.out.println("Password Textbox is enabled");
		} else {
			System.out.println("Password Textbox is disabled");
		}
	}


	@Test
	public void TC_03_Is_Selected() {
		// - Đã được chọn -> Selected
		// - Chưa được chọn -> Diselected
		// - Phạm vi áp dụng: Checkbox, Drop down, Radio button
		
		// Age Under 18 Radio
		driver.get("https://automationfc.github.io/basic-form/index.html");	
		WebElement ageUnder18Radio = driver.findElement(By.cssSelector("input#under_18"));
		ageUnder18Radio.click();
		if (ageUnder18Radio.isSelected()) {
			System.out.println("Age Under 18 Radio is selected");
		} else {
			System.out.println("Age Under 18 Radio is de-selected");
		}
		
		// Java Checkbox
		WebElement javaCheckbox = driver.findElement(By.cssSelector("input#java"));
		javaCheckbox.click();
		if (javaCheckbox.isSelected()) {
			System.out.println("Java Checkbox is selected");
		} else {
			System.out.println("Java Checkbox is de-selected");
		}
		
	}
	

	@Test
	public void TC_04_MailChimp() {
		driver.get("https://login.mailchimp.com/signup/");
		
		// Email Username Textbox
		driver.findElement(By.cssSelector("input#email")).sendKeys("phuong@gmail.com");
		sleepInSecond(3);
		
		WebElement passwordTextbox = driver.findElement(By.cssSelector("input#new_password"));
		
		// Check lowercase (Viết thường)
		passwordTextbox.sendKeys("a");
		sleepInSecond(2);
		
		Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
		
		// Check uppercase
		passwordTextbox.clear();
		passwordTextbox.sendKeys("AAA");
		sleepInSecond(2);
		
		Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
		
		// Check number
		passwordTextbox.clear();
		passwordTextbox.sendKeys("12345");
		sleepInSecond(2);
		
		Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
		
		// Check special
		passwordTextbox.clear();
		passwordTextbox.sendKeys("@!#");
		sleepInSecond(2);
		
		Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char not-completed']")).isDisplayed());
		
		// Check 8 char
		passwordTextbox.clear();
		passwordTextbox.sendKeys("1234567890");
		sleepInSecond(2);
		
		Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char completed']")).isDisplayed());
		
	}

	@AfterClass
	public void afterClass() {
		//driver.quit();
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
