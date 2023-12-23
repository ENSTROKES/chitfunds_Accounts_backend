package com.application.chitfunds.entites;

public class Scheme {

	private Long schemeId;
	private String chitValue;
	private Integer numberOfMembers;
	private Integer numberOfMonths;
	private Double registrationFee;
	private Double dueAmount;
	
	public String getChitValue() {
		return chitValue;
	}
	public void setChitValue(String chitValue) {
		this.chitValue = chitValue;
	}
	public Integer getNumberOfMembers() {
		return numberOfMembers;
	}
	public void setNumberOfMembers(Integer numberOfMembers) {
		this.numberOfMembers = numberOfMembers;
	}
	public Integer getNumberOfMonths() {
		return numberOfMonths;
	}
	public void setNumberOfMonths(Integer numberOfMonths) {
		this.numberOfMonths = numberOfMonths;
	}
	public Double getRegistrationFee() {
		return registrationFee;
	}
	public void setRegistrationFee(Double registrationFee) {
		this.registrationFee = registrationFee;
	}
	public Double getDueAmount() {
		return dueAmount;
	}
	public void setDueAmount(Double dueAmount) {
		this.dueAmount = dueAmount;
	}
	
}
