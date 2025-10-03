package com.demorestassured;

import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {
	
	public static void main(String[] args) {
		// Successfully mocked the dummy response (coming from Files.payload.CoursePrice() method). 
		// API is not ready, so mocked the response
		JsonPath js = new JsonPath(Files.payload.CoursePrice());
		
		// Print No of courses returned by API
		int count = js.getInt("courses.size()");
		System.out.println("No of courses returned by API : " + count);
		
		// Print Purchase Amount
		int totalAmount = js.getInt("dashboard.purchaseAmount");
		System.out.println("Purchase Amount : " + totalAmount);
		
		// Print Title of the first course
		String firtTitle = js.get("courses[0].title");
		System.out.println("Title of the first course : " + firtTitle);
		
		// Print All course titles and their respective Prices
		for(int i=0; i<count; i++) {
			String courseTitle = js.get("courses["+i+"].title");
			int coursePrice = js.getInt("courses["+i+"].price");
			System.out.println(courseTitle + " : " + coursePrice);
			
		}
		
		// Print no of copies sold by RPA Course
		System.out.println("No of copies sold by RPA Course : ");
		
		for(int i=0; i<count; i++) {
			String courseTitle = js.get("courses["+i+"].title");
			if(courseTitle.equalsIgnoreCase("RPA")) {
				int copies = js.getInt("courses["+i+"].copies"); 
				System.out.println(copies);
				break;
			}
		}

		
	}

}
