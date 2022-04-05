package com.property.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.property.dao.PartyRepo;
import com.property.entity.Party;
import com.property.service.IPartyService;

@Service
public class PartyService implements IPartyService {

	@Autowired
	PartyRepo partyRepo;
	
	
	@Override
	public Party save(Party party) throws Exception {	
		try {
			partyRepo.save(party);		
		}catch(Exception e) {
			throw e;
		}
		return party;
	}

	
	@Override
	public Party update(Party party) throws Exception {	
		try {
			partyRepo.save(party);		
		}catch(Exception e) {
			throw e;
		}
		return party;
	}


	@Override
	public List<Party> getAll() throws Exception {
		List<Party> parties = null;
		try {
			parties = partyRepo.findAll();
		}catch(Exception e) {
			throw e;
		}
		return parties;
	}
	
	
}
