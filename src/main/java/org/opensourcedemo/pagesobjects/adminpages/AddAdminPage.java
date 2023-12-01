package org.opensourcedemo.pagesobjects.adminpages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.opensourcedemo.core.properties_manager.GlobalConfig;

import java.time.Duration;

@Log4j2
public class AddAdminPage {

    WebDriver driver;
    WebDriverWait wait ;

    @FindBy(css = "")
    WebElement inputusername;
    @FindBy(css = "")
    WebElement inputpassword;

    public AddAdminPage(WebDriver param_drive){
        driver = param_drive;
        PageFactory.initElements(driver,this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConfig.GLOBALWAIT));
        log.info("Initialize Page Add Admin Page");
    }
}
