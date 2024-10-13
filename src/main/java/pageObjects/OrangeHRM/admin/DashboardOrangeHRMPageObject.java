package pageObjects.OrangeHRM.admin;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUIs.OrangeHRM.admin.AdminDashboardPageUIs;
import pageUIs.OrangeHRM.admin.AdminPimPageUIs;

public class DashboardOrangeHRMPageObject extends BasePage{
	private WebDriver driver;
	
	public DashboardOrangeHRMPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public PimOrangeHRMPageObject openPimOrangeHRMPage (WebDriver driver) {
		waitForElementVisible(driver, AdminPimPageUIs.PIM_LINK);
		clickToElement(driver, AdminPimPageUIs.PIM_LINK);
		return PageGeneratorManager.getAdminPimPage(driver);
	}
}
