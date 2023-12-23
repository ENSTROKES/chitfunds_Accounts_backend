package com.application.chitfunds.service;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.application.chitfunds.entites.Branch;
import com.application.chitfunds.entites.Request;

@Repository
public interface BranchService {
	public Boolean saveBranch(Branch branch);
	public List<Branch> getAllBranch();
	public List<Branch> getAllBranch(Request req);
	public Branch getBranchById(String id);
	public Boolean updateBranch(Branch branch);
	public Boolean deleteBranchById(String id);
	public List<Branch> getAllHeadOffice();

}
