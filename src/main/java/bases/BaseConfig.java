package bases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.github.bonigarcia.wdm.WebDriverManager;
import utilization.Utilize;

@Listeners(bases.TestListener.class)
public class BaseConfig {
	private static WebDriver driver;
	private static WebDriverWait wait;
	private static Actions actions;
	private static long DEFAULT_TIMEOUT = 30000;
	public static SoftAssert sa;
	public static ExtentReports extent;
	private static ExtentSparkReporter spark;
	public static ExtentTest test;
	private static String actualResultPath;
	
	
	@BeforeSuite
	@Parameters({ "browser","headless","url"})
	public void startBrowser(String browser,  String headless,String url) 
	{
		switch (browser.toLowerCase()) {
	        case "firefox":
	            setupFirefoxDriver(headless);
	            break;
	        case "chrome":
	            setupChromeDriver(headless);
	            break;
	        default:
	            throw new IllegalArgumentException("Browser not supported: " + browser);
		}
		configureDriver(url);
		setupExtentReport();
		
        sa = new SoftAssert();
	}
	

	private static void setupChromeDriver(String headless) {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        if (headless.equals("true")) {
            options.addArguments("--headless");
        }
        driver = new ChromeDriver(options);
    }
	
	private static void setupFirefoxDriver(String headless) {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--start-maximized");
        if (headless.equals("true")) {
            options.addArguments("--headless");
        }
        driver = new FirefoxDriver(options);
    }
	
	private static void configureDriver(String url) {
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        actions = new Actions(driver);
        driver.get(url);
        
        wait = new WebDriverWait(driver, TimeUnit.SECONDS.convert(DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS));
    }
	
	private static void setupExtentReport() {
		extent = new ExtentReports();
		spark = new ExtentSparkReporter("index.html");
		spark.config().setTheme(Theme.DARK);
		spark.config().setDocumentTitle("Automation Report");
		spark.config().setReportName("Extent Report Name");
		extent.attachReporter(spark);
	}
	
	@AfterSuite
	public void quitDriver() throws Exception {
		driver.quit();
	}
	
	public static WebDriverWait getWait() {
		return wait;
	}
	
	public static WebDriver getDriver() {
		return driver;
	}
	
	public static Actions getActions() {
		return actions;
	}
	
	public static SoftAssert getSoftAssert() {
        return sa;
    }
	
	public static String getActualResultPath() {
		actualResultPath = Utilize.getAbsolutePath("actualResult/screenshot/");
		return actualResultPath;
	}
}