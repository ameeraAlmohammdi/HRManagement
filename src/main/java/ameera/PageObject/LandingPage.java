package ameera.PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ameera.AbstractsComponents.AbstractComponents;

public class LandingPage extends AbstractComponents {
	WebDriver driver;

	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "[class*='error'] p:nth-child(1)")
	private WebElement usernameText;

	@FindBy(css = "[class*='error'] p:nth-child(2)")
	private WebElement passwordText;

	@FindBy(name = "username")
	private WebElement username;

	@FindBy(name = "password")
	private WebElement password;

	@FindBy(css = ".oxd-button")
	private WebElement loginBtn;

	public PIMPage loginApplication() {

		String name = usernameText.getText().split(":")[1].trim();
		String pass = passwordText.getText().split(":")[1].trim();

		username.sendKeys(name);
		password.sendKeys(pass);
		loginBtn.click();
		PIMPage pimPage = new PIMPage(driver);
		return pimPage;
	}
	
	public void goTo() {
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		
	}
}
