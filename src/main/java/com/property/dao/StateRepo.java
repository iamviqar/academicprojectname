package com.property.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.property.entity.State;

@Repository
public interface StateRepo extends CrudRepository<State, Long> {

	public List<State> findAll();

	public State findOneById(Long id);

}
