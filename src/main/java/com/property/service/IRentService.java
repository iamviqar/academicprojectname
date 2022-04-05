package com.property.service;

import java.util.List;

import com.property.entity.GstPaidLedger;
import com.property.model.GstVO;
import com.property.model.RentArrearVO;
import com.property.model.RentSaveVO;
import com.property.util.ApplicationParam;

public interface IRentService {
	public RentSaveVO save(RentSaveVO rentSaveVo) throws Exception;
	public List<RentSaveVO> getPendingDepositList() throws Exception;
	public List<RentSaveVO> getCollection() throws Exception;
	public List<RentArrearVO> getRentArrears(ApplicationParam arrearParam,Long id) throws Exception;
	public RentSaveVO update(RentSaveVO rentSaveVo) throws Exception;
	public RentSaveVO voidRent(RentSaveVO rentSaveVo) throws Exception;
	public List<RentSaveVO> getCollection(ApplicationParam param, Long id) throws Exception;
	public List<RentSaveVO> getPendingTdsList() throws Exception;
	public void markTdsClaimed(List<Long> rentLedgerIds) throws Exception;
	
}
