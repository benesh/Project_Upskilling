package org.opensourcedemo.pagesobjects.adminpages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.opensourcedemo.core.driver_manager.TestSetup;
import org.opensourcedemo.pagesobjects.LoginPage;

@Log4j2
public class AdminPage {
    TestSetup testSetup;
    @FindBy(css = ".oxd-button--secondary:nth-child(1)")
    WebElement addadminelement;
    @FindBy(css = "a[href*='auth/logout'].oxd-userdropdown-link ")
    WebElement logoutElement;
    @FindBy(css = ".oxd-userdropdown-icon")
    WebElement profilelement;
    public AdminPage(TestSetup param_testsetup){
        testSetup = param_testsetup;
        PageFactory.initElements(testSetup.getDriver(),this);
        log.info("Initialize Admin Page");
    }
    public FormAddAdminUser clickAddAmdin(){
        testSetup.getWait().until(ExpectedConditions.visibilityOf(addadminelement));
        addadminelement.click();
        log.info("Click for Adding Admin");
        return new FormAddAdminUser(testSetup);
    }
    public String getTittle(){
        return testSetup.getDriver().getTitle();
    }
    public AdminPage clickToProfil(){
        testSetup.getWait().until(ExpectedConditions.visibilityOf(profilelement));
        profilelement.click();
        log.info("Click to Profil");
        return this;
    }
    public LoginPage logOutbutton(){
        testSetup.getWait().until(d-> logoutElement.isDisplayed());
        logoutElement.click();
        log.info("Logout");
        return new LoginPage(testSetup);
    }
}
