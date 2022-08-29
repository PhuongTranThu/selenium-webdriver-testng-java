package javaTester;

import org.testng.Assert;

public class Topic_04_Operator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int number = 10;
		number +=5;
		number = number + 5;
		System.out.println(number);
		
		// 15/5=3
		System.out.println(number/5);
		
		// 15%6 = 2 dư 3
		System.out.println(15 % 7);
		
		// tăng 1, in trước rồi cộng sau
		System.out.println(number++);
		// In ra trước 10
		// Tăng number lên 1 -> =11
		
		System.out.println(++number);
		// Cộng cộng trước: tăng number lên 1 =12
		// In number ra : 12
		
		// giảm 1
		System.out.println(number--);
		
		Assert.assertTrue(5 < 6);
		String address = "Ho Chi Minh";
		
		if (address != "Ha Noi" && address != "Da Nang") {
			System.out.println("Address is not the same");
		}
		// Tam nguyên: = ? :
		boolean status = (address == "Ha Noi") ? true : false; 
		
		System.out.println(status);

	}

}
