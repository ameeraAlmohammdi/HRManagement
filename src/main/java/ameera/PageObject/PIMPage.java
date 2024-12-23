package ameera.PageObject;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ameera.AbstractsComponents.AbstractComponents;

public class PIMPage extends AbstractComponents {

	private WebDriver driver;

	public PIMPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this); // Initialize web elements
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}

	// ============================================
	// Web Elements (Page Object Model Elements)
	// ============================================

	// Employee search field by name
	@FindBy(xpath = "(//input[@placeholder='Type for hints...'])[1]")
	private WebElement empName;

	// Employee ID field for searching
	@FindBy(css = "div input[class='oxd-input oxd-input--active']:last-child")
	private WebElement empID;

	// List of employee names (for verification purposes)
	@FindBy(xpath = "//div[@role='cell'][3]")
	private List<WebElement> names;

	// Table rows containing employee data
	@FindBy(css = ".oxd-table-card div:nth-child(3)")
	private List<WebElement> rows;

	// Submit button for submitting search and forms
	@FindBy(xpath = "//button[@type='submit']")
	private WebElement submitButton;

	// Delete icon for employee removal
	@FindBy(xpath = "//div[@role='row']/div[9]//button[2]")
	private WebElement deleteIcon;

	// Success message displayed after deleting an employee
	@FindBy(css = ".oxd-toast--success")
	private WebElement deleteMessage;

	// General success message element
	@FindBy(css = ".oxd-toast--success")
	private WebElement successMessage;

	// Locators for delete and success messages
	private By byDeleteMessage = By.xpath("//div[@class='oxd-toast oxd-toast--success oxd-toast-container--toast']");
	private By bySuccessMessage = By.cssSelector(".oxd-toast.oxd-toast--success.oxd-toast-container--toast");

	// Confirmation button to confirm deletion
	@FindBy(css = "button[class*='oxd-button--label']")
	private WebElement deleteButton;

	// WebElement used for test verification (waiting for page load)
	@FindBy(css = ".oxd-table-body")
	private WebElement test;

	// Button to add a new employee
	@FindBy(xpath = "//button[normalize-space()='Add']")
	private WebElement addButton;

	// Fields for employee details input
	@FindBy(css = "input[placeholder*='First']")
	private WebElement firstNameField;

	@FindBy(css = "input[placeholder*='Middle']")
	private WebElement middleNameField;

	@FindBy(css = "input[placeholder*='Last']")
	private WebElement lastNameField;

	// Switch for toggling additional employee details input
	@FindBy(css = "span[class*='switch-input']")
	private WebElement detailSwitch;

	// Username field for employee account creation
	@FindBy(xpath = "//form[1]/div[1]/div[2]/div[3]/div[1]/div[1]/div[1]/div[2]/input[1]")
	private WebElement usernameField;

	// Username field for employee account creation
	@FindBy(xpath = "//form[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/div[2]/input[1]")
	private WebElement IDField;

	// List of password input fields (for setting password)
	@FindBy(css = "input[type*='password']")
	private List<WebElement> passwordsField;

	// Submit button for confirming employee creation
	@FindBy(xpath = "//button[@type='submit']")
	private WebElement submitButtonCreate;

	// Layout element (used for waiting for elements to load)
	@FindBy(xpath = "//div[@role='row']")
	private WebElement layout;

	// Field for updating the employee's username
	@FindBy(css = "[name*='firstName']")
	private WebElement usernameInputField;

	// Fields for selecting dates (DOB or other dates)
	@FindBy(css = "[placeholder*='yyyy-dd-mm']")
	private List<WebElement> dates;

	// Highlight of the current day in the date picker
	@FindBy(css = ".oxd-calendar-date.--selected.--today")
	private WebElement currentDay;

	// Button for selecting today's date in the date picker
	@FindBy(css = ".oxd-date-input-link.--today")
	private WebElement todayButton;

	// Button to switch to the female gender option
	@FindBy(xpath = "//label[normalize-space()='Female']")
	private WebElement switchFemale;

	// Button to save employee data after updates
	@FindBy(css = "button[type='submit']:nth-child(2)")
	private WebElement saveButton;

	// ============================================
	// Page Actions (Methods)
	// ============================================

	public void addEmployee(String firstName, String middle, String last, String username) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		addButton.click();
		firstNameField.sendKeys(firstName);
		middleNameField.sendKeys(middle);
		lastNameField.sendKeys(last);
	
		detailSwitch.click();
		js.executeScript("window.scrollBy(0,500)"); // Scroll to reveal more elements
		// waitForWebElementToAppear(usernameField);
		usernameField.sendKeys(username); // Enter username
		passwordsField.get(0).sendKeys("Aa123456*"); // Enter password
		passwordsField.get(1).sendKeys("Aa123456*"); // Confirm password
		submitButtonCreate.click(); // Submit form to create employee
	}

	public boolean verifyEmployee(String name) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		empName.sendKeys(name); // Search for employee by name
		submitButton.click(); // Submit search form
		js.executeScript("window.scrollBy(0,500)"); // Scroll for better visibility
		// waitForWebElementToAppear(test); // Wait for search results
		return names.stream().anyMatch(x -> x.getText().contains(name)); // Check if employee exists
	}

	public void deleteEmployee(String name, String ID) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		empName.sendKeys(name);
		//empID.sendKeys(ID);
		submitButton.click(); 
		js.executeScript("window.scrollBy(0,500)"); 
		deleteIcon.click(); 
		deleteButton.click(); 
	}

	public void updateEmployee(String fromName, String toName) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		empName.sendKeys("orrange");
		submitButton.click();
		js.executeScript("window.scrollBy(0,500)");
		waitForWebElementToAppear(layout);
		names.getFirst().click();
		// waitForWebElementToAppear(usernameInputField);
		// usernameInputField.clear();
		usernameInputField.sendKeys(toName);
		dates.get(0).click();
		currentDay.click();
		js.executeScript("window.scrollBy(0,500)");
		dates.get(1).click();
		// waitForWebElementToAppear(todayButton);
		todayButton.click();
		switchFemale.click();
		saveButton.click();
	}

	public String getDeleteMessage() {
		waitForElementToAppear(byDeleteMessage); // Wait for delete message to appear
		return deleteMessage.getText(); // Return message text
	}

	public String getSuccessMessage() {
		waitForElementToAppear(bySuccessMessage); // Wait for success message to appear
		return successMessage.getText(); // Return message text
	}
}
