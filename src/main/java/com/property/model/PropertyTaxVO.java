package com.property.model;

import java.util.List;

import com.property.entity.PropertyTax;

public class PropertyTaxVO {
	private PropertyTax propertyTax;
	private List<FileDescription> files;
	
	
	public PropertyTax getPropertyTax() {
		return propertyTax;
	}
	public void setPropertyTax(PropertyTax propertyTax) {
		this.propertyTax = propertyTax;
	}
	public List<FileDescription> getFiles() {
		return files;
	}
	public void setFiles(List<FileDescription> files) {
		this.files = files;
	}
	

}
