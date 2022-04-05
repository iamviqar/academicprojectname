package com.property.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="property_maintenance")
public class PropertyMaintenance {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Id")
	private Long id;
	
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "IST")
	@Column(name="paid_date")
	private Date paidDate;
	
	@Column(name="amount")
	private Float amount;
	
	@Column(name="payment_mode")
	private Character paymentMode;
	
	@Column(name="description")
	private String description;
	
	@ManyToOne
	@JoinColumn(name="property_id")
	private Property property;
	
	@OneToMany(mappedBy="propertyMaintenance")
    private List<PropertyMaintenanceFile> propertyMaintenanceFiles;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getPaidDate() {
		return paidDate;
	}

	public void setPaidDate(Date paidDate) {
		this.paidDate = paidDate;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	public Character getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(Character paymentMode) {
		this.paymentMode = paymentMode;
	}


	public Property getProperty() {
		return property;
	}

	public void setProperty(Property property) {
		this.property = property;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<PropertyMaintenanceFile> getPropertyMaintenanceFiles() {
		return propertyMaintenanceFiles;
	}

	public void setPropertyMaintenanceFiles(List<PropertyMaintenanceFile> propertyMaintenanceFiles) {
		this.propertyMaintenanceFiles = propertyMaintenanceFiles;
	}

	
	
}
