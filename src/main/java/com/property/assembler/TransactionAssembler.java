package com.property.assembler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.property.entity.RentTransaction;
import com.property.entity.Transaction;
import com.property.model.AccountSummaryVO;

@Component
public class TransactionAssembler {

	public AccountSummaryVO getAccountSummaryVO(Transaction transaction){
		AccountSummaryVO accountSummaryVO = null;
		if(transaction != null) {
			accountSummaryVO = new AccountSummaryVO();
			accountSummaryVO.setAmount(transaction.getAmount());
			accountSummaryVO.setDescription(transaction.getDescription());
			accountSummaryVO.setModeOfPayment(transaction.getPaymentMode());
			accountSummaryVO.setTransactionDate(transaction.getPaidDate());
			accountSummaryVO.setTransactionType(transaction.getDepositWithdrawal());
		}
		return accountSummaryVO;
	}
	
	public AccountSummaryVO getAccountSummaryVO(RentTransaction rentTransaction){
		AccountSummaryVO accountSummaryVO = null;
		if(rentTransaction != null) {
			accountSummaryVO = new AccountSummaryVO();
			accountSummaryVO.setAmount(rentTransaction.getAmount());
			accountSummaryVO.setDescription(rentTransaction.getDescription());
			accountSummaryVO.setModeOfPayment(rentTransaction.getPaymentMode());
			accountSummaryVO.setTransactionDate(rentTransaction.getPaidDate());
			accountSummaryVO.setTransactionType('R');
		}
		return accountSummaryVO;
	}
	
	public List<AccountSummaryVO> getAccountSummaryVOByT(List<Transaction> transactions){
		List<AccountSummaryVO> accountSummaryVOs=null;
		if(transactions!=null && !transactions.isEmpty()) {
			accountSummaryVOs = new ArrayList<AccountSummaryVO>();
		}
		for(Transaction transaction : transactions) {
			accountSummaryVOs.add(this.getAccountSummaryVO(transaction));
		}
		return accountSummaryVOs;
	}
	
	public List<AccountSummaryVO> getAccountSummaryVOByRT(List<RentTransaction> rentTransactions){
		List<AccountSummaryVO> accountSummaryVOs=null;
		if(rentTransactions!=null && !rentTransactions.isEmpty()) {
			accountSummaryVOs = new ArrayList<AccountSummaryVO>();
		}
		for(RentTransaction rentTransaction : rentTransactions) {
			accountSummaryVOs.add(this.getAccountSummaryVO(rentTransaction));
		}
		return accountSummaryVOs;
	}
	
}
