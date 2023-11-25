package org.opensourcedemo.pagesobjects.adminpages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Log4j2
public class FromulerAddingAdmin {

    WebDriver driver;

    @FindBy(css = "")


    public FromulerAddingAdmin(WebDriver paramdriver){
        driver = paramdriver;
        PageFactory.initElements(driver,this);
        log.info("Initialize Formuler Admin Page");
    }

    public
}
