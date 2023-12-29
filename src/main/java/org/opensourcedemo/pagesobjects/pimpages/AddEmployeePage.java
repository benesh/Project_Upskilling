package org.opensourcedemo.pagesobjects.pimpages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.opensourcedemo.pagesobjects.BasePage;

@Log4j2
public class AddEmployeePage extends BasePage {
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
    @FindBy(css = "input[type=\"checkbox\"]~span")
    WebElement buttonswitchecreateuser;
    @FindBy(css = "div.orangehrm-full-width-grid>div:nth-child(1)>div:nth-child(1)>div>input.oxd-input[autocomplete=\"off\"]")
    WebElement inputUsername;
    @FindBy(css = "input[type=\"radio\"][value=\"1\"]~span")
    WebElement radiobuttonenable;
    @FindBy(css = "div.user-password-cell>div>div>input.oxd-input[type=\"password\"]")
    WebElement inputpassword;
    @FindBy(css = "div:nth-child(2)>div>div>input.oxd-input[type=\"password\"]")
    WebElement inputpasswordconfirmationt;
    @FindBy(css="button[type=\"submit\"]")
    WebElement bontouLogin;

    public AddEmployeePage(){
        PageFactory.initElements(getDriver(),this);
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
        invisibilityLoader();
        lamdaWaitIsDisplayed(buttonswitchecreateuser);
        clickElement(buttonswitchecreateuser);
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
        lamdaWaitIsDisplayed(radiobuttonenable);
        clickElement(radiobuttonenable);
        return this;
    }
    public EmployeeDetailsPage clickSaveButton(){
        log.info("Save Employee Info primary");
        invisibilityLoader();
        lamdaWaitIsDisplayed(buttonsavevalidation);
        clickElement(buttonsavevalidation);
        return new EmployeeDetailsPage();
    }
}
