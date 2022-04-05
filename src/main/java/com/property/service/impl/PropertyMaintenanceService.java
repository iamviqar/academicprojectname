package com.property.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.property.assembler.PropertyMaintenanceAssembler;
import com.property.dao.PropertyMaintenanceFileRepo;
import com.property.dao.PropertyMaintenanceRepo;
import com.property.entity.File;
import com.property.entity.PropertyMaintenance;
import com.property.entity.PropertyMaintenanceFile;
import com.property.model.FileDescription;
import com.property.model.PropertyMaintenanceVO;
import com.property.service.IPropertyMaintenanceService;

@Service
public class PropertyMaintenanceService implements IPropertyMaintenanceService {

	@Autowired
	PropertyMaintenanceRepo propertyMaintenanceRepo;
	
	@Autowired
	PropertyMaintenanceFileRepo propertyMaintenanceFileRepo;
	
	@Autowired
	PropertyMaintenanceAssembler propertyMaintenanceAssembler;
	
	
	@Override
	public PropertyMaintenance save(PropertyMaintenance propertyMaintenance) throws Exception {	
		try {
			propertyMaintenanceRepo.save(propertyMaintenance);		
		}catch(Exception e) {
			throw e;
		}
		return propertyMaintenance;
	}

	
	@Override
	public PropertyMaintenance update(PropertyMaintenance propertyMaintenance) throws Exception {	
		try {
			propertyMaintenanceRepo.save(propertyMaintenance);		
		}catch(Exception e) {
			throw e;
		}
		return propertyMaintenance;
	}


	@Override
	public List<PropertyMaintenance> getAll() throws Exception {
		List<PropertyMaintenance> propertyMaintenanceList = null;
		try {
			propertyMaintenanceList = propertyMaintenanceRepo.findAll();
		}catch(Exception e) {
			throw e;
		}
		return propertyMaintenanceList;
	}
	
	@Override
	public List<PropertyMaintenanceVO> getAllVos() throws Exception {
		List<PropertyMaintenance> propertyMaintenanceList = null;
		List<PropertyMaintenanceVO> propertyMaintenanceVOList = null;
		try {
			propertyMaintenanceList = propertyMaintenanceRepo.findAll();
			propertyMaintenanceVOList = propertyMaintenanceAssembler.getVO(propertyMaintenanceList);
		}catch(Exception e) {
			throw e;
		}
		return propertyMaintenanceVOList;
	}
	
	@Override
	@Transactional(rollbackFor = Throwable.class)
	public PropertyMaintenanceVO save(PropertyMaintenanceVO propertyMaintenanceVO) throws Exception {	
		try {
			propertyMaintenanceRepo.save(propertyMaintenanceVO.getPropertyMaintenance());
			if(propertyMaintenanceVO.getFiles()!=null) {
				for(FileDescription fileDescription : propertyMaintenanceVO.getFiles()) {
					PropertyMaintenanceFile propertyMaintenanceFile = new PropertyMaintenanceFile();
					File file = new File();
					file.setId(fileDescription.getId());
					propertyMaintenanceFile.setFile(file);
					propertyMaintenanceFile.setPropertyMaintenance(propertyMaintenanceVO.getPropertyMaintenance());
					propertyMaintenanceFile.setDescription(fileDescription.getDescription());
					propertyMaintenanceFileRepo.save(propertyMaintenanceFile);
				}	
			}
	
			
		}catch(Exception e) {
			throw e;
		}
		return propertyMaintenanceVO;
	}

	
	@Override
	@Transactional(rollbackFor = Throwable.class)
	public PropertyMaintenanceVO update(PropertyMaintenanceVO propertyMaintenanceVO) throws Exception {	
		try {
			PropertyMaintenance propertyMaintenance = propertyMaintenanceRepo.findOneById(propertyMaintenanceVO.getPropertyMaintenance().getId());
			List<PropertyMaintenanceFile>  propertyMaintenanceFiles = new ArrayList<PropertyMaintenanceFile>();
			for(PropertyMaintenanceFile propertyMaintenanceFile:propertyMaintenance.getPropertyMaintenanceFiles()) {
				propertyMaintenanceFiles.add(propertyMaintenanceFile);
			}
			propertyMaintenanceRepo.save(propertyMaintenanceVO.getPropertyMaintenance());
			

			// add new files
			if(propertyMaintenanceVO.getFiles()!=null) {
			for(FileDescription fileDescription : propertyMaintenanceVO.getFiles()) {
				Boolean addFlag = true;
				for(PropertyMaintenanceFile propertyMaintenanceFile : propertyMaintenanceFiles) {
					if(propertyMaintenanceFile.getFile().getId()==fileDescription.getId()) {
						addFlag = false;
						break;
					}
				}
				if(addFlag) {
					PropertyMaintenanceFile propertyMaintenanceFile = new PropertyMaintenanceFile();
					File file = new File();
					file.setId(fileDescription.getId());
					propertyMaintenanceFile.setFile(file);
					propertyMaintenanceFile.setPropertyMaintenance(propertyMaintenanceVO.getPropertyMaintenance());
					propertyMaintenanceFile.setDescription(fileDescription.getDescription());
					propertyMaintenanceFileRepo.save(propertyMaintenanceFile);
				}	
			}
			}
			
			// delete removed files
			for(PropertyMaintenanceFile propertyMaintenanceFile : propertyMaintenanceFiles) {
				Boolean deleteFlag = true;
				for(FileDescription fileDescription : propertyMaintenanceVO.getFiles()) {
					if(propertyMaintenanceFile.getFile().getId()==fileDescription.getId()) {
						deleteFlag = false;
						break;
					}
				}
				if(deleteFlag) {
				propertyMaintenanceFileRepo.deleteById(propertyMaintenanceFile.getId());	
				}
			}
		
		}catch(Exception e) {
			throw e;
		}
		return propertyMaintenanceVO;
	}

	
	
}
