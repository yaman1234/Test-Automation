package concepts;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestNG_chapter2 {

	/*
	 * (priority = 1) is used to set the execution order of @Test Method (testcases)
	 * groups = "login" is used to create the group of testcases
	 * dependsOnMethods("methodnama") could be used if execution of a method is dependent only on success of prior method
	 */

	@BeforeClass()
	public void beforeClass() {
		System.out.println("before class");
	}

	@BeforeMethod()
	public void beforeMethod() {
		System.out.println("before Method");
	}

	@Test(priority = 1, groups = "Login")
	public void method1() {
		int c = 9 / 0;
		System.out.println("method 1");
	}

	@Test(priority = 2, groups = "Login", dependsOnMethods = "method1")
	public void method2() {
		System.out.println("method 2");

	}

	@Test(priority = 4, groups = "Login")
	public void method4() {
		System.out.println("method 4");
	}

	@Test(priority = 3, groups = "search")
	public void method3() {

		System.out.println("method 3");
	}

	@AfterMethod()
	public void afterMethod() {

	}
}
