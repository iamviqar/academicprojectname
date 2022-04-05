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

import com.property.entity.Agreement;
import com.property.model.AgreementVO;
import com.property.model.ServiceResponse;
import com.property.service.IAgreementService;
import com.property.util.ServiceResponseUtils;
import com.property.util.StatusCodes;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/agreement", produces = MediaType.APPLICATION_JSON_VALUE)
public class AgreementController {
	
	@Autowired
	IAgreementService agreementService;
	
	@GetMapping(value = "/all")
    public @ResponseBody ServiceResponse getAll()
                  throws Exception {
          
           ServiceResponse serviceResponse = null;
           try {
        	      List<AgreementVO> agreements = agreementService.getAllWithFiles();
        	      serviceResponse = ServiceResponseUtils.dataResponse(StatusCodes.DATA_RETRIEVE_SUCCESS.getCode(),StatusCodes.DATA_RETRIEVE_SUCCESS.getDescription(),agreements);
           } catch (Exception e) {
                  serviceResponse = ServiceResponseUtils.dataResponse(StatusCodes.DATA_RETRIEVE_FAIL.getCode(),StatusCodes.DATA_RETRIEVE_FAIL.getDescription(), null);
           }
           return serviceResponse;
    }
	
	@GetMapping(value = "/tenant/{tenantId}")
    public @ResponseBody ServiceResponse getByTenant(@PathVariable(value = "tenantId") Long tenantId)
                  throws Exception {
          
           ServiceResponse serviceResponse = null;
           try {
        	      List<Agreement> agreements = agreementService.getByTenant(tenantId);
        	      serviceResponse = ServiceResponseUtils.dataResponse(StatusCodes.DATA_RETRIEVE_SUCCESS.getCode(),StatusCodes.DATA_RETRIEVE_SUCCESS.getDescription(),agreements);
           } catch (Exception e) {
                  serviceResponse = ServiceResponseUtils.dataResponse(StatusCodes.DATA_RETRIEVE_FAIL.getCode(),StatusCodes.DATA_RETRIEVE_FAIL.getDescription(), null);
           }
           return serviceResponse;
    }
	
	@GetMapping(value = "/revise-rent/{agreeementId}")
    public @ResponseBody ServiceResponse reviseRent(@PathVariable(value = "agreeementId") Long agreeementId)
                  throws Exception {
          
           ServiceResponse serviceResponse = null;
           try {
        	    //  List<Agreement> agreements = agreementService.getByTenant(tenantId);
        	   Boolean result = agreementService.reviseRent(agreeementId);
        	      serviceResponse = ServiceResponseUtils.dataResponse(StatusCodes.DATA_RETRIEVE_SUCCESS.getCode(),StatusCodes.DATA_RETRIEVE_SUCCESS.getDescription(),result);
           } catch (Exception e) {
                  serviceResponse = ServiceResponseUtils.dataResponse(StatusCodes.DATA_RETRIEVE_FAIL.getCode(),StatusCodes.DATA_RETRIEVE_FAIL.getDescription(), null);
           }
           return serviceResponse;
    }
	
	@GetMapping(value = "/property/{propertyId}")
    public @ResponseBody ServiceResponse getByProperty(@PathVariable(value = "propertyId") Long propertyId)
                  throws Exception {
          
           ServiceResponse serviceResponse = null;
           try {
        	      List<Agreement> agreements = agreementService.getByProperty(propertyId);
        	      serviceResponse = ServiceResponseUtils.dataResponse(StatusCodes.DATA_RETRIEVE_SUCCESS.getCode(),StatusCodes.DATA_RETRIEVE_SUCCESS.getDescription(),agreements);
           } catch (Exception e) {
                  serviceResponse = ServiceResponseUtils.dataResponse(StatusCodes.DATA_RETRIEVE_FAIL.getCode(),StatusCodes.DATA_RETRIEVE_FAIL.getDescription(), null);
           }
           return serviceResponse;
    }
	
	@GetMapping(value = "/close/{agreementId}")
    public @ResponseBody ServiceResponse close(@PathVariable(value = "agreementId") Long agreementId)
                  throws Exception {
          
           ServiceResponse serviceResponse = null;
           try {
        	   
        	      agreementService.close(agreementId);
        	      serviceResponse = ServiceResponseUtils.dataResponse(StatusCodes.DATA_UPDATE_SUCCESS.getCode(),StatusCodes.DATA_UPDATE_SUCCESS.getDescription(),null);
           } catch (Exception e) {
                  serviceResponse = ServiceResponseUtils.dataResponse(StatusCodes.DATA_UPDATE_FAIL.getCode(),StatusCodes.DATA_UPDATE_FAIL.getDescription(), null);
           }
           return serviceResponse;
    }
	
	@PostMapping(value = "/save")
    public @ResponseBody ServiceResponse save(@RequestBody AgreementVO agreement)
                  throws Exception {
          
           ServiceResponse serviceResponse = null;
           try {
        	      agreementService.save(agreement);
        	      serviceResponse = ServiceResponseUtils.dataResponse(StatusCodes.DATA_INSERT_SUCCESS.getCode(),StatusCodes.DATA_INSERT_SUCCESS.getDescription(),agreement);
           } catch (Exception e) {
        	   if(e.getMessage()!=null && e.getMessage().contains("unique")) {
        		   serviceResponse = ServiceResponseUtils.dataResponse(StatusCodes.AGREEMENT_UNIQUE_CERTIFICATE.getCode(),StatusCodes.AGREEMENT_UNIQUE_CERTIFICATE.getDescription(), null);
        	   }else {
        		   serviceResponse = ServiceResponseUtils.dataResponse(StatusCodes.DATA_INSERT_FAIL.getCode(),StatusCodes.DATA_INSERT_FAIL.getDescription(), null); 
        	   }
                  
           }
        
           return serviceResponse;
    }
	
	@PostMapping(value = "/update")
    public @ResponseBody ServiceResponse update(@RequestBody AgreementVO agreement)
                  throws Exception {
          
           ServiceResponse serviceResponse = null;
           try {
        	      agreementService.update(agreement);
        	      serviceResponse = ServiceResponseUtils.dataResponse(StatusCodes.DATA_INSERT_SUCCESS.getCode(),StatusCodes.DATA_INSERT_SUCCESS.getDescription(),agreement);
           } catch (Exception e) {
        	   if(e.getMessage()!=null && e.getMessage().contains("unique")) {
        		   serviceResponse = ServiceResponseUtils.dataResponse(StatusCodes.AGREEMENT_UNIQUE_CERTIFICATE.getCode(),StatusCodes.AGREEMENT_UNIQUE_CERTIFICATE.getDescription(), null);
        	   }else {
        		   serviceResponse = ServiceResponseUtils.dataResponse(StatusCodes.DATA_INSERT_FAIL.getCode(),StatusCodes.DATA_INSERT_FAIL.getDescription(), null); 
        	   }
                  
           }
           return serviceResponse;
    }

}
