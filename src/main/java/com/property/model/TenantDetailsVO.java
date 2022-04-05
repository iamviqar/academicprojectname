package com.property.model;

import java.util.List;

public class TenantDetailsVO {
	TenantVO tenant;
	List<AgreementVO> agreementList;
	List<RentArrearVO> arrears;
	List<RentSaveVO> paymentHistory;
		
	public List<AgreementVO> getAgreementList() {
		return agreementList;
	}
	public void setAgreementList(List<AgreementVO> agreementList) {
		this.agreementList = agreementList;
	}
	public TenantVO getTenant() {
		return tenant;
	}
	public void setTenant(TenantVO tenant) {
		this.tenant = tenant;
	}
	public List<RentArrearVO> getArrears() {
		return arrears;
	}
	public void setArrears(List<RentArrearVO> arrears) {
		this.arrears = arrears;
	}
	public List<RentSaveVO> getPaymentHistory() {
		return paymentHistory;
	}
	public void setPaymentHistory(List<RentSaveVO> paymentHistory) {
		this.paymentHistory = paymentHistory;
	}
	
	
	
}
