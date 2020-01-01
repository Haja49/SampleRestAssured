package requestsInJira;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class CreateIssue {
	@Test
	public void createIssue() {
		// End Point
		RestAssured.baseURI = "https://rest.atlassian.net/rest/api/2/issue/";

		// Authorization  (preemptive indicates username is validated first and then the api key)
		RestAssured.authentication = RestAssured.preemptive().basic("hajamohideenece@gmail.com",
				"ePxbAZ9jTcZuUWqlr7gM8312");

		// Post the response
		Response response = RestAssured.given().contentType(ContentType.JSON)
				.body(new File("./dataFiles/CreateIssue.json")).post();

		// Get the Status code
		int statusCode = response.getStatusCode();
		System.out.println(statusCode);

		// Convert to JSON path and print the value of a particular field
		JsonPath responseJson = response.jsonPath();
		String incident = responseJson.get("id");
		System.out.println(incident);

	}
}
