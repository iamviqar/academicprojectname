package com.property.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.property.entity.GstMainAccount;
import com.property.entity.State;

@Repository
public interface GstMainAccountRepo extends CrudRepository<GstMainAccount, Long> {

	public List<GstMainAccount> findAll();

	public GstMainAccount findOneById(Long id);

}
