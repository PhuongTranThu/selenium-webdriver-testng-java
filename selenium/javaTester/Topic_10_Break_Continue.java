package javaTester;

public class Topic_10_Break_Continue {

	public static void main(String[] args) {
		for (int i = 0; i < 5; i++) {
			System.out.println("Lần chạy của i (for trên) = " + i);

			// Loai tru
			for (int j = 0; j <= 5; j++) {
				if (j == 4) {
					continue;
				}
				System.out.println(j);
			}

		}
	}
}
