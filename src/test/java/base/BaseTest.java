package base;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import report.ExtentManager;
import utils.ConfigReader;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import auth.TokenManager;

public class BaseTest {
    protected static RequestSpecification requestSpec;
    protected static ResponseSpecification responseSpec;
    
    protected static ExtentReports extent;
    protected static ExtentTest test;

    @BeforeSuite
    public void setupSuite() {
        // Initialize ExtentReports
        extent = ExtentManager.getInstance();
        // Setup RestAssured base URI
        String baseUrl = System.getProperty("baseUrl", ConfigReader.get("base.url"));
        RestAssured.baseURI = baseUrl;
    }

    @AfterSuite
    public void tearDownSuite() {
        if (extent != null) {
            extent.flush(); // Write report to HTML
        }
    }

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
