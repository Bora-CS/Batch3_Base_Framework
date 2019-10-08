package APITests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import org.json.simple.JSONObject;

import DataObjects.Experience;
import DataObjects.Post;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BoraAPIs {
	
	public static final String APPLICATION_URL = "http://ec2-3-86-91-230.compute-1.amazonaws.com:5000";
	public static String token;

	public static void main(String[] args) {
//		login("jon.doe@gmail.com", "Hello12345");
//		getCurrentUserProfile();
		
		getAllPostsByPOJO();
		
	}
	
	public static void getCurrentUserProfile() {
		if (token == null) {
			System.out.println("No token is available to use!");
		} else {
			getCurrentUserProfile(token);
		}
	}
	
	public static void getAllPostsByListOfMap () {
		String endPoint = "/api/posts";
		RestAssured.baseURI = APPLICATION_URL;
		RequestSpecification request = RestAssured.given();
		Response response = request.get(endPoint);
		JsonPath jp = response.jsonPath();
		ArrayList<HashMap<String, Object>> posts = jp.get("");
		System.out.println(posts.size());
		
		int counter = 1;
		for (HashMap<String, Object> post : posts) {
			System.out.println(counter + " " + post.get("text"));
			counter++;
		}
	}
	
	public static void getAllPostsByPOJO () {
		String endPoint = "/api/posts";
		RestAssured.baseURI = APPLICATION_URL;
		RequestSpecification request = RestAssured.given();
		Response response = request.get(endPoint);
		JsonPath jp = response.jsonPath();
		
		Post[] posts = jp.getObject("", Post[].class);

		int counter = 1;
		for (Post post : posts) {
			System.out.println("Post #"+ counter + ": ");
			System.out.println("Posted by user: " + post.user);
			System.out.println("Content: " + post.text + "\n");
			counter++;
		}
	}

	public static void getCurrentUserProfile(String token) {
		String endPoint = "/api/profile";
		RestAssured.baseURI = APPLICATION_URL;
		RequestSpecification request = RestAssured.given();
		request.header("Authorization", token);
		Response response = request.get(endPoint);
		JsonPath jp = response.jsonPath();
//		ArrayList<HashMap<String, Object>> experiences = jp.get("experience");
		
		Experience[] experiences = jp.getObject("experience", Experience[].class);
		for (Experience exp : experiences) {
			System.out.println("John Doe worked at " + exp.company + " as " + exp.title);
		}
		
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
