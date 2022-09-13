package javaTester;

import org.testng.annotations.Test;

public class Topic_12_Excecise {

	String courseName = "Automation Testing 2022 Advanced";

	@Test
	public void TC_01() {
		char courseNameArr[] = courseName.toCharArray();
		int countUpper = 0;
		int countLowser = 0;
		int countNumber = 0;
		for (char character : courseNameArr) {
			if (character <= 'Z' && character >= 'A') {
				countUpper++;
			}

			if (character <= 'z' && character >= 'a') {
				countLowser++;
			}

			if (character <= '9' && character >= '0') {
				countNumber++;
			}
		}
		System.out.println(countUpper);
		System.out.println(countLowser);
		System.out.println(countNumber);
	}

	@Test
	public void TC_02() {
		char courseNameArr[] = courseName.toCharArray();
		int count = 0;
		for (char c : courseNameArr) {
			if (c == 'a') {
				count++;
			}
		}
		System.out.println(count);
	}

	@Test
	public void TC_03() {

		char courseNameArr[] = courseName.toCharArray();

		for (int i = courseNameArr.length - 1; i >= 0; i--) {
			System.out.print(courseNameArr[i]);

		}
		System.out.println();
	}

}
