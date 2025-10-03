package com.demorestassured;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;


public class Basics {

	public static void main(String[] args) {
		
		/*
		 * Given - All input details
		 * When - Submit the API. Resource and HTTP method will always go under When method
		 * Then - Validate the Response under then method
		 */

		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String response = given().log().all()
			.queryParam("key", "qaclick123")
			.header("Content-Type", "application/json")
			.body(Files.payload.AddPlace())
		.when()
			.post("maps/api/place/add/json")
		.then()
			.assertThat()
				.statusCode(200)
				.body("scope", equalTo("APP"))
				.header("Server", "Apache/2.4.52 (Ubuntu)")
			.extract().response().asString();
		
		
		System.out.println("response as string " + response);
		JsonPath js = new JsonPath(response); // for parsing Json
		String placeId = js.getString("place_id");
		System.out.println("Place ID : " + placeId);
		
		//Add place -- Update place with new Address -- Get Place to validate if new address is present in response
		String newAddress = "70 winters madhi walk, USA";
		given().log().all()
			.queryParam("key", "qaclick123")
			.header("Content-Type", "application/json")
			.body("{\n"
					+ "\"place_id\":\""+ placeId + "\",\n"
					+ "\"address\":\""+ newAddress +"\",\n"
					+ "\"key\":\"qaclick123\"\n"
					+ "}")
		.when()
			.put("maps/api/place/update/json")
		.then().log().all()
		.assertThat()
			.statusCode(200)
			.body("msg", equalTo("Address successfully updated"));
		
		//Get method to verify the updated Place
		String getPlaceResponse = given().log().all()
								.queryParam("key", "qaclick123")
								.queryParam("place_id",placeId)
							.when()
								.get("maps/api/place/get/json")
							.then().log().all()
								.assertThat().statusCode(200)
								.extract().response().asString();
		
		System.out.println(" *** Print Get Response *** " + getPlaceResponse);
		//JsonPath jsget1 = new JsonPath(getPlaceResponse);
		JsonPath js1 = Files.ReusableMethods.rawToJson(getPlaceResponse);

		String actualAddress = js1.getString("address");
		System.out.println("Actual Address : " + actualAddress);
		
		//assert(actualAddress.equals(newAddress));
		assertEquals(actualAddress, newAddress);
		
		System.out.println("Address successfully updated and verified");
		
	}
	
}