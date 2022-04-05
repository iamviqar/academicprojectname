package com.property.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class AccountSummaryVO {
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "IST")
	private Date transactionDate;
	private Float amount;
	private String description;
	private Character modeOfPayment;
	private Character transactionType;
	public Date getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
	public Float getAmount() {
		return amount;
	}
	public void setAmount(Float amount) {
		this.amount = amount;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Character getModeOfPayment() {
		return modeOfPayment;
	}
	public void setModeOfPayment(Character modeOfPayment) {
		this.modeOfPayment = modeOfPayment;
	}
	public Character getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(Character transactionType) {
		this.transactionType = transactionType;
	}
	
	

}
