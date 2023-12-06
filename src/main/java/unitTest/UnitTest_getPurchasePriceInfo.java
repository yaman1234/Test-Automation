package unitTest;

import java.time.Duration;

import org.testng.Assert;
//import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import utilities.UtilBase;

public class UnitTest_getPurchasePriceInfo extends UtilBase {

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
		driver.get("http://10.0.1.62:90/ProcessRFQ/1256355");
		Thread.sleep(5000);

//		Initialize variable with expected values
		String expectedHeader = "Status Change";
//		Hit the API
		pqc_po.statusChange_link(2).click();
		Thread.sleep(1000);
//		Initialize variable with actual values
		String actualHeader = pqc_po.statusChange_popover_header().getText();
		System.out.println(actualHeader);

//	Run the Test
		if (actualHeader.equals(expectedHeader)) {

			pqc_po.statusChange_viewDetails_button().click();
			Thread.sleep(3000);

			String header_expected = "PRICE REQUESTED";
			String header_actual = pqc_po.antModalContent_header().getText();

			if (header_actual.equals(header_expected)) {
				System.out.println("SUCCESS : getPurchasePriceRequestInfo");
			}

		} else {
			System.out.println("ERROR : getPurchasePriceRequestInfo ");
			Assert.assertTrue(false);
		}

//clean up
		pqc_po.antModalContent_close_button().click();

	}

}
