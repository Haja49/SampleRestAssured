package requestsInServiceNow;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class CreateChangeRequest {
	@Test
	public void createChangeRequests() {
		// End Point
		RestAssured.baseURI = "https://dev61202.service-now.com/api/now/table/change_request";

		// Authorization
		RestAssured.authentication = RestAssured.basic("admin", "*****");
		// RestAssured.authentication =
		// RestAssured.oauth2("9cbHvHPvLtZeOkJGi8bvY23eyG2o0YMie0-TwlvrS3YMRCsXQS2I3W7PH2U4APSNGkO9LZ4MquLqlE-_XBBJKg");

		// Adding all the required headers to a map
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("content-type", "application/json");
		headers.put("accept", "application/xml");

		// Post the request
		// RestAssured.basic("admin", "xyz"); (alternate authorization)
		Response response = RestAssured.given().contentType(ContentType.JSON)
				// .header(new Header("content-type", "application/json"))
				.headers(headers)
				// Form Parameters can be used instead of Body
				// .formParam("short_description", "Rest Assured")
				// .formParam("Category", "Network")
				.body(new File("./dataFiles/data1.json")).post();

		// Get status code from the response
		int statusCode = response.getStatusCode();
		System.out.println(statusCode);

		// Print the response
		System.out.println(response.prettyPrint());

		// Convert the response to JSON and get the value of particular field
		JsonPath responseJson = response.jsonPath();
		String changeRequestId = responseJson.get("result.sys_id");
		System.out.println(changeRequestId);

		// Print the response headers
		Headers headers2 = response.getHeaders();
		for (Header header : headers2) {
			System.out.println(header.getName());
			System.out.println(header.getValue());
		}

		// Print the cookies
		System.out.println("******************************");
		Map<String, String> cookies = response.getCookies();
		for (Entry<String, String> cookie : cookies.entrySet()) {
			System.out.println(cookie.getKey());
			System.out.println(cookie.getValue());
		}
		System.out.println("******************************");
	}
}
