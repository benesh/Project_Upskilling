package org.opensourcedemo.pagesobjects;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.opensourcedemo.pagesobjects.adminpages.AdminPage;
import org.opensourcedemo.pagesobjects.myinfopage.MyInfoPage;
import org.opensourcedemo.pagesobjects.pimpages.PimPage;
import org.opensourcedemo.pagesobjects.time.TimePage;

@Log4j2
public class DashbordPage extends BasePage {
    @FindBy(css="a[href*='viewPimModule']")
    WebElement pimElement;
    @FindBy(css = "a[href*='viewAdminModule']")
    WebElement adminPageElement;
    @FindBy(css = ".oxd-userdropdown-name")
    WebElement profilNameComplet;
    @FindBy(css = "a[href*='viewTimeModule']")
    WebElement timePageElemennt;
    @FindBy(css = "a[href*='viewMyDetails']")
    WebElement myInfoPage;
    public DashbordPage(){
        PageFactory.initElements(getDriver(),this);
        log.info("Initialize Dashbord Page");
    }
    public PimPage clickPimPage(){
        log.info("Click sur la page PIM");
        lamdaWaitIsDisplayed(pimElement);
        clickElement(pimElement);

        return new PimPage();
    }
    public AdminPage clickAdminPage(){
        log.info("Click Admin Page");
        lamdaWaitIsDisplayed(adminPageElement);
        clickElement(adminPageElement);
        return new AdminPage();
    }
    public String getTittle(){
        return getDriver().getTitle();
    }
    public  String getNameProfil(){
        log.info("Get Complete profil");
        waitOfVisibilityOf(profilNameComplet);
        return profilNameComplet.getText();
    }
    public TimePage clickTimePage(){
        log.info("Click Time Page");
        lamdaWaitIsDisplayed(timePageElemennt);
        clickElement(timePageElemennt);
        return new TimePage();
    }
    public MyInfoPage clickMyInfoPage(){
        log.info("Click my info page");
        lamdaWaitIsDisplayed(myInfoPage);
        clickElement(myInfoPage);
        return new MyInfoPage();
    }
}
