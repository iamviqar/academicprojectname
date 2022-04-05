package com.property.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.property.entity.GstPaidLedger;
import com.property.entity.State;

@Repository
public interface GstPaidLedgerRepo extends CrudRepository<GstPaidLedger, Long> {

	public List<GstPaidLedger> findAll();

	public State findOneById(Long id);

}
