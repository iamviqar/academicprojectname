package com.property.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.property.model.PropertyTaxNotificationVO;
import com.property.model.PropertyTaxVO;
import com.property.model.ServiceResponse;
import com.property.service.INotificationService;
import com.property.util.ServiceResponseUtils;
import com.property.util.StatusCodes;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/notification", produces = MediaType.APPLICATION_JSON_VALUE)
public class NotificationController {
	
	@Autowired
	INotificationService notificationService;
	
		
	@GetMapping(value = "/property-tax")
    public @ResponseBody ServiceResponse getAllRecords()
                  throws Exception {
          
           ServiceResponse serviceResponse = null;
           try {
        	      List<PropertyTaxNotificationVO> propertyTaxNotificationVOs= notificationService.getTaxNotification();
        	      serviceResponse = ServiceResponseUtils.dataResponse(StatusCodes.DATA_RETRIEVE_SUCCESS.getCode(),StatusCodes.DATA_RETRIEVE_SUCCESS.getDescription(),propertyTaxNotificationVOs);
           } catch (Exception e) {
                  serviceResponse = ServiceResponseUtils.dataResponse(StatusCodes.DATA_RETRIEVE_FAIL.getCode(),StatusCodes.DATA_RETRIEVE_FAIL.getDescription(), null);
           }
           return serviceResponse;
    }
	
		
}
