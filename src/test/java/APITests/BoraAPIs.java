package APITests;

import org.json.simple.JSONObject;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BoraAPIs {
	
	public static final String APPLICATION_URL = "http://ec2-3-86-91-230.compute-1.amazonaws.com:5000";

	public static void main(String[] args) {
		
		String endPoint = "/api/users/login";
		RestAssured.baseURI = APPLICATION_URL;
		RequestSpecification request = RestAssured.given();
		request.header("Content-Type", "application/json");
		
		JSONObject requestBody = new JSONObject();
		// { }
		requestBody.put("email", "miller.muradil@gmail.com");
		requestBody.put("password", "murad001");
		//		{
		//			"email": "miller.muradil@gmail.com",
		//			"password": "murad001"
		//		}
		
		request.body(requestBody);
		Response response = request.post(endPoint);
		int statusCode = response.getStatusCode();
		System.out.println(statusCode);
		
		if (statusCode == 200) {
			System.out.println("Request successful");
		} else {
			System.out.println("Request failed");
		}
		
		String responseBody = response.getBody().asString();
		System.out.println(responseBody);
		
	}

}
