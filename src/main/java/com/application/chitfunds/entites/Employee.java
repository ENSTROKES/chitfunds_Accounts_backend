package com.application.chitfunds.entites;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "employee")
public class Employee {
	

	private Long emp_code;
	private String _id;
	private String branch_name;
	private String salution;
	private String first_name;
	private String last_name;
	private String gender;
	private String DOB;
	private String blood_group;
	private String mobile_number;
	private String email;
	private String aadhar_no;
	private String pan_no;
	private String father_name;
	private String spouse_name;
	private String designation;
	private String qalification;
	private String joining_date;
	private Integer Experience;
	private String verified_by;
	private String pervious_salary;
	private String bank_name;
	private String account_holder_name;
	private String account_number;
	private String IFSC_code;
	private Double salary;
	private Double incentive;
	private Integer target;
	private String address;
	private String pincode;
	private String state;
	private String distric;
	private String city;
	private String landmark;
	private String Remarks;
	private Long createdDate;
	private Long lastUpdatedDate;

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public Long getEmp_code() {
		return emp_code;
	}

	public void setEmp_code(Long emp_code) {
		this.emp_code = emp_code;
	}

	public String getBranch_name() {
		return branch_name;
	}

	public void setBranch_name(String branch_name) {
		this.branch_name = branch_name;
	}

	public String getSalution() {
		return salution;
	}

	public void setSalution(String salution) {
		this.salution = salution;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDOB() {
		return DOB;
	}

	public void setDOB(String dOB) {
		DOB = dOB;
	}

	public String getBlood_group() {
		return blood_group;
	}

	public void setBlood_group(String blood_group) {
		this.blood_group = blood_group;
	}

	public String getMobile_number() {
		return mobile_number;
	}

	public void setMobile_number(String mobile_number) {
		this.mobile_number = mobile_number;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAadhar_no() {
		return aadhar_no;
	}

	public void setAadhar_no(String aadhar_no) {
		this.aadhar_no = aadhar_no;
	}

	public String getPan_no() {
		return pan_no;
	}

	public void setPan_no(String pan_no) {
		this.pan_no = pan_no;
	}

	public String getFather_name() {
		return father_name;
	}

	public void setFather_name(String father_name) {
		this.father_name = father_name;
	}

	public String getSpouse_name() {
		return spouse_name;
	}

	public void setSpouse_name(String spouse_name) {
		this.spouse_name = spouse_name;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getQalification() {
		return qalification;
	}

	public void setQalification(String qalification) {
		this.qalification = qalification;
	}

	public Integer getExperience() {
		return Experience;
	}

	public void setExperience(Integer experience) {
		Experience = experience;
	}

	public String getVerified_by() {
		return verified_by;
	}

	public void setVerified_by(String verified_by) {
		this.verified_by = verified_by;
	}

	public String getPervious_salary() {
		return pervious_salary;
	}

	public void setPervious_salary(String pervious_salary) {
		this.pervious_salary = pervious_salary;
	}

	public String getBank_name() {
		return bank_name;
	}

	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}

	public String getAccount_holder_name() {
		return account_holder_name;
	}

	public void setAccount_holder_name(String account_holder_name) {
		this.account_holder_name = account_holder_name;
	}

	public String getAccount_number() {
		return account_number;
	}

	public void setAccount_number(String account_number) {
		this.account_number = account_number;
	}

	public String getIFSC_code() {
		return IFSC_code;
	}

	public void setIFSC_code(String iFSC_code) {
		IFSC_code = iFSC_code;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public Double getIncentive() {
		return incentive;
	}

	public void setIncentive(Double incentive) {
		this.incentive = incentive;
	}

	public Integer getTarget() {
		return target;
	}

	public void setTarget(Integer target) {
		this.target = target;
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

	public String getDistric() {
		return distric;
	}

	public void setDistric(String distric) {
		this.distric = distric;
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
		return Remarks;
	}

	public void setRemarks(String remarks) {
		Remarks = remarks;
	}
	
	

	public String getJoining_date() {
		return joining_date;
	}

	public void setJoining_date(String joining_date) {
		this.joining_date = joining_date;
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

	@Override
	public String toString() {	
		return "Employee [emp_code=" + emp_code + ", branch_name=" + branch_name + ", salution=" + salution
				+ ", first_name=" + first_name + ", last_name=" + last_name + ", gender=" + gender + ", DOB=" + DOB
				+ ", blood_group=" + blood_group + ", mobile_number=" + mobile_number + ", email=" + email
				+ ", aadhar_no=" + aadhar_no + ", pan_no=" + pan_no + ", father_name=" + father_name + ", spouse_name="
				+ spouse_name + ", designation=" + designation + ", qalification=" + qalification + ", joining_date="
				+ joining_date + ", Experience=" + Experience + ", verified_by=" + verified_by + ", pervious_salary="
				+ pervious_salary + ", bank_name=" + bank_name + ", account_holder_name=" + account_holder_name
				+ ", account_number=" + account_number + ", IFSC_code=" + IFSC_code + ", salary=" + salary
				+ ", incentive=" + incentive + ", target=" + target + ", address=" + address + ", pincode=" + pincode
				+ ", state=" + state + ", distric=" + distric + ", city=" + city + "]";
	}

}
