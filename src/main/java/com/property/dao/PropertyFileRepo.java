package com.property.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.property.entity.PropertyFile;

@Repository
public interface PropertyFileRepo extends CrudRepository<PropertyFile, Long> {

	public List<PropertyFile> findAll();

	public PropertyFile findOneById(Long id);

}
