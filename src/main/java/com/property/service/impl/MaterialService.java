package com.property.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.property.dao.MaterialRepo;
import com.property.entity.Material;
import com.property.service.IMaterialService;

@Service
public class MaterialService implements IMaterialService {

	@Autowired
	MaterialRepo prodEntityRepo;
	

	@Override
	public Material save(Material prodEntity) throws Exception {
		try {
			prodEntityRepo.save(prodEntity);
		}catch(Exception e) {
			throw e;
		}
		return prodEntity;
	}


	@Override
	public List<Material> getAll() throws Exception {
		List<Material> properties = null;
		try {
			properties = prodEntityRepo.findAll();
		}catch(Exception e) {
			throw e;
		}
		return properties;
	}

}
