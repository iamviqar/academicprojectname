package com.property.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.property.model.ServiceResponse;
import com.property.model.TenantDetailsVO;
import com.property.model.TenantVO;
import com.property.service.ITenantService;
import com.property.util.ServiceResponseUtils;
import com.property.util.StatusCodes;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/tenant", produces = MediaType.APPLICATION_JSON_VALUE)
public class TenantController {
	@Autowired
	ITenantService tenantService;
	
	@GetMapping(value = "/all")
    public @ResponseBody ServiceResponse getAll()
                  throws Exception {
          
           ServiceResponse serviceResponse = null;
           try {
        	      List<TenantVO> properties = tenantService.getAllWithFiles();
        	      
        	      serviceResponse = ServiceResponseUtils.dataResponse(StatusCodes.DATA_RETRIEVE_SUCCESS.getCode(),StatusCodes.DATA_RETRIEVE_SUCCESS.getDescription(),properties);
           } catch (Exception e) {
                  serviceResponse = ServiceResponseUtils.dataResponse(StatusCodes.DATA_RETRIEVE_FAIL.getCode(),StatusCodes.DATA_RETRIEVE_FAIL.getDescription(), null);
           }
           //log.info("<<<< createTiers()");
           return serviceResponse;
    }
	
	@PostMapping(value = "/save")
    public @ResponseBody ServiceResponse save(@RequestBody TenantVO tenant)
                  throws Exception {
          
           ServiceResponse serviceResponse = null;
           try {
        	      tenantService.save(tenant);
        	      serviceResponse = ServiceResponseUtils.dataResponse(StatusCodes.DATA_INSERT_SUCCESS.getCode(),StatusCodes.DATA_INSERT_SUCCESS.getDescription(),tenant);
           } catch (Exception e) {
        	   if(e.getMessage()!=null && e.getMessage().contains("unique")) {
        		   serviceResponse = ServiceResponseUtils.dataResponse(StatusCodes.TENANT_UNIQUE_CODE.getCode(),StatusCodes.TENANT_UNIQUE_CODE.getDescription(), null);
        	   }else {
        		   serviceResponse = ServiceResponseUtils.dataResponse(StatusCodes.DATA_INSERT_FAIL.getCode(),StatusCodes.DATA_INSERT_FAIL.getDescription(), null); 
        	   }
                  
           }
          
           return serviceResponse;
    }
	
	@PostMapping(value = "/update")
    public @ResponseBody ServiceResponse update(@RequestBody TenantVO tenant)
                  throws Exception {
          
           ServiceResponse serviceResponse = null;
           try {
        	      tenantService.update(tenant);
        	      serviceResponse = ServiceResponseUtils.dataResponse(StatusCodes.DATA_INSERT_SUCCESS.getCode(),StatusCodes.DATA_INSERT_SUCCESS.getDescription(),tenant);
           } catch (Exception e) {
        	   if(e.getMessage()!=null && e.getMessage().contains("unique")) {
        		   serviceResponse = ServiceResponseUtils.dataResponse(StatusCodes.TENANT_UNIQUE_CODE.getCode(),StatusCodes.TENANT_UNIQUE_CODE.getDescription(), null);
        	   }else {
        		   serviceResponse = ServiceResponseUtils.dataResponse(StatusCodes.DATA_INSERT_FAIL.getCode(),StatusCodes.DATA_INSERT_FAIL.getDescription(), null); 
        	   }
           }
          
           return serviceResponse;
    }
	
	@GetMapping(value = "/details/{tenantId}")
    public @ResponseBody ServiceResponse viewTenant(@PathVariable(value = "tenantId") Long tenantId)
                  throws Exception {
          
           ServiceResponse serviceResponse = null;
           try {
        	      TenantDetailsVO tenantDetailsVO = tenantService.getTenantDetails(tenantId);
        	      
        	      serviceResponse = ServiceResponseUtils.dataResponse(StatusCodes.DATA_RETRIEVE_SUCCESS.getCode(),StatusCodes.DATA_RETRIEVE_SUCCESS.getDescription(),tenantDetailsVO);
           } catch (Exception e) {
                  serviceResponse = ServiceResponseUtils.dataResponse(StatusCodes.DATA_RETRIEVE_FAIL.getCode(),StatusCodes.DATA_RETRIEVE_FAIL.getDescription(), null);
           }
           
           return serviceResponse;
    }

}
