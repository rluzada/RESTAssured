package test;

import static io.restassured.RestAssured.*;

import org.hamcrest.Matchers;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class TestOnFakeAPI {

	// @Test
	public void Test01() {
		baseURI = "http://localhost:3000";
		given().when().get("/users/1").then().statusCode(200).log().all().assertThat().body("lastName",
				Matchers.equalTo("Luzada"));

	}

	//@Test
	public void Test02() {
		baseURI = "http://localhost:3000";
		Response response = get("/users");
		System.out.println(response.getStatusCode());	

		String jsonReponse = response.asString();
		// System.out.println(jsonReponse);

		JsonPath js = new JsonPath(jsonReponse);

		int Count = js.get("data.size()");

		for (int i = 0; i < Count; i++) {
			//int value = js.get("data[" + i + "].status.id");
			String firstName = js.getString("firstName");
			String lastName = js.getString("lastName");
			Assert.assertEquals(firstName, "[Rex, Marius, Margaret]");
			Assert.assertEquals(lastName, "[Luzada, Luzada, Luzada]");
			
		}

	}
	
	//@Test
	public void Test03() {
		baseURI = "http://localhost:3000";
		JSONObject json = new JSONObject();
		json.put("firstName", "Mary Pearl");
		json.put("lastName", "Luzada");
		json.put("subjectID", 2);	
		
		given().
			header("Content-Type"," application/json").
			contentType(ContentType.JSON).
			accept(ContentType.JSON).
			body(json.toJSONString()).		
		when().
		 post("/users").
		then().
		 statusCode(201).log().all().
		 body("firstName",
					Matchers.equalTo("Mary Pearl"));

		
	}
	
	//@Test
		public void Test04() {
			baseURI = "http://localhost:3000";
			JSONObject json = new JSONObject();
			json.put("firstName", "Mary Pearl");
			json.put("lastName", "Luzada");
			json.put("id", 3);	
			
			given().
				header("Content-Type"," application/json").
				contentType(ContentType.JSON).
				accept(ContentType.JSON).
				body(json.toJSONString()).		
			when().
			 put("/users/3").
			then().
			 statusCode(200).log().all().
			 body("firstName",
						Matchers.equalTo("Mary Pearl"));

			
		}
		
		@Test
		public void Test05() {
			baseURI = "http://localhost:3000";
			JSONObject json = new JSONObject();
			json.put("firstName", "Rex2");
			json.put("lastName", "Luzada");
			
			
			given().
				header("Content-Type"," application/json").
				contentType(ContentType.JSON).
				accept(ContentType.JSON).
				body(json.toJSONString()).		
			when().
			 patch("/users/1").
			then().
			 statusCode(200).log().all().
			 body("firstName",
						Matchers.equalTo("Rex2"));

			
		}


	//@Test
	public void TestDelete() {

		baseURI = "http://localhost:3000";
		when()
			.delete("/users/6").
		then()
			.statusCode(204).log().all();

	}


}
