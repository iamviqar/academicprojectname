package com.property.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.property.entity.PropertyMaintenanceFile;

@Repository
public interface PropertyMaintenanceFileRepo extends CrudRepository<PropertyMaintenanceFile, Long> {

	public List<PropertyMaintenanceFile> findAll();

	public PropertyMaintenanceFile findOneById(Long id);

}
