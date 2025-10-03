package Files;

import io.restassured.path.json.JsonPath;

public class ReusableMethods {
	
	
	public static JsonPath rawToJson(String response) {
		JsonPath jsget1 = new JsonPath(response);
		return jsget1;
	}

}
