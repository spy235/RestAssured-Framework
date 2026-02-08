package services;


import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.ContactResponse;
import models.LoginRequest;
import models.LoginResponse;
import models.Person;
import models.User;

import static io.restassured.RestAssured.given;

import auth.TokenManager;

public class UserService {

	public static LoginResponse login(String email, String password) {
	    LoginRequest request = new LoginRequest(email, password);

	    LoginResponse response = RestAssured.given()
	            .contentType("application/json")   // optional but safe
	            .body(request)	            
	            .when()
	            .post("/users/login")
	            .then()
	            .statusCode(200)
	            .extract()
	            .as(LoginResponse.class);

	    // Save token
	    TokenManager.setToken(response.getToken());

	    return response;
	}


    public static Response getProfile() {
        return given()
                .header("Authorization", TokenManager.getToken())
                .when()
                .get("/users/me")
                .then()
                .statusCode(200)
                .extract().response();
    }
    
    public static ContactResponse addContact(Person contact) {
        return given()
                .header("Authorization", TokenManager.getToken())
                .contentType("application/json") 
                .body(contact)
                .when()
                .post("/contacts")
                .then()
                .statusCode(201)   // FIX HERE
                .extract()
                .as(ContactResponse.class);
    }
    
    public static Response deleteContact(String id) {
        return given()
                .header("Authorization", TokenManager.getToken())
                .contentType("application/json") 
                .when()
                .delete("/contacts/"+id)
                .then()
                .statusCode(200)
                .extract().response();
    }
    
}

