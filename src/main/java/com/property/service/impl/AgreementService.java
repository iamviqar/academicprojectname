package com.property.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.property.assembler.AgreementAssembler;
import com.property.dao.AgreementFileRepo;
import com.property.dao.AgreementRentHistoryRepo;
import com.property.dao.AgreementRepo;
import com.property.entity.Agreement;
import com.property.entity.AgreementFile;
import com.property.entity.AgreementRentHistory;
import com.property.entity.File;
import com.property.model.AgreementVO;
import com.property.model.FileDescription;
import com.property.service.IAgreementService;

@Service
public class AgreementService implements IAgreementService {

	@Autowired
	AgreementRepo agreementRepo;
	
	@Autowired
	AgreementFileRepo agreementFileRepo;
	
	@Autowired
	AgreementRentHistoryRepo AgreementRentHistoryrepo;
	
	@Autowired
	AgreementAssembler agreementAssembler;
	
	@Override
	public Agreement getById(Long id) throws Exception {
		Agreement agreement = null;
		try {
			agreement = agreementRepo.findOneById(id);
		}catch(Exception e) {
			throw e;
		}
		return agreement;
	}
	
	@Override
	public Agreement save(Agreement agreement) throws Exception {
		try {
			agreementRepo.save(agreement);
		}catch(Exception e) {
			throw e;
		}
		return agreement;
	}
	
	@Override
	public AgreementVO save(AgreementVO agreement) throws Exception {
		try {
			agreement.getAgreement().setIsActive(true);
			agreementRepo.save(agreement.getAgreement());
			if(agreement.getFiles()!=null) {
				for(FileDescription fileDescription : agreement.getFiles()) {
					AgreementFile agreementFile = new AgreementFile();
					File file = new File();
					file.setId(fileDescription.getId());
					agreementFile.setFile(file);
					agreementFile.setAgreement(agreement.getAgreement());
					agreementFile.setDescription(fileDescription.getDescription());
					agreementFileRepo.save(agreementFile);
				}	
			}
		}catch(Exception e) {
			throw e;
		}
		return agreement;
	}
	
	@Override
	@Transactional(rollbackFor = Throwable.class)
	public AgreementVO update(AgreementVO agreementVO) throws Exception {
		try {
			Agreement agreement = agreementRepo.findOneById(agreementVO.getAgreement().getId());
			List<AgreementFile>  agreementFiles = new ArrayList<AgreementFile>();
			for(AgreementFile agreementFile:agreement.getAgreementFiles()) {
				agreementFiles.add(agreementFile);
			}
			agreementRepo.save(agreementVO.getAgreement());
			

			// add new files
			if(agreementVO.getFiles()!=null) {
			for(FileDescription fileDescription : agreementVO.getFiles()) {
				Boolean addFlag = true;
				for(AgreementFile agreementFile : agreementFiles) {
					if(agreementFile.getFile().getId()==fileDescription.getId()) {
						addFlag = false;
						break;
					}
				}
				if(addFlag) {
					AgreementFile agreementFile = new AgreementFile();
					File file = new File();
					file.setId(fileDescription.getId());
					agreementFile.setFile(file);
					agreementFile.setAgreement(agreementVO.getAgreement());
					agreementFile.setDescription(fileDescription.getDescription());
					agreementFileRepo.save(agreementFile);
				}	
			}
			}
			
			// delete removed files
			for(AgreementFile agreementFile : agreementFiles) {
				Boolean deleteFlag = true;
				for(FileDescription fileDescription : agreementVO.getFiles()) {
					if(agreementFile.getFile().getId()==fileDescription.getId()) {
						deleteFlag = false;
						break;
					}
				}
				if(deleteFlag) {
				agreementFileRepo.deleteById(agreementFile.getId());	
				}
			}
		
		}catch(Exception e) {
			throw e;
		}
		return agreementVO;
	}

	@Override
	public Agreement getByCertificateNumber(String certificateNumber) throws Exception{
		Agreement agreement = null;
		try {
			agreement = agreementRepo.findByCertificateNumber(certificateNumber);
		}catch(Exception e) {
			throw e;
		}
		return agreement;
	}
	
	@Override
	public List<Agreement> getByTenant(Long tenantId) throws Exception{
		List<Agreement> agreementList = null;
		try {
			agreementList = agreementAssembler.removePersistanceObjects(agreementRepo.findByTenantId(tenantId));
		}catch(Exception e) {
			throw e;
		}
		return agreementList;
	}
	
	@Override
	public List<Agreement> getByProperty(Long propertyId) throws Exception{
		List<Agreement> agreementList = null;
		try {
			agreementList = agreementAssembler.removePersistanceObjects(agreementRepo.findByIsActiveAndPropertyId(true, propertyId));
		}catch(Exception e) {
			throw e;
		}
		return agreementList;
	}

	@Override
	public List<Agreement> getAll() throws Exception {
		List<Agreement> properties = null;
		try {
			properties = agreementRepo.findAll();
		}catch(Exception e) {
			throw e;
		}
		return properties;
	}
	
	@Override
	public List<AgreementVO> getAllWithFiles() throws Exception {
		List<AgreementVO> agreementVOList = null;
		try {
			List<Agreement> agreements = agreementRepo.findAll();
			agreementVOList = agreementAssembler.get(agreements);
		}catch(Exception e) {
			throw e;
		}
		return agreementVOList;
	}

	@Override
	public int close(Long agreementId) throws Exception {
		int result = 0;
		try {
			agreementRepo.updateIsActive(false, agreementId);
		}catch(Exception e) {
			throw e;
		}
		return result;
	}
	
	@Override
	@Transactional(rollbackFor = Throwable.class)
	public Boolean reviseRent(Long agreementId) throws Exception {
		Boolean result = false;
		try {
			Agreement agreement = agreementRepo.findOneById(agreementId);
			AgreementRentHistory agreementRentHistory = new AgreementRentHistory();
			agreementRentHistory.setAgreement(agreement);
			if(agreement.getLastRevisedRentDate() == null) {
				agreementRentHistory.setStateDate(agreement.getStartDate());	
			}else {
				agreementRentHistory.setStateDate(agreement.getLastRevisedRentDate());
			}
			agreementRentHistory.setEndDate(agreement.getRevisedRentDate());
			agreementRentHistory.setRentAmount(agreement.getRentAmount());
			AgreementRentHistoryrepo.save(agreementRentHistory);
			agreement.setRentAmount(agreement.getRevisedRentAmount());
			agreement.setLastRevisedRentDate(agreement.getRevisedRentDate());
			agreement.setRevisedRentAmount(null);
			agreement.setRevisedRentDate(null);
			agreementRepo.save(agreement);
			
			result = true;
		}catch(Exception e) {
			throw e;
		}
		return result;
	}

}
