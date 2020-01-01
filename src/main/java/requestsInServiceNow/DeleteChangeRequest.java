package requestsInServiceNow;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class DeleteChangeRequest {
	@Test
	public void deleteChangeRequests() {
		// End Point
		RestAssured.baseURI = "https://dev61202.service-now.com/api/now/table/change_request";

		// Authorization
		RestAssured.authentication = RestAssured.basic("admin", "*****");

		// Delete request
		Response response = RestAssured.given().contentType(ContentType.JSON)
				// Path Parameters can be used along with End Point
				// .pathParam("sysi_id", "400315b4dbc20010d27f8e4748961974")
				// .delete();
				.delete("400315b4dbc20010d27f8e4748961974"); // passing the sys_id. Can also be passed along with the
																// end point
		// Get the status code
		int statusCode = response.getStatusCode();
		System.out.println(statusCode);
	}
}
