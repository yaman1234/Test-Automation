package concepts;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestNG_chapter1 {
	
	
//	Types of Annotation in TestNG and execution sequence
//	1-@BeforeSuite
//	2-@BeforeTest
//	3-@BeforeClass
	
	
	/*
	 * note: @BeforeMethod and @AfterMethod is executed every time for each @Test method
	 */
//	4-@BeforeMethod
	//	5- @Test
//	6- @AfterMethod
	
//	7-@After Class
//	8- @AfterTest
//	
	
	
	
	@BeforeSuite
	public void setup() {
		System.out.println("beforeSuite");
	}
	@BeforeTest
	public void beforeTest() {
		System.out.println("beforeTest");
		
	}	
	@BeforeClass
	public void beforeClass() {
		System.out.println("beforeClass");
		
	}
	@BeforeMethod()
	public void beforeMethod() {
		System.out.println("before Method");
		
	}
	
	@Test
	public void testcase1(){
		System.out.println("Test case 1");
	}

	
	@AfterSuite
	public void setupA() {
		System.out.println("Setup");
	}
	@AfterClass
	public void afterClass() {
		System.out.println("afterClass");
		
	}	
	@AfterTest
	public void afterTest() {
		System.out.println("afterTest");
		
	}
	@AfterMethod()
	public void afterMethod() {
		System.out.println("after Method");
		
	}
	

}
