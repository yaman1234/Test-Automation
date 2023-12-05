package test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;
import utilities.LoggerClass;
import utilities.SuperEmail;
import utilities.TableData;
import utilities.UtilBase;
import utilities.WaitUntil;
import utilities.WebElementLib;

public class PQC_functional_test extends UtilBase {

	/*-------------------------------------------------------------------------------------------------------
	File        : PQC_functional_test.java
	Purpose     : 
	Description : This script is intended to cover all API calls and test the API response
	Author(s)   : Yaman Maharjan
	Created     : December 3, 2023
	Notes       : 
	----------------------------------------------------------------------------------------------------------
	P 		API name    				screen					modal			test case description
	----------------------------------------------------------------------------------------------------------
			quickContactSearch       	create customer RFQ     x	  
			createCustomerRFQ			create customer RFQ		x	
			getRFQHeader				processRFQ
			getCustomers				processRFQ
			getCustomerContacts			processRFQ
			changeRFQOwner				processRFQ			
			getCustomerInformation		processRFQ
			getPartInformation			processRFQ
			getRFQPartStockInfo			processRFQ
			getIncomingGood
			getLotDetails
			addPart
			getRFQLines
			getKitPartDetails
			getRFQLineAlternates
			deleteRFQLines
	----------------------------------------------------------------------------------------------------------
	TO DO: 
	Rename all @Test Methods with associated APIs
	Implement Assert in all test methods
	Remove Thread.sleep methods and implement explicit wait
	Track the Time taken by API calls / actions
	Implement @dataGroups annotation
	Define values for variable in an Excel Sheet
	----------------------------------------------------------------------------------------------------------*/

	// setup
	protected static WaitUntil wait;
	protected static LoggerClass logger;

	/*
	 * Create Customer RFQ screen
	 */
	String expectedUrl_createRFQ = "ProcessRFQ";
//	input for search contact
	String contactname = "sabine.burgener@zodiacaerospace.com";

//	Expected values from search contact result
	String customerNumber_expected, customerName_expected, customerCurrency_expected, customerPhone_expected, customerContact_expected;

//	input for Requested Part Details
	String reqPN_input, reqQty_input, reqUom_input, reqDesc_input, reqLineRef_input;

	// used to get the row count
	WebElement table;
	String tableDataRow = "ant-table-row ant-table-row-level-0 primary-row";
	String commentDataRow = "ant-table-row ant-table-row-level-0 comment-row";
	/*
	 * Process RFQ screen
	 */

	@BeforeClass
	public void setup() {
		ExtentSparkReporter spark = new ExtentSparkReporter("testReports/PQC_Fucntional_Test_Report.html");
		extent = new ExtentReports();
		extent.attachReporter(spark);
		initialiseDriver();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
//		wait = new WebDriverWait(driver, timeout);
		wait = new WaitUntil();
//		logger setup
		logger = new LoggerClass();
		logger.setLogName("applicationLog");
		logger.setDefaults();
	}

	/*--------------------------------------
	 Test name		: login_pqc
	 Description	: Test login in PQC Application
	 --------------------------------------*/
	@Test(priority = 5, groups = "login")
	public void login_pqc() {
		try {

			test = extent.createTest("PQC login test");
			logger.info("START : PQC Login Test ---------------------------");

			driver.get(pqc_login_link);
			Thread.sleep(1000);

			driver.get("http://" + pqc_baseurl);
			Thread.sleep(5000);

			driver.get("http://10.0.1.62:90/ProcessRFQ/1256303");
			Thread.sleep(5000);

//			Run the Test
			boolean result = WebElementLib.doesElementExist(pqc_po.navbar_caseQueue());

			if (result) {
				logger.info("SUCCESS : PQC Login Test PASSED");
				test.pass("Login successful");
				test.addScreenCaptureFromPath(capture("loginTest"), "Login Test");
				Assert.assertTrue(result, "Login Passed");
			} else {
				logger.warning("ERROR : PQC Login Test FAILED ");
				test.fail("Login Failed");
				test.addScreenCaptureFromPath(capture("loginTest"), "Login Test");
				Assert.assertTrue(result, "Login Failed");
			}
			logger.info("END : Login Test -------------------------------------------");

		} catch (Exception e) {
			logger.info("ERROR :: login_pqc");
			e.printStackTrace();
		}
	}

	/*
	 * Search contact, Create Customer RFQ page
	 */
	/*--------------------------------------
	 Test name		: login_pqc
	 Description	: Test quickContactSearch API
	 --------------------------------------*/

	@Test(priority = 10, groups = "createRFQ")
	public void quickContactSearch() {
		try {
			logger.info("START : quickContactSearch test ------------------------------");
			test = extent.createTest("quickContactSearch");

			pqc_po.navbar_createCustomerRFQ().click();
			Thread.sleep(3000);

//			Initialize expected variables
			customerNumber_expected = "70995";
			customerName_expected = "SAFRAN CABIN GERMANY GMBH";
			customerCurrency_expected = "USD";
			customerPhone_expected = "(114)927-7270/7100";
			customerContact_expected = "sabine.burgener@zodiacaerospace.com";
			logger.info(searchContact_expected_toString());

//			Hit the quickConatctsearch API
			pqc_po.quickContactSearch_input().sendKeys(contactname);
			pqc_po.quickContactSearch_button().click();
			Thread.sleep(5000);

//			Initialze varaiblaes with actual values
			String customerNumber_actual = pqc_po.customerNumber_output().getText();
			String customerName_actual = pqc_po.customerName_output().getText();
			String customerCurrency_actual = pqc_po.customerCurrency_output().getText();
			String customerPhone_actual = pqc_po.customerPhone_output().getText();
			String customerContact_actual = pqc_po.customerContact_output().getText();
			logger.info(
					"Search Contact actual values [customerNumber_actual=" + customerNumber_actual + ", customerName_actual=" + customerName_actual + ", customerCurrency_actual="
							+ customerCurrency_actual + ", customerPhone_actual=" + customerPhone_actual + ", customerContact_actual=" + customerContact_actual + "]");

//			Run the Test (compare actual values with expected values)
			if (customerName_actual.equals(customerName_expected) && customerNumber_actual.equals(customerNumber_expected)
					&& customerCurrency_actual.equals(customerCurrency_expected) && customerPhone_actual.equals(customerPhone_expected)) {

				if (customerContact_actual.contains(customerContact_expected)) {
					logger.info("SUCCESS : quickContactSearch PASSED");
					test.pass("quickContactSearch test");
					test.addScreenCaptureFromPath(capture("quickContactSearch_success"), "quickContactSearch");
				}

			} else {
				logger.warning("ERROR : search contact test FAILED");
				test.fail("quickContactSearch test");
				test.addScreenCaptureFromPath(capture("quickContactSearch_failed"), "quickContactSearch");
				Assert.assertTrue(false);
			}
			logger.info("END : Search customer test ----------------------------------------");
		} catch (Exception e) {
			logger.info("ERROR :: quickSearchContact_test");
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}

	/*
	 * create manual RFQ test
	 */
	@Test(priority = 15, groups = "createRFQ")
	public void createCustomerRFQ() {
		try {

			boolean skipTest = false;

			// Check the condition
			if (skipTest) {
				driver.get("http://10.0.1.62:90/ProcessRFQ/1256303");
				// If condition is false, skip the test
				System.out.println("Condition is not met. Skipping the test.");
				throw new org.testng.SkipException("Skipping the test as the condition is not met.");
			}

			test = extent.createTest("create customer RFQ manually");
			logger.info("START : create customer RFQ test ---------------------------------------");

			pqc_po.navbar_createCustomerRFQ().click();
			Thread.sleep(3000);

//			Initialize variables
			reqPN_input = "a5210-10p";
			reqQty_input = "10";
			reqUom_input = "EA";
			reqDesc_input = "Indicator";
			reqLineRef_input = "auto";
			logger.info(requestPartDetails_input_toString());

//			Input Request Part Details
			enterRequestedPartDetails(1, reqPN_input, reqQty_input, reqUom_input, reqDesc_input, reqLineRef_input);
//			enterRequestedPartDetails(2, "a5210-9p", reqQty_input, reqUom_input, reqDesc_input, reqLineRef_input);

			test.pass("enter requested part details");
			test.addScreenCaptureFromPath(capture("enterPartDetails_success"), "enter requested part details");
			Thread.sleep(2000);

//			Hit createCustomerRFQ API
			pqc_po.createRfq_button().click();
			logger.info("[create rfq initiated]");

			wait.wait_until_urlContains(expectedUrl_createRFQ);
			Thread.sleep(15000);

			String currentUrl = driver.getCurrentUrl();
			logger.info("Current URL : " + currentUrl);

//			Run the Test (confirm page redirected to ProcessRFQ and RFQ number is visible)
			if (currentUrl.contains(expectedUrl_createRFQ)) {
				wait.wait_element_present(pqc_po.rfqNumber_headout());
				logger.info("SUCCESS : create customer RFQ");
				test.pass("create customer RFQ");
				test.addScreenCaptureFromPath(capture("createRFQ"), "create customer RFQ");

			} else {
				logger.severe("ERROR : create customer RFQ");
				test.fail("create customer RFQ");
				test.addScreenCaptureFromPath(capture("createRFQ"), "create customer RFQ");
				Assert.assertTrue(false);
			}
			logger.info("END : create customer RFQ test ---------------------------------------");
		} catch (Exception e) {
			logger.info("ERROR :: createCustomerRFQ");
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}

	public void enterRequestedPartDetails(int row, String reqPN_input, String reqQty_input, String reqUom_input, String reqDesc_input, String reqLineRef_input) {
		try {
			actions.doubleClick(pqc_po.reqPN_input(row)).sendKeys(reqPN_input).perform();
			actions.doubleClick(pqc_po.reqQty_input(row)).sendKeys(reqQty_input).perform();
			actions.doubleClick(pqc_po.reqUom_input(row)).sendKeys(reqUom_input).perform();
			actions.doubleClick(pqc_po.reqDesc_input(row)).sendKeys(reqDesc_input).perform();
			actions.doubleClick(pqc_po.reqLineRef_input(row)).sendKeys(reqLineRef_input).perform();

		} catch (Exception e) {
			logger.info("ERROR :: enterRequestedPartDetails");
			test.fail("enter requested part details");
			test.addScreenCaptureFromPath(capture("enterPartDetails_failed"), "enter requested part details");
		}
	}

	@Test(priority = 20, groups = "processRFQ")
	public void getRFQHeader() {

		try {

			test = extent.createTest("get RFQ Header");
			logger.info("START : get RFQ Header test ---------------------------------------");

//			Initialize variables with expected values
			/*
			 * used fields: customerNumber_expected customerName_expected
			 * customerContact_expected note: no need to initialize, as these values are
			 * already initialized in searchContact_test method
			 */

//			Hit the getRFQHeader API
			/*
			 * note: no need to perform any specific action to hit the API, as getRFQHeader
			 * API is called automatically upon Processed RFQ screen load
			 */
			Thread.sleep(3000);

//			Initialize variable with actual values
			String accountName_actual = pqc_po.accountName_headout().getAttribute("value");
			String customerType_actual = pqc_po.customerType_headout().getText();
			String customerCurrency_actual = pqc_po.customerCurrency_headout().getText();
			String customerPhone_actual = pqc_po.customerPhone_headout().getText();
			String contactName_actual = pqc_po.contactName_headout().getText();
			String function_actual = pqc_po.contactFunction_headout().getText();
			String contactEmail_actual = pqc_po.contactEmail_headout().getText();
			String contactPhone_actual = pqc_po.contactPhone_headout().getText();
			String rfqNumber_actual = pqc_po.rfqNumber_headout().getText();
			String rfqOwner_actual = pqc_po.rfqOwner_headout().getText();
			String rfqVersion_actual = pqc_po.rfqVersion_headout().getText();
			String rfqCreated_actual = pqc_po.rfqCreated_headout().getText();

			logger.info("RFQ Header actual values :: ");
			logger.info("RFQ : " + rfqNumber_actual);
			logger.info("Account Name : " + accountName_actual);
			logger.info("Contact Name : " + contactName_actual);
			logger.info("get RFQ Contact info values : [ " + pqc_po.contact_info_container().getText().replace("\n", " ") + "]");
			logger.info("get RFQ Request Details values : [ " + pqc_po.request_details_container().getText().replace("\n", " ") + "]");

//			Run the test (compare actual values to expected values)
			if (accountName_actual.contains(customerNumber_expected) && accountName_actual.contains(customerName_expected)
					&& customerCurrency_actual.equals(customerCurrency_expected) && customerPhone_actual.equals(customerPhone_expected)
					&& contactEmail_actual.equals(customerContact_expected)) {
				logger.info("SUCCESS : get RFQ Header pass");
				test.pass("get RFQ Header");
				test.addScreenCaptureFromPath(capture("rfqHeader"), "get RFQ Header");

			} else {
				logger.info("ERROR : get RFQ Header verification Failed");
				test.fail("get RFQ Header");
				test.addScreenCaptureFromPath(capture("rfqHeader"), "get RFQ Header");
				Assert.assertTrue(false);
			}
			logger.info("END : get RFQ Header test -----------------------------------------");

		} catch (Exception e) {
			logger.severe("ERROR :: getRFQHeader_test");
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}

	@Test(priority = 25, groups = "processRFQ")
	public void getCustomers() {
		try {
			test = extent.createTest("getCustomers_test");
			logger.info("START : getCustomers_test  ---------------------------------------");
//				Initialize variable with expected values

			String customerNumber_expected = "11005";
			String customerName_expected = "KLM-ROYAL DUTCH AIRLINES";

//			Hit the API
			pqc_po.pr_getCustomers_button().click();
			Thread.sleep(2000);
			pqc_po.pr_searchCustomer_input().sendKeys("11005");
			Thread.sleep(1000);
			pqc_po.pr_searchCustomer_button().click();
			Thread.sleep(2000);

			ArrayList<String> row1 = TableData.getRowText_css(pqc_po.pr_searchCustomer_table(), 2);
			logger.info("Search customer output : " + row1);

//				Initialize variable with actual values
			logger.info("Search Customer Row Count" + TableData.getRowCount_css(pqc_po.pr_searchCustomer_table()));
			String customerNumber_actual = TableData.getTableCellText_css(pqc_po.pr_searchCustomer_table(), 2, 1);
			String customerName_actual = TableData.getTableCellText_css(pqc_po.pr_searchCustomer_table(), 2, 2);
			logger.info("[ " + customerNumber_actual + " " + customerName_actual + " ]");
//				Run the Test
			if (customerNumber_actual.equals(customerNumber_expected) && customerName_actual.equals(customerName_expected)) {
				logger.info("SUCCESS : getCustomers_test");
				test.pass("getCustomers_test");
//			 Use JavaScriptExecutor to scroll the element into view
				test.addScreenCaptureFromPath(capture("getCustomers_test_success"), "getCustomers_test ");
			} else {
				logger.info("ERROR : getCustomers_test ");
				test.fail("getCustomers_test");
				test.addScreenCaptureFromPath(capture("getCustomers_test_failed"), "getCustomers_test");
				Assert.assertTrue(false);
			}
//			clean up
			Thread.sleep(1000);
			pqc_po.pr_searchCustomer_close_button().click();
			logger.info("END : getCustomers_test---------------------------------------");
		} catch (Exception e) {
			logger.severe("ERROR : getCustomers_test ");
			test.fail("getCustomers_test");
			test.addScreenCaptureFromPath(capture("getCustomers_test_failed"), "getCustomers_test");
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}

	@Test(priority = 30, groups = "processRFQ")
	public void getCustomerContacts() {
		try {
			test = extent.createTest("getCustomerContacts_test");
			logger.info("START : getCustomerContacts_test  ---------------------------------------");
			String testData = "sabine.burgener@zodiacaerospace.com";
//				Initialize variable with expected values
			String customerName_expected = "Sabine Burgener";
			String customerEmailAddress_expected = "sabine.burgener@zodiacaerospace.com";
			logger.info("search Customer Contact Expected Result : [ " + customerName_expected + " " + customerName_expected + " ]");
//				Hit the API
			pqc_po.getCustomerContacts_button().click();
			Thread.sleep(2000);
			pqc_po.searchCustomerContacts_input().sendKeys(testData);
			Thread.sleep(1000);
			pqc_po.searchCustomerContacts_button().click();
			Thread.sleep(2000);

			ArrayList<String> rowData = TableData.getRowText_css(pqc_po.searchCustomerContacts_table(), 2);
			logger.info("Search customer output : " + rowData);

//				Initialize variable with actual values
			logger.info("Search Customer Contact Row Count" + TableData.getRowCount_css(pqc_po.pr_searchCustomer_table()));
			String customerName_actual = TableData.getTableCellText_css(pqc_po.searchCustomerContacts_table(), 2, 3);
			String customerEmailAddress_actual = TableData.getTableCellText_css(pqc_po.searchCustomerContacts_table(), 2, 5);
			logger.info("search Customer Contact Acutal Result : [ " + customerName_actual + " " + customerEmailAddress_actual + " ]");

//				Run the Test
			if (customerName_actual.equals(customerName_expected) && customerEmailAddress_actual.equals(customerEmailAddress_expected)) {
				logger.info("SUCCESS : getCustomerContacts_test ");
				test.pass("getCustomerContacts_test");
				test.addScreenCaptureFromPath(capture("getCustomerContacts_test_success"), "getCustomerContacts_test ");
			} else {
				logger.info("ERROR : getCustomerContacts_test ");
				test.fail("getCustomerContacts_test");
				test.addScreenCaptureFromPath(capture("getCustomerContacts_test_failed"), "getCustomerContacts_test");
				Assert.assertTrue(false);
			}
//			clean up
			Thread.sleep(1000);
			pqc_po.pr_searchCustomer_close_button().click();

			logger.info("END : getCustomerContacts_test---------------------------------------");
		} catch (Exception e) {
			logger.severe("ERROR : getCustomerContacts_test ");
			test.fail("getCustomerContacts_test");
			test.addScreenCaptureFromPath(capture("getCustomerContacts_failed"), "getCustomerContacts_test");
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}

	@Test(priority = 32)
	public void getQuoteHistory() {
		try {
			test = extent.createTest("getQuoteHistory");
			logger.info("START : getQuoteHistory  ---------------------------------------");
//			Initialize variable with expected values
			String expectedHeader = "QUOTE HISTORY";
			String expectedBody = "No Data";
//			Hit the API
//			 Use JavaScriptExecutor to scroll the element into view
			jsDriver.executeScript("arguments[0].scrollIntoView({block: 'center'});", pqc_po.getQuoteHistory_link());
			pqc_po.getQuoteHistory_link().click();
			Thread.sleep(2000);

//			Initialize variable with actual values
			String header = pqc_po.antModalContent_header().getText();
			logger.info("modal Header: " + header);
			String body = pqc_po.antModalContent_body().getText();
			logger.info("modal Body: " + body);

//			Run the Test
			if (expectedHeader.equals(header) && body.contains(expectedBody)) {
				logger.info("SUCCESS : getQuoteHistory");
				test.pass("getQuoteHistory");
				test.addScreenCaptureFromPath(capture("getQuoteHistory"), "getQuoteHistory ");
			} else {
				logger.info("ERROR : getQuoteHistory ");
				test.fail("getQuoteHistory");
				test.addScreenCaptureFromPath(capture("getQuoteHistory_failed"), "getQuoteHistory");
				Assert.assertTrue(false);
			}
//			cleanup
			Thread.sleep(1000);
			pqc_po.antModalContent_ok_button().click();
			logger.info("END : getQuoteHistory---------------------------------------");
		} catch (Exception e) {
			logger.severe("ERROR : getQuoteHistory ");
			test.fail("getQuoteHistory");
			test.addScreenCaptureFromPath(capture("getQuoteHistory_failed"), "getQuoteHistory");
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}

	@Test(priority = 35, groups = "processRFQ")
	public void changeRfqOwner() {
		try {
			test = extent.createTest("changeRFQOwner_test");
			logger.info("START : changeRFQOwner_test  ---------------------------------------");

			String testData = "Sushan Sunuwar";

//			Initialize variable with expected values
			String owner = pqc_po.rfqOwner_headout().getText();
			logger.info("Original Case Owner : [ " + owner + " ]");
			logger.info("testData : [ " + testData + " ]");

//			Hit the API
			pqc_po.changeRFQOwner_button().click();
			Thread.sleep(1000);

			pqc_po.changeRFQOwner_owner_input().sendKeys(testData);
			Thread.sleep(1000);
			pqc_po.changeRFQOwner_owner_input().sendKeys(Keys.ARROW_DOWN);
			pqc_po.changeRFQOwner_owner_input().sendKeys(Keys.ENTER);
			Thread.sleep(1000);

			test.pass("enter owner");
			test.addScreenCaptureFromPath(capture("enterOwner_success"), "enter owner ");

			pqc_po.changeRFQOwner_ok_input().click();
			Thread.sleep(3000);

//			Initialize variable with actual values
			String newOwner = pqc_po.rfqOwner_headout().getText();
			logger.info("newOwner : [ " + newOwner + " ]");

//			Run the Test
			if (newOwner.equals(testData)) {
				logger.info("SUCCESS : changeRFQOwner_test");
				test.pass("changeRFQOwner_test");
				test.addScreenCaptureFromPath(capture("changeRFQOwner_test_success"), "changeRFQOwner_test ");
			} else {
				logger.info("ERROR : changeRFQOwner_test ");
				test.fail("changeRFQOwner_test");
				test.addScreenCaptureFromPath(capture("changeRFQOwner_test_failed"), "changeRFQOwner_test");
				Assert.assertTrue(false);
			}
			Thread.sleep(2000);
			logger.info("END : changeRFQOwner_test---------------------------------------");
		} catch (Exception e) {
			logger.severe("ERROR : changeRFQOwner_test ");
			test.fail("changeRFQOwner_test");
			test.addScreenCaptureFromPath(capture("changeRFQOwner_test_failed"), "changeRFQOwner_test");
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}

	@Test(priority = 40, groups = "processRFQ")
	public void editRfqHeader() {
		try {
			test = extent.createTest("editRfqHeader");
			logger.info("START : editRfqHeader  ---------------------------------------");

//		Initialize variable with expected values
			String expected_custRef = "Ref101";
			logger.info("Customer Reference Test data : " + expected_custRef);

//		click to open Edit Request Details Modal
			pqc_po.editRFQ_button().click();
			Thread.sleep(1000);

//		verify modal header
			String modalHeader = pqc_po.antModalContent_header().getText();
			logger.info("Header: " + modalHeader);
			Assert.assertEquals(modalHeader, "EDIT REQUEST DETAILS");

//		Hit the API
			pqc_po.editRFQ_custReference_input().clear();
			pqc_po.editRFQ_custReference_input().sendKeys(expected_custRef);
			pqc_po.antModalContent_save_button().click();
			Thread.sleep(2000);

//			Initialize variable with actual values
			String actual_custRef = pqc_po.requestDetails_customerRef().getText();
//			Run the Test
			if (expected_custRef.equals(actual_custRef)) {
				logger.info("SUCCESS : editRfqHeader");
				test.pass("editRfqHeader");
//		 Use JavaScriptExecutor to scroll the element into view
				test.addScreenCaptureFromPath(capture("editRfqHeader_success"), "editRfqHeader ");
			} else {
				logger.info("ERROR : editRfqHeader ");
				test.fail("editRfqHeader");
				test.addScreenCaptureFromPath(capture("editRfqHeader_failed"), "editRfqHeader");
				Assert.assertEquals(actual_custRef, expected_custRef);
			}

			logger.info("END : editRfqHeader---------------------------------------");
		} catch (Exception e) {
			logger.severe("ERROR : editRfqHeader ");
			test.fail("editRfqHeader");
			test.addScreenCaptureFromPath(capture("editRfqHeader_failed"), "editRfqHeader");
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}

//	@Test(priority = 4)
	public void purchasePrice_test() {
		try {
			test = extent.createTest("purchasePrice_test");
			logger.info("START : purchasePrice_test  ---------------------------------------");
//			Initialize variable with expected values
			WebElement table = driver.findElement(By.cssSelector(
					"#root > div > div > div > main > div > div.process-rfq-table-container > div.ant-table-wrapper.process-rfq-table > div > div > div > div > div > table"));
			logger.info("Row count :" + TableData.getRowCount_css(table));
			logger.info("Col count : " + TableData.getColumnCount_css(table));

			String lineStatus = TableData.getTableCellText_css(table, 1, 13);
			logger.info("Cell Text : " + lineStatus);

			if (lineStatus.equals("Purchase price missing")) {
//				click the select checkbox
//				click the send quote button
//				should display the purchse price entry modal

				test.addScreenCaptureFromPath(capture("Purchase price request"), "Purchase price request");

			}

			Thread.sleep(3000);
			for (int i = 1; i <= TableData.getRowCount_css(table); i++) {
				ArrayList<String> rowText = TableData.getRowText_css(table, i);
				System.out.println("Row Text  :" + rowText);
			}

//			Hit the API
//			Initialize variable with actual values
//			Run the Test
			if (true) {
				logger.info("SUCCESS : purchasePrice_test test");
				test.pass("purchasePrice_test");
//		 Use JavaScriptExecutor to scroll the element into view
				test.addScreenCaptureFromPath(capture("purchasePrice_test_success"), "purchasePrice_test ");
			} else {
				logger.info("ERROR : purchasePrice_test ");
				test.fail("purchasePrice_test");
				test.addScreenCaptureFromPath(capture("purchasePrice_test_failed"), "purchasePrice_test");
			}
			logger.info("END : methodName_test---------------------------------------");
		} catch (Exception e) {
			logger.severe("ERROR : purchasePrice_test ");
			test.fail("purchasePrice_test");
			test.addScreenCaptureFromPath(capture("purchasePrice_test_failed"), "purchasePrice_test");
			e.printStackTrace();
		}
	}

	@Test(priority = 45, groups = "processRFQ")
	public void getCustomerInformation() {
		try {
			test = extent.createTest("getCustomerInformation");
			logger.info("START : get Customer Information test ---------------------------------------");

//			Initialize variables with expected values
			String accountType_expected = "OEM";
			String accountStatus_expected = "Active";
			String reportingRegion_expected = "EMEA";
			String outsideSalesRep_expected = "REMCO VERHOEVE";
			String insideSalesRep_expected = "UNASSIGNED REP";
			String customerServiceRep_expected = "Claudio Duarte";
			logger.info("Customer Information expected values [accountType_expected=" + accountType_expected + ", accountStatus_expected=" + accountStatus_expected
					+ ", reportingRegion_expected=" + reportingRegion_expected + ", outsideSalesRep_expected=" + outsideSalesRep_expected + ", insideSalesRep_expected="
					+ insideSalesRep_expected + ", customerServiceRep_expected=" + customerServiceRep_expected + "]");

//			Hit getCustomerInformation API
			wait.wait_element_clickable(pqc_po.getCustomerInformation_button());
			pqc_po.getCustomerInformation_button().click();
			Thread.sleep(3000);

//			Initialize variable with actual values
			logger.info(pqc_po.customer_detail_container().getText().replace("\n", " "));
			String accountType_actual = pqc_po.accountType_custout().getText();
			String accountStatus_actual = pqc_po.accountStatus_custout().getText();
			String reportingRegion_actual = pqc_po.reportingRegion_custout().getText();
			String outsideSalesRep_actual = pqc_po.outsideSalesRep_custout().getText();
			String insideSalesRep_actual = pqc_po.insideSalesRep_custout().getText();
			String customerServiceRep_actual = pqc_po.customerServiceRep_custout().getText();
			logger.info("Customer Information acutal values [accountType_actual= " + accountType_actual + " , accountStatus_actual = " + accountStatus_actual
					+ " , reportingRegion_actual = " + reportingRegion_actual + " , outsideSalesRep_actual = " + outsideSalesRep_actual + " , insideSalesRep_actual = "
					+ insideSalesRep_actual + " , customerServiceRep_actual = " + customerServiceRep_actual + " ]");

//			Run the Test (compare actual values with expected values)
			if (accountType_actual.equals(accountType_expected) && accountStatus_actual.equals(accountStatus_expected) && reportingRegion_actual.equals(reportingRegion_expected)
					&& outsideSalesRep_actual.contains(outsideSalesRep_expected) && insideSalesRep_actual.contains(insideSalesRep_expected)
					&& customerServiceRep_actual.contains(customerServiceRep_expected)) {
				logger.info("SUCCESS : getCustomerInformation test");
				test.pass("getCustomerInformation test");
				test.addScreenCaptureFromPath(capture("getCustomerInformation_success"), "getCustomerInformation test");
			} else {
				logger.warning("ERROR : getCustomerInformation test");
				test.fail("getCustomerInformation test");
				test.addScreenCaptureFromPath(capture("getCustomerInformation_failed"), "getCustomerInformation test");
				Assert.assertTrue(false);
			}
			pqc_po.getCustomerInformation_button().click();
			Thread.sleep(2000);
			logger.info("END : get Customer Information test -----------------------------------------");
		} catch (Exception e) {
			logger.severe("ERROR :: getCustomerInformation");
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}

	@Test(priority = 50, groups = "processRFQ")
	public void getPartInfo() {
		try {
			test = extent.createTest("getPartInformation");
			logger.info("START : get Part Information test ---------------------------------------");

//			Initialize variable with expected values
			String pcat_expected = "V81";
			String pcode_expected = "MBA";
			String itemGroup_expected = "GEN";
			String kitType_expected = "(Not a kit part)";
			String manufacturer_expected = "ACTRON MANUFACTURING, INC.";
			String cageCode_expected = "52607";
			String productManager_expected = "G2B";
			logger.info("get Part Information expected values : pcat_expected = " + pcat_expected + " , pcode_expected = " + pcode_expected + " , itemGroup_expected = "
					+ itemGroup_expected + " , kitType_expected = " + kitType_expected + " , manufacturer_expected = " + manufacturer_expected + " , cageCode_expected = "
					+ cageCode_expected + " , productManager_expected = " + productManager_expected);

//			Hit the getPartInformation API (argument 1, - row number)
			int row = 1;
			pqc_po.getPartInformation_link(row).click();
			Thread.sleep(5000);

// Use JavaScriptExecutor to scroll the element into view
			jsDriver.executeScript("arguments[0].scrollIntoView({block: 'center'});", pqc_po.pcat_partout());
			Thread.sleep(2000);

//			Initialize variable with actual values
			logger.info(" Part Details Section values : [ " + pqc_po.part_details_section().getText().replace("\n", " ") + " ]");
			String pcat_actual = pqc_po.pcat_partout().getText();
			String pcode_actual = pqc_po.pcode_partout().getText();
			String itemGroup_actual = pqc_po.itemGroup_partout().getText();
			String kitType_actual = pqc_po.kitType_partout().getText();
			String manufacturer_actual = pqc_po.manufacturer_partout().getText();
			String cageCode_actual = pqc_po.cageCode_partout().getText();
			String productManager_actual = pqc_po.productManager_partout().getText();
			logger.info("get Part Information Actual values : pcat_actual = " + pcat_actual + " , pcode_actual = " + pcode_actual + " , itemGroup_actual = " + itemGroup_actual
					+ " , kitType_actual = " + kitType_actual + " , manufacturer_actual = " + manufacturer_actual + " , cageCode_actual = " + cageCode_actual
					+ " , productManager_actual = " + productManager_actual);

//			Run the Test
			if (pcat_actual.equals(pcat_expected) && pcode_actual.equals(pcode_expected) && itemGroup_actual.equals(itemGroup_expected) && kitType_actual.equals(kitType_expected)
					&& manufacturer_actual.equals(manufacturer_expected) && cageCode_actual.equals(cageCode_expected) && productManager_actual.equals(productManager_expected)) {
				logger.info("SUCCESS : getPartInformation test");
				test.pass("getPartInformation");
// Use JavaScriptExecutor to scroll the element into view
				jsDriver.executeScript("arguments[0].scrollIntoView({block: 'center'});", pqc_po.salesPrice_table());
				Thread.sleep(2000);
				test.addScreenCaptureFromPath(capture("getPartInformation_success"), "getPartInformation test");
			} else {
				logger.info("ERROR : getPartInformation test");
				test.fail("getPartInformation");
				test.addScreenCaptureFromPath(capture("getPartInformation_failed"), "getPartInformation test");
				Assert.assertTrue(false);
			}
//			Clean up
			jsDriver.executeScript("arguments[0].scrollIntoView({block: 'center'});", pqc_po.getPartInformation_link(row));
			Thread.sleep(1000);
			pqc_po.getPartInformation_link(row).click();
			Thread.sleep(2000);

			logger.info("END : get part Information test ---------------------------------------");
		} catch (Exception e) {
			logger.severe("ERROR :: getPartInformation");
			e.printStackTrace();
		}

	}

	@Test(priority = 55, groups = "processRFQ")
	public void getRFQPartStockInfo() {
		try {
			test = extent.createTest("getRFQPartStockInfo");
			logger.info("START : get RFQ Part Stock Info test ---------------------------------------");

//			Initialize variable with expected values
			String totalAvailable_expected = "125 EA";
			String qtyRequested_expected = "10 EA";
			logger.info("getRFQPartStockInfo Expected values : [ Total Available = " + totalAvailable_expected + " , Qty Requested = " + qtyRequested_expected + " ]");

//			Hit the API
			pqc_po.getRFQPartStockInfo_link().click();
			Thread.sleep(3000);

//			Initialize variable with actual values
			String totalAvailable_actual = pqc_po.totalAvailable_partStock().getText();
			String qtyRequested_actual = pqc_po.qtyRequested_partStock().getText();
			logger.info("getRFQPartStockInfo Actual values : [ Total Available = " + totalAvailable_actual + " , Qty Requested = " + qtyRequested_actual + " ]");

//			Run the Test
			if (totalAvailable_actual.contains(totalAvailable_expected) && qtyRequested_actual.contains(qtyRequested_expected)) {
				logger.info("SUCCESS : getRFQPartStockInfo");
				test.pass("gerRFQPartStockInfo");
				test.addScreenCaptureFromPath(capture("gerRFQPartStockInfo_success"), "getRFQPartStockInfo");
			} else {
				logger.info("ERROR : getRFQPartStockInfo_test ");
				test.fail("getRFQPartStockInfo_test");
				test.addScreenCaptureFromPath(capture("getRFQPartStockInfo_test_failed"), "getRFQPartStockInfo_test");
				Assert.assertTrue(false);
			}
//			Close the modal
			pqc_po.getRFQPartStockInfo_link().click();
			logger.info("END : get RFQ Part Stock Info test ---------------------------------------");
		} catch (Exception e) {
			logger.severe("ERROR : getRFQPartStockInfo_test ");
			test.fail("getRFQPartStockInfo_test");
			test.addScreenCaptureFromPath(capture("getRFQPartStockInfo_test_failed"), "getRFQPartStockInfo_test");
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}

	@Test(priority = 60, groups = "processRFQ")
	public void getIncomingGood_test() {
		try {
			test = extent.createTest("getIncomingGood_test");
			logger.info("START : getIncomingGood_test  ---------------------------------------");
//			Initialize variable with expected values
//			Hit the API
			pqc_po.getRFQPartStockInfo_link().click();
			Thread.sleep(2000);
			pqc_po.getIncomingGood_link().click();
			Thread.sleep(2000);
//			Initialize variable with actual values
			String lotDetails_header = pqc_po.antModalContent_header().getText();
			String lotDetails_body = pqc_po.antModalContent_body().getText();
			logger.info("Incoming Goods Header: " + lotDetails_header);
			logger.info("Incoming Goods Body: " + lotDetails_body);
//			Run the Test
			if (lotDetails_body != null) {
				logger.info("SUCCESS : getIncomingGood_test ");
				test.pass("getIncomingGood_test");
				test.addScreenCaptureFromPath(capture("getIncomingGood_test_success"), "getIncomingGood_test ");
			} else {
				logger.info("ERROR : getIncomingGood_test ");
				test.fail("getIncomingGood_test");
				test.addScreenCaptureFromPath(capture("getIncomingGood_test_failed"), "getIncomingGood_test");
				Assert.assertTrue(false);
			}
//			close the modal
			pqc_po.antModalContent_close_button().click();
			logger.info("END : getIncomingGood_test---------------------------------------");
		} catch (Exception e) {
			logger.severe("ERROR : getIncomingGood_test ");
			test.fail("getIncomingGood_test");
			test.addScreenCaptureFromPath(capture("getIncomingGood_test_failed"), "getIncomingGood_test");
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}

	@Test(priority = 65, groups = "processRFQ")
	public void getLotDetails_test() {
		try {
			test = extent.createTest("getLotDetails_test");
			logger.info("START : getLotDetails_test  ---------------------------------------");
//			Initialize variable with expected values
//			Hit the API
			pqc_po.getRFQPartStockInfo_link().click();
			Thread.sleep(2000);
			pqc_po.getLotDetails_link().click();
			Thread.sleep(2000);
//			Initialize variable with actual values
			String lotDetails_header = pqc_po.antModalContent_header().getText();
			String lotDetails_body = pqc_po.antModalContent_body().getText();
			logger.info("Lot Details Header: " + lotDetails_header);
			logger.info("Lot Details Body: " + lotDetails_body);
//			Run the Test
			if (lotDetails_body != null) {
				logger.info("SUCCESS : getLotDetails_test test");
				test.pass("getLotDetails_test");
				test.addScreenCaptureFromPath(capture("getLotDetails_test_success"), "getLotDetails_test ");
			} else {
				logger.info("ERROR : getLotDetails_test ");
				test.fail("getLotDetails_test");
				test.addScreenCaptureFromPath(capture("getLotDetails_test_failed"), "getLotDetails_test");
				Assert.assertTrue(false);
			}
//			clean up
			pqc_po.antModalContent_close_button().click();
			logger.info("END : getLotDetails_test---------------------------------------");
		} catch (Exception e) {
			logger.severe("ERROR : getLotDetails_test ");
			test.fail("getLotDetails_test");
			test.addScreenCaptureFromPath(capture("getLotDetails_test_failed"), "getLotDetails_test");
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}

	@Test(priority = 70, groups = "processRFQ")
	public void addRFQLine() {
		try {
			driver.navigate().refresh();

			test = extent.createTest("addRFQLine");
			logger.info("START : addRFQLine  ---------------------------------------");
			Thread.sleep(3000);
//			Initialize variable with expected values
			String addPartNumber_input = "E0052R16B26SZE";
			String addPartDesc_input = "Kit Test";
			String addQty_input = "10";
			String addUom_input = "EA";
			String addLineRef_input = "aut";

			table = pqc_po.main_table();

//		check the row count before add part
			int tableDataRowCount = getRFQLines_test(table, tableDataRow);
			int commentDataRowCount = getRFQLines_test(table, commentDataRow);
			logger.info("Before Add Part: [RFQ row count " + tableDataRowCount + " , RFQ comment count: " + commentDataRowCount);

//		Click to open add part modal
			pqc_po.addPart_button().click();
			Thread.sleep(1000);

//			get the header of modal
			logger.info("Add Part modal Header: " + pqc_po.antModalContent_header().getText());

//		send Input 
			pqc_po.addPartNumber_input().sendKeys(addPartNumber_input);
			pqc_po.addPartDescription_input().sendKeys(addPartDesc_input);
			pqc_po.addQty_input().sendKeys(addQty_input);
			pqc_po.addUOM_dropdown().sendKeys(addUom_input);
			pqc_po.addUOM_dropdown().sendKeys(Keys.ENTER);
			pqc_po.addLineReference_input().sendKeys(addLineRef_input);
			Thread.sleep(3000);

			test.addScreenCaptureFromPath(capture("addRFQLine_input"), "addRFQLine");
//			Hit the API
			pqc_po.addRFQLine_button().click();
			Thread.sleep(8000);

//			check the row count after add part
			int tableDataRowCount_after = getRFQLines_test(table, tableDataRow);
			int commentDataRowCount_after = getRFQLines_test(table, commentDataRow);
			logger.info("After Add Part: [RFQ row count " + tableDataRowCount_after + " , RFQ comment count: " + commentDataRowCount_after);

//			Run the Test
			if (tableDataRowCount_after > tableDataRowCount) {

//			 	Use JavaScriptExecutor to scroll the element into view
				jsDriver.executeScript("arguments[0].scrollIntoView({block: 'center'});", pqc_po.main_table());
				Thread.sleep(1000);

				// pass
				logger.info("SUCCESS : addRFQLine");
				test.pass("addRFQLine");
				test.addScreenCaptureFromPath(capture("addRFQLine_success"), "addRFQLine");

				List<String> data = TableData.getTextFromTrElements(table, tableDataRow, 2);
				System.out.println(data);

			} else {
				logger.info("ERROR : addRFQLine ");
				test.fail("addRFQLine");
				test.addScreenCaptureFromPath(capture("addRFQLine_failed"), "addRFQLine");
				Assert.assertTrue(false);
			}
			logger.info("END : addRFQLine ---------------------------------------");
			Thread.sleep(3000);
		} catch (Exception e) {
			logger.severe("ERROR : addRFQLine ");
			test.fail("addRFQLine");
			test.addScreenCaptureFromPath(capture("addRFQLine_failed"), "addRFQLine");
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}

	public String getRFQLineStatus(String row) {
		WebElement element = driver.findElement(By.xpath("//*[@id='root']/div/div/div/main/div/div[4]/div[3]/div/div/div/div/div/table/tbody/tr[" + row + "]/td[13]"));
		String status = element.getText();
		return status;
	}

	public int getRFQLines_test(WebElement table, String className) {
		int rowCount = TableData.getTotalTrElementsWithClass(table, className);
		return rowCount;
	}

	public List<String> getTextFromRow(WebElement table, String className, int row) {
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

	@Test(priority = 75, groups = "processRFQ")
	public void getKitPartDetails_test() {
		try {
			test = extent.createTest("getKitPartDetails_test");
			logger.info("START : getKitPartDetails_test  ---------------------------------------");
//			Initialize variable with expected values
//			Hit the API
			int row = 2;
			pqc_po.getPartInformation_link(row).click();
			Thread.sleep(5000);

//Use JavaScriptExecutor to scroll the element into view
			jsDriver.executeScript("arguments[0].scrollIntoView({block: 'center'});", pqc_po.getKitPartDetails_link());
			Thread.sleep(2000);

//		Initialize variable with actual values
			String kitType_actual = pqc_po.getKitPartDetails_link().getText();
			logger.info("Kit Type : " + kitType_actual);

//			Run the Test
			if (kitType_actual != "(Not a kit part)") {

				pqc_po.getKitPartDetails_link().click();
				Thread.sleep(2000);

				test.addScreenCaptureFromPath(capture("getKitPartDetails_test_success"), "getKitPartDetails_test ");

				String header = pqc_po.getKitPartDetails_header().getText();
				logger.info("Header Text: " + header);
				String body = pqc_po.getKitPartDetails_body().getText();
				logger.info("Body Text: " + body);
				String grandTotal = pqc_po.getKitPartDetails_grandTotal().getText();
				logger.info("grandTotal Text: " + grandTotal);

				pqc_po.getKitPartDetails_footer().click();

				logger.info("SUCCESS : getKitPartDetails_test");
				test.pass("getKitPartDetails_test");

//			clean up
				// Use JavaScriptExecutor to scroll the element into view
				jsDriver.executeScript("arguments[0].scrollIntoView({block: 'center'});", pqc_po.getPartInformation_link(row));
				Thread.sleep(1000);
				pqc_po.getPartInformation_link(row).click();
				Thread.sleep(1000);
				driver.navigate().refresh();
				Thread.sleep(5000);

			} else {
				logger.info("ERROR : getKitPartDetails_test ");
				test.fail("getKitPartDetails_test");
				test.addScreenCaptureFromPath(capture("getKitPartDetails_test_failed"), "getKitPartDetails_test");
				Assert.assertTrue(false);
			}
			logger.info("END : getKitPartDetails_test---------------------------------------");
		} catch (Exception e) {
			logger.severe("ERROR : getKitPartDetails_test ");
			test.fail("getKitPartDetails_test");
			test.addScreenCaptureFromPath(capture("getKitPartDetails_test_failed"), "getKitPartDetails_test");
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}

	@Test(priority = 80, groups = "processRFQ")
	public void getRFQLineAlternates_test() {
		try {
			test = extent.createTest("getRFQLineAlternates_test");
			logger.info("START : getRFQLineAlternates_test  ---------------------------------------");
//		Initialize variable with expected values
			table = pqc_po.main_table();
//			check the row count before add part
			int tableDataRowCount = getRFQLines_test(table, tableDataRow);
			int commentDataRowCount = getRFQLines_test(table, commentDataRow);
			logger.info("Before get RFQLineAlternates: [RFQ row count " + tableDataRowCount + " , RFQ comment count: " + commentDataRowCount);

			for (int i = 1; i <= tableDataRowCount; i++) {
				List<String> rowText = getTextFromRow(table, tableDataRow, i);
				logger.info(rowText.toString());
			}
//		Hit the API
//			 Use JavaScriptExecutor to scroll the element into view
			jsDriver.executeScript("arguments[0].scrollIntoView({block: 'center'});", pqc_po.lineAction_getAlternatives(2));
			pqc_po.lineAction_getAlternatives(2).click();
			Thread.sleep(2000);
//		Initialize variable with actual values
//			check the row count after get RFQ Line Alternates
			int tableDataRowCount_after = getRFQLines_test(table, tableDataRow);
			int commentDataRowCount_after = getRFQLines_test(table, commentDataRow);
			logger.info("After get RFQ Line Alternates : [RFQ row count " + tableDataRowCount_after + " , RFQ comment count: " + commentDataRowCount_after);

			for (int i = 1; i <= tableDataRowCount_after; i++) {
				List<String> rowText = getTextFromRow(table, tableDataRow, i);
				logger.info(rowText.toString());
			}
//		Run the Test
			if (commentDataRowCount_after > tableDataRowCount) {
				logger.info("SUCCESS : getRFQLineAlternates_test");
				test.pass("getRFQLineAlternates_test");
				test.addScreenCaptureFromPath(capture("getRFQLineAlternates_test_success"), "getRFQLineAlternates_test ");
			} else {
				logger.info("ERROR : getRFQLineAlternates_test ");
				test.fail("getRFQLineAlternates_test");
				test.addScreenCaptureFromPath(capture("getRFQLineAlternates_test_failed"), "getRFQLineAlternates_test");
				Assert.assertTrue(false);
			}
//			clean up
			pqc_po.lineAction_getAlternatives(2).click();
			Thread.sleep(2000);
			logger.info("END : getRFQLineAlternates_test---------------------------------------");
		} catch (Exception e) {
			logger.severe("ERROR : getRFQLineAlternates_test ");
			test.fail("getRFQLineAlternates_test");
			test.addScreenCaptureFromPath(capture("getRFQLineAlternates_test_failed"), "getRFQLineAlternates_test");
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}

	@Test(priority = 85, groups = "processRFQ")
	public void deleteRFQLines() {
		try {
			test = extent.createTest("deleteRFQLines_test");
			logger.info("START : deleteRFQLines_test  ---------------------------------------");
//			Initialize variable with expected values
			table = pqc_po.main_table();
//			check the row count before add part
			int tableDataRowCount = getRFQLines_test(table, tableDataRow);
			int commentDataRowCount = getRFQLines_test(table, commentDataRow);
			logger.info("Before Delete: [RFQ row count " + tableDataRowCount + " , RFQ comment count: " + commentDataRowCount);
			for (int i = 1; i <= tableDataRowCount; i++) {
				List<String> rowText = getTextFromRow(table, tableDataRow, i);
				logger.info(rowText.toString());
			}
//			Hit the API
//			 Use JavaScriptExecutor to scroll the element into view
			jsDriver.executeScript("arguments[0].scrollIntoView({block: 'center'});", pqc_po.lineAction_delete(2));
			pqc_po.lineAction_delete(2).click();
			Thread.sleep(2000);

//		confirm delete
			pqc_po.delete_confirm().click();
			Thread.sleep(5000);

//			check the row count after Delete RFQ Line
			int tableDataRowCount_after = getRFQLines_test(table, tableDataRow);
			int commentDataRowCount_after = getRFQLines_test(table, commentDataRow);
			logger.info("After Delete RFQ Line : [RFQ row count " + tableDataRowCount_after + " , RFQ comment count: " + commentDataRowCount_after);

			for (int i = 1; i <= tableDataRowCount_after; i++) {
				List<String> rowText = getTextFromRow(table, tableDataRow, i);
				logger.info(rowText.toString());
			}

//			Initialize variable with actual values
//			Run the Test
			if (tableDataRowCount_after < tableDataRowCount) {
				logger.info("SUCCESS : deleteRFQLines_test");
				test.pass("deleteRFQLines_test");
				test.addScreenCaptureFromPath(capture("deleteRFQLines_test_success"), "deleteRFQLines_test ");
			} else {
				logger.info("ERROR : deleteRFQLines_test ");
				test.fail("deleteRFQLines_test");
				test.addScreenCaptureFromPath(capture("deleteRFQLines_test_failed"), "deleteRFQLines_test");
				Assert.assertTrue(false);
			}
			logger.info("END : deleteRFQLines_test---------------------------------------");
		} catch (Exception e) {
			logger.severe("ERROR : deleteRFQLines_test ");
			test.fail("deleteRFQLines_test");
			test.addScreenCaptureFromPath(capture("deleteRFQLines_test_failed"), "deleteRFQLines_test");
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}

//		@Test (priority = 100)
	public void methodName_test() {
		try {
			test = extent.createTest("methodName_test");
			logger.info("START : methodName_test  ---------------------------------------");
//				Initialize variable with expected values
//				Hit the API
//				Initialize variable with actual values
//				Run the Test
			if (true) {
				logger.info("SUCCESS : methodName_test");
				test.pass("methodName_test");
//			 Use JavaScriptExecutor to scroll the element into view
				jsDriver.executeScript("arguments[0].scrollIntoView({block: 'center'});", pqc_po.salesPrice_table());
				test.addScreenCaptureFromPath(capture("methodName_test_success"), "methodName_test ");
			} else {
				logger.info("ERROR : methodName_test ");
				test.fail("methodName_test");
				test.addScreenCaptureFromPath(capture("methodName_test_failed"), "methodName_test");
				Assert.assertTrue(false);
			}
			logger.info("END : methodName_test---------------------------------------");
		} catch (Exception e) {
			logger.severe("ERROR : methodName_test ");
			test.fail("methodName_test");
			test.addScreenCaptureFromPath(capture("methodName_test_failed"), "methodName_test");
			e.printStackTrace();
			Assert.assertTrue(false);
		}
	}

	public String requestPartDetails_input_toString() {
		return "Requested Part Details [reqPN_input=" + reqPN_input + ", reqQty_input=" + reqQty_input + ", reqUom_input=" + reqUom_input + ", reqDesc_input=" + reqDesc_input
				+ ", reqLineRef_input=" + reqLineRef_input + "]";
	}

	public String searchContact_expected_toString() {
		return "Searh Contact expected values [customerNumber_expected=" + customerNumber_expected + ", customerName_expected=" + customerName_expected
				+ ", customerCurrency_expected=" + customerCurrency_expected + ", customerPhone_expected=" + customerPhone_expected + ", customerContact_expected="
				+ customerContact_expected + "]";
	}

	@Test(priority = 999)
	public void emailReportAndLog() {

		try {
			test = extent.createTest("emailReport");
			logger.info("START : emailReportAndLog  ---------------------------------------");
//				Initialize variable with expected values
			String username = "yaman.maharjan@javra.com";
			String password = "!wertyu$@AQW";
			String to = "yaman.maharjan@javra.com";
			String cc = "nishant.sah@javra.com";
			String subject = "Automation Report";
			String description = "This is automated report.";
			String attachmentReport = System.getProperty("user.dir") + "/testReports/PQC_Fucntional_Test_Report.html";
			String attachmentLog = System.getProperty("user.dir") + "/logs/applicationLog.txt";

			SuperEmail.sendReportAndLog(username, password, to, cc, subject, description, attachmentReport, attachmentLog);
			logger.info("SUCCESS : emailReportAndLog");
			test.pass("emailReportAndLog");

			logger.info("END : emailReport---------------------------------------");
		} catch (Exception e) {
			logger.severe("ERROR : emailReport ");
			test.fail("emailReport");
			test.addScreenCaptureFromPath(capture("emailReport_failed"), "emailReport");
			e.printStackTrace();
			Assert.assertTrue(false);
		}

	}

	@AfterClass
	public void teardown() {
		extent.flush();
//		driver.close();
	}

}
