package com.application.chitfunds.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.application.chitfunds.entites.CustomerChitDetails;
import com.application.chitfunds.entites.Customers;
import com.application.chitfunds.repository.CustomerRepo;
import com.application.chitfunds.service.CustomerService;

@CrossOrigin("*")
@RestController
public class CorrectionController {

	@Autowired
	CustomerService custService;
	
	@Autowired
	CustomerRepo repo;
	
	@RequestMapping(value = "updateSchemeIdForCustomers", method = RequestMethod.POST)
	public String updateSchemeIdtoCustomers(@RequestBody List<String> customerIds) {
		List<Customers> customerUpdatedList = new ArrayList<Customers>();
		List<String> customerIdList = new ArrayList<String>();
		customerIdList.addAll(customerIds);
		Boolean updateFlag = false;
		try {
			System.out.println("userID list size : "+customerIdList.size());
			for (String id : customerIdList) {
				Customers customerData = custService.getCustomersById(id);
				if (customerData != null) {
					System.out.println("Customer Name" + customerData.getPersonalDetails().getName() + " for the Id : "
							+ customerData.get_id());
					
					for (CustomerChitDetails chitDetails : customerData.getCustomerChitDetails()) {
						switch (chitDetails.getScheme()) {
						case "20000":
							chitDetails.setSchemeId("64529be9a14e6f67c4b175c0");
							updateFlag = true;
							break;
						case "10000":
							chitDetails.setSchemeId("64529b80a14e6f67c4b175bf");
							updateFlag = true;
							break;
						case "50000":
							chitDetails.setSchemeId("64529e85a14e6f67c4b175c1");
							updateFlag = true;
							break;
						case "100000":
							chitDetails.setSchemeId("6452bc08a14e6f67c4b175c6");
							updateFlag = true;
							break;
						case "1000000":
							chitDetails.setSchemeId("647660594cda635f8120980c");
							updateFlag = true;
							break;
						case "300000":
							chitDetails.setSchemeId("6452bdf8a14e6f67c4b175c9");
							updateFlag = true;
							break;
						case "200000":
							chitDetails.setSchemeId("6452bd25a14e6f67c4b175c8");
							updateFlag = true;
							break;
						case "500000":
							chitDetails.setSchemeId("6452bee1a14e6f67c4b175ca");
							updateFlag = true;
							break;
						case "600000":
							chitDetails.setSchemeId("6476604b4cda635f8120980b");
							updateFlag = true;
							break;
						}
					}
					customerUpdatedList.add(customerData);
				}
			}
			System.out.println("customer to update list size : "+customerUpdatedList.size());
			if(updateFlag) {
				repo.saveAll(customerUpdatedList);
			}
			return "Successfully done...."; 
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}

	}
}
