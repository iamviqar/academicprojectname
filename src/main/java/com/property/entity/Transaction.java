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
@Table(name="transactions")
public class Transaction {
	
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
	
	@Column(name="deposit_withdrawal")
	private Character depositWithdrawal;
	
	@Column(name="deposit_type")
	private Character depositType;
	
	@ManyToOne
	@JoinColumn(name="party_id")
	private Party party;

	@ManyToOne
	@JoinColumn(name="property_id")
	private Property property;
	
	@Column(name="payment_category")
	private String paymentCategory;
	
	@Column(name="gst")
	private Float gst;

	@Column(name="reference_number")
	private String referenceNumber;
	
	@Column(name="invoice_number")
	private String invoiceNumber;
	
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

	public Character getDepositWithdrawal() {
		return depositWithdrawal;
	}

	public void setDepositWithdrawal(Character depositWithdrawal) {
		this.depositWithdrawal = depositWithdrawal;
	}

	public Character getDepositType() {
		return depositType;
	}

	public void setDepositType(Character depositType) {
		this.depositType = depositType;
	}

	public Party getParty() {
		return party;
	}

	public void setParty(Party party) {
		this.party = party;
	}

	public Property getProperty() {
		return property;
	}

	public void setProperty(Property property) {
		this.property = property;
	}

	public String getPaymentCategory() {
		return paymentCategory;
	}

	public void setPaymentCategory(String paymentCategory) {
		this.paymentCategory = paymentCategory;
	}

	public Float getGst() {
		return gst;
	}

	public void setGst(Float gst) {
		this.gst = gst;
	}

	public String getReferenceNumber() {
		return referenceNumber;
	}

	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	
		
}
