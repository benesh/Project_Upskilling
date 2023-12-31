package org.opensourcedemo.core.webdriver_manager;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.opensourcedemo.core.properties_manager.GlobalConfig;

import java.net.MalformedURLException;
import java.net.URL;


@Log4j2
public class WebDriverFactory {

    public static WebDriver getDriver(WebDriverType typeWebDriverType,String headless) throws MalformedURLException {
        switch (typeWebDriverType){
            case WebDriverType.CHROME -> {
                return getWebeDriveChrome(headless);
            }
            case WebDriverType.FIREFOX -> {
                return getWebDriveFirefox(headless);
            }
            case WebDriverType.EDGE -> {
                return getWebDriveEdge(headless);
            }
            default -> {
                throw new RuntimeException("Unexpected Driver type ");
            }
        }
    }
    private static WebDriver getWebeDriveChrome(String headless) throws MalformedURLException {
        log.info("Instantiate Webdrivder Chromer");
        ChromeOptions options = new ChromeOptions();
        if(headless.equals("YES")){
            options.addArguments("--headless=new");
        }
        if(GlobalConfig.ENV.equals("REMOTE")){
            options.setCapability("browserName", "chrome");
            return new RemoteWebDriver(new URL(GlobalConfig.HUB_URL),options);
        }else{
            return new ChromeDriver(options);
        }
    }
    private static WebDriver getWebDriveFirefox(String headless) throws MalformedURLException {
        log.info("Instantiate Webdrivder Firefox");
        FirefoxOptions options = new FirefoxOptions();
        if(headless.equals("YES")){
            options.addArguments("--headless");
        }
        if(GlobalConfig.ENV.equals("REMOTE")){
            options.setCapability("browserName", "firefox");
            return new RemoteWebDriver(new URL(GlobalConfig.HUB_URL),options);
        }else{
            return new FirefoxDriver(options);
        }    }
    private static WebDriver getWebDriveEdge(String headless) throws MalformedURLException {
        log.info("Instantiate Webdrivder Edge");
        EdgeOptions options = new EdgeOptions();
        if(headless.equals("YES")){
            options.addArguments("--headless=new");
        }
        if(GlobalConfig.ENV.equals("REMOTE")){
            options.setCapability("browserName", "edge");
            return new RemoteWebDriver(new URL(GlobalConfig.HUB_URL),options);
        }else{
            return new EdgeDriver(options);
        }
    }
}
