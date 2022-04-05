package com.property.util;

import java.io.Serializable;

import com.property.model.ServiceResponse;


public class ServiceResponseUtils implements Serializable {

  private static final long serialVersionUID = 1L;
  private static ServiceResponse serviceResponse;

  /**
   * This method used to get dataResponse as output.
   * 
   * @param code
   *          code
   * @param description
   *          description
   * @param type
   *          type
   * @return serviceResponse
   */
  public static ServiceResponse dataResponse(String code, String description, Object type) {
    serviceResponse = new ServiceResponse();
    serviceResponse.setStatusCode(code);
    serviceResponse.setDescription(description);
    serviceResponse.setData(type);
    return serviceResponse;

  }

}
