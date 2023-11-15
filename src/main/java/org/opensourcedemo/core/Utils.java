package org.opensourcedemo.core;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;

import java.net.URL;

@Log4j2
public class Utils {
    final static String URL="https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";

    public static WebDriver setup(Driver typeDriver){
        WebDriver driver = new DriverFactory(Driver.FIREFOX).getDriver();
        driver.get(URL);
        driver.manage().window().maximize();
        return driver;
    }
    public void quittingDrivere(WebDriver driver){
        log.info("Quitting Driver");
        driver.quit();
    }
}
