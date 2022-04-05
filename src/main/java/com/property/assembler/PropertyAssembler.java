package com.property.assembler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.property.entity.Property;
import com.property.entity.PropertyFile;
import com.property.model.FileDescription;
import com.property.model.PropertyVO;

@Component
public class PropertyAssembler {
	
	public Property get(Property property) {
		Property updatedProperty =  new Property();
		updatedProperty.setId(property.getId());
		updatedProperty.setAddress(property.getAddress());
		updatedProperty.setName(property.getName());
		updatedProperty.setDescription(property.getDescription());
		updatedProperty.setCode(property.getCode());
		updatedProperty.setLocation(property.getLocation());
		return updatedProperty;
	}
	
	public List<Property> get(List<Property> properties) {
		List<Property> propertyList = null;
		if(properties!=null && !properties.isEmpty()) {
			propertyList = new ArrayList<Property>();
		}
		for(Property property : properties) {
			propertyList.add(this.get(property));
		}
		return propertyList;
	}
	
	public PropertyVO getVO(Property property) {
		PropertyVO propertyVO= new PropertyVO();
		List<FileDescription> files = null;
		if(property.getPropertyFiles()!=null && !property.getPropertyFiles().isEmpty()) {
			files = new ArrayList<FileDescription>();
		}
		Property updatedProperty =  new Property();
		updatedProperty.setId(property.getId());
		updatedProperty.setAddress(property.getAddress());
		updatedProperty.setName(property.getName());
		updatedProperty.setDescription(property.getDescription());
		updatedProperty.setCode(property.getCode());
		updatedProperty.setLocation(property.getLocation());
		for(PropertyFile propertyFile : property.getPropertyFiles()) {
			FileDescription fileDescription = new FileDescription();	
			fileDescription.setId(propertyFile.getFile().getId());
			fileDescription.setDescription(propertyFile.getDescription());
			fileDescription.setName(propertyFile.getFile().getName());
			files.add(fileDescription);
		}
		propertyVO.setProperty(updatedProperty);
		propertyVO.setFiles(files);
		return propertyVO;
		
	}
	
	public List<PropertyVO> getVO(List<Property> propertyList) {
		List<PropertyVO> propertyVOList = null;
		if(propertyList!=null && !propertyList.isEmpty()) {
			propertyVOList = new ArrayList<PropertyVO>();
		}
		for(Property property : propertyList) {
			propertyVOList.add(this.getVO(property));
		}
		
		return propertyVOList;
		
	}

}
