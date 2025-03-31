package com.application.chitfunds.service;

import java.util.List;

import com.application.chitfunds.entites.TemporaryGroup;
import com.application.chitfunds.entites.TemporaryGroupMapping;
import com.application.chitfunds.entites.Request;

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
}
