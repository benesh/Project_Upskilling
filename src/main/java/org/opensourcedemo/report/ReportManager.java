package org.opensourcedemo.report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import lombok.extern.log4j.Log4j2;
import org.opensourcedemo.BaseTest.BaseTest;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
@Log4j2
public class ReportManager {
    protected static ExtentReports extent;
    protected static ExtentSparkReporter sparkreport ;
    protected static ExtentTest test;
    public static String reportDirectory;
    public static void setup(){
        extent = new ExtentReports();
        File filereport = new File("report.html").getAbsoluteFile();
        sparkreport = new ExtentSparkReporter(filereport);
        extent.attachReporter(sparkreport);
    }
    public static void reportFlush(){
        log.info("Flushing Extent Report");
        extent.flush();
    }


}
