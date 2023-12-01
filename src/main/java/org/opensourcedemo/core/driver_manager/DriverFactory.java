package org.opensourcedemo.core.driver_manager;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {

    private Driver typeDriver;
    public DriverFactory(Driver paramDriver){
        typeDriver= paramDriver;
    }
    public WebDriver getDriver(){
        switch (typeDriver){
            case Driver.CHROME -> {
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver();
            }
            case Driver.FIREFOX -> {
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver();
            }
            case Driver.EDGE -> {
                WebDriverManager.edgedriver().setup();
                return new EdgeDriver();
            }
            /*case Driver.OPERA -> {
                WebDriverManager.operadriver().setup();
                return new Webdriver.OperaDriver();
            }*/
        }
        return null;
    }

}
