package test1;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import helper.RawToJson;
import payloads.Payloads;

import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class DynamicJsonPayload {
	
	
	@Test(dataProvider = "BooksData")
	public void addBookToLibrary(String bookIsle, String isbnNumber) {
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		String addBookResponse = given().header("Content-Type", "application/json")
								.body(Payloads.addBookToLibrary(bookIsle,isbnNumber)).when()
								.post("/Library/Addbook.php").then().assertThat().statusCode(200)
								.extract().response().asString();
		System.out.println("Add book response is :: \n " + addBookResponse);
		JsonPath jsonPath = RawToJson.rawToJson(addBookResponse);
		String bookId = jsonPath.get("ID");
		
		System.out.println("ID received from response is: " + bookId);
	}
	
	@Test
	public void addBookToLibraryFromFile() {
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		String addBookResponse = given().log().all().header("Content-Type", "application/json")
								.body(Payloads.generateResourceFromFile("D:\\PFMBFM\\Api's\\Generate access token\\generate_access_token\\Addbook.json")).when()
								.post("/Library/Addbook.php").then().log().all().assertThat().statusCode(200)
								.extract().response().asString();
		System.out.println("Add book response is :: \n " + addBookResponse);
		JsonPath jsonPath = RawToJson.rawToJson(addBookResponse);
		String bookId = jsonPath.get("ID");
		
		System.out.println("ID received from response is: " + bookId);
	}
	
	@DataProvider(name = "BooksData")
	public Object[][] getData() 
	{
		return new Object [][] {{"928397","IS2983"},{"93487","ID2938"},{"43432","TE9823"}};
	}
}
