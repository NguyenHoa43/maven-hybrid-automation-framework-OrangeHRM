package factoryBrowser;

import static org.apache.commons.lang3.SystemUtils.IS_OS_MAC;
import static org.apache.commons.lang3.SystemUtils.IS_OS_WINDOWS;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;


import commons.GlobalConstants;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ChromeDriverManager implements BrowserFactory{

	@Override
	public WebDriver getBrowserDriver() {
		//WebDriverManager.chromedriver().setup();
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("profile.default_content_setting_values.notifications", 2);
		prefs.put("credentials_enable_service", false);
		prefs.put("profile.password_manager_enabled", false);
		prefs.put("autofill.profile_enabled", false);
		if(!IS_OS_WINDOWS) {
			throw new BrowserNotSupportedException("Chrome is not supported on " + System.getProperty("os.name"));
		}
	
		ChromeOptions options = new ChromeOptions();
		ChromeDriverService chromeservice = new ChromeDriverService.Builder().withLogFile(new File(GlobalConstants.BROWSER_LOG + "ChromeDriver.log")).build();
		
		options.addArguments("--disable-notifications");
		options.addArguments("--disable-geolocation");
		// thay đổi ngôn ngữ browser sang tiếng việt
//		options.addArguments("--lang=vi");
		// không cho sử dụng các extension
		options.setExperimentalOption("useAutomationExtension", false);
//		// chạy trên 1 mục được chỉ định trên trình duyệt
//		//* options.addArguments("--user-data-dir=C:/Users/Cko amo/AppData/Local/Google/Chrome/User Data/");
//		//* options.addArguments("--profile-directory=Profile 11");
		options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
//		// chạy trên trình duyệt ẩn danh - khi cần chạy sẽ mở còn không thì không sử dụng
//		// options.addArguments("--incognito");

		options.setExperimentalOption("prefs", prefs);
		System.setProperty("webdriver.chrome.args", "--disable-logging");
//		System.setProperty("webdriver.chrome.silentOutput", "true");

		return new ChromeDriver(chromeservice, options);
	}

}
