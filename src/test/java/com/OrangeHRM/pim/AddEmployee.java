package com.OrangeHRM.pim;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.GlobalConstants;
import commons.PageGeneratorManager;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import pageObjects.OrangeHRM.admin.AdminLoginPageObject;
import pageObjects.OrangeHRM.admin.DashboardOrangeHRMPageObject;
import pageObjects.OrangeHRM.admin.PimOrangeHRMPageObject;

public class AddEmployee extends BaseTest{
	
	private WebDriver driver;
	private String emailAddress, firstName, middleName, lastName,userName, passWord, confirmPassWord;
	private PimOrangeHRMPageObject pimPage;
	private AdminLoginPageObject loginPage;
	private DashboardOrangeHRMPageObject dashBoardPage;
	

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		loginPage = PageGeneratorManager.getAdminLoginPage(driver);
		pimPage = PageGeneratorManager.getAdminPimPage(driver);
		dashBoardPage = PageGeneratorManager.getAdminDashboardPage(driver);
		
		firstName = "Nguyen";
		middleName = "Duc";
		lastName = "Tuan";
		
		userName = "longtime" + fadeNumber();
		passWord = "HappyDay@12";
		confirmPassWord = "HappyDay@12";
		emailAddress = "afc" + fadeNumber() + "@gmail.com";
		
		
		loginPage.inputToUserNameTextBox(driver, GlobalConstants.USER_NAME);
		loginPage.inputToPasswordTextBox(driver, GlobalConstants.PASS_WORD);
		loginPage.clickToButtonLogin(driver);
		pimPage = dashBoardPage.openPimOrangeHRMPage(driver);
		pimPage.sleep(4);
		
		
	}
	// setup report allure
	@Description("Create New Employee")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void TC_01_Creat_New_Employee() {
		pimPage.clickToButtonAdd();
		pimPage.sleep(3);
		pimPage.sendKeyToFirstNameTextBox(firstName);
		pimPage.sendKeyToLastNameTextBox(lastName);
		pimPage.clickToButtonLoginDetails();
		pimPage.sendkeyToUserNameTextBox(userName);
		pimPage.sendkeyToPassWordTextbox(passWord);
		pimPage.sendkeyToConfirmPassWordTextBox(confirmPassWord);
		pimPage.sleep(3);
		pimPage.clickToButtonSave();
		
	}
	
	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserDriver();
		
	}

}
