package test1;

import org.testng.Assert;
import org.testng.annotations.Test;

import helper.RawToJson;
import io.restassured.path.json.JsonPath;
import payloads.Payloads;


public class ComplexJsonParse {
	
	JsonPath complexJsonPath = RawToJson.rawToJson(Payloads.coursePrice());
		
	@Test
	public void verifyCourseSize() {
		int sizeOfArray = complexJsonPath.getInt("courses.size()");
		System.out.println("Size of array "  + sizeOfArray);
		Assert.assertEquals(sizeOfArray, 3);
	}
	
	@Test
	public void verifyPurchaseAmount() {
		int purchaseAmount = complexJsonPath.getInt("dashboard.purchaseAmount");
		System.out.println("Purchase Amount " + purchaseAmount);
		Assert.assertEquals(purchaseAmount, 910);
	}
	
	@Test
	public void verifyFirstCourseTitle() {
		String firstCourseTitle = complexJsonPath.get("courses[0].title");
		System.out.println("First Course title " + firstCourseTitle);
		Assert.assertEquals(firstCourseTitle, "Selenium Python");
	}
	
	@Test
	public void verifyTitlesAndPrices() {
		for (int i = 0 ; i < complexJsonPath.getInt("courses.size()") ; i++) {
			String courseTitles = complexJsonPath.get("courses["+ i +"].title");
			System.out.println("title : " + courseTitles);
			int coursePrices = complexJsonPath.getInt("courses["+ i +"].price");
			System.out.println("course " + coursePrices);
		}

		Assert.assertTrue(true);
	}
	
	@Test
	public void verifyRPACoursePrice() {
		for (int i = 0 ; i < complexJsonPath.getInt("courses.size()") ; i++) {
			String courseTitles = complexJsonPath.get("courses["+ i +"].title");
			if (courseTitles.equalsIgnoreCase("RPA") ) {
				int courseCopies = complexJsonPath.getInt("courses["+ i +"].copies");
				System.out.println("course " + courseCopies);
			}
		}

		Assert.assertTrue(true);
	}
	
	@Test
	public void verifyPurchaseAmountwithBooksSelling() {
		int sellPriceSum = 0;
		for (int i = 0 ; i < complexJsonPath.getInt("courses.size()") ; i++) {
			int copies = (complexJsonPath.get("courses["+ i +"].copies"));
			int price = (complexJsonPath.get("courses["+ i +"].price"));
			int sellPrice = copies * price;
			sellPriceSum += sellPrice; 
		}
		System.out.println("Sell Price " + sellPriceSum);
		int purchaseAmount = complexJsonPath.getInt("dashboard.purchaseAmount");
		System.out.println("Purchase Amount " + purchaseAmount);
		Assert.assertEquals(purchaseAmount,sellPriceSum);
	}
	
}
