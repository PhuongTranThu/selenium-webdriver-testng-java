package javaTester;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.Test;

import com.beust.jcommander.Parameters;

public class Topic_06_Condition_Statement {

	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	@Test
	public void TC_01_If() {
			
			boolean status = 5 > 3;
			System.out.println(status);
			
			// Hàm if sẽ nhận vào 1 điều kiện đúng
			// Kiểm tra duy nhất 1 điều kiện
			// Nếu điều kiện này đúng thì sẽ action (xxx) trong phần thân
			if (status) {
				// đúng thì vào phần thân của if
				// Sai thì bỏ qua
				System.out.println("Go to if");
				
			}
			
			// Gán (assign)
			int studentNumber = 10;
			
			// == So 
			status = (studentNumber != 10);
			
			WebDriver driver = new FirefoxDriver();
			
			WebElement salePopup = driver.findElement(By.id(""));
			// Element luôn luôn có trong DOM dù popup hiển thị hay không
			if (salePopup.isDisplayed()) {
				
			}
			
			// Element ko có trong DOM khi popup không hiển thi
			List<WebElement> salePopups = driver.findElements(By.id(""));
			if (salePopups.size()>0 && salePopups.get(0).isDisplayed()) {
				
			}
			
			// Uncheck to checkbox
			WebElement languagesCheckbox = driver.findElement(By.id(""));
			if (languagesCheckbox.isSelected()) {
				languagesCheckbox.click();
			}
			
			// Check to checkbox
			if (!languagesCheckbox.isSelected()) {
				languagesCheckbox.click();
			}
			
			// Có tới 2 điều kiện: nếu đúng thì vào if - sai thì vào else
			
		}
	@Test
	public void TC_02_If_Else() {
	
		// Có tới 2 điều kiện: nếu như đúng thì vào if - sai thì vào else
		
		// Nếu driver với browser như Chrome, Firefox, Edge, Safari thì dùng hàm click
		// thông thường (builtin) của Selenium của Element
		
		// Nếu driver là IE thì dùng hàm click của JavascriptExecutor
		System.setProperty("webdriver.ie.driver", projectPath + "/browserDrivers/geckodriver");
		driver = new ChromeDriver();
		
		System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
		driver = new ChromeDriver();
	
		if(driver.toString().contains("internet explorer")) {
			System.out.println("Click by Javascript Executor");
		} else {
			System.out.println("Click by Selenium WebElement");
		}
	
	}
	
	@org.testng.annotations.Parameters("browser")
	@Test
	public void TC_03_If_Else_If_Else(String browserName) {
		if (browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("ie")) {
			System.setProperty("webdriver.ie.driver", projectPath + "/browserDrivers/iedriver");
			driver = new InternetExplorerDriver();
		} else { // safari/ Opera/ Coccoc...
			throw new RuntimeException("Please input correct the browser name!");
		}
	}

	@Test
	public void TC_04_If_Else_If_Else() {
		// LoginPage Object
		// Dynamic Page
		
		String pageName = "Login";
		
		if (pageName.equals("Login")) {
			// LoginPage loginPage = new LoginPage();
			// return loginPage;
		} else if (pageName.equals("Register")) {
			// RegisterPage registerPage = new RegisterPage();
			// return RegisterPage;
		} else if (pageName.equals("New Customer")) {
			// CustomerPage customerPage = new CustomerPage();
			// return CustomerPage;
		} else {
			// HomePage homePage = new HomePage();
			// return HomePage;
		}
		
		// if else
		int age = 20;
		String access = (age < 18) ? "You can not access" : "Welcome to our system!";
		
		System.out.println(access);
	}
	}





