package javaTester;

import org.testng.annotations.Test;

public class Topic_11_Exercise_Array {

	int number[] = { 5, 8, 15, 7, 60, 1 };

	@Test
	public void TC_01_Find_Max_Number() {
		int x = 0;
		for (int i = 0; i < number.length; i++) {
			if (x < number[i]) {
				x = number[i];
			}
		}

		System.out.println("First number = " + number[0]);
		System.out.println("Last number = " + number[number.length - 1]);
		System.out.println("Max number = " + x);
	}

	@Test
	public void TC_02_Sum_First_And_Last_Number() {
		System.out.println(number[0] + number[number.length - 1]);
	}

	@Test
	public void TC_03() {
		for (int i = 0; i < number.length; i++) {
			if (number[i] % 2 == 0)
				System.out.println("Even number = " + number[i]);
		}
	}

	@Test
	public void TC_04() {
		for (int i = 0; i < number.length; i++) {
			if (number[i] > 0 & number[i] <= 10)
				System.out.println("Number in (0<=number<=10) = " + number[i]);
		}
	}

	@Test
	public void TC_05() {
		int sum = 0;
		for (int i = 0; i < number.length; i++) {
			sum += number[i];
		}
		System.out.println("sum = " + sum);

		float average = sum / number.length;
		System.out.println("Average all number = " + average);
	}
}
