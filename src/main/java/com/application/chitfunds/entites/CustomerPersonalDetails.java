package com.application.chitfunds.entites;

public class CustomerPersonalDetails {
	

	private Long customerPersonalId;
	private String name;
	private String father;
	private String spouse_name;
	private String DOB;
	private String aadhar_no;
	private String pan;
	private String gender;
	private String occupation;
	private double monthly_income;
	private String marrital_status;
	private String address;
	private Long pincode;
	private String state;
	private String city;
	private String landmark;
	private String phoneNumber;
	private String email; 
	private String altrPhoneNumber;
	
	
	public Long getCustomerPersonalId() {
		return customerPersonalId;
	}
	public void setCustomerPersonalId(Long customerPersonalId) {
		this.customerPersonalId = customerPersonalId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFather() {
		return father;
	}
	public void setFather(String father) {
		this.father = father;
	}
	public String getSpouse_name() {
		return spouse_name;
	}
	public void setSpouse_name(String spouse_name) {
		this.spouse_name = spouse_name;
	}
	public String getDOB() {
		return DOB;
	}
	public void setDOB(String dOB) {
		DOB = dOB;
	}
	public String getAadhar_no() {
		return aadhar_no;
	}
	public void setAadhar_no(String aadhar_no) {
		this.aadhar_no = aadhar_no;
	}
	public String getPan() {
		return pan;
	}
	public void setPan(String pan) {
		this.pan = pan;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public double getMonthly_income() {
		return monthly_income;
	}
	public void setMonthly_income(double monthly_income) {
		this.monthly_income = monthly_income;
	}
	public String getMarrital_status() {
		return marrital_status;
	}
	public void setMarrital_status(String marrital_status) {
		this.marrital_status = marrital_status;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Long getPincode() {
		return pincode;
	}
	public void setPincode(Long pincode) {
		this.pincode = pincode;
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
	public String getLandmark() {
		return landmark;
	}
	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getAltrPhoneNumber() {
		return altrPhoneNumber;
	}
	public void setAltrPhoneNumber(String altrPhoneNumber) {
		this.altrPhoneNumber = altrPhoneNumber;
	}
	@Override
	public String toString() {
		return "CustomerPersonalDetails [customerPersonalId=" + customerPersonalId + ", name=" + name + ", father="
				+ father + ", spouse_name=" + spouse_name + ", DOB=" + DOB + ", aadhar_no=" + aadhar_no + ", pan=" + pan
				+ ", gender=" + gender + ", occupation=" + occupation + ", monthly_income=" + monthly_income
				+ ", marrital_status=" + marrital_status + ", address=" + address + ", pincode=" + pincode + ", state="
				+ state + ", city=" + city + ", landmark=" + landmark + "]";
	} 

	
	

}
