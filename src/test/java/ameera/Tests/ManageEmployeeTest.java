package ameera.Tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ameera.PageObject.PIMPage;
import ameera.TestComponents.BaseTest;

public class ManageEmployeeTest extends BaseTest {

	@Test(dataProvider = "getData")
	public void addEmployee(HashMap<String, String> input) throws IOException {
		
		PIMPage pimPage = landingPage.loginApplication();
		pimPage.goToPIM();

		pimPage.addEmployee(input.get("employeeName"), input.get("employeeMiddleName"), input.get("employeeLastName"),
				 input.get("username"));

		Assert.assertTrue(pimPage.getSuccessMessage().contains("Successfully Saved"), "Employee addition failed.");
	}

	@Test(dataProvider = "getData" , dependsOnMethods = { "addEmployee" })
	public void deleteEmployee(HashMap<String, String> input) throws IOException {

		PIMPage pimPage = landingPage.loginApplication();
		pimPage.goToPIM();
		pimPage.deleteEmployee(input.get("employeeName"),input.get("employeeID"));
		Assert.assertTrue(pimPage.getDeleteMessage().contains("Successfully Deleted"), "Employee deletion failed.");
	}

	@Test
	public void updateEmployee(HashMap<String, String> input) throws IOException {
		PIMPage pimPage = landingPage.loginApplication();
		pimPage.goToPIM();

		// Update the employee's details
		pimPage.updateEmployee(input.get("employeeName"), "ameera");
		Assert.assertTrue(pimPage.getSuccessMessage().contains("Successfully Updated"), "Employee update failed.");
	}

	@DataProvider
	public Object[][] getData() throws IOException {
		// Read employee data from the JSON file and return it
		List<HashMap<String, String>> data = getJsonDataToMap(
				System.getProperty("user.dir") + "\\src\\test\\java\\ameera\\Data\\EmpInfo.json");

		// Return data in a 2D array format for the data provider
		return new Object[][] { { data.get(0) } };
	}
}
