package com.property.model;

import java.util.Date;

import com.property.entity.Property;
import com.property.entity.Tenant;

public class GstRecordVO {
	private String type;
	private Property property;
	private Tenant tenant;
	private String invoice;
	private Date date;
	private Float amount;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Property getProperty() {
		return property;
	}

	public void setProperty(Property property) {
		this.property = property;
	}

	public Tenant getTenant() {
		return tenant;
	}

	public void setTenant(Tenant tenant) {
		this.tenant = tenant;
	}

	public String getInvoice() {
		return invoice;
	}

	public void setInvoice(String invoice) {
		this.invoice = invoice;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

}
