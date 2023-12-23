package com.application.chitfunds.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.application.chitfunds.entites.SlabsEntity;

public interface SlabsRepo extends MongoRepository<SlabsEntity, String>{
	//List<SlabsEntity> findBySlabId(String id);
	

}
