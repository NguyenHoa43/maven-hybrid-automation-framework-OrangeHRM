package commons;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Calendar;
import java.util.Locale;
import java.util.Random;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeSuite;

import com.github.javafaker.Address;
import com.github.javafaker.Faker;

import factoryEnvironment.BrowserStackFactory;
import factoryEnvironment.EnvironmentGridList;
import factoryEnvironment.EnvironmentList;
import factoryEnvironment.GridFactory;
import factoryEnvironment.LocalFactory;
import factoryEnvironment.SaucelabFactory;



public abstract class BaseTest {

	private static ThreadLocal<WebDriver>  driver = new ThreadLocal<WebDriver>();
	protected final Log log;
	private Platform platform;
	
	@BeforeSuite
	public void initBeforeSuite() {
		deleteAllureReport();
	}
	protected BaseTest() {
		log = LogFactory.getLog(getClass());
	}	

	private String getUrlByServerName(String serverName) {
		EnvironmentList enviromentlist = EnvironmentList.valueOf(serverName.toUpperCase());
		
		switch (enviromentlist) {
		case DEV:
			serverName = "http://localhost/orangehrm5/web/index.php/auth/login";
			break;
			
		case TEST:
			serverName = "http://localhost/orangehrm5/web/index.php/auth/login";
			break;
			
			default:
				throw new IllegalArgumentException("Unexpected value: " + serverName);
		}
		return serverName;
	}

	protected WebDriver getBrowserDriver(String browserName, String envName, String serverName, String osName, String ipAddress, String portNumber, String browserVersion,String osVersion) {
		
		EnvironmentGridList environment = EnvironmentGridList.valueOf(envName.toUpperCase());
		
		switch (environment) {
		case LOCAL: 
			driver.set(new LocalFactory(browserName).createDriver());			
		break;
		case GRID:
			driver.set(new GridFactory(browserName, ipAddress, portNumber).createDriver());
		break;
		case BROWSERSTACK:
			driver.set(new BrowserStackFactory(browserName, osName, osVersion).createDriver());
		break;
		case SAUCELAP:
			driver.set(new SaucelabFactory(browserName, osName).createDriver());
		break;
		default:
			driver.set(new LocalFactory(browserName).createDriver());			
			break;
		}
		
		driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(GlobalConstants.getGlobalConstants().getLongTimeout()));
		driver.get().manage().window().maximize();
		System.out.println("Server Name: " + serverName);
		System.out.println("Server url: " + getUrlByServerName(serverName));
		driver.get().get(getUrlByServerName(serverName));
		return driver.get();
	}
	
	protected int fadeNumber() {
		Random rand = new Random();
		return rand.nextInt(99999);
	}
	
	public Address getFaker() {
		Faker faker = new Faker(new Locale("en-US"));
		return faker.address();
	}
	
	public static int getRandomNumber() {
		int uLimit = 999;
		int lLimit = 100;
		Random rand = new Random();
		return lLimit + rand.nextInt(uLimit - lLimit);
	}

	public static int getRandomNumber(int minimum, int maximum) {
		Random rand = new Random();
		return minimum + rand.nextInt(maximum - minimum);
	}

	public static String getRandomEmail() {
		return "automation" + getRandomNumberByDateTime() + "@live.com";
	}

	// Get random number by date time minute second (no duplicate)
	public static long getRandomNumberByDateTime() {
		return Calendar.getInstance().getTimeInMillis() % 100000;
	}
	
	public WebDriver getDriverInstance() {
		return this.driver.get();
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
			String pathFolderDownload = GlobalConstants.getGlobalConstants().getProjectPath() +  "/allure-json";
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

			String driverInstanceName = driver.get().toString().toLowerCase();
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

			if (osName.contains("windows")) {
				cmd = "taskkill /F /FI \"IMAGENAME eq " + browserDriverName + "*\"";
			} else {
				cmd = "pkill " + browserDriverName;
			}

			if (driver != null) {
				driver.get().manage().deleteAllCookies();
				driver.get().quit();
				
				driver.remove();
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
			String pathFolderDownload = GlobalConstants.getGlobalConstants().getProjectPath() + "/allure-json";
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
