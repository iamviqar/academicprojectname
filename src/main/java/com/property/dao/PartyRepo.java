package com.property.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.property.entity.Party;

@Repository
public interface PartyRepo extends CrudRepository<Party, Long> {
	
	public List<Party> findAll();
	
}
