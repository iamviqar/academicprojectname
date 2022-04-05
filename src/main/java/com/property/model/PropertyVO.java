package com.property.model;

import java.util.List;

import com.property.entity.Property;

public class PropertyVO {
	private Property property;
	private List<FileDescription> files;
	public Property getProperty() {
		return property;
	}
	public void setProperty(Property property) {
		this.property = property;
	}
	public List<FileDescription> getFiles() {
		return files;
	}
	public void setFiles(List<FileDescription> files) {
		this.files = files;
	}
	

}
