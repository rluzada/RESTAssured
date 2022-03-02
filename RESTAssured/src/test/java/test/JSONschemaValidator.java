package test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.*;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

public class JSONschemaValidator {
	
	@Test
	public void TestGet() {
		baseURI = "https://reqres.in";
		given().
		get("/api/users?page=2").
		then().
			statusCode(200).
			assertThat().body(matchesJsonSchemaInClasspath("schema.json"));
			
		
	}

}
