
package helper;

import io.restassured.path.json.JsonPath;

public class RawToJson {
	
	public static JsonPath rawToJson (String rawResponse) {
		JsonPath jsonPath = new JsonPath(rawResponse);
		return jsonPath;
	}
}
