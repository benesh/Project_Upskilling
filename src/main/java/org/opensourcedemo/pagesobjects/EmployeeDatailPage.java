package org.opensourcedemo.pagesobjects;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Log4j2
public class EmployeeDatailPage {
    WebDriver driver ;

    @FindBy(css = ".oxd-topbar-header-title")
    WebElement titleElement;


    public EmployeeDatailPage(WebDriver paramdriver){
        driver = paramdriver;
        PageFactory.initElements(driver,this);
        log.info("Initialize EmployeeDatail Page ");
    }

    public String getTitle(){
        log.info("Getting Tittle");
        return titleElement.getText();
    }
}
