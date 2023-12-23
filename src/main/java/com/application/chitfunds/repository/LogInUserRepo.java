package com.application.chitfunds.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.application.chitfunds.entites.LoginUsers;

public interface LogInUserRepo extends MongoRepository<LoginUsers, Long>{
				
	List<LoginUsers> findByUserName(String userName);
	

}
