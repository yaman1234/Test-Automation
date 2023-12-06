package unitTest;

import java.time.Duration;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import utilities.UtilBase;

public class UnitTest_Cancel extends UtilBase{


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
		Thread.sleep(9000);
		
		pqc_po.lineAction_selectRow(2).click();
		Thread.sleep(2000);
		pqc_po.cancel_button().click();
		


	}

	
}
