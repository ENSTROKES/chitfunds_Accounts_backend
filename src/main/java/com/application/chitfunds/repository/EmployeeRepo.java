package com.application.chitfunds.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.application.chitfunds.entites.Employee;

@Repository
public interface EmployeeRepo extends MongoRepository<Employee, String>{
	
	List<Employee> findByEmail(String email);

}
