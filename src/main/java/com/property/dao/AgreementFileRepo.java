package com.property.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.property.entity.AgreementFile;

@Repository
public interface AgreementFileRepo extends CrudRepository<AgreementFile, Long> {

	public List<AgreementFile> findAll();

	public AgreementFile findOneById(Long id);

}
