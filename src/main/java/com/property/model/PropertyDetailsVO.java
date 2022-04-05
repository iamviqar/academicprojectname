package com.property.model;

import java.util.List;

public class PropertyDetailsVO {
	PropertyVO property;
	List<AgreementVO> agreementList;
	List<PropertyTaxVO> propertyTaxList;
	List<PropertyMaintenanceVO> propertyMaintenanceList;
	
	public PropertyVO getProperty() {
		return property;
	}
	public void setProperty(PropertyVO property) {
		this.property = property;
	}
	public List<AgreementVO> getAgreementList() {
		return agreementList;
	}
	public void setAgreementList(List<AgreementVO> agreementList) {
		this.agreementList = agreementList;
	}
	public List<PropertyTaxVO> getPropertyTaxList() {
		return propertyTaxList;
	}
	public void setPropertyTaxList(List<PropertyTaxVO> propertyTaxList) {
		this.propertyTaxList = propertyTaxList;
	}
	public List<PropertyMaintenanceVO> getPropertyMaintenanceList() {
		return propertyMaintenanceList;
	}
	public void setPropertyMaintenanceList(List<PropertyMaintenanceVO> propertyMaintenanceList) {
		this.propertyMaintenanceList = propertyMaintenanceList;
	}
	
	
}
