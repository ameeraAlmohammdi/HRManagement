package ameera.Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import ameera.PageObject.RecruitmentPage;
import ameera.TestComponents.BaseTest;

public class ManageRecruitmentTest extends BaseTest {
	@Test
	public void addRecruitment() {
		landingPage.loginApplication();
		RecruitmentPage recPage = landingPage.goToRecruitment();
		recPage.addRecruitment();
		Assert.assertTrue(recPage.getSuccessMessage().contains("Successfully Saved"), "Employee Recruitment failed.");
	}
}
