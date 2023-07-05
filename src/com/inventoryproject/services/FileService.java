package com.inventoryproject.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.ArrayList;

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
	public ArrayList<ArrayList<String>> getFileContent(Path path) throws IOException{
		System.out.println("The inserted path is " + path);
		ArrayList<String> data = (ArrayList<String>) Files.readAllLines(path);
		ArrayList<ArrayList<String>> lines = new ArrayList<ArrayList<String>>();
		for (int i = 0; i < data.size(); i++) {
			ArrayList<String> line = new ArrayList<>();
			line.add(data.get(i));	
			lines.add(line);
		}
		
		return lines;

	}
}
