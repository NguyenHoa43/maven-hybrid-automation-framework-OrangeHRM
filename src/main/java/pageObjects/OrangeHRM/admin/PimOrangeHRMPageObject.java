package pageObjects.OrangeHRM.admin;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.BasePage;
import commons.PageGeneratorManager;
import io.qameta.allure.Step;
import pageUIs.OrangeHRM.admin.AdminDashboardPageUIs;
import pageUIs.OrangeHRM.admin.AdminPimPageUIs;

public class PimOrangeHRMPageObject extends BasePage {
	private WebDriver driver;
	
	public PimOrangeHRMPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public DashboardOrangeHRMPageObject openDashboardOrangeHRMPage(WebDriver driver) {
		waitForElementVisible(driver, AdminDashboardPageUIs.DASHBOARD_LINK);
		clickToElement(driver, AdminDashboardPageUIs.DASHBOARD_LINK);
		return PageGeneratorManager.getAdminDashboardPage(driver);
	}
	@Step("Navigate to add employee page")
	public void clickToButtonAdd() {
		waitForElementClickable(driver, AdminPimPageUIs.BUTTON_ADD);
		clickToElement(driver, AdminPimPageUIs.BUTTON_ADD);
	}
	@Step("insert first name")
	public void sendKeyToFirstNameTextBox(String valueText) {
		waitForElementVisible(driver, AdminPimPageUIs.INPUT_TO_FIRST_NAME_TEXT_BOX);
		sendkeysToElement(driver,AdminPimPageUIs.INPUT_TO_FIRST_NAME_TEXT_BOX, valueText);
	}
	@Step("insert last name textbox with value is {0}")
	public void sendKeyToLastNameTextBox(String valueText) {
		waitForElementVisible(driver, AdminPimPageUIs.INPUT_TO_LAST_NAME_TEXT_BOX);
		sendkeysToElement(driver,AdminPimPageUIs.INPUT_TO_LAST_NAME_TEXT_BOX, valueText);
	}
	@Step("navigate to details information")
	public void clickToButtonLoginDetails() {
		waitForElementClickable(driver, AdminPimPageUIs.BUTTON_LOGIN_DETAILS);
		clickToElementByJS(driver, AdminPimPageUIs.BUTTON_LOGIN_DETAILS);
	}
	@Step("Navigate save infomation affter create a new employee")
	public void clickToButtonSave() {
		waitForElementClickable(driver, AdminPimPageUIs.BUTTON_SAVE);
		clickToElement(driver, AdminPimPageUIs.BUTTON_SAVE);
	}
	@Step("insert User name to create employee with value is {0} ")
	public void sendkeyToUserNameTextBox(String valueText) {
		waitForElementVisible(driver, AdminPimPageUIs.INPUT_USER_NAME_TEXT_BOX);
		sendkeysToElement(driver, AdminPimPageUIs.INPUT_USER_NAME_TEXT_BOX, valueText);
	}
	@Step("insert passwords to create employee with value is {0}")
	public void sendkeyToPassWordTextbox(String valueText) {
		waitForElementVisible(driver, AdminPimPageUIs.INPUT_PASSWORD_TEXT_BOX);
		sendkeysToElement(driver, AdminPimPageUIs.INPUT_PASSWORD_TEXT_BOX, valueText);
	}
	@Step("insert confirm passwords to create employee with value is {0}")
	public void sendkeyToConfirmPassWordTextBox(String valueText) {
		waitForElementVisible(driver, AdminPimPageUIs.INPUT_CONFIRM_PASSWORD_TEXT_BOX);
		sendkeysToElement(driver, AdminPimPageUIs.INPUT_CONFIRM_PASSWORD_TEXT_BOX, valueText);
	}
	
	

}
