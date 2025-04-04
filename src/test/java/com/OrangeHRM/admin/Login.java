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
	private String emailAddress, userName, passWord;
	private AdminLoginPageObject loginPage;
	
	@Parameters({"browser","server"})
	@BeforeClass
	public void beforeClass(String browserName, String serverName) {
		driver = getBrowser(browserName, serverName);
		loginPage = PageGeneratorManager.getAdminLoginPage(driver);
		userName = "automationdhoa";
		passWord = "Hoabeo43#";
		emailAddress = "afc" + fadeNumber() + "@gmail.com";
		
	}

	
		@Description("Create New Employee")
		@Severity(SeverityLevel.NORMAL)
		@Test
		public void TC_01_Login_Page_Not_User() {
			loginPage.inputToUserNameTextBox(driver, "    ");
			loginPage.inputToPasswordTextBox(driver, passWord);
			loginPage.clickToButtonLogin(driver);
			//verifyEquals(loginPage.getTextErrorMessage(driver), "Required");
			verifyTrue(loginPage.isErrorDisplay(driver));
		}
		@Description("Create New Employee")
		@Severity(SeverityLevel.NORMAL)
		@Test
		public void TC_02_Login_Page_Not_Password() {
			loginPage.refreshToPage(driver);
			loginPage.inputToUserNameTextBox(driver, userName);
			loginPage.inputToPasswordTextBox(driver, "     ");
			loginPage.clickToButtonLogin(driver);
			verifyEquals(loginPage.getTextErrorMessage(driver), "Required");
		}
		@Description("Create New Employee")
		@Severity(SeverityLevel.NORMAL)
		@Test
		public void TC_03_Login_Page_Wrong_User() {
			loginPage.refreshToPage(driver);
			loginPage.inputToUserNameTextBox(driver, "abcAxs");
			loginPage.inputToPasswordTextBox(driver, passWord);
			loginPage.clickToButtonLogin(driver);
			verifyEquals(loginPage.getTextErrorMessageWrongUser(driver), "Invalid credentials");
		}
		@Description("Create New Employee")
		@Severity(SeverityLevel.NORMAL)
		@Test
		public void TC_04_Login_Page_Wrong_Password() {
			loginPage.refreshToPage(driver);
			loginPage.inputToUserNameTextBox(driver, userName);
			loginPage.inputToPasswordTextBox(driver, "abjchcac12");
			loginPage.clickToButtonLogin(driver);
			verifyEquals(loginPage.getTextErrorMessageWrongPassword(driver), "Invalid credentials");
		}
		
		@Test
		public void TC_05_Login_Page_Successfull() {
			loginPage.refreshToPage(driver);
			loginPage.inputToUserNameTextBox(driver, GlobalConstants.USER_NAME);
			loginPage.inputToPasswordTextBox(driver, GlobalConstants.PASS_WORD);
			loginPage.clickToButtonLogin(driver);
			
		}
		
		@AfterClass(alwaysRun = true)
		public void afterClass() {
			closeBrowserDriver();
			
		}

}
