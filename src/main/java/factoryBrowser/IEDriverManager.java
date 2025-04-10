package factoryBrowser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;


public class IEDriverManager implements BrowserFactory{

	@Override
	public WebDriver getBrowserDriver() {
		// TODO Auto-generated method stub
		return new InternetExplorerDriver();
	}

}
