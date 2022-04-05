package com.property.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.property.assembler.TransactionAssembler;
import com.property.dao.GstMainAccountRepo;
import com.property.dao.RentTransactionRepo;
import com.property.dao.TransactionRepo;
import com.property.entity.GstMainAccount;
import com.property.entity.RentTransaction;
import com.property.entity.Transaction;
import com.property.model.AccountSummaryVO;
import com.property.service.ITransactionService;

@Service
public class TransactionService implements ITransactionService {

	@Autowired
	TransactionRepo transactionRepo;
	
	@Autowired
	RentTransactionRepo rentTransactionRepo;
	
	@Autowired
	TransactionAssembler transactionAssembler;
	
	@Autowired
	GstMainAccountRepo gstMainAccountRepo;
	
	@Override
	public Transaction saveDeposit(Transaction transaction) throws Exception {
		
		try {
			transaction.setDepositWithdrawal('D');
			transactionRepo.save(transaction);
		}catch(Exception e) {
			throw e;
		}
		return transaction;
	}

	@Override
	public Transaction saveWithdraw(Transaction transaction) throws Exception {
		
		try {
			transaction.setDepositWithdrawal('W');
			transactionRepo.save(transaction);
			
			GstMainAccount gstMainAccount = gstMainAccountRepo.findOneById(1L);
			if(transaction.getGst()!=null) {
				gstMainAccount.setGstPaid(gstMainAccount.getGstPaid()+transaction.getGst());
				gstMainAccountRepo.save(gstMainAccount);	
			}
			
		}catch(Exception e) {
			throw e;
		}
		return transaction;
	}
	
	@Override
	public List<AccountSummaryVO> getAccountSummary() throws Exception {
		List<AccountSummaryVO> accountSummaryVOs=new ArrayList<AccountSummaryVO>();
		List<AccountSummaryVO> finalList;
		try {
			List<Transaction> transactions = transactionRepo.findAll();
			List<RentTransaction> rentTransactions = rentTransactionRepo.findByRentLedgerIsBankDeposited(true);
			List<AccountSummaryVO> accountSummaryVOs1 = transactionAssembler.getAccountSummaryVOByRT(rentTransactions);
			List<AccountSummaryVO> accountSummaryVOs2 = transactionAssembler.getAccountSummaryVOByT(transactions);
			
			if(accountSummaryVOs1!=null)
			accountSummaryVOs.addAll(accountSummaryVOs1);
			
			if(accountSummaryVOs2!=null)
			accountSummaryVOs.addAll(accountSummaryVOs2);
			
			finalList = accountSummaryVOs.stream().sorted(Comparator.comparing(AccountSummaryVO::getTransactionDate)).collect(Collectors.toList());
		}catch(Exception e) {
			throw e;
		}
		return finalList;
	}

	@Override
	public Float getAvailableBalance() throws Exception {
		Float rentTotal = rentTransactionRepo.getTotalAmount();
		Float transactionTotal = transactionRepo.getTotalAmount();
		if(rentTotal==null) {
			rentTotal = 0F;
		}
		if(transactionTotal==null) {
			transactionTotal = 0F;
		}
		Float total = rentTotal + transactionTotal;
		return total;
	}

	
}
