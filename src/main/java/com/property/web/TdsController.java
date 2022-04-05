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

import com.property.model.RentSaveVO;
import com.property.model.ServiceResponse;
import com.property.service.IRentService;
import com.property.util.ServiceResponseUtils;
import com.property.util.StatusCodes;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/tds", produces = MediaType.APPLICATION_JSON_VALUE)
public class TdsController {
	
	@Autowired
	IRentService rentService;
	
		
	@GetMapping(value = "/pending/all")
    public @ResponseBody ServiceResponse getAllStates()
                  throws Exception {
          
           ServiceResponse serviceResponse = null;
           try {
        	      List<RentSaveVO> rentSaveVOList =  rentService.getPendingTdsList();
        	      serviceResponse = ServiceResponseUtils.dataResponse(StatusCodes.DATA_RETRIEVE_SUCCESS.getCode(),StatusCodes.DATA_RETRIEVE_SUCCESS.getDescription(),rentSaveVOList);
           } catch (Exception e) {
                  serviceResponse = ServiceResponseUtils.dataResponse(StatusCodes.DATA_RETRIEVE_FAIL.getCode(),StatusCodes.DATA_RETRIEVE_FAIL.getDescription(), null);
           }
           return serviceResponse;
    }
	
	@PostMapping(value = "/mark-as-claimed")
    public @ResponseBody ServiceResponse save(@RequestBody List<Long> ledgerIds)
                  throws Exception {
          
           ServiceResponse serviceResponse = null;
           try {
        	     rentService.markTdsClaimed(ledgerIds);
        	      serviceResponse = ServiceResponseUtils.dataResponse(StatusCodes.DATA_INSERT_SUCCESS.getCode(),StatusCodes.DATA_INSERT_SUCCESS.getDescription(),ledgerIds);
           } catch (Exception e) {
        	   if(e.getMessage().contains("Rent Already")) {
        		   serviceResponse = ServiceResponseUtils.dataResponse(StatusCodes.RENT_ALREADY_MARKED.getCode(),StatusCodes.RENT_ALREADY_MARKED.getDescription(), null);
        	   }else if(e.getMessage().contains("Previous Month")) {
        		   serviceResponse = ServiceResponseUtils.dataResponse(StatusCodes.RENT_PREVIOUS_UNPAID.getCode(),StatusCodes.RENT_PREVIOUS_UNPAID.getDescription(), null);
        	   } else {
        		   serviceResponse = ServiceResponseUtils.dataResponse(StatusCodes.DATA_INSERT_FAIL.getCode(),StatusCodes.DATA_INSERT_FAIL.getDescription(), null); 
        	   }
                  
           }
           return serviceResponse;
    }
	
}
