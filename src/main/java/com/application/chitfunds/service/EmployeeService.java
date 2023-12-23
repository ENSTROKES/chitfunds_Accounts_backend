package com.application.chitfunds.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.application.chitfunds.entites.Employee;

@Repository
public interface EmployeeService {

	public Boolean saveEmployee(Employee emp);
	public List<Employee> getAllEmployee();
	public Employee getEmployeeById(String empId);
	public Boolean updateEmployee(Employee emp);
	public Boolean deleteEmployeeById(String empId);
}
