package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class utils {
	public static RequestSpecification req;
	public RequestSpecification requestSpecification() throws IOException
	{
		
		if(req==null) {
		//RestAssured.baseURI="https://rahulshettyacademy.com";
		PrintStream p=new PrintStream(new FileOutputStream("loggin.txt"));
		 req =new RequestSpecBuilder().setBaseUri(getGlobleProperty("baseUrl")).addQueryParam("key", "qaclick123")
				.addFilter(RequestLoggingFilter.logRequestTo(p))
				.addFilter(ResponseLoggingFilter.logResponseTo(p))
				.setContentType(ContentType.JSON).build();
		return req;
		}
		return req;
	}

	public static String getGlobleProperty(String key) throws IOException
	{
		Properties prop=new Properties();
		FileInputStream fis=new FileInputStream("src/test/java/resources/global.properties");
		prop.load(fis);
		return prop.getProperty(key);
		
	}
	
	
	public String getResponse(Response response, String key)
	{
		String responseString=response.asString();

		JsonPath js=new JsonPath(responseString);
		return js.get(key).toString();
	}
}

