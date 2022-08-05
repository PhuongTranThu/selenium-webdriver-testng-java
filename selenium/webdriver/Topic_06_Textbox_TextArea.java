package webdriver;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Textbox_TextArea {

	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String firstName, lastName, employeeID, editFirstName, editLastName;
	String immigrationNumber, comments;


	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		firstName = "Bento";
		lastName = "Tento";
		editLastName = "Sara";
		editFirstName = "Sukara";
		immigrationNumber = "774703475";
		comments = "79 Madeira Way\nMadeira Bench\nFL 3778 USA";
		
			}

	@Test
	public void TC_01_Textbox_Text_Area() {
		driver.get("https://opensource-demo.orangehrmlive.com/");
		
		// Nhập vào User/ Pass
		driver.findElement(By.cssSelector("input#txtUsername")).sendKeys("Admin");
		driver.findElement(By.cssSelector("input#txtPassword")).sendKeys("admin123");
		
		// Click vào button login
		driver.findElement(By.cssSelector("input#btnLogin")).click();
		
        // Mở màn hình Add
		driver.get("https://opensource-demo.orangehrmlive.com/index.php/pim/addEmployee");
		sleepInSecond(5);
				
		// Nhập dữ liệu vào màn hình Add Employee
		driver.findElement(By.cssSelector("input#firstName")).sendKeys(firstName);
		driver.findElement(By.cssSelector("input#lastName")).sendKeys(lastName);
		
		// Lưu giá trị của employee vào biến
		employeeID = driver.findElement(By.cssSelector("input#employeeId")).getAttribute("value");
		
		// Click button Save
		driver.findElement(By.cssSelector("input#btnSave")).click();
		
		// Verify các textbox ở trang personal employee bị disable
		Assert.assertFalse(driver.findElement(By.cssSelector("input#personal_txtEmpFirstName")).isEnabled());
		Assert.assertFalse(driver.findElement(By.cssSelector("input#personal_txtEmpLastName")).isEnabled());
		Assert.assertFalse(driver.findElement(By.cssSelector("input#personal_txtEmployeeId")).isEnabled());
		
		// Verify actual value the same expected value
		Assert.assertEquals(driver.findElement(By.cssSelector("input#personal_txtEmpFirstName")).getAttribute("value"), firstName);
		Assert.assertEquals(driver.findElement(By.cssSelector("input#personal_txtEmpLastName")).getAttribute("value"), lastName);
		Assert.assertEquals(driver.findElement(By.cssSelector("input#personal_txtEmployeeId")).getAttribute("value"), employeeID);

		// Click to button Save
		driver.findElement(By.cssSelector("input#btnSave")).click();
				
		// Verify the fields are enable
		Assert.assertTrue(driver.findElement(By.cssSelector("input#personal_txtEmpFirstName")).isEnabled());
		Assert.assertTrue(driver.findElement(By.cssSelector("input#personal_txtEmpLastName")).isEnabled());
		Assert.assertTrue(driver.findElement(By.cssSelector("input#personal_txtEmployeeId")).isEnabled());
		
		// Edit the fields firstname, lastname
		driver.findElement(By.cssSelector("input#personal_txtEmpFirstName")).clear();
		driver.findElement(By.cssSelector("input#personal_txtEmpFirstName")).sendKeys(editFirstName);
		driver.findElement(By.cssSelector("input#personal_txtEmpLastName")).clear();
		driver.findElement(By.cssSelector("input#personal_txtEmpLastName")).sendKeys(editLastName);
		
		// Click to Save button
		driver.findElement(By.cssSelector("input#btnSave")).click();
		sleepInSecond(3);
		
		// Verify các textbox ở trang personal employee bị disable
		Assert.assertFalse(driver.findElement(By.cssSelector("input#personal_txtEmpFirstName")).isEnabled());
		Assert.assertFalse(driver.findElement(By.cssSelector("input#personal_txtEmpLastName")).isEnabled());
		Assert.assertFalse(driver.findElement(By.cssSelector("input#personal_txtEmployeeId")).isEnabled());
		
		// Verify actual value the same expected value
		Assert.assertEquals(driver.findElement(By.cssSelector("input#personal_txtEmpFirstName")).getAttribute("value"), editFirstName);
		Assert.assertEquals(driver.findElement(By.cssSelector("input#personal_txtEmpLastName")).getAttribute("value"), editLastName);
		Assert.assertEquals(driver.findElement(By.cssSelector("input#personal_txtEmployeeId")).getAttribute("value"), employeeID);

		// Open Immigration
		driver.findElement(By.xpath("//a[text()='Immigration']")).click();
		
		// Click to Add button
		driver.findElement(By.cssSelector("input#btnAdd")).click();
		
		// Enter to Immigration Number and Comments
		driver.findElement(By.cssSelector("input#immigration_number")).sendKeys(immigrationNumber);
		driver.findElement(By.cssSelector("textarea#immigration_comments")).sendKeys(comments);
		sleepInSecond(5);
		
		// Click to Save button
		driver.findElement(By.cssSelector("input#btnSave")).click();
		sleepInSecond(3);
		
		// click to Passport link
		driver.findElement(By.xpath("//a[text()='Passport']")).click();
		
		// Verify actual value sale expected value 
		Assert.assertEquals(driver.findElement(By.cssSelector("input#immigration_number")).getAttribute("value"), immigrationNumber);
		Assert.assertEquals(driver.findElement(By.cssSelector("textarea#immigration_comments")).getAttribute("value"), comments);


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



