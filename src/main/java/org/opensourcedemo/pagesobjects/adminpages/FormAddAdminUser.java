package org.opensourcedemo.pagesobjects.adminpages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.opensourcedemo.core.properties_manager.GlobalConfig;

import java.time.Duration;
import java.util.List;

@Log4j2
public class FormAddAdminUser {
    WebDriver driver;
    WebDriverWait wait ;
    @FindBy(css = "div.oxd-form-row:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div")
    WebElement inptuserroleelement;
    @FindBy(css =".oxd-autocomplete-text-input>input" )
    WebElement inputemployeenameelement;
    @FindBy(css = "div.oxd-grid-item:nth-child(3) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1)")
    WebElement inputstatuselement;
    @FindBy(css = "div.oxd-input-group:nth-child(2) > div:nth-child(2) > input:nth-child(1)")
    WebElement inputpasswordelement; //input.oxd-input[autocomplete="off"]
    @FindBy(css = "div.oxd-form-row:nth-child(2) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(2) > input:nth-child(1)")
    WebElement inputpasswordfconfirmationelement; //input.oxd-input[autocomplete="off"]
    @FindBy(css = ".orangehrm-left-space")
    WebElement buttonsaveelement;

    public FormAddAdminUser(WebDriver paramdriver){
        driver = paramdriver;
        PageFactory.initElements(driver,this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConfig.GLOBALWAIT));
        log.info("Initialize Formuler Admin Page");
    }
    public FormAddAdminUser inputUserRole(){
        wait.until(ExpectedConditions.visibilityOfAllElements(inptuserroleelement));
        inptuserroleelement.click();
        log.info("input role");
        return selectoption( ".oxd-select-option","Admin");
    }
    public FormAddAdminUser inputSatus(){
        wait.until(ExpectedConditions.visibilityOfAllElements(inputstatuselement));
        inputstatuselement.click();
        return selectoption("oxd-select-option>span","Enable");
    }
    public FormAddAdminUser inputEmployeeName(String paramemployeename){
        wait.until(ExpectedConditions.visibilityOf(inputemployeenameelement));
        String firstname = paramemployeename.substring(0,paramemployeename.indexOf(" "));
        inputemployeenameelement.sendKeys(firstname.substring(2));
        log.info("Input Employee Name");
        return selectoption(".oxd-autocomplete-option>span",paramemployeename);
    }
    public FormAddAdminUser inputUserNameAdmin(String paramusername){
        wait.until(ExpectedConditions.visibilityOf(inptuserroleelement));
        inptuserroleelement.sendKeys(paramusername);
        return this;
    }
    public FormAddAdminUser inputPassword(String parampassword){
        wait.until(ExpectedConditions.visibilityOfAllElements(inputpasswordelement));
        inputpasswordelement.sendKeys(parampassword);
        log.info("Input Password");
        return this;
    }
    public FormAddAdminUser inputPassworConfirmation(String parampasswor){
        wait.until(ExpectedConditions.visibilityOfAllElements(inputpasswordfconfirmationelement));
        inputpasswordfconfirmationelement.sendKeys(parampasswor);
        return this;
    }
    public AdminPage buttonSaveAdmin(){
        wait.until(ExpectedConditions.visibilityOfAllElements(buttonsaveelement));
        buttonsaveelement.click();
        return new AdminPage(driver);
    }
    public FormAddAdminUser selectoption (String cssselector,String optiontoselect){
        By userroleselector = By.cssSelector(cssselector);
        List<WebElement> listuserrolestatus = driver.findElements(userroleselector);
        WebElement oneoption = driver.findElement(userroleselector);
        wait.until(ExpectedConditions.visibilityOfAllElements(listuserrolestatus));
//        wait.until(d-> listuserrolestatus.getFirst().isDisplayed());
//        wait.until(ExpectedConditions.visibilityOf(oneoption));
        for(WebElement optionelement : listuserrolestatus){
            if(optionelement.getText().equals(optiontoselect)){
                optionelement.click();
                break;
            }
        }
        /*WebElement optiontoselectelement = listuserrolestatus.stream()
                .filter( e -> e.getText().equals(optiontoselect))
                .findFirst()
                .orElse(null);
        optiontoselectelement.click();*/
        return this;
    }
}
