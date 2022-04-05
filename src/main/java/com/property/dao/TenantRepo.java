package com.property.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.property.entity.Tenant;

@Repository
public interface TenantRepo extends CrudRepository<Tenant, Long> {
	
	public List<Tenant> findAll();
	
	public Tenant findOneById(Long id);
	
	public Tenant findByCode(String code);

}
