package test;

import static io.restassured.RestAssured.*;

import java.util.HashMap;
import java.util.Map;

import org.hamcrest.Matchers;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class GetAndPost {

	@Test
	public void TestGet() {
		baseURI = "https://reqres.in";
		given().
		get("/api/users?page=2").
		then().
			statusCode(200).
			body("data[1].first_name", Matchers.equalTo("Lindsay"))
			.log().all();
	}
	@Test
	public void TestPost() {	

		JSONObject jsonrequest = new JSONObject();
		jsonrequest.put("Name", "Rex Luzada");
		jsonrequest.put("Job", "Cyclist");

		System.out.println(jsonrequest.toJSONString());
		baseURI = "https://reqres.in/api";
		given().
		  	header("Content-Type"," application/json").
			contentType(ContentType.JSON).
			accept(ContentType.JSON).
			body(jsonrequest.toJSONString())
		.when()
			.post("/users").
		then()
			.statusCode(201).log().all();

	}

	@Test
	public void TestPut() {

		JSONObject jsonrequest = new JSONObject();
		jsonrequest.put("Name", "Rex Luzada");
		jsonrequest.put("Job", "Cyclist");

		baseURI = "https://reqres.in/api";
		given().
			header("Content-Type"," application/json").
			contentType(ContentType.JSON).
			accept(ContentType.JSON).
			body(jsonrequest.toJSONString()).
		when()
			.put("/users").
		then()
			.statusCode(200).log().all();

	}

	@Test
	public void TestPatch() {

		JSONObject jsonrequest = new JSONObject();
		jsonrequest.put("Name", "Rex Luzada");
		jsonrequest.put("Job", "Cyclist");

		baseURI = "https://reqres.in/api";
		given().
			header("Content-Type"," application/json").
			contentType(ContentType.JSON).
			accept(ContentType.JSON).
			body(jsonrequest.toJSONString()).
		when()
			.patch("/users/2").
		then()
			.statusCode(200).log().all();

	}

	@Test
	public void TestDelete() {

		baseURI = "https://reqres.in/api";
		when()
			.delete("/users/2").
		then()
			.statusCode(204).log().all();

	}

}
