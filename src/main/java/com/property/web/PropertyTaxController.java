package com.property.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.property.model.PropertyTaxVO;
import com.property.model.ServiceResponse;
import com.property.service.IPropertyTaxService;
import com.property.util.ServiceResponseUtils;
import com.property.util.StatusCodes;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/property-tax", produces = MediaType.APPLICATION_JSON_VALUE)
public class PropertyTaxController {
	@Autowired
	IPropertyTaxService propertyTaxService;
	
	@GetMapping(value = "/all")
    public @ResponseBody ServiceResponse getAll()
                  throws Exception {
          
           ServiceResponse serviceResponse = null;
           try {
        	      List<PropertyTaxVO> propertyTaxList = propertyTaxService.getAllVos();
        	      
        	      serviceResponse = ServiceResponseUtils.dataResponse(StatusCodes.DATA_RETRIEVE_SUCCESS.getCode(),StatusCodes.DATA_RETRIEVE_SUCCESS.getDescription(),propertyTaxList);
           } catch (Exception e) {
                  serviceResponse = ServiceResponseUtils.dataResponse(StatusCodes.DATA_RETRIEVE_FAIL.getCode(),StatusCodes.DATA_RETRIEVE_FAIL.getDescription(), null);
           }
           //log.info("<<<< createTiers()");
           return serviceResponse;
    }
	
	@PostMapping(value = "/save")
    public @ResponseBody ServiceResponse save(@RequestBody PropertyTaxVO propertyTax)
                  throws Exception {
          
           ServiceResponse serviceResponse = null;
           try {
        	      propertyTaxService.save(propertyTax);
        	      serviceResponse = ServiceResponseUtils.dataResponse(StatusCodes.DATA_INSERT_SUCCESS.getCode(),StatusCodes.DATA_INSERT_SUCCESS.getDescription(),propertyTax);
           } catch (Exception e) {
                  serviceResponse = ServiceResponseUtils.dataResponse(StatusCodes.DATA_INSERT_FAIL.getCode(),StatusCodes.DATA_INSERT_FAIL.getDescription(), null);
           }
           //log.info("<<<< createTiers()");
           return serviceResponse;
    }
	
	@PostMapping(value = "/update")
    public @ResponseBody ServiceResponse update(@RequestBody PropertyTaxVO propertyTax)
                  throws Exception {
          
           ServiceResponse serviceResponse = null;
           try {
        	   propertyTaxService.update(propertyTax);
        	      serviceResponse = ServiceResponseUtils.dataResponse(StatusCodes.DATA_INSERT_SUCCESS.getCode(),StatusCodes.DATA_INSERT_SUCCESS.getDescription(),propertyTax);
           } catch (Exception e) {
                  serviceResponse = ServiceResponseUtils.dataResponse(StatusCodes.DATA_INSERT_FAIL.getCode(),StatusCodes.DATA_INSERT_FAIL.getDescription(), null);
           }
           //log.info("<<<< createTiers()");
           return serviceResponse;
    }

}
