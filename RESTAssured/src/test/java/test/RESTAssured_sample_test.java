package test;

import static org.testng.Assert.assertEquals;

import org.apache.http.util.Asserts;
import org.hamcrest.Matchers;
import org.hamcrest.core.IsEqual;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

import io.restassured.response.Response;


public class RESTAssured_sample_test {

	@Test
	public void test1() {
		Response response = get("https://reqres.in/api/users?page=2");
		System.out.println(response.getStatusCode());
		System.out.println(response.getBody().asPrettyString());

		int statusCode = response.getStatusCode();

		assertEquals(statusCode, 200);

	}

	@Test
	public void test2() {
		baseURI = "https://reqres.in";		
		given().			
		get("/api/users?page=2").
		then().
		statusCode(200).
		body("data[1].first_name",Matchers.equalTo("Lindsay")).
		log().all();
		

	}

}
