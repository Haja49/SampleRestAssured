package requestsInServiceNow;

import java.io.File;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class CreateChangeRequestUsingDP {

	@DataProvider(name = "GetFiles", parallel = true) // achieving parallel execution for multiple data's
	public String[][] getFileNames() {
		// Creating a two dimensional array and pass the input files. Here there are two
		// input JSON files
		String[][] fileNames = new String[2][1];
		fileNames[0][0] = "./dataFiles/data1.json";
		fileNames[1][0] = "./dataFiles/data2.json";
		return fileNames;
	}

	@Test(dataProvider = "GetFiles")
	public void getChangeRequests(String fileName) {
		// End Point
		RestAssured.baseURI = "https://dev61202.service-now.com/api/now/table/change_request";

		// Authorization
		RestAssured.authentication = RestAssured.basic("admin", "*****");

		// Pass the data from JSON file in the body using Data Provider and post the
		// response
		File file = new File(fileName);
		Response response = RestAssured.given().contentType(ContentType.JSON).body(file).post();

		// Get the status code from the response
		int statusCode = response.getStatusCode();
		System.out.println(statusCode);

		// Convert the result to JSON path and print the result of a particular field
		JsonPath responseJson = response.jsonPath();
		String incident = responseJson.get("result.sys_id"); // In case of XML, return type (Object) should to type cast to String
		System.out.println(incident);

	}
}
