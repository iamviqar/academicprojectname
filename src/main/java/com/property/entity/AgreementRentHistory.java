package com.property.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="agreement_rent_history")
public class AgreementRentHistory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Id")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="agreement_id")
	private Agreement agreement;
	
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "IST")
	@Column(name="start_date")
	private Date stateDate;

	@JsonFormat(pattern="yyyy-MM-dd",timezone = "IST")
	@Column(name="end_date")
	private Date endDate;

	@Column(name="rent_amount")
	private Float rentAmount;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Agreement getAgreement() {
		return agreement;
	}

	public void setAgreement(Agreement agreement) {
		this.agreement = agreement;
	}

	public Date getStateDate() {
		return stateDate;
	}

	public void setStateDate(Date stateDate) {
		this.stateDate = stateDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Float getRentAmount() {
		return rentAmount;
	}

	public void setRentAmount(Float rentAmount) {
		this.rentAmount = rentAmount;
	}

	


	
	
}
