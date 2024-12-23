package ameera.Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import ameera.PageObject.LeavePage;
import ameera.TestComponents.BaseTest;

public class ManageLeaveTest extends BaseTest  {
	@Test
	public void addLeaveRequest() {
		landingPage.loginApplication();
		LeavePage leavePage = landingPage.goToLeave();
		leavePage.addLeaveRequest();
		Assert.assertTrue(leavePage.getSuccessMessage().contains("Successfully Saved"), "Employee Leave request failed.");
	}
}
