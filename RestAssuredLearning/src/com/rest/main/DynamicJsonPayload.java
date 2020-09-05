package com.rest.main;

import org.testng.annotations.Test;

import com.rest.helper.RawToJson;
import com.rest.payloads.Payloads;

import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class DynamicJsonPayload {
	
	
	@Test
	public void addBookToLibrary() {
		RestAssured.baseURI = "http://216.10.245.166";
		
		String addBookResponse = given().log().all().header("Content-Type", "application/json")
								.body(Payloads.addBookToLibrary("22984757")).when()
								.post("/Library/AddBook.php").then().log().all().assertThat().statusCode(200)
								.extract().response().asString();
		System.out.println("Add book response is :: \n " + addBookResponse);
		JsonPath jsonPath = RawToJson.rawToJson(addBookResponse);
		String bookId = jsonPath.get("ID");
		
		System.out.println("ID received from response is: " + bookId);
	}
	
}
