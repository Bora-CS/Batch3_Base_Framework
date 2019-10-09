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
		login("jon.doe@gmail.com", "Hello12345");		
		int originalNumberOfPosts = getTotalNumberOfPosts();
		System.out.println("There's currently " + originalNumberOfPosts + " posts");
		String message = "This is a very very new and exciting message";
		createPost(message);
		if (getTotalNumberOfPosts() == (originalNumberOfPosts + 1)) {
			System.out.println("There's now " + getTotalNumberOfPosts() + " posts, which is expected.");
			Post[] allPosts = getAllPostsByPOJO();
			boolean found = false;
			for (Post post : allPosts) {
				if (post.text.equals(message)) {
					found = true;
					break;
				}
			}
			System.out.println(found ? "Pass - post with correct message found!" : "Fail - post with expected message not found!");
		} else {
			System.out.println("Something went wrong, seems like there's no new post created.");
		}
	}
	
	public static void getCurrentUserProfile() {
		if (token == null) {
			System.out.println("No token is available to use!");
		} else {
			getCurrentUserProfile(token);
		}
	}
	
	public static void createPost (String content) {
		String endPoint = "/api/posts";
		RestAssured.baseURI = APPLICATION_URL;
		RequestSpecification request = RestAssured.given();
		request.header("Authorization", token);
		request.header("Content-Type", "application/json");
		
		JSONObject json = new JSONObject();
		json.put("text", content);
		
		request.body(json);
		Response response = request.post(endPoint);
		if (response.getStatusCode() == 200) {
			System.out.println("Post created successfully with message: " + content);
		} else {
			System.out.println("Post creation failed");
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
	
	public static Post[] getAllPostsByPOJO () {
		String endPoint = "/api/posts";
		RestAssured.baseURI = APPLICATION_URL;
		RequestSpecification request = RestAssured.given();
		Response response = request.get(endPoint);
		JsonPath jp = response.jsonPath();
		
		Post[] posts = jp.getObject("", Post[].class);
		return posts;
	}
	
	public static int getTotalNumberOfPosts () {
		String endPoint = "/api/posts";
		RestAssured.baseURI = APPLICATION_URL;
		RequestSpecification request = RestAssured.given();
		Response response = request.get(endPoint);
		JsonPath jp = response.jsonPath();
		
		Post[] posts = jp.getObject("", Post[].class);
		return posts.length;
	}
	
	public static void validateNewPostAdded (String postContent) {
		String endPoint = "/api/posts";
		RestAssured.baseURI = APPLICATION_URL;
		RequestSpecification request = RestAssured.given();
		Response response = request.get(endPoint);
		JsonPath jp = response.jsonPath();
		
		Post[] posts = jp.getObject("", Post[].class);

		
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
