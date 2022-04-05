package com.property.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.property.entity.TenantFile;

@Repository
public interface TenantFileRepo extends CrudRepository<TenantFile, Long> {

	public List<TenantFile> findAll();

	public TenantFile findOneById(Long id);

}
