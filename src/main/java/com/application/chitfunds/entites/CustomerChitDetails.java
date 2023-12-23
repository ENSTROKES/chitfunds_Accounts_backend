package com.application.chitfunds.entites;

public class CustomerChitDetails {
	
	private String chitId;
	private String schemeId ;
	private String scheme;
	private String subscription;
	private String collection_route;
	//private String collection_pincode;
	private String chit_asking_month;
	private String remarks;
	private Long created_date;


	public String getChitId() {
		return chitId;
	}

	public void setChitId(String chitId) {
		this.chitId = chitId;
	}

	public String getScheme() {
		return scheme;
	}

	public void setScheme(String scheme) {
		this.scheme = scheme;
	}

	

	public String getSubscription() {
		return subscription;
	}

	public void setSubscription(String subscription) {
		this.subscription = subscription;
	}

	public String getCollection_route() {
		return collection_route;
	}

	public void setCollection_route(String collection_route) {
		this.collection_route = collection_route;
	}

	/*
	 * public String getCollection_pincode() { return collection_pincode; }
	 * 
	 * public void setCollection_pincode(String collection_pincode) {
	 * this.collection_pincode = collection_pincode; }
	 */

	public String getChit_asking_month() {
		return chit_asking_month;
	}

	public void setChit_asking_month(String chit_asking_month) {
		this.chit_asking_month = chit_asking_month;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Long getCreated_date() {
		return created_date;
	}

	public void setCreated_date(Long created_date) {
		this.created_date = created_date;
	}

	public String getSchemeId() {
		return schemeId;
	}

	public void setSchemeId(String schemeId) {
		this.schemeId = schemeId;
	}

	@Override
	public String toString() {
		return "CustomerChitDetails [chitId=" + chitId + ", scheme=" + scheme + ", chit_asking_month=" + chit_asking_month + ", remarks=" + remarks + ", created_date=" + created_date
				+ "]";
	}

}
