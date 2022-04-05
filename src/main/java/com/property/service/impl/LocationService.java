package com.property.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.property.dao.LocationRepo;
import com.property.entity.Location;
import com.property.service.ILocationService;

@Service
public class LocationService implements ILocationService {

	@Autowired
	LocationRepo locationRepo;
	
	@Override
	public Location getById(Long id) throws Exception {
		Location location = null;
		try {
			location = locationRepo.findOneById(id);
		}catch(Exception e) {
			throw e;
		}
		return location;
	}

	@Override
	public Location save(Location location) throws Exception {
		try {
			locationRepo.save(location);
		}catch(Exception e) {
			throw e;
		}
		return location;
	}


	@Override
	public List<Location> getAll() throws Exception {
		List<Location> properties = null;
		try {
			properties = locationRepo.findAll();
		}catch(Exception e) {
			throw e;
		}
		return properties;
	}

	@Override
	public List<Location> getByCity(Long cityId) throws Exception {
		List<Location> cities = null;
		try {
			cities = locationRepo.findByCityId(cityId);
		}catch(Exception e) {
			throw e;
		}
		return cities;
	}

}
