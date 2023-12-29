package org.opensourcedemo.pagesobjects.adminpages;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.opensourcedemo.pagesobjects.LoginPage;
import org.opensourcedemo.pagesobjects.BasePage;

@Log4j2
public class AdminPage extends BasePage {
    @FindBy(css = ".oxd-button--secondary:nth-child(1)")
    WebElement addadminelement;
    @FindBy(css = "a[href*='auth/logout'].oxd-userdropdown-link ")
    WebElement logoutElement;
    @FindBy(css = ".oxd-userdropdown-icon")
    WebElement profilelement;
    public AdminPage(){
        PageFactory.initElements(getDriver(),this);
        log.info("Initialize Admin Page");
    }
    public FormAddAdminUser clickAddAmdin(){
        waitOfVisibilityOf(addadminelement);
        clickElement(addadminelement);
        log.info("Click for Adding Admin");
        return new FormAddAdminUser();
    }
    public String getTittle(){
        return getDriver().getTitle();
    }
    public AdminPage clickToProfil(){
        invisibilityLoader();
        waitOfVisibilityOf(profilelement);
        clickElement(profilelement);
        log.info("Click to Profil");
        return this;
    }
    public LoginPage logOutbutton(){
        lamdaWaitIsDisplayed(logoutElement);
        clickElement(logoutElement);
        log.info("Logout");
        return new LoginPage();
    }
}
