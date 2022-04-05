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

import com.property.entity.Party;
import com.property.model.ServiceResponse;
import com.property.service.IPartyService;
import com.property.util.ServiceResponseUtils;
import com.property.util.StatusCodes;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/party", produces = MediaType.APPLICATION_JSON_VALUE)
public class PartyController {
	@Autowired
	IPartyService partyService;
	
	@GetMapping(value = "/all")
    public @ResponseBody ServiceResponse getAll()
                  throws Exception {
          
           ServiceResponse serviceResponse = null;
           try {
        	      List<Party> parties = partyService.getAll();
        	      
        	      serviceResponse = ServiceResponseUtils.dataResponse(StatusCodes.DATA_RETRIEVE_SUCCESS.getCode(),StatusCodes.DATA_RETRIEVE_SUCCESS.getDescription(),parties);
           } catch (Exception e) {
                  serviceResponse = ServiceResponseUtils.dataResponse(StatusCodes.DATA_RETRIEVE_FAIL.getCode(),StatusCodes.DATA_RETRIEVE_FAIL.getDescription(), null);
           }
          
           return serviceResponse;
    }
	
	@PostMapping(value = "/save")
    public @ResponseBody ServiceResponse save(@RequestBody Party party)
                  throws Exception {
          
           ServiceResponse serviceResponse = null;
           try {
        	      partyService.save(party);
        	      serviceResponse = ServiceResponseUtils.dataResponse(StatusCodes.DATA_INSERT_SUCCESS.getCode(),StatusCodes.DATA_INSERT_SUCCESS.getDescription(),party);
           } catch (Exception e) {
        	   if(e.getMessage()!=null && e.getMessage().contains("unique")) {
        		   serviceResponse = ServiceResponseUtils.dataResponse(StatusCodes.PARTY_UNIQUE_CODE.getCode(),StatusCodes.PARTY_UNIQUE_CODE.getDescription(), null);
        	   }else {
        		   serviceResponse = ServiceResponseUtils.dataResponse(StatusCodes.DATA_INSERT_FAIL.getCode(),StatusCodes.DATA_INSERT_FAIL.getDescription(), null); 
        	   }
                  
           }
           
           return serviceResponse;
    }
	
	@PostMapping(value = "/update")
    public @ResponseBody ServiceResponse update(@RequestBody Party party)
                  throws Exception {
          
           ServiceResponse serviceResponse = null;
           try {
        	   partyService.update(party);
        	      serviceResponse = ServiceResponseUtils.dataResponse(StatusCodes.DATA_INSERT_SUCCESS.getCode(),StatusCodes.DATA_INSERT_SUCCESS.getDescription(),party);
           } catch (Exception e) {
        	   if(e.getMessage()!=null && e.getMessage().contains("unique")) {
        		   serviceResponse = ServiceResponseUtils.dataResponse(StatusCodes.PARTY_UNIQUE_CODE.getCode(),StatusCodes.PARTY_UNIQUE_CODE.getDescription(), null);
        	   }else {
        		   serviceResponse = ServiceResponseUtils.dataResponse(StatusCodes.DATA_INSERT_FAIL.getCode(),StatusCodes.DATA_INSERT_FAIL.getDescription(), null); 
        	   }
                  
           }
           
           return serviceResponse;
    }

}
