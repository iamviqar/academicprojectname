package com.property.service;

import com.property.entity.GstPaidLedger;
import com.property.model.GstVO;
import com.property.model.MainGstVO;

public interface IGstService {

	public MainGstVO getGstRecords() throws Exception;

	public GstPaidLedger save(GstPaidLedger gstPaidLedger) throws Exception;

	public GstVO getGstInfo() throws Exception;
	
}
