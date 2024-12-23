package ameera.PageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ameera.AbstractsComponents.AbstractComponents;

public class LeavePage extends AbstractComponents {

	public WebDriver driver;

	public LeavePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// ============================================
	// Web Elements (Page Object Model Elements)
	// ============================================
	private By bySuccessMessage = By.cssSelector(".oxd-toast.oxd-toast--success.oxd-toast-container--toast");

	@FindBy(css = "header[class='oxd-topbar'] li:nth-child(1) a:nth-child(1)")
	private WebElement applyButton;

	@FindBy(xpath = "//div[@class='oxd-select-text-input']")
	private WebElement leaveType;

	@FindBy(css = "div[role = \"listbox\"]")
	private List<WebElement> leaveTypeList;

	@FindBy(css = ":nth-child(1) > .oxd-input-group > :nth-child(2) > .oxd-date-wrapper > .oxd-date-input > .oxd-input")
	private WebElement fromDate;

	@FindBy(css = ":nth-child(2) > .oxd-input-group > :nth-child(2) > .oxd-date-wrapper > .oxd-date-input > .oxd-input")
	private WebElement toDate;

	@FindBy(css = ".oxd-textarea--resize-vertical")
	private WebElement comment;

	@FindBy(xpath = "//button[@type='submit']")
	private WebElement submitButton;

	@FindBy(css = ".oxd-toast--success")
	private WebElement successMessage;

	// ============================================
	// Page Actions (Methods)
	// ============================================

	public void addLeaveRequest() {
		applyButton.click();
		waitForWebElementToAppear(leaveType);
		leaveType.click();
		WebElement fmla = leaveTypeList.stream().filter(x -> x.getText().contains("US")).findFirst()
				.orElseThrow(() -> new NoSuchElementException("FMLA option not found"));

		fmla.click();
		fromDate.sendKeys("2024-20-12");
		toDate.clear();
		//toDate.sendKeys("2024-20-12");

		comment.sendKeys("This is my leave request");
		submitButton.click();
	}

	public String getSuccessMessage() {
		waitForElementToAppear(bySuccessMessage); // Wait for success message to appear
		return successMessage.getText(); // Return message text
	}
}
