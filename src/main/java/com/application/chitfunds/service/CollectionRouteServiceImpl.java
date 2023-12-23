package com.application.chitfunds.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

import com.application.chitfunds.SequenceGenerator;
import com.application.chitfunds.entites.CollectionRouteModel;
import com.application.chitfunds.entites.Request;
import com.application.chitfunds.repository.CollectionRouteRepo;
import com.application.chitfunds.util.Constant;

@Service
public class CollectionRouteServiceImpl implements CollectionRouteService {

	@Autowired
	SequenceGenerator sequenceGenerator;
	
	@Autowired
	MongoOperations mongoOperations;
	
	@Autowired
	CollectionRouteRepo collectionRepo;
	
	public Boolean saveRoute(CollectionRouteModel model) {
		CollectionRouteModel route = model;
		try {
			if(route.get_id()==null) {
				route.setCollectionId(sequenceGenerator.generateSequence(Constant.COLLECTION_ROUTE_SEQUENCE));
			}
			route.setCreatedDate(new Date().getTime());
			route.setUpdatedDate(new Date().getTime());
			CollectionRouteModel result = collectionRepo.save(route);
			if (result != null)
				return true;
			else
				return false;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("error During save Collection Route : " + e.getMessage());
			return false;
		}
		
	}

	public List<CollectionRouteModel> getAll(Request req) {
		List<CollectionRouteModel> result = collectionRepo.findAll();
		return result;
	}

	public CollectionRouteModel getByid(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
