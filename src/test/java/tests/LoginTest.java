package tests;


import org.testng.annotations.Test;

import base.BaseTest;
import models.LoginResponse;
import services.UserService;
import utils.ConfigReader;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginTest extends BaseTest {
    @Test
    public void loginAndFetchProfile() {
    	    String email = ConfigReader.get("user.email");
    	    String password = ConfigReader.get("user.password");

    	    LoginResponse loginResponse = UserService.login(email, password);

    	    // Validate token
    	    assertThat(loginResponse.getToken()).isNotBlank();

    	    // Validate user info
    	    assertThat(loginResponse.getUser().getEmail()).isEqualTo(email);
    	    assertThat(loginResponse.getUser().getFirstName()).isEqualTo("testuser");
    }
}
