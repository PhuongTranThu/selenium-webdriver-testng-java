package testng;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Topic_08_Depend {

	@Test
	public void Product_01_Create_Product() {
		Assert.assertTrue(false);
	}

	@Test(dependsOnMethods = "Product_01_Create_Product")
	public void Product_02_View_Product() {
	}

	@Test(dependsOnMethods = "Product_02_View_Product")
	public void Product_03_Update_Product() {
	}

	@Test(dependsOnMethods = "Product_03_Update_Product")
	public void Product_04_Delete_Product() {
	}

}
