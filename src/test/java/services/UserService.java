package services;


import io.restassured.response.Response;
import models.LoginRequest;
import models.LoginResponse;

import static io.restassured.RestAssured.given;

import auth.TokenManager;

public class UserService {

	public static LoginResponse login(String email, String password) {
	    LoginRequest request = new LoginRequest(email, password);

	    LoginResponse response = given()
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
}
