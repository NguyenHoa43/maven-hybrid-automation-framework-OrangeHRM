package commons;

import java.io.File;

public class GlobalConstants {
	
	public static final String PAGE_URL = "http://localhost/orangehrm5/web/index.php/auth/login";


	public static final String OS_NAME = System.getProperty("os.name");
	public static final String PROJECT_PATH = System.getProperty("user.dir");
	public static final String JAVA_VERSION = System.getProperty("java.version");

	public static final String UPLOAD_FLIE = PROJECT_PATH + File.separator + "uploadFile" + File.separator;
	public static final String DOWNLOAD_FILE = PROJECT_PATH + File.separator + "downloadFile" + File.separator;
	
	public static final String BROWSER_LOG = PROJECT_PATH + File.separator + "browserLogs" + File.separator;
	public static final String DRAG_DROP_HTML5 = PROJECT_PATH + File.separator + "dragDropHTML5";
	public static final String AUTO_IT_SCRIPT = PROJECT_PATH + File.separator + "autoIT";
	public static final String BROWSER_EXTENSION = PROJECT_PATH + File.separator + "browserExtensions" + File.separator;
	public static final String REPORTNG_SCREENSHOT = PROJECT_PATH + File.separator + "ReportNGImages" + File.separator;
	
	public static final String REPORTNG_SCREENSHOT_EXTENTV5 = PROJECT_PATH + File.separator + "extentV5" + File.separator;
	public static final String RESOURCE_PATH = PROJECT_PATH + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator;
	public static final String DB_DEV_URL = "192.168.1.15:9860";
	public static final String USER_NAME = "automationdhoa";
	public static final String PASS_WORD = "Hoabeo@43#";
	public static final long SHORT_TIMEOUT = 5;
	public static final long LONG_TIME_OUT = 100;
	public static final long RETRY_TEST_FAIL = 3;
	
	
	// run on loud BrowserStack.com
	public static final String BROWSER_USERNAME = "";
	public static final String BROWSER_AUTOMATE_KEY = "";
	public static final String BROWSER_STACK_URL = "https://" + BROWSER_USERNAME + ":" + BROWSER_AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";
	
	// run on loud SauceLab
	public static final String SAUCE_USERNAME = "";
	public static final String SAUCE_AUTOMATE_KEY = "";
	public static final String SAUCE_URL = "https://" + SAUCE_USERNAME + " : " + SAUCE_AUTOMATE_KEY + "@ondemand.eu-central-1.saucelabs.com:443/wd/hub";
	

}
