package com.property.service;

import java.util.List;

import com.property.entity.Material;

public interface IMaterialService {

	public List<Material> getAll() throws Exception;

	Material save(Material material) throws Exception;
	
}
