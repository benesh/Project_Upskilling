package org.opensourcedemo.pagesobjects;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.awt.*;

@Log4j2
public class AddEmployeePage {
    WebDriver driver;

    @FindBy(css="")
    WebElement inputFirstname;

    @FindBy(css="")
    WebElement inputMiddlename;

    @FindBy(css = "")
    WebElement inputLastname;

    @FindBy(css = "")
    WebElement saveButtonvalidation;

    public AddEmployeePage(WebDriver param_driver){
        driver = param_driver;
        PageFactory.initElements(driver,this);
        log.info("Initialize Add Employee Page");
    }
    public AddEmployeePage typeInputFirstName (String paramFirstName){
        inputFirstname.sendKeys(paramFirstName);
        log.info("Typing First Name");
        return this;
    }
    public AddEmployeePage typeInputLastName (String paramLastName){
        inputLastname.sendKeys(paramLastName);
        log.info("Typing First Name");
        return this;
    }
    public AddEmployeePage typeInputMiddletName (String paramMiddleName){
        inputMiddlename.sendKeys(paramMiddleName);
        log.info("Typing First Name");
        return this;
    }

    public EmployeeDatailPage clickSaveButton(){
        return new EmployeeDatailPage(driver);
    }

}
