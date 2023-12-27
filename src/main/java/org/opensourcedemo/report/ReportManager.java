package org.opensourcedemo.report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import lombok.extern.log4j.Log4j2;
import java.io.File;

@Log4j2
public class ReportManager {
    public static ExtentReports setup(){
        ExtentReports extent = new ExtentReports();
        File filereport = new File("report.html").getAbsoluteFile();
        ExtentSparkReporter sparkreport = new ExtentSparkReporter(filereport);
        extent.attachReporter(sparkreport);
        return extent;
    }
}
