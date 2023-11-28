package org.opensourcedemo.pagesobjects.pimpages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.opensourcedemo.core.GlobalConfig;


import java.time.Duration;

@Log4j2
public class AddEmployeePage {
    WebDriver driver;
    WebDriverWait wait ;
    @FindBy(css=".orangehrm-firstname")
    WebElement inputFirstname;
    @FindBy(css=".orangehrm-middlename")
    WebElement inputMiddlename;
    @FindBy(css = ".orangehrm-lastname")
    WebElement inputLastname;
    @FindBy(css = ".oxd-button--secondary")
    WebElement saveButtonvalidation;

    public AddEmployeePage(WebDriver param_driver){
        driver = param_driver;
        PageFactory.initElements(driver,this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConfig.GLOBALWAIT));
        log.info("Initialize Add Employee Page");
    }
    public AddEmployeePage typeInputFirstName (String paramFirstName){
        wait.until(ExpectedConditions.visibilityOfAllElements(inputFirstname));
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
