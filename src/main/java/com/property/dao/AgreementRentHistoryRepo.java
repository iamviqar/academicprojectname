package com.property.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.property.entity.AgreementRentHistory;

@Repository
public interface AgreementRentHistoryRepo extends CrudRepository<AgreementRentHistory, Long> {

	public List<AgreementRentHistory> findAll();

	public AgreementRentHistory findOneById(Long id);

}
