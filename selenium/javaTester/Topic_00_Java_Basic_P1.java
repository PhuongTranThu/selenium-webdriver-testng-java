package javaTester;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_00_Java_Basic_P1 {
	
	// Toàn cục
	String homePageUrl="";
	
	@BeforeClass
	public void beforeClass() {
		
	}

	@Test
	public void TC_01() {
		
	// Cục bộ: Sử dụng trong phạm vi của testcase/hàm
		// Toàn cục
		String homePageUrl="";

		// block 
	if (3<5) {
		// CỤc bộ
		String homePageUrl1="";
		
		System.out.println(homePageUrl1);
	}
	}

	@Test
	public void TC_02() {

	}
}
