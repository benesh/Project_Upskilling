package org.opensourcedemo.pagesobjects.pimpages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.opensourcedemo.core.driver_manager.TestSetup;
import org.opensourcedemo.pagesobjects.PageObjectParent;

@Log4j2
public class EmployeeDatailsPage extends PageObjectParent {
    TestSetup testsetup;
    @FindBy(css = ".oxd-topbar-header-title")
    WebElement titleElement;
//    @FindBy(css="button.oxd-button--secondary:nth-child(2)")
    @FindBy(xpath = "/html/body/div/div[1]/div[2]/div[2]/div/div/div/div[2]/div[1]/form/div[5]/button")
    WebElement savebuttonElemnt;
    @FindBy(css = "li.oxd-topbar-body-nav-tab:nth-child(2)")
    WebElement employeeListelement;
    @FindBy(css = ".oxd-button--secondary:nth-child(1)")
    WebElement buttonsavewithbloddtype;
    public EmployeeDatailsPage(TestSetup paramtestetup){
        super(paramtestetup);
        PageFactory.initElements(testsetup.getDriver(),this);
        log.info("Initialize Employee Datail Page ");
    }
    public EmployeeDatailsPage clickSaveButton(){
        log.info("Save EmployeSecondary");
        clickwithWait(savebuttonElemnt);
        return this;
    }
    public EmployeeDatailsPage clickSaveButtonWithBloodType(){
        log.info("Save Employe");
        clickwithWait(buttonsavewithbloddtype);
        return this;
    }
    public PimPage clickEmployeeList(){
        log.info("Click sur Employe List ou PIM Page");
        clickwithWait(employeeListelement);
        return new PimPage(testsetup);
    }
    public String getTitle(){
        log.info("Getting Tittle");
        return titleElement.getText();
    }
}
