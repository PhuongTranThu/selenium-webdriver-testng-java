package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_Xpath_Part1 {

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
	public void TC_01() {
		driver.get("https://www.facebook.com/");	
		
		//HTML format của element
		/*<input class= "text from-control"  
		 * id="txtEmail" name="txtEmail" 
		 * placeholder="Địa chỉ email" 
		 * type="Email" value="">*/
		 
		// input[@class='text form-control']
		// input[@id='txtEmail'] -* *
		// input[@name='txtEmail'] -* *
		// input[@placeholder='Địa chỉ email']
		// input[@type='email']
		// input[@value='']
		
	
		// 1 - < hoặc <>
		// 2 - Tên thẻ (Tagname): html/input/head/body/form/label/span...
		// 3 - Thuộc tính (attribute name): class/ id/ placeholder/ type/ value...
		// 4 - giá trị của thuộc tính (Attribute value): txtLoginUsername/ Địa chỉ email...
		// 5 - > hoặc </>
		
		//tagname - attribute value - attribute value
		
		//Xpath format (tuyệt đối)
		//Absolute XPath: /html/body/...
		//Realative XPath: //tagname[@attribute-name='attribute value']
		//Realative XPath: //tagname[@attribute-name="attribute value"]
		
		//Css format
		// tagname[attribute-name='attribute value']
		// tagname[attribute-name="attribute value"]
	}

	@Test
	public void TC_02() {
		
	}


	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
