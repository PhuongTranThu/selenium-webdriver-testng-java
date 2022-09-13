package javaTester;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Topic_12_String {

	public static void main(String[] args) {
		System.setProperty("webdriver.gecko.driver", "./browserDrivers/geckodriver");
		WebDriver driver = new FirefoxDriver();

		String schoolName = "Automation Testing";
		String courseName = schoolName.toLowerCase();
		String schoolAddress = "Ho Chi Minh City";

		System.out.println("Do dai = " + schoolName.length());
		System.out.println("Lay ra 1 ky tu = " + schoolName.charAt(0));
		System.out.println("Nối 2 chuỗi = " + schoolName.concat(schoolAddress));
		System.out.println("Nối 2 chuỗi = " + schoolName + schoolAddress);

		// Tuyệt đối
		System.out.println("Kiểm tra 2 chuỗi nối bằng nhau tuyệt đối = " + schoolName.equals(schoolAddress));
		System.out.println("Kiểm tra 2 chuỗi nối bằng nhau tuyệt đối = " + schoolName.equals("Automation Testing"));

		// Nulti browser
		System.out.println("Kiểm tra 2 chuỗi nối bằng nhau tương đối = " + schoolName.equalsIgnoreCase(courseName));

		// startsWith/ endsWith/ contains
		System.out.println("Có bắt đầu bằng 1 kí tự/ chuối kí tự = " + schoolName.startsWith("A"));
		System.out.println("Có bắt đầu bằng 1 kí tự/ chuối kí tự = " + schoolName.startsWith("Automation Testing"));

		System.out.println("Có chứa 1 kí tự/ chuối kí tự = " + schoolName.contains("Automation Testing"));
		System.out.println("Có chứa 1 kí tự/ chuối kí tự = " + schoolName.contains("Testing"));

		System.out.println("Có kết thúc bằng 1 kí tự/ chuỗi kí tự = " + schoolName.endsWith("Testing"));
		System.out.println("Có kết thúc bằng 1 kí tự/ chuỗi kí tự = " + schoolName.endsWith("g"));
		System.out.println("Có kết thúc bằng 1 kí tự/ chuỗi kí tự = " + schoolName.endsWith("Automation"));

		System.out.println("Vị trí của 1 kí tự/ chuỗi kí tự trong chuỗi = " + schoolName.indexOf("Automation"));
		System.out.println("Vị trí của 1 kí tự/ chuỗi kí tự trong chuỗi = " + schoolName.indexOf("A"));
		System.out.println("Vị trí của 1 kí tự/ chuỗi kí tự trong chuỗi = " + schoolName.indexOf("Testing"));

		System.out.println("Vị trí của 1 kí tự/ chuỗi kí tự trong chuỗi = " + schoolName.substring(11));
		System.out.println("Vị trí của 1 kí tự/ chuỗi kí tự trong chuỗi = " + schoolName.substring(11, 15));

		// Split: Tách chuối thành 1 mảng dựa vào kí tự/ chuỗi kí tự
		String result = "Viewing 48 of 132 results";
		String results[] = result.split(" ");

		System.out.println(results[1]);

		// Replace
		String productPrice = "$100.00";
		productPrice = productPrice.replace("$", " ");

		// Sắp xếp nó: Sort Data (Asc/ Desc)
		float productPriceF = Float.parseFloat(productPrice);
		System.out.println(productPriceF);

		productPrice = String.valueOf(productPriceF);
		System.out.println(productPrice);

		String osName = System.getProperty("os.name");
		System.out.println(osName);
		// Handle multiple OS: MAC, Win
		if (osName.toLowerCase().contains("windows")) {
			Keys key = Keys.CONTROL;
		} else {
			Keys key = Keys.COMMAND;
		}

		String driverInstanceName = driver.toString();
		System.out.println(driverInstanceName);
		if (driverInstanceName.contains("internetexplorer")) {
			// Sleep cung them 5s sau moi sự kiện chuyển page
		}

		String helloWorld = "  \n  \t   Hello World!";
		System.out.println(helloWorld.trim());

		// Dynamic locator
		String dynamicButtonXpath = "//button[@id='%s']";
		System.out.println("Click to login button = " + dynamicButtonXpath.format(dynamicButtonXpath, "login"));
		System.out.println("Click to search button = " + dynamicButtonXpath.format(dynamicButtonXpath, "search"));
	}
}
