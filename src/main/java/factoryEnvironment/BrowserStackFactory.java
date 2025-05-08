package factoryEnvironment;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import commons.GlobalConstants;

public class BrowserStackFactory {
	
	private WebDriver driver;
	private String browserName;
	private String osName;
	private String osVersion;
	
	public BrowserStackFactory(String browserName, String osName, String osVersion) {
		this.browserName = browserName;
		this.osName = osName;
		this.osVersion = osVersion;
	}

	public WebDriver createDriver() {
		DesiredCapabilities	capabilities = new DesiredCapabilities();
		capabilities.setCapability("os", osName);
		capabilities.setCapability("os_version", osVersion);
		capabilities.setCapability("browser", browserName);
		capabilities.setCapability("browser_version", "latest");
		capabilities.setCapability("browserstack.debug", "true");
		capabilities.setCapability("project", "Orange HRM");
		capabilities.setCapability("resolution", "1920x1080");
		capabilities.setCapability("os", osName);
		capabilities.setCapability("name", "Run on" + osName + " | " + osVersion + " | " + browserName);
		
		try {
			driver = new RemoteWebDriver(new URL(GlobalConstants.getGlobalConstants().getBrowserStackUrl()), capabilities);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		return driver;
	}
	
}
