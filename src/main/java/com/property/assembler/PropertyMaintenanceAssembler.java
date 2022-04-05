package com.property.assembler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.property.entity.PropertyMaintenance;
import com.property.entity.PropertyMaintenanceFile;
import com.property.model.FileDescription;
import com.property.model.PropertyMaintenanceVO;

@Component
public class PropertyMaintenanceAssembler {

	@Autowired
	PropertyAssembler propertyAssembler;
	
	
	public PropertyMaintenance get(PropertyMaintenance propertyMaintenance) {
		PropertyMaintenance updatedPropertyMaintenance = new PropertyMaintenance();
		updatedPropertyMaintenance.setAmount(propertyMaintenance.getAmount());
		updatedPropertyMaintenance.setDescription(propertyMaintenance.getDescription());
		updatedPropertyMaintenance.setId(propertyMaintenance.getId());
		updatedPropertyMaintenance.setPaidDate(propertyMaintenance.getPaidDate());
		updatedPropertyMaintenance.setPaymentMode(propertyMaintenance.getPaymentMode());
		updatedPropertyMaintenance.setProperty(propertyAssembler.get(propertyMaintenance.getProperty()));
		return updatedPropertyMaintenance;	
	}
	
	public PropertyMaintenanceVO getVO(PropertyMaintenance propertyMaintenance) {
		PropertyMaintenanceVO propertyMaintenanceVO = new PropertyMaintenanceVO();
		List<FileDescription> files = null;
		if(propertyMaintenance.getPropertyMaintenanceFiles()!=null && !propertyMaintenance.getPropertyMaintenanceFiles().isEmpty()) {
			files = new ArrayList<FileDescription>();
		}
		propertyMaintenanceVO.setPropertyMaintenance(this.get(propertyMaintenance));
		for(PropertyMaintenanceFile propertyFile : propertyMaintenance.getPropertyMaintenanceFiles()) {
			FileDescription fileDescription = new FileDescription();	
			fileDescription.setId(propertyFile.getFile().getId());
			fileDescription.setDescription(propertyFile.getDescription());
			fileDescription.setName(propertyFile.getFile().getName());
			files.add(fileDescription);
		}	
		propertyMaintenanceVO.setFiles(files);
		return propertyMaintenanceVO;	
	}
	
	public List<PropertyMaintenanceVO> getVO(List<PropertyMaintenance> propertyMaintenanceList){
		List<PropertyMaintenanceVO> propertyMaintenanceVOList = null;
		if(propertyMaintenanceList!=null && !propertyMaintenanceList.isEmpty()) {
			propertyMaintenanceVOList = new ArrayList<PropertyMaintenanceVO>();
		}
		for(PropertyMaintenance propertyMaintenance :propertyMaintenanceList) {
			propertyMaintenanceVOList.add(this.getVO(propertyMaintenance));
		}
		return propertyMaintenanceVOList;
	}
	
}
