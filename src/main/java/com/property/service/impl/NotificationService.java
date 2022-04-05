package com.property.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.property.assembler.PropertyAssembler;
import com.property.dao.PropertyRepo;
import com.property.dao.PropertyTaxRepo;
import com.property.entity.Property;
import com.property.model.PropertyTaxNotificationVO;
import com.property.service.INotificationService;
import com.property.util.DateUtil;

@Service
public class NotificationService implements INotificationService {
	
	@Autowired
	PropertyRepo propertyRepo;
	
	@Autowired
	PropertyTaxRepo propertyTaxRepo;
	
	@Autowired
	PropertyAssembler propertyAssembler;
	
	@Value("${taxNotificationStartYear}")
	Integer taxNotificationStartYear;
	
	@Value("${taxNotificationMonth}")
	Integer taxNotificationMonth;

	@Override
	public List<PropertyTaxNotificationVO> getTaxNotification() throws Exception {
		List<PropertyTaxNotificationVO> propertyTaxNotificationVOList = new ArrayList<PropertyTaxNotificationVO>();
		Integer currentYear = DateUtil.getCurrentYear();
		Integer currentMonth = DateUtil.getCurrentMonth();
		Integer taxNotificationEndYear = null;
		Boolean includeCurrentYear = false;
		if(currentMonth>=taxNotificationMonth) {
			includeCurrentYear = true;
		}
		if(includeCurrentYear) {
			taxNotificationEndYear = currentYear;
		}else {
			taxNotificationEndYear = currentYear-1;
		}
		List<Property> propertiesFromDao = propertyRepo.findAll();
		List<Property> properties = propertyAssembler.get(propertiesFromDao);
		for(Property property : properties) {
			PropertyTaxNotificationVO propertyTaxNotificationVO = null;
			List<Integer> yearsFromDao = propertyTaxRepo.findYearsByPropertyId(property.getId());
			List<Integer> years = new ArrayList<Integer>();
			for(Integer i=taxNotificationStartYear ; i<=taxNotificationEndYear ; i++) {
				if(!yearsFromDao.contains(i)) {
					years.add(i);
				}
			}
			if(!years.isEmpty()) {
				propertyTaxNotificationVO = new PropertyTaxNotificationVO();
				propertyTaxNotificationVO.setProperty(property);
				propertyTaxNotificationVO.setYears(years);
				propertyTaxNotificationVOList.add(propertyTaxNotificationVO);
			}
		}
		return propertyTaxNotificationVOList;
	}

	
}
