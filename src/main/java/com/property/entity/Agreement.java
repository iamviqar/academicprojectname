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
@Table(name="agreements")
public class Agreement {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Id")
	private Long id;
	
	@Column(name="certificate_number")
	private String certificateNumber;
	
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "IST")
	@Column(name="start_date")
	private Date startDate;
	
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "IST")
	@Column(name="end_date")
	private Date endDate;
	
	@Column(name="advance_amount")
	private Float advanceAmount;
	
	@Column(name="rent_amount")
	private Float rentAmount;
	
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "IST")
	@Column(name="certificate_issue_date")
	private Date certificateIssueDate;
	
	@Column(name="account_reference")
	private String accountReference;
	
	@Column(name="unique_doc_reference")
	private String uniqueDocReference;
	
	@Column(name="purchased_by")
	private Character purchasedBy;
	
	@Column(name="description_of_document")
	private String descriptionOfDocument;
	
	@Column(name="consideration_price")
	private Double considerationPrice;
	
	@Column(name="first_party")
	private Character firstParty;
	
	@Column(name="second_party")
	private Character secondParty;
	
	@Column(name="stamp_duty_paid_by")
	private Character stampDutyPaidBy;
	
	@Column(name="stamp_duty_amount")
	private Double stampDutyAmount;
	
	@Column(name="content")
	private String content;
	
	@Column(name="is_active")
	private Boolean isActive;
	
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "IST")
	@Column(name="revised_rent_date")
	private Date revisedRentDate;
	
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "IST")
	@Column(name="last_revised_rent_date")
	private Date lastRevisedRentDate;
	
	@Column(name="revised_rent_amount")
	private Float revisedRentAmount;
	
	@Column(name="enhancement_period")
	private Integer enhancementPeriod;
	
	@Column(name="gst_percentage")
	private Float gstPercentage;
	
	@Column(name="tds_percentage")
	private Float tdsPercentage;
	
	@ManyToOne
	@JoinColumn(name="tenant_id")
	private Tenant tenant;
	
	@ManyToOne
	@JoinColumn(name="property_id")
	private Property property;
	
	@OneToMany(mappedBy="agreement")
    private List<AgreementFile> agreementFiles;
	
	@OneToMany(mappedBy="agreement")
    private List<AgreementRentHistory> agreementRentHistory;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Tenant getTenant() {
		return tenant;
	}

	public void setTenant(Tenant tenant) {
		this.tenant = tenant;
	}

	public Property getProperty() {
		return property;
	}

	public void setProperty(Property property) {
		this.property = property;
	}

	public String getCertificateNumber() {
		return certificateNumber;
	}

	public void setCertificateNumber(String certificateNumber) {
		this.certificateNumber = certificateNumber;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getCertificateIssueDate() {
		return certificateIssueDate;
	}

	public void setCertificateIssueDate(Date certificateIssueDate) {
		this.certificateIssueDate = certificateIssueDate;
	}

	public String getAccountReference() {
		return accountReference;
	}

	public void setAccountReference(String accountReference) {
		this.accountReference = accountReference;
	}

	public String getUniqueDocReference() {
		return uniqueDocReference;
	}

	public void setUniqueDocReference(String uniqueDocReference) {
		this.uniqueDocReference = uniqueDocReference;
	}

	public Character getPurchasedBy() {
		return purchasedBy;
	}

	public void setPurchasedBy(Character purchasedBy) {
		this.purchasedBy = purchasedBy;
	}

	public String getDescriptionOfDocument() {
		return descriptionOfDocument;
	}

	public void setDescriptionOfDocument(String descriptionOfDocument) {
		this.descriptionOfDocument = descriptionOfDocument;
	}

	public Character getFirstParty() {
		return firstParty;
	}

	public void setFirstParty(Character firstParty) {
		this.firstParty = firstParty;
	}

	public Character getSecondParty() {
		return secondParty;
	}

	public void setSecondParty(Character secondParty) {
		this.secondParty = secondParty;
	}

	public Character getStampDutyPaidBy() {
		return stampDutyPaidBy;
	}

	public void setStampDutyPaidBy(Character stampDutyPaidBy) {
		this.stampDutyPaidBy = stampDutyPaidBy;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public List<AgreementFile> getAgreementFiles() {
		return agreementFiles;
	}

	public void setAgreementFiles(List<AgreementFile> agreementFiles) {
		this.agreementFiles = agreementFiles;
	}

	public Double getConsiderationPrice() {
		return considerationPrice;
	}

	public void setConsiderationPrice(Double considerationPrice) {
		this.considerationPrice = considerationPrice;
	}

	public Double getStampDutyAmount() {
		return stampDutyAmount;
	}

	public void setStampDutyAmount(Double stampDutyAmount) {
		this.stampDutyAmount = stampDutyAmount;
	}

	public Float getAdvanceAmount() {
		return advanceAmount;
	}

	public void setAdvanceAmount(Float advanceAmount) {
		this.advanceAmount = advanceAmount;
	}

	public Float getRentAmount() {
		return rentAmount;
	}

	public void setRentAmount(Float rentAmount) {
		this.rentAmount = rentAmount;
	}

	public Date getRevisedRentDate() {
		return revisedRentDate;
	}

	public void setRevisedRentDate(Date revisedRentDate) {
		this.revisedRentDate = revisedRentDate;
	}

	public Float getRevisedRentAmount() {
		return revisedRentAmount;
	}

	public void setRevisedRentAmount(Float revisedRentAmount) {
		this.revisedRentAmount = revisedRentAmount;
	}

	public Integer getEnhancementPeriod() {
		return enhancementPeriod;
	}

	public void setEnhancementPeriod(Integer enhancementPeriod) {
		this.enhancementPeriod = enhancementPeriod;
	}

	public Date getLastRevisedRentDate() {
		return lastRevisedRentDate;
	}

	public void setLastRevisedRentDate(Date lastRevisedRentDate) {
		this.lastRevisedRentDate = lastRevisedRentDate;
	}

	public Float getGstPercentage() {
		return gstPercentage;
	}

	public void setGstPercentage(Float gstPercentage) {
		this.gstPercentage = gstPercentage;
	}

	public Float getTdsPercentage() {
		return tdsPercentage;
	}

	public void setTdsPercentage(Float tdsPercentage) {
		this.tdsPercentage = tdsPercentage;
	}

	public List<AgreementRentHistory> getAgreementRentHistory() {
		return agreementRentHistory;
	}

	public void setAgreementRentHistory(List<AgreementRentHistory> agreementRentHistory) {
		this.agreementRentHistory = agreementRentHistory;
	}
	
	
	
	
	
		
}
