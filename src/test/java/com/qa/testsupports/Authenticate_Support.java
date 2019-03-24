package com.qa.testsupports;

import java.io.IOException;
import java.util.HashMap;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.clientuniversal.Constants;
import com.qa.clientuniversal.RestClient;
import com.qa.pojo.Pojo_Auth_Post;
import com.qa.pojo.Pojo_Token;


public class Authenticate_Support extends RestClient {
	
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
		headerMap.put("Referer", "https://uix-stage04.blueoptima.com/uix/login");
		
		return headerMap;
	}
	
	public static HashMap<String, String> setUpNegativeHeader()
	{
		headerMap=new HashMap();
		headerMap.put("Accept","Wrong Header");
		headerMap.put("Connection", "keep-alive");
		headerMap.put("Content-Type", "application/json;charset=UTF-8");
		headerMap.put("Cookie", "_ga=fgfgrgr66806128.1553428715");
		headerMap.put("Host", "Wrong Header");	
		headerMap.put("Origin", "Wrong Header");
		headerMap.put("Referer", "https://uix-stage04.blueoptima.com/uix/login");
		
		return headerMap;
	}
	
	public static String setUpNegativeBody()
	{
		mapper = new ObjectMapper();
		Pojo_Auth_Post p1=new Pojo_Auth_Post();
		p1.setUserName("Wrong Body");
		p1.setPassword("Wrong Body");
		p1.setTerminate(Constants.setTerminate);
		p1.setRememeberMe(Constants.setRememberMe);
		try {
			entityString=mapper.writeValueAsString(p1);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return entityString;
	}
	
	public static String setUpBody() throws JsonProcessingException 
	{
		mapper = new ObjectMapper();
		Pojo_Auth_Post p1=new Pojo_Auth_Post();
		p1.setUserName(prop.getProperty("username"));
		p1.setPassword(prop.getProperty("password"));
		p1.setTerminate(Constants.setTerminate);
		p1.setRememeberMe(Constants.setRememberMe);
		entityString=mapper.writeValueAsString(p1);
		return entityString;
	}

	public static void sendToken(String token)
	{
		
		try 
		{
			prop.setProperty("Auth-token",token);
			prop.store(fos,"token");
			Pojo_Token.setToken(token);
		} 
		
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

}
