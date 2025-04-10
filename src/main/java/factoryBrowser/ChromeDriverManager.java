package factoryBrowser;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;


import commons.GlobalConstants;

public class ChromeDriverManager implements BrowserFactory{

	@Override
	public WebDriver getBrowserDriver() {
		
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("profile.default_content_setting_values.notifications", 2);
		prefs.put("credentials_enable_service", false);
		prefs.put("profile.password_manager_enabled", false);
		prefs.put("autofill.profile_enabled", false);
		
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", prefs);
		ChromeDriverService chromeDriverService = new ChromeDriverService.Builder().withLogFile(new File(GlobalConstants.BROWSER_LOG + "chromeDriver.log")).build();
		
		options.addArguments("--disable-notifications");
		options.addArguments("--disable-geolocation");
		options.addArguments("--lang=vi");	
		options.setExperimentalOption("useAutomationExtension", false);
		options.addArguments("--user-data-dir=C:/Users/Cko amo/AppData/Local/Google/Chrome/User Data/");
		options.addArguments("--profile-directory=Profile 11");
		options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
		
		System.setProperty("webdriver.chrome.args", "--disable-logging");
		System.setProperty("webdriver.chrome.silentOutput", "true");
		return new ChromeDriver(chromeDriverService, options);
	}

}
