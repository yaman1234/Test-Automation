package concepts;

import java.time.Duration;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import utilities.UtilBase;

public class TestNG_chapter3 extends UtilBase {

//	note: need to run using TestNG.xml file
	
	@BeforeClass()
	public void beforeClass() {
		initialiseDriver();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

	}

	@BeforeMethod()
	public void beforeMethod() {
		System.out.println("/");
	}

	@AfterMethod()
	public void afterMethod() {
		System.out.println("//");
	}

	@Test(priority = 1, groups = "Login")
	@Parameters({"username", "password", "env"})
	public void loginTest(String username, String password, String env) {


		String pqc_login_link = "http://" + username + ":" + password + "@" + env;

		driver.get(pqc_login_link);
		driver.get("http://" + env);

		boolean result = driver.findElement(By.xpath("//*[@id='root']/div/div/div/header/h1/img")).isDisplayed();
		Assert.assertEquals(result, true, "expected element not found");
	}

	@Test()
	public void titleTest() {
		String title = driver.getTitle();
		Assert.assertEquals(title, "Quoting Application");
	}

	@AfterClass
	public void afterClass() {

//		driver.close();
	}
}
