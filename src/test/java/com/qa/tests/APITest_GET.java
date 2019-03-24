package com.qa.tests;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.testng.annotations.Test;

import com.qa.clientuniversal.RestClient;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;

public class APITest_GET {
	
	RestClient rc;
	String url="https://reqres.in";
	String apiurl;
	
	@Test(priority=1)
	public void getAPITest() throws ClientProtocolException, IOException
	{
		rc=new RestClient();
		apiurl=url+"/api/users/2";
		CloseableHttpResponse response=rc.getcall(apiurl);
		
		//1. GET STATUS LINE AND STATUS CODE ----------------------------
		int code=response.getStatusLine().getStatusCode();
		System.out.println("Response code is :"+code);
		System.out.println("Response Line is :"+response.getStatusLine());
		
		//2.GET HEADERS ---------------------------------
		
		Header[] headers=response.getAllHeaders();
		HashMap<String,String> headerMap=new HashMap<String,String>();
		for(Header h:headers)
		{
			headerMap.put(h.getName(), h.getValue());
		}
		System.out.println("Headers are : "+headerMap);
		System.out.println("CONTENT TYPE FOR HEADER FIELD IS : "+headerMap.get("Content-Type"));
		
		//3. RESPONSE BODY -----------------------
		HttpEntity entity=response.getEntity();
		String responseString=EntityUtils.toString(entity);
		System.out.println("Body is :"+responseString);
		
	}
	}	
		