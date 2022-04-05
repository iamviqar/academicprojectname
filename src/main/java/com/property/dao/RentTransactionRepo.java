package com.property.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.property.entity.RentTransaction;

@Repository
public interface RentTransactionRepo extends CrudRepository<RentTransaction, Long> {
	public List<RentTransaction> findAll();
	public RentTransaction findOneById(Long id);	
	public RentTransaction findByRentLedgerAgreementIdAndRentLedgerMonthYear(Long agreementId, Date monthYear);	
	public List<RentTransaction> findByRentLedgerIsBankDeposited(Boolean isBankDeposited);
	public List<RentTransaction> findByRentLedgerIsBankDepositedAndRentLedgerAgreementTenantId(Boolean isBankDeposited,Long tenantId);
	public List<RentTransaction> findByRentLedgerIsBankDepositedAndRentLedgerAgreementPropertyId(Boolean isBankDeposited,Long propertyId);
	
	@Query("SELECT max(rt.rentLedger.monthYear),rt.rentLedger.agreement.id from RentTransaction rt where rt.rentLedger.isBankDeposited=true group by rt.rentLedger.agreement.id")
	public List<Object[]> getPreviousMonthRent();
	
	@Query("SELECT max(rt.rentLedger.monthYear),rt.rentLedger.agreement.id from RentTransaction rt where rt.rentLedger.isBankDeposited=true and rentLedger.agreement.property.id=?1 group by rt.rentLedger.agreement.id")
	public List<Object[]> getPreviousMonthRentByProperty(Long propertyId);
	
	@Query("SELECT max(rt.rentLedger.monthYear),rt.rentLedger.agreement.id from RentTransaction rt where rt.rentLedger.isBankDeposited=true and rentLedger.agreement.tenant.id=?1 group by rt.rentLedger.agreement.id")
	public List<Object[]> getPreviousMonthRentByTenant(Long tenantId);
	
	@Query("SELECT max(rt.rentLedger.monthYear),rt.rentLedger.agreement.id from RentTransaction rt where rentLedger.agreement.id=?1 group by rt.rentLedger.agreement.id")
	public List<Object[]> getPreviousMonthRentByAgreement(Long agreementId);
	
	@Query("SELECT sum(rt.amount) from RentTransaction rt where rt.rentLedger.isBankDeposited=true")
	public Float getTotalAmount();
	
}
