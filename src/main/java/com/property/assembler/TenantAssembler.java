package com.property.assembler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.property.entity.Tenant;
import com.property.entity.TenantFile;
import com.property.model.FileDescription;
import com.property.model.TenantVO;

@Component
public class TenantAssembler {
	
	public TenantVO get(Tenant tenant) {
		TenantVO tenantVO= new TenantVO();
		List<FileDescription> files = null;
		if(tenant.getTenantFiles()!=null && !tenant.getTenantFiles().isEmpty()) {
			files = new ArrayList<FileDescription>();
		}
		Tenant updatedTenant =  new Tenant();
		updatedTenant.setId(tenant.getId());
		updatedTenant.setAddress(tenant.getAddress());
		updatedTenant.setName(tenant.getName());
		updatedTenant.setCode(tenant.getCode());
		updatedTenant.setMobile(tenant.getMobile());
		updatedTenant.setCity(tenant.getCity());
		updatedTenant.setContactPersonName(tenant.getContactPersonName());
		updatedTenant.setEmail(tenant.getEmail());
		updatedTenant.setGstin(tenant.getGstin());
		for(TenantFile tenantFile : tenant.getTenantFiles()) {
			FileDescription fileDescription = new FileDescription();	
			fileDescription.setId(tenantFile.getFile().getId());
			fileDescription.setDescription(tenantFile.getDescription());
			fileDescription.setName(tenantFile.getFile().getName());
			files.add(fileDescription);
		}
		tenantVO.setTenant(updatedTenant);
		tenantVO.setFiles(files);
		return tenantVO;
		
	}
	
	public List<TenantVO> get(List<Tenant> tenantList) {
		List<TenantVO> tenantVOList = null;
		if(tenantList!=null && !tenantList.isEmpty()) {
			tenantVOList = new ArrayList<TenantVO>();
		}
		for(Tenant tenant : tenantList) {
		    tenantVOList.add(this.get(tenant));
		}
		
		return tenantVOList;
		
	}

}
