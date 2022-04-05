package com.property.model;

import java.util.List;

import com.property.entity.GstMainAccount;

public class GstVO {
	private GstMainAccount gstAccount;
	private List<RentSaveVO> gstRecords;
	public GstMainAccount getGstAccount() {
		return gstAccount;
	}
	public void setGstAccount(GstMainAccount gstAccount) {
		this.gstAccount = gstAccount;
	}
	public List<RentSaveVO> getGstRecords() {
		return gstRecords;
	}
	public void setGstRecords(List<RentSaveVO> gstRecords) {
		this.gstRecords = gstRecords;
	}
	
	
}
