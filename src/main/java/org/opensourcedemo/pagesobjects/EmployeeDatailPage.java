package org.opensourcedemo.pagesobjects;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

@Log4j2
public class EmployeeDatailPage {
    WebDriver driver ;


    public EmployeeDatailPage(WebDriver paramdriver){
        driver = paramdriver;
        PageFactory.initElements(driver,this);
        log.info("Initialize EmployeeDatail Page ");
    }
}
