package com.application.chitfunds.service;

import java.util.List;

import com.application.chitfunds.entites.TemporaryGroup;
import com.application.chitfunds.entites.TemporaryGroupMapping;
import com.application.chitfunds.entites.Request;
import com.application.chitfunds.entites.SlabsEntity;

public interface TemporaryGroupService {
    
    List<TemporaryGroup> getAllTemporaryGroups();
    List<TemporaryGroup> getAllTemporaryGroups(Request req);
    TemporaryGroup getTemporaryGroupById(String id);
    Boolean createTemporaryGroup(TemporaryGroup group);
    Boolean deleteTemporaryGroup(String id);

    Boolean createTemporaryMapping(TemporaryGroupMapping mapDetail);
    List<TemporaryGroupMapping> listTemporaryMappings();
    TemporaryGroupMapping getTemporaryMapById(String id);
    Boolean updateTemporaryMapDetails(TemporaryGroupMapping map);
    Boolean deleteTemporaryMapById(String id);
    
    public Boolean createSlab(SlabsEntity s);
	public List<SlabsEntity> getAllSlabs();
	public SlabsEntity findSlabById(String id);
}
