package com.property.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.property.entity.PropertyTax;

@Repository
public interface PropertyTaxRepo extends CrudRepository<PropertyTax, Long> {
	
	public List<PropertyTax> findAll();
	public List<PropertyTax> findByPropertyId(Long propertyId);
	public PropertyTax findOneById(Long id);
	
	@Query("SELECT pt.paidYear FROM PropertyTax pt WHERE pt.property.id = :propertyId")
	public List<Integer> findYearsByPropertyId(@Param("propertyId") Long propertyId);
	
}
