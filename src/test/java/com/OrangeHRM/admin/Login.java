package com.OrangeHRM.admin;

import java.util.Locale;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import commons.BaseTest;
import commons.GlobalConstants;
import commons.PageGeneratorManager;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import pageObjects.OrangeHRM.admin.AdminLoginPageObject;

public class Login extends BaseTest{
	
	private WebDriver driver;
	private String emailAddress, userName, passWord, browserName, appURL, envName, serverName, osName, ipAddress, portNumber, browserVersion, osVersion;
	private AdminLoginPageObject loginPage;
	
	@Parameters({"envName","serverName","browser","ipAdress","portNumber", "osName","osVersion","browserVersion"})
	@BeforeClass
	public void beforeClass(@Optional("local") String envName, @Optional("dev") String serverName, String browserName,@Optional("localhost") String ipAddress,@Optional("4444") String portNumber,@Optional("windows") String osName,@Optional("10") String osVersion,@Optional("local") String browserVersion) {
		driver = getBrowserDriver(browserName, envName, serverName, osName, ipAddress, portNumber, browserVersion, osVersion);
		loginPage = PageGeneratorManager.getAdminLoginPage(driver);
		userName = "automationdhoa";
		passWord = "Hoabeo43#";
		emailAddress = "afc" + fadeNumber() + "@gmail.com";
		System.out.printf("Luồng: %s - id: %d \n", Thread.currentThread().getName(), Thread.currentThread().getId());
		
	}

	
		@Description("Login Page not ID user")
		@Severity(SeverityLevel.NORMAL)
		@Test
		public void TC_01_Login_Page_Not_User() {
			loginPage.inputToUserNameTextBox(driver, "    ");
			loginPage.inputToPasswordTextBox(driver, passWord);
			loginPage.clickToButtonLogin(driver);
			//verifyEquals(loginPage.getTextErrorMessage(driver), "Required");
			verifyTrue(loginPage.isErrorDisplay(driver));
		}
		@Description("Login Page not Password")
		@Severity(SeverityLevel.NORMAL)
		@Test
		public void TC_02_Login_Page_Not_Password() {
			loginPage.refreshToPage(driver);
			loginPage.inputToUserNameTextBox(driver, userName);
			loginPage.inputToPasswordTextBox(driver, "     ");
			loginPage.clickToButtonLogin(driver);
			verifyEquals(loginPage.getTextErrorMessage(driver), "Required");
		}
		@Description("Login Page wrong ID User")
		@Severity(SeverityLevel.NORMAL)
		@Test
		public void TC_03_Login_Page_Wrong_User() {
			loginPage.refreshToPage(driver);
			loginPage.inputToUserNameTextBox(driver, "abcAxs");
			loginPage.inputToPasswordTextBox(driver, passWord);
			loginPage.clickToButtonLogin(driver);
			verifyEquals(loginPage.getTextErrorMessageWrongUser(driver), "Invalid credentials");
		}
		@Description("Login Page wrong Password")
		@Severity(SeverityLevel.NORMAL)
		@Test
		public void TC_04_Login_Page_Wrong_Password() {
			loginPage.refreshToPage(driver);
			loginPage.inputToUserNameTextBox(driver, userName);
			loginPage.inputToPasswordTextBox(driver, "abjchcac12");
			loginPage.clickToButtonLogin(driver);
			verifyEquals(loginPage.getTextErrorMessageWrongPassword(driver), "Invalid credentials");
		}
		@Description("Login Page with user name and password true")
		@Severity(SeverityLevel.NORMAL)
		@Test
		public void TC_05_Login_Page_Successfull() {
			loginPage.refreshToPage(driver);
			loginPage.inputToUserNameTextBox(driver, GlobalConstants.getGlobalConstants().getUserName());
			loginPage.inputToPasswordTextBox(driver, GlobalConstants.getGlobalConstants().getPassWord());
			loginPage.clickToButtonLogin(driver);
			
		}
		
		@AfterClass(alwaysRun = true)
		public void afterClass() {
			closeBrowserDriver();
			
		}

}
