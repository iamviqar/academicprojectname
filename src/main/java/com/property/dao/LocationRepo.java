package com.property.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.property.entity.Location;

@Repository
public interface LocationRepo extends CrudRepository<Location, Long> {

	public List<Location> findAll();

	public Location findOneById(Long id);
	
	@Query("SELECT l FROM Location l WHERE l.city.id = :cityId")
	public List<Location> findByCityId(@Param("cityId") Long cityId);
	
}
