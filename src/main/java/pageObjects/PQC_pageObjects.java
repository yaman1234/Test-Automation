package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import utilities.WebElementLib;

public class PQC_pageObjects {

	/* NAVBAR section elements : start */
	public WebElement navbar_caseQueue() {
		return WebElementLib.findMyElement("xpath", "//*[@id='root']/div/div/div/header/nav/ul/li[1]/span/span/a");
	}

	public WebElement navbar_myOpenRfq() {
		return WebElementLib.findMyElement("xpath", "//*[@id='root']/div/div/div/header/nav/ul/li[2]/span/span/a");
	}

	public WebElement navbar_myTrainingRfq() {
		return WebElementLib.findMyElement("xpath", "//*[@id='root']/div/div/div/header/nav/ul/li[3]/span/span/a");
	}

	public WebElement navbar_myActiveQuotes() {
		return WebElementLib.findMyElement("xpath", "//*[@id='root']/div/div/div/header/nav/ul/li[4]/span/span/a");
	}

	public WebElement navbar_globalSearch() {
		return WebElementLib.findMyElement("xpath", "//*[@id='root']/div/div/div/header/nav/ul/li[5]/span/span/a");
	}

	public WebElement navbar_createCustomerRFQ() {
		return WebElementLib.findMyElement("xpath", "//*[@id='root']/div/div/div/header/nav/ul/li[6]/span/span/a");
	}

	public WebElement navbar_sqc() {
		return WebElementLib.findMyElement("xpath", "//*[@id='PQ-spotQoute']/div[2]/svg");
	}

	public WebElement navbar_nc() {
		return WebElementLib.findMyElement("xpath", "//*[@id='pq-comm-root']/div/div[1]/svg");
	}

	/* NAVBAR section elements : end */

	/* GLOBAL SEARCH page elements : start */
	public WebElement search_button() {
		return WebElementLib.findMyElement("xpath", "//*[@id='root']/div/div/div/main/div/div[1]/form/div[1]/div[5]/button");
	}

	public WebElement caseNumber_input() {
		return WebElementLib.findMyElement("xpath", "//*[@id=\"root\"]/div/div/div/main/div/div[1]/form/div[2]/div[1]/div[6]/div[2]/div/div/input");
	}

	public WebElement rfqNumber_input() {
		return WebElementLib.findMyElement("xpath", "//*[@id=\"root\"]/div/div/div/main/div/div[1]/form/div[2]/div[1]/div[5]/div[2]/div/div/input");
	}

	public WebElement table_table() {
		return WebElementLib.findMyElement("xpath", "//table");
	}
	/************** GLOBAL SEARCH page elements : end ************/

	/*
	 * 
	 */

	/*********** PROCESS RFQ screen, page elements : start ***********/
	/* get Customer API */
//	get customer (hit getCustomer API and opens search customer modal)
	public WebElement pr_getCustomers_button() {
		return WebElementLib.findMyElement("xpath", "//*[@id=\"root\"]/div/div/div/main/div/div[1]/form/div/div[1]/div[1]/div[2]/div/div/span/span/span/button");
	}
	
	/* getQuoteHistory API */
//	get customer contacts
	public WebElement getQuoteHistory_link() {
		return WebElementLib.findMyElement("xpath", "//a[@title='Quote History']");
	}

	/* get Customer Contacts API */
//	get customer contacts
	public WebElement getCustomerContacts_button() {
		return WebElementLib.findMyElement("xpath", "//*[@id=\"root\"]/div/div/div/main/div/div[1]/form/div/div[2]/div[1]/div[2]/div/div/span/span/span/button");
	}

	/* changeRFQOwner API */
	public WebElement changeRFQOwner_button() {
		return WebElementLib.findMyElement("xpath", "//*[@id='root']/div/div/div/main/div/div[1]/form/div/div[3]/div[3]/div[2]/div/div/div/div[2]/button");
	}

	/* getCustomerInformation API */
	public WebElement getCustomerInformation_button() {
		return WebElementLib.findMyElement("xpath", "//*[@id='root']/div/div/div/main/div/div[3]/div/button");
	}

	/* getPartInformation API */
	public WebElement getPartInformation_link(int row) {
		return WebElementLib.findMyElement("xpath",
				"//div[@class='ant-table-wrapper process-rfq-table']/div/div/div/div/div/table/tbody/tr[@class='ant-table-row ant-table-row-level-0 primary-row'][" + row + "]/td[6]/div/a");
	}

	/* getRFQPartStockInfo API */
	public WebElement getRFQPartStockInfo_link() {
		return WebElementLib.findMyElement("xpath", "//*[@id='root']/div/div/div/main/div/div[4]/div[3]/div/div/div/div/div/table/tbody/tr[1]/td[9]/span[2]");
	}

	/* getIncomingGoods API */
	public WebElement getIncomingGood_link() {
		return WebElementLib.findMyElement("xpath", "/html/body/div[5]/div/div/div/div[2]/div/div/div[4]/button[1]");
	}

	/* getLotDetails API */
	public WebElement getLotDetails_link() {
		return WebElementLib.findMyElement("xpath", "/html/body/div[5]/div/div/div/div[2]/div/div/div[4]/button[2]");
	}

	/*
	 * 
	 * 
	 * 
	 */

	/* start----- modal: Search Customer */
//	search customer textbox
	public WebElement pr_searchCustomer_input() {
		return WebElementLib.findMyElement("xpath", "//div[@class='ant-modal contact-customer-search-modal']/div/div[2]/div/div/div/div/span/span/input");
	}

//	search customer button (hit getCustomer API and show result in table)
	public WebElement pr_searchCustomer_button() {
		return WebElementLib.findMyElement("xpath", "//div[@class='ant-modal contact-customer-search-modal']/div/div[2]/div/div/div/div/span/span/span/button");
	}

//	search customer table
	public WebElement pr_searchCustomer_table() {
		return WebElementLib.findMyElement("xpath", "//div[@class='ant-modal contact-customer-search-modal']/div/div[2]/div[2]/div/div/div/div/div[2]/table");
	}

//	search customer table close button
	public WebElement pr_searchCustomer_close_button() {
		return WebElementLib.findMyElement("xpath", "//div[@class='ant-modal contact-customer-search-modal']/div/button");
	}

//	screen: process rfq, modal: search customer, label: ok, type: button
	public WebElement pr_searchCustomer_ok_button() {
		return WebElementLib.findMyElement("xpath", "//div[@class='ant-modal contact-customer-search-modal']/div/div[3]/button");
	}
	/* end ----- modal: Search Customer */

	/*
	 * 
	 */

	/* start----- modal: Search Customer Contact */
//	search customer contact textbox
	public WebElement searchCustomerContacts_input() {
		return WebElementLib.findMyElement("xpath", "//div[@class='ant-modal contact-customer-search-modal']/div/div[2]/div/div/div/div/span/span/input");
	}

//	search customer button (hit getCustomer API and show result in table)
	public WebElement searchCustomerContacts_button() {
		return WebElementLib.findMyElement("xpath", "//div[@class='ant-modal contact-customer-search-modal']/div/div[2]/div/div/div/div/span/span/span/button");
	}

	public WebElement searchCustomerContacts_table() {
		return WebElementLib.findMyElement("xpath", "//div[@class='ant-modal contact-customer-search-modal']/div/div[2]/div[2]/div/div/div/div/div[2]/table");
	}

//	search customer table close button
	public WebElement searchCustomerContacts_close_button() {
		return WebElementLib.findMyElement("xpath", "//div[@class='ant-modal contact-customer-search-modal']/div/button");
	}

//	search customer table ok button
	public WebElement searchCustomerContacts_ok_button() {
		return WebElementLib.findMyElement("xpath", "//div[@class='ant-modal contact-customer-search-modal']/div/div[3]/button");
	}
	/* end ----- modal: Search Customer Contact */

	/*
	 * 
	 */

	/* start----- modal: Assign Case RFQ */
//	Assign Case RFQ Modal
	public WebElement changeRFQOwner_owner_input() {
		return WebElementLib.findMyElement("xpath", "//*[@id='rc_select_0']");
	}

	public WebElement changeRFQOwner_comment_input() {
		return WebElementLib.findMyElement("xpath", "//textarea[@class='ant-input' and @placeholder='Comment']");
	}

	public WebElement changeRFQOwner_cancel_input() {
		return WebElementLib.findMyElement("xpath", "//div[@class='ant-modal-footer']/button[1]");
	}

	public WebElement changeRFQOwner_ok_input() {
		return WebElementLib.findMyElement("xpath", "//div[@class='ant-modal-footer']/button[2]");
	}
	/* end ----- modal: Assign Case RFQ */

	/*
	 * 
	 */

	public WebElement main_table() {
		return WebElementLib.findMyElement("xpath", "//*[@id='root']/div/div/div/main/div/div[4]/div[3]/div/div/div/div/div/table");
	}

	public WebElement sendQuote_button() {
		return WebElementLib.findMyElement("xpath", "//div[@class='process-rfq-actions sticky-content']/div[2]/button/span[text()='Send Quote']");
	}

// click to	open the add part modal
	public WebElement addPart_button() {
		return WebElementLib.findMyElement("xpath", "//div[@class='process-rfq-actions sticky-content']/div[1]/button/span[text()='Add Part']");
	}

	
		public WebElement cancel_button() {
		return WebElementLib.findMyElement("xpath", "//div[@class='process-rfq-actions sticky-content']/div[1]/button/span[text()='Cancel']");
	}
		
		public WebElement reprocess_button() {
		return WebElementLib.findMyElement("xpath", "//div[@class='process-rfq-actions sticky-content']/div[1]/button/span[text()='Reprocess']");
	}
		
		public WebElement preview_button() {
		return WebElementLib.findMyElement("xpath", "//div[@class='process-rfq-actions sticky-content']/div[2]/button/span[text()='Preview']");
	}
	/*
	 * 
	 */

	/* start ----- modal: ADD REQUESTED PART FOR RFQ */

//	ADD REQUESTED PART FOR RFQ
	public WebElement addPartNumber_input() {
		return WebElementLib.findMyElement("xpath", "//*[@id=\"partNo\"]");
	}

	public WebElement addPartDescription_input() {
		return WebElementLib.findMyElement("xpath", "//*[@id=\"partDescription\"]");
	}

	public WebElement addQty_input() {
		return WebElementLib.findMyElement("xpath", "//*[@id=\"partQty\"]");
	}

	public WebElement addUOM_dropdown() {
		return WebElementLib.findMyElement("xpath", "//*[@id=\"partUOM\"]");
	}

	public WebElement addLineReference_input() {
		return WebElementLib.findMyElement("xpath", "//*[@id=\"lineRef\"]");
	}

	public WebElement addRFQLine_button() {
		return WebElementLib.findMyElement("xpath", "//button[@data-class='add-part-button']");
	}

	/*
	 * 
	 */

	/***************** Create Customer RFQ screen *********************/

	/* start ------ Create Customer RFQ screen */
	/* quickContactSearch API */
	public WebElement quickContactSearch_button() {
		return WebElementLib.findMyElement("xpath", "//div[@class='customer-contact-search-container']/form/div/div[1]/div/div[2]/div/div/span/span/span/button");
	}

	/* createCustomerRFQ API */
	public WebElement createRfq_button() {
		return WebElementLib.findMyElement("xpath", "//*[@id='root']/div/div/div/main/div/div[3]/div[2]/div[2]/div/div[2]/button[1]/span");
	}

	public WebElement quickContactSearch_input() {
		return WebElementLib.findMyElement("xpath", "//*[@id='customer-contact-search']");
	}

	/* start --- Customer Information section, create customer RFQ screen */
	public WebElement customerNumber_output() {
		return WebElementLib.findMyElement("xpath", "//*[@id=\"root\"]/div/div/div/main/div/div[2]/div[2]/div[1]/div/div/table/tbody/tr[1]/td/div/span[2]");
	}

	public WebElement customerName_output() {
		return WebElementLib.findMyElement("xpath", "//*[@id=\"root\"]/div/div/div/main/div/div[2]/div[2]/div[1]/div/div/table/tbody/tr[2]/td/div/span[2]");
	}

	public WebElement customerCurrency_output() {
		return WebElementLib.findMyElement("xpath", "//*[@id=\"root\"]/div/div/div/main/div/div[2]/div[2]/div[1]/div/div/table/tbody/tr[3]/td/div/span[2]");
	}

	public WebElement customerPhone_output() {
		return WebElementLib.findMyElement("xpath", "//*[@id=\"root\"]/div/div/div/main/div/div[2]/div[2]/div[1]/div/div/table/tbody/tr[4]/td/div/span[2]");
	}

	public WebElement customerContact_output() {
		return WebElementLib.findMyElement("xpath", "//*[@id=\"root\"]/div/div/div/main/div/div[2]/div[2]/div[2]/div/div/table/tbody/tr/td/div/span[2]/div/div/div/div[1]");
	}
	/* end --- Customer Information section, create customer RFQ screen */

	/*
	 * start --- Requested Part Details section (table), create customer RFQ screen
	 */
	public WebElement reqPN_input(int row) {
		return WebElementLib.findMyElement("xpath", "//div[@class='ht_master handsontable']/div/div/div/table/tbody/tr[" + row + "]/td[1]");
	}

	public WebElement reqQty_input(int row) {
		return WebElementLib.findMyElement("xpath", "//div[@class='ht_master handsontable']/div/div/div/table/tbody/tr[" + row + "]/td[2]");
	}

	public WebElement reqUom_input(int row) {
		return WebElementLib.findMyElement("xpath", "//div[@class='ht_master handsontable']/div/div/div/table/tbody/tr[" + row + "]/td[3]");
	}

	public WebElement reqDesc_input(int row) {
		return WebElementLib.findMyElement("xpath", "//div[@class='ht_master handsontable']/div/div/div/table/tbody/tr[" + row + "]/td[4]");
	}

	public WebElement reqLineRef_input(int row) {
		return WebElementLib.findMyElement("xpath", "//div[@class='ht_master handsontable']/div/div/div/table/tbody/tr[" + row + "]/td[5]");
	}
	/*
	 * end --- Requested Part Details section (table), create customer RFQ screen
	 */

	/*
	 * 
	 */

	/*
	 * start --- contact-info-container, Process RFQ screen
	 */
	public WebElement contact_info_container() {
		return WebElementLib.findMyElement("xpath", "//div[@class='contact-info-container']");
	}

	public WebElement rfqNumber_headout() {
		return WebElementLib.findMyElement("xpath", "//*[@id=\"root\"]/div/div/div/main/div/div[1]/form/div/div[3]/div[1]/div[2]/div");
	}

	public WebElement rfqVersion_headout() {
		return WebElementLib.findMyElement("xpath", "//*[@id=\"root\"]/div/div/div/main/div/div[1]/form/div/div[3]/div[2]/div[2]/div/div");
	}

	public WebElement rfqOwner_headout() {
		return WebElementLib.findMyElement("xpath", "//*[@id=\"root\"]/div/div/div/main/div/div[1]/form/div/div[3]/div[3]/div[2]/div/div/div/div[1]");
	}

	public WebElement rfqCreated_headout() {
		return WebElementLib.findMyElement("xpath", "//*[@id=\"root\"]/div/div/div/main/div/div[1]/form/div/div[3]/div[4]/div[2]/div/div");
	}

	public WebElement accountName_headout() {
		return WebElementLib.findMyElement("xpath", "//*[@id=\"root\"]/div/div/div/main/div/div[1]/form/div/div[1]/div[1]/div[2]/div/div/span/span/input");
	}

	public WebElement customerType_headout() {
		return WebElementLib.findMyElement("xpath", "//*[@id=\"root\"]/div/div/div/main/div/div[1]/form/div/div[1]/div[2]/div[2]/div/div");
	}

	public WebElement customerCurrency_headout() {
		return WebElementLib.findMyElement("xpath", "//*[@id=\"root\"]/div/div/div/main/div/div[1]/form/div/div[1]/div[3]/div[2]/div/div");
	}

	public WebElement customerPhone_headout() {
		return WebElementLib.findMyElement("xpath", "//*[@id=\"root\"]/div/div/div/main/div/div[1]/form/div/div[1]/div[4]/div[2]/div/div");
	}

	public WebElement contactName_headout() {
		return WebElementLib.findMyElement("xpath", "//*[@id=\"root\"]/div/div/div/main/div/div[1]/form/div/div[2]/div[1]/div[2]/div/div/span/span/input");
	}

	public WebElement contactFunction_headout() {
		return WebElementLib.findMyElement("xpath", "//*[@id=\"root\"]/div/div/div/main/div/div[1]/form/div/div[2]/div[2]/div[2]/div/div");
	}

	public WebElement contactEmail_headout() {
		return WebElementLib.findMyElement("xpath", "//*[@id=\"root\"]/div/div/div/main/div/div[1]/form/div/div[2]/div[3]/div[2]/div/div");
	}

	public WebElement contactPhone_headout() {
		return WebElementLib.findMyElement("xpath", "//*[@id=\"root\"]/div/div/div/main/div/div[1]/form/div/div[2]/div[4]/div[2]/div/div");
	}

	/*
	 * start --- request-details-container, Process RFQ screen
	 */
	public WebElement request_details_container() {
		return WebElementLib.findMyElement("xpath", "//div[@class='request-details-container']");
	}
	public WebElement requestDetails_paymentTerms() {
		return WebElementLib.findMyElement("xpath", "//div[@class='request-details-container']/form/div/div[1]/div[1]/div[2]");
	}
	public WebElement requestDetails_deliveryCondition() {
		return WebElementLib.findMyElement("xpath", "//div[@class='request-details-container']/form/div/div[1]/div[2]/div[2]");
	}
	public WebElement requestDetails_quoteOrigin() {
		return WebElementLib.findMyElement("xpath", "//div[@class='request-details-container']/form/div/div[1]/div[3]/div[2]");
	}
	public WebElement requestDetails_caseNumber() {
		return WebElementLib.findMyElement("xpath", "//div[@class='request-details-container']/form/div/div[1]/div[4]/div[2]");
	}
	public WebElement requestDetails_customerRef() {
		return WebElementLib.findMyElement("xpath", "//div[@class='request-details-container']/form/div/div[2]/div[1]/div[2]");
	}
	public WebElement requestDetails_expiryDate() {
		return WebElementLib.findMyElement("xpath", "//div[@class='request-details-container']/form/div/div[2]/div[2]/div[2]");
	}
	public WebElement requestDetails_quoteAll_checkbox() {
		return WebElementLib.findMyElement("xpath", "");
	}
	public WebElement requestDetails_closeAutomatically_checkbox() {
		return WebElementLib.findMyElement("xpath", "");
	}
	/*
	 * end --- request-details-container, Process RFQ screen
	 */

	/*
	 * 
	 */

	/*
	 * customer-detail-container, PROCESS RFQ screen
	 */

	public WebElement customer_detail_container() {
		return WebElementLib.findMyElement("xpath", "//div[@class='customer-detail-container']");
	}

	public WebElement accountType_custout() {
		return WebElementLib.findMyElement("xpath", "//*[@id=\"root\"]/div/div/div/main/div/div[3]/div[2]/div/table/tbody/tr[1]/td[1]/div/span[2]");
	}

	public WebElement accountStatus_custout() {
		return WebElementLib.findMyElement("xpath", "//*[@id=\"root\"]/div/div/div/main/div/div[3]/div[2]/div/table/tbody/tr[2]/td[1]/div/span[2]");
	}

	public WebElement reportingRegion_custout() {
		return WebElementLib.findMyElement("xpath", "//*[@id=\"root\"]/div/div/div/main/div/div[3]/div[2]/div/table/tbody/tr[3]/td[1]/div/span[2]");
	}

	public WebElement outsideSalesRep_custout() {
		return WebElementLib.findMyElement("xpath", "//*[@id=\"root\"]/div/div/div/main/div/div[3]/div[2]/div/table/tbody/tr[2]/td[2]/div/span[2]");
	}

	public WebElement insideSalesRep_custout() {
		return WebElementLib.findMyElement("xpath", "//*[@id=\"root\"]/div/div/div/main/div/div[3]/div[2]/div/table/tbody/tr[3]/td[2]/div/span[2]");
	}

	public WebElement customerServiceRep_custout() {
		return WebElementLib.findMyElement("xpath", "//*[@id=\"root\"]/div/div/div/main/div/div[3]/div[2]/div/table/tbody/tr[4]/td[2]/div/span[2]");
	}

	/*
	 * end --- customer-detail-container, PROCESS RFQ screen
	 */

	/*
	 * start -- part-details-section, getPartinformation, PROCESS RFQ screen
	 */

	public WebElement part_details_section() {
		return WebElementLib.findMyElement("xpath", "//div[@class='part-details-section']");
	}

	public WebElement pcat_partout() {
		return WebElementLib.findMyElement("xpath", "//div[@class='part-details-section']/div/div[1]/div/div/table/tbody/tr[1]/td/div/span[2]");
	}

	public WebElement pcode_partout() {
		return WebElementLib.findMyElement("xpath",
				"//div[@class='part-details-section']/div/div[1]/div/div/table/tbody/tr[2]/td/div/span[2]");
	}

	public WebElement itemGroup_partout() {
		return WebElementLib.findMyElement("xpath",
				"//div[@class='part-details-section']/div/div[1]/div/div/table/tbody/tr[3]/td/div/span[2]");
	}

	public WebElement kitType_partout() {
		return WebElementLib.findMyElement("xpath",
				"//div[@class='part-details-section']/div/div[1]/div/div/table/tbody/tr[4]/td/div/span[2]/span");
	}

	public WebElement manufacturer_partout() {
		return WebElementLib.findMyElement("xpath",
				"//*[@id=\"root\"]/div/div/div/main/div/div[4]/div[3]/div/div/div/div/div/table/tbody/tr[3]/td/div/div[1]/div/div[2]/div/div/table/tbody/tr[2]/td/div/span[2]");
	}

	public WebElement cageCode_partout() {
		return WebElementLib.findMyElement("xpath",
				"//*[@id=\"root\"]/div/div/div/main/div/div[4]/div[3]/div/div/div/div/div/table/tbody/tr[3]/td/div/div[1]/div/div[2]/div/div/table/tbody/tr[3]/td/div/span[2]");
	}

	public WebElement productManager_partout() {
		return WebElementLib.findMyElement("xpath",
				"//*[@id=\"root\"]/div/div/div/main/div/div[4]/div[3]/div/div/div/div/div/table/tbody/tr[3]/td/div/div[1]/div/div[2]/div/div/table/tbody/tr[5]/td/div/span[2]");
	}

//	tables

	public WebElement salesPrice_table() {
		return WebElementLib.findMyElement("xpath", "//*[@id=\"root\"]/div/div/div/main/div/div[4]/div[3]/div/div/div/div/div/table/tbody/tr[3]/td/div/div[2]/div[1]/div");
	}

	public WebElement purchasePrice_table() {
		return WebElementLib.findMyElement("xpath", "//*[@id=\"root\"]/div/div/div/main/div/div[4]/div[3]/div/div/div/div/div/table/tbody/tr[3]/td/div/div[2]/div[2]");
	}

	public WebElement quoteHistory_table() {
		return WebElementLib.findMyElement("xpath", "//*[@id=\"root\"]/div/div/div/main/div/div[4]/div[3]/div/div/div/div/div/table/tbody/tr[3]/td/div/div[3]/div[1]");
	}

	public WebElement salesHistory_table() {
		return WebElementLib.findMyElement("xpath", "//*[@id=\"root\"]/div/div/div/main/div/div[4]/div[3]/div/div/div/div/div/table/tbody/tr[3]/td/div/div[3]/div[2]");
	}

	public WebElement stockAvailability_table() {
		return WebElementLib.findMyElement("xpath", "//*[@id=\"root\"]/div/div/div/main/div/div[4]/div[3]/div/div/div/div/div/table/tbody/tr[3]/td/div/div[4]");
	}
	/*
	 * end -- part-details-section, getPartinformation, PROCESS RFQ screen
	 */

	/*
	 * 
	 */
	
	
	/*
	 * start -- getPartStockinfo, Lot Details modal, Incoming Good modal  PROCESS RFQ screen	
	 */
	public WebElement totalAvailable_partStock() {
		return WebElementLib.findMyElement("xpath", "/html/body/div[5]/div/div/div/div[2]/div/div/div[2]");
	}

	public WebElement qtyRequested_partStock() {
		return WebElementLib.findMyElement("xpath", "/html/body/div[5]/div/div/div/div[2]/div/div/div[3]");
	}


	/*
	 * end -- getPartStockinfo, Lot Details modal, Incoming Good modal  PROCESS RFQ screen	
	 */
	
	/*
	 * 
	 */
	
	/*
	 * start -- getKitPartDetails, Process RFQ screen
	 */
	// div[@class='ant-table-wrapper
	// process-rfq-table']/div/div/div/div/div/table/tbody/tr[@class='ant-table-row
	// ant-table-row-level-0'][2]/td[6]/div/a
	public WebElement getKitPartDetails_link() {
		return WebElementLib.findMyElement("xpath",
				"//*[@id='root']/div/div/div/main/div/div[4]/div[3]/div/div/div/div/div/table/tbody/tr[5]/td/div/div[1]/div/div[1]/div/div/table/tbody/tr[4]/td/div/span[2]/a");
	}

//	Kit Component details modal
	public WebElement kitComponentDetails_table() {
		return WebElementLib.findMyElement("xpath", "//div[@class='ant-modal-content']");
	}

	public WebElement getKitPartDetails_header() {
		return WebElementLib.findMyElement("xpath", "//div[@class='ant-modal-content']/div[1]");
	}

	public WebElement getKitPartDetails_body() {
		return WebElementLib.findMyElement("xpath", "//div[@class='ant-modal-content']/div[2]");
	}

	public WebElement getKitPartDetails_grandTotal() {
		return WebElementLib.findMyElement("xpath", "//div[@class='ant-modal-content']/div[2]/div[2]");
	}

	public WebElement getKitPartDetails_footer() {
		return WebElementLib.findMyElement("xpath", "//div[@class='ant-modal-content']/div[3]/button");
	}
	/*
	 * end -- getKitPartDetails, Process RFQ screen
	 */
	
	/*
	 * 
	 */
	
	/*
	 *  start -- RFQ Line Actions Col
	 */

	public WebElement lineAction_edit(int row) {
		return WebElementLib.findMyElement("xpath",
				"//div[@class='ant-table-wrapper process-rfq-table']/div/div/div/div/div/table/tbody/tr[@class='ant-table-row ant-table-row-level-0 primary-row'][" + row
						+ "]/td[2]/div/span[@title='Edit']");
	}
	
	/* deleteRFQLines API */
	public WebElement lineAction_delete(int row) {
		return WebElementLib.findMyElement("xpath",
				"//div[@class='ant-table-wrapper process-rfq-table']/div/div/div/div/div/table/tbody/tr[@class='ant-table-row ant-table-row-level-0 primary-row'][" + row
						+ "]/td[2]/div/span[@title='Delete']");
	}

	public WebElement delete_confirm() {
		return WebElementLib.findMyElement("xpath", "//div[@class='ant-modal ant-modal-confirm ant-modal-confirm-confirm']/div/div/div/div[2]/button[2]");
	}

	/* getRFQLineAlternates API */
	public WebElement lineAction_getAlternatives(int row) {
		return WebElementLib.findMyElement("xpath",
				"//div[@class='ant-table-wrapper process-rfq-table']/div/div/div/div/div/table/tbody/tr[@class='ant-table-row ant-table-row-level-0 primary-row'][" + row
						+ "]/td[2]/div/span[@title='Alternatives']");
	}
	
	public WebElement lineAction_selectRow(int row) {
		return WebElementLib.findMyElement("xpath","//tr[@class='ant-table-row ant-table-row-level-0 primary-row']["+row+"]/td[1]/label/span/input");
	}
	/*
	 * end -- RFQ Line Actions Col
	 */

	
	/*
	 * 
	 */
	
	/*
	 * start --- popover
	 * note: same xpath applied for all popovers (sales price break, ships from, certificates)
	 */
	
	public WebElement popoverContent() {
		return WebElementLib.findMyElement("xpath", "//div[@class='ant-popover-content']");
	}
	
	
	/*
	 * 
	 */
	
	
	/*
	 * start --- ant-modal-content (Add Part, Incoming Goods, Lot Details, Send Quote, getQuoteHistory)
	 */
	public WebElement antModalContent() {
		return WebElementLib.findMyElement("xpath", "//div[@class='ant-modal-content']");
	}
	public WebElement antModalContent_header() {
		return WebElementLib.findMyElement("xpath", "//div[@class='ant-modal-content']/div[@class='ant-modal-header']");
	}
	public WebElement antModalContent_body() {
		return WebElementLib.findMyElement("xpath", "//div[@class='ant-modal-content']/div[@class='ant-modal-body']");
	}
	public WebElement antModalContent_footer() {
		return WebElementLib.findMyElement("xpath", "//div[@class='ant-modal-content']/div[@class='ant-modal-footer']");
	}
	public WebElement antModalContent_close_button() {
		return WebElementLib.findMyElement("xpath", "//div[@class='ant-modal-content']/button[@aria-label='Close']");
	}
	public WebElement antModalContent_ok_button() {
		return WebElementLib.findMyElement("xpath", "//div[@class='ant-modal-footer']/button/span[text()='Ok']");
	}
	public WebElement antModalContent_leave_button() {
		return WebElementLib.findMyElement("xpath", "//div[@class='ant-modal-footer']/button[span[text()='Leave']]");
	}
	public WebElement antModalContent_cancel_button() {
		return WebElementLib.findMyElement("xpath", "//div[@class='ant-modal-footer']/button[span[text()='Cancel']]");
	}
	public WebElement antModalContent_save_button() {
		return WebElementLib.findMyElement("xpath", "//div[@class='ant-modal-footer']/button[span[text()='Save']]");
	}
	
	/*
	 * end -- ant-modal-content
	 */
	
	/*
	 * 
	 */
	
	/*
	 * start -- PRICE REQUEST modal, PRICE REQUEST DETAIL modal
	 */
	
	
	public WebElement getPriceRequestInfo_link() {
		return WebElementLib.findMyElement("xpath", "//div[@class='ant-modal-content']/div[2]/div/div/div/div/div/div[2]/table/tbody/tr[3]/td/div[3]/a");
	}
	
	
	
	public WebElement priceRequestDetailModal_header() {
		return WebElementLib.findMyElement("xpath", "//div[@class='ant-modal add-part-modal']/div[@class='ant-modal-content']/div[@class='ant-modal-header']");
	}
	
	public WebElement priceRequestDetalModal_close_button() {
		return WebElementLib.findMyElement("xpath", "//div[@class='ant-modal add-part-modal']/div[@class='ant-modal-content']/button");
	}
	
	
	
	
	
	public WebElement priceRequestModal() {
		return WebElementLib.findMyElement("xpath", "//div[@class='ant-modal price-request-modal']");
	}
	
	
	public WebElement priceRequestModal_ok_button() {
		return WebElementLib.findMyElement("xpath", "//div[@class='ant-modal price-request-modal']/div[@class='ant-modal-content']/div[@class='ant-modal-footer']/button[@title='Ok']");
	}
	
	
	
	
	public WebElement priceRequestModal_header() {
		return WebElementLib.findMyElement("xpath", "//div[@class='ant-modal price-request-modal']/div[@class='ant-modal-content']/div[@class='ant-modal-header']");
	}
	//div[@class='ant-modal-content']/div[2]/div/div/div/div/div/div[2]/table/tbody/tr[3]/td/div[2]
	
	public WebElement priceRequestModal_comment() {
		return WebElementLib.findMyElement("xpath", "//div[@class='ant-modal-content']/div[2]/div/div/div/div/div/div[2]/table/tbody/tr[3]/td/div[1]/textarea");
	}
	
	
	public WebElement priceRequestModal_proceedYes_button() {
		return WebElementLib.findMyElement("xpath", "//div[@class='ant-modal-content']/div[2]/div/div/div/div/div/div[2]/table/tbody/tr[3]/td/div[2]/button[1]");
	}

	
	public WebElement priceRequestModal_proceedNo_button() {
		return WebElementLib.findMyElement("xpath", "//div[@class='ant-modal-content']/div[2]/div/div/div/div/div/div[2]/table/tbody/tr[3]/td/div[2]/button[2]");
	}
	
	public WebElement priceRequestModal_notifyMe_checkbox() {
		return WebElementLib.findMyElement("xpath", "");
	}

	
	public WebElement priceRequestModal_newRequest_button() {
		return WebElementLib.findMyElement("xpath", "//div[@class='ant-modal-content']/div[2]/div/div/div/div/div/div[2]/table/tbody/tr[3]/td/div[3]/label/span[1]/input");
	}
	/*
	 * end -- price-request-modal
	 */
	
	
	
	
	
	/*
	 * start -- Edit Request Details
	 */
	public WebElement editRFQ_button() {
		return WebElementLib.findMyElement("xpath", "//button[span[text()='Edit']]");
	}
	
	public WebElement deleteRFQ_button() {
		return WebElementLib.findMyElement("xpath", "//button[span[text()='Delete']]");
	}
	
	
	
	public WebElement editRFQ_custReference_input() {
		return WebElementLib.findMyElement("xpath", "//*[@id='crhcustref']");
	}
	
	/*
	 * end -- Edit Request Details
	 */
	
	/*
	 * 
	 */
	
	/*
	 * start -- Send quote to customer
	 */
	
	
	public WebElement sendQuoteModal() {
		return WebElementLib.findMyElement("xpath", "//div[@class='ant-modal send-quote-modal']");
	}
	
	public WebElement sendQuoteModal_header() {
		return WebElementLib.findMyElement("xpath", "//div[@class='ant-modal send-quote-modal']/div/div[@class='ant-modal-header']");
	}
	
	
	public WebElement sendQuoteModal_close_button() {
		return WebElementLib.findMyElement("xpath", "//div[@class='ant-modal send-quote-modal']/div/button[@class='ant-modal-close']");
	}
	
	public WebElement sendQuoteModal_sendQuote_button() {
		return WebElementLib.findMyElement("xpath", "//div[@class='ant-modal send-quote-modal']/div/div[@class='ant-modal-footer']/button[@title='Send Quote Document to Customer']");
	}
	
	public WebElement sendQuoteModal_previewQuote_button() {
		return WebElementLib.findMyElement("xpath", "//div[@class='ant-modal send-quote-modal']/div/div[@class='ant-modal-footer']/button[@title='Preview Customer Quote Document']");
	}
	
	
	
	/*
	 * end -- Send quote to customer
	 */
	
	
	
	public WebElement statusChange_link(int row) {
		return WebElementLib.findMyElement("xpath", "//tr[@class='ant-table-row ant-table-row-level-0 primary-row']["+row+"]/td[13]/div/a");
	}
	
	public WebElement statusChange_popover_header() {
		return WebElementLib.findMyElement("xpath", "//div[@class='ant-popover-content']/div[2]/div[@class='ant-popover-title']");
	}
	
	
	public WebElement statusChange_viewDetails_button() {
		return WebElementLib.findMyElement("xpath", "//div[@class='status-change-popover']/div[@class='action-section']/button[@title='View Details']");
	}
	
	
	
}
