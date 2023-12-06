package unitTest;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import utilities.TableData;
import utilities.UtilBase;

public class UnitTest_template extends UtilBase {
	
	int row = 2;

	@BeforeClass
	public void setup() throws InterruptedException {

		initialiseDriver();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		
		
		driver.get(pqc_login_link);
		Thread.sleep(1000);
		driver.get("http://" + pqc_baseurl);
		driver.get("http://10.0.1.62:90/ProcessRFQ/1256338");
		Thread.sleep(5000);

	}

	@Test()
	public void getPartPricingDetails() throws InterruptedException {

		String tableDataRow = "ant-table-row ant-table-row-level-0 primary-row";
		WebElement table = pqc_po.main_table();

//		scroll into view
		jsDriver.executeScript("arguments[0].scrollIntoView({block: 'center'});", pqc_po.lineAction_selectRow(row));

//		check the status
		String status = getRFQLineStatus(row);
		System.out.println(status);

//		validate status
		if (status.contains("Purchase price missing")) {

//		click the checkbox
			pqc_po.lineAction_selectRow(row).click();
//			click send quote button
			pqc_po.sendQuote_button().click();

//			verify the title of modal
			String header = pqc_po.priceRequestModal_header().getText();
			System.out.println(header);
			String expectedHeader = "PRICE REQUEST";

//			test
			if (header.equals(expectedHeader)) {
				System.out.println("Test pass: getPartPricingDetails");
			}
		}

//		cleanup

	}
	
	
	
	
	
	@Test(priority = 2)
	public void getPriceRequestInfo() {
		
		pqc_po.getPriceRequestInfo_link().click();
		
		
		String expectedHeader = "PRICE REQUEST DETAIL";
		String header = pqc_po.priceRequestDetailModal_header().getText();
		System.out.println(header);
		
		
//		test
		if (header.equals(expectedHeader)) {
			System.out.println("Test pass: getPriceRequestInfo");
		}
	}
		
		
		
	@Test (priority = 3)
	public void createProcessRelay() throws InterruptedException {
//		close the pricerequestdetails modal
		pqc_po.priceRequestDetalModal_close_button().click();
		
		pqc_po.priceRequestModal_proceedYes_button().click();
		
		pqc_po.priceRequestModal_ok_button().click();
		Thread.sleep(7000);
		
//		close send quote modal
		
		
		
//		check the status
//		check the status
		String status = getRFQLineStatus(row);
		System.out.println(status);

//		validate status
		if (status.contains("Purchase Price Requested")) {
			
			System.out.println("PASS: createProcessRelay");
			
		}
		
	}
	
	
	
	
	
	
	

	public void getRFQLine_check(int row) {
		WebElement rfqLine_checkbox = driver.findElement(By.xpath("//tr[@class='ant-table-row ant-table-row-level-0 primary-row'][" + row + "]/td[1]/label/span/input"));
		rfqLine_checkbox.click();
	}

	public String getRFQLineStatus(int row) {
		WebElement element = driver.findElement(By.xpath("//tr[@class='ant-table-row ant-table-row-level-0 primary-row'][" + row + "]/td[13]"));
		String status = element.getText();
		return status;
	}

	public int getRFQLines_count(WebElement table, String className) {
		int rowCount = TableData.getTotalTrElementsWithClass(table, className);
		return rowCount;
	}

	public List<String> getRFQLines_text(WebElement table, String className, int row) {
		List<String> tdTextList = new ArrayList<>();

		String xpathExpression = ".//tbody//tr[@class='" + className + "'][" + row + "]";
		// Find all matching <tr> elements
		for (WebElement trElement : table.findElements(By.xpath(xpathExpression))) {
			// Find all <td> elements within the current <tr>
			for (int i = 0; i < trElement.findElements(By.tagName("td")).size(); i++) {
				WebElement tdElement = trElement.findElements(By.tagName("td")).get(i); // Adjust index to be 0-based

				// Add the text of each <td> element to the list
				tdTextList.add(tdElement.getText().replace("\n", " "));
			}
		}
		return tdTextList;
	}

//Request for this part is handled by material management team
//	getPriceRequestInfo

	@AfterClass()
	public void teardown() {
//		driver.quit();
	}

}
