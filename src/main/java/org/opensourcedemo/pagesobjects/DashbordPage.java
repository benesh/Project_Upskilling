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
    WebElement pimPage;
    @FindBy(css = "a[href*='viewAdminModule']")
    WebElement adminPage;
    @FindBy(css = ".oxd-userdropdown-name")
    WebElement profilNameComplet;
    @FindBy(css = "a[href*='viewTimeModule']")
    WebElement timePage;
    @FindBy(css = "a[href*='viewMyDetails']")
    WebElement myInfoPage;
    public DashbordPage(){
        PageFactory.initElements(getDriver(),this);
        log.info("Initialize Dashbord Page");
    }
    public PimPage clickPimPage(){
        log.info("Click sur la page PIM");
        lamdaWaitIsDisplayed(pimPage);
        clickElement(pimPage);

        return new PimPage();
    }
    public AdminPage clickAdminPage(){
        log.info("Click Admin Page");
        lamdaWaitIsDisplayed(adminPage);
        clickElement(adminPage);
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
        lamdaWaitIsDisplayed(timePage);
        clickElement(timePage);
        return new TimePage();
    }
    public MyInfoPage clickMyInfoPage(){
        log.info("Click my info page");
        lamdaWaitIsDisplayed(myInfoPage);
        clickElement(myInfoPage);
        return new MyInfoPage();
    }
}
