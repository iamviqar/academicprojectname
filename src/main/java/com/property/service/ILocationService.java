package com.property.service;

import java.util.List;

import com.property.entity.Location;

public interface ILocationService {

	public List<Location> getAll() throws Exception;
	public Location getById(Long id) throws Exception;
	public List<Location> getByCity(Long cityId) throws Exception;
	Location save(Location city) throws Exception;
	
}
