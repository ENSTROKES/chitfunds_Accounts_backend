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
import com.application.chitfunds.entites.TemporaryGroup;
import com.application.chitfunds.entites.TemporaryGroupMapping;
import com.application.chitfunds.entites.Request;
import com.application.chitfunds.repository.TemporaryGroupRepo;
import com.application.chitfunds.repository.TemporaryGroupMappingRepo;
import com.application.chitfunds.util.Constant;

@Service
public class TemporaryGroupServiceImpl implements TemporaryGroupService {

    @Autowired
    private TemporaryGroupRepo repo;

    @Autowired
    private TemporaryGroupMappingRepo mapRepo;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private SequenceGenerator sequenceGenerator;

    @Override
    public List<TemporaryGroup> getAllTemporaryGroups() {
        return repo.findAll();
    }

    @Override
    public List<TemporaryGroup> getAllTemporaryGroups(Request req) {
        Query query = new Query();

        if (req.getSchemeId() != null) {
            query.addCriteria(Criteria.where("schemeId").is(req.getSchemeId()));
        }
        if (req.getStartDate() != null) {
            query.addCriteria(Criteria.where("startingDate").is(req.getStartDate()));
        }
        if (req.getRegiterType() != null) {
            query.addCriteria(Criteria.where("groupType").is(req.getRegiterType()));
        }
        if (req.getAutionDate() != null) {
            query.addCriteria(Criteria.where("lauctionDate").is(req.getAutionDate()));
        }

        if (req.getPage() != null && req.getSize() != null) {
            Pageable paging = PageRequest.of(req.getPage(), req.getSize());
            query.with(paging);
        }

        return mongoTemplate.find(query, TemporaryGroup.class);
    }

    @Override
    public TemporaryGroup getTemporaryGroupById(String id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Boolean createTemporaryGroup(TemporaryGroup group) {
        try {
            if (group.getGroupId() == null) {
                group.setGroupId(sequenceGenerator.generateSequence(Constant.TEMP_GROUP_SEQUENCE));
            }
            group.setCreatedDate(new Date().getTime());
            group.setLastUpdatedDate(new Date().getTime());
            return repo.save(group) != null;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean deleteTemporaryGroup(String id) {
        try {
            repo.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean createTemporaryMapping(TemporaryGroupMapping mapDetail) {
        try {
            mapRepo.save(mapDetail);
            return true;
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<TemporaryGroupMapping> listTemporaryMappings() {
        return mapRepo.findAll();
    }

    @Override
    public TemporaryGroupMapping getTemporaryMapById(String id) {
        return mapRepo.findById(id).orElse(null);
    }

    @Override
    public Boolean updateTemporaryMapDetails(TemporaryGroupMapping map) {
        Optional<TemporaryGroupMapping> existingMap = mapRepo.findById(String.valueOf(map.getMappingId()));
        if (existingMap.isPresent()) {
            TemporaryGroupMapping updatedMap = setMapData(map, existingMap.get());
            mapRepo.save(updatedMap);
            return true;
        }
        return false;
    }

    private TemporaryGroupMapping setMapData(TemporaryGroupMapping newMap, TemporaryGroupMapping oldMap) {
        oldMap.setCollectionPincode(newMap.getCollectionPincode());
        oldMap.setCustomerId(newMap.getCustomerId());
        oldMap.setCustomerName(newMap.getCustomerName());
        oldMap.setGroupId(newMap.getGroupId());
        oldMap.setGroupName(newMap.getGroupName());
        oldMap.setUpdatedDate(new Date().getTime());
        return oldMap;
    }

    @Override
    public Boolean deleteTemporaryMapById(String id) {
        try {
            mapRepo.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
