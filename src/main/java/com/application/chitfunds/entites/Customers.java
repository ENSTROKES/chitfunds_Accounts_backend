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
	private Boolean checked = false;
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

	// Address Fields
	private String permanentAddress;
	private String collectionAddress;
	private Boolean isSameAsPermanent;

	// House Ownership & Duration
	private Boolean isOwnHouse;
	private int durationOfStay;

	// Occupation Details
	private String occupation;
	private String occupationAddress;
	private int yearsInOccupation;

	// District & Pincode
	private String district;
	private String pincode;

	// Co-Applicant Details
	private String coApplicantName;
	private String coApplicantRelationship;
	private String coApplicantAadhar;
	private String coApplicantPhone;

	// Nominee Phone Number
	private String nomineePhoneNumber;

	// State & City Selection
	private String state;
	private String city;

	// Chit Commitment
	private Boolean isChitCommitment;
	private String commitmentMonth;
	private String commitmentAmount;

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

	public String getPermanentAddress() {
		return permanentAddress;
	}

	public void setPermanentAddress(String permanentAddress) {
		this.permanentAddress = permanentAddress;
	}

	public String getCollectionAddress() {
		return collectionAddress;
	}

	public void setCollectionAddress(String collectionAddress) {
		this.collectionAddress = collectionAddress;
	}

	public Boolean getIsSameAsPermanent() {
		return isSameAsPermanent;
	}

	public void setIsSameAsPermanent(Boolean isSameAsPermanent) {
		this.isSameAsPermanent = isSameAsPermanent;
	}

	public Boolean getIsOwnHouse() {
		return isOwnHouse;
	}

	public void setIsOwnHouse(Boolean isOwnHouse) {
		this.isOwnHouse = isOwnHouse;
	}

	public int getDurationOfStay() {
		return durationOfStay;
	}

	public void setDurationOfStay(int durationOfStay) {
		this.durationOfStay = durationOfStay;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getOccupationAddress() {
		return occupationAddress;
	}

	public void setOccupationAddress(String occupationAddress) {
		this.occupationAddress = occupationAddress;
	}

	public int getYearsInOccupation() {
		return yearsInOccupation;
	}

	public void setYearsInOccupation(int yearsInOccupation) {
		this.yearsInOccupation = yearsInOccupation;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getCoApplicantName() {
		return coApplicantName;
	}

	public void setCoApplicantName(String coApplicantName) {
		this.coApplicantName = coApplicantName;
	}

	public String getCoApplicantRelationship() {
		return coApplicantRelationship;
	}

	public void setCoApplicantRelationship(String coApplicantRelationship) {
		this.coApplicantRelationship = coApplicantRelationship;
	}

	public String getCoApplicantAadhar() {
		return coApplicantAadhar;
	}

	public void setCoApplicantAadhar(String coApplicantAadhar) {
		this.coApplicantAadhar = coApplicantAadhar;
	}

	public String getCoApplicantPhone() {
		return coApplicantPhone;
	}

	public void setCoApplicantPhone(String coApplicantPhone) {
		this.coApplicantPhone = coApplicantPhone;
	}

	public String getNomineePhoneNumber() {
		return nomineePhoneNumber;
	}

	public void setNomineePhoneNumber(String nomineePhoneNumber) {
		this.nomineePhoneNumber = nomineePhoneNumber;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Boolean getIsChitCommitment() {
		return isChitCommitment;
	}

	public void setIsChitCommitment(Boolean isChitCommitment) {
		this.isChitCommitment = isChitCommitment;
	}

	public String getCommitmentMonth() {
		return commitmentMonth;
	}

	public void setCommitmentMonth(String commitmentMonth) {
		this.commitmentMonth = commitmentMonth;
	}

	public String getCommitmentAmount() {
		return commitmentAmount;
	}

	public void setCommitmentAmount(String commitmentAmount) {
		this.commitmentAmount = commitmentAmount;
	}

}
