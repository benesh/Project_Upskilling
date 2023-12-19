package org.opensourcedemo.BaseTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.opensourcedemo.core.webdriver_manager.TestSetup;
import org.opensourcedemo.core.properties_manager.ConfigProperties;
import org.opensourcedemo.core.properties_manager.PropertiesFactory;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Log4j2
public class BaseTest {
    protected TestSetup testsetup;
    protected ConfigProperties configproperties;
    protected String pathconfig ;
    PropertiesFactory propfactory;
    protected ExtentReports extent;
    protected ExtentSparkReporter sparkreport ;
    protected ExtentTest logger;
    File filereport;

    public BaseTest( String param_config){
        pathconfig = param_config;
    }
    public String getScreenShot(WebDriver driver, String screenshotName) throws IOException {
        String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
// after execution, you could see a folder "FailedTestsScreenshots" under src folder
        String destination = configproperties.getPathScreenshot() + screenshotName + dateName + ".png";
        File finalDestination = new File(destination);
        FileUtils.copyFile(source, finalDestination);
        return destination;
    }
    @BeforeSuite
    public void setupAll(){
        log.info("Initialize Base Test and file: " +pathconfig);
        propfactory = new PropertiesFactory();
        configproperties = propfactory.factoryProperty(pathconfig);
        testsetup = new TestSetup();
        extent = new ExtentReports();
        filereport = new File(configproperties.getPathReport()+"report.html" );
        sparkreport = new ExtentSparkReporter(filereport);
        extent.attachReporter(sparkreport);
    }
    @BeforeMethod
    public void setup(){
        log.info("Setup before Method: "+pathconfig);
        testsetup.setup(configproperties);
    }
    @AfterMethod
    public void reportMethod(ITestResult result) throws IOException {
        if(result.getStatus() == ITestResult.FAILURE){
//MarkupHelper is used to display the output in different colors
            logger.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
            logger.log(Status.FAIL, MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));
//To capture screenshot path and store the path of the screenshot in the string "screenshotPath"
//We do pass the path captured by this method in to the extent reports using "logger.addScreenCapture" method.
//String Scrnshot=TakeScreenshot.captuerScreenshot(driver,"TestCaseFailed");
            String screenshotPath = getScreenShot(testsetup.getDriver(), result.getName());
//To add it in the extent report
            logger.fail("Test Case Failed Snapshot is below " + logger.addScreenCaptureFromPath(screenshotPath));
        }
        else if(result.getStatus() == ITestResult.SKIP){
            logger.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " - Test Case Skipped", ExtentColor.ORANGE));
        }
        else if(result.getStatus() == ITestResult.SUCCESS)
        {
            logger.log(Status.PASS, MarkupHelper.createLabel(result.getName()+" Test Case PASSED", ExtentColor.GREEN));
        }
    }
    @AfterMethod(dependsOnMethods = "reportMethod")
    public void quittingDriver(){
        log.info("Quitting Driver");
        testsetup.quitDriver();
    }

    @AfterSuite
    public void reportFlush(){
        log.info("Flushing Extent Report");
        extent.flush();
    }
}
