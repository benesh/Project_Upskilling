package org.opensourcedemo.pagesobjects.pimpages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.opensourcedemo.core.driver_manager.TestSetup;
import org.opensourcedemo.core.properties_manager.GlobalConfig;


import java.time.Duration;
import java.util.NoSuchElementException;

@Log4j2
public class AddEmployeePage {
    TestSetup testsetup;
    @FindBy(css=".orangehrm-firstname")
    WebElement inputFirstname;
    @FindBy(css=".orangehrm-middlename")
    WebElement inputMiddlename;
    @FindBy(css = ".orangehrm-lastname")
    WebElement inputLastname;
    @FindBy(css = "button.oxd-button:nth-child(3)")
    WebElement saveButtonvalidation;
    public AddEmployeePage(TestSetup param_testsetup){
        testsetup = param_testsetup;
        testsetup.getFwait().ignoring(ElementClickInterceptedException.class, java.util.NoSuchElementException.class);
        PageFactory.initElements(testsetup.getDriver(),this);
        log.info("Initialize Add Employee Page");

    }
    public AddEmployeePage inputFirstName(String paramFirstName){
        testsetup.getFwait().until(ExpectedConditions.visibilityOf(inputFirstname));
        inputFirstname.sendKeys(paramFirstName);
        log.info("Typing firstname");
        return this;
    }
    public AddEmployeePage inputLastName(String paramLastName){
        inputLastname.sendKeys(paramLastName);
        log.info("Typing lastname");
        return this;
    }
    public AddEmployeePage inputMiddletName(String paramMiddleName){
        if(paramMiddleName !=null){
            inputMiddlename.sendKeys(paramMiddleName);
            log.info("Typing middlename");
            return this;
        }else {
            log.info("middlename Null");
            return this;
        }
    }
    public EmployeeDatailsPage clickSaveButton(){
        /*testsetup.getFwait().ignoring(ElementClickInterceptedException.class);
        testsetup.getFwait().until( ExpectedConditions.visibilityOf(saveButtonvalidation));
        testsetup.getFwait().until(ExpectedConditions.elementToBeClickable(saveButtonvalidation));*/
//        testsetup.getFwait().until(d -> saveButtonvalidation.isEnabled());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }
        saveButtonvalidation.click();
        log.info("Save Employee Info primary");
        return new EmployeeDatailsPage(testsetup);
    }

}
