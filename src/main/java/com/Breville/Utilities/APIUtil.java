package com.Breville.Utilities;

import java.util.Map;

import org.json.simple.JSONObject;

import com.Breville.APIBaseSetup.BaseSetup;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class APIUtil extends BaseSetup {
	private Response response;
	RequestSpecification httpRequest;
	private Exception e;

	public static Response postRequest(String url, Map<String, String> headers, String inputJson) {
		return (Response)((ValidatableResponse)((Response)RestAssured.given().relaxedHTTPSValidation().headers(headers).body(inputJson).post(url, new Object[0])).then()).extract().response();
	}

	public static Response getRequest(String url, Map<String, String> headers, String inputJson) {
		return (Response) ((ValidatableResponse) ((Response) RestAssured.given().relaxedHTTPSValidation().headers(headers).body(inputJson)
				.get(url)).then()).extract().response();
	}

	public String getStatus() {
		return response.getBody().asString();
	}

	public int getStatusCode() {
		return response.getStatusCode();
	}

	public boolean isSuccessful() {
		int code = response.getStatusCode();
		if (code == 200 || code == 201 || code == 202 || code == 203 || code == 204 || code == 205)
			return true;
		return false;
	}

	public String getStatusDescription() {
		return response.getStatusLine();
	}

	public Response getResponse() {
		return response;
	}

	public Exception getException() {
		return e;
	}

}
