package com.application.chitfunds.controller;


import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.application.chitfunds.entites.Request;
import com.application.chitfunds.entites.Response;
import com.application.chitfunds.entites.SlabsEntity;
import com.application.chitfunds.entites.TemporaryGroup;
import com.application.chitfunds.entites.TemporaryGroupMapping;
import com.application.chitfunds.repository.TemporaryGroupRepo;
import com.application.chitfunds.service.TemporaryGroupService;
import com.mongodb.lang.Nullable;

@CrossOrigin("*")
@RestController
public class TemporaryGroupController {

    @Autowired
    TemporaryGroupService service;

    @Autowired
    TemporaryGroupRepo repo;

    @RequestMapping(value = "getTemporaryGroupCount", method = RequestMethod.GET)
    public Long getReceiptCount() {
        return repo.count();
    }

    @RequestMapping(value = "createTemporaryGroup", method = RequestMethod.POST)
    public Response createGroup(@RequestBody TemporaryGroup grp) {
        Response res = new Response();
        try {
            TemporaryGroup group = grp;

            SlabsEntity slab = service.findSlabById(group.getSchemeId());
            if (slab != null) {
                int count = slab.getInstallment() - 1;
                group.setVacantCount(count);
            }

            if (service.createTemporaryGroup(group)) {
                res.setResponseCode(200);
                res.setResponseMessage("Temporary Group created successfully...");
            } else {
                res.setResponseCode(201);
                res.setResponseMessage("Temporary Group not created");
            }

        } catch (Exception e) {
            res.setResponseCode(300);
            res.setResponseMessage("error during group creation... Exception : " + e.getMessage());
        }
        return res;
    }

    @RequestMapping(value = "getAllTemporaryGroup", method = RequestMethod.GET)
    public Response getAllGroup(@RequestParam("page") @Nullable Integer page,
                                @RequestParam("size") @Nullable Integer size,
                                @RequestParam("scheme") @Nullable String scheme,
                                @RequestParam("register") @Nullable String registerType,
                                @RequestParam("auctionDate") @Nullable Integer auctionDate,
                                @RequestParam("startDate") @Nullable String startDate) {
        Response res = new Response();
        Request req = new Request();
        try {
            if (scheme != null) req.setSchemeId(scheme);
            if (auctionDate != null) req.setAutionDate(auctionDate);
            if (registerType != null) req.setRegiterType(registerType);
            if (startDate != null) req.setStartDate(startDate);
            if (page != null && size != null) {
                req.setSize(size);
                req.setPage(page - 1);
            }

            res.setObject(service.getAllTemporaryGroups(req));
            res.setResponseCode(200);
            res.setResponseMessage("Successfully got the Temporary Group list");
        } catch (Exception e) {
            res.setErrorCode(400);
            res.setErrorMessage("error during fetch Temporary Group Data ");
        }
        return res;
    }

    @RequestMapping(value = "getTemporaryGroupById", method = RequestMethod.GET)
    public Response getGroupById(@RequestParam("id") String id) {
        Response res = new Response();
        try {
            TemporaryGroup response = service.getTemporaryGroupById(id);

            if (response != null) {
                res.setObject(response);
            } else {
                throw new IOException("Temporary Group not found...");
            }

            res.setResponseCode(200);
            res.setResponseMessage("Successfully Got the temporary group details");
        } catch (Exception e) {
            res.setErrorCode(400);
            res.setErrorMessage("error during get Temporary Group Data ");
        }
        return res;
    }

    @RequestMapping(value = "deleteTemporaryGroupById", method = RequestMethod.DELETE)
    public Response deleteGroupById(@RequestParam("id") String id) {
        Response res = new Response();
        try {
            service.deleteTemporaryGroup(id);
            res.setResponseCode(200);
            res.setResponseMessage("Successfully deleted the Temporary Group details");
        } catch (Exception e) {
            res.setErrorCode(400);
            res.setErrorMessage("error during delete Temporary Group Data ");
        }
        return res;
    }

    @RequestMapping(value = "createTemporaryGroupMapping", method = RequestMethod.POST)
    public Response createCustomerGroupMapping(@RequestBody TemporaryGroupMapping map) {
        Response res = new Response();
        try {
            if (service.createTemporaryMapping(map)) {
                res.setResponseCode(200);
                res.setResponseMessage("Temporary Group mapped successfully...");
            } else {
                res.setResponseCode(201);
                res.setResponseMessage("Temporary Group Mapping is not created");
            }

        } catch (Exception e) {
            res.setResponseCode(300);
            res.setResponseMessage("error during Temporary Group mapping creation... Exception : " + e.getMessage());
        }
        return res;
    }

    @RequestMapping(value = "getAllTemporaryMap", method = RequestMethod.GET)
    public Response getAllMapDetails() {
        Response res = new Response();
        try {
            res.setObject(service.listTemporaryMappings());
            res.setResponseCode(200);
            res.setResponseMessage("Successfully got the Temporary Mapping list");
        } catch (Exception e) {
            res.setErrorCode(400);
            res.setErrorMessage("error during fetch Temporary Mapped Data ");
        }
        return res;
    }

    @RequestMapping(value = "getTemporaryMapById", method = RequestMethod.GET)
    public Response getMapById(@RequestParam("id") String id) {
        Response res = new Response();
        try {
            TemporaryGroupMapping response = service.getTemporaryMapById(id);

            if (response != null) {
                res.setObject(response);
            } else {
                throw new IOException("Temporary Mapping not found...");
            }

            res.setResponseCode(200);
            res.setResponseMessage("Successfully Got the Temporary Group map details");
        } catch (Exception e) {
            res.setErrorCode(400);
            res.setErrorMessage("error during get Temporary Map Data ");
        }
        return res;
    }

    @RequestMapping(value = "deleteTemporaryMapById", method = RequestMethod.DELETE)
    public Response deleteMapById(@RequestParam("id") String id) {
        Response res = new Response();
        try {
            service.deleteTemporaryMapById(id);
            res.setResponseCode(200);
            res.setResponseMessage("Successfully deleted the Temporary Map details");
        } catch (Exception e) {
            res.setErrorCode(400);
            res.setErrorMessage("error during delete Temporary Map Data ");
        }
        return res;
    }

    @RequestMapping(value = "updateTemporaryGroupMap", method = RequestMethod.PUT)
    public Response updateGroupCustomerMapping(@RequestBody TemporaryGroupMapping map) {
        Response res = new Response();
        try {
            if (service.updateTemporaryMapDetails(map)) {
                res.setResponseCode(200);
                res.setResponseMessage("Successfully updated the Temporary Map details");
            } else {
                res.setResponseCode(201);
                res.setResponseMessage("Issue while updating the Temporary Map details");
            }
        } catch (Exception e) {
            res.setErrorCode(400);
            res.setErrorMessage("error during update Temporary Map Data ");
        }
        return res;
    }

    @RequestMapping(value = "getAllTemporarySlabs", method = RequestMethod.GET)
    public Response getAllSlabs() {
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

    @RequestMapping(value = "findTemporarySlabById", method = RequestMethod.GET)
    public Response findSlabById(@RequestParam("slabId") String id) {
        Response res = new Response();
        try {
            res.setObject(service.findSlabById(id));
            res.setResponseCode(200);
            res.setResponseMessage("Successfully got the slab");
        } catch (Exception e) {
            res.setErrorCode(400);
            res.setErrorMessage("error during fetch slab by id");
        }
        return res;
    }
}