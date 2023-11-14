package org.opensourcedemo.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BaseDriverFirefox implements BaseDriver{
    WebDriver driver;
    public BaseDriverFirefox(){
        driver = new FirefoxDriver();
    }

    @Override
    public WebDriver getDriver(){
        return driver;
    }

}
