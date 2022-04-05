package com.property.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.property.assembler.AgreementAssembler;
import com.property.assembler.TenantAssembler;
import com.property.dao.AgreementRepo;
import com.property.dao.FileRepo;
import com.property.dao.TenantFileRepo;
import com.property.dao.TenantRepo;
import com.property.entity.File;
import com.property.entity.Tenant;
import com.property.entity.TenantFile;
import com.property.model.FileDescription;
import com.property.model.TenantDetailsVO;
import com.property.model.TenantVO;
import com.property.service.ITenantService;
import com.property.util.ApplicationParam;

@Service
public class TenantService implements ITenantService {

	@Autowired
	TenantRepo tenantRepo;
	
	@Autowired
	TenantFileRepo tenantFileRepo;
	
	@Autowired
	AgreementRepo agreementRepo;

	@Autowired
	FileRepo fileRepo;
	
	@Autowired
	RentService rentService;
	
	@Autowired
	TenantAssembler tenantAssembler;
	
	@Autowired
	AgreementAssembler agreementAssembler;
	
	
	@Override
	public Tenant getById(Long id) throws Exception {
		Tenant tenant = null;
		try {
			tenant = tenantRepo.findOneById(id);
		}catch(Exception e) {
			throw e;
		}
		return tenant;
	}
	
	@Override
	public TenantVO save(TenantVO tenantVO) throws Exception {	
		try {
			tenantRepo.save(tenantVO.getTenant());
			if(tenantVO.getFiles()!=null) {
				for(FileDescription fileDescription : tenantVO.getFiles()) {
					TenantFile tenantFile = new TenantFile();
					File file = new File();
					file.setId(fileDescription.getId());
					tenantFile.setFile(file);
					tenantFile.setTenant(tenantVO.getTenant());
					tenantFile.setDescription(fileDescription.getDescription());
					tenantFileRepo.save(tenantFile);
				}	
			}
	
			
		}catch(Exception e) {
			throw e;
		}
		return tenantVO;
	}

	
	@Override
	@Transactional(rollbackFor = Throwable.class)
	public TenantVO update(TenantVO tenantVO) throws Exception {	
		try {
			Tenant tenant = tenantRepo.findOneById(tenantVO.getTenant().getId());
			List<TenantFile>  tenantFiles = new ArrayList<TenantFile>();
			for(TenantFile tenantFile:tenant.getTenantFiles()) {
				tenantFiles.add(tenantFile);
			}
			tenantRepo.save(tenantVO.getTenant());
			

			// add new files
			if(tenantVO.getFiles()!=null) {
			for(FileDescription fileDescription : tenantVO.getFiles()) {
				Boolean addFlag = true;
				for(TenantFile tenantFile : tenantFiles) {
					if(tenantFile.getFile().getId()==fileDescription.getId()) {
						addFlag = false;
						break;
					}
				}
				if(addFlag) {
					TenantFile tenantFile = new TenantFile();
					File file = new File();
					file.setId(fileDescription.getId());
					tenantFile.setFile(file);
					tenantFile.setTenant(tenantVO.getTenant());
					tenantFile.setDescription(fileDescription.getDescription());
					tenantFileRepo.save(tenantFile);
				}	
			}
			}
			
			// delete removed files
			for(TenantFile tenantFile : tenantFiles) {
				Boolean deleteFlag = true;
				for(FileDescription fileDescription : tenantVO.getFiles()) {
					if(tenantFile.getFile().getId()==fileDescription.getId()) {
						deleteFlag = false;
						break;
					}
				}
				if(deleteFlag) {
				tenantFileRepo.deleteById(tenantFile.getId());	
				}
			}
		
		}catch(Exception e) {
			throw e;
		}
		return tenantVO;
	}

	
	@Override
	public Tenant getByCode(String code) throws Exception{
		Tenant tenant = null;
		try {
			tenant = tenantRepo.findByCode(code);
		}catch(Exception e) {
			throw e;
		}
		return tenant;
	}

	@Override
	public List<Tenant> getAll() throws Exception {
		List<Tenant> tenants = null;
		try {
			tenants = tenantRepo.findAll();
		}catch(Exception e) {
			throw e;
		}
		return tenants;
	}
	
	@Override
	public List<TenantVO> getAllWithFiles() throws Exception {
		List<TenantVO> tenantVOList = null;
		try {
			List<Tenant> tenants = tenantRepo.findAll();
			tenantVOList = tenantAssembler.get(tenants);
		}catch(Exception e) {
			throw e;
		}
		return tenantVOList;
	}
	
	@Override
	public TenantVO getWithFiles(Long tenantId) throws Exception {
		TenantVO tenantVO = null;
		try {
			Tenant tenant = tenantRepo.findOneById(tenantId);
			tenantVO = tenantAssembler.get(tenant);
		}catch(Exception e) {
			throw e;
		}
		return tenantVO;
	}
	
	
	@Override
	public TenantDetailsVO getTenantDetails(Long tenantId) throws Exception{
		TenantDetailsVO tenantDetailsVO = new TenantDetailsVO();
		try {
			tenantDetailsVO.setTenant(this.getWithFiles(tenantId));
			tenantDetailsVO.setAgreementList(agreementAssembler.get(agreementRepo.findByIsActiveAndTenantId(true, tenantId)));
			tenantDetailsVO.setArrears(rentService.getRentArrears(ApplicationParam.TENANT,tenantId));
			tenantDetailsVO.setPaymentHistory(rentService.getCollection(ApplicationParam.TENANT, tenantId));
		}catch(Exception e) {
			throw e;
		}
		return tenantDetailsVO;
	}

}
