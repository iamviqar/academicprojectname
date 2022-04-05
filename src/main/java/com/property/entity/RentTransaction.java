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
@Table(name="rent_transactions")
public class RentTransaction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Id")
	private Long id;
	
	@Column(name="paid_date")
	private Date paidDate;
	
	@Column(name="amount")
	private Float amount;
	
	@Column(name="old_amount")
	private Float oldAmount;
	
	@Column(name="payment_mode")
	private Character paymentMode;
	
	@Column(name="description")
	private String description;
	
	@Column(name="is_rolled_back")
	private Boolean isRolledBack;
	
	@Column(name="rolled_back_on")
	private Date rolledBackOn;
	
	@Column(name="cheque_no")
	private String chequeNo;
	
	@ManyToOne
	@JoinColumn(name="rent_ledger_id")
	private RentLedger rentLedger;
	
	
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public RentLedger getRentLedger() {
		return rentLedger;
	}

	public void setRentLedger(RentLedger rentLedger) {
		this.rentLedger = rentLedger;
	}

	public Float getOldAmount() {
		return oldAmount;
	}

	public void setOldAmount(Float oldAmount) {
		this.oldAmount = oldAmount;
	}

	public Boolean getIsRolledBack() {
		return isRolledBack;
	}

	public void setIsRolledBack(Boolean isRolledBack) {
		this.isRolledBack = isRolledBack;
	}

	public Date getRolledBackOn() {
		return rolledBackOn;
	}

	public void setRolledBackOn(Date rolledBackOn) {
		this.rolledBackOn = rolledBackOn;
	}

	public String getChequeNo() {
		return chequeNo;
	}

	public void setChequeNo(String chequeNo) {
		this.chequeNo = chequeNo;
	}
	
	
	
	

		
}
