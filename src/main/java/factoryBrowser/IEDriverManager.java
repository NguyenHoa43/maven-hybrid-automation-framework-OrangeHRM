package factoryBrowser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import static org.apache.commons.lang3.SystemUtils.IS_OS_WINDOWS;
import io.github.bonigarcia.wdm.WebDriverManager;


public class IEDriverManager implements BrowserFactory{

	@Override
	public WebDriver getBrowserDriver() {
		
		if(!IS_OS_WINDOWS) {
			throw new BrowserNotSupportedException("IE is not supported on " + System.getProperty("os.name"));
		}
		
		WebDriverManager.iedriver().arch32().setup();
		return new InternetExplorerDriver();
	}

}
