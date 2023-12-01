package org.opensourcedemo.core.driver_manager;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.opensourcedemo.core.properties_manager.ConfigProperties;

@Log4j2
public class Utils {

    public static WebDriver setup(ConfigProperties propconfig){
        WebDriver driver = new DriverFactory(propconfig.getBrowser()).getDriver();
        driver.get(propconfig.getUrl());
        driver.manage().window().maximize();
        return driver;
    }

    public static void quittingDrivere(WebDriver driver){
        log.info("Quitting Driver");
        driver.quit();
    }
}
