package sampleChaining;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class CreateChangeRequest extends BaseRequests {
	@Test
	public void getChangeRequests() {
		// Post the Request
		Response response = headers.contentType(ContentType.JSON).body(new File("./dataFiles/data1.json")).post();

		// Get Status Code
		int statusCode = response.getStatusCode();
		System.out.println(statusCode);

		// System.out.println(response.prettyPrint());

		// Convert the response to JSON path and print particular filed
		JsonPath responseJson = response.jsonPath();
		sys_id = responseJson.get("result.sys_id");
		System.out.println(sys_id);

	}
}
