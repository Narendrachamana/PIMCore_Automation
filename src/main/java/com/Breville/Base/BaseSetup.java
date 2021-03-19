package com.Breville.Base;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.BasicConfigurator;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.Breville.Pages.GeneralPage;
import com.Breville.Pages.HomePage;
import com.Breville.Pages.LoginPage;
import com.Breville.Pages.ProductImportAndExportPage;
import com.Breville.Utilities.DateForUse;
import com.Breville.Utilities.ReadProperty;
import com.Breville.Utilities.WrapperMethods;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseSetup {

	public static WebDriver driver;

	ReadProperty pr = new ReadProperty("Config");

	protected String browser = pr.getProperty("BROWSER");
	String url = "";

	public String username = pr.getProperty("USERNAME");
	public String password = pr.getProperty("PASSWORD");
	public String enviornmentName = pr.getProperty("ENVIRONMENT");
	public String platform = pr.getProperty("PLATFORM");
	
	public static DesiredCapabilities capability;
	public static String repoDate;
	public ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest extlogger;
	//private static final boolean DEFAULT_VERIFY_CONTAINS = true;

	public static WrapperMethods  wrapperMethods;
	public static LoginPage loginpage;
	public static HomePage homepage;
	public static ProductImportAndExportPage productImportAndExportPage;
	public static GeneralPage generalPage;
	
	@BeforeSuite
	public void setExtent() {
		// specify location of the report
		repoDate = DateForUse.getDateTimeAsFormat();
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + File.separator + "Reports" + File.separator
				+ repoDate + File.separator + "ExtentReport_" + DateForUse.getDateTimeAsFormat() + ".html");

		String css = ".r-img {width: 100%;}";
		htmlReporter.config().setCSS(css);
		htmlReporter.config().setDocumentTitle("PIMCORE Automation Report");
		htmlReporter.config().setReportName("PIMCORE Application Testing"); 
		htmlReporter.config().setTheme(Theme.DARK);


		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);

		// Passing General information
		extent.setSystemInfo("Environemnt", "UAT");
		extent.setSystemInfo("User", "Narendra");
		extent.setSystemInfo("Browser",browser);
		extent.setSystemInfo("Platform", platform);
		
	}
	
	@BeforeMethod
	public void instalization() throws Exception {
		
		capability = new DesiredCapabilities();
	//set Operating System 
			if (platform.equalsIgnoreCase("mac")) {
				capability.setPlatform(Platform.MAC);
			} else if (platform.equalsIgnoreCase("linux")) {
				capability.setPlatform(Platform.LINUX);
			} else if (platform.equalsIgnoreCase("xp")) {
				capability.setPlatform(Platform.XP);
			} else if (platform.equalsIgnoreCase("vista")) {
				capability.setPlatform(Platform.VISTA);
			} else if (platform.equalsIgnoreCase("windows")) {
				capability.setPlatform(Platform.WINDOWS);
		} else if (platform.equalsIgnoreCase("android")) {
			capability.setPlatform(Platform.ANDROID);
			} else {
				capability.setPlatform(Platform.ANY);
			}
	

		// switch case to pick application URL based on environment name from ConfigFile
		switch (enviornmentName) {

		case "Dev":
			url = pr.getProperty("QAURL");
			break;
		case "PROD":
			url = pr.getProperty("PRODURL");
			break;
		case "QA":
			url = pr.getProperty("UATURL");

		}

		try {

			if (browser.equalsIgnoreCase("firefox")) {
				WebDriverManager.firefoxdriver().setup();
				FirefoxProfile profile = new FirefoxProfile();
			    profile.setPreference("browser.cache.disk.enable", false);
			    profile.setPreference("browser.cache.memory.enable", false);
			    profile.setPreference("browser.cache.offline.enable", false);
			    profile.setPreference("network.http.use-cache", false);
			    FirefoxOptions options = new FirefoxOptions().setProfile(profile);
				driver = new FirefoxDriver(options);

			} else if (browser.equalsIgnoreCase("chrome")) {
				WebDriverManager.chromedriver().setup();
				DesiredCapabilities capabilities = DesiredCapabilities.chrome();
				ChromeOptions options = new ChromeOptions();
				options.addArguments("disable-extensions");
				capabilities.setCapability(ChromeOptions.CAPABILITY, options);
				driver = new ChromeDriver(capabilities);

			} else if (browser.equalsIgnoreCase("ie")) {
				DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
				ieCapabilities.setCapability("ignoreProtectedModeSettings", true);
				ieCapabilities.setCapability("ignoreZoomSetting", true);
				ieCapabilities.setCapability("requireWindowFocus", true);  
				ieCapabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);   
				ieCapabilities.setJavascriptEnabled(true);                                                                          
				ieCapabilities.setCapability("ignoreProtectedModeSettings", true); 
				WebDriverManager.iedriver().setup();
				driver = new InternetExplorerDriver(ieCapabilities);
				JavascriptExecutor executor = (JavascriptExecutor) driver;
				executor.executeScript("document.body.style.transform='scale(0.9)'", true);

			} else if (browser.equalsIgnoreCase("edge")) {
				DesiredCapabilities edgeCapabilities = DesiredCapabilities.edge();
				edgeCapabilities.setCapability("ignoreZoomSetting", true);
				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver(edgeCapabilities);
				JavascriptExecutor executor = (JavascriptExecutor) driver;
				executor.executeScript("document.body.style.zoom='100%';");

			} else {
				extlogger.log(Status.INFO, " There is no browser available : " + browser);
				extlogger.log(Status.FAIL,"Snapshot below:" + extlogger.addScreenCaptureFromPath(WrapperMethods.getScreenShot()));
				throw new RuntimeException(browser + " : browser not available. Please mention valid browser");
			}

		} catch (NullPointerException e) {
			extlogger.log(Status.INFO, "Exception while launching driver:" + e);
			extlogger.log(Status.FAIL,"Snapshot below:" + extlogger.addScreenCaptureFromPath(WrapperMethods.getScreenShot()));
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		driver.get(url);
		//extlogger.log(LogStatus.INFO, "Entered url on browser");
		wrapperMethods = new WrapperMethods();
		loginpage = new LoginPage();
		homepage = new HomePage();
		productImportAndExportPage = new ProductImportAndExportPage();
		generalPage = new GeneralPage();
	}

	@AfterMethod
	public void getResult(ITestResult result) throws Exception {
		if (result.getStatus() == ITestResult.FAILURE) {
			try {
				extlogger.log(Status.FAIL, "Test Case Failed is " + result.getName());
				extlogger.log(Status.FAIL,"Test Case Failed is " + result.getThrowable().getMessage(),
						MediaEntityBuilder.createScreenCaptureFromPath(WrapperMethods.getScreenShot()).build());

			} catch (IOException e) {

			}

		} else if (result.getStatus() == ITestResult.SKIP) {
			extlogger.log(Status.SKIP, "Test Case Skipped is " + result.getName());
		}

		else if (result.getStatus() == ITestResult.SUCCESS) {
			extlogger.log(Status.PASS, result.getName() + " Test Case Passed");

		}

		driver.close();
	}

	@AfterSuite
	public void endReport() {
	
		
		/*extlogger = extent.createTest("Verify if user can logout of Breville Application",
				"User should be logged out of the application");

		extlogger.log(Status.INFO, "Going to close the browser");
		extlogger.log(Status.INFO, "*****************Test case Ended***************");
		driver.quit();*/
		extent.flush();
	}

}
