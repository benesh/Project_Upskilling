package org.opensourcedemo.pagesobjects.adminpages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.opensourcedemo.pagesobjects.BasePage;
import java.util.List;

@Log4j2
public class FormAddAdminUser extends BasePage {
    @FindBy(css = "div:nth-child(1)>div>div>div>div>div>i.oxd-select-text--arrow")
    WebElement inptuSetRoleElement;
    @FindBy(css =".oxd-autocomplete-text-input>input" )
    WebElement inputemployeenameelement;
    @FindBy(css = "div:nth-child(3)>div>div>div>div>div>i.oxd-select-text--arrow")
    WebElement inputstatuselement;
    @FindBy(css = "div:nth-child(1)>div>div>div>div>input.oxd-input")
    WebElement inputSserNameAdminElement;
    @FindBy(css = "div:nth-child(1)>div>div>input[type=\"password\"]")
    WebElement inputPasswordElement;
    @FindBy(css = "div>div:nth-child(1)>div>input[type=\"password\"]")
    WebElement inputPasswordConfirmationElement;
    @FindBy(css = ".orangehrm-left-space")
    WebElement buttonSaveElement;
    @FindBy(css = ".oxd-select-option>span")
    List<WebElement> listStatusElement;
    @FindBy(css = ".oxd-select-option>span")
    List<WebElement> listRoleElement;
    @FindBy(css = ".oxd-autocomplete-option>span")
    List<WebElement> listNameElements;
    @FindBy(css = "div.oxd-toast-icon-wrap--success")
    WebElement iconAlertSucsess;
    public FormAddAdminUser(){
        PageFactory.initElements(getDriver(),this);
        log.info("Initialize formuler admin page");
    }
    public FormAddAdminUser setUserToAdminRole(){
        log.info("set user role");
        waitOfVisibilityOf(inptuSetRoleElement);
        clickElement(inptuSetRoleElement);
        waitOfVisibilityOfListElement(listRoleElement);
        clickElement(listRoleElement.getFirst());
        return this;
    }
    public FormAddAdminUser setSatusUserAccountToEnable(){
        waitOfVisibilityOf(inputstatuselement);
        clickElement(inputstatuselement);
        waitOfVisibilityOfListElement(listStatusElement);
        clickElement(listStatusElement.getFirst());
        return this;
    }
    public FormAddAdminUser typeEmployeeName(String paramemployeename){
        log.info("type employee Name");
        waitOfVisibilityOf(inputemployeenameelement);
        String firstname = paramemployeename.substring(0,paramemployeename.indexOf(" "));
        inputemployeenameelement.sendKeys(firstname);
        waitOfVisibilityOfListElement(listNameElements);
        WebElement optiontoselectelement = listNameElements.stream().filter(x -> x.getText().equals(paramemployeename)).findAny().get();
        clickElement(optiontoselectelement);
        return this;
    }
    public FormAddAdminUser inputUserAdminName(String paramusername){
        log.info("Input Username Admin");
        waitOfVisibilityOf(inputSserNameAdminElement);
        typeKeys(inputSserNameAdminElement,paramusername);
        return this;
    }
    public FormAddAdminUser inputPassword(String parampassword){
        waitOfVisibilityOf(inputPasswordElement);
        typeKeys(inputPasswordElement,parampassword);
        log.info("Input Password");
        return this;
    }
    public FormAddAdminUser inputPassworConfirmation(String parampasswor){
        waitOfVisibilityOf(inputPasswordConfirmationElement);
        typeKeys(inputPasswordConfirmationElement,parampasswor);
        log.info("Input Password Confirmation");
        return this;
    }
    public FormAddAdminUser buttonSaveAdmin(){
        log.info("Submit Admin");
        waitOfVisibilityOf(buttonSaveElement);
        clickElement(buttonSaveElement);
        return this;
    }
    public AdminPage handlerSuccessAlert(){
        log.info("Verification de l'alerte succès");
        waitOfVisibilityOf(iconAlertSucsess);
        log.info("Success of uploading");
        return new AdminPage();
    }
}
