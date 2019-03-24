package com.qa.tests;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.clientuniversal.RestClient;
import com.qa.pojo.pojoput;

public class APITest_PUT {
	RestClient rc;
	String url="https://reqres.in";
	String apiurl;
	
	@Test(priority=2)
	public void putApiTest() throws IOException
	{
		apiurl=url+"/api/users/2";
		System.out.println("Hitting URL : "+apiurl);
		
		rc=new RestClient();
		HashMap<String,String> headerMap=new HashMap<String,String>();
		headerMap.put("Content-Type","application/json");
		
		ObjectMapper mapper=new ObjectMapper();
		pojoput p3=new pojoput();
		p3.setJob("zion resident");
		p3.setName("morpheus");
		


		String entityString=mapper.writeValueAsString(p3);
		System.out.println("INPUT IS : "+entityString);
		CloseableHttpResponse response=rc.putcall(apiurl, entityString, headerMap);
		HttpEntity entity=response.getEntity();
		String resp=EntityUtils.toString(entity);
		
		System.out.println("Response Code is : "+response.getStatusLine().getStatusCode());
		System.out.println("Response LINE is : "+response.getStatusLine());
		
		pojoput p4=mapper.readValue(resp,pojoput.class);
		System.out.println("POJO OBJCT JOB VALUE IS :"+p4.getJob());
		System.out.println("POJO OBJCT NAME VALUE IS :"+p4.getName());
		System.out.println("POJO OBJCT UPDATED AT IS :"+p4.getUpdatedAt());
		
		System.out.println("RESPONSE IS : "+resp);
	}
}
