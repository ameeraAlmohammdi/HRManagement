package ameera.Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import ameera.PageObject.ClaimPage;
import ameera.TestComponents.BaseTest;

public class ManageClaimTest  extends BaseTest  {
	@Test
	public void addClaim() throws InterruptedException {
		landingPage.loginApplication();
		ClaimPage claimPage = landingPage.goToClaim();
		claimPage.addClaim();
		Assert.assertTrue(claimPage.getSuccessMessage().contains("Successfully Saved"), "Employee Claim request failed.");
	}
}
