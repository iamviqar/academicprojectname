package com.property.service;

import java.util.List;

import com.property.entity.PropertyTax;
import com.property.model.PropertyTaxVO;

public interface IPropertyTaxService {

	public List<PropertyTax> getAll() throws Exception;
	PropertyTax save(PropertyTax propertyTax) throws Exception;
	PropertyTax update(PropertyTax propertyTax) throws Exception;
	PropertyTaxVO save(PropertyTaxVO propertyTaxVO) throws Exception;
	PropertyTaxVO update(PropertyTaxVO propertyTaxVO) throws Exception;
	List<PropertyTaxVO> getAllVos() throws Exception;

}
