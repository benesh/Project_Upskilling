package org.opensourcedemo.pagesobjects.myinfopage;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.opensourcedemo.pagesobjects.BasePage;

import java.io.File;
import java.util.List;


@Log4j2
public class MyInfoPage extends BasePage {
    @FindBy(css = "h6~button.oxd-button")
    WebElement addattachementbutton;
    @FindBy(css = "input.oxd-file-input[type=\"file\"]")
    WebElement inputuploadfilelement;
    @FindBy(css = "div.oxd-table-body>div>div.oxd-table-row[role=\"row\"]>div:nth-child(2)>div")
    List<WebElement> listnameuploadedfile;
    @FindBy(css = "div.oxd-table-body>div>div.oxd-table-row[role=\"row\"]>div:nth-child(6)>div")
    List<WebElement> listdateoffileuploaded;
    @FindBy(css = "div.oxd-form-actions>p~button.oxd-button[type=\"submit\"]:nth-child(3)")
    WebElement buttonsaveforuploadedfile;
    @FindBy(css = "div.oxd-toast-icon-wrap--success")
    WebElement iconAlertSucsess;
    @FindBy(css = "div.oxd-toast-content--success")
    WebElement printSuccessMessage;
    public MyInfoPage(){
        PageFactory.initElements(getDriver(),this);
        invisibilityLoader();
        log.info("Initalize My Ifno page ");
    }
    public MyInfoPage clickAddAttachement(){
        log.info("Click Add Button Attachement");
        waitOfVisibilityOf(addattachementbutton);
        clickElement(addattachementbutton);
        return this;
    }
    public MyInfoPage updaloadFile(String paramfile){
        log.info("Upload file");
        File fileob = new File(paramfile);
        inputuploadfilelement.sendKeys(fileob.getAbsolutePath());
        return this;
    }
    public MyInfoPage clickSaveButtonForFileUploaded(){
        log.info("Click for file updloaded");
        clickElement(buttonsaveforuploadedfile);
        return this;
    }
    public MyInfoPage handlerSuccessAlert(){
        log.info("Verification de l'alerte succès");
        waitOfVisibilityOf(iconAlertSucsess);
        log.info("Success of uploading");
        return this;
    }
    public int verifyIfIsUploaded(String paramnamefile){
        invisibilityLoader();
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
