package org.opensourcedemo.pagesobjects.myinfopage;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.opensourcedemo.core.driver_manager.TestSetup;
import org.opensourcedemo.pagesobjects.PageObjectParent;
import org.opensourcedemo.pagesobjects.pimpages.EmployeeDatailsPage;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Log4j2
public class MyInfoPage extends PageObjectParent {

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
    @FindBy(css = "div.oxd-table-body>div>div.oxd-table-row[role=\"row\"]>div:nth-child(2)>div")
    List<WebElement> listnameuploadedfile;

    @FindBy(css = "div.oxd-table-body>div>div.oxd-table-row[role=\"row\"]>div:nth-child(6)>div")
    List<WebElement> listdateoffileuploaded;

    public MyInfoPage(TestSetup paramtesteur){
        super(paramtesteur);
        PageFactory.initElements(testsetup.getDriver(),this);
        testsetup.getWait().until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.oxd-form-loader")));
        log.info("Initalize My Ifno page ");
    }

    public MyInfoPage typeBirthDate(String datebirth){
        log.info("type birth date");
        typeKeys(inputbirthdate,datebirth);
        return this;
    }
    public MyInfoPage clickGender(String param_gender){
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
    public MyInfoPage clicktBloodListInput(){
        log.info(" select blood type ");
        clickwithWait(bloodtypelement);
        return this;
    }
    public MyInfoPage clickRancdomBloodtypeOption(){
        log.info(" select blood type ");
        clickwithWait(listbloodtypeoptionelement.get(getRandomBloodtype()));
        return this;
    }
    public int getRandomBloodtype(){
        return (int)(Math.random() * 8) + 1;
    }
    public MyInfoPage refreshPage(){
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
    public MyInfoPage clickAddAttachement(){
        log.info("Click Add Button Attachement");
        clickwithWait(addattachementbutton);
        return this;
    }
    public MyInfoPage updaloadFile(File paramfile){
        log.info("Upload file");
        testsetup.getWait().until(ExpectedConditions.visibilityOf(inputuploadfilelement));
        inputuploadfilelement.sendKeys(paramfile.getAbsolutePath());
        return this;
    }
    public int verifyIfIsUploaded(String paramnamefile){
        log.info(" Get name of file uploaded ");
        int index = 0;
         for(WebElement element : listnameuploadedfile){
             if (element.getText().equals(paramnamefile)){
                 return index;
             }else{
                 index ++;
             }
         }
         return -1;
    }
    public String getFileDateUploaded(int index){
        log.info("Get date of file uploaded");
        return listdateoffileuploaded.get(index).getText();
    }

}
