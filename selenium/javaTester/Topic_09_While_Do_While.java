package javaTester;

import java.util.Scanner;

import org.testng.annotations.Test;

// Class
public class Topic_09_While_Do_While {

	static Scanner scanner = new Scanner(System.in);

	// Function
	public static void main(String[] args) {
		Topic_09_While_Do_While topic = new Topic_09_While_Do_While();
		topic.TC_04_While();

		TC_01_For();
		TC_02_While();
		TC_03_Do_While();
		TC_04_While();
	}

	@Test
	public static void TC_01_For() {
		int number = scanner.nextInt();

		for (int i = number; i < 100; i++) {
			if (i % 2 == 0) {
				System.out.println(i);
			}
		}
	}

	@Test
	public static void TC_02_While() {
		int number = scanner.nextInt();

		while (number < 100) {
			if (number % 2 == 0)
				System.out.println(number);
			number++;

		}
	}

	@Test
	public static void TC_03_Do_While() {
		int number = scanner.nextInt();

		do {
			if (number % 2 == 0) {
				System.out.println(number);
				number++;
			}
		} while (number < 100);
	}

	@Test
	public static void TC_04_While() {
		int numberA = scanner.nextInt();
		int numberB = scanner.nextInt();

		while (numberA < numberB) {
			if (numberA % 3 == 0 && numberA % 5 == 0) {
				System.out.println(numberA);

			}
			numberA++;
		}
	}

	@Test
	public static void TC_05_While() {
		int numberA = scanner.nextInt();
		int i = 0;

		while (numberA > 0) {
			if (numberA % 2 != 0) {
				i += numberA;
				// i = i + numberA

			}
			numberA--;
		}
		System.out.println(i);
	}

	@Test
	public static void TC_06_While() {
		int numberA = scanner.nextInt();
		int i = 1;

		while (numberA > 0) {
			i *= numberA;
			// i = i + numberA
			numberA--;
		}
		System.out.println(i);
	}

}
