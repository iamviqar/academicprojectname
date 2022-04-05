package com.property.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.property.entity.GstPaidLedger;
import com.property.model.GstVO;
import com.property.model.MainGstVO;
import com.property.model.ServiceResponse;
import com.property.service.IGstService;
import com.property.service.IRentService;
import com.property.util.ServiceResponseUtils;
import com.property.util.StatusCodes;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/gst", produces = MediaType.APPLICATION_JSON_VALUE)
public class GstController {
	
	@Autowired
	IRentService rentService;
	
	@Autowired
	IGstService gstService;
		
	@GetMapping(value = "/all")
    public @ResponseBody ServiceResponse getAllRecords()
                  throws Exception {
          
           ServiceResponse serviceResponse = null;
           try {
        	      GstVO gstVO = gstService.getGstInfo();
        	      serviceResponse = ServiceResponseUtils.dataResponse(StatusCodes.DATA_RETRIEVE_SUCCESS.getCode(),StatusCodes.DATA_RETRIEVE_SUCCESS.getDescription(),gstVO);
           } catch (Exception e) {
                  serviceResponse = ServiceResponseUtils.dataResponse(StatusCodes.DATA_RETRIEVE_FAIL.getCode(),StatusCodes.DATA_RETRIEVE_FAIL.getDescription(), null);
           }
           return serviceResponse;
    }
	
	@GetMapping(value = "/report")
    public @ResponseBody ServiceResponse getGstReport()
                  throws Exception {
          
           ServiceResponse serviceResponse = null;
           try {
        	   MainGstVO mainGstVO = gstService.getGstRecords();
        	      serviceResponse = ServiceResponseUtils.dataResponse(StatusCodes.DATA_RETRIEVE_SUCCESS.getCode(),StatusCodes.DATA_RETRIEVE_SUCCESS.getDescription(),mainGstVO);
           } catch (Exception e) {
                  serviceResponse = ServiceResponseUtils.dataResponse(StatusCodes.DATA_RETRIEVE_FAIL.getCode(),StatusCodes.DATA_RETRIEVE_FAIL.getDescription(), null);
           }
           return serviceResponse;
    }
	
	@PostMapping(value = "/save")
    public @ResponseBody ServiceResponse update(@RequestBody GstPaidLedger gstPaidLedger)
                  throws Exception {
          
           ServiceResponse serviceResponse = null;
           try {
        	   gstService.save(gstPaidLedger);
        	      serviceResponse = ServiceResponseUtils.dataResponse(StatusCodes.DATA_INSERT_SUCCESS.getCode(),StatusCodes.DATA_INSERT_SUCCESS.getDescription(),gstPaidLedger);
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
