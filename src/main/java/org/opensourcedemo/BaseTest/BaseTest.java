package org.opensourcedemo.BaseTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.cucumber.java.pl.I;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.opensourcedemo.core.driver_manager.TestSetup;
import org.opensourcedemo.core.properties_manager.ConfigProperties;
import org.opensourcedemo.core.properties_manager.PropertiesFactory;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;

@Log4j2
public class BaseTest {
    protected TestSetup testsetup;
    protected ConfigProperties configproperties;
    protected String pathconfig ;
    PropertiesFactory propfactory;
    ExtentReports extent;
    ExtentSparkReporter sparkreport ;
    File filereport;


    public BaseTest( String param_config){
        pathconfig = param_config;
    }
    @BeforeSuite
    public void setupAll(){
        log.info("Initialize Base Test and file: " +pathconfig);
        propfactory = new PropertiesFactory();
        configproperties = propfactory.factoryProperty(pathconfig);
        testsetup = new TestSetup();
        extent = new ExtentReports();
        filereport = new File("report.html" );
        sparkreport = new ExtentSparkReporter(filereport);
        extent.attachReporter(sparkreport);
    }
    @BeforeMethod
    public void setup(){
        log.info("Setup before Method: "+pathconfig);
        testsetup.setup(configproperties);
    }

  /*  @BeforeMethod
    public void createATestCaseReport(){
    }*/

    @AfterMethod
    public void resultscreenshot(ITestResult result){
        File scrFile =  ((TakesScreenshot)testsetup.getDriver()).getScreenshotAs(OutputType.FILE);
        String name = "/screenshot.png";
        try {
            FileUtils.copyFile(scrFile,new File(configproperties.getPathScreenshot()+ result.getName() + name));
        } catch (IOException e) {
            log.error(e);
            throw new RuntimeException(e);
        }
    }
    @AfterMethod
    public void reportMethod(ITestResult result){
        ExtentTest test = extent.createTest(result.getName());
        if( result.getStatus() == ITestResult.FAILURE){
            test.log(Status.FAIL,"This Test failed");
        }else if (result.getStatus() == ITestResult.SUCCESS){
            test.log(Status.PASS,"This Test failed");
        }
    }
    @AfterMethod(dependsOnMethods = "resultscreenshot")
    public void quittingDriver(){
        log.info("Quitting Driver");
        testsetup.quitDriver();
        extent.flush();
    }
}
