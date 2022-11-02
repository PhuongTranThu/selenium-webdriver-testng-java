package testng;

import org.testng.annotations.Test;

public class Topic_03_Priority {

	@Test(enabled = true, description = "JIRA_0787_Create a new Employee ans verify Employee")
	public void EndUser_01_Register_New_Employee() {

	}

	@Test
	public void EndUser_02_View_Employee() {

	}

	@Test
	public void EndUser_03_Edit_Employee() {

	}

	@Test(enabled = false)
	public void EndUser_04_Move_Employee() {

	}

}
