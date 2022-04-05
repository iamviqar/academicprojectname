package com.property.model;

import java.util.List;

import com.property.entity.Property;

public class PropertyTaxNotificationVO {
	private Property property;
	private List<Integer> years;
	public Property getProperty() {
		return property;
	}
	public void setProperty(Property property) {
		this.property = property;
	}
	public List<Integer> getYears() {
		return years;
	}
	public void setYears(List<Integer> years) {
		this.years = years;
	}
}
