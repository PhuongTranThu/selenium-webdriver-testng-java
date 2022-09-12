package javaTester;

import java.util.Arrays;
import java.util.List;

public class Topic_11_Array {

	public static void main(String[] args) {

		int[] student = { 12, 7, 5, 61, 15 };

		// Lay ra phan tu dau tien
		System.out.println(student[0]);
		System.out.println(student[1]);

		String studentName[] = { "Nam", "Long", "An" };
		studentName[0] = "Hoa";

		for (int i = 0; i < studentName.length; i++) {

			if (studentName[i].equals("Long")) {
				System.out.println("Click vào Long");
			}

			List<String> names = Arrays.asList("A", "B", "C");
			for (String name : names) {
				System.out.println("Name: " + name);

			}

			String std_Name = Arrays.toString(studentName);
			System.out.println(std_Name);

			// Ko ket hop voi dieu kien
			// for (String std : studentName) {
			// stdName.
		}
	}

}
