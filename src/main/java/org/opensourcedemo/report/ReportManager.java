package org.opensourcedemo.report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.reporter.configuration.ViewName;
import lombok.extern.log4j.Log4j2;
import org.opensourcedemo.core.properties_manager.GlobalConfig;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

@Log4j2
public class ReportManager {
    public static ExtentReports setup(){
        ExtentReports extent = new ExtentReports();
        String dateName = GlobalConfig.getDateForName();
        File filereport = new File(  GlobalConfig.PATHREPORT + "report-extent.html").getAbsoluteFile();
        ExtentSparkReporter sparkreport = new ExtentSparkReporter(filereport);
        extent.setSystemInfo("Host Name", GlobalConfig.HOSTNAME);
        extent.setSystemInfo("Environment", GlobalConfig.ENV.name());
        extent.setSystemInfo("User Name", GlobalConfig.USER);
        extent.setSystemInfo("Software Test", GlobalConfig.SOFTWARETEST);
        sparkreport.config().setDocumentTitle(GlobalConfig.DOCUMENTTITLE);
        // Name of the report
        sparkreport.config().setReportName(GlobalConfig.REPORTNAME);
        // Dark Theme
        sparkreport.config().setTheme(Theme.STANDARD);
        extent.attachReporter(sparkreport);
        return extent;
    }
}
