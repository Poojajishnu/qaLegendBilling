package generalTest;




	
	import org.testng.ITestContext;
	import org.testng.ITestListener;
	import org.testng.ITestResult;

	import com.aventstack.extentreports.ExtentReports;
	import com.aventstack.extentreports.ExtentTest;
	import com.aventstack.extentreports.Status;

import com.qa.test.Base;
import com.qa.utility.ExtendReportUtility;


	public class Listener extends Base implements ITestListener 
	{
	ExtentTest test;

	ExtentReports extent=ExtendReportUtility.createExtentReports();
	ThreadLocal<ExtentTest> extentTest =new ThreadLocal<ExtentTest>();



	public void onTestStart(ITestResult result) {
	
	ITestListener.super.onTestStart(result);
	test =extent.createTest(result.getMethod().getMethodName());
	extentTest.set(test);

	}

	public void onTestSuccess(ITestResult result) {
	
	ITestListener.super.onTestSuccess(result);
	extentTest.get().log(Status.PASS, "Test Passed");
	}

	public void onTestFailure(ITestResult result) {
	
	ITestListener.super.onTestFailure(result);
	extentTest.get().log(Status.FAIL, "Test Failed");
	extentTest.get().fail(result.getThrowable());
	

	}

	public void onTestSkipped(ITestResult result) {
	ITestListener.super.onTestSkipped(result);
	extentTest.get().log(Status.SKIP, "Test Skipped");

	}



	public void onFinish(ITestContext context) {

	ITestListener.super.onFinish(context);
	extent.flush();
	}


	}


