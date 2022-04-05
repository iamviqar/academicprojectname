package com.property.model;

import java.util.List;

import com.property.entity.Agreement;

public class AgreementVO {
	private Agreement agreement;
	private List<FileDescription> files;
	public Agreement getAgreement() {
		return agreement;
	}
	public void setAgreement(Agreement agreement) {
		this.agreement = agreement;
	}
	public List<FileDescription> getFiles() {
		return files;
	}
	public void setFiles(List<FileDescription> files) {
		this.files = files;
	}
	
}
