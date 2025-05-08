package commons;

import java.io.File;

import lombok.Getter;
import lombok.Setter;

//có sử dụng thư viện lombok để set getter và getter cho class tự động thay vì viết nhiều đoạn code setter và getter cùng nhau 
@Getter
@Setter
public class GlobalConstants {
	
	private static GlobalConstants globalInstance;
	
	private GlobalConstants() {
		
	}
	
	public static synchronized GlobalConstants getGlobalConstants() {
		
		if(globalInstance == null) {
			globalInstance = new GlobalConstants();
		}
		
		return globalInstance; 
	}
	
	private final String pageUrl = "http://localhost/orangehrm5/web/index.php/auth/login";


	private final String osName = System.getProperty("os.name");
	private final String projectPath = System.getProperty("user.dir");
	private final String javaVersion = System.getProperty("java.version");

	private final String uploadFile = projectPath + File.separator + "uploadFile" + File.separator;
	private final String dowloadFile = projectPath + File.separator + "downloadFile" + File.separator;
	
	private final String browserLog = projectPath + File.separator + "browserLogs" + File.separator;
	private final String dragDropHTML5 = projectPath + File.separator + "dragDropHTML5";
	private final String autoITScript = projectPath + File.separator + "autoIT";
	private final String browserExtension = projectPath + File.separator + "browserExtensions" + File.separator;
	private final String reportingScreenshot = projectPath + File.separator + "ReportNGImages" + File.separator;
	
	private final String reportingScreenShotExtentV5 = projectPath + File.separator + "extentV5" + File.separator;
	private final String resourcePath = projectPath + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator;
	private final String dbDevUrl = "192.168.1.15:9860";
	private final String userName = "automationdhoa";
	private final String passWord = "Hoabeo@43#";
	private final long shortTimeout = 5;
	private final long longTimeout = 100;
	private final long retryestFail = 3;
	
	
	// run on loud BrowserStack.com
	private final String browserUserName = "";
	private final String browserAutomateKey = "";
	private final String browserStackUrl = "https://" + browserUserName + ":" + browserAutomateKey + "@hub-cloud.browserstack.com/wd/hub";
	
	// run on loud SauceLab
	private final String sauceUserName = "";
	private final String sauceAutomateKey = "";
	private final String sauceUrl = "https://" + sauceUserName + " : " + sauceAutomateKey + "@ondemand.eu-central-1.saucelabs.com:443/wd/hub";
	

}
