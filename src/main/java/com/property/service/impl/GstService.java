package com.property.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.property.assembler.AgreementAssembler;
import com.property.assembler.PropertyAssembler;
import com.property.assembler.RentAssembler;
import com.property.assembler.TenantAssembler;
import com.property.dao.GstMainAccountRepo;
import com.property.dao.GstPaidLedgerRepo;
import com.property.dao.RentLedgerRepo;
import com.property.dao.TransactionRepo;
import com.property.entity.GstMainAccount;
import com.property.entity.GstPaidLedger;
import com.property.entity.RentLedger;
import com.property.entity.Transaction;
import com.property.model.GstRecordVO;
import com.property.model.GstVO;
import com.property.model.MainGstVO;
import com.property.model.RentSaveVO;
import com.property.service.IGstService;

@Service
public class GstService implements IGstService {
	@Autowired
	GstPaidLedgerRepo gstPaidLedgerRepo;
	
	@Autowired
	GstMainAccountRepo gstMainAccountRepo;
	
	@Autowired
	RentLedgerRepo rentLedgerRepo;
	
	@Autowired
	TransactionRepo transationRepo;

	@Autowired
	TenantAssembler tenantAssembler;
	
	@Autowired
	PropertyAssembler propertyAssembler;
	
	@Autowired
	AgreementAssembler agreementAssembler;
	
	@Autowired
	RentAssembler rentAssembler;
	
	
	//pending gst report
	@Override
	public MainGstVO getGstRecords() throws Exception {
		MainGstVO mainGstVO = new MainGstVO();
		List<GstRecordVO> gstRecords = new ArrayList<GstRecordVO>();
		try {
			// get mainGstAccount, rent,withdrawal and govt payments
			GstMainAccount gstMainAccount = gstMainAccountRepo.findOneById(1L);
			List<RentLedger> rentLedgerList = rentLedgerRepo.findByIsBankDepositedAndGstAmountGreaterThan(true, 0F);
			List<Transaction> transactionList = transationRepo.findByDepositWithdrawalAndGstGreaterThan('W', 0F);
			List<GstPaidLedger> gstPaidEntries = gstPaidLedgerRepo.findAll();
			mainGstVO.setGstAccount(gstMainAccount);
			for(RentLedger rentLedger : rentLedgerList) {
				GstRecordVO gstRecordVO = new GstRecordVO();
				gstRecordVO.setType("RENT");
				gstRecordVO.setAmount(rentLedger.getGstAmount());
				gstRecordVO.setDate(rentLedger.getMonthYear());
				gstRecordVO.setProperty(propertyAssembler.get(rentLedger.getAgreement().getProperty()));
				gstRecordVO.setTenant(tenantAssembler.get(rentLedger.getAgreement().getTenant()).getTenant());
				gstRecords.add(gstRecordVO);
			}
			for(Transaction transaction : transactionList) {
				GstRecordVO gstRecordVO = new GstRecordVO();
				gstRecordVO.setType("WITHDRAWAL");
				gstRecordVO.setAmount(transaction.getGst());
				gstRecordVO.setInvoice(transaction.getInvoiceNumber());
				gstRecordVO.setProperty(propertyAssembler.get(transaction.getProperty()));
				gstRecordVO.setDate(transaction.getPaidDate());
				gstRecords.add(gstRecordVO);
			}
			for(GstPaidLedger gstPaidLedger : gstPaidEntries) {
				GstRecordVO gstRecordVO = new GstRecordVO();
				gstRecordVO.setType("GOVT");
				gstRecordVO.setDate(gstPaidLedger.getPaidOn());
				gstRecordVO.setAmount(gstPaidLedger.getAmount());
				gstRecords.add(gstRecordVO);
			}
			
			List<GstRecordVO> sortedList = gstRecords.stream().sorted(Comparator.comparing(GstRecordVO::getDate)).collect(Collectors.toList());
			mainGstVO.setGstRecords(sortedList);
		}catch(Exception e) {
			
		}
		return mainGstVO;
	}
	
	@Override
	@Transactional(rollbackFor = Throwable.class)
	public GstPaidLedger save(GstPaidLedger gstPaidLedger) throws Exception {
		try {
			gstPaidLedgerRepo.save(gstPaidLedger);
			GstMainAccount gstMainAccount = gstMainAccountRepo.findOneById(1L);
			gstMainAccount.setGstPaid(gstMainAccount.getGstPaid()+gstPaidLedger.getAmount());
			gstMainAccountRepo.save(gstMainAccount);
		}catch(Exception e) {
			
		}
		return gstPaidLedger;
	}
	
	@Override
	public GstVO getGstInfo() throws Exception {
		GstVO gstVO = new GstVO();
		List<RentSaveVO> rentSaveVOs = null;
		try {
			List<RentLedger> rentLedgerList = rentLedgerRepo.findByIsBankDepositedAndGstAmountGreaterThan(true, 0F);
			//rentLedgerList.addAll(rentLedgerRepo.findByIsBankDepositedAndGstAmountGreaterThan(true, 0F));
			rentSaveVOs = rentAssembler.getRentSaveVOByRentLedger(rentLedgerList);
			GstMainAccount gstMainAccount = gstMainAccountRepo.findOneById(1L);
			gstVO.setGstAccount(gstMainAccount);
			gstVO.setGstRecords(rentSaveVOs);
		} catch (Exception e) {

		}
		return gstVO;
	}


}
