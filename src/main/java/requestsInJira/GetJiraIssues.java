package requestsInJira;

import java.util.List;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GetJiraIssues {
	@Test
	public void getJiraIssues() {
		// End Point
		RestAssured.baseURI = "https://rest.atlassian.net/rest/api/2/search";

		// Authorization
		RestAssured.authentication = RestAssured.preemptive().basic("hajamohideenece@gmail.com",
				"r8q3W3GLEfsSQmuaeQF23479");

		// Get all Requests
		Response response = RestAssured.given().contentType(ContentType.JSON).param("jql", "project=\"RA\"").get();

		// Get the status code
		int statusCode = response.getStatusCode();
		System.out.println(statusCode);

		// COnvert the request to JSON and print the value of the issues
		JsonPath responseJson = response.jsonPath();

		List<String> issues = responseJson.getList("issues.id");
		System.out.println("Count of Issues: " + issues.size());
		for (String issueID : issues) {
			System.out.println(issueID);
		}

		// Get Particular issue
		System.out.println(responseJson.get("issues[0].id"));
	}
}
