package org.opensourcedemo.pagesobjects;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.opensourcedemo.core.driver_manager.TestSetup;


@Log4j2
public class LoginPage {
    TestSetup testsetup;

    @FindBy(css="input[name=\"username\"]")
    private WebElement inputUsername;

    @FindBy(css="input[name=\"password\"]")
    WebElement inputPassword;

    @FindBy(css="button[type=\"submit\"]")
    WebElement bontouLogin;

    public LoginPage(TestSetup paramtestsetup){
        testsetup = paramtestsetup;
        testsetup.getWait().ignoring(ElementClickInterceptedException.class, java.util.NoSuchElementException.class);
        PageFactory.initElements(testsetup.getDriver(),this);
        log.info("Initialize Login Page");
    }
    public LoginPage inputUserName(String parma_unsername ){
        testsetup.getWait().until(ExpectedConditions.visibilityOf(inputUsername));
        log.info("Typing user name " + parma_unsername);
        inputUsername.sendKeys(parma_unsername);
        return this;
    }
    public LoginPage inputPwd(String parma_password ){
        log.info("Typing user Password ");
        testsetup.getWait().until(ExpectedConditions.visibilityOf(inputPassword));
        inputPassword.sendKeys(parma_password);
        return this;
    }
    public DashbordPage clickButtonLogin(){
        testsetup.getWait().until(b -> bontouLogin.isDisplayed());
        bontouLogin.click();
        return new DashbordPage(testsetup);
    }
}
