package com.application.chitfunds.entites;

import java.util.List;

public class CustomerGroupMapRequest {

	private String groupId;
	private List<CustomerDetailsForMapping> customerDetails;
	
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public List<CustomerDetailsForMapping> getCustomerDetails() {
		return customerDetails;
	}
	public void setCustomerDetails(List<CustomerDetailsForMapping> customerDetails) {
		this.customerDetails = customerDetails;
	}

	
	
}
