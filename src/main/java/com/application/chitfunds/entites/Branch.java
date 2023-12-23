package com.application.chitfunds.entites;

import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

//@Entity
@Document(collection ="branch")
public class Branch {
	@Transient
    public static final String SEQUENCE_NAME = "branch_sequence";
	
	private Long branchId;
	private String _id;
	private String officeName;
	private String phoneNumber;
	private String emailID;
	private String address;
	private String pincode;
	private String state;
	private String district;
	private String city;
	private String landmark;
	private String remarks;
	private Boolean headOffice;
	private Long createdDate;
	private Long lastUpdatedDate;
	private String headOfficeName;
	private String startUpDate;
	private String branchCode;
	
	

	public String getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public Long getBranchId() {
		return branchId;
	}
	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}
	public String getOfficeName() {
		return officeName;
	}
	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}

	public String getHeadOfficeName() {
		return headOfficeName;
	}
	public void setHeadOfficeName(String headOfficeName) {
		this.headOfficeName = headOfficeName;
	}
	public String getStartUpDate() {
		return startUpDate;
	}
	public void setStartUpDate(String startUpDate) {
		this.startUpDate = startUpDate;
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
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmailID() {
		return emailID;
	}
	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getLandmark() {
		return landmark;
	}
	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Boolean getHeadOffice() {
		return headOffice;
	}
	public void setHeadOffice(Boolean headOffice) {
		this.headOffice = headOffice;
	}
	
	
	
	

}
