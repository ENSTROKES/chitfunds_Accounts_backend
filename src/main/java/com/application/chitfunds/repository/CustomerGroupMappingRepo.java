package com.application.chitfunds.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.application.chitfunds.entites.CustomerGroupMapping;

public interface CustomerGroupMappingRepo extends MongoRepository<CustomerGroupMapping, String>{
	List<CustomerGroupMapping> findByGroupId(String groupId);
}
