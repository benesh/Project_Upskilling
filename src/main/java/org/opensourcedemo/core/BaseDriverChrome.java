package org.opensourcedemo.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BaseDriverChrome implements BaseDriver{
    WebDriver driver;
    public BaseDriverChrome(){
        driver = new ChromeDriver();
    }

    public WebDriver getDriver(){
        return driver;
    }

}
