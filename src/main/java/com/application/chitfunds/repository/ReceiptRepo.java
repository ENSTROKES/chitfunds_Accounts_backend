package com.application.chitfunds.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.application.chitfunds.entites.Receipt;

public interface ReceiptRepo extends MongoRepository<Receipt, String>{
	List<Receipt> findByCustomerId(String customerId);
}
