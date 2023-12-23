package com.application.chitfunds.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.application.chitfunds.entites.Branch;
import com.application.chitfunds.entites.CollectionRouteModel;
import com.application.chitfunds.entites.Request;
import com.application.chitfunds.entites.Response;
import com.application.chitfunds.repository.BranchRepo;
import com.application.chitfunds.service.BranchService;
import com.application.chitfunds.service.CollectionRouteService;

@CrossOrigin("*") 
@RestController
public class BranchController {
	
	@Autowired
	BranchService branchService;
	
	@Autowired
	BranchRepo branchRepo;
	
	@Autowired
	CollectionRouteService routeService;
	
	
	@RequestMapping(value = "getBranchCount", method = RequestMethod.GET)
	public Long getReceiptCount() {
		Long total = 0L;
		total = branchRepo.count();
		//System.out.println("total customer count : "+ total);
		return total;
	}
	
	@RequestMapping(value = "createBranch", method = RequestMethod.POST)
	public Response createBranch(@RequestBody Branch branch) {
		Response res = new Response();
		try {
		Branch branchAdd = branch;
		if(branchAdd.getHeadOffice()) {
			branchAdd.setHeadOffice(false);
		}else {
			branchAdd.setHeadOffice(true);
		}
		if(branchService.saveBranch(branchAdd)) {
		res.setResponseCode(200);
		res.setResponseMessage("Branch created successfully...");
		}else {
			res.setResponseCode(201);
			res.setResponseMessage("Branch not created");
		}
		
		}catch(Exception e) {
			System.out.println();
			res.setResponseCode(300);
			res.setResponseMessage("error duringbranch creation... Exception : "+e.getMessage());
		}
		return res;
	}
	
	@RequestMapping(value = "getAllBranches", method = RequestMethod.GET)
	public Response getAllBranch(@RequestParam("page") @Nullable Integer page,
			@RequestParam("size") @Nullable Integer size, @RequestParam("headOffice") @Nullable Boolean type) {
		Response res = new Response();
		Request req = new Request();
		try {
			if (type != null) {
				req.setHeadOffice(type);
			}
			if (page != null && size != null) {
				req.setSize(size);
				req.setPage(page-1);
			} 		
			res.setObject(branchService.getAllBranch(req));
			res.setResponseCode(200);
			res.setResponseMessage("Successfully got the branch list");
		} catch (Exception e) {
			res.setErrorCode(400);
			res.setErrorMessage("error during fetch customer Data ");
		}
		return res;
	}

	@RequestMapping(value = "getBranchById", method = RequestMethod.GET)
	public Response getBranchById(@RequestParam("id") String branchId) {
		Response res = new Response();
		try {
			System.out.println("branchId : "+ branchId);
			Branch branchResponse = branchService.getBranchById(branchId);

			if (!branchResponse.equals(null)) {
				res.setObject(branchResponse);
			} else {
				throw new IOException("Branchs not found...");
			}

			res.setResponseCode(200);
			res.setResponseMessage("Successfully Got the branch details");
		} catch (Exception e) {
			res.setErrorCode(400);
			res.setErrorMessage("error during get branch Data ");
		}
		return res;
	}

	@RequestMapping(value = "deleteBranchById", method = RequestMethod.DELETE)
	public Response deleteBranchById(@RequestParam("id") String branchId) {
		Response res = new Response();
		try {
			branchService.deleteBranchById(branchId);
			res.setResponseCode(200);
			res.setResponseMessage("Successfully deleted the branch details");
		} catch (Exception e) {
			res.setErrorCode(400);
			res.setErrorMessage("error during delete branch Data ");
		}
		return res;
	}
	
	@RequestMapping(value = "getAllHeadOffice", method = RequestMethod.GET)
	public Response getAllHeadOffice() {
		Response res = new Response();
		try {
			res.setObject(branchService.getAllHeadOffice());
			res.setResponseCode(200);
			res.setResponseMessage("Head office List fetch successfully");
		} catch (Exception e) {
			res.setErrorCode(400);
			res.setErrorMessage("error during fetching branch Data ");
		}
		return res;
	}
	
	@RequestMapping(value = "updateBranch", method = RequestMethod.POST)
	public Response updateBranch(@RequestBody Branch branch) {
		Response res = new Response();
		try {
		Branch branchAdd = branch;
		if(branchService.updateBranch(branchAdd)) {
		res.setResponseCode(200);
		res.setResponseMessage("Branch updated successfully...");
		}else {
			res.setResponseCode(201);
			res.setResponseMessage("Branch not updated");
		}
		
		}catch(Exception e) {
			System.out.println();
			res.setResponseCode(300);
			res.setResponseMessage("error duringbranch updation... Exception : "+e.getMessage());
		}
		return res;
	}
	
	@RequestMapping(value = "createRoute", method = RequestMethod.POST)
	public Response createBranch(@RequestBody CollectionRouteModel route) {
		Response res = new Response();
		try {
			CollectionRouteModel routemodel = route;
		if(routeService.saveRoute(routemodel)) {
		res.setResponseCode(200);
		res.setResponseMessage("Route created successfully...");
		}else {
			res.setResponseCode(201);
			res.setResponseMessage("Route not created");
		}
		
		}catch(Exception e) {
			System.out.println();
			res.setResponseCode(300);
			res.setResponseMessage("error duringbranch creation... Exception : "+e.getMessage());
		}
		return res;
	}
	
	@RequestMapping(value = "getAllRote", method = RequestMethod.GET)
	public Response getAllRoute(@RequestParam("page") @Nullable Integer page, @RequestParam("size") @Nullable Integer size) {
		Response res = new Response();
		Request req = new Request();
		try {
			
			if (page != null && size != null) {
				req.setSize(size);
				req.setPage(page-1);
			} 		
			res.setObject(routeService.getAll(req));
			res.setResponseCode(200);
			res.setResponseMessage("Successfully got the branch list");
		} catch (Exception e) {
			res.setErrorCode(400);
			res.setErrorMessage("error during fetch customer Data ");
		}
		return res;
	}

	@RequestMapping(value = "getRouteById", method = RequestMethod.GET)
	public Response getRouteById(@RequestParam("id") String id) {
		Response res = new Response();
		try {
			
			CollectionRouteModel routeResponse = routeService.getByid(id);

			if (!routeResponse.equals(null)) {
				res.setObject(routeResponse);
			} else {
				throw new IOException("Collection not found...");
			}

			res.setResponseCode(200);
			res.setResponseMessage("Successfully Got the branch details");
		} catch (Exception e) {
			res.setErrorCode(400);
			res.setErrorMessage("error during get branch Data ");
		}
		return res;
	}

	
}
