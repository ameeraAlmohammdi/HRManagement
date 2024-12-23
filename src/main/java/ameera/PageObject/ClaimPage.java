package ameera.PageObject;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ameera.AbstractsComponents.AbstractComponents;

public class ClaimPage extends AbstractComponents {

	public WebDriver driver;

	public ClaimPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// ============================================
	// Web Elements (Page Object Model Elements)
	// ============================================

	private By bySuccessMessage = By.cssSelector(".oxd-toast.oxd-toast--success.oxd-toast-container--toast");
	private By empList = By.cssSelector(".oxd-autocomplete-dropdown");

	@FindBy(css = ".oxd-toast--success")
	private WebElement successMessage;

	@FindBy(css = "button[class='oxd-button oxd-button--medium oxd-button--secondary']")
	private WebElement assignClaimButton;

	@FindBy(css = "input[placeholder='Type for hints...']")
	private WebElement employeeName;

	@FindBy(css = ".oxd-autocomplete-dropdown")
	private List<WebElement> employeesList;

	@FindBy(css = "div[role = 'listbox']")
	private WebElement employeeList;

	@FindBy(xpath = "//form[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]")
	private WebElement event;

	@FindBy(css = ".oxd-select-dropdown")
	private List<WebElement> eventList;

	@FindBy(xpath = "//form[1]/div[2]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]")
	private WebElement currency;

	@FindBy(css = ".oxd-select-dropdown")
	private List<WebElement> currencyList;

	@FindBy(css = "[class*='oxd-textarea']")
	private WebElement remark;

	@FindBy(css = "button[type='submit']")
	private WebElement createButton;

	// ============================================
	// Page Actions (Methods)
	// ============================================

	public void addClaim() throws InterruptedException {

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
		assignClaimButton.click();

		employeeName.click();
		employeeName.sendKeys("test");

		Actions act = new Actions(driver);
		Thread.sleep(5000L);
		act.sendKeys(Keys.DOWN).perform();
		act.sendKeys(Keys.ENTER).perform();

		// Click on the Event element
		event.click();
		eventList.stream().filter(option -> option.getText().contains("Medical")).findFirst().ifPresent(option -> {
			option.click();
		});

		// Click on the Currency element
		currency.click();
		currencyList.stream().filter(option -> option.getText().contains("Saudi")).findFirst().ifPresent(option -> {
			option.click();
		});

		remark.sendKeys("This is my remarks");
		createButton.click();
	}

	public String getSuccessMessage() {
		waitForElementToAppear(bySuccessMessage); // Wait for success message to appear
		return successMessage.getText(); // Return message text
	}

}
