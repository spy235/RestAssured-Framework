package base;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;


import auth.TokenManager;

public class BaseTest {
    protected static RequestSpecification requestSpec;
    protected static ResponseSpecification responseSpec;

    @BeforeClass
    public void setup() {
        String baseUri = utils.ConfigReader.get("base.uri");
        requestSpec = SpecBuilders.createRequestSpec(baseUri);
        responseSpec = SpecBuilders.createResponseSpec();

        // set default specs to RestAssured (optional but convenient)
        RestAssured.requestSpecification = requestSpec;
        RestAssured.responseSpecification = responseSpec;
    }

    @AfterClass
    public void tearDown() {
        TokenManager.clear();
    }
}
