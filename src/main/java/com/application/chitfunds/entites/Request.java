package com.application.chitfunds.entites;

public class Request {
	private Object object;
	private String startDate;
	private Integer size;
	private Integer page;
	private String regiterType;
	private Integer autionDate;
	private String schemeId;
	private String schemeName;
	private String collectionRoute;
	private String joiningDate;
	private String branch;
	private Boolean headOffice;
	private String receiptType;
	private String billDate;
	
	
	public String getBillDate() {
		return billDate;
	}

	public void setBillDate(String billDate) {
		this.billDate = billDate;
	}

	public Boolean getHeadOffice() {
		return headOffice;
	}

	public String getReceiptType() {
		return receiptType;
	}

	public void setReceiptType(String receiptType) {
		this.receiptType = receiptType;
	}

	public void setHeadOffice(Boolean headOffice) {
		this.headOffice = headOffice;
	}

	public String getCollectionRoute() {
		return collectionRoute;
	}

	public void setCollectionRoute(String collectionRoute) {
		this.collectionRoute = collectionRoute;
	}

	public String getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(String joiningDate) {
		this.joiningDate = joiningDate;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public String getRegiterType() {
		return regiterType;
	}

	public void setRegiterType(String regiterType) {
		this.regiterType = regiterType;
	}

	public Integer getAutionDate() {
		return autionDate;
	}

	public void setAutionDate(Integer autionDate) {
		this.autionDate = autionDate;
	}

	public String getSchemeId() {
		return schemeId;
	}

	public void setSchemeId(String schemeId) {
		this.schemeId = schemeId;
	}

	public String getSchemeName() {
		return schemeName;
	}

	public void setSchemeName(String schemeName) {
		this.schemeName = schemeName;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	
}
