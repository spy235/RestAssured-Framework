package base;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import io.qameta.allure.restassured.AllureRestAssured;

import static org.hamcrest.Matchers.*;

public class SpecBuilders {
    public static RequestSpecification createRequestSpec(String baseUri) {
        return new RequestSpecBuilder()
                .setBaseUri(baseUri)
                .setContentType("application/json")
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .addFilter(new AllureRestAssured()) // attachments to Allure
                .build();
    }

    public static ResponseSpecification createResponseSpec() {
        return new ResponseSpecBuilder()
                // add generic checks here, or return more flexible spec per test
                .expectResponseTime(lessThan(5_000L))
                .build();
    }
}
