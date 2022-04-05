package com.property.assembler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.property.entity.PropertyTax;
import com.property.entity.PropertyTaxFile;
import com.property.model.FileDescription;
import com.property.model.PropertyTaxVO;

@Component
public class PropertyTaxAssembler {
	@Autowired
	PropertyAssembler propertyAssembler;
	
	public PropertyTax get(PropertyTax propertyTax) {
		PropertyTax updatedPropertyTax = new PropertyTax();
		updatedPropertyTax.setAmount(propertyTax.getAmount());
		updatedPropertyTax.setId(propertyTax.getId());
		updatedPropertyTax.setPaidDate(propertyTax.getPaidDate());
		updatedPropertyTax.setPaymentMode(propertyTax.getPaymentMode());
		updatedPropertyTax.setProperty(propertyAssembler.get(propertyTax.getProperty()));
		updatedPropertyTax.setPaidYear(propertyTax.getPaidYear());
		return updatedPropertyTax;
	}
	
	public PropertyTaxVO getVO(PropertyTax propertyTax) {
		PropertyTaxVO propertyTaxVO = new PropertyTaxVO();
		propertyTaxVO.setPropertyTax(this.get(propertyTax));
		List<FileDescription> files = null;
		if(propertyTax.getPropertyTaxFiles()!=null && !propertyTax.getPropertyTaxFiles().isEmpty()) {
			files = new ArrayList<FileDescription>();
		}
		for(PropertyTaxFile propertyFile : propertyTax.getPropertyTaxFiles()) {
			FileDescription fileDescription = new FileDescription();	
			fileDescription.setId(propertyFile.getFile().getId());
			fileDescription.setDescription(propertyFile.getDescription());
			fileDescription.setName(propertyFile.getFile().getName());
			files.add(fileDescription);
		}
		propertyTaxVO.setFiles(files);
		return propertyTaxVO;
	}
	
	public List<PropertyTaxVO> getVOs(List<PropertyTax> propertyTaxList) {
		List<PropertyTaxVO> propertyTaxVOList = null;
	
		if(propertyTaxList!=null && !propertyTaxList.isEmpty()) {
			propertyTaxVOList = new ArrayList<PropertyTaxVO>();
		}
		
		for(PropertyTax propertyTax : propertyTaxList) {
			propertyTaxVOList.add(this.getVO(propertyTax));
		}
		
		return propertyTaxVOList;
	}
	
}
