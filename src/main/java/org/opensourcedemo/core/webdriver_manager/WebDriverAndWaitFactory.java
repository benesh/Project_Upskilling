package org.opensourcedemo.core.webdriver_manager;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.opensourcedemo.core.properties_manager.GlobalConfig;

import java.time.Duration;


@Log4j2
public class WebDriverAndWaitFactory {

    public static WebDriver getDriver(WebDriverType typeWebDriverType,String headless){
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
    public static WebDriver getWebeDriveChrome(String headless){
        log.info("Instantiate Webdrivder Chromer");
        WebDriverManager.chromedriver().setup();
        if(headless.equals("YES")){
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless=new");
            return new ChromeDriver(options);
        }else{
            return new ChromeDriver();
        }
    }
    public static WebDriver getWebDriveFirefox(String headless){
        log.info("Instantiate Webdrivder Firefox");
        WebDriverManager.firefoxdriver().setup();
        if(headless.equals("YES")){
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--headless=new");
            return new FirefoxDriver(options);
        }else{
            return new FirefoxDriver();
        }
    }
    public static WebDriver getWebDriveEdge(String headless){
        log.info("Instantiate Webdrivder Edge");
        WebDriverManager.edgedriver().setup();
        if(headless.equals("YES")){
            EdgeOptions options = new EdgeOptions();
            options.addArguments("--headless=new");
            return new EdgeDriver(options);
        }else{
            return new EdgeDriver();
        }
    }

    public static WebDriverWait getWait(WebDriver driver){
        log.info("Instantiate Wait");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConfig.GLOBALWAIT));
        return wait;
    }
}
