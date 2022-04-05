package com.property.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.property.entity.Material;

@Repository
public interface MaterialRepo extends CrudRepository<Material, Long> {
	
	public List<Material> findAll();
	
}
