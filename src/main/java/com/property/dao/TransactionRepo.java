package com.property.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.property.entity.Transaction;

@Repository
public interface TransactionRepo extends CrudRepository<Transaction, Long> {
	public List<Transaction> findAll();
	public Transaction findOneById(Long id);
	public List<Transaction> findByDepositWithdrawalAndGstGreaterThan(Character depositOrWithdrawal,Float gstAmount);
	@Query("SELECT sum(t.amount) from Transaction t ")
	public Float getTotalAmount();
}
