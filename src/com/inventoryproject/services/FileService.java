package com.inventoryproject.services;

public class FileService {
	private static FileService fileService = null;
	private FileService() {
		
	}
	public static FileService getInstance() {
		if (fileService == null) {
			fileService = new FileService();
		}
		return fileService;		
	}
	
	public String testMethod() {
		return "Hello from File Service";
	}
}
