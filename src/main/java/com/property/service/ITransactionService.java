package com.property.service;

import java.util.List;

import com.property.entity.Transaction;
import com.property.model.AccountSummaryVO;

public interface ITransactionService {
	public Transaction saveDeposit(Transaction transaction) throws Exception;
	public Transaction saveWithdraw(Transaction transaction) throws Exception;
	public List<AccountSummaryVO> getAccountSummary() throws Exception;
	public Float getAvailableBalance() throws Exception;
	
}
