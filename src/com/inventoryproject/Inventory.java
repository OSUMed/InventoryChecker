package com.inventoryproject;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import com.inventoryproject.services.*;
public class Inventory {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("First Comment");
		System.out.println("Second Comment");
		Product firstProduct = new Product(0, "My Book", 10, 1);
		System.out.println(firstProduct);
		FileService fileService = FileService.getInstance();
		System.out.println(fileService.testMethod());
		List<String> lines = fileService.printFileContentsToConsole(Path.of("inventoryData.txt"));
		System.out.println(lines);
	}

}
