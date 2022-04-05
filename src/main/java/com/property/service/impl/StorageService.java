package com.property.service.impl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import com.property.dao.FileRepo;
import com.property.entity.File;
import com.property.model.FileVO;
import com.property.service.IStorageService;
import com.property.util.FileUtil;
import com.property.util.StringUtil;

@Service
public class StorageService implements IStorageService {

	@Autowired
	FileRepo fileRepo;
	
	private final Path rootLocation = Paths.get("upload-dir");

	@Override
	public File store(MultipartFile file) throws Exception{
		File fileResponse = null;
		try {
			String fileName = StringUtil.getUniqueString() +"."+ FileUtil.getFileExtension(file.getOriginalFilename());
			Files.copy(file.getInputStream(), this.rootLocation.resolve(fileName));
			fileResponse = new File();
			fileResponse.setName(file.getOriginalFilename());
			fileResponse.setUniqueName(fileName);
			fileRepo.save(fileResponse);
		} catch (Exception e) {
			throw new Exception("FAIL!");
		}
		return fileResponse;
	}

	@Override
	public FileVO loadFile(Long fileId) throws Exception{
		FileVO fileVO = null;
		try {
			File fileResponse = fileRepo.findOneById(fileId);
			Path file = rootLocation.resolve(fileResponse.getUniqueName());
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				fileVO = new FileVO();
				fileVO.setResource(resource);
				fileVO.setFile(fileResponse);
			} else {
				throw new Exception("FAIL!");
			}
		} catch (MalformedURLException e) {
			throw new Exception("FAIL!");
		}
		return fileVO;
	}

	@Override
	public void deleteAll() {
		FileSystemUtils.deleteRecursively(rootLocation.toFile());
	}

	@Override
	public void init() {
		try {
			Files.createDirectory(rootLocation);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
