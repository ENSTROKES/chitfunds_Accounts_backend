package com.application.chitfunds.entites;

public class CustomerNomineeDetails {

	private Long nomineeId;
	private String name;
	//private String DOB;
	private String relationship;
	//private String nominee_address;
	private Long createdDate;
	private String adharNumber;
	
	public Long getNomineeId() {
		return nomineeId;
	}
	public void setNomineeId(Long nomineeId) {
		this.nomineeId = nomineeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	/*
	 * public String getDOB() { return DOB; } public void setDOB(String dOB) { DOB =
	 * dOB; }
	 */
	public String getRelationship() {
		return relationship;
	}
	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}

	/*
	 * public String getNominee_address() { return nominee_address; } public void
	 * setNominee_address(String nominee_address) { this.nominee_address =
	 * nominee_address; }
	 */
	public Long getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Long createdDate) {
		this.createdDate = createdDate;
	}
	
	public String getAdharNumber() {
		return adharNumber;
	}
	public void setAdharNumber(String adharNumber) {
		this.adharNumber = adharNumber;
	}
	@Override
	public String toString() {
		return "CustomerNomineeDetails [nomineeId=" + nomineeId + ", name=" + name + ", relationship="
				+ relationship + ", createdDate=" + createdDate + "]";
	}
	
	
}
