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

import com.property.model.RentArrearVO;
import com.property.model.RentSaveVO;
import com.property.model.ServiceResponse;
import com.property.service.IRentService;
import com.property.util.ApplicationParam;
import com.property.util.ServiceResponseUtils;
import com.property.util.StatusCodes;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/rent", produces = MediaType.APPLICATION_JSON_VALUE)
public class RentController {
	
	@Autowired
	IRentService rentService;
	
	@PostMapping(value = "/save")
    public @ResponseBody ServiceResponse save(@RequestBody RentSaveVO rentSaveVO)
                  throws Exception {
          
           ServiceResponse serviceResponse = null;
           try {
        	      rentService.save(rentSaveVO);
        	      serviceResponse = ServiceResponseUtils.dataResponse(StatusCodes.DATA_INSERT_SUCCESS.getCode(),StatusCodes.DATA_INSERT_SUCCESS.getDescription(),rentSaveVO);
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
	
	@PostMapping(value = "/update")
    public @ResponseBody ServiceResponse update(@RequestBody RentSaveVO rentSaveVO)
                  throws Exception {
          
           ServiceResponse serviceResponse = null;
           try {
        	      rentService.update(rentSaveVO);
        	      serviceResponse = ServiceResponseUtils.dataResponse(StatusCodes.DATA_INSERT_SUCCESS.getCode(),StatusCodes.DATA_INSERT_SUCCESS.getDescription(),rentSaveVO);
           } catch (Exception e) {
        	   serviceResponse = ServiceResponseUtils.dataResponse(StatusCodes.DATA_INSERT_FAIL.getCode(),StatusCodes.DATA_INSERT_FAIL.getDescription(), null);          
           }
           return serviceResponse;
    }
	
	@PostMapping(value = "/void-rent")
    public @ResponseBody ServiceResponse voidRent(@RequestBody RentSaveVO rentSaveVO)
                  throws Exception {
          
           ServiceResponse serviceResponse = null;
           try {
        	      rentService.voidRent(rentSaveVO);
        	      serviceResponse = ServiceResponseUtils.dataResponse(StatusCodes.DATA_INSERT_SUCCESS.getCode(),StatusCodes.DATA_INSERT_SUCCESS.getDescription(),rentSaveVO);
           } catch (Exception e) {
        	   serviceResponse = ServiceResponseUtils.dataResponse(StatusCodes.DATA_INSERT_FAIL.getCode(),StatusCodes.DATA_INSERT_FAIL.getDescription(), null);          
           }
           return serviceResponse;
    }
	
	@GetMapping(value = "/pending-deposit")
    public @ResponseBody ServiceResponse getAll()
                  throws Exception {
          
           ServiceResponse serviceResponse = null;
           try {
        	      List<RentSaveVO> rentSaveVOs = rentService.getPendingDepositList();
        	      serviceResponse = ServiceResponseUtils.dataResponse(StatusCodes.DATA_RETRIEVE_SUCCESS.getCode(),StatusCodes.DATA_RETRIEVE_SUCCESS.getDescription(),rentSaveVOs);
           } catch (Exception e) {
                  serviceResponse = ServiceResponseUtils.dataResponse(StatusCodes.DATA_RETRIEVE_FAIL.getCode(),StatusCodes.DATA_RETRIEVE_FAIL.getDescription(), null);
           }
           return serviceResponse;
    }
	
	@GetMapping(value = "/collection")
    public @ResponseBody ServiceResponse getCollection()
                  throws Exception {
          
           ServiceResponse serviceResponse = null;
           try {
        	      List<RentSaveVO> rentSaveVOs = rentService.getCollection();
        	      serviceResponse = ServiceResponseUtils.dataResponse(StatusCodes.DATA_RETRIEVE_SUCCESS.getCode(),StatusCodes.DATA_RETRIEVE_SUCCESS.getDescription(),rentSaveVOs);
           } catch (Exception e) {
                  serviceResponse = ServiceResponseUtils.dataResponse(StatusCodes.DATA_RETRIEVE_FAIL.getCode(),StatusCodes.DATA_RETRIEVE_FAIL.getDescription(), null);
           }
           return serviceResponse;
    }
	
	
	@GetMapping(value = "/arrears")
    public @ResponseBody ServiceResponse getArrears()
                  throws Exception {
          
           ServiceResponse serviceResponse = null;
           try {
        	   List<RentArrearVO>rentSaveVOs = rentService.getRentArrears(ApplicationParam.ALL,null);
        	      serviceResponse = ServiceResponseUtils.dataResponse(StatusCodes.DATA_RETRIEVE_SUCCESS.getCode(),StatusCodes.DATA_RETRIEVE_SUCCESS.getDescription(),rentSaveVOs);
           } catch (Exception e) {
                  serviceResponse = ServiceResponseUtils.dataResponse(StatusCodes.DATA_RETRIEVE_FAIL.getCode(),StatusCodes.DATA_RETRIEVE_FAIL.getDescription(), null);
           }
           return serviceResponse;
    }
	
	@GetMapping(value = "/arrears/{arrearType}/{id}")
    public @ResponseBody ServiceResponse getArrearsByProperty(@PathVariable(value = "arrearType") String arrearType,@PathVariable(value = "id") Long id)
                  throws Exception {
          
           ServiceResponse serviceResponse = null;
           try {
        	   List<RentArrearVO> rentSaveVOs = null;
        	   if(arrearType!=null && arrearType.equalsIgnoreCase("property")) {
        		   rentSaveVOs = rentService.getRentArrears(ApplicationParam.PROPERTY,id);  
        	   }else if(arrearType!=null && arrearType.equalsIgnoreCase("tenant")) {
        		   rentSaveVOs = rentService.getRentArrears(ApplicationParam.TENANT,id);
        	   }
        	    serviceResponse = ServiceResponseUtils.dataResponse(StatusCodes.DATA_RETRIEVE_SUCCESS.getCode(),StatusCodes.DATA_RETRIEVE_SUCCESS.getDescription(),rentSaveVOs);
           } catch (Exception e) {
                  serviceResponse = ServiceResponseUtils.dataResponse(StatusCodes.DATA_RETRIEVE_FAIL.getCode(),StatusCodes.DATA_RETRIEVE_FAIL.getDescription(), null);
           }
           return serviceResponse;
    }
	
}
