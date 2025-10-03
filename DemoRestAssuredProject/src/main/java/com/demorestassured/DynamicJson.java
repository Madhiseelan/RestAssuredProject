package com.demorestassured;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Files.ReusableMethods;
import Files.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class DynamicJson {
	
	
	@Test(dataProvider="BooksData")
	public void addBook (String isbn, String aisle) 
	{
		RestAssured.baseURI = "http://216.10.245.166";
		
		String response = given().log().all()
			.header("Content-Type", "application/json")
			//Sending dynamic parameters to the payload method from test
			//.body(payload.AddBook("isbnsdsd", "867868"))
			.body(payload.AddBook(isbn, aisle))
		.when()
			.post("/Library/Addbook.php")
		.then().log().all()
		    .assertThat().statusCode(200)
		    .extract().response().asString();
		
		JsonPath js = ReusableMethods.rawToJson(response);
		String id = js.get("ID");
		System.out.println("Response - ID is ********* :"+ id);
		
	}
	
	//TestNG Data Provider for Parameterization
	@DataProvider(name="BooksData")
	public Object[][] getData() {
		//Create multi-dimentional array -- Collection of arrays
		Object[][] object = new Object[][] {
			{"isbgghhdsd", "8678687"},
			{"isbuuytdsd", "8678687"},
			{"isboofgdsd", "8678687"}
		};
		
		return object;
	}

}
