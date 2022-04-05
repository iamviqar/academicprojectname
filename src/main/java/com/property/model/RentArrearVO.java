package com.property.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.property.entity.Agreement;

public class RentArrearVO {
	private Agreement agreement;
	private List<DateAmountVO> arrearMonthsList;
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "IST")
	private Float totalAmountOverDue;
	public Agreement getAgreement() {
		return agreement;
	}
	public void setAgreement(Agreement agreement) {
		this.agreement = agreement;
	}
	public Float getTotalAmountOverDue() {
		return totalAmountOverDue;
	}
	public void setTotalAmountOverDue(Float totalAmountOverDue) {
		this.totalAmountOverDue = totalAmountOverDue;
	}
	public List<DateAmountVO> getArrearMonthsList() {
		return arrearMonthsList;
	}
	public void setArrearMonthsList(List<DateAmountVO> arrearMonthsList) {
		this.arrearMonthsList = arrearMonthsList;
	}
	
	
	
	
}
