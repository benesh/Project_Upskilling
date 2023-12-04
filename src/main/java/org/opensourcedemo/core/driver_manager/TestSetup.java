package org.opensourcedemo.core.driver_manager;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.opensourcedemo.core.properties_manager.ConfigProperties;
import org.opensourcedemo.core.properties_manager.GlobalConfig;

import java.time.Duration;

@Log4j2
public class TestSetup {
    @Getter
    WebDriver driver;
    @Getter
    WebDriverWait fwait;
    public void setup(ConfigProperties propconfig){
        initializeDriver(propconfig);
        initializeWait();
    }
    public void quitDriver(){
        log.info("Quitting Driver");
        driver.quit();
    }
    private void initializeDriver(ConfigProperties propconfig){
        driver = new DriverFactory(propconfig.getBrowser()).getDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.get(propconfig.getUrl());
        driver.manage().window().maximize();
    }
    private void initializeWait(){
        /*fwait = new FluentWait(driver)
                .withTimeout(Duration.ofSeconds(GlobalConfig.GLOBALWAIT))
                .pollingEvery(Duration.ofMillis(GlobalConfig.GLOBALPOLLING));*/
        fwait = new WebDriverWait(driver,Duration.ofSeconds(GlobalConfig.GLOBALWAIT));

    }

}
