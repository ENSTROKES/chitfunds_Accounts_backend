package com.application.chitfunds.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.application.chitfunds.entites.TemporaryGroup;

public interface TemporaryGroupRepo extends MongoRepository<TemporaryGroup, String> {

}