package com.application.chitfunds.service;

import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.application.chitfunds.entites.CustomerGroupMapRequest;
import com.application.chitfunds.entites.CustomerGroupMapping;
import com.application.chitfunds.entites.Customers;
import com.application.chitfunds.entites.Request;

public interface CustomerService {
	public Customers saveCustomer(Customers cust);
	public List<Customers> getAllCustomers(Integer page, Integer size);
	public List<Customers> getAllCustomers(String slabId);
	public List<Customers> getAllCustomers(Request req);
	public Customers getCustomersById(String custId);
	public Boolean updateCustomers(Customers cust);
	public Boolean deleteCustomersById(String custId);
	public Boolean saveMappedData(CustomerGroupMapRequest req);
	public  List<CustomerGroupMapping> getMappedData(String groupId);
	public List<Customers> getCustomersListForMap(String slabId);
	
	void init();
    public String saveFile(MultipartFile file, Long id);
    public Resource loadFile(String fileName);
	public List<CustomerGroupMapping> getMappedCustomerByGroupAndCustomerId(String groupId, String customerId, String ticketNum );
	public List<Customers> getCustomerBySlabDetails(String slabId);

}
