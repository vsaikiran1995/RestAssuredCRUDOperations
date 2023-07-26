package apitestingwithRestAssured;

import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ReusableMethods {

	String URL = "http://localhost:8088/employees/";
	Response response;
	int employeeId;
	int numOfEmployees;

	public Response GetAllEmployees() {
		Response response = RestAssured.given()
		           .baseUri(URL)
		           .when()
		           .get();

		return response;
	}

	public Response PostEmployee(String firtName, String lastName, String salary, String email) {
		JSONObject jobj =  new JSONObject();
		jobj.put("firstName", firtName);
		jobj.put("lastName", lastName);
		jobj.put("salary", salary);
		jobj.put("email", email);
		
		Response response = RestAssured.given()
				                       .baseUri(URL)
				                       .when()
				                       .contentType(ContentType.JSON)
				                       .accept(ContentType.JSON)
				                       .body(jobj.toString())
				                       .post();

		return response;
	}

	public Response UpdateEmployee(int id,String firstName,String lastName,String salary,String email) {
		JSONObject jobj =  new JSONObject();
		jobj.put("firstName", firstName);
		jobj.put("lastName", lastName);
		jobj.put("salary", salary);
		jobj.put("email", email);
		
		Response response = RestAssured.given()
				                       .baseUri(URL+id)
				                       .when()
				                       .contentType(ContentType.JSON)
				                       .accept(ContentType.JSON)
				                       .body(jobj.toString())
				                       .put();

		return response;
	}

	public Response GetSingleEmployeeWithEmpId(int id) {
		Response response = RestAssured.given()
				                       .baseUri(URL+id)
				                       .when()
				                       .get();

		return response;
	}

	public Response DeleteEmployee(int id) {
		Response response = RestAssured.given()
		           .baseUri(URL+id)
		           .when()
		           .delete();

		return response;
	}

}
