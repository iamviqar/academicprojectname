package com.property.service;

import java.util.List;

import com.property.entity.PropertyMaintenance;
import com.property.model.PropertyMaintenanceVO;

public interface IPropertyMaintenanceService {

	public List<PropertyMaintenance> getAll() throws Exception;
	public List<PropertyMaintenanceVO> getAllVos() throws Exception;
	PropertyMaintenance save(PropertyMaintenance propertyMaintenance) throws Exception;
	PropertyMaintenance update(PropertyMaintenance propertyMaintenance) throws Exception;
	PropertyMaintenanceVO save(PropertyMaintenanceVO propertyMaintenanceVO) throws Exception;
	PropertyMaintenanceVO update(PropertyMaintenanceVO propertyMaintenanceVO) throws Exception;

}
