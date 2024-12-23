package ameera.PageObject;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ameera.AbstractsComponents.AbstractComponents;

public class RecruitmentPage extends AbstractComponents {
	
	public WebDriver driver;

	public RecruitmentPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	// ============================================
	// Web Elements (Page Object Model Elements)
	// ============================================
	
	@FindBy(css = "button[class='oxd-button oxd-button--medium oxd-button--secondary']")
	private WebElement addButton;

	@FindBy(xpath = "//input[@placeholder='First Name']")
	private WebElement firstNameField;

	@FindBy(xpath = "//input[@placeholder='Middle Name']")
	private WebElement middleNameField;

	@FindBy(name = "lastName")
	private WebElement lastNameField;

	@FindBy(xpath = "//div[@class='oxd-select-text-input']")
	private WebElement dropdown;

	@FindBy(xpath = "div[role='listbox']")
	private List<WebElement> dropdownList;
	
	@FindBy(xpath = "//div[3]//div[1]//div[1]//div[1]//div[2]//input[1]")
	private WebElement emailField;

	@FindBy(css = "input[placeholder='yyyy-dd-mm']")
	private WebElement applicationDate;

	@FindBy(xpath = "//div[@class='oxd-calendar-date --selected --today']")
	private WebElement currentDay;

	@FindBy(css = "textarea[placeholder='Type here']")
	private WebElement note;

	@FindBy(css = "button[type='submit']")
	private WebElement submitButton;

	@FindBy(css = ".oxd-toast--success")
	private WebElement successMessage;
	// ============================================
	// Page Actions (Methods)
	// ============================================
	public void addRecruitment() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		addButton.click();
		firstNameField.sendKeys("ameera");
		middleNameField.sendKeys("abduallah");
		lastNameField.sendKeys("almohammadi");
		//dropdown.click();
		//waitForWebElementToAppear(dropdownList.getFirst());
		//dropdownList.getLast().click();
		emailField.sendKeys("admin@gmail.com");
		applicationDate.click();
		currentDay.click();
		note.sendKeys("Im new Note");
		submitButton.click();
		
	}
	public String getSuccessMessage() {
		waitForWebElementToAppear(successMessage); // Wait for success message to appear
		return successMessage.getText(); // Return message text
	}
}
