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
		System.out.println(sizeOfArray);
		Assert.assertEquals(sizeOfArray, 3);
	}
	
}
