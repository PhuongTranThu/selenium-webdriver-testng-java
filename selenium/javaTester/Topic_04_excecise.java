package javaTester;

import org.testng.annotations.Test;

public class Topic_04_excecise {
	
	
	@Test
	public void swapNumber() {
		int a = 5;
		int b = 6;
		
		a = a + b;
		b = a - b;
		a = a - b;
		System.out.println(a);
		System.out.println(b);
	}

}
