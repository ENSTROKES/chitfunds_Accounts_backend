package com.application.chitfunds.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.application.chitfunds.entites.DocumentsModel;

public interface DocumentRepo extends MongoRepository<DocumentsModel, String>{
	Optional<DocumentsModel> findByName(String name);
	Optional<List<DocumentsModel>> findByCustomerId(Long customerId);
}
