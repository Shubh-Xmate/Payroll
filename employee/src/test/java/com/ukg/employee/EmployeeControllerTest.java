package com.ukg.employee;

import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.springframework.context.annotation.DependsOn;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeeControllerTest {

	public static Integer employeeId;
	public static String mobileNumber = "8979593098";

	@Test
	@Order(1)
	void createUser() {
		HashMap<String, Object> data = new HashMap<>();
		data.put("firstName", "silkie");
		data.put("lastName", "agarwal");
		data.put("mobileNumber", mobileNumber); // Example mobile number
		data.put("dob", "2018-11-01");
		data.put("managerId", 1);
		data.put("roleId", "1,2");
		data.put("dateOfJoining", "2018-11-01");
		data.put("salaryId", 1);

		Response response = given()
				.contentType("application/json")
				.body(data)
				.when()
				.post("http://localhost:8090/api/create")
				.then()
				.log().all()
				.extract().response();

		int statusCode = response.getStatusCode();
		if (statusCode == 201) {
			String message = response.jsonPath().getString("message");

			// Extract employeeId from the message
			Pattern pattern = Pattern.compile("employeeId = (\\d+)");
			Matcher matcher = pattern.matcher(message);
			if (matcher.find()) {
				employeeId = Integer.parseInt(matcher.group(1));
				System.out.println("Created Employee ID: " + employeeId);
			} else {
				System.out.println("Failed to extract employeeId from success message.");
			}

			System.out.println("Created mobileNumber: " + mobileNumber);
		}  else if (statusCode == 400) {
			String errorMessage = response.jsonPath().getString("errorMessage");
			System.out.println("Error: " + errorMessage);

			// Extract employeeId from errorMessage if it exists
			Pattern pattern = Pattern.compile("employeeId = (\\d+)");
			Matcher matcher = pattern.matcher(errorMessage);
			if (matcher.find()) {
				employeeId = Integer.parseInt(matcher.group(1));
				System.out.println("Existing Employee ID: " + employeeId);
			} else {
				System.out.println("Failed to extract employeeId from error message.");
			}
		}
	}

	@Test
	@Order(2)
	void fetchUserWithValidMobileNumber() {
		Response response = given()
				.when()
				.get("http://localhost:8090/api/fetch?mobileNumber=" + mobileNumber)
				.then()
				.log().all()
				.extract().response();

		int statusCode = response.getStatusCode();

		if (statusCode == 200) {
			// Positive case: User with the mobile number exists// Store employeeId globally
			response
					.then()
					.statusCode(200)
					.body("firstName", equalTo("silkie"))
					.body("lastName", equalTo("agarwal"))
					.body("mobileNumber", equalTo(mobileNumber))
					.body("dob", equalTo("2018-11-01"))
					.body("managerId", equalTo(1))
					.body("roleId", equalTo("1,2"))
					.body("dateOfJoining", equalTo("2018-11-01"))
					.body("salaryId", equalTo(1))
					.body("employeeId", equalTo(employeeId)) // Verify employeeId in response body
					.log().all();
		} else if (statusCode == 404) {
			// Negative case: User with the mobile number does not exist
			response
					.then()
					.statusCode(400)
					.body("errorMessage", equalTo("In Employee , mobileNumber is not found for value " + mobileNumber))
					.body("apiPath", equalTo("uri=/api/fetch"))
					.body("statusCode", equalTo("BAD_REQUEST"))
					.log().all();
		} else {
			// Handle unexpected status code
			System.out.println("Unexpected status code: " + statusCode);
			Assertions.fail("Unexpected status code: " + statusCode);
		}
	}



	@Test
	@Order(3)
	void fetchUserByEmployeeId() {
		System.out.println(employeeId);
		if (employeeId == 0)
			Assertions.fail("employee not found");
			// Use the global employeeId variable
		else {
			Response response = given()
					.when()
					.get("http://localhost:8090/api/fetchById?employeeId=" + employeeId)
					.then()
					.extract().response();

			int statusCode = response.getStatusCode();

			if (statusCode == 200) {
				// Positive case: Employee ID exists
				response
						.then()
						.statusCode(200)
						.body("firstName", equalTo("silkie"))
						.body("lastName", equalTo("agarwal"))
						.body("mobileNumber", equalTo(mobileNumber))
						.body("dob", equalTo("2018-11-01"))
						.body("managerId", equalTo(1))
						.body("roleId", equalTo("1,2"))
						.body("dateOfJoining", equalTo("2018-11-01"))
						.body("salaryId", equalTo(1))
						.log().all();
			} else if (statusCode == 404) {
				// Negative case: Employee ID does not exist
				response
						.then()
						.statusCode(404)
						.body("message", equalTo("Employee not found for ID: " + employeeId))
						.log().all();
			} else {
				// Handle unexpected status code
				System.out.println("Unexpected status code: " + statusCode);
			}
		}
	}

	@Test
	@Order(6)
	void updateUser() {
		HashMap<String, Object> data = new HashMap<>();
		data.put("firstName", "silkie");
		data.put("lastName", "agarwal");
		data.put("mobileNumber", mobileNumber);
		data.put("dob", "2018-11-01");
		data.put("managerId", 2);
		data.put("roleId", "1,2");
		data.put("dateOfJoining", "2018-11-01");
		data.put("salaryId", 1);
		data.put("employeeId", employeeId);

		Response response = given()
				.contentType("application/json")
				.body(data)
				.when()
				.put("http://localhost:8090/api/update?mobileNumber=" + mobileNumber)
				.then()
				.log().all()
				.extract().response();

		int statusCode = response.getStatusCode();
		if (statusCode == 200) {
			System.out.println("User with mobile number " + mobileNumber + " was successfully updated.");
		} else if (statusCode == 404) {
			System.out.println("User with mobile number " + mobileNumber + " was not found.");
		} else {
			System.out.println("Unexpected status code: " + statusCode);
		}
	}

	@Test
	@Order(7)
	void deleteUser() {
		Response response = given()
				.when()
				.delete("http://localhost:8090/api/delete?mobileNumber=" + mobileNumber)
				.then()
				.log().all()
				.extract().response();

		int statusCode = response.getStatusCode();
		if (statusCode == 200) {
			System.out.println("User with mobile number " + mobileNumber + " was successfully deleted.");
		} else if (statusCode == 404) {
			System.out.println("User with mobile number " + mobileNumber + " was not found.");
		} else {
			System.out.println("Unexpected status code: " + statusCode);
		}
	}
	@Test()
	@Order(4)
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
	@Order(5)
	void fetchPayrollforValidUser() {
		Response response = given()
				.when()
				.get("http://localhost:8080/api/payroll/fetch?employeeId=" + employeeId + "&payrollMonth=July&payrollYear=2024")
				.then()
				.log().all()
				.extract().response();

		int statusCode = response.getStatusCode();

		if (statusCode == 200) {
			// Positive case: User with the mobile number exists // Store employeeId globally
			response
					.then()
					.statusCode(200)
					.body("employeeId", equalTo(employeeId))
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