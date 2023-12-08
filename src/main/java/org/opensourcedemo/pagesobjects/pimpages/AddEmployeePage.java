package org.opensourcedemo.pagesobjects.pimpages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.opensourcedemo.core.driver_manager.TestSetup;
import org.opensourcedemo.pagesobjects.PageObjectParent;

@Log4j2
public class AddEmployeePage extends PageObjectParent {
    @FindBy(css=".orangehrm-firstname")
    WebElement inputFirstname;
    @FindBy(css=".orangehrm-middlename")
    WebElement inputMiddlename;
    @FindBy(css = ".orangehrm-lastname")
    WebElement inputLastname;
    @FindBy(css = "button.oxd-button:nth-child(3)")
    WebElement buttonsavevalidation;

    @FindBy(css = "form.oxd-form")
    WebElement formelement;
    @FindBy(css = "span.oxd-switch-input")
    WebElement buttonswitchecreateuser;
    @FindBy(css = "div.oxd-form-row:nth-child(4) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > input:nth-child(1)")
    WebElement inputUsername;
    @FindBy(css = ".--status-grouped-field > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > label:nth-child(1) > input:nth-child(1)")
    WebElement radiobuttonstatus;
    @FindBy(css = ".user-password-cell > div:nth-child(2) > div:nth-child(2) > input:nth-child(1)")
    WebElement inputpassword;
    @FindBy(css = "div.oxd-form-row:nth-child(5) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(2) > input:nth-child(1)")
    WebElement inputpasswordconfirmationt;

    public AddEmployeePage(TestSetup param_testsetup){
        super(param_testsetup);
        testsetup.getWait().ignoring(ElementClickInterceptedException.class, java.util.NoSuchElementException.class);
        PageFactory.initElements(testsetup.getDriver(),this);
//        PageFactory.initElements(new AjaxElementLocatorFactory(testsetup.getDriver(), 10), this);
        log.info("Initialize Add Employee Page");
    }
    public AddEmployeePage typeFirstName(String paramFirstName){
        log.info("Typing firstname");
        typeKeys(inputFirstname,paramFirstName);
        return this;
    }
    public AddEmployeePage typeLastName(String paramLastName){
        log.info("Typing lastname");
        typeKeys(inputLastname,paramLastName );
        inputLastname.sendKeys(paramLastName);
        return this;
    }
    public AddEmployeePage typeMiddletName(String paramMiddleName){
        if(paramMiddleName !=null){
            log.info("Typing middlename");
            typeKeys(inputMiddlename,paramMiddleName);
            return this;
        }else {
            log.info("middlename Null");
            return this;
        }
    }
    public AddEmployeePage clickswitchCreateLoginDetails(){
        log.info("Click switch button");
        testsetup.getWait().until(b -> buttonswitchecreateuser.isEnabled());
        buttonswitchecreateuser.click();
        return this;
    }
    public AddEmployeePage typeUsername(String parmausername){
        log.info("Type username");
        typeKeys(inputUsername,parmausername);
        return this;
    } public AddEmployeePage typePassword(String parmausername){
        log.info("Type username ");
        typeKeys(inputpassword,parmausername);
        return this;
    } public AddEmployeePage typePasswordConfirmation(String parmausername){
        log.info("Type username ");
        typeKeys(inputpasswordconfirmationt,parmausername);
        return this;
    }
    public AddEmployeePage clickRadioButtonStatus(){
        log.info("Click on radio button to enable the Account");
        clickwithWait(radiobuttonstatus);
        return this;
    }

    public EmployeeDatailsPage clickSaveButton(){
        log.info("Save Employee Info primary");
        clickwithWait(buttonsavevalidation);
        return new EmployeeDatailsPage(testsetup);
    }


}
