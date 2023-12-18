package org.opensourcedemo.core.driver_manager;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class WebDriverFactory {

    private WebDriverType typeWebDriverType;
    public WebDriverFactory(WebDriverType paramWebDriverType){
        typeWebDriverType = paramWebDriverType;
    }
    public WebDriver getDriver(String headless){
        switch (typeWebDriverType){
            case WebDriverType.CHROME -> {
                return getWebeDriveChrome(headless);
            }
            case WebDriverType.FIREFOX -> {
                return new FirefoxDriver();
            }
            case WebDriverType.EDGE -> {
                return new EdgeDriver();
            }
        }
        return null;
    }
    public WebDriver getWebeDriveChrome(String headless){
        WebDriverManager.chromedriver().setup();
        if(headless.equals("YES")){
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless=new");
            return new ChromeDriver(options);
        }else{
            return new ChromeDriver();
        }
    }
    public WebDriver getWebeDriveFirefox(String headless){
        WebDriverManager.firefoxdriver().setup();
        if(headless.equals("YES")){
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--headless=new");
            return new FirefoxDriver(options);
        }else{
            return new FirefoxDriver();
        }
    }
    public WebDriver getWebeDriveEdge(String headless){
        WebDriverManager.edgedriver().setup();
        if(headless.equals("YES")){
            EdgeOptions options = new EdgeOptions();
            options.addArguments("--headless=new");
            return new EdgeDriver(options);
        }else{
            return new EdgeDriver();
        }
    }
}
