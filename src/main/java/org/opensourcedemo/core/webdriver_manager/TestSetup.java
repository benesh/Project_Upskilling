package org.opensourcedemo.core.webdriver_manager;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.opensourcedemo.core.properties_manager.GlobalConfig;
import java.time.Duration;

@Log4j2
public class TestSetup {
    public static WebDriver setupWebDriver(WebDriverType paramWebdrivertype,String paramHeadless){
        WebDriver driver = new WebDriverAndWaitFactory(paramWebdrivertype).getDriver(paramHeadless);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.manage().window().maximize();
        return driver;
    }
    public static WebDriverWait setupWebDriverWait()
    private WebDriver initializeDriver(){
        WebDriver driver = new WebDriverAndWaitFactory(webDriverType).getDriver(headleass);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.manage().window().maximize();
        return driver;
    }
    private WebDriverWait initializeWait(WebDriver driver){
        /*fwait = new FluentWait(driver)
                .withTimeout(Duration.ofSeconds(GlobalConfig.GLOBALWAIT))
                .pollingEvery(Duration.ofMillis(GlobalConfig.GLOBALPOLLING));*/
         WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(GlobalConfig.GLOBALWAIT));
         return wait;

    }

}
