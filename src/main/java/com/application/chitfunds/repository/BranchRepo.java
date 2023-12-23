package com.application.chitfunds.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.application.chitfunds.entites.Branch;

public interface BranchRepo extends MongoRepository<Branch, String>{
	List<Branch> findByHeadOffice(boolean bol);
}
