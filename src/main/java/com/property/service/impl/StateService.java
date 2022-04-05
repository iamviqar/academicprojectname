package com.property.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.property.dao.StateRepo;
import com.property.entity.State;
import com.property.service.IStateService;

@Service
public class StateService implements IStateService {

	@Autowired
	StateRepo stateRepo;
	
	@Override
	public State getById(Long id) throws Exception {
		State state = null;
		try {
			state = stateRepo.findOneById(id);
		}catch(Exception e) {
			throw e;
		}
		return state;
	}

	@Override
	public State save(State state) throws Exception {
		try {
			stateRepo.save(state);
		}catch(Exception e) {
			throw e;
		}
		return state;
	}


	@Override
	public List<State> getAll() throws Exception {
		List<State> properties = null;
		try {
			properties = stateRepo.findAll();
		}catch(Exception e) {
			throw e;
		}
		return properties;
	}

}
