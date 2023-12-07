package org.opensourcedemo.BaseTest;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.opensourcedemo.core.driver_manager.TestSetup;
import org.opensourcedemo.core.properties_manager.ConfigProperties;
import org.opensourcedemo.core.properties_manager.PropertiesFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.IOException;

@Log4j2
public class BaseTest {
    protected TestSetup testsetup;
    protected ConfigProperties configproperties;
//    protected String pathconfig = "src/main/resources/entry_data/scenario1/config.properties";
    PropertiesFactory propfactory;
    @Parameters({"pathconfigall"})
    @BeforeSuite
    public void setupAll(String pathconfig){
        log.info("Initialize Base Test and file: " +pathconfig);
        propfactory = new PropertiesFactory();
        configproperties = propfactory.factoryProperty(pathconfig);
        testsetup = new TestSetup();
    }
    @Parameters({"pathconfig"})
    @BeforeMethod
    public void setup(String pathloog){
        log.info("Setup before Method: "+pathloog);
        testsetup.setup(configproperties);
    }
    @AfterMethod
    public void resulscreenshot(ITestResult result){
//        if (result.getStatus() == ITestResult.FAILURE){
        File scrFile =  ((TakesScreenshot)testsetup.getDriver()).getScreenshotAs(OutputType.FILE);
        String name = "screenshot.png";
        try {
            FileUtils.copyFile(scrFile,new File("test_output/screenshots/" + name));
        } catch (IOException e) {
            log.error(e);
            throw new RuntimeException(e);
        }
//        }
//        log.info("Quitting Driver");
        testsetup.quitDriver();
    }
 /*   @AfterMethod
    public void quittingDriver(){
        testsetup.quitDriver();
    }*/
}
