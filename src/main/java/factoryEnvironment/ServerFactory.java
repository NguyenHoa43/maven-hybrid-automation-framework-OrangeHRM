package factoryEnvironment;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class ServerFactory {
	
	private WebDriver driver;
	private String browserName;
	private String serverName;
	
	
	public ServerFactory(String browserName, String serverName) {
		this.browserName = browserName;
		this.serverName = serverName;
	}

	public WebDriver createDriver() {
		BrowserList browserList = BrowserList.valueOf(browserName.toUpperCase());
		
		switch (browserList) {
		case CHROME:			
			driver = new ChromeDriver();
			break;
		case FIREFOX:			
			driver = new FirefoxDriver();
			break;
		case EDGE:
			driver = new EdgeDriver();
			break;
		case CHROME_HEADLESS:
			ChromeOptions chOption = new ChromeOptions();
			chOption.addArguments("--headless");
			chOption.addArguments("window-size=1920x1080");
			driver = new ChromeDriver(chOption);
			break;
		case EDGE_HEADLESS:
			EdgeOptions egOption = new EdgeOptions();
			egOption.addArguments("--headless");
			egOption.addArguments("window-size=1920x1080");
			driver = new EdgeDriver(egOption);
			break;
		case FIREFOX_HEADLESS:
			FirefoxOptions ffOptions = new FirefoxOptions();
			ffOptions.addArguments("--headless");
			ffOptions.addArguments("window-size=1920x1080");
			driver = new FirefoxDriver(ffOptions);
			break;
		default:
			throw new RuntimeException("Browser name is not valid");
		}
		
		return driver;
	}
}
