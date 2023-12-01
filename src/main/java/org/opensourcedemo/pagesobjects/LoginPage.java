package org.opensourcedemo.pagesobjects;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.opensourcedemo.core.properties_manager.GlobalConfig;

import java.time.Duration;


@Log4j2
public class LoginPage {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(css="input[name=\"username\"]")
    private WebElement inputUsername;

    @FindBy(css="input[name=\"password\"]")
    WebElement inputPassword;

    @FindBy(css="button[type=\"submit\"]")
    WebElement bontouLogin;

    public LoginPage(WebDriver paramDriver){
        driver = paramDriver;
        PageFactory.initElements(driver,this);
        wait =  new WebDriverWait(driver, Duration.ofSeconds(GlobalConfig.GLOBALWAIT));
        log.info("Initialize Login Page");
    }

    public LoginPage inputUserName(String parma_unsername ){
        wait.until(ExpectedConditions.visibilityOfAllElements(inputUsername));
        log.info("Typing user name " + parma_unsername);
        inputUsername.sendKeys(parma_unsername);
        return this;
    }
    public LoginPage inputPwd(String parma_password ){
        log.info("Typing user Password ");
        wait.until(ExpectedConditions.visibilityOfAllElements(inputPassword));
        inputPassword.sendKeys(parma_password);
        return this;
    }

    public DashbordPage clickButtonLogin(){
        bontouLogin.click();
        return new DashbordPage(driver);
    }

}
