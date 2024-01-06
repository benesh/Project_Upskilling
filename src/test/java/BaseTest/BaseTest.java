package BaseTest;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ThreadGuard;
import org.opensourcedemo.core.properties_manager.GlobalConfig;
import org.opensourcedemo.core.properties_manager.ReaderPropertiesJsonFile;
import org.opensourcedemo.core.webdriver_manager.TestSetup;
import org.opensourcedemo.core.properties_manager.ConfigProperties;
import org.opensourcedemo.core.webdriver_manager.WebDriverType;
import org.opensourcedemo.pagesobjects.BasePage;
import org.testng.annotations.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

@Log4j2
public class BaseTest extends BasePage {
    public static ConfigProperties configproperties;
//    public ThreadLocal<Properties> propertiesSuite = new ThreadLocal<>();
    protected String webDriverType;
    Properties propertiesSuite;

    public BaseTest(){
        log.info("initialize BaseTest Class ");
    }
    protected synchronized void setPorpertiesSuite(Properties prop ){
        log.info("Initialize Config Suite");
//         propertiesSuite.set(prop);
        propertiesSuite=prop;
    }
    public WebDriverType getWebDriverType(){
        return WebDriverType.valueOf(webDriverType);
    }

    public Properties getPropertiesSuite(){
        log.info("Get Properties Suite");
//        return propertiesSuite.get();
        return propertiesSuite;
    }
    @Parameters({"config"})
    @BeforeSuite
    public void setupConfig(String pathConfig){
        configproperties = new ConfigProperties(ReaderPropertiesJsonFile.readPropertiesFromFile(pathConfig));
    }
    @BeforeMethod
    public void setup(){
        log.info("Setup before Method");
        if(webDriverType!= null){
            setDriver(ThreadGuard.protect(TestSetup.setupWebDriver (WebDriverType.valueOf(webDriverType) ,configproperties.getHeadless())));

        }else {
            setDriver(ThreadGuard.protect(TestSetup.setupWebDriver (configproperties.getBrowser(),configproperties.getHeadless())));
        }
        getDriver().get(getPropertiesSuite().getProperty("URL"));
        setWait(TestSetup.setupWebDriverWait(getDriver()));
    }
    @AfterMethod
    public void quittingDriver(){
        log.info("Quitting Driver");
        getDriver().quit();
        removeThreadDriver();
        removeWait();
    }
    public static String takeScreenShot(String testName) {
        String pathreport = GlobalConfig.PATHREPORT + GlobalConfig.PATHSCREENCHSOT;
        String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        String finaleFileName = dateName + "-"+ testName + ".png";
        TakesScreenshot ts = (TakesScreenshot) getDriver();
        File source = ts.getScreenshotAs(OutputType.FILE);
        String destination = pathreport + finaleFileName;
        File finalDestination = new File(destination);
        try {
            FileUtils.copyFile(source, finalDestination);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return finaleFileName;
    }
}
