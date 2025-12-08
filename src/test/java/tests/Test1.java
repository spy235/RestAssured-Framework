package tests;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class Test1 {
	
	String userToken = "";
	String email= "testusermap1@gmail.com";
	@Test
	public void login() {
	    RestAssured.baseURI = "https://thinking-tester-contact-list.herokuapp.com";
		 String res = RestAssured.given()
	        .header("Content-Type", "application/json")
	        .body("{\"email\": \""+email+"\", \"password\": \"Yashas@235@\"}").log().all()
	        .when()
	        .post("/users/login")
	        .then()
	        .log().all()
	        .statusCode(200).extract().asString();
		 JsonPath jsonPath = new JsonPath(res);
		 System.out.println(jsonPath.getString("token"));
		 userToken = jsonPath.getString("token");
	}
	
	@Test(dependsOnMethods = "login")
	public void userDeatils() {
		  RestAssured.baseURI = "https://thinking-tester-contact-list.herokuapp.com";
			 String res = RestAssured.given()
		        .header("Content-Type", "application/json").header("Authorization",userToken)
		        .log().all()
		        .when()
		        .get("/users/me")
		        .then()
		        .log().all()
		        .statusCode(200).extract().asString();
	}
	
}
