package BaseTest;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ThreadGuard;
import org.opensourcedemo.core.properties_manager.ReaderPropertiesJsonFile;
import org.opensourcedemo.core.webdriver_manager.TestSetup;
import org.opensourcedemo.core.properties_manager.ConfigProperties;
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
    public Properties propertiesSuite ;
    public BaseTest(){
        log.info("initialize BaseTest Class ");
        String pathconfig ="src/main/resources/config/config.properties";
        configproperties = new ConfigProperties(ReaderPropertiesJsonFile.readPropertiesFromFile(pathconfig));
    }
    protected void initializePorpertiesSuite(Properties prop ){
        log.info("Initialize Config Suite");
         propertiesSuite = prop;
    }
    @BeforeMethod
    public void setup(){
        log.info("Setup before Method");
        driver.set(ThreadGuard.protect(TestSetup.setupWebDriver (configproperties.getBrowser(),configproperties.getHeadless())));
        getDriver().get(propertiesSuite.getProperty("URL"));
        wait.set(TestSetup.setupWebDriverWait(getDriver()));
    }
    @AfterMethod
    public void quittingDriver(){
        log.info("Quitting Driver");
        getDriver().quit();
        driver.remove();
        wait.remove();
    }
    public static String takeScreenShot(String fileName) {

        String pathreport = BaseTest.configproperties.getPathScreenshot();
        String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot ts = (TakesScreenshot) getDriver();
        File source = ts.getScreenshotAs(OutputType.FILE);
        String destination = pathreport + dateName + "-"+ fileName + ".png";
        File finalDestination = new File(destination);
        try {
            FileUtils.copyFile(source, finalDestination);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return finalDestination.getPath();
    }
}
