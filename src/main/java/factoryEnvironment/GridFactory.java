package factoryEnvironment;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariOptions;

import factoryEnvironment.BrowserList;

public class GridFactory {
	
	private WebDriver driver;
	private String browserName;
	private String ipAddress;
	private String portNumber;
	private String osName;
	public Platform platform;
	
	public GridFactory(String browserName, String ipAddress, String portNumber) {
		this.browserName = browserName;
		this.ipAddress = ipAddress;
		this.portNumber = portNumber;
	}
	
	public WebDriver createDriver() {
		BrowserList browserList = BrowserList.valueOf(browserName.toUpperCase());
		Capabilities capability = null;

		if (osName.toLowerCase().contains("windows")) {
			platform = Platform.WINDOWS;
		} else {
			platform = Platform.ANY;
		}

		switch (browserList) {
		case FIREFOX:
			FirefoxOptions fOptions = new FirefoxOptions();
			fOptions.setCapability(CapabilityType.PLATFORM_NAME, platform);
			capability = fOptions;
			break;
		case CHROME:
			ChromeOptions cOptions = new ChromeOptions();
			cOptions.setCapability(CapabilityType.PLATFORM_NAME, platform);
			capability = cOptions;
			break;
		case EDGE:
			EdgeOptions eOptions = new EdgeOptions();
			eOptions.setCapability(CapabilityType.PLATFORM_NAME, platform);
			capability = eOptions;
			break;
		case SAFARI:
			SafariOptions sOptions = new SafariOptions();
			sOptions.setCapability(CapabilityType.PLATFORM_NAME, platform);
			capability = sOptions;
			break;
		default:
			throw new RuntimeException("Browser is not valid!");
		}

		try {
			driver = new RemoteWebDriver(new URL(String.format("http://%s:%s/", ipAddress, portNumber)), capability);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return driver;
	}
}
