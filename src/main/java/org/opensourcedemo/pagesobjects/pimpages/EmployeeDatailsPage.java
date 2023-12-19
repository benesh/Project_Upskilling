package org.opensourcedemo.pagesobjects.pimpages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.opensourcedemo.core.webdriver_manager.TestSetup;
import org.opensourcedemo.pagesobjects.LoginPage;
import org.opensourcedemo.pagesobjects.PageObjectParent;

import java.io.File;
import java.util.List;

@Log4j2
public class EmployeeDatailsPage extends PageObjectParent {
    @FindBy(css = ".oxd-topbar-header-title")
    WebElement titleElement;
    @FindBy(css = "p~button.orangehrm-left-space")
    WebElement savebuttonElemnt;
    @FindBy(css = "li.oxd-topbar-body-nav-tab:nth-child(2)")
    WebElement employeeListelement;
    @FindBy(css = ".oxd-button--secondary:nth-child(1)")
    WebElement buttonsavewithbloddtype;
    @FindBy(css = "a[href*='auth/logout'].oxd-userdropdown-link ")
    WebElement logoutElement;
    @FindBy(css = ".oxd-userdropdown-icon")
    WebElement profilelement;
    @FindBy(css = "div:nth-child(1)>div>div>div>div>input[placeholder=\"yyyy-mm-dd\"]")
    WebElement inputbirthdate;
    @FindBy(css = "input[type=\"radio\"][value=\"1\"]~span")
    WebElement radiobuttonmale;
    @FindBy(css = "input[type=\"radio\"][value=\"1\"]")
    WebElement radiobuttonmaletogetvalue;
    @FindBy(css = "input[type=\"radio\"][value=\"2\"]~span")
    WebElement radiobuttonfemale;
    @FindBy(css = "input[type=\"radio\"][value=\"2\"]")
    WebElement radiobuttonfemaletogetvalue;
    @FindBy(css = "div:nth-child(1)>div>div>div>div>div>div>div>i.bi-caret-down-fill")
    WebElement bloodtypelement;
    @FindBy(css = "form>:nth-child(1)>div>div>div>div>div>div>div.oxd-select-text-input")
    WebElement inputbloodtypeelement;
    @FindBy(css = "div.oxd-select-option")
    List<WebElement> listbloodtypeoptionelement;
    @FindBy(css = "h6~button.oxd-button")
    WebElement addattachementbutton;
    @FindBy(css = "div.oxd-file-button")
    WebElement inputuploadfilelement;
    @FindBy(css = "div.oxd-table>div.oxd-table-body>div>div>div.oxd-table-cell:nth-child(2)")
    WebElement filenameuploadedelement;
    public EmployeeDatailsPage(TestSetup paramtestetup){
        super(paramtestetup);
        PageFactory.initElements(testsetup.getDriver(),this);
        testsetup.getWait().until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.oxd-form-loader")));
        log.info("Initialize Employee Datail Page ");
    }
    public EmployeeDatailsPage clickSaveButton(){
        log.info("Save EmployeSecondary");
        testsetup.getWait().until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.oxd-form-loader")));
        testsetup.getWait().until(b -> savebuttonElemnt.isDisplayed());
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
    public EmployeeDatailsPage clickToProfil(){
        testsetup.getWait().until(ExpectedConditions.visibilityOf(profilelement));
        profilelement.click();
        log.info("Click to Profil");
        return this;
    }
    public LoginPage logOutbutton(){
        log.info("Logout");
        testsetup.getWait().until(d-> logoutElement.isDisplayed());
        logoutElement.click();
        return new LoginPage(testsetup);
    }
    public EmployeeDatailsPage typeBirthDate(String datebirth){
        log.info("type birth date");
        typeKeys(inputbirthdate,datebirth);
        return this;
    }
    public EmployeeDatailsPage clickGender(String param_gender){
        log.info("Set gender");
        if(param_gender.equals("M")){
            //testsetup.getWait().until(b -> radiobuttonmale.isDisplayed());
            radiobuttonmale.click();
            return this;
        }else if(param_gender.equals("F")){
            testsetup.getWait().until(b -> radiobuttonfemale.isDisplayed());
            radiobuttonfemale.click();
            return this;
        }else {
            log.error("Gender not defined correctly :" + param_gender );
            return this;
        }
    }
    public EmployeeDatailsPage clicktBloodListInput(){
        log.info(" select blood type ");
        clickwithWait(bloodtypelement);
        return this;
    }
    public EmployeeDatailsPage clickRancdomBloodtypeOption(){
        log.info(" select blood type ");
        clickwithWait(listbloodtypeoptionelement.get(getRandomBloodtype()));
        return this;
    }
    public int getRandomBloodtype(){
        return (int)(Math.random() * 8) + 1;
    }
    public EmployeeDatailsPage refreshPage(){
        log.info(" Refresh Page ");
        testsetup.getDriver().navigate().refresh();
        testsetup.getWait().
                until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.oxd-form-loader")));
        return this;
    }
    public String getBirtdaydate(){
        log.info(" Get birth daten ");
        return inputbirthdate.getAttribute("value");
    }
    public Boolean isRadioButtonMaleSelected(){
        log.info(" Is The user a man ");
        return radiobuttonmaletogetvalue.isSelected();
    }
    public Boolean isRadioButtonFemalealeSelected(){
        log.info(" Is The user a female ");
        return radiobuttonfemaletogetvalue.isSelected();
    }
    public String getBloodTypeValue(){
        log.info(" Get the blood type ");
        return inputbloodtypeelement.getText();
    }
    public EmployeeDatailsPage clickAddAttachement(){
        log.info("Click Add Button Attachement");
        clickwithWait(addattachementbutton);
        return this;
    }
    public EmployeeDatailsPage updaloadFile(File paramfile){
        log.info("Upload file");
        testsetup.getWait().until(ExpectedConditions.visibilityOf(inputuploadfilelement));
        inputuploadfilelement.sendKeys(paramfile.getAbsolutePath());
        return this;
    }
    public String getNameOfFileUploaded(){
        log.info(" Get name of file uploaded ");
        return filenameuploadedelement.getText();
    }

}
