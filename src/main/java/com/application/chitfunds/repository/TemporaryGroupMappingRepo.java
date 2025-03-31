package com.application.chitfunds.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.application.chitfunds.entites.TemporaryGroupMapping;

public interface TemporaryGroupMappingRepo extends MongoRepository<TemporaryGroupMapping, String> {
    List<TemporaryGroupMapping> findByGroupId(String groupId);
    List<TemporaryGroupMapping> findByCustomerId(String customerId);
}
