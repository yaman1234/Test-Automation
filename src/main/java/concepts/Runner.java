package concepts;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;
import utilities.SuperEmail;
import utilities.TableData;
import utilities.UtilBase;

public class Runner extends UtilBase {

	@BeforeClass(alwaysRun = true)
	public void setup() {
		initialiseDriver();
	}

	@AfterClass(alwaysRun = true)
	public void teardown() {
	}

	@Test(priority = 1)
	public void method1() throws InterruptedException {
		
		
		boolean b = true;
		
	     // Check the condition
        if (b) {
            // If condition is false, skip the test
            System.out.println("Condition is not met. Skipping the test.");
            throw new org.testng.SkipException("Skipping the test as the condition is not met.");
        }
		
		
		Assert.assertFalse(false, "Test Failed");
//		Assert.assertEquals(b, true, "Failed");
		
		System.out.println("Method 1");
//		driver.get(pqc_login_link);
//		Thread.sleep(2000);
//
//		driver.get("http://10.0.1.62:90/ProcessRFQ/1256107");
//		Thread.sleep(5000);

	}

	@Test(priority = 3)
	public void getTextDiv() {
		boolean b = true;
		
		Assert.assertTrue(false, "Test Failed");
//		Assert.assertEquals(b, false, "Failed 2");
		
		System.out.println("Method 2");
		
//		WebElement element = driver.findElement(By.xpath("//div[@class='request-details-container']"));
//		System.out.println(element.getAttribute(pqc_username));
	}
	
	
//	@Test(priority = 5)
	public void method2() throws InterruptedException {
		WebElement table = pqc_po.main_table();

		String tableDataRow = "ant-table-row ant-table-row-level-0";
		String commentDataRow = "ant-table-row ant-table-row-level-0 comment-row";

		int tableDataRowCount = getTotalTrElementsWithClass(table, tableDataRow);
		int commentDataRowCount = getTotalTrElementsWithClass(table, commentDataRow);

		System.out.println("tableDataRowCount : " + tableDataRowCount);
		System.out.println("commentDataRowCount : " + commentDataRowCount);

		List<String> data = getTextFromTrElements(table, tableDataRow, 2);
		System.out.println(data);
		System.out.println("Status : " + data.get(12));

	}
	

	

	public static int getTotalTrElementsWithClass(WebElement table, String className) {
		String xpathExpression = ".//tbody//tr[@class='" + className + "']";
		int totalTrElements = table.findElements(By.xpath(xpathExpression)).size();

		return totalTrElements;
	}


	public List<String> getTextFromTrElements(WebElement table, String className, int row) {
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

}