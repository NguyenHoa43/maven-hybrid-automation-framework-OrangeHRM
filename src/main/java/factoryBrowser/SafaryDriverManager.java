package factoryBrowser;

import static org.apache.commons.lang3.SystemUtils.IS_OS_MAC;
import static org.apache.commons.lang3.SystemUtils.IS_OS_WINDOWS;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SafaryDriverManager implements BrowserFactory{

	@Override
	public WebDriver getBrowserDriver() {
		WebDriverManager.safaridriver().setup();
		if(!IS_OS_MAC) {
			throw new BrowserNotSupportedException("SAFARI is not supported on " + System.getProperty("os.name"));
		}
		
		SafariOptions options = new SafariOptions();
		
		options.setCapability("safari.cleanSession", true);
		
		return new SafariDriver(options);
	}

}
