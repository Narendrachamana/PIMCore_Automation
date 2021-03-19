package com.Breville.APIBaseSetup;

import org.testng.annotations.BeforeSuite;
import com.Breville.Utilities.ReadProperty;

import io.restassured.RestAssured;

public class BaseSetup {
	
	ReadProperty pr = new ReadProperty("Config");
	public  String url = pr.getProperty("baseURI");
	
	@BeforeSuite
	public void setUp()
	{
	
	}
	
	public void tearDown()
	{
		
	}
	

}
