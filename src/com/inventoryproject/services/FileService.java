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
	
	// Singleton so only one instance made:
	public static FileService getInstance() {
		if (fileService == null) {
			fileService = new FileService();
		}
		return fileService;		
	}
	
	// Reads files and inserts its contents into a data structure:
	public ArrayList<ArrayList<String>> getFileContent(Path path) throws IOException{
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
