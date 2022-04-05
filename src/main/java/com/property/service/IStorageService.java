package com.property.service;

import org.springframework.web.multipart.MultipartFile;

import com.property.entity.File;
import com.property.model.FileVO;

public interface IStorageService {

	File store(MultipartFile file) throws Exception;
	void deleteAll();

	void init();

	FileVO loadFile(Long fileId) throws Exception;

}
