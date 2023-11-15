package org.opensourcedemo.core;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.managers.OperaDriverManager;
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
                return new ChromeDriver();
            }
            case Driver.FIREFOX -> {
                return new FirefoxDriver();
            }
            case Driver.EDGE -> {
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
