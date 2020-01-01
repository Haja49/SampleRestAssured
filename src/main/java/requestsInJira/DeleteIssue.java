package requestsInJira;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class DeleteIssue {
	@Test
	public void deleteIssue() {
		// End Point
		RestAssured.baseURI = "https://rest.atlassian.net/rest/api/2/issue";

		// Authorization
		RestAssured.authentication = RestAssured.preemptive().basic("hajamohideenece@gmail.com",
				"r8q3W3GLEfsSQmuaeQF23479");

		// Delete Request
		Response response = RestAssured.given().contentType(ContentType.JSON).delete("10024");

		// Get the Status Code
		int statusCode = response.getStatusCode();
		System.out.println(statusCode);

	}
}
