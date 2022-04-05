package com.property.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.property.entity.RentLedger;

@Repository
public interface RentLedgerRepo extends CrudRepository<RentLedger, Long> {
	public List<RentLedger> findAll();
	public RentLedger findOneById(Long id);
	public RentLedger findByAgreementIdAndMonthYear(Long agreementId, Date monthYear);
	public List<RentLedger> findByAgreementIsActiveAndIsBankDeposited(Boolean isActive,Boolean isBankDeposited);
	public List<RentLedger> findByIsTdsPaidAndTdsAmountGreaterThan(Boolean isActive,Float tdsAmount);
	public List<RentLedger> findByIsBankDepositedAndGstAmountGreaterThan(Boolean isBankDeposited,Float gstAmount);
	public List<RentLedger> findByIsTdsPaidIsNotNullAndTdsAmountIsNotNull();
	public List<RentLedger> findByTdsAmountGreaterThanAndIsTdsPaidOrIsTdsPaidIsNull(Float tdsAmount,Boolean isActive);
}
