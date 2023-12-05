package unitTest;

import java.time.Duration;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import utilities.UtilBase;

public class UnitTest_getQuoteHistory extends UtilBase {

	@BeforeClass
	public void setup() {

		initialiseDriver();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	}
	
	@Test()
	public void getQuoteHistory() throws InterruptedException {
		driver.get(pqc_login_link);
		Thread.sleep(1000);
		driver.get("http://" + pqc_baseurl);
		driver.get("http://10.0.1.62:90/ProcessRFQ/1256317");
		Thread.sleep(5000);

		
		pqc_po.getQuoteHistory_link().click();
		Thread.sleep(2000);
		
		String expectedHeader = "QUOTE HISTORY";
		String header = pqc_po.antModalContent_header().getText();
		System.out.println(header);
		System.out.println();
		String body = pqc_po.antModalContent_body().getText();
		System.out.println(body);
		
//		test
		if(expectedHeader.equals(header) && body.contains("No Data")) {
			System.out.println("PASS");
		}else {
			Assert.assertEquals(header, expectedHeader);
		}
		
//		cleanup
		pqc_po.antModalContent_ok_button().click();
	}
	
	
	
	@AfterClass()
	public void teardown(){
//		driver.quit();
	}

}
