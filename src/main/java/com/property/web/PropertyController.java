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

import com.property.entity.Property;
import com.property.model.PropertyDetailsVO;
import com.property.model.PropertyVO;
import com.property.model.ServiceResponse;
import com.property.service.IPropertyService;
import com.property.util.ServiceResponseUtils;
import com.property.util.StatusCodes;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/property", produces = MediaType.APPLICATION_JSON_VALUE)
public class PropertyController {
	
	@Autowired
	IPropertyService propertyService;
	
	@GetMapping(value = "/all")
    public @ResponseBody ServiceResponse getAll()
                  throws Exception {
          
           ServiceResponse serviceResponse = null;
           try {
        	      List<PropertyVO> properties = propertyService.getAllWithFiles();
        	      
        	      serviceResponse = ServiceResponseUtils.dataResponse(StatusCodes.DATA_RETRIEVE_SUCCESS.getCode(),StatusCodes.DATA_RETRIEVE_SUCCESS.getDescription(),properties);
           } catch (Exception e) {
                  serviceResponse = ServiceResponseUtils.dataResponse(StatusCodes.DATA_RETRIEVE_FAIL.getCode(),StatusCodes.DATA_RETRIEVE_FAIL.getDescription(), null);
           }
           
           return serviceResponse;
    }
	
	@GetMapping(value = "/all/{tenantId}")
    public @ResponseBody ServiceResponse getAllByTenant(@PathVariable(value = "tenantId") Long tenantId)
                  throws Exception {
          
           ServiceResponse serviceResponse = null;
           try {
        	      List<Property> properties = propertyService.getByTenant(tenantId);
        	      serviceResponse = ServiceResponseUtils.dataResponse(StatusCodes.DATA_RETRIEVE_SUCCESS.getCode(),StatusCodes.DATA_RETRIEVE_SUCCESS.getDescription(),properties);
           } catch (Exception e) {
                  serviceResponse = ServiceResponseUtils.dataResponse(StatusCodes.DATA_RETRIEVE_FAIL.getCode(),StatusCodes.DATA_RETRIEVE_FAIL.getDescription(), null);
           }
           
           return serviceResponse;
    }
	
	@PostMapping(value = "/save")
    public @ResponseBody ServiceResponse save(@RequestBody PropertyVO property)
                  throws Exception {
          
           ServiceResponse serviceResponse = null;
           try {
        	      propertyService.save(property);
        	      serviceResponse = ServiceResponseUtils.dataResponse(StatusCodes.DATA_INSERT_SUCCESS.getCode(),StatusCodes.DATA_INSERT_SUCCESS.getDescription(),property);
           } catch (Exception e) {
        	   if(e.getMessage()!=null && e.getMessage().contains("unique")) {
        		   serviceResponse = ServiceResponseUtils.dataResponse(StatusCodes.PROPERTY_UNIQUE_CODE.getCode(),StatusCodes.PROPERTY_UNIQUE_CODE.getDescription(), null);
        	   }else {
        		   serviceResponse = ServiceResponseUtils.dataResponse(StatusCodes.DATA_INSERT_FAIL.getCode(),StatusCodes.DATA_INSERT_FAIL.getDescription(), null); 
        	   }
                  
           }
           
           return serviceResponse;
    }
	
	@PostMapping(value = "/update")
    public @ResponseBody ServiceResponse update(@RequestBody PropertyVO property)
                  throws Exception {
          
           ServiceResponse serviceResponse = null;
           try {
        	      propertyService.update(property);
        	      serviceResponse = ServiceResponseUtils.dataResponse(StatusCodes.DATA_INSERT_SUCCESS.getCode(),StatusCodes.DATA_INSERT_SUCCESS.getDescription(),property);
           } catch (Exception e) {
        	   if(e.getMessage()!=null && e.getMessage().contains("unique")) {
        		   serviceResponse = ServiceResponseUtils.dataResponse(StatusCodes.PROPERTY_UNIQUE_CODE.getCode(),StatusCodes.PROPERTY_UNIQUE_CODE.getDescription(), null);
        	   }else {
        		   serviceResponse = ServiceResponseUtils.dataResponse(StatusCodes.DATA_INSERT_FAIL.getCode(),StatusCodes.DATA_INSERT_FAIL.getDescription(), null); 
        	   }
           }
           
           return serviceResponse;
    }
	
	@GetMapping(value = "/details/{propertyId}")
    public @ResponseBody ServiceResponse viewProperty(@PathVariable(value = "propertyId") Long propertyId)
                  throws Exception {
          
           ServiceResponse serviceResponse = null;
           try {
        	      PropertyDetailsVO propertyDetailsVO = propertyService.getPropertyDetails(propertyId);
        	      
        	      serviceResponse = ServiceResponseUtils.dataResponse(StatusCodes.DATA_RETRIEVE_SUCCESS.getCode(),StatusCodes.DATA_RETRIEVE_SUCCESS.getDescription(),propertyDetailsVO);
           } catch (Exception e) {
                  serviceResponse = ServiceResponseUtils.dataResponse(StatusCodes.DATA_RETRIEVE_FAIL.getCode(),StatusCodes.DATA_RETRIEVE_FAIL.getDescription(), null);
           }
           
           return serviceResponse;
    }
	

}
