package com.qa.tests;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.clientuniversal.RestClient;

public class APITest_DELETE {
	RestClient rc;
	String url="https://reqres.in";
	String apiurl;
	
	@Test(priority=4)
	public void deleteAPITest() throws ClientProtocolException, IOException
	{
		apiurl=url+"/api/users/2";
		rc=new RestClient();
		CloseableHttpResponse response=rc.deletecall(apiurl);
		System.out.println("Response Code is : "+response.getStatusLine().getStatusCode());
		System.out.println("Response LINE is : "+response.getStatusLine());
		Assert.assertEquals(response.getStatusLine().getStatusCode(),204);
		
		Header[] headers=response.getAllHeaders();
		HashMap<String,String> headerMap=new HashMap<String,String>();
		for(Header h:headers)
		{
			headerMap.put(h.getName(), h.getValue());
		}
		System.out.println("Headers are : "+headerMap);
		Assert.assertNull(response.getEntity());
		
	}
}
