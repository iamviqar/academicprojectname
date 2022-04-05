package com.property.model;

import java.util.List;

import com.property.entity.PropertyMaintenance;

public class PropertyMaintenanceVO {
	PropertyMaintenance propertyMaintenance;
	private List<FileDescription> files;
	public PropertyMaintenance getPropertyMaintenance() {
		return propertyMaintenance;
	}
	public void setPropertyMaintenance(PropertyMaintenance propertyMaintenance) {
		this.propertyMaintenance = propertyMaintenance;
	}
	public List<FileDescription> getFiles() {
		return files;
	}
	public void setFiles(List<FileDescription> files) {
		this.files = files;
	}
	
}
