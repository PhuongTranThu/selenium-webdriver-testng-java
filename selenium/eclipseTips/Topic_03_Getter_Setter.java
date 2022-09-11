package eclipseTips;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class Topic_03_Getter_Setter {

	WebDriver driver;

	@Test // Dev A
	public void Login_01_Email_Empty() {

	}

	@Test // Dev B
	public void Login_02_email_invalid() throws InterruptedException {
		Thread.sleep(5000);

		System.out.println(driver instanceof WebDriver);
		// Local
		WebDriver driver = null;
		driver.get("");

		// Global
		this.driver.get("");
	}

	@Test // Dev C
	public void Login_03_EmailIncorrect() {

	}

}
