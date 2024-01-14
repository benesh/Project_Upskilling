package org.opensourcedemo.core.webdriver_manager;

import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.opensourcedemo.core.properties_manager.GlobalConfig;

import java.net.MalformedURLException;
import java.time.Duration;

/*
* Class for doing all the setup for ClassTest or suiteTest in a class test
* Setup all the element (Webdriver, WaitWebDriver , .... )for an instannce
* Param :
* * Enum : WebDriverType {FIREFOX,CHROME,EDGE}
* * Enum Headless  {YES , NO}
*
 */

@Log4j2
public class TestSetup {
    public static WebDriver setupWebDriver(WebDriverType paramWebdrivertype,Headless paramHeadless){
        WebDriver driver = null;
        try {
            driver = WebDriverFactory.getDriver(paramWebdrivertype, paramHeadless);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(GlobalConfig.IMPLICITWAIT));
        driver.manage().window().maximize();
        return driver;
    }
    public static WebDriverWait setupWebDriverWait(WebDriver driver){
        return new WebDriverWait(driver,Duration.ofSeconds(GlobalConfig.EXPLICITWAIT));
    }

}
