package com.qa.clientuniversal;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.log4j.Logger;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;


public class RestClient {
	
	public static Properties prop;
	public static Properties prop2;
	public String url="https://uix-stage04.blueoptima.com";
	public static FileOutputStream fos;
	public static Logger logger;
	public static ExtentHtmlReporter reporter;
	public static ExtentReports extent;
    
	public static void initialize()
	{
		//Initializing Properties File for Environment variables
		prop=InitiliazerClass.propInitializer(prop,System.getProperty("user.dir")+"/Config/config.properties");
		prop2=InitiliazerClass.propInitializer(prop2,System.getProperty("user.dir")+"/Config/token.properties");
		
		// Initializing Log4j logging Files
		logger=InitiliazerClass.logInitialize(logger);
		
		// Initializing Extent Reports Files
		extent=InitiliazerClass.reportInitializer(reporter,extent);
	
		
		try {
			
			fos=new FileOutputStream(System.getProperty("user.dir")+"/Config/token.properties");
		
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
	}
	
	//1.GET CALL
	public CloseableHttpResponse getcall(String url) throws ClientProtocolException, IOException
	{
		CloseableHttpClient httpclient=HttpClients.createDefault();
		HttpGet httpget=new HttpGet(url);
		CloseableHttpResponse response=httpclient.execute(httpget);
		return response;
	}
	
	//2.POST CALL
	public static CloseableHttpResponse postcall(String url,String entityString,HashMap<String,String> headerMap) throws IOException
	{
		CloseableHttpClient httpclient=HttpClients.createDefault();
		HttpPost httppost = new HttpPost(url);
		httppost.setEntity(new StringEntity(entityString));
		for(Entry<String,String> entry:headerMap.entrySet())
		{
			httppost.addHeader(entry.getKey(),entry.getValue());
		}
		CloseableHttpResponse response=	httpclient.execute(httppost);
		return response;
		
	}
	
	//3.PUT CALL ================================================
	public CloseableHttpResponse putcall(String url,String entityString,HashMap<String,String>headerMap) throws IOException
	{
		CloseableHttpClient httpclient=HttpClients.createDefault();
		HttpPut httpput=new HttpPut(url);
		for(Entry<String,String> entry:headerMap.entrySet() )
		{
			httpput.addHeader(entry.getKey(),entry.getValue());
		}
		
		httpput.setEntity(new StringEntity(entityString));
		CloseableHttpResponse response=httpclient.execute(httpput);
		return response;
		
	}
	
	//4. DELETE CALL ========================
	public CloseableHttpResponse deletecall(String url) throws IOException
	{
		
		CloseableHttpClient httpclient=HttpClients.createDefault();
		HttpDelete httpdelete=new HttpDelete(url);
		CloseableHttpResponse response= httpclient.execute(httpdelete);
		return response;
	}
}
