package com.application.chitfunds.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.application.chitfunds.SequenceGenerator;
import com.application.chitfunds.entites.CustomerGroupMapping;
import com.application.chitfunds.entites.Customers;
import com.application.chitfunds.entites.GroupEntity;
import com.application.chitfunds.entites.Request;
import com.application.chitfunds.entites.SlabsEntity;
import com.application.chitfunds.repository.CustomerGroupMappingRepo;
import com.application.chitfunds.repository.GroupRepo;
import com.application.chitfunds.repository.SlabsRepo;
import com.application.chitfunds.util.Constant;

@Service
public class GroupServiceImpl implements GroupService{

	@Autowired
	GroupRepo repo;
	
	@Autowired
	SlabsRepo slabRepo;
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	@Autowired
	CustomerGroupMappingRepo mapRepo;
	
	@Autowired
	SequenceGenerator sequenceGenerator;
	
	@Override
	public List<GroupEntity> getAllGroupList() {
		List<GroupEntity> groupList = repo.findAll();
		return groupList;
	}
	
	@Override
	public List<GroupEntity> getAllGroupList(Request req) {
		Query query = new Query();
		
		if (req.getSchemeId() != null) {
			query.addCriteria(new Criteria().where("schemeId").is(req.getSchemeId()));
		}
		if(req.getStartDate()!=null) {
			query.addCriteria(new Criteria().where("startingDate").is(req.getStartDate()));			
		}
		if (req.getRegiterType() != null) {
			query.addCriteria(new Criteria().where("groupType").is(req.getRegiterType()));			
		}
		if (req.getAutionDate() != null) {
			query.addCriteria(new Criteria().where("lauctionDate").is(req.getAutionDate()));
		}
		
		if (req.getPage() != null && req.getSize() != null) {
			Pageable paging = PageRequest.of(req.getPage(), req.getSize());
			query.with(paging);
		}

		List<GroupEntity> groupList = mongoTemplate.find(query, GroupEntity.class);
		//List<GroupEntity> groupList = repo.findAll();
		return groupList;
	}
	@Override
	public GroupEntity getGroupById(String id) {
		Optional<GroupEntity> group = null;
		try{
			group = repo.findById(id);
		}catch(Exception e) {
		System.out.println("error in : "+e.getMessage());
		e.printStackTrace();
		}
		finally {
			if (group.isPresent())
				return group.get();
			else
				return null;
		}
		
	}

	@Override
	public Boolean createGroup(GroupEntity grp) {
		GroupEntity group = grp;
		try {
			if(group.getGroupId()==null) {
				group.setGroupId(sequenceGenerator.generateSequence(Constant.GROUP_SEQUENCE));
			}
			group.setCreatedDate(new Date().getTime());
			group.setLastUpdatedDate(new Date().getTime());
			GroupEntity result = repo.save(group);
			if (result != null)
				return true;
			else
				return false;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("error During save Group: " + e.getMessage());
			return false;
		}
	}

	@Override
	public Boolean deleteGroup(String id) {
		try {
			repo.deleteById(id);
			return true;
		} catch (Exception e) {

			return false;
		}
	}
	
	
	@Override
	public Boolean createMapping(CustomerGroupMapping mapdetail) {
		Boolean flag = false;
		try {
			mapRepo.save(mapdetail);
			flag = true;
		} catch (Exception e) {
			System.out.println("Exception : "+ e.getMessage());
		}
		return flag;
	}

	@Override
	public List<CustomerGroupMapping> listMapping() {
		List<CustomerGroupMapping> mapList = mapRepo.findAll();
		return mapList;
	}

	@Override
	public CustomerGroupMapping getMapById(String mapId) {
		Optional<CustomerGroupMapping> cust = mapRepo.findById(mapId);
		if (cust.isPresent())
			return cust.get();
		else
			return null;
	}

	@Override
	public Boolean updateMapDetails(CustomerGroupMapping map) {

		/*
		 * CustomerGroupMapping mapData = new CustomerGroupMapping(); Boolean flag =
		 * false;
		 * 
		 * try { Optional<CustomerGroupMapping> opt =
		 * mapRepo.findById(map.getMappingId()); if (opt.isPresent()) { mapData =
		 * opt.get(); } if (!mapData.equals(null)) { mapRepo.save(setMapData(map,
		 * mapData)); flag = true; } } catch (Exception e) {
		 * 
		 * } return flag;
		 */
		return null;
	}
	public CustomerGroupMapping setMapData(CustomerGroupMapping newMap, CustomerGroupMapping oldMap) {

		try {
			
//			oldMap.setChit_asking_month(newMap.getChit_asking_month());
//			oldMap.setCollection_area(newMap.getCollection_area());
			oldMap.setCollection_pincode(oldMap.getCollection_pincode());
			oldMap.setCustomerId(newMap.getCustomerId());
			oldMap.setCustomerName(newMap.getCustomerName());
			oldMap.setGroupId(newMap.getGroupId());
			oldMap.setGroupName(newMap.getGroupName());
			//oldMap.setRemarks(newMap.getRemarks());	
			oldMap.setUpdatedDate(new Date().getTime());
		} catch (Exception e) {

		}
		return oldMap;

	}

	@Override
	public Boolean deleteMapById(String id) {
		try {
			mapRepo.deleteById(id);
			return true;
		} catch (Exception e) {

			return false;
		}
	}

	@Override
	public Boolean createSlab(SlabsEntity s) {
		SlabsEntity slab = s;
		try {
			if(slab.getSlabId()==null) {
				slab.setSlabId(sequenceGenerator.generateSequence(Constant.SLAB_SEQUENCE));
			}
			slab.setCreatedDate(new Date().getTime());
			slab.setUpdatedDate(new Date().getTime());
			SlabsEntity result = slabRepo.save(slab);
			if (result != null)
				return true;
			else
				return false;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("error During save Group: " + e.getMessage());
			return false;
		}
	}

	@Override
	public List<SlabsEntity> getAllSlabs() {
		List<SlabsEntity> slbList = slabRepo.findAll();
		return slbList;
	}
	
	@Override
	public SlabsEntity findSlabById(String id) {
		Optional<SlabsEntity> slbList = slabRepo.findById(id);
		if (slbList.isPresent())
			return slbList.get();
		else
			return null;
		
	}
}
