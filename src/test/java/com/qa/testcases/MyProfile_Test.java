package com.qa.testcases;

import java.io.IOException;
import java.util.HashMap;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.qa.clientuniversal.Constants;
import com.qa.clientuniversal.RestClient;
import com.qa.tests.APITest_POST;
import com.qa.testsupports.MyProfile_Support;

public class MyProfile_Test extends RestClient {
	
	String apiurl=url+Constants.MyProfile_POSTUrl;
	private HashMap<String,String> headerMap;
	private static CloseableHttpResponse response;
	private static String entityString;
	private static HttpEntity entity;
	private static String entity2;
	private static String sessiontoken=null;
	private static APITest_POST testpost;
	private static ExtentTest elog;
	private static String MsgResponse;
	
	@BeforeClass public void setUp() 
	{ 
	//initialize(); 
	  testpost=new APITest_POST(); 
	  elog=extent
				.createTest("MyProfile_POST Test");
	 }
	
	@Test(priority=5)
	public void setMyProfile()
	{
		{
			try 
			{
				headerMap=MyProfile_Support.setUpHeader();
				entityString=MyProfile_Support.setUpBody();
				response=testpost.postAPITest(apiurl,headerMap,entityString);
				Assert.assertEquals(response.getStatusLine().getStatusCode(),200);
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
		elog.log(Status.PASS,"My Profile POST Test is Verified");
	}
	
	
	@Test(priority=5)
	public void getMyProfile()
	{
		{
			try 
			{
				headerMap=MyProfile_Support.setUpHeader();
				entityString=MyProfile_Support.setUpBody();
				response=testpost.postAPITest(apiurl,headerMap,entityString);
				
				Assert.assertEquals(response.getStatusLine().getStatusCode(),200);
				
				entity=response.getEntity();
				entity2=EntityUtils.toString(entity);
				JSONObject ob=new JSONObject(entity2);
				MsgResponse =ob.get("messageKey").toString();
				
				Assert.assertTrue(MsgResponse.equals("PROFILE.UPDATED_SUCCESSFULLY"));
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
			elog.log(Status.PASS,"My Profile POST Test is Verified");
	}
	
}
