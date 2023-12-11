package org.opensourcedemo.pagesobjects.adminpages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.opensourcedemo.core.driver_manager.TestSetup;
import org.opensourcedemo.pagesobjects.PageObjectParent;
import java.util.List;

@Log4j2
public class FormAddAdminUser extends PageObjectParent {
    @FindBy(css = "div:nth-child(1)>div>div>div>div>div>i.oxd-select-text--arrow")
    WebElement inptuserroleelement;
    @FindBy(css =".oxd-autocomplete-text-input>input" )
    WebElement inputemployeenameelement;
    @FindBy(css = "div:nth-child(3)>div>div>div>div>div>i.oxd-select-text--arrow")
    WebElement inputstatuselement;
    @FindBy(css = "div:nth-child(1)>div>div>div>div>input.oxd-input")
    WebElement inputusernameadminelement;
    @FindBy(css = "div:nth-child(1)>div>div>input[type=\"password\"]")
    WebElement inputpasswordelement;
    @FindBy(css = "div>div:nth-child(1)>div>input[type=\"password\"]")
    WebElement inputpasswordfconfirmationelement;
    @FindBy(css = ".orangehrm-left-space")
    WebElement buttonsaveelement;
    @FindBy(css = ".oxd-select-option")
    List<WebElement> liststatuselement;
    @FindBy(css = ".oxd-select-option>span")
    List<WebElement> listroleelement;
    public FormAddAdminUser(TestSetup paramtestsetup){
        super(paramtestsetup);
        PageFactory.initElements(testsetup.getDriver(),this);
        log.info("Initialize formuler admin page");
    }
    public List<WebElement> getElementfind(String cssSelector){
        return testsetup.getDriver().findElements(By.cssSelector( cssSelector));
    }
    public FormAddAdminUser setUserToAdminRole(){
        log.info("set user role");
        testsetup.getWait() .until(ExpectedConditions.visibilityOf(inptuserroleelement));
        inptuserroleelement.click();
        List<WebElement> listOption = getElementfind(".oxd-select-option");
        WebElement optiontoselectelement = listOption.get(1) ;
        testsetup.getWait().until(ExpectedConditions.visibilityOf(optiontoselectelement));
        optiontoselectelement.click();
        return this;
    }
    public FormAddAdminUser setSatusUserAccountToEnable(){
        testsetup.getWait().until(ExpectedConditions.visibilityOf(inputstatuselement));
        inputstatuselement.click();
        List<WebElement> listOption = getElementfind(".oxd-select-option>span");
        WebElement optiontoselectelement = listOption.getFirst();
        testsetup.getWait().until(ExpectedConditions.visibilityOf(optiontoselectelement));
        optiontoselectelement.click();
        return this;
    }
    public FormAddAdminUser typeEmployeeName(String paramemployeename){
        log.info("type employee Name");
        testsetup.getWait().until(ExpectedConditions.visibilityOf(inputemployeenameelement));
        String firstname = paramemployeename.substring(0,paramemployeename.indexOf(" "));
        inputemployeenameelement.sendKeys(firstname.substring(2));
        List<WebElement> listOption = getElementfind(".oxd-autocomplete-option>span");
        WebElement optiontoselectelement = listOption.stream().filter(x -> x.getText().equals(paramemployeename)).findAny().get();
        optiontoselectelement.click();
        return this;
    }
    public FormAddAdminUser inputUserNameAdmin(String paramusername){
        log.info("Input Username Admin");
        testsetup.getWait().until(ExpectedConditions.visibilityOf(inputusernameadminelement));
        inputusernameadminelement.sendKeys(paramusername);
        return this;
    }
    public FormAddAdminUser inputPassword(String parampassword){
//        testSetup.getFwait().until(ExpectedConditions.visibilityOf(inputpasswordelement));
        inputpasswordelement.sendKeys(parampassword);
        log.info("Input Password");
        return this;
    }
    public FormAddAdminUser inputPassworConfirmation(String parampasswor){
        testsetup.getWait().until(ExpectedConditions.visibilityOfAllElements(inputpasswordfconfirmationelement));
        inputpasswordfconfirmationelement.sendKeys(parampasswor);
        log.info("Input Password Confirmation");
        return this;
    }
    public AdminPage buttonSaveAdmin(){
        log.info("Submit Admin");
        testsetup.getWait().until(ExpectedConditions.visibilityOfAllElements(buttonsaveelement));
        buttonsaveelement.click();
        return new AdminPage(testsetup);
    }
}
