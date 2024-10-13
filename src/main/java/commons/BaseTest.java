package commons;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeDriverService;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxDriverService;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeSuite;



public class BaseTest {

	private WebDriver driver;
	protected final Log log;
	
	@BeforeSuite
	public void initBeforeSuite() {
		deleteAllureReport();
	}
	protected BaseTest() {
		log = LogFactory.getLog(getClass());
	}

	
	protected WebDriver getBrowserDriver(String browserName) {
		BrowserList browserList = BrowserList.valueOf(browserName.toUpperCase());
		switch (browserList) {
		case CHROME:
			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("profile.default_content_setting_values.notifications", 2);
			prefs.put("credentials_enable_service", false);
			prefs.put("profile.password_manager_enabled", false);
			prefs.put("autofill.profile_enabled", false);
			
			prefs.put("profile.default_content_settings.popups", 0);
			prefs.put("download.default_directory", GlobalConstants.DOWNLOAD_FILE);
			
			ChromeDriverService chromeService = new ChromeDriverService.Builder().withLogFile(new File(GlobalConstants.BROWSER_LOG + "chromeDriver.log")).build();
			ChromeOptions chomeOptions = new ChromeOptions();
			chomeOptions.addArguments("--disable-notifications");
			chomeOptions.addArguments("--disable-geolocation");
			chomeOptions.addArguments("--lang=vi");
			/*code run inprivate - chạy ẩn danh */
			//chomeOptions.addArguments("--incognito");
			/*code chạy browser trên profile để tránh xác nhận capcha hoặc verify human*/
			//chomeOptions.addArguments("--user-data-dir=C:/Users/Cko amo/AppData/Local/Google/Chrome/User Data/");
			//chomeOptions.addArguments("--profile-directory=Profile 11");
			chomeOptions.setExperimentalOption("prefs", prefs);
			chomeOptions.setExperimentalOption("useAutomationExtension", false);
			chomeOptions.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
			driver = new ChromeDriver(chromeService, chomeOptions);
			break;
		case FIREFOX:
			FirefoxOptions firefoxOption = new FirefoxOptions();
			firefoxOption.addPreference("browser.download.folderList", 2);
			firefoxOption.addPreference("browser.download.dir", GlobalConstants.DOWNLOAD_FILE);
			firefoxOption.addPreference("browser.download.useDownloadDir", true);
			firefoxOption.addPreference("browser.helperApps.neverAsk.saveToDisk","multipart/x-zip, application/zip, application/x-zip-compressed, " + "application/x-compressed,application/msword,application/csv," + "text/csv,image/pnj, image/jpeg, application/pdf, text/html, " + "text/plain, application/excel, application/vnd.ms-excel, " + "application/x-excel, application/x-msexcel, application/octet-stream");
			firefoxOption.addPreference("pdfjs.disabled", true);
			/*run inprivate - chạy ẩn danh */
			//firefoxOption.addArguments("-private");
			
			FirefoxDriverService firefoxservice = new GeckoDriverService.Builder().withLogFile(new File(GlobalConstants.BROWSER_LOG + "FirefoxDriver.log")).build();
			driver = new FirefoxDriver(firefoxservice, firefoxOption);
			break;
		case EDGE:
			Map<String, Object> prefss = new HashMap<String, Object>();
			prefss.put("profile.default_content_setting_values.notifications", 2);
			prefss.put("credentials_enable_service", false);
			prefss.put("profile.password_manager_enabled", false);
			prefss.put("autofill.profile_enabled", false);
			
			
			
			EdgeDriverService edgeservice = new EdgeDriverService.Builder().withLogFile(new File(GlobalConstants.BROWSER_LOG + "edgeDriver.log")).build();
			EdgeOptions edgeOption = new EdgeOptions();
			edgeOption.addArguments("--disable-notifications");
			edgeOption.addArguments("--disable-geolocation");
			edgeOption.addArguments("--lang=vi");
			/*run inprivate - chạy ẩn danh */
			//edgeOption.addArguments("--inprivate");
			edgeOption.setExperimentalOption("useAutomationExtension", false);
			edgeOption.setExperimentalOption("prefs", prefss);
			edgeOption.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
			driver = new EdgeDriver(edgeservice, edgeOption);
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
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(GlobalConstants.LONG_TIME_OUT));
		driver.get(GlobalConstants.PAGE_URL);
		return driver;
	}

	protected WebDriver getBrowserDriver(String browserName, String appURL) {
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
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(GlobalConstants.LONG_TIME_OUT));
		driver.get(appURL);
		return driver;
	}

	protected int fadeNumber() {
		Random rand = new Random();
		return rand.nextInt(99999);
	}
	
	public WebDriver getDriverInstance() {
		return this.driver;
	}
	
	protected boolean verifyTrue(boolean condition) {
		boolean status = true;
		try {
			Assert.assertTrue(condition);
			log.info("---------------------- Passed -----------------------");
		} catch (Throwable e) {
			status = false;
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
			log.info("---------------------- Failed -----------------------");
		}
		return status;
	}

	protected boolean verifyFalse(boolean condition) {
		boolean status = true;
		try {
			Assert.assertFalse(condition);
			log.info("---------------------- Passed -----------------------");
		} catch (Throwable e) {
			status = false;
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
			log.info("---------------------- Failed -----------------------");
		}
		return status;
	}

	protected boolean verifyEquals(Object actual, Object expected) {
		boolean status = true;
		try {
			Assert.assertEquals(actual, expected);
			log.info("---------------------- Passed -----------------------");
		} catch (Throwable e) {
			status = false;
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
			log.info("---------------------- Failed -----------------------");
		}
		return status;
	}
	public void deleteAllureReport() {
		try {
			String pathFolderDownload = GlobalConstants.PROJECT_PATH +  "/allure-json";
			File file = new File(pathFolderDownload);
			File[] listOfFiles = file.listFiles();
			if (listOfFiles.length != 0) {
				for (int i = 0; i < listOfFiles.length; i++) {
					if (listOfFiles[i].isFile() && !listOfFiles[i].getName().equals("environment.properties")) {
						new File(listOfFiles[i].toString()).delete();
					}
				}
			}
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
    }
	
	protected void closeBrowserDriver() {
		String cmd = null;
		try {
			String osName = System.getProperty("os.name").toLowerCase();
			log.info("OS name = " + osName);

			String driverInstanceName = driver.toString().toLowerCase();
			log.info("Driver instance name = " + driverInstanceName);

			String browserDriverName = null;

			if (driverInstanceName.contains("chrome")) {
				browserDriverName = "chromedriver";
			} else if (driverInstanceName.contains("internetexplorer")) {
				browserDriverName = "IEDriverServer";
			} else if (driverInstanceName.contains("firefox")) {
				browserDriverName = "geckodriver";
			} else if (driverInstanceName.contains("edge")) {
				browserDriverName = "msedgedriver";
			} else if (driverInstanceName.contains("opera")) {
				browserDriverName = "operadriver";
			} else {
				browserDriverName = "safaridriver";
			}

			if (osName.contains("window")) {
				cmd = "taskkill /F /FI \"IMAGENAME eq " + browserDriverName + "*\"";
			} else {
				cmd = "pkill " + browserDriverName;
			}

			if (driver != null) {
				driver.manage().deleteAllCookies();
				driver.quit();
			}
		} catch (Exception e) {
			log.info(e.getMessage());
		} finally {
			try {
				Process process = Runtime.getRuntime().exec(cmd);
				process.waitFor();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void deleteAllureReport(String folderName) {
		try {
			String pathFolderDownload = GlobalConstants.PROJECT_PATH + "/allure-json";
			File file = new File(pathFolderDownload);
			File[] listOfFiles = file.listFiles();
			if (listOfFiles.length != 0) {
				for (int i = 0; i < listOfFiles.length; i++) {
					if (listOfFiles[i].isFile() && !listOfFiles[i].getName().equals("environment.properties")) {
						new File(listOfFiles[i].toString()).delete();
					}
				}
			}
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}

	}
	
}
