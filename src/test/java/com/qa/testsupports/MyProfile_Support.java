package com.qa.testsupports;

import java.util.HashMap;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.clientuniversal.Constants;
import com.qa.clientuniversal.RestClient;
import com.qa.pojo.Pojo_Auth_Post;
import com.qa.pojo.Pojo_Profile_Post;
import com.qa.pojo.Pojo_Token;

public class MyProfile_Support extends RestClient{
	private static HashMap<String,String> headerMap;
	private static String entityString;
	private static ObjectMapper mapper;
	
	public static HashMap<String, String> setUpHeader()
	{
		headerMap=new HashMap();
		headerMap.put("Accept","application/json, text/plain, */*");
		headerMap.put("Connection", "keep-alive");
		headerMap.put("Content-Type", Constants.ContentType);
		headerMap.put("Cookie", Constants.cookie);
		headerMap.put("Host", "uix-stage04.blueoptima.com");	
		headerMap.put("Origin", "https://uix-stage04.blueoptima.com");
		headerMap.put("X-Auth-Token",Pojo_Token.getToken());
		
		return headerMap;
	}
	
	public static String setUpBody() throws JsonProcessingException 
	{
		mapper = new ObjectMapper();
		Pojo_Profile_Post p1=new Pojo_Profile_Post();
		p1.setFirstname(prop.getProperty("firstname"));
		p1.setSurname(prop.getProperty("surname"));
		p1.setE_mail(prop.getProperty("email"));
		p1.setTitle("");
		entityString=mapper.writeValueAsString(p1);
		return entityString;
	}

}
