package com.property.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.property.entity.PropertyMaintenance;

@Repository
public interface PropertyMaintenanceRepo extends CrudRepository<PropertyMaintenance, Long> {
	
	public List<PropertyMaintenance> findAll();
	public List<PropertyMaintenance> findByPropertyId(Long propertyId);
	public PropertyMaintenance findOneById(Long id);
	
}
