package com.application.chitfunds.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.application.chitfunds.entites.CollectionRouteModel;
import com.application.chitfunds.entites.Request;

public interface CollectionRouteService {

	public Boolean saveRoute(CollectionRouteModel model); 
	public List<CollectionRouteModel> getAll(Request req);
	public CollectionRouteModel getByid(String id);
	
}
