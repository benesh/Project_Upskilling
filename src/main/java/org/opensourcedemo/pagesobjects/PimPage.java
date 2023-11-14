package org.opensourcedemo.pagesobjects;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Log4j2
public class PimPage {
    WebDriver driver;


    @FindBy(css="")
    WebElement addButton;

    public PimPage(WebDriver paramDriver){
        driver = paramDriver;
        PageFactory.initElements(driver,this);
        log.info("Initialize PimPage");
    }

    public AddEmployeePage clickAddButton(){
        addButton.click();
        log.info("Click on Add Button");
        return new AddEmployeePage(driver);
    }
}
