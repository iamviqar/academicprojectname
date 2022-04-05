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

@Entity
@Table(name="rent_ledger")
public class RentLedger {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Id")
	private Long id;
	
	@Column(name="month_year")
	private Date monthYear;
	
	@Column(name="total_amount")
	private Float totalAmount;
	
	@Column(name="is_bank_deposited")
	private Boolean isBankDeposited;
	
	@Column(name="is_tds_paid")
	private Boolean isTdsPaid;
	
	@Column(name="tds_amount")
	private Float tdsAmount;
	
	@Column(name="gst_amount")
	private Float gstAmount;
	
	@ManyToOne
	@JoinColumn(name="agreement_id")
	private Agreement agreement;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getMonthYear() {
		return monthYear;
	}

	public void setMonthYear(Date monthYear) {
		this.monthYear = monthYear;
	}

	public Float getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Float totalAmount) {
		this.totalAmount = totalAmount;
	}


	public Agreement getAgreement() {
		return agreement;
	}

	public void setAgreement(Agreement agreement) {
		this.agreement = agreement;
	}

	public Boolean getIsBankDeposited() {
		return isBankDeposited;
	}

	public void setIsBankDeposited(Boolean isBankDeposited) {
		this.isBankDeposited = isBankDeposited;
	}

	public Float getTdsAmount() {
		return tdsAmount;
	}

	public void setTdsAmount(Float tdsAmount) {
		this.tdsAmount = tdsAmount;
	}

	public Float getGstAmount() {
		return gstAmount;
	}

	public void setGstAmount(Float gstAmount) {
		this.gstAmount = gstAmount;
	}

	public Boolean getIsTdsPaid() {
		return isTdsPaid;
	}

	public void setIsTdsPaid(Boolean isTdsPaid) {
		this.isTdsPaid = isTdsPaid;
	}
	
}
