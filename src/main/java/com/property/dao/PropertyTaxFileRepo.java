package com.property.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.property.entity.PropertyTaxFile;

@Repository
public interface PropertyTaxFileRepo extends CrudRepository<PropertyTaxFile, Long> {

	public List<PropertyTaxFile> findAll();

	public PropertyTaxFile findOneById(Long id);

}
