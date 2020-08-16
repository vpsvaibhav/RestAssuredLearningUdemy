package com.rest.main;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import com.rest.payloads.Payloads;

public class RestAssuredFirstClass {
	
	public static void main(String[] args) {
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		String addResponse = given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body(Payloads.addPlace()).when().post("/maps/api/place/add/json")
		.then().assertThat().statusCode(200).body("scope", equalTo("APP"))
		.header("server", "Apache/2.4.18 (Ubuntu)").extract().response().asString();
		
		System.out.println("Add Response begins here ...................\n" + addResponse);
		
		JsonPath jsonPath = new JsonPath(addResponse);
		String placeId = jsonPath.getString("place_id");
		
		System.out.println("Place id value is :" + placeId);
		
		String updateResponse = given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body(Payloads.updatePlace(placeId)).when().put("/maps/api/place/update/json")
		.then().assertThat().statusCode(200).body("msg",equalTo("Address successfully updated")).extract().response().asString();
		
		System.out.println("Update Response begins here ...................\n" + updateResponse);
		
		
	}
	
}
