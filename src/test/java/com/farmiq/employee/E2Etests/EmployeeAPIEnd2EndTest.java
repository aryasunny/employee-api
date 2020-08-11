package com.farmiq.employee.E2Etests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;



public class EmployeeAPIEnd2EndTest {

    private Response response;
    private ValidatableResponse json;
    private RequestSpecification request;
    private String ENDPOINT_EMPLOYEES = "http://localhost:8080/employees";

    @Test
    public void test_getEmployees_Should_Return_Status_200_OK() {

        given().relaxedHTTPSValidation().
                when().
                get(ENDPOINT_EMPLOYEES).
                then().
                assertThat().
                statusCode(200).
                and().
                contentType(ContentType.JSON);
    }
}
