package org.opensourcedemo.BaseTest;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.opensourcedemo.core.properties_manager.GlobalConfig;
import org.opensourcedemo.core.properties_manager.ReaderPropertiesFile;
import org.opensourcedemo.core.webdriver_manager.TestSetup;
import org.opensourcedemo.core.properties_manager.ConfigProperties;
import org.opensourcedemo.report.ReportManager;
import org.testng.annotations.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

@Log4j2
public class BaseTest {
    public TestSetup testsetup;
    public ConfigProperties configproperties;
    public Properties propertiesSuite ;
    public static ReportManager report ;


    public BaseTest(){
        log.info("initialize BaseTes Class ");
        configproperties = new ConfigProperties(ReaderPropertiesFile.readPropertiesFromFile(GlobalConfig.PATHCONFIG));
    }
    protected void initializeConfigSuite(Properties prop ){
        log.info("Initialize Config Suite");
         propertiesSuite = prop;
    }
    @BeforeSuite
    public void setupAll(){
        log.info("Initialize Base Test and file: ");
        testsetup = new TestSetup(configproperties.getBrowser(),configproperties.getHeadless());
    }
    @BeforeMethod
    public void setup(){
        log.info("Setup before Method");
        testsetup.setup();
        testsetup.getToUrl(propertiesSuite.getProperty("URL"));
    }
    @AfterMethod
    public void quittingDriver(){
        log.info("Quitting Driver");
        testsetup.getDriver().quit();
    }
    public String takeScreenShot() {
        String pathreport = propertiesSuite.getProperty("pathscreenshot");
        String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot ts = (TakesScreenshot) testsetup.getDriver();
        File source = ts.getScreenshotAs(OutputType.FILE);
// after execution, you could see a folder "FailedTestsScreenshots" under src folder
        String destination = pathreport + dateName + ".png";
        File finalDestination = new File(destination);
        try {
            FileUtils.copyFile(source, finalDestination);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return destination;
    }
}
