package factoryEnvironment;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import commons.GlobalConstants;
import factoryBrowser.BrowserNotSupportedException;
import factoryBrowser.ChromeDriverManager;
import factoryBrowser.EdgeDriverManager;
import factoryBrowser.FirefoxDriverManager;
import factoryBrowser.HeadlessChromeDriverManager;
import factoryBrowser.HeadlessFiefoxDriverManager;
import factoryBrowser.IEDriverManager;
import factoryBrowser.SafaryDriverManager;
import freemarker.core.ReturnInstruction.Return;
import io.github.bonigarcia.wdm.WebDriverManager;

public class LocalFactory {
	private WebDriver driver;
	private String browserName;
	
	
	public LocalFactory(String browserName) {
		this.browserName = browserName;
	}

	public WebDriver createDriver() {
		BrowserList browser = BrowserList.valueOf(browserName.toUpperCase());
		switch(browser) {
		case FIREFOX:
			driver = new FirefoxDriverManager().getBrowserDriver();
			break;
		case CHROME:
			driver = new ChromeDriverManager().getBrowserDriver();
			break;
		case EDGE:
			driver = new EdgeDriverManager().getBrowserDriver();
			break;
		case IE:
			driver = new IEDriverManager().getBrowserDriver();
			break;
		case CHROME_HEADLESS:
			driver = new HeadlessChromeDriverManager().getBrowserDriver();
			break;
		case FIREFOX_HEADLESS:
			driver = new HeadlessFiefoxDriverManager().getBrowserDriver();
			break;
		case SAFARI:
			driver = new SafaryDriverManager().getBrowserDriver();
			break;
			default:
				throw new BrowserNotSupportedException(browserName);
		}
		return driver;
	}
}
