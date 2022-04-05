package com.property.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.property.dao.CityRepo;
import com.property.entity.City;
import com.property.service.ICityService;

@Service
public class CityService implements ICityService {

	@Autowired
	CityRepo cityRepo;
	
	@Override
	public City getById(Long id) throws Exception {
		City city = null;
		try {
			city = cityRepo.findOneById(id);
		}catch(Exception e) {
			throw e;
		}
		return city;
	}

	@Override
	public City save(City city) throws Exception {
		try {
			cityRepo.save(city);
		}catch(Exception e) {
			throw e;
		}
		return city;
	}


	@Override
	public List<City> getAll() throws Exception {
		List<City> properties = null;
		try {
			properties = cityRepo.findAll();
		}catch(Exception e) {
			throw e;
		}
		return properties;
	}

	@Override
	public List<City> getByStateId(Long stateId) throws Exception {
		List<City> cities = null;
		try {
			cities = cityRepo.findByStateId(stateId);
		}catch(Exception e) {
			throw e;
		}
		return cities;
	}

}
