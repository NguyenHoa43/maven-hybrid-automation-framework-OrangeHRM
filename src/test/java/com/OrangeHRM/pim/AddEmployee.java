package com.OrangeHRM.pim;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
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
	

	@Parameters({"envName","serverName","browser","ipAdress","portNumber", "osName","osVersion","browserVersion"})
	@BeforeClass
	public void beforeClass(@Optional("local") String envName, @Optional("dev") String serverName,@Optional("firefox") String browserName,@Optional("localhost") String ipAddress,@Optional("4444") String portNumber,@Optional("windows") String osName,@Optional("10") String osVersion,@Optional("local") String browserVersion) {
		driver = getBrowserDriver(browserName, envName, serverName, osName, ipAddress, portNumber, browserVersion, osVersion);
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
		System.out.printf("Luồng: %s - id: %d \n", Thread.currentThread().getName(), Thread.currentThread().getId());
		
		loginPage.inputToUserNameTextBox(driver, GlobalConstants.getGlobalConstants().getUserName());
		loginPage.inputToPasswordTextBox(driver, GlobalConstants.getGlobalConstants().getPassWord());
		loginPage.clickToButtonLogin(driver);
		pimPage = dashBoardPage.openPimOrangeHRMPage(driver);
		pimPage.sleep(4);
		
		
	}
	// setup report allure
	@Description("Create New Employee in Page Pim")
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
