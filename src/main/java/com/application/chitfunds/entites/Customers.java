package com.application.chitfunds.entites;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

//@Entity
@Document(collection = "customer")
public class Customers {

	private String _id;
	private Long customerId;
	private String customerGenId;
	private String branchName;
	private String joiningDate;
	private Boolean checked =false;
	// private String guarantor;
	private String referedType;
	private String referedBy;
	private Boolean isMappedToGroup = false;
	private Long createdDate;
//	@OneToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "customerPersonalId")
	private CustomerPersonalDetails personalDetails;

//	@OneToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "customerNomineeId")
	private CustomerNomineeDetails customerNomineeDetails;

	// @OneToMany(cascade = CascadeType.ALL)
	private List<CustomerChitDetails> customerChitDetails;
	private String branchCode;
	
	
	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public String getCustomerGenId() {
		return customerGenId;
	}

	public void setCustomerGenId(String customerGenId) {
		this.customerGenId = customerGenId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	/*
	 * public String getGuarantor() { return guarantor; }
	 * 
	 * public void setGuarantor(String guarantor) { this.guarantor = guarantor; }
	 */

	public String get_id() {
		return _id;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public CustomerPersonalDetails getPersonalDetails() {
		return personalDetails;
	}

	public void setPersonalDetails(CustomerPersonalDetails personalDetails) {
		this.personalDetails = personalDetails;
	}

	public CustomerNomineeDetails getCustomerNomineeDetails() {
		return customerNomineeDetails;
	}

	public void setCustomerNomineeDetails(CustomerNomineeDetails customerNomineeDetails) {
		this.customerNomineeDetails = customerNomineeDetails;
	}

	public List<CustomerChitDetails> getCustomerChitDetails() {
		return customerChitDetails;
	}

	public void setCustomerChitDetails(List<CustomerChitDetails> customerChitDetails) {
		this.customerChitDetails = customerChitDetails;
	}

	public Boolean getIsMappedToGroup() {
		return isMappedToGroup;
	}

	public void setIsMappedToGroup(Boolean isMappedToGroup) {
		this.isMappedToGroup = isMappedToGroup;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(String joiningDate) {
		this.joiningDate = joiningDate;
	}

	public String getReferedType() {
		return referedType;
	}

	public void setReferedType(String referedType) {
		this.referedType = referedType;
	}

	public String getReferedBy() {
		return referedBy;
	}

	public void setReferedBy(String referedBy) {
		this.referedBy = referedBy;
	}

	public Long getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Long createdDate) {
		this.createdDate = createdDate;
	}

}
