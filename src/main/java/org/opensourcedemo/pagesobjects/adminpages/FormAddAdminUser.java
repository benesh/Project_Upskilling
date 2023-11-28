package org.opensourcedemo.pagesobjects.adminpages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.opensourcedemo.core.GlobalConfig;

import java.time.Duration;
import java.util.List;

@Log4j2
public class FormAddAdminUser {
    WebDriver driver;
    WebDriverWait wait ;
    @FindBy(css = "div.oxd-form-row:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div")
    WebElement inptuserroleelement;
    @FindBy(css =".oxd-autocomplete-text-input" )
    WebElement inputemployeenameelement;
    @FindBy(css = "div.oxd-grid-item:nth-child(3) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1)")
    WebElement inputstatuselement;
    @FindBy(css = "div.oxd-input-group:nth-child(2) > div:nth-child(2) > input:nth-child(1)")
    WebElement inputpasswordelement;
    @FindBy(css = "div.oxd-form-row:nth-child(2) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(2) > input:nth-child(1)")
    WebElement inputpasswordfconfirmationelement;
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
        return selectoption("oxd-select-option","Enable");
    }
    public FormAddAdminUser inputEmployeeName(String paramemployeename){
        wait.until(ExpectedConditions.visibilityOfAllElements(inputemployeenameelement));
        String firstname = paramemployeename.substring(0,1);//paramemployeename.indexOf(" "));
        inputemployeenameelement.sendKeys(firstname);
        log.info("Input Emplyee Name");
        return selectoption(".oxd-autocomplete-option",paramemployeename);
    }
    public FormAddAdminUser inputUserNameAdmin(String paramusername){
        wait.until(ExpectedConditions.visibilityOfAllElements(inptuserroleelement));
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
        List<WebElement> listuserrolestatus = driver.findElements( userroleselector);
        wait.until(ExpectedConditions.visibilityOfAllElements(listuserrolestatus));
        WebElement optiontoselectelement = listuserrolestatus.stream()
                .filter( e -> e.getText().equals(optiontoselect))
                .findFirst()
                .orElse(null);
        optiontoselectelement.click();
        return this;
    }
}
