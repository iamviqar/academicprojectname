package com.property.service;

import java.util.List;

import com.property.entity.City;

public interface ICityService {

	public List<City> getAll() throws Exception;
	public City getById(Long id) throws Exception;
	public List<City> getByStateId(Long stateId) throws Exception;
	City save(City city) throws Exception;
	
}
