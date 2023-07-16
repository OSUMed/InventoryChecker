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
	
	/**
	 * Singleton so only one instance is created.
	 * @return The instance of FileService.
	 */
	public static FileService getInstance() {
		if (fileService == null) {
			fileService = new FileService();
		}
		return fileService;		
	}

	/**
	Reads the contents of a file and inserts them into a data structure.
	@param path The path of the file to be read.
	@return An ArrayList of ArrayLists, where each inner ArrayList represents a line from the file.
	@throws IOException If an error occurs while reading the file.
	*/
	public ArrayList<ArrayList<String>> getFileContent(Path path) throws IOException{
		
		// Read the file and collect its contents:
		ArrayList<String> data = (ArrayList<String>) Files.readAllLines(path);
		
		// Create a data structure to store each line from the file:
		ArrayList<ArrayList<String>> lines = new ArrayList<ArrayList<String>>();
		
		// Iterate over the data and add each line to the lines data structure:
		for (int i = 0; i < data.size(); i++) {
			ArrayList<String> line = new ArrayList<>();
			line.add(data.get(i));	
			lines.add(line);
		}
		return lines;

	}
}
