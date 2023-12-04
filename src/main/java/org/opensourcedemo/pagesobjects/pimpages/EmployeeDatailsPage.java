package org.opensourcedemo.pagesobjects.pimpages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.opensourcedemo.core.driver_manager.TestSetup;

@Log4j2
public class EmployeeDatailsPage {
    TestSetup testsetup;
    @FindBy(css = ".oxd-topbar-header-title")
    WebElement titleElement;
    @FindBy(css="button.oxd-button--secondary:nth-child(2)")
    WebElement savebuttonElemnt;
    @FindBy(css = "li.oxd-topbar-body-nav-tab:nth-child(2)")
    WebElement employeeListelement;
    @FindBy(css = ".oxd-button--secondary:nth-child(1)")
    WebElement savebuttonwithbloddtype;
    public EmployeeDatailsPage(TestSetup paramtestetup){
        testsetup = paramtestetup;
        PageFactory.initElements(testsetup.getDriver(),this);
        log.info("Initialize EmployeeDatail Page ");
    }
    public EmployeeDatailsPage clickSaveButton(){
        /*testsetup.getFwait().ignoring(ElementClickInterceptedException.class);
        testsetup.getFwait().until( ExpectedConditions.visibilityOf(savebuttonElemnt));
        testsetup.getFwait().until(ExpectedConditions.elementToBeClickable(savebuttonElemnt));*/
//        testsetup.getFwait().until(d -> savebuttonElemnt.isEnabled());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }
        savebuttonElemnt.click();
        log.info("Save EmployeSecondary");
        return this;
    }
    public EmployeeDatailsPage clickSaveButtonWithBloodType(){
        testsetup.getFwait().until(ExpectedConditions.visibilityOf(savebuttonwithbloddtype));
        savebuttonwithbloddtype.click();
        log.info("Save Employe");
        return this;
    }
    public PimPage clickEmployeeList(){
        testsetup.getFwait().until(ExpectedConditions.visibilityOf(employeeListelement));
        employeeListelement.click();
        log.info("Click sur Employe List ou PIM Page");
        return new PimPage(testsetup);
    }
    public String getTitle(){
        log.info("Getting Tittle");
        return titleElement.getText();
    }
}
