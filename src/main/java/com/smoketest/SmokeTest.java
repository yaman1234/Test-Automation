package com.smoketest;

import java.time.Duration;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import utilities.UtilBase;

public class SmokeTest extends UtilBase{

	
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

//	login test
	@Test(priority = 1, groups = "login")
	@Parameters({"username", "password", "env"})
	public void loginTest(String username, String password, String env) {


		String pqc_login_link = "http://" + username + ":" + password + "@" + env;

		driver.get(pqc_login_link);
		driver.get("http://" + env);

		boolean result = driver.findElement(By.xpath("//*[@id='root']/div/div/div/header/h1/img")).isDisplayed();
		Assert.assertEquals(result, true, "Login Failed");
	}
//	Title test
	@Test(groups = "login")
	public void titleTest() {
		String title = driver.getTitle();
		Assert.assertEquals(title, "Quoting Application");
	}
	
	


//	Navbar test
	@Test(groups = "smoketest")
	public void navbar_casesqueue() {
		pqc_po.navbar_caseQueue().click();
		String title = driver.getTitle();
		Assert.assertEquals(title, "Quoting Application");
	}
	
}
