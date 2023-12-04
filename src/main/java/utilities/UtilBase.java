package utilities;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjects.PQC_pageObjects;
import pageObjects.SQ_pageObjects;

public class UtilBase {

	// global driver(s) initialization, visible to child classes
	protected static WebDriver driver = null;
	protected static Actions actions = null;
	protected static JavascriptExecutor jsDriver = null;


	

	
//	EXTENT REPORT
	protected static ExtentReports extent;
	protected static ExtentTest test, precondition;
	
//	PAGE OBJECTS
	protected SQ_pageObjects sq_po = new SQ_pageObjects();
	protected PQC_pageObjects pqc_po = new PQC_pageObjects();
	
	
//	PQC VARIABLES
	protected String pqc_username = "yamah022";
	protected String pqc_password = "1@work";
	
	protected String pqc_baseurl = "10.0.1.62:90";
	protected String pqc_login_link = "http://" + pqc_username + ":" + pqc_password + "@" + pqc_baseurl;
	
//	global vairables
	protected String caseNumber_global = "";
	
	
	
	public static void initialiseDriver() {
//		String browserName = ExcelRead.getData(1, 2, 0);
		String browserName = "chrome";
//		String path = System.getProperty("user.dir") + "\\browserDrivers\\";

		if (browserName.equalsIgnoreCase("IE")) {
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
		} else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		} else {				
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();	
		}
		driver.manage().window().maximize();
//		Actions class is an ability provided by Selenium for handling keyboard and mouse events.
		actions = new Actions(driver);
//		JavaScriptExecutor is an interface that provides a mechanism to execute Javascript through selenium driver.
		jsDriver = (JavascriptExecutor) driver;
	}

	public static void initialiseDriverwithprofile(String userProfilePath){
		WebDriverManager.chromedriver().setup();
		  // Create a ChromeOptions object.
        ChromeOptions options = new ChromeOptions();

        // Set the path to the user profile directory.
        options.addArguments("user-data-dir=" + userProfilePath);
        // Launch Chrome with the configured options.
        driver = new ChromeDriver(options);
        jsDriver = (JavascriptExecutor) driver;
        Actions actions = new Actions(driver);
	}
	/**
	 * Captures screenshot of the current window of the browser driver
	 * 
	 * @param screenShotName
	 * @return
	 */
	protected static String capture(String screenShotName) {

		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String screenshotPath = "screenshots//" + screenShotName + System.currentTimeMillis() + ".png";

		try {
			FileUtils.copyFile(scrFile, new File(screenshotPath));
		} catch (IOException e) {
			System.err.println("Error occurred saving screenshot!!");
			e.printStackTrace();
		}
//		return screenshotPath;						
		/* change return statement to below statement if you are not using email report	*/	
		return new File (screenshotPath).getAbsolutePath();
	}
	

}
