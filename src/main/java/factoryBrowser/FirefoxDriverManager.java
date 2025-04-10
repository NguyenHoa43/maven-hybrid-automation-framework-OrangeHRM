package factoryBrowser;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxDriverService;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.GeckoDriverService;

import commons.GlobalConstants;

public class FirefoxDriverManager implements BrowserFactory{

	@Override
	public WebDriver getBrowserDriver() {
		FirefoxOptions options = new FirefoxOptions();
		FirefoxDriverService firefoxservice = new GeckoDriverService.Builder().withLogFile(new File(GlobalConstants.BROWSER_LOG + "FirefoxDriver.log")).build();
		
		options.addPreference("browser.download.folderList", 2);
		options.addPreference("browser.download.dir", GlobalConstants.DOWNLOAD_FILE);
		options.addPreference("browser.download.useDownloadDir", true);
		options.addPreference("browser.helperApps.nerverAsk.saveToDisk", "multipart/x-zip, application/zip, application/x-zip-compressed, application/x-compressed, application/msword, application/csv, text/csv, image/png, image/jpeg, application/pdf, text/html, text/plain, application/excel, application/vnd.ms-excel, application/x-excel, application/x-msexcel, application/octet-stream");
		options.addPreference("pdfjs.disabled", true);
		
		
		return new FirefoxDriver(firefoxservice, options);
	}

}
	