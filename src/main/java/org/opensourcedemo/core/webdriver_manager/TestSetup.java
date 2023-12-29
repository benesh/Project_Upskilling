package org.opensourcedemo.core.webdriver_manager;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.opensourcedemo.core.properties_manager.GlobalConfig;
import java.time.Duration;

@Log4j2
public class TestSetup {
    public static WebDriver setupWebDriver(WebDriverType paramWebdrivertype,String paramHeadless){
        WebDriver driver = WebDriverFactory.getDriver(paramWebdrivertype, paramHeadless);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(GlobalConfig.IMPLICITWAIT));
        driver.manage().window().maximize();
        return driver;
    }
    public static WebDriverWait setupWebDriverWait(WebDriver driver){
        return new WebDriverWait(driver,Duration.ofSeconds(GlobalConfig.EXPLICITWAIT));
    }
}
