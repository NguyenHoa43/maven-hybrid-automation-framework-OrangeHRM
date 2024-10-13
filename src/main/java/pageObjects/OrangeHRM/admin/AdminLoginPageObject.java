package pageObjects.OrangeHRM.admin;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import io.qameta.allure.Step;
import pageUIs.OrangeHRM.admin.AdminOrangeHRMPageUIs;

public class AdminLoginPageObject extends BasePage{
	private WebDriver driver;
	
	public AdminLoginPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	@Step("insert user name with value is {0}")
	public void inputToUserNameTextBox(WebDriver driver, String value) {
		waitForElementVisible(driver, AdminOrangeHRMPageUIs.INPUT_USERNAME);
		sendkeysToElement(driver, AdminOrangeHRMPageUIs.INPUT_USERNAME, value);
	}
	@Step("insert password with value is {0}")
	public void inputToPasswordTextBox(WebDriver driver, String value) {
		waitForElementVisible(driver, AdminOrangeHRMPageUIs.INPUT_PASSWORD);
		sendkeysToElement(driver, AdminOrangeHRMPageUIs.INPUT_PASSWORD, value);
	}
	@Step("Navigate to page login ")
	public void clickToButtonLogin(WebDriver driver) {
		waitForElementClickable(driver, AdminOrangeHRMPageUIs.LOGIN_LINK);
		clickToElement(driver, AdminOrangeHRMPageUIs.LOGIN_LINK);
	}
	@Step("get Text error messgage")
	public String getTextErrorMessage(WebDriver driver) {
		waitForElementVisible(driver, AdminOrangeHRMPageUIs.ERROR_MASSAGE);
		return getElementText(driver, AdminOrangeHRMPageUIs.ERROR_MASSAGE);
		
	}
	@Step("get Text error message wrong usser ")
	public String getTextErrorMessageWrongUser(WebDriver driver) {
		waitForElementVisible(driver, AdminOrangeHRMPageUIs.ERROR_MASSAGE_WRONG_USER);
		return getElementText(driver, AdminOrangeHRMPageUIs.ERROR_MASSAGE_WRONG_USER);
		
	}
	
	@Step("get text error")
	public String getTextErrorMessageWrongPassword(WebDriver driver) {
		waitForElementVisible(driver, AdminOrangeHRMPageUIs.ERROR_MASSAGE_WRONG_USER);
		return getElementText(driver, AdminOrangeHRMPageUIs.ERROR_MASSAGE_WRONG_USER);
		
	}
	@Step("error display")
	public boolean isErrorDisplay(WebDriver driver) {
		waitForElementVisible(driver, AdminOrangeHRMPageUIs.ERROR_MASSAGE);
		return isElementDisplay(driver, AdminOrangeHRMPageUIs.ERROR_MASSAGE);
	}
}
