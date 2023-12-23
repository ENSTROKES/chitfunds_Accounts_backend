package com.application.chitfunds.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.chitfunds.entites.Employee;
import com.application.chitfunds.repository.EmployeeRepo;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepo employeeRepo;

	@Override
	public Boolean saveEmployee(Employee emp) {
		Employee employee = emp;
		try {
			employee.setCreatedDate(new Date().getTime());
			employee.setLastUpdatedDate(new Date().getTime());
			Employee result = employeeRepo.save(employee);
			if (result != null)
				return true;
			else
				return false;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("error During save employee: " + e.getMessage());
			return false;
		}

	}

	@Override
	public List<Employee> getAllEmployee() {
		List<Employee> empList = employeeRepo.findAll();
		return empList;
	}

	@Override
	public Employee getEmployeeById(String empId) {
		Optional<Employee> emp = employeeRepo.findById(empId);
		if (emp.isPresent())
			return emp.get();
		else
			return null;
	}

	@Override
	public Boolean updateEmployee(Employee emp) {

		Employee empData = new Employee();
		Boolean flag = false;

		try {
			Optional<Employee> opt = employeeRepo.findById(emp.get_id());
			if (opt.isPresent()) {
				empData = opt.get();
			}
			if (!empData.equals(null)) {
				employeeRepo.save(setData(emp, empData));
				flag = true;
			}
		} catch (Exception e) {

		}
		return flag;
	}

	@Override
	public Boolean deleteEmployeeById(String empId) {
		try {
			employeeRepo.deleteById(empId);
			return true;
		} catch (Exception e) {

			return false;
		}

	}

	public Employee setData(Employee emp, Employee employee) {

		try {
			employee.setAccount_holder_name(emp.getAccount_holder_name());
			employee.setAccount_number(emp.getAccount_number());
			employee.setAddress(emp.getAddress());
			employee.setBank_name(emp.getBank_name());
			employee.setBranch_name(emp.getBranch_name());
			employee.setCity(emp.getCity());
			employee.setDesignation(emp.getDesignation());
			employee.setDistric(emp.getDistric());
			employee.setExperience(emp.getExperience());
			employee.setFather_name(emp.getFather_name());
			employee.setIFSC_code(emp.getIFSC_code());
			employee.setIncentive(emp.getIncentive());
			employee.setMobile_number(emp.getMobile_number());
			employee.setPan_no(emp.getPan_no());
			employee.setPervious_salary(emp.getPervious_salary());
			employee.setPincode(emp.getPincode());
			employee.setRemarks(emp.getRemarks());
			employee.setSalary(emp.getSalary());
			employee.setSalution(emp.getSalution());
			employee.setSpouse_name(emp.getSpouse_name());
			employee.setVerified_by(emp.getVerified_by());
			employee.setTarget(emp.getTarget());
			employee.setLastUpdatedDate(new Date().getTime());

		} catch (Exception e) {

		}
		return employee;

	}

}
