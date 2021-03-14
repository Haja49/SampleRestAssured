package sampleChaining;

import org.testng.annotations.BeforeSuite;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class BaseRequests {
	public static String sys_id;
	public static RequestSpecification headers;

	@BeforeSuite
	public void init() {
		// End Point
		RestAssured.baseURI = "https://dev61202.service-now.com/api/now/table/change_request";

		// Authorization
		RestAssured.authentication = RestAssured.basic("admin", "xxx");

		// Get log headers
		headers = RestAssured.given().log().headers();
	}
}
