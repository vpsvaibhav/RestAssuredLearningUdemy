package com.rest.payloads;

import java.nio.file.Files;
import java.nio.file.Paths;

public class Payloads {
	
	public static String addPlace() {
		return ("{\r\n" + 
				"  \"location\": {\r\n" + 
				"    \"lat\": -38.383494,\r\n" + 
				"    \"lng\": 33.427362\r\n" + 
				"  },\r\n" + 
				"  \"accuracy\": 50,\r\n" + 
				"  \"name\": \"Frontline house\",\r\n" + 
				"  \"phone_number\": \"(+91) 983 893 3937\",\r\n" + 
				"  \"address\": \"29, side layout, cohen 09\",\r\n" + 
				"  \"types\": [\r\n" + 
				"    \"shoe park\",\r\n" + 
				"    \"shop\"\r\n" + 
				"  ],\r\n" + 
				"  \"website\": \"http://google.com\",\r\n" + 
				"  \"language\": \"French-IN\"\r\n" + 
				"}\r\n" + 
				"");
	}
	
	public static String updatePlace(String placeId) {
		return ("{\r\n" + 
				"\"place_id\":\""+ placeId +"\",\r\n" + 
				"\"address\":\"70 winter walk, USA\",\r\n" + 
				"\"key\":\"qaclick123\"\r\n" + 
				"}\r\n" + 
				"");
	}
	
	public static String deletePlace(String placeId) {
		return ("{\r\n" + 
				"    \"place_id\":\"" + placeId + "\"}\r\n" + 
				"");
	}
	
	public static String addBookToLibrary(String bookAisle, String isbnNumber) {
		return ("{\r\n" + 
				"\"name\":\"Learn Appium Automation with Java\",\r\n" + 
				"\"isbn\":\"" + isbnNumber + "\",\r\n" + 
				"\"aisle\":\""+ bookAisle +"\",\r\n" + 
				"\"author\":\"John foe\"\r\n" + 
				"}\r\n" + 
				"");
	}
	
	public static String generateResourceFromFile (String filePath) {
		try {
			return new String (Files.readAllBytes(Paths.get(filePath)));
		} catch (Exception e) {
			return null;
		} 
	}
}
