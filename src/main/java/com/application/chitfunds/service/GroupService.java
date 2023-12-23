package com.application.chitfunds.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.application.chitfunds.entites.CustomerGroupMapping;
import com.application.chitfunds.entites.GroupEntity;
import com.application.chitfunds.entites.Request;
import com.application.chitfunds.entites.SlabsEntity;

@Repository
public interface GroupService {
	
	public List<GroupEntity> getAllGroupList();
	public List<GroupEntity> getAllGroupList(Request req);
	public GroupEntity getGroupById(String id);
	public Boolean createGroup(GroupEntity group);
	public Boolean deleteGroup(String id);
	
	public Boolean createMapping(CustomerGroupMapping mapdetail);
	public List<CustomerGroupMapping> listMapping();
	public CustomerGroupMapping getMapById(String id);
	public Boolean updateMapDetails(CustomerGroupMapping map);
	public Boolean deleteMapById(String id);
	
	public Boolean createSlab(SlabsEntity s);
	public List<SlabsEntity> getAllSlabs();
	public SlabsEntity findSlabById(String id);
	
}
