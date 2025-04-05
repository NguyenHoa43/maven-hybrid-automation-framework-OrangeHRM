package factoryEnvironment;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import commons.GlobalConstants;
import freemarker.core.ReturnInstruction.Return;

public class LocalFactory {
	private WebDriver driver;
	private String browserName;
	
	
	public LocalFactory(String browserName) {
		this.browserName = browserName;
	}

	public WebDriver createDriver() {
		
		if (browserName.equals("firefox")) {
			
			driver = new FirefoxDriver();
		} else if (browserName.equals("headlessfirefox")) {
			
			FirefoxOptions options = new FirefoxOptions();
			options.addArguments("--headless");
			options.addArguments("window-size=1920x1080");
			driver = new FirefoxDriver(options);
		} else if (browserName.equals("coccoc")) {
			
			ChromeOptions options = new ChromeOptions();
			if (GlobalConstants.OS_NAME.startsWith("Windows")) {
				options.setBinary("C:\\Program Files\\CocCoc\\Browser\\Application\\browser.exe");
			} else {
				options.setBinary(".....");
			}
			driver = new ChromeDriver(options);
		} else if (browserName.equals("chrome")) {
			
			driver = new ChromeDriver();
		} else if (browserName.equals("headlesschrome")) {
			
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--headless");
			options.addArguments("window-size=1920x1080");
			driver = new ChromeDriver(options);
		
		} else if (browserName.equals("edge")) {
			
			driver = new EdgeDriver();
		} else {
			throw new RuntimeException("Browser name invalid !");
		}
		return driver;
	}
	
}
