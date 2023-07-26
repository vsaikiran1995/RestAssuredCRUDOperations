package apitestingwithRestAssured;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;

public class E2ETesting extends ReusableMethods{
	JsonPath jpath;

	@Test
	public void TC01_GetAllEmployees()	{
		System.out.println("Inside Get All Employees:");
		response = GetAllEmployees();
		Assert.assertEquals(response.statusCode(), 200);
		System.out.println("Response body before operation: "+response.body().asString());
		jpath = response.jsonPath();
		numOfEmployees=jpath.getList("employees").size();
		Assert.assertEquals(numOfEmployees, 3);
	}
	@Test
	public void TC02_PostEmployee() {
		System.out.println("Inside Create Employee:");
		response = PostEmployee("John","Kiran","74000","Testing@gmail.com");
		Assert.assertEquals(response.statusCode(), 201);
		System.out.println("Response body is "+response.body().asString());	
		Assert.assertEquals(response.jsonPath().get("firstName"), "John");
		jpath = response.jsonPath();
		employeeId = jpath.get("id");
		System.out.println("Created employeeid is :"+employeeId);
		jpath = response.jsonPath();
		response=GetAllEmployees();
		System.out.println("Response body before operation: "+response.body().asString());
		
		
	}
	
	@Test
	public void TC03_UpdateEmployee() {
		System.out.println("Inside Update Employee:");
		response = UpdateEmployee(employeeId,"Tom","Curse","74000","Testing@gmail.com");
		Assert.assertEquals(response.statusCode(), 200);
		jpath = response.jsonPath();
		Assert.assertNotEquals(jpath.get("firstName"), "John");
	}
	
	@Test
	public void TC04_GetSingleEmployeeWithID( ) {
		System.out.println("Inside Get Single Employee:");
		response = GetSingleEmployeeWithEmpId(employeeId);
		System.out.println("Response body is :"+response.body().asString());
		Assert.assertEquals(response.statusCode(), 200);
		jpath=response.jsonPath();
		Assert.assertEquals(jpath.get("firstName"), "Tom");
	}
	
	
	@Test
	public void TC05_DeleteEmployee() {
		System.out.println("Inside delete employee method");
		response = DeleteEmployee(employeeId);
		Assert.assertEquals(response.statusCode(), 200);
		response = GetAllEmployees();
		System.out.println("After CRUD Operations: "+response.body().asString());
	}
	
	@Test
	public void TC06_GetSingleEmployeeWithID() {
		System.out.println("Inside Get Single Employee:");
		response = DeleteEmployee(employeeId);
		Assert.assertEquals(response.statusCode(), 400);
		
	}
	
	@Test
	public void TC07_GetAllEmployees() {
		System.out.println("Inside Get AllEmployees:");
		response = GetAllEmployees();
		Assert.assertEquals(response.statusCode(), 200);
		System.out.println("Response body before operation: "+response.body().asString());
		jpath = response.jsonPath();
		numOfEmployees=jpath.getList("employees").size();
		Assert.assertEquals(numOfEmployees, 3);
		
	}

}
