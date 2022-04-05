package com.property.service;

import java.util.List;

import com.property.model.PropertyTaxNotificationVO;

public interface INotificationService {

	public List<PropertyTaxNotificationVO> getTaxNotification() throws Exception;
	
}
