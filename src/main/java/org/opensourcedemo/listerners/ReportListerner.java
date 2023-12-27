package org.opensourcedemo.listerners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import lombok.extern.log4j.Log4j2;
import org.opensourcedemo.BaseTest.BaseTest;
import org.opensourcedemo.report.ReportManager;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

@Log4j2
public class ReportListerner implements ITestListener {
    ExtentReports extent = ReportManager.setup();
    ExtentTest test;
    ThreadLocal <ExtentTest> extentTestThread = new ThreadLocal<>();
    @Override
    public void onStart(ITestContext context) {
        ITestListener.super.onStart(context);
        System.out.println("onStart et le status"+ context.getName());
    }
    @Override
    public void onTestStart(ITestResult result) {
        ITestListener.super.onTestStart(result);
        test = extent.createTest(result.getName());
        extentTestThread.set(test);
        log.info(" on start test, listener create test " + result.getName());
    }
    @Override
    public void onTestFailure(ITestResult result) {
        if(result.getStatus() == ITestResult.FAILURE){
            extentTestThread.get().log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
            extentTestThread.get().log(Status.FAIL, MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));
           // String screenshotPath = BaseTest.takeScreenShot(result.getName());
            extentTestThread.get().fail("Test Case Failed Snapshot is below " );//+ test.addScreenCaptureFromPath(screenshotPath));
        }
    }
    @Override
    public void onTestSkipped(ITestResult result) {
        log.info("On skipped Test");
        if(result.getStatus() == ITestResult.SKIP){
            extentTestThread.get().log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " - Test Case Skipped", ExtentColor.ORANGE));
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
         if(result.getStatus() == ITestResult.SUCCESS)
        {
            extentTestThread.get().assignCategory(result.getMethod().getGroups());
            extentTestThread.get().log(Status.PASS, MarkupHelper.createLabel(result.getName()+" Test Case PASSED", ExtentColor.BLUE));

        }
    }

}
