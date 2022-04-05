package com.property.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.property.entity.Agreement;

@Repository
public interface AgreementRepo extends CrudRepository<Agreement, Long> {
    public List<Agreement> findAll();
    
    public List<Agreement> findByTenantId(Long tenantId);
    
    public List<Agreement> findByIsActive(Boolean isActive);
    
    public List<Agreement> findByIsActiveAndPropertyId(Boolean isActive,Long propertyId);
    
    public List<Agreement> findByIsActiveAndTenantId(Boolean isActive,Long tenantId);
	
	public Agreement findOneById(Long id);
	
	public Agreement findByCertificateNumber(String certificateNumber);
	
	@Transactional
	@Modifying
	@Query("update Agreement a set a.isActive=?1 where a.id=?2")
	public int updateIsActive(Boolean isActive,Long agreementId);
	
	
}
