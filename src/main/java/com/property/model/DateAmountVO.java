package com.property.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class DateAmountVO {
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "IST")
	private Date date;
	private Float amount;
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Float getAmount() {
		return amount;
	}
	public void setAmount(Float amount) {
		this.amount = amount;
	}

}
