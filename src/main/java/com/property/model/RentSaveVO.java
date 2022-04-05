package com.property.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.property.entity.Agreement;

public class RentSaveVO {
	
	private Long ledgerId;
	
	private Long transactionId;
	
	private Agreement agreement;
	
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "IST")
	private Date monthYear;
	
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "IST")
	private Date paidDate;
	
	private Float amount;
	
	private Float gstAmount;
	
	private Float tdsAmount;
	
	private Character paymentMode;
	
	private String description;
	
	private String chequeNo;

	public Agreement getAgreement() {
		return agreement;
	}

	public void setAgreement(Agreement agreement) {
		this.agreement = agreement;
	}

	public Date getMonthYear() {
		return monthYear;
	}

	public void setMonthYear(Date monthYear) {
		this.monthYear = monthYear;
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

	public Long getLedgerId() {
		return ledgerId;
	}

	public void setLedgerId(Long ledgerId) {
		this.ledgerId = ledgerId;
	}

	public Long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}

	public String getChequeNo() {
		return chequeNo;
	}

	public void setChequeNo(String chequeNo) {
		this.chequeNo = chequeNo;
	}

	public Float getGstAmount() {
		return gstAmount;
	}

	public void setGstAmount(Float gstAmount) {
		this.gstAmount = gstAmount;
	}

	public Float getTdsAmount() {
		return tdsAmount;
	}

	public void setTdsAmount(Float tdsAmount) {
		this.tdsAmount = tdsAmount;
	}
	
	
	
}
