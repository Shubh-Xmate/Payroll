package com.payrollAndSalary.salary;

import io.restassured.response.Response;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PayrollControllerTest {

	public static String mobileNumber = "9935389980";

	@Test
	@Order(1)
	void createPayrollforUser() {
		HashMap<String, Object> data = new HashMap<>();
		data.put("mobileNumber", mobileNumber); // Example mobile number

		Response response = given()
				.contentType("application/json")
				.body(data)
				.when()
				.post("http://localhost:8080/api/payroll/create?mobileNumber=" + mobileNumber)
				.then()
				.log().all()
				.extract().response();

		int statusCode = response.getStatusCode();
		if (statusCode == 201) {
			String message = response.jsonPath().getString("message");
		} else {
			String errorMessage = response.jsonPath().getString("errorMessage");
			System.out.println("Error: " + errorMessage);
		}
	}

	@Test
	@Order(2)
	void fetchPayrollforValidUser() {
		Response response = given()
				.when()
				.get("http://localhost:8080/api/payroll/fetch?employeeId=3&payrollMonth=July&payrollYear=2024")
				.then()
				.log().all()
				.extract().response();

		int statusCode = response.getStatusCode();

		if (statusCode == 200) {
			// Positive case: User with the mobile number exists// Store employeeId globally
			response
					.then()
					.statusCode(200)
					.body("employeeId", equalTo(3))
					.body("payrollMonth", equalTo("July"))
					.body("payrollYear", equalTo(2024))
					.body("deductions", equalTo(0.0F))
					.body("netSalary", equalTo(215000.0F))
					.log().all();
		} else if (statusCode == 400) {
			String errorMessage = response.jsonPath().getString("errorMessage");
			System.out.println("Error: " + errorMessage);
		}
	}
}
