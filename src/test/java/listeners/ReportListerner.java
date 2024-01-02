package listeners;

import BaseTest.BaseTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.configuration.ViewName;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.opensourcedemo.core.properties_manager.GlobalConfig;
import org.opensourcedemo.core.webdriver_manager.WebDriverType;
import org.opensourcedemo.report.ReportManager;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.Arrays;

@Log4j2
public class ReportListerner implements ITestListener {
    ExtentReports extent;
    static ThreadLocal <ExtentTest> extentTestThread = new ThreadLocal<>();
    @Override
    public void onStart(ITestContext context) {
        System.out.println("onStart et le status"+ context.getName());
        if(!GlobalConfig.REPORTTEST.booleanValue()){
            extent = ReportManager.setup();
            GlobalConfig.REPORTTEST=true;
        }
    }
    @Override
    public void onTestStart(ITestResult result) {
        extentTestThread.set(extent.createTest(result.getName()));
        extentTestThread.get().assignCategory(result.getMethod().getGroups())
                        .info(Arrays.toString(result.getMethod().getGroups()))
                .info(result.getMethod().getDescription())
                .info(result.getMethod().getMethodName())
                .info(result.getMethod().getQualifiedName())
                .assignDevice(getBrowserName(BaseTest.getDriver()))
        ;
        log.info(" on start test, listener create test " + result.getName());
    }
    @Override
    public synchronized void onTestFailure(ITestResult result) {
        if(result.getStatus() == ITestResult.FAILURE){
            String failedTest = result.getName();
            String fileName = BaseTest.takeScreenShot(failedTest);
            extentTestThread.get()
                    .log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " - Test Case Failed ", ExtentColor.RED));
            extentTestThread.get()
                    .log(Status.FAIL, MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed ", ExtentColor.RED));
            extentTestThread.get()
                    .fail("Test Case Failed Snapshot is below " + extentTestThread.get().addScreenCaptureFromPath(GlobalConfig.PATHSCREENCHSOT +fileName));
        }
    }
    @Override
    public void onTestSkipped(ITestResult result) {
        log.info("On test skipped");
        String testSkipped = result.getName();
        String fileName = BaseTest.takeScreenShot(testSkipped);
        if(result.getStatus() == ITestResult.SKIP){
            extentTestThread.get().log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " - Test Case Skipped", ExtentColor.ORANGE))
                    .addScreenCaptureFromPath(GlobalConfig.PATHSCREENCHSOT + fileName);
        }
    }
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
        System.out.println("onTestFail with succes et le status"+ result.getStatus());
    }
    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        ITestListener.super.onTestFailedWithTimeout(result);
        System.out.println("onTestFail et le status"+ result.getStatus());
    }
    @Override
    public void onFinish(ITestContext context) {
        log.info("onFinish et le status"+ context.getName());
        extent.flush();
    }
    @Override
    public void onTestSuccess(ITestResult result){
        log.info("Test success " + result.getMethod().getMethodName());
        String testSucces = result.getName();
        String fileName = BaseTest.takeScreenShot(testSucces);
        extentTestThread.get().log(Status.PASS, MarkupHelper.createLabel(result.getName()+" Test Case PASSED", ExtentColor.BLUE))
                .addScreenCaptureFromPath(GlobalConfig.PATHSCREENCHSOT + fileName);
    }
    public static String getBrowserName(WebDriver paramDriver){
        String basetestname = BaseTest.getDriver().toString();
        if (basetestname.toLowerCase().contains("chrome")){
            return WebDriverType.CHROME.name();
        } else if (basetestname.toLowerCase().contains("firefox")) {
            return WebDriverType.FIREFOX.name();
        } else if (basetestname.toLowerCase().contains("edge")) {
            return WebDriverType.EDGE.name();
        }else {
            return "BROWSER-UNKNOWN";
        }
    }
}
