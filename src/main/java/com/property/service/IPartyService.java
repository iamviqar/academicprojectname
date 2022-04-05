package com.property.service;

import java.util.List;

import com.property.entity.Party;

public interface IPartyService {

	public List<Party> getAll() throws Exception;
	Party save(Party party) throws Exception;
	Party update(Party party) throws Exception;
	
	
}
