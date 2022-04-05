package com.property.service;

import java.util.List;

import com.property.entity.State;

public interface IStateService {

	public List<State> getAll() throws Exception;
	public State getById(Long id) throws Exception;
	State save(State state) throws Exception;
	
}
