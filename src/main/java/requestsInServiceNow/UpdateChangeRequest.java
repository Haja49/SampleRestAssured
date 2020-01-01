package requestsInServiceNow;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class UpdateChangeRequest {
	@Test
	public void updateChangeRequest() {
		// End Point
		RestAssured.baseURI = "https://dev62749.service-now.com/api/now/v2/table/incident";

		// Authorization
		RestAssured.authentication = RestAssured.basic("admin", "Tuna@123");

		// Update the Change Request
		Response response = RestAssured.given().log().all().contentType(ContentType.JSON)
				.body("{\"short_description\" : \"Rest Assured updated this incident\"}")
				.put("4f8a840ddb810010cbea2bfa4b961958");
		response.prettyPrint();

		// Convert to JSON path and print the field value
		JsonPath jsonPath = response.jsonPath();
		String number = jsonPath.get("result.number");
		System.out.println(number);
		System.out.println(response.getStatusCode());
		System.out.println(response.getStatusLine());
	}
}
