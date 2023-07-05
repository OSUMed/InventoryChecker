package com.inventoryproject;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import com.inventoryproject.services.*;
public class Inventory {

	public static void main(String[] args) throws IOException {		
		// Create File Service:
		FileService fileService = FileService.getInstance();
		
		// Collect inventory data:
		List<String> lines = fileService.getFileContent(Path.of("data.txt"));
		
		// Iterate through data and make objects:
		// eg: Product firstProduct = new Product(0, "My Book", 10, 1);
		
		// Collect necessary information:
		
		
		// Export relevant data into output file:
		
		System.out.println(lines);
	}

}
