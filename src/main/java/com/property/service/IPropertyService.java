package com.property.service;

import java.util.List;

import com.property.entity.Property;
import com.property.model.PropertyDetailsVO;
import com.property.model.PropertyVO;

public interface IPropertyService {

	public List<Property> getAll() throws Exception;
	public Property getById(Long id) throws Exception;
	public List<Property> getByTenant(Long tenantId) throws Exception;
	public Property getByCode(String code) throws Exception;
	public PropertyVO save(PropertyVO propertyVO) throws Exception;
	public PropertyVO update(PropertyVO propertyVO) throws Exception;
	public PropertyDetailsVO getPropertyDetails(Long propertyId) throws Exception;
	public List<PropertyVO> getAllWithFiles() throws Exception;
	public PropertyVO getWithFiles(Long propertyId) throws Exception;
	
}
