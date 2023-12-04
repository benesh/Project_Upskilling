package org.opensourcedemo.pagesobjects.adminpages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.opensourcedemo.core.driver_manager.TestSetup;
import org.opensourcedemo.core.properties_manager.GlobalConfig;

import java.time.Duration;
import java.util.List;

@Log4j2
public class FormAddAdminUser {
    TestSetup testSetup;
    @FindBy(css = "div.oxd-form-row:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div")
    WebElement inptuserroleelement;
    @FindBy(css =".oxd-autocomplete-text-input>input" )
    WebElement inputemployeenameelement;
    @FindBy(css = "div.oxd-grid-item:nth-child(3) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1)")
    WebElement inputstatuselement;
    @FindBy(css = "div.oxd-grid-item:nth-child(4) > div:nth-child(1) > div:nth-child(2) > input:nth-child(1)")
    WebElement inputusernameadminelement;
    @FindBy(css = ".user-password-cell > div:nth-child(1) > div:nth-child(2) > input:nth-child(1)")
    WebElement inputpasswordelement; //input.oxd-input[autocomplete="off"]
    @FindBy(css = "div.oxd-form-row:nth-child(2) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(2) > input:nth-child(1)")
    WebElement inputpasswordfconfirmationelement; //input.oxd-input[autocomplete="off"]
    @FindBy(css = ".orangehrm-left-space")
    WebElement buttonsaveelement;
    public FormAddAdminUser(TestSetup paramtestsetup){
        testSetup = paramtestsetup;
        PageFactory.initElements(testSetup.getDriver(),this);
        log.info("Initialize Formuler Admin Page");
    }
    public FormAddAdminUser inputUserRole(){
        testSetup.getFwait() .until(ExpectedConditions.visibilityOf(inptuserroleelement));
        inptuserroleelement.click();
        log.info("input User role");
        return selectoption( ".oxd-select-option","Administrador");
    }
    public FormAddAdminUser inputSatus(){
        testSetup.getFwait().until(ExpectedConditions.visibilityOf(inputstatuselement));
        inputstatuselement.click();
        return selectoption(".oxd-select-option>span","Habilitado");
    }
    public FormAddAdminUser inputEmployeeName(String paramemployeename){
        testSetup.getFwait().until(ExpectedConditions.visibilityOf(inputemployeenameelement));
        String firstname = paramemployeename.substring(0,paramemployeename.indexOf(" "));
        inputemployeenameelement.sendKeys(firstname.substring(2));
        log.info("Input Employee Name");
        return selectoption(".oxd-autocomplete-option>span",paramemployeename);
    }
    public FormAddAdminUser inputUserNameAdmin(String paramusername){
        testSetup.getFwait().until(ExpectedConditions.visibilityOf(inputusernameadminelement));
        inputusernameadminelement.sendKeys(paramusername);
        log.info("Input Username Admin");
        return this;
    }
    public FormAddAdminUser inputPassword(String parampassword){
//        testSetup.getFwait().until(ExpectedConditions.visibilityOfAllElements(inputpasswordelement));
        inputpasswordelement.sendKeys(parampassword);
        log.info("Input Password");
        return this;
    }
    public FormAddAdminUser inputPassworConfirmation(String parampasswor){
        testSetup.getFwait().until(ExpectedConditions.visibilityOfAllElements(inputpasswordfconfirmationelement));
        inputpasswordfconfirmationelement.sendKeys(parampasswor);
        log.info("Input Password Confirmation");

        return this;
    }
    public AdminPage buttonSaveAdmin(){
        testSetup.getFwait().until(ExpectedConditions.visibilityOfAllElements(buttonsaveelement));
        buttonsaveelement.click();
        log.info("Submit Admin");
        return new AdminPage(testSetup);
    }
    public FormAddAdminUser selectoption (String cssselector,String optiontoselect){
        By userroleselector = By.cssSelector(cssselector);
        List<WebElement> listuserrolestatus = testSetup.getDriver().findElements(userroleselector);
        WebElement oneoption = testSetup.getDriver().findElement(userroleselector);
        testSetup.getFwait().until(ExpectedConditions.visibilityOfAllElements(listuserrolestatus));
//        wait.until(d-> listuserrolestatus.getFirst().isDisplayed());
//        wait.until(ExpectedConditions.visibilityOf(oneoption));
        for(WebElement optionelement : listuserrolestatus){
            if(optionelement.getText().equals(optiontoselect)){
                optionelement.click();
                break;
            }
        }
/*        WebElement optiontoselectelement = listuserrolestatus.stream()
                .filter( e -> e.getText().equals(optiontoselect))
                .findFirst()
                .findFirst();*//*
        optiontoselectelement.click();*/
        return this;
    }
}
