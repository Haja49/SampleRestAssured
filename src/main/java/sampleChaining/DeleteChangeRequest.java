package sampleChaining;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class DeleteChangeRequest extends BaseRequests {
	@Test(dependsOnMethods = { "chaining.CreateChangeRequest.getChangeRequests" })
	// creating a dependency with Create Requests
	public void getChangeRequests() {
		// Delete Request
		Response response = headers.contentType(ContentType.JSON).delete(sys_id); // passing the sys_id

		// Get the status code
		int statusCode = response.getStatusCode();
		System.out.println(statusCode);
	}
}
