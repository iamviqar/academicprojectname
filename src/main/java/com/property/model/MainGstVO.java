package com.property.model;

import java.util.List;

import com.property.entity.GstMainAccount;

public class MainGstVO {
	private GstMainAccount gstAccount;
	private List<GstRecordVO> gstRecords;
	public GstMainAccount getGstAccount() {
		return gstAccount;
	}
	public void setGstAccount(GstMainAccount gstAccount) {
		this.gstAccount = gstAccount;
	}
	public List<GstRecordVO> getGstRecords() {
		return gstRecords;
	}
	public void setGstRecords(List<GstRecordVO> gstRecords) {
		this.gstRecords = gstRecords;
	}
	
	
	
}
