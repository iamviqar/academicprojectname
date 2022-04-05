package com.property.util;

public class FileUtil {
	public static String getFileExtension(String fileName) throws Exception{
		String[] fileNamArr = fileName.split("\\.");
		return fileNamArr[fileNamArr.length-1];
	}
}
