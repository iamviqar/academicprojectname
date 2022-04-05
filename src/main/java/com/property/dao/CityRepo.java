package com.property.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.property.entity.City;

@Repository
public interface CityRepo extends CrudRepository<City, Long> {

	public List<City> findAll();

	public City findOneById(Long id);
	
	@Query("SELECT c FROM City c WHERE c.state.id = :stateId")
	public List<City> findByStateId(@Param("stateId") Long stateId);
	
}
