package requestsInJira;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UpdateIssue {
	@Test
	public void createIssue() {
		// End Point
		RestAssured.baseURI = "https://rest.atlassian.net/rest/api/2/issue";

		// Authorization
		RestAssured.authentication = RestAssured.preemptive().basic("hajamohideenece@gmail.com",
				"r8q3W3GLEfsSQmuaeQF23479");

		// Update Request
		Response response = RestAssured.given().contentType(ContentType.JSON)
				.body("{ \"fields\": {\"summary\": \"Updated Defect Information\"}}").put("10020");

		// Print the response
		response.prettyPrint();

		// Print Status code
		System.out.println(response.getStatusCode());

	}
}
