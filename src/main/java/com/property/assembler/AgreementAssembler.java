package com.property.assembler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.property.entity.Agreement;
import com.property.entity.AgreementFile;
import com.property.model.AgreementVO;
import com.property.model.FileDescription;

@Component
public class AgreementAssembler {
	
	@Autowired
	TenantAssembler tenantAssembler;
	
	@Autowired
	PropertyAssembler propertyAssembler;
	
	public Agreement removePersistanceObjects(Agreement agreement) {
		Agreement updatedAgreement = new Agreement();
		updatedAgreement.setId(agreement.getId());
		updatedAgreement.setAccountReference(agreement.getAccountReference());
		updatedAgreement.setAdvanceAmount(agreement.getAdvanceAmount());
		updatedAgreement.setCertificateIssueDate(agreement.getCertificateIssueDate());
		updatedAgreement.setCertificateNumber(agreement.getCertificateNumber());
		updatedAgreement.setConsiderationPrice(agreement.getConsiderationPrice());
		updatedAgreement.setContent(agreement.getContent());
		updatedAgreement.setDescriptionOfDocument(agreement.getDescriptionOfDocument());
		updatedAgreement.setEndDate(agreement.getEndDate());
		updatedAgreement.setFirstParty(agreement.getFirstParty());
		updatedAgreement.setIsActive(agreement.getIsActive());
		updatedAgreement.setProperty(propertyAssembler.getVO(agreement.getProperty()).getProperty());
		updatedAgreement.setPurchasedBy(agreement.getPurchasedBy());
		updatedAgreement.setRentAmount(agreement.getRentAmount());
		updatedAgreement.setSecondParty(agreement.getSecondParty());
		updatedAgreement.setStampDutyAmount(agreement.getStampDutyAmount());
		updatedAgreement.setStampDutyPaidBy(agreement.getStampDutyPaidBy());
		updatedAgreement.setStartDate(agreement.getStartDate());
		updatedAgreement.setTenant(tenantAssembler.get(agreement.getTenant()).getTenant());
		updatedAgreement.setUniqueDocReference(agreement.getUniqueDocReference());
		updatedAgreement.setEnhancementPeriod(agreement.getEnhancementPeriod());
		updatedAgreement.setRevisedRentAmount(agreement.getRevisedRentAmount());
		updatedAgreement.setRevisedRentDate(agreement.getRevisedRentDate());
		updatedAgreement.setGstPercentage(agreement.getGstPercentage());
		updatedAgreement.setTdsPercentage(agreement.getTdsPercentage());
		return updatedAgreement;
	}
	
	public List<Agreement> removePersistanceObjects(List<Agreement> agreementList) {
		List<Agreement> updatedagreementList = null;
		if (agreementList != null && !agreementList.isEmpty()) {
			updatedagreementList = new ArrayList<Agreement>();
		}
		for (Agreement agreement : agreementList) {
			updatedagreementList.add(this.removePersistanceObjects(agreement));
		}
		return updatedagreementList;
	}

	public AgreementVO get(Agreement agreement) {
		AgreementVO agreementVO = new AgreementVO();
		List<FileDescription> files = null;
		if (agreement.getAgreementFiles() != null && !agreement.getAgreementFiles().isEmpty()) {
			files = new ArrayList<FileDescription>();
		}
		Agreement updatedAgreement = new Agreement();
		updatedAgreement.setId(agreement.getId());
		updatedAgreement.setAccountReference(agreement.getAccountReference());
		updatedAgreement.setAdvanceAmount(agreement.getAdvanceAmount());
		updatedAgreement.setCertificateIssueDate(agreement.getCertificateIssueDate());
		updatedAgreement.setCertificateNumber(agreement.getCertificateNumber());
		updatedAgreement.setConsiderationPrice(agreement.getConsiderationPrice());
		updatedAgreement.setContent(agreement.getContent());
		updatedAgreement.setDescriptionOfDocument(agreement.getDescriptionOfDocument());
		updatedAgreement.setEndDate(agreement.getEndDate());
		updatedAgreement.setFirstParty(agreement.getFirstParty());
		updatedAgreement.setIsActive(agreement.getIsActive());
		updatedAgreement.setProperty(propertyAssembler.getVO(agreement.getProperty()).getProperty());
		updatedAgreement.setPurchasedBy(agreement.getPurchasedBy());
		updatedAgreement.setRentAmount(agreement.getRentAmount());
		updatedAgreement.setSecondParty(agreement.getSecondParty());
		updatedAgreement.setStampDutyAmount(agreement.getStampDutyAmount());
		updatedAgreement.setStampDutyPaidBy(agreement.getStampDutyPaidBy());
		updatedAgreement.setStartDate(agreement.getStartDate());
		updatedAgreement.setTenant(tenantAssembler.get(agreement.getTenant()).getTenant());
		updatedAgreement.setUniqueDocReference(agreement.getUniqueDocReference());
		updatedAgreement.setRevisedRentAmount(agreement.getRevisedRentAmount());
		updatedAgreement.setRevisedRentDate(agreement.getRevisedRentDate());
		updatedAgreement.setEnhancementPeriod(agreement.getEnhancementPeriod());
		for (AgreementFile agreementFile : agreement.getAgreementFiles()) {
			FileDescription fileDescription = new FileDescription();
			fileDescription.setId(agreementFile.getFile().getId());
			fileDescription.setDescription(agreementFile.getDescription());
			fileDescription.setName(agreementFile.getFile().getName());
			files.add(fileDescription);
		}
		updatedAgreement.setTdsPercentage(agreement.getTdsPercentage());
		updatedAgreement.setGstPercentage(agreement.getGstPercentage());
		agreementVO.setAgreement(updatedAgreement);
		agreementVO.setFiles(files);
		return agreementVO;

	}

	public List<AgreementVO> get(List<Agreement> agreementList) {
		List<AgreementVO> agreementVOList = null;
		if (agreementList != null && !agreementList.isEmpty()) {
			agreementVOList = new ArrayList<AgreementVO>();
		}
		for (Agreement agreement : agreementList) {
			agreementVOList.add(this.get(agreement));
		}

		return agreementVOList;

	}

}
