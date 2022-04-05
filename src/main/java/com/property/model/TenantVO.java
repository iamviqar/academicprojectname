package com.property.model;

import java.util.List;

import com.property.entity.Tenant;

public class TenantVO {
	private Tenant tenant;
	private List<FileDescription> files;
	
	
	public Tenant getTenant() {
		return tenant;
	}
	public void setTenant(Tenant tenant) {
		this.tenant = tenant;
	}
	public List<FileDescription> getFiles() {
		return files;
	}
	public void setFiles(List<FileDescription> files) {
		this.files = files;
	}
	

}
