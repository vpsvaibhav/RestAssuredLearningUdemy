package test1;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import payloads.Payloads;

public class RestAssuredFirstClass {

	public static void main(String[] args) {
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		// Add API  
		String addResponse = given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body(Payloads.addPlace()).when().post("/maps/api/place/add/json")
		.then().assertThat().statusCode(200).                                            
		body("scope", equalTo("APP"))
		.header("server", "Apache/2.4.18 (Ubuntu)").extract().response().asString();
		
		System.out.println("Add Response begins here ...................\n" + addResponse);
		
		JsonPath jsonPath = new JsonPath(addResponse);
		String placeId = jsonPath.getString("place_id");
		
		System.out.println("Place id value is :" + placeId);
		
		// Update API
		String updateResponse = given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body(Payloads.updatePlace(placeId)).when().put("/maps/api/place/update/json")
		.then().assertThat().statusCode(200).
		body("msg",equalTo("Address successfully updated"))
		.extract().response().asString();
		
		System.out.println("Update Response begins here ...................\n" + updateResponse);
		
		// GET API
		String getResponse = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeId)
				.header("Content-Type","application/json").when()
				.get("/maps/api/place/get/json").then().statusCode(200).extract().response().asString();
		
		System.out.println("Get Response begins here ......................\n" + getResponse);
		JsonPath jsonPathGet = new JsonPath(getResponse);
		String address = jsonPathGet.getString("address");
		System.out.println("Get response contains Address value as :: " + address);
		
		// DELETE API
		String deleteResponse = given().queryParam("key", "qaclick123").header("Content-Type","application/json").body(Payloads.deletePlace(placeId))
		.when().delete("/maps/api/place/delete/json").then().statusCode(200).
		body("status", equalTo("OK")).
		extract().response().asString();
		
		System.out.println("Delete Response begins here .................\n "+ deleteResponse);
		
	}

}
