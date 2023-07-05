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
		ArrayList<Product> productObjects = new ArrayList<>();
		boolean isFirstLine = true;
		for (ArrayList<String> line : lines) {
			for (String properties : line) {
				
				// Skip Headers:
				if (isFirstLine) {
					isFirstLine = false;
					continue;
				}
				
				// Create List of strings and change to format ArrayList:
				String[] propertiesList = properties.split(",");
				ArrayList<String> propertiesArrayList = new ArrayList<>();
				for (String item: propertiesList)
					propertiesArrayList.add(item);
				
		        // Access elements by index using get
				int id = Integer.parseInt(propertiesArrayList.get(0));
				String name = propertiesArrayList.get(1);
				int quantity = Integer.parseInt(propertiesArrayList.get(2));
		        float price = Float.parseFloat(propertiesArrayList.get(3));
		        
		        // Create new Object and add to list:
		        Product product = new Product(id, name, quantity, price)
				productObjects.add(product);
		       
			}
		}
		System.out.println(productObjects);
		
		// Collect necessary information by iterating through objects:
		
		
		// Export relevant data into output file:
		
//		System.out.println(lines);
	}

}
