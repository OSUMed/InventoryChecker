package com.inventoryproject;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import com.inventoryproject.services.*;
public class Inventory {

	public static void main(String[] args) throws IOException {		
		// Create File Service:
		FileService fileService = FileService.getInstance();
		
		// Collect inventory data:
		List<ArrayList<String>> lines = fileService.getFileContent(Path.of("data.txt"));
		
		// Change input data String -> Objects:
		// eg: Product firstProduct = new Product(0, "My Book", 10, 1);
		boolean isFirstLine = true;
		for (ArrayList<String> line : lines) {
			for (String property : line) {
				if (isFirstLine) {
					isFirstLine = false;
					continue;
				}
				System.out.println(property);
			}
		}
		
		
		
		// Collect necessary information:
		
		
		// Export relevant data into output file:
		
//		System.out.println(lines);
	}

}
