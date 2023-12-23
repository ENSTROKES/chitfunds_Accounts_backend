package com.application.chitfunds.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.application.chitfunds.SequenceGenerator;
import com.application.chitfunds.entites.Employee;
import com.application.chitfunds.entites.LoginUsers;
import com.application.chitfunds.entites.Request;
import com.application.chitfunds.entites.Response;
import com.application.chitfunds.repository.EmployeeRepo;
import com.application.chitfunds.repository.LogInUserRepo;
import com.application.chitfunds.service.EmployeeService;
import com.application.chitfunds.util.Constant;
import com.application.chitfunds.validation.EmployeeValidation;

@CrossOrigin("*")
@RestController
public class EmployeeController {

	@Autowired
	EmployeeService empServiec;
	
	@Autowired
	SequenceGenerator sequenceGenerator;
	
	@Autowired
	LogInUserRepo loginUserRepo;

	@Autowired
	private EmployeeRepo employeeRepo;

	@Autowired
	private EmployeeValidation validate;

	/*
	 * @RequestMapping(value = "/testing", method = RequestMethod.GET) public
	 * List<Employee> test() { List<Employee> employee = new ArrayList<>(); try {
	 * employee = employeeRepo.findByEmail("test@gmial.com");
	 * 
	 * } catch (Exception e) { System.out.println(e.getMessage()); }
	 * 
	 * return employee; }
	 */

	@RequestMapping(value = "createEmployee", method = RequestMethod.POST)
	public Response createEmployee(@RequestBody Employee request) {
		Response res = new Response();
		Employee emp = new Employee();
		try {
			emp = request;

			if (validate.validateEmployee(emp) && employeeRepo.findByEmail(request.getEmail()).size() > 0) {
				res.setResponseCode(200);
				res.setResponseMessage("Employee data already Exist");
			} else {
				Boolean flag = empServiec.saveEmployee(emp);
				if (flag) {
					res.setResponseCode(200);
					res.setResponseMessage("Successfully save the employee details");
				} else {
					res.setResponseCode(200);
					res.setResponseMessage("error during save employee Data ");
				}
			}
		} catch (Exception e) {
			res.setErrorCode(500);
			res.setErrorMessage(e.getMessage());
		}
		return res;
	}

	@RequestMapping(value = "getAllEmployee", method = RequestMethod.GET)
	public Response getAllEmployee() {
		Response res = new Response();

		try {
			res.setObject(empServiec.getAllEmployee());
			res.setResponseCode(200);
			res.setResponseMessage("Successfully Got the all employee details");
		} catch (Exception e) {
			res.setErrorCode(400);
			res.setErrorMessage("error during save employee Data ");
		}
		return res;
	}

	@RequestMapping(value = "getEmployeeById", method = RequestMethod.GET)
	public Response getEmployeeById(@RequestParam("id") String empId) {
		Response res = new Response();
		List<Employee> empList = new ArrayList<>();

		try {
			Employee empResponse = empServiec.getEmployeeById(empId);
			if (!empResponse.equals(null)) {
				res.setObject(empResponse);
			} else {
				throw new IOException("Employee details not found...");
			}

			res.setResponseCode(200);
			res.setResponseMessage("Successfully Got the employee details");
		} catch (Exception e) {
			res.setErrorCode(400);
			res.setErrorMessage("error during save employee Data ");
		}
		return res;
	}

	@RequestMapping(value = "deleteEmployeeById", method = RequestMethod.DELETE)
	public Response deleteEmployeeById(@RequestParam("id") String empId) {
		Response res = new Response();
		try {
			empServiec.deleteEmployeeById(empId);
			res.setResponseCode(200);
			res.setResponseMessage("Successfully deleted the employee details");
		} catch (Exception e) {
			res.setErrorCode(400);
			res.setErrorMessage("error during delete employee Data ");
		}
		return res;
	}

	@RequestMapping(value = "updateEmployee", method = RequestMethod.PUT)
	public Response updateEmployee(@RequestBody Employee emp) {
		Response res = new Response();
		try {
			if (empServiec.updateEmployee(emp)) {
				res.setResponseCode(200);
				res.setResponseMessage("Successfully updated the employee details");
			} else {
				res.setResponseCode(200);
				res.setResponseMessage("Issue while updated the employee details");
			}
		} catch (Exception e) {
			res.setErrorCode(400);
			res.setErrorMessage("error during update employee Data ");
		}
		return res;
	}

	@RequestMapping(value = "userLogin", method = RequestMethod.GET)
	public Response usrLoginInValidation(@RequestParam("userName") String userName,
			@RequestParam("passWord") String pass) {
		Response res = new Response();
		try {
			List<LoginUsers> users = loginUserRepo.findByUserName(userName);
			if (users != null && !users.isEmpty()) {
				for (LoginUsers user : users) {
					String password = user.getPassWord();
					if (pass.equals(password)) {
						res.setResponseCode(200);
						res.setResponseMessage("User Logged in successfully...");
					} else {
						res.setResponseCode(201);
						res.setResponseMessage("Password wrong");
					}
				}
			}else {
				res.setResponseCode(201);
				res.setResponseMessage("user not found");
			}
		} catch (Exception e) {
			res.setResponseCode(201);
			res.setResponseMessage("user not found");
			res.setErrorMessage("error : " + e.getMessage());
		}

		return res;
	}
	
	@RequestMapping(value = "createUser", method = RequestMethod.POST)
	public Response userCreation(@RequestBody LoginUsers user) {
		Response res = new Response();
		try {
		if(user.getUserId()==null) {
			user.setUserId(sequenceGenerator.generateSequence(Constant.LOGIN_SEQUENCE));
		}
		LoginUsers userResult=  loginUserRepo.save(user);
		res.setObject(userResult);
		res.setResponseCode(200);
		}catch(Exception e) {
			res.setResponseCode(201);
			res.setResponseMessage(e.getMessage());
		}
		
		return res;
	}
	
}
