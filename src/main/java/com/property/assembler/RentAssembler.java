package com.property.assembler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.property.entity.RentLedger;
import com.property.entity.RentTransaction;
import com.property.model.RentSaveVO;

@Component
public class RentAssembler {

	@Autowired
	AgreementAssembler agreementAssembler;
	
	public RentLedger getRentLedger(RentSaveVO rentVO) {
		RentLedger rentLedger = null;
		if (rentVO != null) {
			rentLedger = new RentLedger();
			rentLedger.setAgreement(rentVO.getAgreement());
			rentLedger.setMonthYear(rentVO.getMonthYear());
			rentLedger.setId(rentVO.getLedgerId());
			rentLedger.setTdsAmount(rentVO.getTdsAmount());
			rentLedger.setGstAmount(rentVO.getGstAmount());
		}
		return rentLedger;
	}

	public RentSaveVO getRentSaveVO(RentTransaction rentTransaction) {
		RentSaveVO rentSaveVO = new RentSaveVO();
		rentSaveVO.setLedgerId(rentTransaction.getRentLedger().getId());
		rentSaveVO.setTransactionId(rentTransaction.getId());
		rentSaveVO.setAgreement(agreementAssembler.removePersistanceObjects(rentTransaction.getRentLedger().getAgreement()));
		rentSaveVO.setAmount(rentTransaction.getRentLedger().getTotalAmount());
		rentSaveVO.setDescription(rentTransaction.getDescription());
		rentSaveVO.setMonthYear(rentTransaction.getRentLedger().getMonthYear());
		rentSaveVO.setPaidDate(rentTransaction.getPaidDate());
		rentSaveVO.setPaymentMode(rentTransaction.getPaymentMode());
		return rentSaveVO;
	}
	
	public RentSaveVO getRentSaveVOByRentLedger(RentLedger rentLedger) {
		RentSaveVO rentSaveVO = new RentSaveVO();
		rentSaveVO.setLedgerId(rentLedger.getId());
		rentSaveVO.setAgreement(agreementAssembler.removePersistanceObjects(rentLedger.getAgreement()));
		rentSaveVO.setAmount(rentLedger.getTotalAmount());
		rentSaveVO.setTdsAmount(rentLedger.getTdsAmount());
		rentSaveVO.setGstAmount(rentLedger.getGstAmount());
		rentSaveVO.setMonthYear(rentLedger.getMonthYear());
		return rentSaveVO;
	}
	
	public List<RentSaveVO> getRentSaveVOByRentLedger(List<RentLedger> rentLedgerList) {
		List<RentSaveVO> updatedRentTransactions = null;
		if (rentLedgerList != null && !rentLedgerList.isEmpty()) {
			updatedRentTransactions = new ArrayList<RentSaveVO>();
			for (RentLedger rentLedger : rentLedgerList) {
				updatedRentTransactions.add(this.getRentSaveVOByRentLedger(rentLedger));
			}
		}
		return updatedRentTransactions;
	}

	public List<RentSaveVO> getRentSaveVO(List<RentTransaction> rentTransactions) {
		List<RentSaveVO> updatedRentTransactions = null;
		if (rentTransactions != null && !rentTransactions.isEmpty()) {
			updatedRentTransactions = new ArrayList<RentSaveVO>();
			for (RentTransaction rentTransaction : rentTransactions) {
				updatedRentTransactions.add(this.getRentSaveVO(rentTransaction));
			}
		}
		return updatedRentTransactions;
	}

	public RentTransaction getRentTransaction(RentSaveVO rentVO) {
		RentTransaction rentTransaction = null;
		if (rentVO != null) {
			rentTransaction = new RentTransaction();
			rentTransaction.setAmount(rentVO.getAmount());
			rentTransaction.setDescription(rentVO.getDescription());
			rentTransaction.setPaidDate(rentVO.getPaidDate());
			rentTransaction.setPaymentMode(rentVO.getPaymentMode());
			rentTransaction.setChequeNo(rentVO.getChequeNo());
		}
		return rentTransaction;
	}

}
