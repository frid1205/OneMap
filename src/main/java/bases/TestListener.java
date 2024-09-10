package bases;

import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;


public class TestListener extends BaseConfig implements ISuiteListener, ITestListener {
	//private static ExtentReports extent = null;
	

    @Override
    public void onStart(ITestContext context) {
    	System.out.println("start");
    	test = extent.createTest(context.getName());
    }

    @Override
    public void onTestStart(ITestResult result) {
    	test.info(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.pass("Test Passed: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        // Dijalankan jika metode tes gagal
        System.out.println("Test Failed: " + result.getMethod().getMethodName());
        System.out.println("Cause: " + result.getThrowable());
        test.fail("Test Failed: " + result.getMethod().getMethodName()+"\n Cause : "+result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        // Dijalankan jika metode tes dilewati
        System.out.println("Test Skipped: " + result.getMethod().getMethodName());
        System.out.println("Cause: " + result.getThrowable());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // Dijalankan jika metode tes gagal tetapi berada dalam persentase keberhasilan yang ditentukan
        System.out.println("Test Failed But Within Success Percentage: " + result.getMethod().getMethodName());
    }


    @Override
    public void onFinish(ITestContext context) {
    	if (extent != null) {
    		System.out.println("finish");
			extent.flush();
		}
    }
}
