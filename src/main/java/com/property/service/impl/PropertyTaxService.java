package com.property.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.property.assembler.PropertyTaxAssembler;
import com.property.dao.PropertyTaxFileRepo;
import com.property.dao.PropertyTaxRepo;
import com.property.entity.File;
import com.property.entity.PropertyTax;
import com.property.entity.PropertyTaxFile;
import com.property.model.FileDescription;
import com.property.model.PropertyTaxVO;
import com.property.service.IPropertyTaxService;

@Service
public class PropertyTaxService implements IPropertyTaxService {

	@Autowired
	PropertyTaxRepo propertyTaxRepo;
	
	@Autowired
	PropertyTaxFileRepo propertyTaxFileRepo;
	
	@Autowired
	PropertyTaxAssembler propertyTaxAssembler;
	
	
	@Override
	public PropertyTax save(PropertyTax propertyTax) throws Exception {	
		try {
			propertyTaxRepo.save(propertyTax);		
		}catch(Exception e) {
			throw e;
		}
		return propertyTax;
	}

	
	@Override
	public PropertyTax update(PropertyTax party) throws Exception {	
		try {
			propertyTaxRepo.save(party);		
		}catch(Exception e) {
			throw e;
		}
		return party;
	}


	@Override
	public List<PropertyTax> getAll() throws Exception {
		List<PropertyTax> propertyTaxList = null;
		try {
			propertyTaxList = propertyTaxRepo.findAll();
		}catch(Exception e) {
			throw e;
		}
		return propertyTaxList;
	}
	
	@Override
	public List<PropertyTaxVO> getAllVos() throws Exception {
		List<PropertyTax> propertyTaxList = null;
		List<PropertyTaxVO> propertyTaxVOList = null;
		try {
			propertyTaxList = propertyTaxRepo.findAll();
			propertyTaxVOList= propertyTaxAssembler.getVOs(propertyTaxList);
		}catch(Exception e) {
			throw e;
		}
		return propertyTaxVOList;
	}
	
	@Override
	@Transactional(rollbackFor = Throwable.class)
	public PropertyTaxVO save(PropertyTaxVO propertyTaxVO) throws Exception {	
		try {
			propertyTaxRepo.save(propertyTaxVO.getPropertyTax());
			if(propertyTaxVO.getFiles()!=null) {
				for(FileDescription fileDescription : propertyTaxVO.getFiles()) {
					PropertyTaxFile propertyTaxFile = new PropertyTaxFile();
					File file = new File();
					file.setId(fileDescription.getId());
					propertyTaxFile.setFile(file);
					propertyTaxFile.setPropertyTax(propertyTaxVO.getPropertyTax());
					propertyTaxFile.setDescription(fileDescription.getDescription());
					propertyTaxFileRepo.save(propertyTaxFile);
				}	
			}
	
			
		}catch(Exception e) {
			throw e;
		}
		return propertyTaxVO;
	}

	
	@Override
	@Transactional(rollbackFor = Throwable.class)
	public PropertyTaxVO update(PropertyTaxVO propertyTaxVO) throws Exception {	
		try {
			PropertyTax propertyTax = propertyTaxRepo.findOneById(propertyTaxVO.getPropertyTax().getId());
			List<PropertyTaxFile>  propertyTaxFiles = new ArrayList<PropertyTaxFile>();
			for(PropertyTaxFile propertyTaxFile:propertyTax.getPropertyTaxFiles()) {
				propertyTaxFiles.add(propertyTaxFile);
			}
			propertyTaxRepo.save(propertyTaxVO.getPropertyTax());
			

			// add new files
			if(propertyTaxVO.getFiles()!=null) {
			for(FileDescription fileDescription : propertyTaxVO.getFiles()) {
				Boolean addFlag = true;
				for(PropertyTaxFile propertyTaxFile : propertyTaxFiles) {
					if(propertyTaxFile.getFile().getId()==fileDescription.getId()) {
						addFlag = false;
						break;
					}
				}
				if(addFlag) {
					PropertyTaxFile propertyTaxFile = new PropertyTaxFile();
					File file = new File();
					file.setId(fileDescription.getId());
					propertyTaxFile.setFile(file);
					propertyTaxFile.setPropertyTax(propertyTaxVO.getPropertyTax());
					propertyTaxFile.setDescription(fileDescription.getDescription());
					propertyTaxFileRepo.save(propertyTaxFile);
				}	
			}
			}
			
			// delete removed files
			for(PropertyTaxFile propertyTaxFile : propertyTaxFiles) {
				Boolean deleteFlag = true;
				for(FileDescription fileDescription : propertyTaxVO.getFiles()) {
					if(propertyTaxFile.getFile().getId()==fileDescription.getId()) {
						deleteFlag = false;
						break;
					}
				}
				if(deleteFlag) {
				propertyTaxFileRepo.deleteById(propertyTaxFile.getId());	
				}
			}
		
		}catch(Exception e) {
			throw e;
		}
		return propertyTaxVO;
	}

	
	
}
