package com.application.chitfunds.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.application.chitfunds.entites.GroupEntity;

public interface GroupRepo extends MongoRepository<GroupEntity, String>{

}
