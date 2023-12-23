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

import com.application.chitfunds.entites.GroupEntity;
import com.application.chitfunds.entites.Request;
import com.application.chitfunds.entites.Response;
import com.application.chitfunds.entites.SlabsEntity;
import com.application.chitfunds.repository.GroupRepo;
import com.application.chitfunds.service.GroupService;

@CrossOrigin("*")
@RestController
public class GroupController {

	@Autowired
	GroupService service;

	@Autowired
	GroupRepo repo;
	
	@RequestMapping(value = "getGroupCount", method = RequestMethod.GET)
	public Long getReceiptCount() {
		Long total = 0L;
		total = repo.count();
		//System.out.println("total customer count : "+ total);
		return total;
	}
	
	@RequestMapping(value = "createGroup", method = RequestMethod.POST)
	public Response createGroup(@RequestBody GroupEntity grp) {
		Response res = new Response();
		try {
			GroupEntity group = grp;
			SlabsEntity slab = service.findSlabById(group.getSchemeId());
			int count = slab.getInstallment() - 1;
			group.setVacantCount(count);

			if (service.createGroup(group)) {
				res.setResponseCode(200);
				res.setResponseMessage("Group created successfully...");
			} else {
				res.setResponseCode(201);
				res.setResponseMessage("Group not created");
			}

		} catch (Exception e) {
			System.out.println();
			res.setResponseCode(300);
			res.setResponseMessage("error during group creation... Exception : " + e.getMessage());
		}
		return res;
	}

	@RequestMapping(value = "getAllGroup", method = RequestMethod.GET)
	public Response getAllGroup(@RequestParam("page") @Nullable Integer page,
			@RequestParam("size") @Nullable Integer size, @RequestParam("scheme") @Nullable String scheme,
			@RequestParam("register") @Nullable String registerType,
			@RequestParam("auctionDate") @Nullable Integer auctionDate, 
			@RequestParam("startDate") @Nullable String startDate) {
		Response res = new Response();
		Request req = new Request();
		try {
			if (scheme != null) {
				req.setSchemeId(scheme);
			}
			
			if (auctionDate != null) {
				req.setAutionDate(auctionDate);
			}
			if (registerType != null) {
				req.setRegiterType(registerType);
			}
			if (startDate != null) {
				req.setStartDate(startDate);
			}
			if (page != null && size != null) {
				req.setSize(size);
				req.setPage(page-1);
			} 			
			res.setObject(service.getAllGroupList(req));
			res.setResponseCode(200);
			res.setResponseMessage("Successfully got the Group list");
		} catch (Exception e) {
			res.setErrorCode(400);
			res.setErrorMessage("error during fetch Group Data ");
		}
		return res;
	}

	@RequestMapping(value = "getGroupById", method = RequestMethod.GET)
	public Response getGroupById(@RequestParam("id") String id) {
		Response res = new Response();
		try {

			GroupEntity response = service.getGroupById(id);

			if (!response.equals(null)) {
				res.setObject(response);
			} else {
				throw new IOException("Group not found...");
			}

			res.setResponseCode(200);
			res.setResponseMessage("Successfully Got the group details");
		} catch (Exception e) {
			res.setErrorCode(400);
			res.setErrorMessage("error during get Group Data ");
		}
		return res;
	}

	@RequestMapping(value = "deleteGroupById", method = RequestMethod.DELETE)
	public Response deleteGroupById(@RequestParam("id") String id) {
		Response res = new Response();
		try {
			service.deleteGroup(id);
			res.setResponseCode(200);
			res.setResponseMessage("Successfully deleted the Group details");
		} catch (Exception e) {
			res.setErrorCode(400);
			res.setErrorMessage("error during delete Group Data ");
		}
		return res;
	}

	/*
	 * @RequestMapping(value = "createCustomerGroupMapping", method =
	 * RequestMethod.POST) public Response createCustomerGroupMapping(@RequestBody
	 * CustomerGroupMapping map) { Response res = new Response(); try {
	 * CustomerGroupMapping group = map;
	 * 
	 * if(service.createMapping(group)) { res.setResponseCode(200);
	 * res.setResponseMessage("Customer Group mapped successfully..."); }else {
	 * res.setResponseCode(201);
	 * res.setResponseMessage("Customer Group Mapping is not created"); }
	 * 
	 * }catch(Exception e) { System.out.println(); res.setResponseCode(300);
	 * res.setResponseMessage("error during Customer Group creation... Exception : "
	 * +e.getMessage()); } return res; }
	 * 
	 * 
	 * @RequestMapping(value = "getAllMap", method = RequestMethod.GET) public
	 * Response getAllMapDetails() { Response res = new Response();
	 * 
	 * try { res.setObject(service.listMapping()); res.setResponseCode(200);
	 * res.setResponseMessage("Successfully got the Map list"); } catch (Exception
	 * e) { res.setErrorCode(400);
	 * res.setErrorMessage("error during fetch Mapped Data "); } return res; }
	 * 
	 * @RequestMapping(value = "getMapById", method = RequestMethod.GET) public
	 * Response getMapById(@RequestParam("id") Long id) { Response res = new
	 * Response(); try {
	 * 
	 * CustomerGroupMapping response = service.getMapById(id);
	 * 
	 * if (!response.equals(null)) { res.setObject(response); } else { throw new
	 * IOException("Map not found..."); }
	 * 
	 * res.setResponseCode(200);
	 * res.setResponseMessage("Successfully Got the customer Group map details"); }
	 * catch (Exception e) { res.setErrorCode(400);
	 * res.setErrorMessage("error during get map Data "); } return res; }
	 * 
	 * @RequestMapping(value = "deleteMapById", method = RequestMethod.DELETE)
	 * public Response deleteMapById(@RequestParam("id") Long id) { Response res =
	 * new Response(); try { service.deleteMapById(id); res.setResponseCode(200);
	 * res.setResponseMessage("Successfully deleted the Map details"); } catch
	 * (Exception e) { res.setErrorCode(400);
	 * res.setErrorMessage("error during delete Map Data "); } return res; }
	 * 
	 * @RequestMapping(value = "updatecustomerGroupMap", method = RequestMethod.PUT)
	 * public Response updateGroupCustomerMapping(@RequestBody CustomerGroupMapping
	 * map) { Response res = new Response(); try { if
	 * (service.updateMapDetails(map)) { res.setResponseCode(200);
	 * res.setResponseMessage("Successfully updated the map details"); } else {
	 * res.setResponseCode(200);
	 * res.setResponseMessage("Issue while updated the map details"); } } catch
	 * (Exception e) { res.setErrorCode(400);
	 * res.setErrorMessage("error during update employee Data "); } return res; }
	 */

	@RequestMapping(value = "createSlab", method = RequestMethod.POST)
	public Response createSlab(@RequestBody SlabsEntity slb) {
		Response res = new Response();
		try {
			SlabsEntity slab = slb;

			if (service.createSlab(slab)) {
				res.setResponseCode(200);
				res.setResponseMessage("Slab created successfully...");
			} else {
				res.setResponseCode(201);
				res.setResponseMessage("Slab not created");
			}

		} catch (Exception e) {
			System.out.println();
			res.setResponseCode(300);
			res.setResponseMessage("error during slab creation... Exception : " + e.getMessage());
		}
		return res;
	}

	@RequestMapping(value = "getAllSlab", method = RequestMethod.GET)
	public Response getAllSlab() {
		Response res = new Response();

		try {
			res.setObject(service.getAllSlabs());
			res.setResponseCode(200);
			res.setResponseMessage("Successfully got the slab list");
		} catch (Exception e) {
			res.setErrorCode(400);
			res.setErrorMessage("error during fetch Slab Data ");
		}
		return res;
	}

	@RequestMapping(value = "findSlabById", method = RequestMethod.GET)
	public Response getAllSlab(@RequestParam("slabId") String id) {
		Response res = new Response();

		try {
			res.setObject(service.findSlabById(id));
			res.setResponseCode(200);
			res.setResponseMessage("Successfully got the slab list");
		} catch (Exception e) {
			res.setErrorCode(400);
			res.setErrorMessage("error during fetch Slab Data ");
		}
		return res;
	}

}
