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

import com.property.entity.Transaction;
import com.property.model.AccountSummaryVO;
import com.property.model.ServiceResponse;
import com.property.service.ITransactionService;
import com.property.util.ServiceResponseUtils;
import com.property.util.StatusCodes;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/transaction", produces = MediaType.APPLICATION_JSON_VALUE)
public class TransactionController {


	@Autowired
	ITransactionService transactionService;
	
	@PostMapping(value = "/deposit")
    public @ResponseBody ServiceResponse deposit(@RequestBody Transaction transaction)
                  throws Exception {
          
           ServiceResponse serviceResponse = null;
           try {
        	   transactionService.saveDeposit(transaction);
        	      serviceResponse = ServiceResponseUtils.dataResponse(StatusCodes.DATA_INSERT_SUCCESS.getCode(),StatusCodes.DATA_INSERT_SUCCESS.getDescription(),transaction);
           } catch (Exception e) {
        	   serviceResponse = ServiceResponseUtils.dataResponse(StatusCodes.DATA_INSERT_FAIL.getCode(),StatusCodes.DATA_INSERT_FAIL.getDescription(), null);                  
           }
           return serviceResponse;
    }
	
	@PostMapping(value = "/withdraw")
    public @ResponseBody ServiceResponse withdraw(@RequestBody Transaction transaction)
                  throws Exception {
          
           ServiceResponse serviceResponse = null;
           try {
        	     transactionService.saveWithdraw(transaction);
        	      serviceResponse = ServiceResponseUtils.dataResponse(StatusCodes.DATA_INSERT_SUCCESS.getCode(),StatusCodes.DATA_INSERT_SUCCESS.getDescription(),transaction);
           } catch (Exception e) {
        	   serviceResponse = ServiceResponseUtils.dataResponse(StatusCodes.DATA_INSERT_FAIL.getCode(),StatusCodes.DATA_INSERT_FAIL.getDescription(), null);             
           }
           return serviceResponse;
    }
	
	@GetMapping(value = "/summary")
    public @ResponseBody ServiceResponse summary()
                  throws Exception {
          
           ServiceResponse serviceResponse = null;
           try {
        	      List<AccountSummaryVO> accountSummaryVOs = transactionService.getAccountSummary();
        	      serviceResponse = ServiceResponseUtils.dataResponse(StatusCodes.DATA_RETRIEVE_SUCCESS.getCode(),StatusCodes.DATA_RETRIEVE_SUCCESS.getDescription(),accountSummaryVOs);
           } catch (Exception e) {
                  serviceResponse = ServiceResponseUtils.dataResponse(StatusCodes.DATA_RETRIEVE_FAIL.getCode(),StatusCodes.DATA_RETRIEVE_FAIL.getDescription(), null);
           }
           
           return serviceResponse;
    }
	
	@GetMapping(value = "/available-balance")
    public @ResponseBody ServiceResponse availableBalance()
                  throws Exception {
          
           ServiceResponse serviceResponse = null;
           try {
        	      Float total = transactionService.getAvailableBalance();
        	      serviceResponse = ServiceResponseUtils.dataResponse(StatusCodes.DATA_RETRIEVE_SUCCESS.getCode(),StatusCodes.DATA_RETRIEVE_SUCCESS.getDescription(),total);
           } catch (Exception e) {
                  serviceResponse = ServiceResponseUtils.dataResponse(StatusCodes.DATA_RETRIEVE_FAIL.getCode(),StatusCodes.DATA_RETRIEVE_FAIL.getDescription(), null);
           }
           
           return serviceResponse;
    }
	
	
	
}
