package com.inventoryproject.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

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
	public List<String> printFileContentsToConsole(Path path) throws IOException{
		System.out.println("The inserted path is " + path);
		List<String> lines = Files.readAllLines(path);
		System.out.println(lines);
		return lines;

	}
}
