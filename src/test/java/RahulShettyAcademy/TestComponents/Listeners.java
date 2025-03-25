package RahulShettyAcademy.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import RahulShettyAcademy.Resources.ExtentReporterNG;

//Before any test execution , control will come here
public class Listeners extends BaseTest implements ITestListener {

	ExtentTest test;
	ExtentReports extent = ExtentReporterNG.getReportObject();
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();// Thread safe, one run ine if 'll be created

	@Override
	public void onTestStart(ITestResult result) {// in this result variable all info about the test case is stored.
		// TODO Auto-generated method stub
		
		test = extent.createTest(result.getMethod().getMethodName());// This step holds an entry in the report for your
		// info
		// about the driver too by which the test case
		// is executed in the browser

		extentTest.set(test);// It 'll make the test object Threadsafe now//unique thread
								// id(ErrorValidationTest)->test // test case. result variable also holds the

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
	
		extentTest.get().log(Status.PASS, "Test Passed");// extentTest.get() will give the Thread ID with test object
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		
		extentTest.get().fail(result.getThrowable());// result is stroing all infor about the test case,
											// If the test case fails, as we are in this block,getThrowable() method
											// prints the failure message

		// Fails so Take screen shot + attach the screen shot into the report
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
//			shown above getTestClass() goes to xml file to see the class name, then getRealClass() goes to real class, 
//			then getField("driver") goes to the field driver in class level to fetch it. fields are always class level not method level

		} catch (Exception e) {// Exception is the parent class, It will catch all types of Exceptions
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String filePath = null;
		try {

			filePath = getScreenShot(result.getMethod().getMethodName(), driver);// we are passing the driver
																					// used by our test case to the
																					// utility taking screen shot
																					// method
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();// If screen shot does not exists then it will print the message
		}

		extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
// In this step attach the screen
		// shot taken to the Report

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSkipped(result);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
	
		extent.flush();// after all test cases you want run inividually or through xml is completed,
						// this statment is run after that only report will be generated. If you don't
						// write this statement, then all entries will be added to the Report but Report
						// will not be genarated.
	}

}
