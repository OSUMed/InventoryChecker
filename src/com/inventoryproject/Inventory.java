package com.inventoryproject;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
		        Product product = new Product(id, name, quantity, price);
				productObjects.add(product);
		       
			}
		}
		
		// Collect necessary information by iterating through objects:
		ArrayList<Product> filteredObjects = new ArrayList<>();
		for (Product product: productObjects) {
			if (product.getPrice() > .99 && product.getPrice() < 100) {
				if (product.getQuantity() < 20) {
					filteredObjects.add(product);
				}
				
			}
			if (product.getPrice() >= 101 && product.getQuantity() <= 10) {
				filteredObjects.add(product);
			}
		}
		
		// Collect items into writable format and print to output.txt:
		Path fileDestination = Path.of("output.txt");
		List<String> linesToWrite = new ArrayList<>();
		for (Product product : filteredObjects) {
			linesToWrite.add(product.toString());
        }
		Files.write(fileDestination, linesToWrite, StandardCharsets.UTF_8);
		
		System.out.println("Please check output.txt file for items that need to be reordered");
		
	}

}
