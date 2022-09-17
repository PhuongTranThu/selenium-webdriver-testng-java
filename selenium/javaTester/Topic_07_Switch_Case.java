package javaTester;

import java.util.Scanner;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Topic_07_Switch_Case {

	WebDriver driver;
	Scanner scanner = new Scanner(System.in);
	String projectPath = System.getProperty("user.dir");

	@Parameters("browser")
	@Test
	public void TC_01_(String browserName) {
		switch (browserName) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver");
			driver = new ChromeDriver();
			break;
		case "firefox":
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
			driver = new FirefoxDriver();
		default:
			new RuntimeException("Please input correct the browser name!");
			break;

		}
	}

	@Test
	public void TC_02_If_Else_If_Else() {
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

	@Test
	public void TC_04() {

		int firstNumber = scanner.nextInt();
		int secondNumber = scanner.nextInt();
		String operator = scanner.next();

		switch (operator) {
		case "+":
			System.out.println("A + B = " + (firstNumber + secondNumber));
			break;

		case "-":
			System.out.println("A - B = " + (firstNumber - secondNumber));
			break;

		case "*":
			System.out.println("A x B = " + (firstNumber * secondNumber));
			break;

		case "/":
			System.out.println("A / B = " + (firstNumber / secondNumber));
			break;
		default:
			break;
		}
	}
}
