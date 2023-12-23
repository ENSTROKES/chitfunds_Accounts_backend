package com.application.chitfunds.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.application.chitfunds.entites.Customers;

public interface CustomerRepo extends MongoRepository<Customers, String>{
	Long countByCustomerGenId();
	 //public List<Customers> findCustomersOrderByJoining_Date();
	//phoneNumber
	//List<Customers> findByPersonalDetails(Long );
	//List<Customers> findAllBySchemId(Long schemeId);
}
