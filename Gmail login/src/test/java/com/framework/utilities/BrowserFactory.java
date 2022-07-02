package com.framework.utilities;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Reporter;
import java.util.logging.*;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserFactory {
	 		
	@SuppressWarnings("deprecation")
	public static WebDriver startBrowser(WebDriver driver,String browser,String url) {

		if (browser.equalsIgnoreCase("FireFox")) {
			WebDriverManager.firefoxdriver().setup();
			FirefoxProfile p = new FirefoxProfile();
			// To download inside project folder
			p.setPreference("browser.download.panel.shown", false);
			p.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet,application/vnd.ms-excel,application/zip,application/pdf,application/msword,application/vnd.openxmlformats-officedocument.wordprocessingml.document");
			p.setPreference("browser.download.folderList", 2);
			p.setPreference("browser.download.dir", System.getProperty("user.dir"));
			p.setAcceptUntrustedCertificates(true);
			p.setAssumeUntrustedCertificateIssuer(false);
			driver = new FirefoxDriver();

		} else if (browser.equalsIgnoreCase("Chrome")) {
			try {
				Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe");
			} catch (Exception e) {
				
			}
			disableSeleniumLogs();
			WebDriverManager.chromedriver().setup();
			HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
			String download = System.getProperty("user.home") + "\\Downloads";
			chromePrefs.put("profile.default_content_settings.popups", 0);
			chromePrefs.put("download.default_directory", download);
			chromePrefs.put("profile.content_settings.exceptions.automatic_downloads.*.setting", 1);
			String DRIVER_PATH ="./Drivers/chromedriver.exe";
			System.setProperty("Dwebdriver.chrome.driver", DRIVER_PATH);
            ChromeOptions options = new ChromeOptions();
            options.setExperimentalOption("useAutomationExtension", false);
			options.setExperimentalOption("excludeSwitches",Collections.singletonList("enable-automation"));
			options.addArguments("--incognito");
			options.addArguments("disable-popup-blocking");
			options.addArguments("--disable-extensions");
			options.setExperimentalOption("prefs", chromePrefs);
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability("chrome.switches", Arrays.asList("--ignore-certificate-errors"));	
			capabilities.setCapability(CapabilityType.HAS_NATIVE_EVENTS, true);
			capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);	 
			capabilities.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, true);	
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			capabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
		    ChromeDriverService service = new ChromeDriverService.Builder().usingDriverExecutable(new File(DRIVER_PATH)).usingAnyFreePort().build();
		    options.merge(capabilities);
		    driver = new ChromeDriver(service,options);
					
		} else if (browser.equalsIgnoreCase("IE")) {

			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL, "");
			capabilities.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL, true);
			capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			capabilities.setCapability(CapabilityType.HAS_NATIVE_EVENTS, true);
			capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			capabilities.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, true);
			capabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
			String DRIVER_PATH = System.getProperty("user.dir") + "\\src\\test\\resources\\IEDriverServer.exe";
			System.setProperty("webdriver.ie.driver", DRIVER_PATH);
			String path = System.getProperty("user.dir");
			String cmd = "REG ADD \"HKEY_CURRENT_USER\\Software\\Microsoft\\Internet Explorer\\Main\" /F /V \"Default Download Directory\" /T REG_SZ /D "+ path;
			try {
			    Runtime.getRuntime().exec(cmd);
			} catch (Exception e) {
			    System.out.println("Coulnd't change the registry for default directory for IE");
			}
			driver = new InternetExplorerDriver(capabilities);
		
		} else if (browser.equalsIgnoreCase("Safari")) {
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			capabilities.setCapability(CapabilityType.HAS_NATIVE_EVENTS, true);
			capabilities.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, true);
			capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			driver = new SafariDriver(capabilities);
			driver.manage().window().maximize();
			driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		} else if (browser.equalsIgnoreCase("edge")) {
			System.setProperty("webdriver.edge.driver",
					"C:\\Program Files (x86)\\Microsoft Web Driver\\MicrosoftWebDriver.exe");
			driver = new EdgeDriver();
		}

		else {

			Reporter.log("Incorrect 'Web Browser' name provided");

		}
		driver.manage().window().maximize();
	   	driver.get(url);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
		return driver;

	}
	
	public static void quitbrowser(WebDriver driver) {
		
		driver.quit();
	}
 
	public static void disableSeleniumLogs() {    
	    System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
	    Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF);
	}
}
