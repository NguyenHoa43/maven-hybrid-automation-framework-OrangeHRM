package factoryBrowser;

import static org.apache.commons.lang3.SystemUtils.IS_OS_WINDOWS;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeDriverService;
import org.openqa.selenium.edge.EdgeOptions;
import static org.apache.commons.lang3.SystemUtils.IS_OS_WINDOWS;
import static org.apache.commons.lang3.SystemUtils.IS_OS_MAC;
import commons.GlobalConstants;
import io.github.bonigarcia.wdm.WebDriverManager;

public class EdgeDriverManager implements BrowserFactory{

	@Override
	public WebDriver getBrowserDriver() {
		//WebDriverManager.edgedriver().setup();
		
		if(!IS_OS_WINDOWS) {
			throw new BrowserNotSupportedException("EDGE is not supported on " + System.getProperty("os.name"));
		}
		Map<String, Object> prefss = new HashMap<String, Object>();
		prefss.put("profile.default_content_setting_values.notifications", 2);
		prefss.put("credentials_enable_service", false);
		prefss.put("profile.password_manager_enabled", false);
		prefss.put("autofill.profile_enabled", false);		
		
		EdgeDriverService edgeservice = new EdgeDriverService.Builder().withLogFile(new File(GlobalConstants.getGlobalConstants().getBrowserLog() + "edgeDriver.log")).build();
		EdgeOptions edgeOption = new EdgeOptions();
		edgeOption.addArguments("--disable-notifications");
		edgeOption.addArguments("--disable-geolocation");
		edgeOption.addArguments("--lang=vi");
		edgeOption.setExperimentalOption("useAutomationExtension", false);
		edgeOption.setExperimentalOption("prefs", prefss);
		edgeOption.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
		
		return new EdgeDriver(edgeservice, edgeOption);
	}

}
