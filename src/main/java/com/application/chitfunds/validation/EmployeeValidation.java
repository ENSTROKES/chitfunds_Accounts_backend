package com.application.chitfunds.validation;

import org.springframework.stereotype.Repository;

import com.application.chitfunds.entites.Employee;

@Repository
public class EmployeeValidation {
	
	public Boolean validateEmployee(Employee emp) {
		Boolean flag = false;
		if(null != emp.getEmail() && !emp.getEmail().isEmpty()) {
			flag = true;
		}
		
		return flag;
	}

}
