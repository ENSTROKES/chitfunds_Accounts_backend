package com.application.chitfunds.entites;

public class LedgerEntity {
	private String _id;
	private String customerName;
	private String collectionRoute;
	private String groupName;
	private String ticketNumber;
	private Double pendingAmount;
	private Double paidAmount;
	private String lastPaiedDate;
	private Integer installment;
	private String contactNumber;
	
	public Double getPaidAmount() {
		return paidAmount;
	}
	public void setPaidAmount(Double paidAmount) {
		this.paidAmount = paidAmount;
	}
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCollectionRoute() {
		return collectionRoute;
	}
	public void setCollectionRoute(String collectionRoute) {
		this.collectionRoute = collectionRoute;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getTicketNumber() {
		return ticketNumber;
	}
	public void setTicketNumber(String ticketNumber) {
		this.ticketNumber = ticketNumber;
	}
	public Double getPendingAmount() {
		return pendingAmount;
	}
	public void setPendingAmount(Double pendingAmount) {
		this.pendingAmount = pendingAmount;
	}
	public String getLastPaiedDate() {
		return lastPaiedDate;
	}
	public void setLastPaiedDate(String lastPaiedDate) {
		this.lastPaiedDate = lastPaiedDate;
	}
	public Integer getInstallment() {
		return installment;
	}
	public void setInstallment(Integer installment) {
		this.installment = installment;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	
	
}
