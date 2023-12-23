package com.application.chitfunds.service;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.*;
import org.springframework.stereotype.Service;
import com.application.chitfunds.SequenceGenerator;
import com.application.chitfunds.entites.Branch;
import com.application.chitfunds.entites.Request;
import com.application.chitfunds.repository.BranchRepo;
import com.application.chitfunds.util.Constant;

@Service
public class BranchServiceImpl implements BranchService{

	@Autowired
	BranchRepo branchRepo;
	
	@Autowired
	SequenceGenerator sequenceGenerator;
	
	@Autowired
	MongoOperations mongoOperations;
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	@Override
	public Boolean saveBranch(Branch branchs) {
		
		Branch branch = branchs;
		try {
			if(branch.getBranchId()==null) {
				branch.setBranchId(sequenceGenerator.generateSequence(Constant.BRANCH_SEQUENCE));
			}
			branch.setCreatedDate(new Date().getTime());
			branch.setLastUpdatedDate(new Date().getTime());
			Branch result = branchRepo.save(branch);
			if (result != null)
				return true;
			else
				return false;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("error During save Branch: " + e.getMessage());
			return false;
		}

	}

	@Override
	public List<Branch> getAllBranch() {
		List<Branch> branchList = branchRepo.findAll();
		return branchList;
	}
	
	@Override
	public List<Branch> getAllBranch(Request req) {
		Query query =new Query();
		try {
		
		if (req.getHeadOffice() != null) {
			query.addCriteria(new Criteria().where("headOffice").is(req.getHeadOffice()));
		}
		
		if (req.getPage() != null && req.getSize() != null) {
			Pageable paging = PageRequest.of(req.getPage(), req.getSize());
			query.with(paging);
		}
		
		List<Branch> branchList = mongoTemplate.find(query, Branch.class);
		return branchList;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	@Override
	public Branch getBranchById(String branchId) {
		Optional<Branch> branch;
		Branch result= null;
		try{
		System.out.println("service branchId : "+ branchId);
		branch = branchRepo.findById(branchId);
		if(branch.isPresent()) {
			result = branch.get();
		}
		//result = mongoOperations.findOne(query(Criteria.where("branchId").is(branchId)),Branch.class);	
			return result;
		}catch(Exception e) {
		System.out.println("error in : "+e.getMessage());
		e.printStackTrace();
		return result; 
		}
				
	}

	@Override
	public Boolean updateBranch(Branch branchs) {
		/*Branch branch = branchs;
		try {
			Update update = new Update();
			update.addToSet("officeName",branch.getOfficeName());
			update.addToSet("phoneNumber",branch.getPhoneNumber());
			update.addToSet("emailID",branch.getEmailID());
			update.addToSet("address",branch.getAddress());
			update.addToSet("pincode",branch.getPincode());
			update.addToSet("state",branch.getState());
			update.addToSet("district",branch.getDistrict());
			update.addToSet("headOffice",branch.getHeadOfficeName());
			update.addToSet("lastUpdatedDate",new Date().getTime());
			
			Branch result = mongoOperations.findAndModify(query(Criteria.where("branchId").is(branch.getBranchId())), 
					update, Branch.class);
			if (result != null)
				return true;
			else
				return false;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("error During save Branch: " + e.getMessage());
			return false;
		}*/
		return null;
	}

	@Override
	public Boolean deleteBranchById(String branchId) {
		try {
			branchRepo.deleteById(branchId);
			return true;
		} catch (Exception e) {

			return false;
		}
	}

	@Override
	public List<Branch> getAllHeadOffice() {
		List<Branch> branchList = branchRepo.findByHeadOffice(true);
		return branchList;
	}

}
