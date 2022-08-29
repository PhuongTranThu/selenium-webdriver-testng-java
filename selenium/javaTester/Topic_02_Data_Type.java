package javaTester;

import java.sql.Date;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Topic_02_Data_Type {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// 1. Kiểu dữ liệu nguyên thủy (int, long, double (float), boolean
		// 8 loại: 
		// Số nguyên: byte, short, int, long
		byte bNumber = 5;
		
		short sNumber = 500;
		
		int iNumber = 6000;
		
		long lNumber = 123456789;
		
		// Số thực: float, double
		
		float salary = 12.5f;
		
		double point = 9.8d;
		// Ký tự: Char
		char a = 'a';
		
		// Logic: boolean
		Boolean marriedStatus=true;
		marriedStatus=false;
		
		// 2. Kiểu dữ liệu tham chiếu
		// Chuỗi: String (Chữ, số, ký tự đặc biệt)
		//Dấu nháy đôi
		String emailInvalid ="abd@123#vc";
		
		// Class/ interface (Date time)
		
		Date date= new Date(0, 0, 0);
		
		WebDriver driver= new FirefoxDriver();
		
		WebDriver driver2;
		
		// Đối tượng/ Object
		Object student;
		
		//ArraStringy: mảng (Phải khai báo số lượng dữ liệu trước)- Cố định số lượng
		int number[]= {10, 15, 20};
		String address[]= { "Đà Năng", "Hà Nội", "Hồ Chí Minh"};
		
		// Class 
		Topic_02_Data_Type topic;
		
		
		// List/ set/ Queue/ Map (Collection)- Động
		List<Integer> studentNumber = new ArrayList<>();
		List<String> studentAdress = new ArrayList<String>();
		Set<String> studentCitySet = new LinkedHashSet<>();
		List<WebElement> homePageLinks = driver.findElements(By.tagName("a"));
		Set<String> allWindows = driver.getWindowHandles();
		
	}

}
