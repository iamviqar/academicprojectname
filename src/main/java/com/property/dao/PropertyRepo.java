package com.property.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.property.entity.Property;

@Repository
public interface PropertyRepo extends  CrudRepository<Property, Long> {
	
	public List<Property> findAll();
	
	public Property findOneById(Long id);
	
	public Property findByCode(String code);
	
	@Query("select a.property from Agreement a where a.tenant.id = ?1")
	public List<Property> findByTenant(Long tenantId);
	

}
