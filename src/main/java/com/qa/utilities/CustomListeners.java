package com.qa.utilities;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.qa.clientuniversal.RestClient;



public class CustomListeners extends RestClient implements ITestListener
{

	public void onTestStart(ITestResult result) {
		logger.info(result.getMethod().getMethodName()+ " == Starting==");
		
	}

	public void onTestSuccess(ITestResult result) {
		//System.out.println(result.getMethod().getMethodName()+" Pass ******");
		logger.info(result.getMethod().getMethodName()+ " Pass ******");
		System.out.println("");
	}

	public void onTestFailure(ITestResult result) {
	//	System.out.println(result.getMethod().getMethodName()+" Fail ******");
		logger.info(result.getMethod().getMethodName()+ " Fail *****");
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

}
