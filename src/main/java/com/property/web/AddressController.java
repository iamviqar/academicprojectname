package com.property.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.property.entity.City;
import com.property.entity.Location;
import com.property.entity.State;
import com.property.model.ServiceResponse;
import com.property.service.ICityService;
import com.property.service.ILocationService;
import com.property.service.IPropertyService;
import com.property.service.IStateService;
import com.property.util.ServiceResponseUtils;
import com.property.util.StatusCodes;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/address", produces = MediaType.APPLICATION_JSON_VALUE)
public class AddressController {
	
	@Autowired
	IPropertyService propertyService;
	
	@Autowired
	IStateService stateService;
	
	@Autowired
	ICityService cityService;
	
	@Autowired
	ILocationService locationService;
	
	
	
	@GetMapping(value = "/states/all")
    public @ResponseBody ServiceResponse getAllStates()
                  throws Exception {
          
           ServiceResponse serviceResponse = null;
           try {
        	      List<State> states = stateService.getAll();
        	      serviceResponse = ServiceResponseUtils.dataResponse(StatusCodes.DATA_RETRIEVE_SUCCESS.getCode(),StatusCodes.DATA_RETRIEVE_SUCCESS.getDescription(),states);
           } catch (Exception e) {
                  serviceResponse = ServiceResponseUtils.dataResponse(StatusCodes.DATA_RETRIEVE_FAIL.getCode(),StatusCodes.DATA_RETRIEVE_FAIL.getDescription(), null);
           }
           return serviceResponse;
    }
	
	@GetMapping(value = "/cities/state/{stateId}")
    public @ResponseBody ServiceResponse getCitiesByState(@PathVariable(value = "stateId") Long stateId)
                  throws Exception {
          
           ServiceResponse serviceResponse = null;
           try {
        	      List<City> cities = cityService.getByStateId(stateId);
        	      serviceResponse = ServiceResponseUtils.dataResponse(StatusCodes.DATA_RETRIEVE_SUCCESS.getCode(),StatusCodes.DATA_RETRIEVE_SUCCESS.getDescription(),cities);
           } catch (Exception e) {
                  serviceResponse = ServiceResponseUtils.dataResponse(StatusCodes.DATA_RETRIEVE_FAIL.getCode(),StatusCodes.DATA_RETRIEVE_FAIL.getDescription(), null);
           }
           //log.info("<<<< createTiers()");
           return serviceResponse;
    }
	
	@GetMapping(value = "/locations/city/{cityId}")
    public @ResponseBody ServiceResponse getLocationsByCity(@PathVariable(value = "cityId") Long cityId)
                  throws Exception {
          
           ServiceResponse serviceResponse = null;
           try {
        	      List<Location> locations = locationService.getByCity(cityId);
        	      serviceResponse = ServiceResponseUtils.dataResponse(StatusCodes.DATA_RETRIEVE_SUCCESS.getCode(),StatusCodes.DATA_RETRIEVE_SUCCESS.getDescription(),locations);
           } catch (Exception e) {
                  serviceResponse = ServiceResponseUtils.dataResponse(StatusCodes.DATA_RETRIEVE_FAIL.getCode(),StatusCodes.DATA_RETRIEVE_FAIL.getDescription(), null);
           }
           //log.info("<<<< createTiers()");
           return serviceResponse;
    }
	
	@GetMapping(value = "/all-locations")
    public @ResponseBody ServiceResponse getAllLocations()
                  throws Exception {
          
           ServiceResponse serviceResponse = null;
           try {
        	      List<Location> locations = locationService.getAll();
        	      serviceResponse = ServiceResponseUtils.dataResponse(StatusCodes.DATA_RETRIEVE_SUCCESS.getCode(),StatusCodes.DATA_RETRIEVE_SUCCESS.getDescription(),locations);
           } catch (Exception e) {
                  serviceResponse = ServiceResponseUtils.dataResponse(StatusCodes.DATA_RETRIEVE_FAIL.getCode(),StatusCodes.DATA_RETRIEVE_FAIL.getDescription(), null);
           }
           //log.info("<<<< createTiers()");
           return serviceResponse;
    }
	
}
