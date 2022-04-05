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

import com.property.model.PropertyMaintenanceVO;
import com.property.model.ServiceResponse;
import com.property.service.IPropertyMaintenanceService;
import com.property.util.ServiceResponseUtils;
import com.property.util.StatusCodes;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/property-maintenance", produces = MediaType.APPLICATION_JSON_VALUE)
public class PropertyMaintenanceController {
	@Autowired
	IPropertyMaintenanceService propertyMaintenanceService;
	
	@GetMapping(value = "/all")
    public @ResponseBody ServiceResponse getAll()
                  throws Exception {
          
           ServiceResponse serviceResponse = null;
           try {
        	      List<PropertyMaintenanceVO> propertyMaintenanceList = propertyMaintenanceService.getAllVos();
        	      
        	      serviceResponse = ServiceResponseUtils.dataResponse(StatusCodes.DATA_RETRIEVE_SUCCESS.getCode(),StatusCodes.DATA_RETRIEVE_SUCCESS.getDescription(),propertyMaintenanceList);
           } catch (Exception e) {
                  serviceResponse = ServiceResponseUtils.dataResponse(StatusCodes.DATA_RETRIEVE_FAIL.getCode(),StatusCodes.DATA_RETRIEVE_FAIL.getDescription(), null);
           }
           //log.info("<<<< createTiers()");
           return serviceResponse;
    }
	
	@PostMapping(value = "/save")
    public @ResponseBody ServiceResponse save(@RequestBody PropertyMaintenanceVO propertyMaintenance)
                  throws Exception {
          
           ServiceResponse serviceResponse = null;
           try {
        	      propertyMaintenanceService.save(propertyMaintenance);
        	      serviceResponse = ServiceResponseUtils.dataResponse(StatusCodes.DATA_INSERT_SUCCESS.getCode(),StatusCodes.DATA_INSERT_SUCCESS.getDescription(),propertyMaintenance);
           } catch (Exception e) {
                  serviceResponse = ServiceResponseUtils.dataResponse(StatusCodes.DATA_INSERT_FAIL.getCode(),StatusCodes.DATA_INSERT_FAIL.getDescription(), null);
           }
           //log.info("<<<< createTiers()");
           return serviceResponse;
    }
	
	@PostMapping(value = "/update")
    public @ResponseBody ServiceResponse update(@RequestBody PropertyMaintenanceVO propertyMaintenance)
                  throws Exception {
          
           ServiceResponse serviceResponse = null;
           try {
        	   propertyMaintenanceService.update(propertyMaintenance);
        	      serviceResponse = ServiceResponseUtils.dataResponse(StatusCodes.DATA_INSERT_SUCCESS.getCode(),StatusCodes.DATA_INSERT_SUCCESS.getDescription(),propertyMaintenance);
           } catch (Exception e) {
                  serviceResponse = ServiceResponseUtils.dataResponse(StatusCodes.DATA_INSERT_FAIL.getCode(),StatusCodes.DATA_INSERT_FAIL.getDescription(), null);
           }
           //log.info("<<<< createTiers()");
           return serviceResponse;
    }

}
