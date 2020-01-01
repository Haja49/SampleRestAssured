package requestsInServiceNow;

import java.util.List;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GetChangeRequests {
	@Test
	public void getChangeRequests() {
		// End Point
		RestAssured.baseURI = "https://dev61202.service-now.com/api/now/table/change_request";

		// Authorization
		RestAssured.authentication = RestAssured.basic("admin", "*****");

		// Get Request and Print the logs
		Response response = RestAssured.given().log().all().get();

		// Get the status code from the response
		int statusCode = response.getStatusCode();
		System.out.println(statusCode);

		// Print the response
		response.prettyPrint();

		// Convert to JSON and get all fields under a particular field
		JsonPath responseJson = response.jsonPath();
		List<String> incidentsList = responseJson.getList("result.number");

		// Print all the results
		System.out.println("Count: " + incidentsList.size());
		for (String incident : incidentsList) {
			System.out.println(incident);
		}

		List<String> sysIdList = responseJson.getList("result.sys_id");
		for (String sysID : sysIdList) {
			System.out.println(sysID);
		}
	}
}
