package com.application.chitfunds.entites;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection ="receipt")
public class Receipt {
	
	private Long rId;
	private String _id;
	private String receiptId;
	private String branchName;
	private String receiptDate;
	private String collectionEmployee;
	private String customerName;
	private String selectEnrollment;
	private String receiptType;
	private Long billAmount;
	private Long createdDate;
	private Long lastUpdatedDate;
	private String customerId;
	private String groupId;
	private String employeeId;
	private String collectionRoute;
	private String paymentMode;
	private String ticketNumber;
	
	
	
	public String getTicketNumber() {
		return ticketNumber;
	}
	public void setTicketNumber(String ticketNumber) {
		this.ticketNumber = ticketNumber;
	}
	public String getPaymentMode() {
		return paymentMode;
	}
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
	public String getCollectionRoute() {
		return collectionRoute;
	}
	public void setCollectionRoute(String collectionRoute) {
		this.collectionRoute = collectionRoute;
	}
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	

	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public Long getrId() {
		return rId;
	}
	public void setrId(Long rId) {
		this.rId = rId;
	}
	public String getReceiptId() {
		return receiptId;
	}
	public void setReceiptId(String receiptId) {
		this.receiptId = receiptId;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getReceiptDate() {
		return receiptDate;
	}
	public void setReceiptDate(String receiptDate) {
		this.receiptDate = receiptDate;
	}
	public String getCollectionEmployee() {
		return collectionEmployee;
	}
	public void setCollectionEmployee(String collectionEmployee) {
		this.collectionEmployee = collectionEmployee;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getSelectEnrollment() {
		return selectEnrollment;
	}
	public void setSelectEnrollment(String selectEnrollment) {
		this.selectEnrollment = selectEnrollment;
	}
	public String getReceiptType() {
		return receiptType;
	}
	public void setReceiptType(String receiptType) {
		this.receiptType = receiptType;
	}
	public Long getBillAmount() {
		return billAmount;
	}
	public void setBillAmount(Long billAmount) {
		this.billAmount = billAmount;
	}
	public Long getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Long createdDate) {
		this.createdDate = createdDate;
	}
	public Long getLastUpdatedDate() {
		return lastUpdatedDate;
	}
	public void setLastUpdatedDate(Long lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}
	
	

}
