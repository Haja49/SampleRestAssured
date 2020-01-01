package requestsInServiceNow;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class GetChangeRequestsXml {
	@Test
	public void getChangeRequests() {
		// End Point
		RestAssured.baseURI = "https://dev61202.service-now.com/api/now/table/change_request";

		// Authorization
		RestAssured.authentication = RestAssured.basic("admin", "*****");

		// Adding all required parameters to a map
		Map<String, String> params = new HashMap<String, String>();
		params.put("category", "Network"); // Request Parameter
		params.put("state", "2"); // Request Parameter
		params.put("sysparm_fields", "sys_id,number,priority"); // Query Parameter

		// Get the response by using parameters and injecting XML data as input
		Response response = RestAssured.given().accept(ContentType.XML)
				// passing Request and Query Parameters
				.params(params).queryParam("sysparm_fields", "sys_id,number,priority")

				// Form Parameters can be used instead of Body .formParams("key", "value");
				// Path Parameters can be used along with End Point .pathParams("id", "path to be added to End Point")
				.get();
		// Get the status code from the response
		int statusCode = response.getStatusCode();
		System.out.println(statusCode);

		// Print the response
		response.prettyPrint();

		// Convert the response to XML and get all the fields under a particular field
		XmlPath responseXml = response.xmlPath();
		List<String> incidentsList = responseXml.getList("response.result.number");

		// Print all the results
		System.out.println("Count: " + incidentsList.size());
		for (String incident : incidentsList) {
			System.out.println(incident);
		}

		List<String> sysIdList = responseXml.getList("response.result.sys_id");
		for (String sysID : sysIdList) {
			System.out.println(sysID);
		}
	}
}
