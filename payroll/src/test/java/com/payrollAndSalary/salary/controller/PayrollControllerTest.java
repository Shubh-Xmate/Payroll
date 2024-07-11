package com.payrollAndSalary.salary.controller;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PayrollControllerTest {

    @BeforeAll
    public static void setup() {
        // Specify the base URL to the RESTful web service
        RestAssured.baseURI = "http://localhost:8080/api/payroll";
    }

    @BeforeEach
    public void setupTestData() {
        // Create first payroll record
        Long employeeId1 = 110L;
        given()
                .contentType(ContentType.JSON)
                .when()
                .post("/create?employeeId=" + employeeId1)
                .then()
                .statusCode(201);

        // Create second payroll record
        Long employeeId2 = 120L;
        given()
                .contentType(ContentType.JSON)
                .when()
                .post("/create?employeeId=" + employeeId2)
                .then()
                .statusCode(201);
    }

    @Test
    public void testHelloWorld() {
        given().
                when().
                get("/hello").
                then().
                statusCode(200).
                body(equalTo("Hello World"));
    }

    @Test
    public void testCreatePayroll() {
        Long employeeId = 130L;

        String requestBody = """
                {
                  "employeeId": 130
                }""";

        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/create")
                .then()
                .statusCode(201)
                .extract().response();

        String status = response.jsonPath().getString("statusCode");
        String message = response.jsonPath().getString("message");

        assertEquals("201", status);
        assertEquals("Created successfully", message);
    }

    @Test
    public void testFetchPayroll() {
        Long employeeId = 110L;
        String month = "July";
        int year = 2024;

        given()
                .when()
                .get("/fetch?employeeId=" + employeeId + "&payrollMonth=" + month + "&payrollYear=" + year)
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("employeeId", equalTo(employeeId.intValue()))
                .body("payrollMonth", equalTo(month))
                .body("payrollYear", equalTo(year))
                .body("deductions", notNullValue())
                .body("netSalary", notNullValue());
    }
}
