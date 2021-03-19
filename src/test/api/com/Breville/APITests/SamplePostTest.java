package com.Breville.APITests;


import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.Breville.Utilities.APIUtil;
import com.Breville.Utilities.ExcelReader;

import io.restassured.response.Response;


public class SamplePostTest extends APIUtil{

	@Test(dataProvider = "createUserData")
	public void verifyCustomerDetails(String name1 ,String job1)
	{	
		//String jsonBody = "{\"name\":\"+name+\",\"job\":\"Software1\"}"; 
		
		JSONObject reqparams = new JSONObject();
		
		reqparams.put("name",name1);
		reqparams.put("job",job1);
		
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Accept", "application/json");
		headers.put("Content-Type", "application/json");

		Response response=	postRequest(url,headers,reqparams.toJSONString());

		System.out.println(url);

		response.getStatusCode();

		System.out.println(response.getStatusCode());
		System.out.println();
	}
	
	@DataProvider(name = "createUserData")
	public Object [][]  getUserData() throws Exception
	{
		
		String path = System.getProperty("user.dir")+"\\Resources\\UserData.xlsx";
		
		return ExcelReader.getDataFromSpreadSheet(path,"Sheet1");
	}

}
