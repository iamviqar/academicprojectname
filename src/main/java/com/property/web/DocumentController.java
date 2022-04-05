package com.property.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.property.entity.File;
import com.property.model.FileVO;
import com.property.model.ServiceResponse;
import com.property.service.impl.StorageService;
import com.property.util.ServiceResponseUtils;
import com.property.util.StatusCodes;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@Controller
@RequestMapping(value = "/document")
public class DocumentController {

	@Autowired
	StorageService storageService;

	
	@PostMapping("/upload")
	@ResponseBody
	public ServiceResponse handleFileUpload(@RequestParam("file") MultipartFile file) {
		ServiceResponse serviceResponse = null;
		try {
			File fileResponse = storageService.store(file);
			serviceResponse = ServiceResponseUtils.dataResponse(StatusCodes.DATA_INSERT_SUCCESS.getCode(),StatusCodes.DATA_INSERT_SUCCESS.getDescription(),fileResponse);
			
		} catch (Exception e) {
			serviceResponse = ServiceResponseUtils.dataResponse(StatusCodes.DATA_INSERT_FAIL.getCode(),StatusCodes.DATA_INSERT_FAIL.getDescription(), null);
		}
		return serviceResponse;
	}
	
	@GetMapping("/download/{fileId}")
	@ResponseBody
	public ResponseEntity<Resource> getFile(@PathVariable Long fileId) {
	try {
		FileVO file = storageService.loadFile(fileId);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFile().getName() + "\"")
				.body(file.getResource());
	}catch(Exception e) {
		e.printStackTrace();
		return null;
	}
	
	}
	
	
}
