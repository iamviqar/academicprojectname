package com.property.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.property.entity.User;
import com.property.model.ServiceResponse;
import com.property.service.impl.UserService;
import com.property.util.ServiceResponseUtils;
import com.property.util.StatusCodes;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
	@Autowired
	UserService userService;
	
	@PostMapping(value = "/signup")
    public @ResponseBody ServiceResponse signUp(@RequestBody User user)
                  throws Exception {
          
           ServiceResponse serviceResponse = null;
           try {
        	   userService.signUp(user);
        	      serviceResponse = ServiceResponseUtils.dataResponse(StatusCodes.DATA_INSERT_SUCCESS.getCode(),StatusCodes.DATA_INSERT_SUCCESS.getDescription(),user);
           } catch (Exception e) {
        	   if(e.getMessage().contains("unique_user_name")) {
        		   serviceResponse = ServiceResponseUtils.dataResponse(StatusCodes.USERNAME_UNIQUE.getCode(),StatusCodes.USERNAME_UNIQUE.getDescription(), null);
        	   }else {
        		   serviceResponse = ServiceResponseUtils.dataResponse(StatusCodes.DATA_INSERT_FAIL.getCode(),StatusCodes.DATA_INSERT_FAIL.getDescription(), null); 
        	   }
                  
           }
           
           return serviceResponse;
    }
	
	@PostMapping(value = "/login")
    public @ResponseBody ServiceResponse login(@RequestBody User user)
                  throws Exception {
          
           ServiceResponse serviceResponse = null;
           try {
        	   User authUser = userService.login(user);
        	      user.setId(authUser.getId());
        	      user.setUserRole(authUser.getUserRole());
        	      serviceResponse = ServiceResponseUtils.dataResponse(StatusCodes.AUTH_SUCCESS.getCode(),StatusCodes.AUTH_SUCCESS.getDescription(),user);
           } catch (Exception e) {
                  serviceResponse = ServiceResponseUtils.dataResponse(StatusCodes.AUTH_FAIL.getCode(),StatusCodes.AUTH_FAIL.getDescription(), null);
           }
          
           return serviceResponse;
    }
}
