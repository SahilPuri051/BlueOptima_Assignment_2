package com.qa.tests;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.qa.clientuniversal.RestClient;
import com.qa.pojo.Pojoget_UNFINISHED;
import com.qa.pojo.Pojo_Auth_Post;

public class APITest_POST extends RestClient {

	HashMap<String,String> headerMap;

	public CloseableHttpResponse postAPITest(String apiurl,HashMap<String,String> headerMap,String entityString) throws IOException
		{
		
		System.out.println("Hitting URL : "+apiurl);
		System.out.println("Request Header :"+entityString);
		System.out.println("Request Body:"+headerMap);
	
		this.headerMap=headerMap;
		CloseableHttpResponse response=RestClient.postcall(apiurl, entityString, headerMap);
	
    	return response;
		}
	}
     
