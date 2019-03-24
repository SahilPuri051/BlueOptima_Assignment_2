package com.qa.testcases;

import java.io.IOException;
import java.util.HashMap;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.clientuniversal.Constants;
import com.qa.clientuniversal.RestClient;
import com.qa.pojo.Pojo_Auth_Post;
import com.qa.tests.APITest_POST;
import com.qa.testsupports.Authenticate_Support;

public class Authenticate_Test extends RestClient {
	
	String apiurl=url+Constants.Auth_Url;
	APITest_POST testpost;
	private HashMap<String,String> headerMap;
	private static String entityString;
	private static CloseableHttpResponse response;
	private static HttpEntity entity;
	private static String entity2;
	private static String sessiontoken=null;
	ExtentTest elog;
	
	@BeforeTest
	public void Authenticate_Test()
	{
		initialize();
	}
	
	@BeforeClass
	public void setUp() 
	{
		testpost=new APITest_POST();
		elog=extent
				.createTest("Authentication Post Test");
	}
	
	@Test(priority=1)
	public void negativeAuthtest1()
	{
		try 
		{
			headerMap=Authenticate_Support.setUpNegativeHeader();
			entityString=Authenticate_Support.setUpBody();
			response=testpost.postAPITest(apiurl,headerMap,entityString);
			Assert.assertEquals(response.getStatusLine().getStatusCode(),404);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		elog.log(Status.PASS,"Negative Header Authentication Test is Verified");
	}
	
	@Test(priority=2)
	public void negativeAuthtest2()
	{
		try 
		{
			headerMap=Authenticate_Support.setUpHeader();
			entityString=Authenticate_Support.setUpNegativeBody();
			response=testpost.postAPITest(apiurl,headerMap,entityString);
			Assert.assertEquals(response.getStatusLine().getStatusCode(),401);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		elog.log(Status.PASS,"Negative Body Authentication Test is Verified");
	}
	
	
	@Test(priority=3)
	public void AuthresponseCode() throws JsonProcessingException 
		{
		try 
		{
			headerMap=Authenticate_Support.setUpHeader();
			entityString=Authenticate_Support.setUpBody();
			response=testpost.postAPITest(apiurl,headerMap,entityString);
			entity=response.getEntity();
			entity2=EntityUtils.toString(entity);
			System.out.println(response.getStatusLine().getStatusCode());
			Assert.assertEquals(response.getStatusLine().getStatusCode(),200);
		} 
		catch (IOException e) 
		{
		
			e.printStackTrace();
		}
		elog.log(Status.PASS,"Positive Authentication Test is Verified");
		}
		
	
	@Test(priority=4)
	public void AuthresponseContent() 
		{
		try {
			headerMap=Authenticate_Support.setUpHeader();
			entityString=Authenticate_Support.setUpBody();
			response=testpost.postAPITest(apiurl,headerMap,entityString);
			entity=response.getEntity();
		
			entity2=EntityUtils.toString(entity);
		} 
		catch (IOException e) 
		{
		
			e.printStackTrace();
		}
		
		System.out.println("Output Body : "+entity2);
	    JSONObject ob=new JSONObject(entity2);
	    sessiontoken =ob.get("token").toString();
	    Assert.assertNotNull(sessiontoken);
	    Authenticate_Support.sendToken(sessiontoken);
	    elog.log(Status.PASS," Authentication Response Test is Verified");
		}

		
		
}
