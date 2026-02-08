package tests;

import base.BaseTest;
import io.restassured.response.Response;
import models.ContactResponse;
import models.LoginResponse;
import models.Person;
import services.UserService;

import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class LoginTest extends BaseTest {

    private LoginResponse loginResponse;
    private ContactResponse contactRes;

    @Test
    public void loginAndFetchProfile() throws NoSuchMethodException, SecurityException {
        // Create a test in the report
        test = extent.createTest(getClass().getMethod("myTestMethod").getName());

        String email = "testuser@example.com"; // or ConfigReader.get("user.email")
        String password = "password";          // or ConfigReader.get("user.password")

        loginResponse = UserService.login(email, password);
        test.info("Login API called for: " + email);

        // Validations
        assertThat(loginResponse.getToken()).isNotBlank();
        test.pass("Token generated successfully");

        assertThat(loginResponse.getUser().getEmail()).isEqualTo(email);
        test.pass("User email validated");

        assertThat(loginResponse.getUser().getFirstName()).isEqualTo("testuser");
        test.pass("User first name validated");
    }

    @Test(dependsOnMethods = "loginAndFetchProfile")
    public void addContactsAndValidate() {
        test = extent.createTest("Add Contact and Validate");

        Person contact = new Person(
                "Yashas", "J", "1901-01-01", "testuser1@fake.com",
                "8005555555", "1 Main St.", "Apartment A", "Anytown",
                "KS", "12345", "USA"
        );

        contactRes = UserService.addContact(contact);
        test.info("Add Contact API called");
        assertThat(contactRes.getFirstName()).isEqualTo("Yashas");
        assertThat(contactRes.getEmail()).isEqualTo("testuser1@fake.com");
        test.pass("Contact added and validated successfully");
    }

    @Test(dependsOnMethods = "addContactsAndValidate")
    public void deleteContactAndValidate() {
        test = extent.createTest("Delete Contact and Validate");

        Response res = UserService.deleteContact(contactRes.getId());
        test.info("Delete Contact API called for ID: " + contactRes.getId());

        assertThat(res.asString()).contains("Contact deleted");
        test.pass("Contact deleted successfully");
    }
}
