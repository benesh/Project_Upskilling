package org.opensourcedemo.pagesobjects;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.opensourcedemo.core.driver_manager.TestSetup;
import org.opensourcedemo.pagesobjects.adminpages.AdminPage;
import org.opensourcedemo.pagesobjects.pimpages.PimPage;

@Log4j2
public class DashbordPage {
    TestSetup testsetup;
    @FindBy(css="a[href*='viewPimModule']")
    WebElement pimElement;
    @FindBy(css = "a[href*='viewAdminModule']")
    WebElement adminPageElement;
    public DashbordPage(TestSetup param_testsetoup){
        testsetup = param_testsetoup;
        PageFactory.initElements(testsetup.getDriver(),this);
        log.info("Initialize Dashbord Page");
    }
    public PimPage clickPimPage(){
        testsetup.getWait().until(ExpectedConditions.visibilityOf(pimElement));
        pimElement.click();
        log.info("Click sur la page PIM");
        return new PimPage(testsetup);
    }
    public AdminPage clickAdminPage(){
        testsetup.getWait().until(ExpectedConditions.visibilityOf(pimElement));
        adminPageElement.click();
        log.info("Click Admin Page");
        return new AdminPage(testsetup);
    }
    public String getTittle(){
        return testsetup.getDriver().getTitle();
    }
}
