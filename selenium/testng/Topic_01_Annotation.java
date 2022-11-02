package testng;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Topic_01_Annotation {
	@Test()
	public void TC_01() {
		System.out.println("Testcase 01");
	}

	@Test()
	public void TC_02() {
		System.out.println("Testcase 02");

	}

	@BeforeMethod
	public void beforeMethod() {
		System.out.println("Before Methode");

	}

	@AfterMethod
	public void afterMethod() {
		System.out.println("After Method");

	}

	@BeforeClass
	public void beforeClass() {
		System.out.println("Before Class");

	}

	@AfterClass
	public void afterClass() {
		System.out.println("After Class");

	}

	@BeforeTest
	public void beforeTest() {
		System.out.println("Before Test");

	}

	@AfterTest
	public void afterTest() {
		System.out.println("After Test");

	}

	@BeforeSuite
	public void beforeSuite() {
		System.out.println("Before Suite");

	}

	@AfterSuite
	public void afterSuite() {
		System.out.println("After Suite");

	}

}
