package com.property.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.property.assembler.AgreementAssembler;
import com.property.assembler.RentAssembler;
import com.property.dao.AgreementRentHistoryRepo;
import com.property.dao.AgreementRepo;
import com.property.dao.GstMainAccountRepo;
import com.property.dao.RentLedgerRepo;
import com.property.dao.RentTransactionRepo;
import com.property.entity.Agreement;
import com.property.entity.AgreementRentHistory;
import com.property.entity.GstMainAccount;
import com.property.entity.RentLedger;
import com.property.entity.RentTransaction;
import com.property.model.DateAmountVO;
import com.property.model.RentArrearVO;
import com.property.model.RentSaveVO;
import com.property.service.IRentService;
import com.property.util.ApplicationParam;
import com.property.util.DateUtil;

@Service
public class RentService implements IRentService {

	@Autowired
	RentLedgerRepo rentLedgerRepo;

	@Autowired
	RentTransactionRepo rentTransactionRepo;

	@Autowired
	RentAssembler rentAssembler;
	
	@Autowired
	AgreementAssembler agreementAssembler;

	@Autowired
	AgreementRepo agreementRepo;
	
	@Autowired
	AgreementRentHistoryRepo agreementRentHistoryrepo;
	
	@Autowired
	GstMainAccountRepo gstMainAccountRepo;

	@Value("${rent.due.date}")
	Integer rentDueDate;

	@Override
	@Transactional(rollbackFor = Throwable.class)
	public RentSaveVO save(RentSaveVO rentSaveVo) throws Exception {
		try {
			Long rentLedgerId = null;
			Long rentTransactionId = null;
			
			RentTransaction rentTransactionDAO = rentTransactionRepo.findByRentLedgerAgreementIdAndRentLedgerMonthYear(
					rentSaveVo.getAgreement().getId(), DateUtil.removeTimeFromDate(rentSaveVo.getMonthYear()));
			Agreement agreement = agreementRepo.findOneById(rentSaveVo.getAgreement().getId());
			
			// >>> check if rent already paid
			if (rentTransactionDAO != null
					&& (rentTransactionDAO.getIsRolledBack() == null || !rentTransactionDAO.getIsRolledBack())) {
				throw new Exception("Rent Already Paid For This Month");
			} else if (rentTransactionDAO != null) {
				rentLedgerId = rentTransactionDAO.getRentLedger().getId();
				rentTransactionId = rentTransactionDAO.getId();
			}
			// <<< check if rent already paid
			
			// >>> check current previous month rent pending
			Date previousMonth = null;
			Date currentMonth = null;
			List<Object[]> previousMonthDao = rentTransactionRepo.getPreviousMonthRentByAgreement(rentSaveVo.getAgreement().getId());
			if(previousMonthDao!=null && !previousMonthDao.isEmpty()) {
				previousMonth = (Date)previousMonthDao.get(0)[0];
				currentMonth = DateUtil.getNextMonth(previousMonth);
			}else {
				currentMonth = DateUtil.removeDayTimeFromDate(agreement.getStartDate());
			}
			if(!currentMonth.equals(DateUtil.removeTimeFromDate(rentSaveVo.getMonthYear()))) {
				throw new Exception("Previous Month Rent Pending");
			}
			// <<< check current previous month rent pending
			
			RentLedger rentLedger = rentAssembler.getRentLedger(rentSaveVo);
			rentLedger.setTotalAmount(rentSaveVo.getAmount());
			if (rentSaveVo.getDescription() != null && !rentSaveVo.getDescription().trim().equals("")) {
				rentLedger.setIsBankDeposited(true);
				rentLedger.setIsTdsPaid(false);
			} else {
				rentLedger.setIsBankDeposited(false);
			}
			rentLedger.setId(rentLedgerId);
			
			rentLedgerRepo.save(rentLedger);
			if(rentLedger.getIsBankDeposited().equals(true) && rentLedger.getGstAmount()!=null && rentLedger.getGstAmount() > 0F) {
				GstMainAccount gstMainAccount = gstMainAccountRepo.findOneById(1L);
				gstMainAccount.setGstCollected(gstMainAccount.getGstCollected()+rentLedger.getGstAmount());
				gstMainAccountRepo.save(gstMainAccount);
			}
			RentTransaction rentTransaction = rentAssembler.getRentTransaction(rentSaveVo);
			rentTransaction.setRentLedger(rentLedger);
			rentTransaction.setId(rentTransactionId);
			if (rentTransactionDAO != null) {
				rentTransaction.setOldAmount(rentTransactionDAO.getOldAmount());
				rentTransaction.setRolledBackOn(rentTransactionDAO.getRolledBackOn());
			}
			rentTransactionRepo.save(rentTransaction);
			rentSaveVo.setTransactionId(rentTransaction.getId());
			rentSaveVo.setLedgerId(rentLedger.getId());
			
			//update revised rent
			if(agreement.getRevisedRentDate()!=null && agreement.getRevisedRentAmount()!=null) {
				Calendar cal1 = Calendar.getInstance();
				Calendar cal2 = Calendar.getInstance();
				cal1.setTime(agreement.getRevisedRentDate());
				cal2.setTime(rentSaveVo.getMonthYear());
				if(cal1.get(Calendar.YEAR)==cal2.get(Calendar.YEAR) && 
						cal1.get(Calendar.MONTH)==cal2.get(Calendar.MONTH)) {
					AgreementRentHistory agreementRentHistory = new AgreementRentHistory();
					agreementRentHistory.setAgreement(agreement);
					if(agreement.getLastRevisedRentDate() == null) {
						agreementRentHistory.setStateDate(agreement.getStartDate());	
					}else {
						agreementRentHistory.setStateDate(agreement.getLastRevisedRentDate());
					}
					agreementRentHistory.setEndDate(agreement.getRevisedRentDate());
					agreementRentHistory.setRentAmount(agreement.getRentAmount());
					agreementRentHistoryrepo.save(agreementRentHistory);
					agreement.setRentAmount(agreement.getRevisedRentAmount());
					agreement.setLastRevisedRentDate(agreement.getRevisedRentDate());
					agreement.setRevisedRentAmount(null);
					agreement.setRevisedRentDate(null);
					agreementRepo.save(agreement);
				}
			}
		} catch (Exception e) {
			throw e;
		}
		return rentSaveVo;
	}

	@Override
	public RentSaveVO update(RentSaveVO rentSaveVo) throws Exception {
		try {
			RentTransaction rentTransaction = rentTransactionRepo.findOneById(rentSaveVo.getTransactionId());
			rentTransaction.setDescription(rentSaveVo.getDescription());
			rentTransaction.getRentLedger().setIsBankDeposited(true);
			rentTransactionRepo.save(rentTransaction);
			if(rentTransaction.getRentLedger().getGstAmount()!=null && rentTransaction.getRentLedger().getGstAmount() > 0F) {
				GstMainAccount gstMainAccount = gstMainAccountRepo.findOneById(1L);
				gstMainAccount.setGstCollected(gstMainAccount.getGstCollected()+rentTransaction.getRentLedger().getGstAmount());
				gstMainAccountRepo.save(gstMainAccount);
			}
			rentSaveVo.setLedgerId(rentTransaction.getRentLedger().getId());
			rentSaveVo.setTransactionId(rentTransaction.getId());
		} catch (Exception e) {
			throw e;
		}
		return rentSaveVo;
	}

	@Override
	public RentSaveVO voidRent(RentSaveVO rentSaveVo) throws Exception {
		try {
			Long transactionId = rentSaveVo.getTransactionId();
			Long ledgerId = rentSaveVo.getLedgerId();
			rentTransactionRepo.deleteById(transactionId);
			rentLedgerRepo.deleteById(ledgerId);
			/*
			 * RentTransaction rentTransaction =
			 * rentTransactionRepo.findOneById(rentSaveVo.getTransactionId());
			 * rentTransaction.setIsRolledBack(true); rentTransaction.setRolledBackOn(new
			 * Date()); rentTransaction.setOldAmount(rentTransaction.getAmount());
			 * rentTransaction.setAmount(0F);
			 * rentTransaction.getRentLedger().setTotalAmount(0F);
			 * rentTransactionRepo.save(rentTransaction);
			 * rentSaveVo.setLedgerId(rentTransaction.getRentLedger().getId());
			 * rentSaveVo.setTransactionId(rentTransaction.getId());
			 */
		} catch (Exception e) {
			throw e;
		}
		return rentSaveVo;
	}

	@Override
	public List<RentSaveVO> getPendingDepositList() throws Exception {
		List<RentSaveVO> RentSaveVOs = null;
		try {
			List<RentTransaction> rentTransactions = rentTransactionRepo.findByRentLedgerIsBankDeposited(false);
			RentSaveVOs = rentAssembler.getRentSaveVO(rentTransactions);
		} catch (Exception e) {

		}
		return RentSaveVOs;
	}
	
	@Override
	public List<RentSaveVO> getPendingTdsList() throws Exception {
		List<RentSaveVO> RentSaveVOs = null;
		try {
			List<RentLedger> rentLedgerList = rentLedgerRepo.findByIsTdsPaidAndTdsAmountGreaterThan(false, 0F);
			rentLedgerList.addAll(rentLedgerRepo.findByIsTdsPaidAndTdsAmountGreaterThan(null, 0F));
			RentSaveVOs = rentAssembler.getRentSaveVOByRentLedger(rentLedgerList);
		} catch (Exception e) {

		}
		return RentSaveVOs;
	}
	
		
	@Override
	@Transactional(rollbackFor = Throwable.class)
	public void markTdsClaimed(List<Long> rentLedgerIds) throws Exception {
		try {
		for(Long rentLedgerId : rentLedgerIds) {
			RentLedger rentLedger = rentLedgerRepo.findOneById(rentLedgerId);
			if(rentLedger!=null) {
			rentLedger.setIsTdsPaid(true);
			rentLedgerRepo.save(rentLedger);
			}
		}
		} catch (Exception e) {

		}
	}
	
	@Override
	public List<RentSaveVO> getCollection() throws Exception {
		List<RentSaveVO> RentSaveVOs = null;
		try {
			List<RentTransaction> rentTransactions = rentTransactionRepo.findByRentLedgerIsBankDeposited(true);
			RentSaveVOs = rentAssembler.getRentSaveVO(rentTransactions);
		} catch (Exception e) {

		}
		return RentSaveVOs;
	}
	
	@Override
	public List<RentSaveVO> getCollection(ApplicationParam param,Long id) throws Exception {
		List<RentSaveVO> RentSaveVOs = null;
		try {
			List<RentTransaction> rentTransactions = null;
			if(param.equals(ApplicationParam.TENANT)) {
				rentTransactions = rentTransactionRepo.findByRentLedgerIsBankDepositedAndRentLedgerAgreementTenantId(true, id);	
			}
			if(param.equals(ApplicationParam.PROPERTY)) {
				rentTransactions = rentTransactionRepo.findByRentLedgerIsBankDepositedAndRentLedgerAgreementPropertyId(true, id);	
			}
		
			RentSaveVOs = rentAssembler.getRentSaveVO(rentTransactions);
		} catch (Exception e) {

		}
		return RentSaveVOs;
	}
	
	

	@Override
	public List<RentArrearVO> getRentArrears( ApplicationParam arrearParam,Long id) throws Exception {
		List<RentArrearVO> finalList = new ArrayList<RentArrearVO>();
		List<Object[]> previousMonthRentList = null;
		List<Agreement> agreements = null;
		if(arrearParam == ApplicationParam.ALL) {
			previousMonthRentList = rentTransactionRepo.getPreviousMonthRent();
			agreements = agreementRepo.findByIsActive(true);
		}else if(arrearParam == ApplicationParam.PROPERTY){
			previousMonthRentList = rentTransactionRepo.getPreviousMonthRentByProperty(id);
			agreements = agreementRepo.findByIsActiveAndPropertyId(true, id);
		}else if(arrearParam == ApplicationParam.TENANT) {
			previousMonthRentList = rentTransactionRepo.getPreviousMonthRentByTenant(id);
			agreements = agreementRepo.findByIsActiveAndTenantId(true, id);
		}
		
		Boolean includePreviousMonth = false;
		Date arrearEndDate = null;
		
		if (DateUtil.getCurrentDayOfMonth() > rentDueDate) {
			includePreviousMonth = true;
		}
		// get arrearEndDate 
		if(includePreviousMonth) {
			arrearEndDate = DateUtil.getPreviousMonth(new Date());
		}else {
			arrearEndDate = DateUtil.getPreviousMonth(DateUtil.getPreviousMonth(new Date())) ;
		}
		for (Agreement agreement : agreements) {
			RentArrearVO rentArrearVO;
			Date arrearStartDate = null;
			List<AgreementRentHistory> agreementRentHistory = agreement.getAgreementRentHistory();
			for(Object[] item : previousMonthRentList) {
				if(item[1].equals(agreement.getId())) {
					arrearStartDate = DateUtil.getNextMonth((Date)item[0]);
				}
			}
			if(arrearStartDate == null) {
				arrearStartDate = DateUtil.removeTimeFromDate(agreement.getStartDate());
			}
			List<Date> dateList = DateUtil.getMonthsBetweenDateRange(arrearStartDate, arrearEndDate);
			List<DateAmountVO> arrearList = new ArrayList<DateAmountVO>();
			
			for(Date item : dateList) {
				DateAmountVO dateAmountVO = new DateAmountVO();
				dateAmountVO.setDate(item);
			    Boolean flag = true;
			    Float rentAmount = null;
				for(AgreementRentHistory item1 : agreementRentHistory) {
					if(DateUtil.isDateBetweenRange(item,item1.getStateDate(),item1.getEndDate())) {
						flag = false;
						rentAmount = item1.getRentAmount();
					}
			    }
				if(flag) {
					rentAmount = agreement.getRentAmount();
				}
				dateAmountVO.setAmount(rentAmount);
			    arrearList.add(dateAmountVO);
			}
			if(dateList.size()>0) {
				rentArrearVO = new RentArrearVO();
				rentArrearVO.setAgreement(agreementAssembler.removePersistanceObjects(agreement));
				rentArrearVO.setArrearMonthsList(arrearList);
				Float totalAmount = 0F;
				for(DateAmountVO dateAmountVO : arrearList) {
					totalAmount = totalAmount + dateAmountVO.getAmount();
				}
				rentArrearVO.setTotalAmountOverDue(totalAmount);
				finalList.add(rentArrearVO);
			}
		}
		return finalList;
	}

	

}
