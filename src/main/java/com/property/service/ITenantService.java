package com.property.service;

import java.util.List;

import com.property.entity.Tenant;
import com.property.model.TenantDetailsVO;
import com.property.model.TenantVO;

public interface ITenantService {

	public List<Tenant> getAll() throws Exception;
	public Tenant getById(Long id) throws Exception;
	public Tenant getByCode(String code) throws Exception;
	public TenantVO save(TenantVO tenantVO) throws Exception;
	public TenantVO update(TenantVO tenantVO) throws Exception;
	public List<TenantVO> getAllWithFiles() throws Exception;
	public TenantDetailsVO getTenantDetails(Long tenantId) throws Exception;
	public TenantVO getWithFiles(Long tenantId) throws Exception;
	
}
