package com.application.chitfunds.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.application.chitfunds.entites.CollectionRouteModel;

public interface CollectionRouteRepo extends MongoRepository<CollectionRouteModel, String>{

}
