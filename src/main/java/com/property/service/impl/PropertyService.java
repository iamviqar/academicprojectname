package com.property.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.property.assembler.AgreementAssembler;
import com.property.assembler.PropertyAssembler;
import com.property.assembler.PropertyMaintenanceAssembler;
import com.property.assembler.PropertyTaxAssembler;
import com.property.dao.AgreementRepo;
import com.property.dao.FileRepo;
import com.property.dao.PropertyFileRepo;
import com.property.dao.PropertyMaintenanceRepo;
import com.property.dao.PropertyRepo;
import com.property.dao.PropertyTaxRepo;
import com.property.entity.File;
import com.property.entity.Property;
import com.property.entity.PropertyFile;
import com.property.model.FileDescription;
import com.property.model.PropertyDetailsVO;
import com.property.model.PropertyVO;
import com.property.service.IPropertyService;

@Service
public class PropertyService implements IPropertyService {

	@Autowired
	PropertyRepo propertyRepo;
	
	@Autowired
	AgreementRepo agreementRepo;
	
	@Autowired
	PropertyFileRepo propertyFileRepo;
	
	@Autowired
	FileRepo fileRepo;
	
	@Autowired
	PropertyMaintenanceRepo propertyMaintenanceRepo;
	
	@Autowired
	PropertyTaxRepo propertyTaxRepo;
	
	@Autowired
	PropertyAssembler propertyAssembler;
	
	@Autowired
	AgreementAssembler agreementAssembler;
	
	@Autowired
	PropertyMaintenanceAssembler propertyMaintenanceAssembler;
	
	@Autowired
	PropertyTaxAssembler propertyTaxAssembler;
	
	
	
	@Override
	public Property getById(Long id) throws Exception {
		Property property = null;
		try {
			property = propertyRepo.findOneById(id);
		}catch(Exception e) {
			throw e;
		}
		return property;
	}
	
	@Override
	@Transactional(rollbackFor = Throwable.class)
	public PropertyVO save(PropertyVO propertyVO) throws Exception {	
		try {
			propertyRepo.save(propertyVO.getProperty());
			if(propertyVO.getFiles()!=null) {
				for(FileDescription fileDescription : propertyVO.getFiles()) {
					PropertyFile propertyFile = new PropertyFile();
					File file = new File();
					file.setId(fileDescription.getId());
					propertyFile.setFile(file);
					propertyFile.setProperty(propertyVO.getProperty());
					propertyFile.setDescription(fileDescription.getDescription());
					propertyFileRepo.save(propertyFile);
				}	
			}
	
			
		}catch(Exception e) {
			throw e;
		}
		return propertyVO;
	}

	
	@Override
	@Transactional(rollbackFor = Throwable.class)
	public PropertyVO update(PropertyVO propertyVO) throws Exception {	
		try {
			Property property = propertyRepo.findOneById(propertyVO.getProperty().getId());
			List<PropertyFile>  propertyFiles = new ArrayList<PropertyFile>();
			for(PropertyFile propertyFile:property.getPropertyFiles()) {
				propertyFiles.add(propertyFile);
			}
			propertyRepo.save(propertyVO.getProperty());
			

			// add new files
			if(propertyVO.getFiles()!=null) {
			for(FileDescription fileDescription : propertyVO.getFiles()) {
				Boolean addFlag = true;
				for(PropertyFile propertyFile : propertyFiles) {
					if(propertyFile.getFile().getId()==fileDescription.getId()) {
						addFlag = false;
						break;
					}
				}
				if(addFlag) {
					PropertyFile propertyFile = new PropertyFile();
					File file = new File();
					file.setId(fileDescription.getId());
					propertyFile.setFile(file);
					propertyFile.setProperty(propertyVO.getProperty());
					propertyFile.setDescription(fileDescription.getDescription());
					propertyFileRepo.save(propertyFile);
				}	
			}
			}
			
			// delete removed files
			for(PropertyFile propertyFile : propertyFiles) {
				Boolean deleteFlag = true;
				for(FileDescription fileDescription : propertyVO.getFiles()) {
					if(propertyFile.getFile().getId()==fileDescription.getId()) {
						deleteFlag = false;
						break;
					}
				}
				if(deleteFlag) {
				propertyFileRepo.deleteById(propertyFile.getId());	
				}
			}
		
		}catch(Exception e) {
			throw e;
		}
		return propertyVO;
	}

	
	@Override
	public Property getByCode(String code) throws Exception{
		Property property = null;
		try {
			property = propertyRepo.findByCode(code);
		}catch(Exception e) {
			throw e;
		}
		return property;
	}

	@Override
	public List<Property> getAll() throws Exception {
		List<Property> properties = null;
		try {
			properties = propertyRepo.findAll();
		}catch(Exception e) {
			throw e;
		}
		return properties;
	}
	
	@Override
	public List<PropertyVO> getAllWithFiles() throws Exception {
		List<PropertyVO> propertyVOList = null;
		try {
			List<Property> properties = propertyRepo.findAll();
			propertyVOList = propertyAssembler.getVO(properties);
		}catch(Exception e) {
			throw e;
		}
		return propertyVOList;
	}
	
	
	@Override
	public PropertyVO getWithFiles(Long propertyId) throws Exception {
		PropertyVO propertyVO = null;
		try {
			Property property = propertyRepo.findOneById(propertyId);
			propertyVO = propertyAssembler.getVO(property);
		}catch(Exception e) {
			throw e;
		}
		return propertyVO;
	}

	@Override
	public List<Property> getByTenant(Long tenantId) throws Exception {
		List<Property> propertyList = null;
		try {
			propertyList = propertyAssembler.get(propertyRepo.findByTenant(tenantId));
		}catch(Exception e) {
			throw e;
		}
		return propertyList;
	}

	@Override
	public PropertyDetailsVO getPropertyDetails(Long propertyId) throws Exception {
		PropertyDetailsVO propertyDetailsVO = new PropertyDetailsVO();
		try {
			propertyDetailsVO.setProperty(this.getWithFiles(propertyId));
			propertyDetailsVO.setAgreementList(agreementAssembler.get(agreementRepo.findByIsActiveAndPropertyId(true, propertyId)));
			propertyDetailsVO.setPropertyMaintenanceList(propertyMaintenanceAssembler.getVO(propertyMaintenanceRepo.findByPropertyId(propertyId)));
			propertyDetailsVO.setPropertyTaxList(propertyTaxAssembler.getVOs(propertyTaxRepo.findByPropertyId(propertyId)));
		}catch(Exception e) {
			throw e;
		}
		return propertyDetailsVO;
	}

}
