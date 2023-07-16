package com.inventoryproject;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import com.inventoryproject.services.*;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Inventory {

	public static void main(String[] args) throws IOException {		
		// Create File Service:
		FileService fileService = FileService.getInstance();
		
		// Collect inventory data:
		List<ArrayList<String>> lines = fileService.getFileContent(Path.of("data.txt"));
		
		// Collect input data String -> Objects
		List<Product> productObjects = parseProductObjects(lines);

		// Collect necessary information by filtering:
		List<Product> filteredObjects = filterProductObjects(productObjects);		

		// Uncomment to do via Stream API instead:
//		List<Product> productObjects = parseProductObjectsStream(lines);
//		List<Product> filteredObjects = filterProductObjectsUsingStream(productObjects);		
		
		// Collect items into writable format and print to output.txt:
		Path fileDestination = Path.of("output.txt");
		List<String> linesToWrite = new ArrayList<>();
		for (Product product : filteredObjects) {
			linesToWrite.add(product.toString());
        }
		Files.write(fileDestination, linesToWrite, StandardCharsets.UTF_8);
		
		System.out.println("Please check output.txt file for items that need to be reordered");
		
	}
	
    /**
     * Parses the lines of inventory data and creates a list of Product objects.
     *
     * @param lines The lines of inventory data.
     * @return The list of Product objects.
     */
	private static List<Product> parseProductObjects(List<ArrayList<String>> lines){
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
				
		        // Access elements by index:
				int id = Integer.parseInt(propertiesArrayList.get(0));
				String name = propertiesArrayList.get(1);
				int quantity = Integer.parseInt(propertiesArrayList.get(2));
		        float price = Float.parseFloat(propertiesArrayList.get(3));
		        
		        // Create new Object and add to list:
		        Product product = new Product(id, name, quantity, price);
				productObjects.add(product);
		       
			}
		}
		return productObjects;
				
	}
	
    /**
     * Filters the list of products based on specified criteria:
     *
     * @param products The list of products to filter.
     * @return The filtered list of products.
     */
	private static List<Product> filterProductObjects(List<Product> productObjects){
		ArrayList<Product> filteredObjects = new ArrayList<>();
		for (Product product: productObjects) {
			if (reorderPriceUnder100(product.getPrice(), product.getQuantity()) || 
					reorderPriceAbove100(product.getPrice(), product.getQuantity())){
				filteredObjects.add(product);
			}
		}
		return filteredObjects;
	}
	
	/**
	 * Checks if the price of a product needs reordering:
	 * If price in range 99 cents to 100 dollars, reorder item if quantity is 
	 * under 20
	 * @param price The price of the product.
	 * @param quantity The number of the quantity.
	 * @return true if the product needs reordering, false otherwise.
	 */
	private static boolean reorderPriceUnder100(float price, int quantity) {
		return price > .99 && price < 101 && quantity <= 20;
	}
	
	/**
	 * Checks if the price of a product needs reordering:
	 * If price is above 100 dollars, reorder item if quantity is under 10
	 * @param price The price of the product.
	 * @param quantity The number of the quantity.
	 * @return true if the product needs reordering, false otherwise.
	 */
	private static boolean reorderPriceAbove100(float price, int quantity) {
		return price >= 101 && quantity <= 10;
	}
	
	/**
	 * Repeating Parsing and Filtering objectives by instead using the Stream API
	 */
	
    /**
     * Filters the list of products based on specified criteria using Stream API:
     *
     * @param products The list of products to filter.
     * @return The filtered list of products.
     */
	private static List<Product> filterProductObjectsUsingStream(List<Product> productObjects){
		Stream<Product> filteredStream = productObjects.stream()
				.filter(product->reorderPriceUnder100(product.getPrice(), product.getQuantity())
						|| reorderPriceAbove100(product.getPrice(), product.getQuantity()));
		return filteredStream.collect(Collectors.toList());
	}
	
}
