package org.opensourcedemo.core.driver_manager;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {

    private DriverType typeDriverType;
    public DriverFactory(DriverType paramDriverType){
        typeDriverType = paramDriverType;
    }
    public WebDriver getDriver(){
        switch (typeDriverType){
            case DriverType.CHROME -> {
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver();
            }
            case DriverType.FIREFOX -> {
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver();
            }
            case DriverType.EDGE -> {
                WebDriverManager.edgedriver().setup();
                return new EdgeDriver();
            }
            /*case DriverType.OPERA -> {
                WebDriverManager.operadriver().setup();
                return new
            }*/
        }
        return null;
    }

}
