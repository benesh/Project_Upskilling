package org.opensourcedemo.pagesobjects.adminpages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.opensourcedemo.core.webdriver_manager.TestSetup;
import org.opensourcedemo.pagesobjects.LoginPage;
import org.opensourcedemo.pagesobjects.PageObjectParent;

@Log4j2
public class AdminPage extends PageObjectParent {
    @FindBy(css = ".oxd-button--secondary:nth-child(1)")
    WebElement addadminelement;
    @FindBy(css = "a[href*='auth/logout'].oxd-userdropdown-link ")
    WebElement logoutElement;
    @FindBy(css = ".oxd-userdropdown-icon")
    WebElement profilelement;
    public AdminPage(TestSetup param_testsetup){
        super(param_testsetup);
        PageFactory.initElements(testsetup.getDriver(),this);
        log.info("Initialize Admin Page");
    }
    public FormAddAdminUser clickAddAmdin(){
        testsetup.getWait().until(ExpectedConditions.visibilityOf(addadminelement));
        addadminelement.click();
        log.info("Click for Adding Admin");
        return new FormAddAdminUser(testsetup);
    }
    public String getTittle(){
        return testsetup.getDriver().getTitle();
    }
    public AdminPage clickToProfil(){
        testsetup.getWait().until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.oxd-form-loader")));
        testsetup.getWait().until(ExpectedConditions.visibilityOf(profilelement));
        profilelement.click();
        log.info("Click to Profil");
        return this;
    }
    public LoginPage logOutbutton(){
        testsetup.getWait().until(d-> logoutElement.isDisplayed());
        logoutElement.click();
        log.info("Logout");
        return new LoginPage(testsetup);
    }
}
