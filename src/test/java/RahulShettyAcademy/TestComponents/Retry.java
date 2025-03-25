package RahulShettyAcademy.TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

//when we run our test cases, sometimes test fail not due to real functionality not passing,
//but due to some flakiness in test scripts or inconsistencies in the application, that time
//there is an Interface named IRetryAnalyzer which listens to Test Failure, and rerun it 
//whenever a test fails, It goes Listeners, then it comes back here asking whether user wants to rerun it again, 
//and how many times rerun
//whatever test cases you feel is failing without any proper reason, due to falkiness
//in that class level, write this line retryAnalyzer = Retry.class

public class Retry implements IRetryAnalyzer {

	int count = 0;
	int maxTry = 1;

	@Override
	public boolean retry(ITestResult result) {
		if (count < maxTry) {
			count++;
			return true;
		}
		return false;
	}

	// The above retry() method is returning false, as long as this method returns
	// true, it will rerun.
	// The above method runs once, rerun happens once.

}
