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
@Table(name="property_tax")
public class PropertyTax {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Id")
	private Long id;
	
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "IST")
	@Column(name="paid_date")
	private Date paidDate;
	
	@Column(name="paid_year")
	private Integer paidYear;
	
	@Column(name="amount")
	private Float amount;
	
	@Column(name="payment_mode")
	private Character paymentMode;
	
	@ManyToOne
	@JoinColumn(name="property_id")
	private Property property;
	
	@OneToMany(mappedBy="propertyTax")
    private List<PropertyTaxFile> propertyTaxFiles;
	
	
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

	public Integer getPaidYear() {
		return paidYear;
	}

	public void setPaidYear(Integer paidYear) {
		this.paidYear = paidYear;
	}

	public Property getProperty() {
		return property;
	}

	public void setProperty(Property property) {
		this.property = property;
	}

	public List<PropertyTaxFile> getPropertyTaxFiles() {
		return propertyTaxFiles;
	}

	public void setPropertyTaxFiles(List<PropertyTaxFile> propertyTaxFiles) {
		this.propertyTaxFiles = propertyTaxFiles;
	}
	
	

		
}
