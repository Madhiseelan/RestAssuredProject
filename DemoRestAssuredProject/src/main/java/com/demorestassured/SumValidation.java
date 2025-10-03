package com.demorestassured;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;

public class SumValidation {
	
	// Verify if Sum of all Course prices matches with Purchase Amount
	@Test
	public void sumOfCourses()
	{
		int sum = 0;
		JsonPath js = new JsonPath(Files.payload.CoursePrice());
		int count = js.getInt("courses.size()");
		int expectedTotalAmount = js.getInt("dashboard.purchaseAmount");
		
		for(int i=0; i<count; i++) {
			int price = js.getInt("courses["+i+"].price");
			int copies = js.getInt("courses["+i+"].copies");
			int amount = price * copies;
			System.out.println("Amount of " + js.get("courses["+i+"].title") + " : " + amount);
			sum = sum + amount;
		}
		Assert.assertEquals(sum, expectedTotalAmount);
		System.out.println("Amount matches with Purchase Amount : " + sum);
	}

}
