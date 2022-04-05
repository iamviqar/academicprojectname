package com.property.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.property.entity.File;

@Repository
public interface FileRepo extends CrudRepository<File, Long> {

	public List<File> findAll();

	public File findOneById(Long id);

}
