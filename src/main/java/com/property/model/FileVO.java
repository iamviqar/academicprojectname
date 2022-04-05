package com.property.model;

import org.springframework.core.io.Resource;

import com.property.entity.File;

public class FileVO {

	private Resource resource;
	private File file;
	public Resource getResource() {
		return resource;
	}
	public void setResource(Resource resource) {
		this.resource = resource;
	}
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	
	
	
}
