package commons;

import org.openqa.selenium.WebDriver;

import pageObjects.OrangeHRM.admin.AdminLoginPageObject;
import pageObjects.OrangeHRM.admin.DashboardOrangeHRMPageObject;
import pageObjects.OrangeHRM.admin.PimOrangeHRMPageObject;



public class PageGeneratorManager {
	
	public static AdminLoginPageObject getAdminLoginPage(WebDriver driver) {
		return new AdminLoginPageObject(driver);
	}
	
	public static DashboardOrangeHRMPageObject getAdminDashboardPage(WebDriver driver) {
		return new DashboardOrangeHRMPageObject(driver);
	}

	public static PimOrangeHRMPageObject getAdminPimPage(WebDriver driver) {
		return new PimOrangeHRMPageObject(driver);
	}
}
