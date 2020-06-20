package Karros_Test;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;



import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

//Question: The API endpoint - GET 
//https://my-json-server.typicode.com/typicode/demo/posts/1

public class API_GetMethod {
	@Test
	  public void VerifyResCode() throws IOException{
		  String url = "https://my-json-server.typicode.com/typicode/demo/posts";
		  RestAssured.baseURI =url;
		  RequestSpecification request = RestAssured.given();
		  Response resp = request.request(Method.GET, "/1");
		  int statuscode = resp.getStatusCode();
		  Assert.assertEquals(statuscode, 200);
		  
	}
	  @Test(dependsOnMethods="VerifyResCode")
	  public void VerifyId() throws IOException{
		  String url = "https://my-json-server.typicode.com/typicode/demo/posts";
		  RestAssured.baseURI =url;
		  RequestSpecification request = RestAssured.given();
		  Response resp = request.request(Method.GET, "/1");
		  JsonPath path = resp.jsonPath();
		  int id = path.get("id");
		  Assert.assertEquals(id, 1);
		 // int id = resp.getBody().asString().get;
	  }
}
