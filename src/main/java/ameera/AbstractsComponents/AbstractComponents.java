package ameera.AbstractsComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import ameera.PageObject.ClaimPage;
import ameera.PageObject.LeavePage;
import ameera.PageObject.PIMPage;
import ameera.PageObject.RecruitmentPage;

public class AbstractComponents {
	WebDriver driver;

	public AbstractComponents(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[2]/ul[1]/li[2]/a[1]")
	WebElement PIMButton;

	@FindBy(xpath = "//span[normalize-space()='Recruitment']")
	WebElement RecButton;

	@FindBy(xpath = "//span[normalize-space()='Leave']")
	WebElement leaveButton;
	
	@FindBy(xpath = "//span[normalize-space()='Claim']")
	WebElement claimButton;

	public PIMPage goToPIM() {
		PIMButton.click();
		PIMPage pimPage = new PIMPage(driver);
		return pimPage;
	}

	public RecruitmentPage goToRecruitment() {
		RecButton.click();
		RecruitmentPage recPage = new RecruitmentPage(driver);
		return recPage;
	}

	public LeavePage goToLeave() {
		leaveButton.click();
		LeavePage leavePage = new LeavePage(driver);
		return leavePage;
	}
	public ClaimPage goToClaim() {
		claimButton.click();
		ClaimPage claimPage = new ClaimPage(driver);
		return claimPage;
	}

	public void waitForElementToAppear(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}

	public void waitForWebElementToAppear(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(ele));
	}

	public void waitForWebElementToBeClickable(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.elementToBeClickable(ele));
	}

	public void waitForWebElementStale(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.stalenessOf(ele));
	}

}
