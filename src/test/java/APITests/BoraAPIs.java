package APITests;

import org.json.simple.JSONObject;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BoraAPIs {
	
	public static final String APPLICATION_URL = "http://ec2-3-86-91-230.compute-1.amazonaws.com:5000";
	public static String token;

	public static void main(String[] args) {
		
		login("miller.muradil@gmail.com", "murad001");
		getCurrentUserProfile();
		
	}
	
	public static void getCurrentUserProfile() {
		if (token == null) {
			System.out.println("No token is available to use!");
		} else {
			getCurrentUserProfile(token);
		}
	}

	public static void getCurrentUserProfile(String token) {
		String endPoint = "/api/profile";
		RestAssured.baseURI = APPLICATION_URL;
		RequestSpecification request = RestAssured.given();
		request.header("Authorization", token);
		Response response = request.get(endPoint);
		JsonPath jp = response.jsonPath();
		String handle = jp.get("handle");
		String name = jp.get("user.name");
		System.out.println("Profile retireved for User:"+ name + " with Handle:"+ handle);
	}

	public static void login(String email, String password) {
		try {
			String endPoint = "/api/users/login";
			RestAssured.baseURI = APPLICATION_URL;
			RequestSpecification request = RestAssured.given();
			request.header("Content-Type", "application/json");
			JSONObject requestBody = new JSONObject();
			requestBody.put("email", email);
			requestBody.put("password", password);
			request.body(requestBody);
			Response response = request.post(endPoint);
			JsonPath jp = response.jsonPath();
			token = jp.get("token");
			if (token == null) {
				throw new Exception(response.getBody().asString());
			}
		} catch (Exception e) {
			System.out.println("Token Generation Failed ==>");
			e.printStackTrace();
		}
	}

}
