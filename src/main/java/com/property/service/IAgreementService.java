package com.property.service;

import java.util.List;

import com.property.entity.Agreement;
import com.property.model.AgreementVO;

public interface IAgreementService {
	public List<Agreement> getAll() throws Exception;
	public Agreement getById(Long id) throws Exception;
	public Agreement getByCertificateNumber(String certificateNumber) throws Exception;
	public Agreement save(Agreement agreement) throws Exception;
	public List<AgreementVO> getAllWithFiles() throws Exception;
	public AgreementVO save(AgreementVO agreement) throws Exception;
	public AgreementVO update(AgreementVO agreement) throws Exception;
	public List<Agreement> getByTenant(Long tenantId) throws Exception;
	public int close(Long agreementId) throws Exception;
	public List<Agreement> getByProperty(Long propertyId) throws Exception;
	public Boolean reviseRent(Long agreementId) throws Exception;
}
