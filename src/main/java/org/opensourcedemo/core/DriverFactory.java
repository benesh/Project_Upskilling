package org.opensourcedemo.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {

    private Driver typeDriver;
    public DriverFactory(Driver paramDriver){
        typeDriver= paramDriver;
    }

    public WebDriver getDriverFactory(){
        switch (typeDriver){
            case Driver.CHROME -> {
                return new ChromeDriver();
            }
            case Driver.FIREFOX -> {
                return new FirefoxDriver();
            }
            case Driver.EDGE -> {
                return new EdgeDriver();
            }
        }
        return null;
    }

}
