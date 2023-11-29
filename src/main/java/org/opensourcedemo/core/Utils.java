package org.opensourcedemo.core;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

@Log4j2
public class Utils {

    public static WebDriver setup(Driver typeDriver,String URL){
        WebDriver driver = new DriverFactory(typeDriver).getDriver();
        driver.get(URL);
        driver.manage().window().maximize();
        return driver;
    }

    public void quittingDrivere(WebDriver driver){
        log.info("Quitting Driver");
        driver.quit();
    }
}
